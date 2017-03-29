/*
Author: Matthew Cormier
Filename: interests.sql
Wede 3201

Discription: this table will hold login account information for users.
*/

DROP TABLE IF EXISTS interests CASCADE;

CREATE TABLE interests (
	user_id VARCHAR(20) NOT NULL,
	interest_id VARCHAR(20) NOT NULL
);

