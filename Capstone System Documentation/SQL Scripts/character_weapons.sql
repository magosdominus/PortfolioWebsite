DROP TABLE IF EXISTS character_weapons;
CREATE TABLE character_weapons
(
	characterID int(255) NOT NULL,
	weaponID int(255) NOT NULL,
	qty int(2),

	PRIMARY KEY (characterID, weaponID)
);