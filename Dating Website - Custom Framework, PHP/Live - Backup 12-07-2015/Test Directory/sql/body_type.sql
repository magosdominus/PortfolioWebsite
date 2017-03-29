/*File: Body_Type.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender property/value table
*/

DROP TABLE IF EXISTS Body_Type;

CREATE TABLE Body_Type(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO Body_Type (value, property) VALUES (1, 'Average');

INSERT INTO Body_Type (value, property) VALUES (2, 'Overweight');

INSERT INTO Body_Type (value, property) VALUES (4, 'Thin');

INSERT INTO Body_Type (value, property) VALUES (8, 'Athletic');


