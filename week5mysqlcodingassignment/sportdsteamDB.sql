CREATE database IF NOT EXISTS sportsteams;

use sportsteams;

DROP TABLE IF EXISTS sportsteams;

CREATE TABLE sportsteams (
	id int(10) NOT NULL auto_increment,
	name varchar(50) NOT NULL,
	PRIMARY KEY(id)
);