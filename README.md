# EECS-2311-Group-Project
Group Project 


**HOW TO RUN:**
_and a little bit of a troubleshooting guide...._

Step 1:
Ensure JavaFX SDK is installed on your computer - https://gluonhq.com/products/javafx/
* unzip the SDK into a location on your computer - Take note of the file path to lib (e.g. C:\Java\javafx-sdk-20\lib)


Step 2: Add JavaFX library to project
Right click on "Pawfect Pairs/src" --> Build Path --> Configure Build Path... --> Libraries 

1. Click 'Class Path' --> Add Library --> User Library --> User Libaries --> New 
2. Enter a descriptive name such as "JavaFX", then click "Ok"
3. Click on your new library --> Add Jars --> navigate to lib folder (\PawfectPairs\src\lib) and add all contents to your library. 
4. Click "Ok and Close" then select your new user libary, and click 'Finish'. 
5. You should now see your new library under class path!

![image](https://github.com/Isaiahak/EECS-2311-Group-Project/assets/119365237/a134a816-4d4d-461d-8a5f-ad852b9acf80)



**TO ESTABLISH A CONNECTION TO THE DATABASE WITH THE SQL FILE**


Create a blank database in terminal and run the following command in the directory that contains the sql file.
pg_restore -U username -d dbname -1 newpawdb.sql
Replace username with the username that you used to create the database you created, replace dbname with the name of the database you created. 

![image](https://github.com/Isaiahak/EECS-2311-Group-Project/assets/97921328/4d0a26a9-8637-4c68-9cd5-6e03cd3bf756)

In the database connection method,  (src>backend.database.Database.java) replace "password" with your postgresql password, "postgres" with your username, and "pawdb" with the dbname defined in the above command.

Note: Please ensure to use finalter2db.sql

**To Run**
In your command terminal, navigate to the "PawfectPairs" directory and type gradle run!
