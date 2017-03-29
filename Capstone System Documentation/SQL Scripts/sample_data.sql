TRUNCATE users;

INSERT INTO users
VALUES (null, "MagosDominus", "password1234", "a", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO users
VALUES (null, "RJ", "password2222", "c", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO users
VALUES (null, "Test3", "password3333", "c", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

TRUNCATE games;

INSERT INTO games
VALUES (null, 1, "Cadia Planetfall", "Gathering Storm I: Fall of Cadia battle #2", 2);
INSERT INTO games
VALUES (null, 2, "Cadia Catacombs", "Gathering Storm I: Fall of Cadia battle #3", 2);
INSERT INTO games
VALUES (null, 1, "Terra", "Gathering Storm III: Crusade battle #4", 2);

TRUNCATE codexes;

INSERT INTO codexes
VALUES (null, "Astra Militarum", "7", "The Astra Militarum, more commonly known as the Imperial Guard, is the single greatest army in the galaxy. Comprising countless soldiers from a million worlds...");
INSERT INTO codexes
VALUES (null, "Cult Mechanicus", "7", "The Tech-Priests of the Cult Mechanicus control grotesque armies of cybernetic horrors. Every one of thier priesthood wields technologies beyond mortal imagining...");

TRUNCATE allies;

INSERT INTO allies
VALUES (1, 2, "Brothers in arms");