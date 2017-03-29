DROP TABLE IF EXISTS unit_characters;
CREATE TABLE unit_characters
(
	unitID int(255) NOT NULL,
	characterID int(255) NOT NULL,
	qty int(10) NOT NULL,

	PRIMARY KEY (unitID, characterID)
);