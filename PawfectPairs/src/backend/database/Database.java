package backend.database;

import java.sql.*;
import java.sql.Connection.*;
import java.util.ArrayList;

import backend.dog.Dog;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*
 * Public class to centralise all communcations to and from database
 */
public class Database {
	private static DatabaseConnector databaseConnector = new DatabaseConnector();
	
	
	/*
	 * Method to return poster by given id
	 */
	
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
 * Method to return all dogs to show in dog profiles
 */

	 public static ArrayList<Dog> getAllDogs (){

        ArrayList<Dog> dogProfiles = new ArrayList<>();

        try{
        Connection connection = databaseConnector.connect();
        Statement statement = connection.createStatement () ;
        ResultSet resultSet = statement.executeQuery ("SELECT * FROM dog") ;
        while (resultSet.next()) {
        	// only add a dog if adoption = false
			if(!resultSet.getBoolean("adopted")) {
			    Dog dog = new Dog(resultSet.getString ("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energyid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"), resultSet.getInt("posterid"), resultSet.getBoolean("adopted"), 
			    resultSet.getString("imagePath"), resultSet.getString("biography"));
                dogProfiles.add(dog);
                }
         }
             connection.close () ;

           }
         catch (SQLException e) {
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
	
		
	


        String sql = "INSERT INTO tags (preference, tagname) VALUES (?, ?)";

        // userid | username | userpassword |      email      | likeddogs

        try{
        	Connection connection = databaseConnector.connect();
        	PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, 0);
            pstmt.setString(2, "hey");
           // pstmt.setString(3, "1234");
            //pstmt.setString(4, "ajisjd");
           // pstmt.setString(4, null);

           // pstmt.setInt(2, user.getAge());
            //pstmt.setString(3, user.getBreed());

            pstmt.executeUpdate();
            System.out.println("Dog uploaded successfully.");

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
}
	
/*
 * Method to get a dog's tags
 */
	
	public static ArrayList<Tag> getDogTags(int dogId){
		ArrayList<Tag> tags = new ArrayList<Tag>();
		
		// get all tags in dogtag data table associated with the dog id
		
		
//		SELECT tagname
//		FROM tags
//		JOIN dogtag On tags.tagid = dogtag.tagid
//		JOIN dog ON dogtag.dogid = dog.dogid
//		WHERE dog.dogid = 0;
//		
		try {
			Connection connection = databaseConnector.connect();
			Statement statement = connection.createStatement () ;
			ResultSet resultSet = statement.executeQuery ("SELECT tagname FROM tags JOIN dogtag On tags.tagid = dogtag.tagid JOIN dog ON dogtag.dogid = "
					+ "dog.dogid WHERE dog.dogid ="+ dogId + ";");
			
			while (resultSet.next()) {	 
//				System.out.println(resultSet.getString("tagname"));
				tags.add(new Tag(resultSet.getString("tagname")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return tags;
	}

/*
 * Method to get user's liked dogs
 */
	
/*

Method to get a dog's tags*/

/*
 
Method to get user's liked dogs*/
	


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


