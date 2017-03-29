/*File: Religion.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender property/value table
*/

DROP TABLE IF EXISTS Religion;

CREATE TABLE Religion(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO Religion (value, property) VALUES (1, 'Christianity');

INSERT INTO Religion (value, property) VALUES (2, 'Islam');

INSERT INTO Religion (value, property) VALUES (4, 'Hinduism');

INSERT INTO Religion (value, property) VALUES (8, 'Judaism');

INSERT INTO Religion (value, property) VALUES (16, 'Agnosticism/Atheistism ');

INSERT INTO Religion (value, property) VALUES (32, 'Other');