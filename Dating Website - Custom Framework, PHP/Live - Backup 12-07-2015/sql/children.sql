/*File: Children.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender property/value table
*/

DROP TABLE IF EXISTS Children;

CREATE TABLE Children(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO Children (value, property) VALUES (1, 'Yes');

INSERT INTO Children (value, property) VALUES (2, 'No');