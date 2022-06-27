SQL  
```sql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tenant (
	id VARCHAR(4) PRIMARY KEY,
    tenant_id VARCHAR(4) NOT NULL,
    name VARCHAR(255) UNIQUE,
    status VARCHAR(64) CHECK (status IN ('active', 'suspended', 'disabled')),
    tier VARCHAR(64) CHECK (tier IN ('gold', 'silver', 'bronze'))
);
CREATE INDEX idx_tenant_tenantId ON tenant(tenant_id);

CREATE TABLE tenant_user (
    user_id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    tenant_ref VARCHAR(4) NOT NULL REFERENCES tenant (id) ON DELETE RESTRICT,
	tenant_id VARCHAR(4) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    given_name VARCHAR(255) NOT NULL CHECK (given_name <> ''),
    family_name VARCHAR(255) NOT NULL CHECK (family_name <> '')
);

ALTER TABLE tenant ENABLE ROW LEVEL SECURITY;
ALTER TABLE tenant_user ENABLE ROW LEVEL SECURITY;

CREATE POLICY tenant_isolation_policy ON tenant
USING (tenant_id = current_setting('app.current_tenant')::VARCHAR);
CREATE POLICY tenant_user_isolation_policy ON tenant_user
USING (tenant_id = current_setting('app.current_tenant')::VARCHAR);

INSERT INTO tenant(id, tenant_id, name, status, tier) VALUES('0001', 'T001', 'tenant_1', 'active', 'gold');
INSERT INTO tenant(id, tenant_id, name, status, tier) VALUES('0002', 'T002', 'tenant_2', 'active', 'bronze');

INSERT INTO tenant_user(tenant_ref, tenant_id, email, given_name, family_name) VALUES('0001', 'T001', 'user1@email.com', 'User', 'One');
INSERT INTO tenant_user(tenant_ref, tenant_id, email, given_name, family_name) VALUES('0001', 'T001', 'user2@email.com', 'User', 'Two');
INSERT INTO tenant_user(tenant_ref, tenant_id, email, given_name, family_name) VALUES('0002', 'T002', 'user3@email.com', 'User', 'Three');

CREATE ROLE "mt-rls-user" WITH
    LOGIN
    NOSUPERUSER
    INHERIT
    NOCREATEDB
    NOCREATEROLE
    NOREPLICATION
    ENCRYPTED PASSWORD 'SCRAM-SHA-256$4096:sQwmvL46L4Wjnrg0X6KvQA==$LD9Y5ec/iW4Gkk1RQQVfYga/7/nXj38YPiN93Q1ac+Y=:V4rBv0Pko4zbV/DbLJ0IfMZjKeo3MNbSkkp1Jyh0of8=';

GRANT ALL PRIVILEGES ON DATABASE "mt-rls" TO "mt-rls-user";
GRANT ALL PRIVILEGES ON TABLE "tenant" TO "mt-rls-user";
GRANT ALL PRIVILEGES ON TABLE "tenant_user" TO "mt-rls-user";
```

To test  
```text
curl -H "X-TENANT-ID: T001" localhost:8080/tenant/T001
curl -H "X-TENANT-ID: T002" localhost:8080/tenant/T002
```