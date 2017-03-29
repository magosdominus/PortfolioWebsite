DROP TABLE IF EXISTS character_rules;
CREATE TABLE character_rules
(
	characterID int(255) NOT NULL,
	ruleID int(255) NOT NULL,

	PRIMARY KEY (characterID, ruleID)
);