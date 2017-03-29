DROP TABLE IF EXISTS lists;
CREATE TABLE lists
(
	listID int(255) NOT NULL AUTO_INCREMENT,
	userID int(255) NOT NULL,
	listName char(255) NOT NULL,
	dateCreated timestamp,
	lastUpdated timestamp,
	maxPoints int(10),
	detachementType char(255),
	ruleFormat char(255),
	warLordTrait char(255),
	minTroop int(2),
	maxTroop int(2),
	minElite int(2),
	maxElite int(2),
	minFastAttack int(2),
	maxFastAttack int(2),
	minHQ int(2),
	maxHQ int(2),
	minHeavySupport int(2),
	maxHeavySupport int(2),
	minFort int(2),
	maxFort int(2),


	PRIMARY KEY (listID),
	FOREIGN KEY (userID) REFERENCES users(userID)
);