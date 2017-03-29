DROP TABLE IF EXISTS selected_mission_rules;
CREATE TABLE selected_mission_rules
(
	missionRuleID int(255) NOT NULL,
	gameID int(255) NOT NULL,

	PRIMARY KEY (gameID, missionRuleID)
);