DROP TABLE IF EXISTS games;
CREATE TABLE games
(
	gameID int(255) NOT NULL AUTO_INCREMENT,
	userID int(255) NOT NULL,
	gameName char(50),
	gameDescription text(500),
	numberOfPlayers int(2),

	PRIMARY KEY (gameID),
	FOREIGN KEY (userID) REFERENCES users(userID)
);