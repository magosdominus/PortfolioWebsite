DROP TABLE IF EXISTS wargear;
CREATE TABLE wargear
(
	wargearID int(255) NOT NULL AUTO_INCREMENT,
	price int(255),
	description text(500) NOT NULL,

	PRIMARY KEY (wargearID)
);