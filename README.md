# EECS-2311-Group-Project
Group Project 


**HOW TO RUN:**

_Step 1: Download_
Ensure JavaFX SDK is installed on your computer - https://gluonhq.com/products/javafx/
* unzip the SDK into a location on your computer - Take note of the file path to lib (e.g. C:\Java\javafx-sdk-20\lib)

Ensure Postrgesql is installed on your computer, take note of the file path to postgresql-42.7.2.jar (e.g. C:\Program Files (x86)\PostgreSQL\pgJDBC)

Ensure Gradle is installed on your computer. 


_Step 2: Add JavaFX library to the project_
Right click on "Pawfect Pairs/src" --> Build Path --> Configure Build Path... --> Libraries 

1. Click 'Class Path' --> Add Library --> User Library --> User Libaries --> New 
2. Enter a descriptive name such as "JavaFX", then click "Ok"
3. Click on your new library --> Add Jars --> navigate to lib folder (\PawfectPairs\src\lib) and add all contents to your library. 
4. Click "Ok and Close" then select your new user libary, and click 'Finish'. 
5. You should now see your new library under class path!

![image](https://github.com/Isaiahak/EECS-2311-Group-Project/assets/97921328/8d64f5cb-db5a-4f15-8918-c493b7061bf9)


_Step 3: Add the PostreSQL jar to the project_
Similar to the previous step, select the 'Configure Build Path' and go to the Libraries tab
1. Click 'Module Path' --> and double-click 'postgresql-42.7.2'
2. A window should pop up, here enter the path to the postgresql-42.7.2.jar file that you took note of earlier, select the jar, click open, and then click 'Apply and close'

_Step 4: Establish a connection to the database with the sql file_

Note: Please ensure to use "Itr3_FinalDB_Group_3.sql" located in "EECS-2311-Group-Project"

Create a blank database in the terminal or pgAdmin.

For the terminal: run the following command in the directory that contains the sql file.
pg_restore -U username -d dbname -1 newpawdb.sql
Replace username with the username that you used to create the database you created, replace dbname with the name of the database you created. 

![image](https://github.com/Isaiahak/EECS-2311-Group-Project/assets/97921328/4d0a26a9-8637-4c68-9cd5-6e03cd3bf756)

For pgAdmin: Simply right-click the empty database you created, select 'Restore..." and put the path to the sql file, select 'open' and 'Restore'

In the database connection method,  (src>backend.database.Database.java) replace "password" with your postgresql password, "postgres" with the postrgesql username used to create the database, and "pawdb" with the dbname defined in the above command.


**To Run**

In your command terminal, navigate to the 'PawfectPairs' directory and type gradle run!
