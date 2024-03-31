# EECS-2311-Group-Project
Group Project 


**HOW TO RUN:**
_and a little bit of a troubleshooting guide...._

Step 1:
Import this project by cloning the URL
Ensure JavaFX SDK is installed on your computer - https://gluonhq.com/products/javafx/
* unzip the SDK into a location on your computer - Take note of the file path to lib (e.g. C:\Java\javafx-sdk-20\lib)
Ensure Postrgesql is installed on your computer, take note of the file path to postgresql-42.7.2.jar (e.g. C:\Program Files (x86)\PostgreSQL\pgJDBC)

Step 2: Add JavaFX library to the project
Right click on "Pawfect Pairs/src" --> Build Path --> Configure Build Path... --> Libraries 

1. Click 'Class Path' --> Add Library --> User Library --> User Libaries --> New 
2. Enter a descriptive name such as "JavaFX", then click "Ok"
3. Click on your new library --> Add Jars --> navigate to lib folder (\PawfectPairs\src\lib) and add all contents to your library. 
4. Click "Ok and Close" then select your new user libary, and click 'Finish'. 
5. You should now see your new library under class path!

![image](https://github.com/Isaiahak/EECS-2311-Group-Project/assets/97921328/8d64f5cb-db5a-4f15-8918-c493b7061bf9)


Step 3: Add the PostreSQL jar to the project
Similar to the previous step, select the 'Configure Build Path' and go to the Libraries tab
1. Click 'Module Path' --> and double-click 'postgresql-42.7.2'
2. A window should pop up, here enter the path to the postgresql-42.7.2.jar file that you took note of earlier, select the jar, click open, and then click 'Apply and close'

**TO ESTABLISH A CONNECTION TO THE DATABASE WITH THE SQL FILE**


Create a blank database in terminal and run the following command in the directory that contains the sql file.
pg_restore -U username -d dbname -1 newpawdb.sql
Replace username with the username that you used to create the database you created, replace dbname with the name of the database you created. 

![image](https://github.com/Isaiahak/EECS-2311-Group-Project/assets/97921328/4d0a26a9-8637-4c68-9cd5-6e03cd3bf756)

In the database connection method,  (src>backend.database.Database.java) replace "password" with your postgresql password, "postgres" with your username, and "pawdb" with the dbname defined in the above command.

Note: Please ensure to use finalter2db.sql

**To Run**

In your command terminal, navigate to the 'PawfectPairs' directory and type gradle run!
