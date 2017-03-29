/*File: Relationship_status.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender property/value table
*/

DROP TABLE IF EXISTS Relationship_status;

CREATE TABLE Relationship_status(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO Relationship_status (value, property) VALUES (1, 'Single');

INSERT INTO Relationship_status (value, property) VALUES (2, 'Married');

INSERT INTO Relationship_status (value, property) VALUES (4, 'In a relationship');

INSERT INTO Relationship_status (value, property) VALUES (8, 'Separated/Divorced');

INSERT INTO Relationship_status (value, property) VALUES (16, 'Other');