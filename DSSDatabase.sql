DROP DATABASE IF EXISTS DSSDatabase;

CREATE DATABASE DSSDatabase;

USE DSSDatabase;

CREATE TABLE users(userName VARCHAR(127) primary key, password VARCHAR(127), userType VARCHAR(2));
CREATE TABLE userInformation(userName VARCHAR(127) REFERENCES users(userName), firstName VARCHAR(127), lastName VARCHAR(127), userInterests TEXT);
CREATE TABLE userApplications(userName VARCHAR(127) primary key, password VARCHAR(127), formalName VARCHAR(127), interests TEXT);

CREATE TABLE Messages(messageID int NOT NULL AUTO_INCREMENT, userName VARCHAR(127) REFERENCES users(userName), messageType VARCHAR(127), subject TEXT, message TEXT,
PRIMARY KEY (messageID));

CREATE TABLE TabooWords(location VARCHAR(127) REFERENCES Documents(documentID), word VARCHAR(127),
 CONSTRAINT sh_doc_key PRIMARY KEY (location, word));

CREATE TABLE Documents(documentID VARCHAR(127) primary key, documentName VARCHAR(127), owner VARCHAR(127) REFERENCES users(userName), documentType VARCHAR(127), lockedBy VARCHAR(127) REFERENCES users(userName), contents TEXT, versionCount INT);
CREATE TABLE OldDocuments(versionNumber INT , documentID VARCHAR(127), commands TEXT, updatedBY VARCHAR(127) REFERENCES users(userName), updateDate VARCHAR(127),
CONSTRAINT FOREIGN KEY (documentID) REFERENCES Documents(documentID) ON DELETE CASCADE,
CONSTRAINT update_key PRIMARY KEY (versionNumber,documentID)
);

CREATE TABLE SharedDocuments(sharedWith VARCHAR(127) REFERENCES users(userName), documentID VARCHAR(127),
CONSTRAINT FOREIGN KEY (documentID) REFERENCES Documents(documentID) ON DELETE CASCADE,
 CONSTRAINT sh_doc_key PRIMARY KEY (sharedWith, documentID)
 );

CREATE TABLE FlaggedDocuments(documentID VARCHAR(127), userName VARCHAR(127) REFERENCES users(userName),
CONSTRAINT FOREIGN KEY (documentID) REFERENCES Documents(documentID) ON DELETE CASCADE,
CONSTRAINT flag_doc_key PRIMARY KEY (documentID,userName)
);


INSERT INTO users VALUES ('sys','sys','SU');
INSERT INTO users VALUES ('admin','password','SU');
INSERT INTO users VALUES ('nabhan', 'password123','OU');
INSERT INTO users VALUES ('naeem', 'password456','OU');
INSERT INTO users VALUES ('hamza', 'password789','OU');
INSERT INTO users VALUES ('kareem', 'password321','OU');

INSERT INTO userInformation VALUES ('nabhan','nabhan','maswood','Programming, Video Games, Computers, Television');
INSERT INTO userInformation VALUES ('naeem','naeem','naeem','Programming, Computers');
INSERT INTO userInformation VALUES ('hamza','hamza','hamza','Programming, Television, Computers, Sports');
INSERT INTO userInformation VALUES ('kareem','kareem','kareem','Programming, Sports, Computers, Television');


INSERT INTO TabooWords VALUES ('GLOBAL','Crap');

INSERT INTO TabooWords VALUES ('GLOBAL','UNK');
INSERT INTO TabooWords VALUES ('GLOBAL','snap');
INSERT INTO TabooWords VALUES ('GLOBAL','apple');
INSERT INTO TabooWords VALUES ('GLOBAL','pear');
INSERT INTO TabooWords VALUES ('GLOBAL','foo');
INSERT INTO TabooWords VALUES ('GLOBAL','bar');


INSERT INTO Documents VALUES ('GLOBAL','GLOBAL','sys','',null,'',0);