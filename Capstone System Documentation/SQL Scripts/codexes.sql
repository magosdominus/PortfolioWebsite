DROP TABLE IF EXISTS codexes;
CREATE TABLE codexes
(
	codexID int(255) NOT NULL AUTO_INCREMENT,
	codexName char(255) NOT NULL,
	edition char(2) NOT NULL,
	description text(500) NOT NULL,

	PRIMARY KEY (codexID)
);