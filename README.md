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

To run the system you run the Java Class file "DocumentSharingSystem" which contains the main method to create the database. 

The SystemManager contains all the methods that query the database since there is a single database connection in the program. 
