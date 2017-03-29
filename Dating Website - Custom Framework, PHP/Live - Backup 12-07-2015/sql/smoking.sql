/*File: Smoking.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender property/value table
*/

DROP TABLE IF EXISTS Smoking;

CREATE TABLE Smoking(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO Smoking (value, property) VALUES (1, 'Yes');

INSERT INTO Smoking (value, property) VALUES (2, 'No');