DROP DATABASE IF EXISTS DSSDatabase;

CREATE DATABASE DSSDatabase;

USE DSSDatabase;

CREATE TABLE users(userName VARCHAR(127) primary key, password VARCHAR(127), userType VARCHAR(2));
CREATE TABLE userInformation(userName VARCHAR(127) REFERENCES users(userName), firstName VARCHAR(127), lastName VARCHAR(127), userInterests TEXT);
CREATE TABLE userApplications(userName VARCHAR(127) primary key, password VARCHAR(127), formalName VARCHAR(127), interests TEXT);

CREATE TABLE Messages(messageID VARCHAR(127) primary key, userName VARCHAR(127) REFERENCES users(userName), messageType VARCHAR(127), subject TEXT, message TEXT);

CREATE TABLE TabooWords(location VARCHAR(127) REFERENCES Documents(documentID), word VARCHAR(127));

CREATE TABLE Documents(documentID VARCHAR(127) primary key, documentName VARCHAR(127), owner VARCHAR(127) REFERENCES users(userName), documentType VARCHAR(127), lockedBy VARCHAR(127) REFERENCES users(userName), contents TEXT);
CREATE TABLE DocumentUpdates(updateID VARCHAR(127) primary key, documentID VARCHAR(127) REFERENCES Documents(documentID),version INT, commands TEXT, updatedBY VARCHAR(127) REFERENCES users(userName));


INSERT INTO users VALUES ('admin','password','SU');
INSERT INTO users VALUES ('nabhan', 'password123','OU');
INSERT INTO users VALUES ('naeem', 'password456','OU');
INSERT INTO users VALUES ('hamza', 'password789','OU');
INSERT INTO users VALUES ('kareem', 'password321','OU');

INSERT INTO userInformation VALUES ('nabhan','Nabhan','Maswood','none');

INSERT INTO Documents VALUES ('GLOBAL','GLOBAL','admin','',null,'');