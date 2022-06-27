package io.github.bluething.playground.multitenancy.imperative.config.db;

import io.github.bluething.playground.multitenancy.imperative.config.multitenancy.TenantContext;
import org.springframework.jdbc.datasource.ConnectionProxy;
import org.springframework.jdbc.datasource.DelegatingDataSource;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TenantAwareDataSource extends DelegatingDataSource {
    public TenantAwareDataSource(DataSource targetDatasource) {
        super(targetDatasource);
    }

    @Override
    public Connection getConnection() throws SQLException {
        final Connection connection = getTargetDataSource().getConnection();
        setTenantId(connection);
        return getTenantAwareConnectionProxy(connection);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        final Connection connection = getTargetDataSource().getConnection(username, password);
        setTenantId(connection);
        return getTenantAwareConnectionProxy(connection);
    }

    // Every time the app asks the data source for a connection, set the PostgreSQL session
    // variable to the tenant id to enforce data isolation.
    private void setTenantId(Connection connection) throws SQLException {
        try(Statement sql = connection.createStatement()) {
            String tenantId = TenantContext.getTenantId();
            sql.execute("SET app.current_tenant TO '" + tenantId + "'");
        }
    }
    // When the connection is closed, clear tenant id from PostgreSQL session
    private void clearTenantId(Connection connection) throws SQLException {
        try(Statement sql = connection.createStatement()) {
            sql.execute("RESET app.current_tenant");
        }
    }

    // Connection Proxy that intercepts close() to reset the tenant_id
    protected Connection getTenantAwareConnectionProxy(Connection connection) {
        return (Connection) Proxy.newProxyInstance(
                ConnectionProxy.class.getClassLoader(),
                new Class[] {ConnectionProxy.class},
                new TenantAwareDataSource.TenantAwareInvocationHandler(connection));
    }

    // Connection Proxy invocation handler that intercepts close() to reset the tenant_id
    private class TenantAwareInvocationHandler implements InvocationHandler {
        private final Connection target;

        private TenantAwareInvocationHandler(Connection target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            switch (method.getName()) {
                case "equals":
                    return proxy == args[0];
                case "hashCode":
                    return System.identityHashCode(proxy);
                case "toString":
                    return "Tenant-aware proxy for target Connection [" + this.target.toString() + "]";
                case "unwrap":
                    if (((Class) args[0]).isInstance(proxy)) {
                        return proxy;
                    } else {
                        return method.invoke(target, args);
                    }
                case "isWrapperFor":
                    if (((Class) args[0]).isInstance(proxy)) {
                        return true;
                    } else {
                        return method.invoke(target, args);
                    }
                case "getTargetConnection":
                    return target;
                default:
                    if (method.getName().equals("close")) {
                        clearTenantId(target);
                    }
                    return method.invoke(target, args);
            }
        }
    }
}
