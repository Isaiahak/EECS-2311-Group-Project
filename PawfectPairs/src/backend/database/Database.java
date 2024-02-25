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
	
	
//	public static void main(String[] args) {
//		
//		connection =  databaseConnector.connect();
//		
////		System.out.println("testing");
//       
//
//	}
////	
	
	
//	public static ArrayList<Dog> getDogsFromDB(){ // default method, just get all dogs
//		
//		System.out.println("Bruhhh");
//		
//		ArrayList<Dog> dogs = new ArrayList<>();
//		
//		
//		
//            String sql = "SELECT * FROM dog";
//            
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                 ResultSet resultSet = preparedStatement.executeQuery()) {
//
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String dogName = resultSet.getString("dogname");
//                    boolean adopted = resultSet.getBoolean("adopted");
//                    String biography = resultSet.getString("biography");
//                    String imagePath = resultSet.getString("imagepath");
//                    int posterId = resultSet.getInt("posterid");
//                    int ageId = resultSet.getInt("ageid");
//                    int energyId = resultSet.getInt("energyid");
//                    int sizeId = resultSet.getInt("sizeid");
//                    int sexId = resultSet.getInt("sexid");
//                    
//                    Poster poster = null;
//					try {
//						poster = getPosterById(posterId);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} 
//
//                    
//                    // public Dog(String name, int id, int age, int energyLevel, int size, int sex, Poster poster, boolean adopted, String imagePath, String biography) {
//                    Dog dog = new Dog(dogName, id, ageId, energyId, sizeId, sexId, poster, adopted, imagePath, biography); 
//                    dogs.add(dog);
//                }
//            
//        } catch (SQLException e) {
//            e.printStackTrace(); // Handle exceptions properly in a real application
//        }
//	
//	    return dogs;
//	}
//		


private static Poster getPosterById(int posterId) throws SQLException {
    String posterSql = "SELECT * FROM poster";
    
    
//    try {
//    	PreparedStatement posterStatement = connection.prepareStatement();
//        posterStatement.setInt(1, posterId);
//
//        try {
//        	ResultSet posterResultSet = posterStatement.executeQuery();
//            if (posterResultSet.next()) {
//                int id = posterResultSet.getInt("poster_id");
//                String displayName = posterResultSet.getString("displayname");
//                int score = posterResultSet.getInt("score");
//
//                return new Poster(id, displayName, score);
//            }
//        }
//    }
    return null; // Return null if no poster is found with the given posterId
}


/*
 * Method to return all dogs
 */

	private ArrayList<Dog> getAllDogs (){
		
		 		ArrayList<Dog> dogProfiles = new ArrayList<>();
		 	
		 		Connection connection = databaseConnector.connect();
				
				Statement statement = connection.createStatement () ;
				ResultSet resultSet = statement.executeQuery ("SELECT * FROM dog") ;
					
				//ResultSet resultSetPoster = statement.executeQuery("SELECT * FROM poster");
				 while (resultSet.next ()) {
						 
				
						 
						 	Dog dog = new Dog(resultSet.getString ("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energyid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"), poster, resultSet.getBoolean("adopted"), 
					 			resultSet.getString("imagePath"), resultSet.getString("biography"));
					 	 dogProfiles.add(dog);
					 	 //   int sex, Poster poster, boolean adopted, String imagePath, String biography)
					 	//dogid | dogname | adopted | biography | imagepath | posterid | ageid | energyid | sizeid | sexid
					 	
				 }
					 connection.close () ;
			           
			       } catch (SQLException e) {
			 			System.out.println ("Connection failure.") ;
			 			e.printStackTrace () ;
			       }	
			  
			    
			    
		return dogProfiles;
		
	}

/*
 * method to return poster by given id
 */

/*
 * Method to get user's liked dogs
 */
	
}
//Sidney
//try {
//    // Load the JDBC driver for your database
//    Class.forName("org.postgresql.Driver"); // Replace with your database driver
//
//    // Create a connection URL
//    String url = "jdbc:postgresql://localhost:5432/pawdb"; //change it to newestpawdb after the clone of that db is done
//    // Replace with the appropriate JDBC URL for your database
//
//    // Provide your database username and password
//    String username = "postgres";
//    String password = "12345";
//
//    // Establish the connection
//    Connection connection = DriverManager.getConnection(url, username, password);
//    return connection;
//} catch (ClassNotFoundException | SQLException e) {
//    e.printStackTrace(); // Handle or log the exception appropriately
//    return null;
//}

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