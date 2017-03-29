/*File: Horoscope.sql
Author: YOUR NAME
Date: THE DATE YOU CREATED THE FILE
Description: SQL file to create gender property/value table
*/
DROP TABLE IF EXISTS Horoscope;

CREATE TABLE Horoscope(
value SMALLINT PRIMARY KEY,
property VARCHAR(30) NOT NULL
);

INSERT INTO Horoscope (value, property) VALUES (1, 'Aries');

INSERT INTO Horoscope (value, property) VALUES (2, 'Taurus');

INSERT INTO Horoscope (value, property) VALUES (4, 'Gemini');

INSERT INTO Horoscope (value, property) VALUES (8, 'Cancer');

INSERT INTO Horoscope (value, property) VALUES (16, 'Leo');

INSERT INTO Horoscope (value, property) VALUES (32, 'Virgo');

INSERT INTO Horoscope (value, property) VALUES (64, 'Libra');

INSERT INTO Horoscope (value, property) VALUES (128, 'Scorpio');

INSERT INTO Horoscope (value, property) VALUES (256, 'Libra');

INSERT INTO Horoscope (value, property) VALUES (512, 'Sagittaries');

INSERT INTO Horoscope (value, property) VALUES (1024, 'Capricorn ');

INSERT INTO Horoscope (value, property) VALUES (2048, 'Aquarius');

INSERT INTO Horoscope (value, property) VALUES (4096, 'Pisces');
