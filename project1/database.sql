CREATE SCHEMA reimbursement AUTHORIZATION postgres;


-- reimbursement.employee_id_seq definition

-- DROP SEQUENCE reimbursement.employee_id_seq;

CREATE SEQUENCE reimbursement.employee_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- reimbursement.login_id_seq definition

-- DROP SEQUENCE reimbursement.login_id_seq;

CREATE SEQUENCE reimbursement.login_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- reimbursement.personal_id_seq definition

-- DROP SEQUENCE reimbursement.personal_id_seq;

CREATE SEQUENCE reimbursement.personal_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- reimbursement.reimburse_request_id definition

-- DROP SEQUENCE reimbursement.reimburse_request_id;

CREATE SEQUENCE reimbursement.reimburse_request_id
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- reimbursement.login_information definition

-- Drop table

-- DROP TABLE reimbursement.login_information;

CREATE TABLE reimbursement.login_information (
	id int4 NOT NULL,
	email varchar NOT NULL,
	"password" varchar NOT NULL,
	salt varchar NULL,
	CONSTRAINT newtable_pk PRIMARY KEY (id),
	CONSTRAINT newtable_un UNIQUE (email)
);








-- reimbursement.employee_information definition

-- Drop table

-- DROP TABLE reimbursement.employee_information;

CREATE TABLE reimbursement.employee_information (
	employee_id int4 NOT NULL,
	login_id int4 NOT NULL,
	"role" varchar NOT NULL,
	CONSTRAINT employee_information_pk PRIMARY KEY (employee_id)
);


-- reimbursement.employee_information foreign keys

ALTER TABLE reimbursement.employee_information ADD CONSTRAINT employee_information_fk FOREIGN KEY (login_id) REFERENCES reimbursement.login_information(id);






-- reimbursement.personal_information definition

-- Drop table

-- DROP TABLE reimbursement.personal_information;

CREATE TABLE reimbursement.personal_information (
	personal_id int4 NOT NULL,
	employee_id int4 NOT NULL,
	first_name varchar NOT NULL,
	address varchar NOT NULL,
	city varchar NOT NULL,
	state varchar(2) NOT NULL,
	zipcode varchar NOT NULL,
	phonenumber varchar NOT NULL,
	last_name varchar NOT NULL,
	CONSTRAINT personal_information_pk PRIMARY KEY (personal_id)
);


-- reimbursement.personal_information foreign keys

ALTER TABLE reimbursement.personal_information ADD CONSTRAINT personal_information_fk FOREIGN KEY (employee_id) REFERENCES reimbursement.employee_information(employee_id);






-- reimbursement.reimbursement_requests definition

-- Drop table

-- DROP TABLE reimbursement.reimbursement_requests;

CREATE TABLE reimbursement.reimbursement_requests (
	request_id int4 NOT NULL,
	employee_id int4 NOT NULL,
	"type" varchar NOT NULL,
	description varchar NOT NULL,
	amount float4 NOT NULL,
	"time_stamp" timestamp NOT NULL,
	status varchar NOT NULL,
	CONSTRAINT reimbursement_requests_pk PRIMARY KEY (request_id)
);


-- reimbursement.reimbursement_requests foreign keys

ALTER TABLE reimbursement.reimbursement_requests ADD CONSTRAINT reimbursement_requests_fk FOREIGN KEY (employee_id) REFERENCES reimbursement.employee_information(employee_id);


