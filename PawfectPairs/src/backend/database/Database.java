package backend.database;

import java.sql.*;
import java.sql.Connection.*;
import java.util.ArrayList;

import backend.dog.Dog;
import backend.poster.Poster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*
 * Public class to centralise all communcations to and from database
 */
public class Database {
	private static DatabaseConnector databaseConnector = new DatabaseConnector();
//	private static Connection connection = databaseConnector.connect();
	
	 public static Poster getPosterById(int posterId){
		 Poster poster = null; 
		 try {
			 	Connection connection = databaseConnector.connect();
			 	
			 	
			 	
			 	
			    String sql = "SELECT * FROM poster WHERE poster_id = ?";
			    
			    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			        preparedStatement.setInt(1, posterId);
	
			        try (ResultSet resultSet = preparedStatement.executeQuery()) {
			            if (resultSet.next()) {
			            	
			                String displayName = resultSet.getString("displayName");
	//			                System.out.println(displayName);
			                int score = resultSet.getInt("score");
			                
			                poster = new Poster(score, displayName, posterId); // SCORE IS HARDCODeD BCUZ ERROR WTF????
			                // Use the displayName as needed
			            } else {
			                // Handle the case where no poster is found with the given posterId
			            }
			        }
			    }
			} catch (SQLException e) {
			    e.printStackTrace(); // Handle exceptions properly in a real application
			}
		return poster;
		 }


/*
 * Method to return all dogs
 */

	public static ArrayList<Dog> getAllDogs(){
		
		ArrayList<Dog> dogProfiles = new ArrayList<>();
		try{
		Connection connection = databaseConnector.connect();
		Statement statement = connection.createStatement () ;
		ResultSet resultSet = statement.executeQuery ("SELECT * FROM dog") ;
		 while (resultSet.next()) {	 				   
			Dog dog = new Dog(resultSet.getString ("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energyid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"), resultSet.getInt("posterid"), resultSet.getBoolean("adopted"), 
		 	resultSet.getString("imagePath"), resultSet.getString("biography"));
			
		 	dogProfiles.add(dog);
			 	
		 }
			 connection.close () ;
	           
	       } catch (SQLException e) {
	 			System.out.println ("Connection failure.") ;
	 			e.printStackTrace () ;
	       }	
		  
	    
		return dogProfiles;
		
	}
	
	public static void updateAllAdoptedDogs(ArrayList<Dog> doglist) {
		for(Dog d : doglist) {
			if(d.getAdopted() == true) {
				try {
					Connection connection = databaseConnector.connect();
					Statement statement = connection.createStatement () ;
					ResultSet resultSet = statement.executeQuery ("UPDATE dogs SET adopted = TRUE WHERE dogid = " + d.getId());
					connection.close();
				}
				
				catch (SQLException e) {
		 			System.out.println ("Connection failure.") ;
		 			e.printStackTrace () ;
		       }
				
			}
		}
	
		
	}
		
	public static void addLikedDog(int dogID,int userID){
	
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
	        try {
	        	 connection = databaseConnector.connect();
	        	 String sql = "INSERT INTO dog_user_relationship (dogID, userID) VALUES (?, ?)";
	        	 preparedStatement = connection.prepareStatement(sql);
	        	 preparedStatement.setInt(1, dogID);
	        	 preparedStatement.setInt(2, userID);
	        	 int rowsAffected = preparedStatement.executeUpdate();
	        	 if (rowsAffected > 0) {
	                System.out.println("Dog-User relationship added successfully!");
	            } else {
	                System.out.println("Failed to add Dog-User relationship.");
	            }
	        } 
	        catch (SQLException e) {
	            e.printStackTrace();
	          
	        } 
	        finally {
	            try {
	                if (preparedStatement != null) {
	                    preparedStatement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } 
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	}

	public static void removeLikedDog(int dogID,int userID){
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	    try {
	    	 connection = databaseConnector.connect();
	    	 String sql = "DELETE FROM userdogs WHERE dogid = " + dogID + "AND tagid  =" + userID + ";";
	    	 preparedStatement = connection.prepareStatement(sql);
	    	 preparedStatement.setInt(1, dogID);
	    	 preparedStatement.setInt(2, userID);
	    	 int rowsAffected = preparedStatement.executeUpdate();
	    	 if (rowsAffected > 0) {
	            System.out.println("Dog-User relationship added successfully!");
	        } else {
	            System.out.println("Failed to add Dog-User relationship.");
	        }
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	      
	    } 
	    finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } 
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}



	public static ArrayList<Dog> getLikedDog(int userID){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Dog> list = new ArrayList<Dog>();
		
	    try {
	    	 connection = databaseConnector.connect();
	    	 Statement statement = connection.createStatement ();
	    	 ResultSet resultSet = statement.executeQuery("SELECT * FROM dogs JOIN userdogs ON dogs.dogid = userdogs.dogid userdogs.userid = " + userID +";");
	    	 while (resultSet.next()) {	
	    		 Dog dog = new Dog(resultSet.getString ("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energyid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"), resultSet.getInt("posterid"), resultSet.getBoolean("adopted"), 
						 	resultSet.getString("imagePath"), resultSet.getString("biography"));
	    		 list.add(dog);
	    	 }			 	
		}
	    catch (SQLException e) {
	    	System.out.println ("Connection failure.") ;
			e.printStackTrace () ;
	    }	    
	    return list;	
	}
		
		
	public static void setDogAdopted() {
	
		
	}

/*
 * method to return poster by given id
 */

/*
 * Method to get user's liked dogs
 */
	
	public static ArrayList<Dog> getLikedDogs(int userId) {
		ArrayList<Dog> likedDogs = new ArrayList<Dog>();
		
	//		// get liked tags here 
	//		
	//		
	//		int[] likedDogsIds = query.split(",");
	//		// iterate through dogs by tags
	//		
	//		Object likedDogIds;
	//		for(int i = 0; i < likedDogIds.length; i++) {
	//			
	//		}
	//		
		
		
		return likedDogs;
	}	

/*

Method to get a dog's tags*/

/*
 
Method to get user's liked dogs*/
	public static void main(String[] args) {
		
	}


	class DatabaseConnector {
	    public Connection connect() {
	        // Code to establish a database connection
	        try{
	        	System.out.println("Trying to connect....");
	        	
	        	Class.forName("org.postgresql.Driver"); // Replace with your database driver
	        	
	        	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/paw", "postgres", "doglover123");
	//        	System.out.println( "Connected to the PostgreSQL server successfully.");
	        	
	        	return connection; 
	
	        } catch (ClassNotFoundException | SQLException e) {
	        	
	        	System.out.println("Connection failed");
	            e.printStackTrace();
	            
	        } 
			return null;
			
	    }
	        
	}
}

