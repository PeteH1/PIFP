DROP TABLE IF EXISTS player CASCADE;

CREATE TABLE player
 (
 	id INTEGER PRIMARY KEY auto_increment, 
	name VARCHAR(255),
	age INTEGER,
	position VARCHAR(255),
	nationality VARCHAR(255)
 );