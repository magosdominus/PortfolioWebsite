DROP TABLE IF EXISTS players;
CREATE TABLE players
(
	gameID int(255) NOT NULL,
	listID int(255) NOT NULL,
	tacticalObjectives text(500) NOT NULL,
	firstTurn boolean NOT NULL,

	PRIMARY KEY (gameID, listID)
);