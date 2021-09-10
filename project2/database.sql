-- DROP SCHEMA "Reimbursement";

CREATE SCHEMA "Reimbursement" AUTHORIZATION postgres;




-- "Reimbursement".employee definition

-- Drop table

-- DROP TABLE "Reimbursement".employee;

CREATE TABLE "Reimbursement".employee (
	employee_id serial NOT NULL,
	username varchar NOT NULL,
	email varchar NOT NULL,
	"password" varchar NOT NULL,
	"role" varchar NOT NULL,
	first_name varchar NOT NULL,
	last_name varchar NOT NULL,
	CONSTRAINT employee_pk PRIMARY KEY (employee_id)
);

-- "Reimbursement".ticket definition

-- Drop table

-- DROP TABLE "Reimbursement".ticket;

CREATE TABLE "Reimbursement".ticket (
	ticket_id serial NOT NULL,
	employee_id int4 NOT NULL,
	"type" varchar NOT NULL,
	description varchar NOT NULL,
	amount float4 NOT NULL,
	"time" timestamp NOT NULL,
	status varchar NOT NULL,
	CONSTRAINT ticket_pk PRIMARY KEY (ticket_id)
);


-- "Reimbursement".ticket foreign keys

ALTER TABLE "Reimbursement".ticket ADD CONSTRAINT ticket_fk FOREIGN KEY (employee_id) REFERENCES "Reimbursement".employee(employee_id);



-- "Reimbursement".employee_id_seq definition

-- DROP SEQUENCE "Reimbursement".employee_id_seq;

CREATE SEQUENCE "Reimbursement".employee_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- "Reimbursement".ticket_id_seq definition

-- DROP SEQUENCE "Reimbursement".ticket_id_seq;

CREATE SEQUENCE "Reimbursement".ticket_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
