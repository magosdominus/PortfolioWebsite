DROP TABLE IF EXISTS character_special_rules;
CREATE TABLE character_special_rules
(
	characterID int(255) NOT NULL,
	specialRuleID int(255) NOT NULL,

	PRIMARY KEY (characterID, specialRuleID)
);