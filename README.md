# EECS-2311-Group-Project
Group Project 


HOW TO RUN:

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


Step 3: VM Arguments
Navigate to ApplicationStart (\src\guilayout\ApplicationStart.java). Right click, and select: Run as --> Run Configuration 
Double click on " Java Application" to create a new run config. Ensure the main class and project is set correctly, as seen below.

![image](https://github.com/Isaiahak/EECS-2311-Group-Project/assets/119365237/14f4f1d8-4bce-49bb-b5b0-97299957743d)

Navigate to "Arguments" and enter the following VM arguments:
MAC/LINUX: --module-path [PATH TO SDK HERE] --add-modules javafx.controls,javafx.fxml
WINDOWS: --module-path "[PATH TO SDK HERE]" --add-modules javafx.controls,javafx.fxml
Remember how I said you should have your javaFX library path? Use that path in place of [PATH TO SDK HERE]

![image](https://github.com/Isaiahak/EECS-2311-Group-Project/assets/119365237/4b39f030-54fe-406a-b800-942d0a99fa9b)

Now, click "Apply" and "Run"! 

Enjoy :D

PS

We were having issues with the .classpath file being deleted upon pushing changes.
If this is the case, please create a .classpath file, place it as seen below:

![image](https://github.com/Isaiahak/EECS-2311-Group-Project/assets/119365237/cdc74695-c89f-4a74-8327-235533129e65)


The .classpath should look like this:
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER">
		<attributes>
			<attribute name="module" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="src" path="EECS-2311-Group-Project-main/PawfectPairs/src"/>
	<classpathentry kind="src" path="EECS-2311-Group-Project-main/PawfectPairs/test"/>
	<classpathentry kind="con" path="org.eclipse.jdt.USER_LIBRARY/JavaFX"/>
	<classpathentry kind="output" path="EECS-2311-Group-Project-main/PawfectPairs/bin"/>
</classpath>


