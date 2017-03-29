/*File: relationship_sought.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender property/value table
*/

DROP TABLE IF EXISTS relationship_sought;

CREATE TABLE relationship_sought(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO relationship_sought (value, property) VALUES (1, 'Short-term Relationship');

INSERT INTO relationship_sought (value, property) VALUES (2, 'Long-term Relationship');

INSERT INTO relationship_sought (value, property) VALUES (3, 'Friendship');

INSERT INTO relationship_sought (value, property) VALUES (4, 'Other ');