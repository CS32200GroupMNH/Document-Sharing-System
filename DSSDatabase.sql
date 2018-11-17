DROP DATABASE IF EXISTS DSSDatabase;

CREATE DATABASE DSSDatabase;

USE DSSDatabase;

CREATE TABLE users(userName VARCHAR(127) primary key, password VARCHAR(127), userType VARCHAR(2));

INSERT INTO users VALUES ('nabhan', 'password123','SU');
INSERT INTO users VALUES ('naeem', 'password456','SU');
INSERT INTO users VALUES ('hamza', 'password789','SU');
INSERT INTO users VALUES ('kareem', 'password321','SU');