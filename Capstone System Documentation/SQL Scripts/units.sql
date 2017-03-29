DROP TABLE IF EXISTS units;
CREATE TABLE units
(
	unitID int(255) NOT NULL AUTO_INCREMENT,
	codexID int(255) NOT NULL,
	unitName char(255) NOT NULL,
	unitClass char(20) NOT NULL,
	unitPrice int(5),
	unitComposition char(255),

	PRIMARY KEY (unitID),
	FOREIGN KEY (codexID) REFERENCES codexes(codexID)
);