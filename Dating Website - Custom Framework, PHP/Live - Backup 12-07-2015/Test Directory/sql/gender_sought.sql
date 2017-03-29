/*File: gender_sought.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender_sought property/value table
*/
DROP TABLE IF EXISTS gender_sought;

CREATE TABLE gender_sought(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO gender_sought (value, property) VALUES (1, 'Male');

INSERT INTO gender_sought (value, property) VALUES (2, 'Female');