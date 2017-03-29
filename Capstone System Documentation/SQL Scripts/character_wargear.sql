DROP TABLE IF EXISTS character_wargear;
CREATE TABLE character_wargear
(
	characterID int(255) NOT NULL,
	wargearID int(255) NOT NULL,
	
	PRIMARY KEY (characterID, wargearID)
);