/*
Author: Matthew Cormier
Filename: users.sql
Wede 3201

Discription: this table will hold login account information for users.
*/

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
	user_id VARCHAR(20) PRIMARY KEY,
	password VARCHAR(32) NOT NULL,
	user_type CHAR(1) NOT NULL,
	email_address VARCHAR(256) NOT NULL,
	first_name VARCHAR(128) NOT NULL,
	last_name VARCHAR(128) NOT NULL,
	birth_date DATE NOT NULL,
	enrol_date DATE NOT NULL,
	last_access DATE NOT NULL
);


