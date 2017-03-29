DROP TABLE IF EXISTS users;
CREATE TABLE users
(
	userID int(255) NOT NULL AUTO_INCREMENT,
	username char(20) NOT NULL,
	password char(255) NOT NULL,
	userType char(1) NOT NULL,
	dateCreated timestamp  NOT NULL
                           DEFAULT CURRENT_TIMESTAMP,
	lastUpdated timestamp  NOT NULL
                           DEFAULT CURRENT_TIMESTAMP 
                           ON UPDATE CURRENT_TIMESTAMP,

	PRIMARY KEY (userID)
);