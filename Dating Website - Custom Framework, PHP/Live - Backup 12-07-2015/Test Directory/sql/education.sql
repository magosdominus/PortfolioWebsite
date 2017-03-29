/*File: Education.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender property/value table
*/

DROP TABLE IF EXISTS Education;

CREATE TABLE Education(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO Education (value, property) VALUES (1, 'Highschool');

INSERT INTO Education (value, property) VALUES (2, 'College Diploma');

INSERT INTO Education (value, property) VALUES (4, 'Undergraduate Degree');

INSERT INTO Education (value, property) VALUES (8, 'Masters Degree/PhD' );

INSERT INTO Education (value, property) VALUES (16, 'Other');

