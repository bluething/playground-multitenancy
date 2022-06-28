Sql  
```sql
SET search_path TO public;
CREATE TABLE tenant (
	tenant_id VARCHAR(4) PRIMARY KEY,
    schema VARCHAR(30)
);
INSERT INTO tenant(tenant_id, schema) VALUES('T001', 't001');
INSERT INTO tenant(tenant_id, schema) VALUES('T002', 't002');

CREATE SCHEMA IF NOT EXISTS t001;
CREATE SCHEMA IF NOT EXISTS t002;

CREATE TABLE department (
	department_id VARCHAR(4) PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE employee (
	employee_id VARCHAR(4) PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
	department_id VARCHAR(4) NOT NULL REFERENCES department (department_id) ON DELETE SET NULL
);

SET search_path TO t001;
INSERT INTO department(department_id, name) VALUES('D001', 'Department 1');
INSERT INTO department(department_id, name) VALUES('D002', 'Department 2');
INSERT INTO employee(employee_id, name, department_id) VALUES('E001', 'Sebut saja Bunga', 'D001');
INSERT INTO employee(employee_id, name, department_id) VALUES('E002', 'Sebut saja Budi', 'D002');
INSERT INTO employee(employee_id, name, department_id) VALUES('E003', 'Sebut saja Joko', 'D002');
SELECT * FROM department d JOIN employee e ON d.department_id=e.department_id;

SET search_path TO t002;
INSERT INTO department(department_id, name) VALUES('D100', 'Ghibah club');
INSERT INTO department(department_id, name) VALUES('D200', 'Mager club');
INSERT INTO employee(employee_id, name, department_id) VALUES('E100', 'Si Panjul', 'D100');
INSERT INTO employee(employee_id, name, department_id) VALUES('E200', 'Si Bopeng', 'D100');
INSERT INTO employee(employee_id, name, department_id) VALUES('E300', 'Si Codet', 'D200');
SELECT * FROM department d JOIN employee e ON d.department_id=e.department_id;
```

For test  
```text
curl -H "X-TENANT-ID: T001" localhost:8080/departments
curl -H "X-TENANT-ID: T002" localhost:8080/departments
```

Get and release connection each time we request  
```text
2022-06-28 16:12:51.264  INFO 205009 --- [nio-8080-exec-4] SchemaBasedMultiTenantConnectionProvider : Get connection for tenant T001
2022-06-28 16:12:51.272  INFO 205009 --- [nio-8080-exec-4] SchemaBasedMultiTenantConnectionProvider : Release connection for tenant T001
2022-06-28 16:15:32.271  INFO 205009 --- [nio-8080-exec-6] SchemaBasedMultiTenantConnectionProvider : Get connection for tenant T002
2022-06-28 16:15:32.277  INFO 205009 --- [nio-8080-exec-6] SchemaBasedMultiTenantConnectionProvider : Release connection for tenant T002
```