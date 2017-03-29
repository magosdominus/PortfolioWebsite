DROP TABLE IF EXISTS rules;
CREATE TABLE rules
(
	ruleID int(255) NOT NULL AUTO_INCREMENT,
	ruleName char(255) NOT NULL,
	description text(500) NOT NULL,
	
	PRIMARY KEY (ruleID)
);