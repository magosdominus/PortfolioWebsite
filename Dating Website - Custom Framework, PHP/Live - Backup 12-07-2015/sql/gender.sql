/*File: gender.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender property/value table
*/
DROP TABLE IF EXISTS gender;

CREATE TABLE gender(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO gender (value, property) VALUES (1, 'Male');

INSERT INTO gender (value, property) VALUES (2, 'Female');