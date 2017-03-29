DROP TABLE IF EXISTS artifacts;
CREATE TABLE artifacts
(
	artifactID int(255) NOT NULL AUTO_INCREMENT,
	name int(255) NOT NULL,
	description text(500) NOT NULL,
	price int(10),

	PRIMARY KEY (artifactID)
);