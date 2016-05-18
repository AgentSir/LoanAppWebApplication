DROP TABLE client IF EXISTS;

CREATE TABLE client (
	client_id BIGINT IDENTITY NOT NULL,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	PRIMARY KEY (client_id)
);

DROP TABLE loan_application IF EXISTS;

CREATE TABLE loan_application (
	loan_application_id BIGINT IDENTITY NOT NULL,
        first_name VARCHAR(255),
	last_name VARCHAR(255),
	amount DECIMAL(19,2),
	client_id BIGINT,
	loan_application_date TIMESTAMP NOT NULL,
	ip_address VARCHAR(255),
	status INTEGER,
	term TIMESTAMP NOT NULL,
	PRIMARY KEY (loan_application_id)
);

