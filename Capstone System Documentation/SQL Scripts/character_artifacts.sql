DROP TABLE IF EXISTS character_artifacts;
CREATE TABLE character_artifacts
(
	characterID int(255) NOT NULL,
	artifactID int(255) NOT NULL,

	PRIMARY KEY (characterID, artifactID)
);