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
					
				//ResultSet resultSetPoster = statement.executeQuery("SELECT * FROM poster");
				 while (resultSet.next()) {	 
//					 	Poster poster = getPosterById(resultSet.getInt("posterId"));
					 
					 	// only add a dog if adoption = false
						if(!resultSet.getBoolean("adopted")) {
							Dog dog = new Dog(resultSet.getString ("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energyid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"), resultSet.getInt("posterid"), resultSet.getBoolean("adopted"), 
									resultSet.getString("imagePath"), resultSet.getString("biography"));
						
						
						
							dogProfiles.add(dog);
						}
						
					 	
				 }
					 connection.close () ;
			           
			       } catch (SQLException e) {
			 			System.out.println ("Connection failure.") ;
			 			e.printStackTrace () ;
			       }	
				  
			    
		return dogProfiles;
		
	}
	
	
	public static void uploadDog(User  user) {

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

        } catch (SQLException e) {
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
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return tags;
	}

/*
 * Method to get user's liked dogs
 */
	
/*
 * Method to add to user's liked dogs
 */
	
	
	
//	public static ArrayList<Dog> getLikedDogs(int userId) {
//		ArrayList<Dog> likedDogs = new ArrayList<Dog>();
//		
////		// get liked tags here 
////		
////		
////		int[] likedDogsIds = query.split(",");
////		// iterate through dogs by tags
////		
////		Object likedDogIds;
////		for(int i = 0; i < likedDogIds.length; i++) {
////			
////		}
////		
//		
//		
//		return likedDogs;
//	}
	
	
	
}


class DatabaseConnector {
    public Connection connect() {
        // Code to establish a database connection
        try{
        	System.out.println("Trying to connect....");
        	
        	Class.forName("org.postgresql.Driver"); // Replace with your database driver
        	
        	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/paw3", "postgres", "doglover123");
//        	System.out.println( "Connected to the PostgreSQL server successfully.");
        	
        	return connection; 

        } catch (ClassNotFoundException | SQLException e) {
        	
        	System.out.println("Connection failed");
            e.printStackTrace();
            
        } 
		return null;
		
    }
    
    
    
}