/*
Author: Matthew Cormier
Filename: profiles.sql
Wede 3201

Discription: this table will hold the profile information for users.
*/

DROP TABLE IF EXISTS profiles;

CREATE TABLE profiles (
	user_id VARCHAR(20) PRIMARY KEY REFERENCES users (user_id),
	user_bio VARCHAR(1000) NOT NULL,
	user_picture SMALLINT NOT NULL,
	user_headline VARCHAR(100) NOT NULL,
	match_description VARCHAR(1000) NOT NULL,
	city SMALLINT NOT NULL,
	gender SMALLINT,
	gender_sought SMALLINT,
    
	relationship_sought SMALLINT,
	body_type SMALLINT,
	height VARCHAR(10),
	horoscope SMALLINT,
	ethnicity SMALLINT,
	religion SMALLINT,
	relationship_status SMALLINT,
	children SMALLINT,
	smoking SMALLINT,
	education SMALLINT,
	occupation VARCHAR(255),
	interests VARCHAR(255)
);

/* ALTER TABLE profiles ADD CONSTRAINT DF_Picture DEFAULT '0' FOR relationship_sought;
ALTER TABLE profiles ADD CONSTRAINT DF_Picture DEFAULT '0' FOR body_type;
ALTER TABLE profiles ADD CONSTRAINT DF_Picture DEFAULT '0' FOR age;
ALTER TABLE profiles ADD CONSTRAINT DF_Picture DEFAULT '0' FOR horoscopw;
ALTER TABLE profiles ADD CONSTRAINT DF_Picture DEFAULT '0' FOR ethnicity;
ALTER TABLE profiles ADD CONSTRAINT DF_Picture DEFAULT '0' FOR religion;
ALTER TABLE profiles ADD CONSTRAINT DF_Picture DEFAULT '0' FOR relationship_status;
ALTER TABLE profiles ADD CONSTRAINT DF_Picture DEFAULT '0' FOR childern;
ALTER TABLE profiles ADD CONSTRAINT DF_Picture DEFAULT '0' FOR smoking;
ALTER TABLE profiles ADD CONSTRAINT DF_Picture DEFAULT '0' FOR education; */
