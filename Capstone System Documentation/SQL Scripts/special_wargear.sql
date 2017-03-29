DROP TABLE IF EXISTS special_wargear;
CREATE TABLE special_wargear
(
	specialWargearID int(255) NOT NULL AUTO_INCREMENT,
	special_issue_price int(255) NOT NULL,
	description text(500) NOT NULL,
	weaponRange char(50),
	s char(50),
	ap char(50),
	type char(100),

	PRIMARY KEY (specialWargearID)
);