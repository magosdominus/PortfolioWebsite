DROP TABLE IF EXISTS character_special_wargear;
CREATE TABLE character_special_wargear
(
	characterID int(255) NOT NULL,
	specialWargearID int(255) NOT NULL,
	
	PRIMARY KEY (characterID, specialWargearID)
);