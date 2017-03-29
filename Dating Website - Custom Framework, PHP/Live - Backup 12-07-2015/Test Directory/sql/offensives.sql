DROP TABLE IF EXISTS offensives CASCADE;

CREATE TABLE offensives (
	reporting_user VARCHAR(20) PRIMARY KEY,
	offending_user VARCHAR(32) NOT NULL,
	status VARCHAR(1) NOT NULL
);
INSERT INTO offensives (reporting_user, offending_user, status) VALUES ('test1010', 'Devashree', 'o');

