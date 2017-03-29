DROP TABLE IF EXISTS character_options;
CREATE TABLE character_options
(
	characterID int(255) NOT NULL,
	optionID int(255) NOT NULL,
	qty int(10) NOT NULL,

	PRIMARY KEY (characterID, optionID)
);