/*File: city.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create cuty property/value table
*/
DROP TABLE IF EXISTS city;

CREATE TABLE city(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO city (value, property) VALUES (1, 'Prefer Not To Say');

INSERT INTO city (value, property) VALUES (2, 'Ajax');

INSERT INTO city (value, property) VALUES (4, 'Brooklin');

INSERT INTO city (value, property) VALUES (8, 'Bowmanville');

INSERT INTO city (value, property) VALUES (16, 'Oshawa');

INSERT INTO city (value, property) VALUES (32, 'Pickering');

INSERT INTO city (value, property) VALUES (64, 'Port Perry');

INSERT INTO city (value, property) VALUES (128, 'Whitby');