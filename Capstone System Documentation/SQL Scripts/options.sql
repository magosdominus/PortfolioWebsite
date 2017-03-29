DROP TABLE IF EXISTS options;
CREATE TABLE options
(
	optionID int(255) NOT NULL AUTO_INCREMENT,
	weaponID int(255) NOT NULL,
	description text(500) NOT NULL,
	price int(10),
	maxLimit int(2),
	optionType char(50),

	PRIMARY KEY (optionID),
	FOREIGN KEY (weaponID) REFERENCES weapons(weaponID)
);