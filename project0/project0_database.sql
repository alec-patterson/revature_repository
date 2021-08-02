
-- DROP SCHEMA "BankingSystem";

CREATE SCHEMA "BankingSystem" AUTHORIZATION postgres;





-- "BankingSystem".account_id_seq definition

-- DROP SEQUENCE "BankingSystem".account_id_seq;

CREATE SEQUENCE "BankingSystem".account_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- "BankingSystem".account_information_seq definition

-- DROP SEQUENCE "BankingSystem".account_information_seq;

CREATE SEQUENCE "BankingSystem".account_information_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- "BankingSystem".accounts_account_id_seq definition

-- DROP SEQUENCE "BankingSystem".accounts_account_id_seq;

CREATE SEQUENCE "BankingSystem".accounts_account_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- "BankingSystem".bank_id_seq definition

-- DROP SEQUENCE "BankingSystem".bank_id_seq;

CREATE SEQUENCE "BankingSystem".bank_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- "BankingSystem".customer_bank_information_bank_id_seq definition

-- DROP SEQUENCE "BankingSystem".customer_bank_information_bank_id_seq;

CREATE SEQUENCE "BankingSystem".customer_bank_information_bank_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- "BankingSystem".employee_id_seq definition

-- DROP SEQUENCE "BankingSystem".employee_id_seq;

CREATE SEQUENCE "BankingSystem".employee_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- "BankingSystem".employee_information_employee_id_seq definition

-- DROP SEQUENCE "BankingSystem".employee_information_employee_id_seq;

CREATE SEQUENCE "BankingSystem".employee_information_employee_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- "BankingSystem".login_information_id_seq definition

-- DROP SEQUENCE "BankingSystem".login_information_id_seq;

CREATE SEQUENCE "BankingSystem".login_information_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- "BankingSystem".transaction_seq definition

-- DROP SEQUENCE "BankingSystem".transaction_seq;

CREATE SEQUENCE "BankingSystem".transaction_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- "BankingSystem".transactions_transaction_id_seq definition

-- DROP SEQUENCE "BankingSystem".transactions_transaction_id_seq;

CREATE SEQUENCE "BankingSystem".transactions_transaction_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- "BankingSystem".user_login_seq definition

-- DROP SEQUENCE "BankingSystem".user_login_seq;

CREATE SEQUENCE "BankingSystem".user_login_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;






-- "BankingSystem".login_information definition

-- Drop table

-- DROP TABLE "BankingSystem".login_information;

CREATE TABLE "BankingSystem".login_information (
	id serial NOT NULL,
	username varchar NOT NULL,
	email varchar NOT NULL,
	"password" varchar NOT NULL,
	CONSTRAINT login_information_pk PRIMARY KEY (id)
);


-- "BankingSystem".states definition

-- Drop table

-- DROP TABLE "BankingSystem".states;

CREATE TABLE "BankingSystem".states (
	state varchar(2) NOT NULL
);


-- "BankingSystem".customer_accounts definition

-- Drop table

-- DROP TABLE "BankingSystem".customer_accounts;

CREATE TABLE "BankingSystem".customer_accounts (
	account_id int4 NOT NULL DEFAULT nextval('"BankingSystem".accounts_account_id_seq'::regclass),
	login_id int4 NOT NULL,
	date_joined date NOT NULL,
	"needs approval" bool NOT NULL DEFAULT true,
	"role" varchar NOT NULL DEFAULT 'customer'::character varying,
	CONSTRAINT accounts_pk PRIMARY KEY (account_id),
	CONSTRAINT accounts_fk FOREIGN KEY (login_id) REFERENCES "BankingSystem".login_information(id)
);


-- "BankingSystem".customer_bank_information definition

-- Drop table

-- DROP TABLE "BankingSystem".customer_bank_information;

CREATE TABLE "BankingSystem".customer_bank_information (
	checking float4 NOT NULL DEFAULT 0,
	saving float4 NOT NULL DEFAULT 0,
	account_id int4 NOT NULL,
	bank_id int4 NOT NULL DEFAULT nextval('"BankingSystem".bank_id_seq'::regclass),
	CONSTRAINT customer_bank_information_pk PRIMARY KEY (bank_id),
	CONSTRAINT customer_bank_information_fk FOREIGN KEY (account_id) REFERENCES "BankingSystem".customer_accounts(account_id)
);


-- "BankingSystem".customer_personal_information definition

-- Drop table

-- DROP TABLE "BankingSystem".customer_personal_information;

CREATE TABLE "BankingSystem".customer_personal_information (
	account_id int4 NOT NULL,
	"name" varchar NOT NULL,
	address varchar NOT NULL,
	city varchar NOT NULL,
	state varchar(2) NOT NULL,
	zip_code varchar NOT NULL,
	phone_number varchar NULL,
	CONSTRAINT customer_personal_information_fk FOREIGN KEY (account_id) REFERENCES "BankingSystem".customer_accounts(account_id)
);


-- "BankingSystem".employee_information definition

-- Drop table

-- DROP TABLE "BankingSystem".employee_information;

CREATE TABLE "BankingSystem".employee_information (
	employee_id serial NOT NULL,
	login_id int4 NOT NULL,
	salary float4 NOT NULL DEFAULT 70000,
	CONSTRAINT employee_information_pk PRIMARY KEY (employee_id),
	CONSTRAINT employee_information_fk FOREIGN KEY (login_id) REFERENCES "BankingSystem".login_information(id)
);


-- "BankingSystem".transactions definition

-- Drop table

-- DROP TABLE "BankingSystem".transactions;

CREATE TABLE "BankingSystem".transactions (
	transaction_id serial NOT NULL,
	bank_account_id int4 NOT NULL,
	description varchar NOT NULL,
	"operator" varchar(1) NOT NULL,
	amount float4 NOT NULL,
	account varchar NOT NULL,
	CONSTRAINT transactions_pk PRIMARY KEY (transaction_id),
	CONSTRAINT transactions_fk FOREIGN KEY (bank_account_id) REFERENCES "BankingSystem".customer_bank_information(bank_id)
);

insert into "BankingSystem".login_information (username, email, password) values ('admin', 'admin@bank.com', 'admin');
insert into "BankingSystem".customer_accounts (login_id, date_joined, role) values ((select id from "BankingSystem".login_information where username = 'admin'), now(), 'admin');
insert into "BankingSystem".customer_personal_information (account_id, name, address, city, state, zip_code, phone_number) values ((select account_id from "BankingSystem".customer_accounts ca inner join "BankingSystem".login_information li on li.id = ca.login_id where li.username = 'admin'), 'bank admin', '4800 project zero bank', 'Las Vegas', 'NV', '234902', '7028771500');
insert into "BankingSystem".customer_bank_information (account_id) values ((select account_id from "BankingSystem".customer_accounts ca inner join "BankingSystem".login_information li on li.id = ca.login_id where li.username = 'admin'));
insert into "BankingSystem".employee_information (login_id, salary) values ((select id from "BankingSystem".login_information where username = 'admin'), 1000000);
