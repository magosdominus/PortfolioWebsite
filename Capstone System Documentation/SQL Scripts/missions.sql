DROP TABLE IF EXISTS missions;
CREATE TABLE missions
(
	missionID int(255) NOT NULL AUTO_INCREMENT,
	missionName char(50) NOT NULL,
	missionDescription text(500),
	tableSize char(50) NOT NULL,
	primaryObjectives text(500) NOT NULL,
	secondaryObjectives text(500),
	missionLength int(2) NOT NULL,
	deployment text(500) NOT NULL,
	victoryObjectives text(500) NOT NULL,

	PRIMARY KEY (missionID)
);