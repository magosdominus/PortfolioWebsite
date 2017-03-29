DROP TABLE IF EXISTS weapons;
CREATE TABLE weapons
(
	weaponID int(255) NOT NULL AUTO_INCREMENT,
	weaponName char(255) NOT NULL,
	weaponRange char(50),
	s char(50),
	ap char(50),
	type char (100) NOT NULL,

	PRIMARY KEY (weaponID)
);