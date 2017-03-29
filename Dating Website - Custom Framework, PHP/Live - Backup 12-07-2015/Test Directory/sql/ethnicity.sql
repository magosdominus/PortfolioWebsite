/*File: Ethnicity.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender property/value table
*/

DROP TABLE IF EXISTS Ethnicity;

CREATE TABLE Ethnicity(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO Ethnicity (value, property) VALUES (1, 'White');

INSERT INTO Ethnicity (value, property) VALUES (2, 'Black');

INSERT INTO Ethnicity (value, property) VALUES (4, 'Middle-eastern ');

INSERT INTO Ethnicity (value, property) VALUES (8, 'East Asian');

INSERT INTO Ethnicity (value, property) VALUES (16, 'South Asian');

INSERT INTO Ethnicity (value, property) VALUES (32, 'Latino');

INSERT INTO Ethnicity (value, property) VALUES (64, 'Mixed');

INSERT INTO Ethnicity (value, property) VALUES (128, 'Other');