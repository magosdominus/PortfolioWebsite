DROP TABLE IF EXISTS selected_list_rules;
CREATE TABLE selected_list_rules
(
	listID int(255) NOT NULL,
	listRuleID int(255) NOT NULL,

	PRIMARY KEY (listID, listRuleID)
);