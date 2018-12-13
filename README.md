DOCUMENT SHARING SYSTEM

Language: Java
Storage: Database
Operating System: Windows
IDE: IntelliJ

To Run the project you need to have MYSQL server and set up the database with user "root" and password "password"
If you don't have the user like that in the SystemManager you can go to the getConnection method and change the login
for the database. 

When you have MYSQL installed you set up the database by running the commands in DSSDatabase.SQL file in the project. You can just copy and paste all the commands into the MYSQL console and the database will be set up.

The project uses JAVA SDK 9.0 and requires the MYSQL connector library "mysql-connector-java-8.0.13.jar".

To run the system you run the Java Class file "DocumentSharingSystem" which contains the main method start the system 

The SystemManager contains all the methods that query the database since there is a single database connection in the program.



Updating Membership: Not Working

Maintaning a List of Taboo Words: Working

Look at Taboo Words Suggestions: Not Working

Unlock any Locked Documents: Working

SU Process Complaints about OUs: Not Working

Create New Documents: Working

Invite Other Users: Working

Document Status as Public, Restricted, Shared, and Private: Working

Accept or Deny Invitations from other OUs: Not Working

Lock a Shared Document to Update: Working

Update a Locked Document and Remember Who Updated: Working

Unlock a Shared Document Locked by him/herself: Working

File Complaints to Owner or SU: Working

Owner of Document Processes Complaints: Not Working

Unlock Locked Documents that is being updated by others: Working

Search Own Files Based on Partial KeyWords: Not Working

Search Information about OUs based on name or interests: Not Working

Read Open Documents: Working

Retrieve Old Versions: Working

Complain about Open Documents: Working

Send Suggestions for Taboo Words: Working

Apply to be OU: Working

Creative Feature(Spell Checker): Not Working

