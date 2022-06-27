package io.github.bluething.playground.multitenancy.imperative.config.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean
    @ConfigurationProperties("multitenancy.master.datasource")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("multitenancy.tenant.datasource")
    public DataSourceProperties tenantDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("multitenancy.tenant.datasource.hikari")
    public DataSource tenantDataSource() {
        HikariDataSource dataSource = tenantDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        dataSource.setPoolName("tenantDataSource");
        return new TenantAwareDataSource(dataSource);
    }
}
