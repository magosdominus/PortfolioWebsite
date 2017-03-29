DROP TABLE IF EXISTS allies;
CREATE TABLE allies
(
	codexID int(255) NOT NULL,
	allyCodexID int(255) NOT NULL,
	typeOfAlliance char(50) NOT NULL,

	PRIMARY KEY (codexID, allyCodexID)
);