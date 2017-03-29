DROP TABLE IF EXISTS mission_rules;
CREATE TABLE mission_rules
(
	missionRuleID int(255) NOT NULL AUTO_INCREMENT,
	missionRuleDescription text(500) NOT NULL,

	PRIMARY KEY (missionRuleID)
);