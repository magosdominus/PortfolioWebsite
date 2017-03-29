DROP TABLE IF EXISTS list_units;
CREATE TABLE list_units
(
	listID int(255) NOT NULL,
	unitID int(255) NOT NULL,

	PRIMARY KEY (listID, unitID)
);