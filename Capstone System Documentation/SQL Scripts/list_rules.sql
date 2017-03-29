DROP TABLE IF EXISTS list_rules;
CREATE TABLE list_rules
(
	listRuleID int(255) NOT NULL AUTO_INCREMENT,
	description text(500) NOT NULL,

	PRIMARY KEY (listRuleID)
);