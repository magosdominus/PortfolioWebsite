/*
Name: Gemlawala Maitri Rajeshbhai
WebD 2201
Date: 13-06-2015
*/


DROP TABLE IF EXISTS users;
 
CREATE TABLE users(
user_id VARCHAR(20)       PRIMARY KEY,
password VARCHAR(32)     NOT NULL,
user_type VARCHAR(1)     NOT NULL,
email_address VARCHAR(256) NOT NULL,
first_name VARCHAR(128)   NOT NULL,
last_name VARCHAR(128)    NOT NULL,
birth_date DATE  NOT NULL,
enrol_date DATE  NOT NULL,
last_access DATE NOT NULL);
