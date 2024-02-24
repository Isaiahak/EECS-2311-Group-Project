package backend;

import java.sql.*;
import java.util.ArrayList;

import backend.dog.Dog;
import backend.poster.Poster;
/*
 * Public class to centralise all communcations to and from database
 */
public class Database {
	
	
	public static void main(String[] args) {
       
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.connect();
        
        
        if(connection != null) {
        	 Statement statement = null;
             ResultSet resultSet = null;
             
             
		    try {
		    	statement = connection.createStatement();
		    	String sqlQuery = "SELECT * FROM PawfectPairs.poster";
		    	resultSet = statement.executeQuery(sqlQuery);
		    	ArrayList<Poster> posterlist = new ArrayList<Poster>();
		    	while(resultSet.next()) {
		    		Poster poster = new Poster(resultSet.getInt("score"), resultSet.getString("displayName"), resultSet.getInt("posterID"));
		    		posterlist.add(poster);
		    	}
		    	ArrayList<Dog> doglist = new ArrayList<Dog>();
		    	statement = connection.createStatement();
		    	sqlQuery = "SELECT * FROM PawfectPairs.dogs";
				resultSet = statement.executeQuery(sqlQuery);
				while(resultSet.next()) {
					Poster dogPoster = new Poster(0, "DawgOwner", 0);
					for(Poster p : posterlist) {
						if(p.getUniqueId() == resultSet.getInt("posterID")) {
							dogPoster = p;
						}
					}
					Dog dog = new Dog(resultSet.getString("name"), resultSet.getInt("ID"), resultSet.getInt("ageID"),resultSet.getInt("energyLevelID"), resultSet.getInt("sizeID"), resultSet.getInt("sexID"), dogPoster, resultSet.getBoolean("adopted"),"","");
					doglist.add(dog);
				}
				for (Poster p : posterlist) {
					System.out.println("Display name = " + p.getDisplayName() + ", Poster ID = " + p.getUniqueId() + ", Poster score = " + p.getScore());
				}
				for (Dog d : doglist) {
					System.out.println("dog name = " + d.getName() + ", dog id = " + d.getId() + ", dog's age = " + d.getAge() + ", dog's energy level = " + d.getEnergyLevel() + "dog's size =" + d.getSize() + "dog's sex = " + d.getSex() + "dog's adoption status = " + d.getAdopted());
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    finally {
		    	
		    	try {
                    // Close the resources in the finally block
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
		    	
		        try {
		        	if(connection != null && !connection.isClosed()) {
		        		connection.close();
		        	}    
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
	    }
	}
}
	

class DatabaseConnector {
    public Connection connect() {
        // Code to establish a database connection
        try{
        	return DriverManager.getConnection("jdbc:postgresql://localhost/isaiah_db", "member", "321123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
		
    }
}

