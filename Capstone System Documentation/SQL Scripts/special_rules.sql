DROP TABLE IF EXISTS special_rules;
CREATE TABLE special_rules
(
	specialRuleID int(255) NOT NULL AUTO_INCREMENT,
	name int(255) NOT NULL,
	description text(500) NOT NULL,

	PRIMARY KEY (specialRuleID)
);