DROP TABLE IF EXISTS characters;
CREATE TABLE characters
(
	characterID int(255) NOT NULL AUTO_INCREMENT,
	ws int(5),
	bs int(2),
	s int(2),
	t int(2),
	w int(2),
	i int(2),
	a int(2),
	ld int(2),
	sv char(10),
	front int(2),
	side int(2),
	rear int(2),
	hp int(2),
	characterType char(20),
	mayTakeSpecial int(1),
	mayTakeArtifact int(1),

	PRIMARY KEY (characterID)
);