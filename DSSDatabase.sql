DROP DATABASE IF EXISTS DSSDatabase;

CREATE DATABASE DSSDatabase;

USE DSSDatabase;

CREATE TABLE users(userName VARCHAR(127) primary key, password VARCHAR(127), userType VARCHAR(2));
CREATE TABLE userInformation(userName VARCHAR(127) REFERENCES users(userName), firstName VARCHAR(127), lastName VARCHAR(127), userInterests TEXT);
CREATE TABLE userApplications(userName VARCHAR(127) primary key, password VARCHAR(127), formalName VARCHAR(127), interests TEXT);

INSERT INTO users VALUES ('nabhan', 'password123','OU');
INSERT INTO users VALUES ('naeem', 'password456','OU');
INSERT INTO users VALUES ('hamza', 'password789','OU');
INSERT INTO users VALUES ('kareem', 'password321','OU');

INSERT INTO userInformation VALUES ('nabhan','Nabhan','Maswood','none');