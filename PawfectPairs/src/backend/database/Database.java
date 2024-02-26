package backend.database;

import java.sql.*;
import java.sql.Connection.*;
import java.util.ArrayList;

import backend.dog.Dog;
import backend.dog.trait.Attribute;
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
	 * DOG METHODS
	 */
	
	public static ArrayList<Dog> getAllDogs(){

	        ArrayList<Dog> dogProfiles = new ArrayList<>();

	        try{
	        Connection connection = databaseConnector.connect();
	        Statement statement = connection.createStatement () ;
	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM dog WHERE adopted = FALSE;");
	        while (resultSet.next()) {
	        	// only add a dog if adoption = false and its id is not negative (if negative, its a dummy dog)	
			    Dog dog = new Dog(resultSet.getString ("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energylevelid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"), resultSet.getInt("posterid"), resultSet.getBoolean("adopted"), 
			    resultSet.getString("imagePath"), resultSet.getString("biography"));
                dogProfiles.add(dog);
	                
	         }
	             connection.close () ;

	           }
	         catch (SQLException e) {
	        	 	System.out.println ("Connection failure.") ;
	        	 	e.printStackTrace () ;
	          }
	         return dogProfiles;
		}
	
	//gets the users ideal dog
	public static Dog getADog(int userid){

        Dog dog = null;
        

        try{
        Connection connection = databaseConnector.connect();
        Statement statement = connection.createStatement () ;
        ResultSet resultSet = statement.executeQuery ("SELECT * FROM idealdogs WHERE idealdogs.dogid = " + userid+ ";") ;
        while (resultSet.next()) {
        	// only add a dog if adoption = false and its id is not negative (if negative, its a dummy dog)
		    dog = new Dog(resultSet.getString("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energylevelid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"));            
         }
             connection.close () ;

           }
         catch (SQLException e) {
        	 	System.out.println ("Connection failure.") ;
        	 	e.printStackTrace () ;
          }
         return dog;
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
	
	public static ArrayList<Dog> getPosterDogs(int posterId){

        ArrayList<Dog> dogProfiles = new ArrayList<>();

        try{
        Connection connection = databaseConnector.connect();
        Statement statement = connection.createStatement () ;
        ResultSet resultSet = statement.executeQuery ("SELECT * FROM dog WHERE dog.posterid = " + posterId + ";");
        while (resultSet.next()) {
        	// only add a dog if adoption = false and its id is not negative (if negative, its a dummy dog)	
		    Dog dog = new Dog(resultSet.getString ("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energylevelid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"), resultSet.getInt("posterid"), resultSet.getBoolean("adopted"), 
		    resultSet.getString("imagePath"), resultSet.getString("biography"));
            dogProfiles.add(dog);
                
         }
             connection.close () ;

           }
         catch (SQLException e) {
        	 	System.out.println ("Connection failure.") ;
        	 	e.printStackTrace () ;
          }
         return dogProfiles;
	}

		
	public static void addLikedDog(int dogID,int userID){
	
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
	        try {
	        	 connection = databaseConnector.connect();
	        	 String sql = "INSERT INTO userdogs (dogid, userid) VALUES (?, ?)";
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
	    	 preparedStatement = connection.prepareStatement("DELETE FROM userdogs WHERE dogid = " + dogID + "AND userid  =" + userID + ";");
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

	public static ArrayList<Dog> getLikedDogs(int userID){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Dog> list = new ArrayList<Dog>();
		
	    try {
	    	 connection = databaseConnector.connect();
	    	 Statement statement = connection.createStatement ();
	    	  
	    	 ResultSet resultSet = statement.executeQuery("SELECT * FROM dog JOIN userdogs ON dog.dogid = userdogs.dogid WHERE userdogs.userid = " + userID +";");
	    			 while (resultSet.next()) {	
	    		 Dog dog = new Dog(resultSet.getString ("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energylevelid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"), resultSet.getInt("posterid"), resultSet.getBoolean("adopted"), 
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
			
	public static void setDogAdopted(Dog d) {		
		if(d.getAdopted() == true) {
			try {
				Connection connection = databaseConnector.connect();
				Statement statement = connection.createStatement () ;
				ResultSet resultSet = statement.executeQuery ("UPDATE dog SET adopted = TRUE WHERE dogid = " + d.getId());
				connection.close();
			}
			
			catch (SQLException e) {
	 			System.out.println ("Connection failure.") ;
	 			e.printStackTrace () ;
	       }
			
		}
}

	
	// method for adding the ideal user dog to the db
	public static void addDog(int userID) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	 connection = databaseConnector.connect();
        	 String sql = "INSERT INTO idealdogs (dogname, ageid, energylevelid, sizeid, sexid,dogid) VALUES (?, ?, ? , ?, ?, ?)";
        	 preparedStatement = connection.prepareStatement(sql);
        	 preparedStatement.setString(1, "idealDog");  
        	 preparedStatement.setInt(2,0);
        	 preparedStatement.setInt(3,0);
        	 preparedStatement.setInt(4,0);
        	 preparedStatement.setInt(5,0);
        	 preparedStatement.setInt(6, userID);
	 
        	 int rowsAffected = preparedStatement.executeUpdate();
        	 if (rowsAffected > 0) {
                System.out.println("Dog added successfully!");
            } else {
                System.out.println("Failed to add Dog");
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
	
	/*
	 * TAG METHODS
	 */
	
	public static ArrayList<Tag> getAllTags(){

	        ArrayList<Tag> tags = new ArrayList<>();

	        try{
	        Connection connection = databaseConnector.connect();
	        Statement statement = connection.createStatement () ;
	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM tags") ;
	        while (resultSet.next()) {
	        	tags.add(new Tag(resultSet.getString("tagname")));
	                 
	         }
	             connection.close () ;

	           }
	         catch (SQLException e) {
	        	 	System.out.println ("Connection failure.") ;
	        	 	e.printStackTrace () ;
	          }
	         return tags;
		}
	  
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
			ResultSet resultSet = statement.executeQuery ("SELECT tagname FROM tags JOIN idealdogtag ON tags.tagid = idealdogtag.tagid WHERE idealdogtag.idealdogid = " + dogId + ";");
			
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

	public static void addDogTags(int dogid, int tagid, String tablename){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	 connection = databaseConnector.connect();
        	 String sql = "INSERT INTO "+ tablename + " (idealdogid, tagid) VALUES (?, ?)";
        	 preparedStatement = connection.prepareStatement(sql);
        	 preparedStatement.setInt(1, dogid);
        	 preparedStatement.setInt(2, tagid);
        	 int rowsAffected = preparedStatement.executeUpdate();
        	 if (rowsAffected > 0) {
                System.out.println("DogTag relationship added successfully!");
            } else {
                System.out.println("Failed to add DogTag relationship.");
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
	
	
	//method for adding the tags to the ideal dog
	public static void setDogTags(ArrayList<Tag> tags, int dogid) {
		for (Tag t : tags) {
			Database.addDogTags(dogid,Database.getTagID(t.getTagName()),"idealdogtag");
		}
	}
	
	public static int getTagID(String tagname) {
		int tagid = 0;
		try{
	        Connection connection = databaseConnector.connect();
	        Statement statement = connection.createStatement () ;
	        ResultSet resultSet = statement.executeQuery ("SELECT tagid FROM tags WHERE tagname = '" + tagname + "'") ;
	        while (resultSet.next()) {
	        	tagid = resultSet.getInt("tagid");         
	         }
	             connection.close () ;

	           }
	         catch (SQLException e) {
	        	 	System.out.println ("Connection failure.") ;
	        	 	e.printStackTrace () ;
	          }
	         
		return tagid;
		
		
	}
	
	public static void removeDogTags(int dogid,int tagid, String tablename) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    try {
	    	 connection = databaseConnector.connect();
	    	 preparedStatement = connection.prepareStatement("DELETE FROM "+ tablename +" WHERE idealdogid = " + dogid + " AND tagid  = " + tagid + ";");
	    	 int rowsAffected = preparedStatement.executeUpdate();
	    	 if (rowsAffected > 0) {
	            System.out.println("Dogtag remove successfully!");
	        } else {
	            System.out.println("Failed to remove Dogtag ");
	        }
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	      
	    } 
		
	}

	public static User getUser(String username, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		User user = null;
		
	    try {
	    	connection = databaseConnector.connect();
	    	
//	        Statement statement = connection.createStatement ();
//	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM users WHERE username = " + username + " AND userpassword  = " + password + ";") ;
	        
	        String sql = "SELECT * FROM users WHERE username = ? AND userpassword = ?";
	        
	        preparedStatement = connection.prepareStatement(sql);
	        
	        preparedStatement.setString(1,  username);
	        preparedStatement.setString(2, password);
	        
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	        user = new User(resultSet.getString("username"),resultSet.getString("userpassword"));
	        user.setUserID(resultSet.getInt("userid"));
	        user.setEmail(resultSet.getString("email"));
			    for (Dog d : Database.getLikedDogs(user.getUserID())) {
			    	user.addLikedDogs(d);
			    }
	        }
	        user.setDog(user.getUserID());
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	      
	    }
	    
		return user; 	
	}
	
	// gets user id
	public static int getUserID(String username, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int userid = 0;
		
	    try {
	    	connection = databaseConnector.connect();
	    	
//	        Statement statement = connection.createStatement ();
//	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM users WHERE username = " + username + " AND userpassword  = " + password + ";") ;
	        
	        String sql = "SELECT userid FROM users WHERE username = ? AND userpassword = ?";
	        
	        preparedStatement = connection.prepareStatement(sql);
	        
	        preparedStatement.setString(1,  username);
	        preparedStatement.setString(2, password);
	        
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	        	userid = resultSet.getInt("userid");
	        }
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	      
	    }
	    
		return userid; 	
	}

	public static boolean addUser(String username, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	 connection = databaseConnector.connect();
        	 String sql = "INSERT INTO users (username, userpassword) VALUES (?, ?)";
        	 preparedStatement = connection.prepareStatement(sql);
        	 preparedStatement.setString(1, username);
        	 preparedStatement.setString(2, password);
        	 int rowsAffected = preparedStatement.executeUpdate();
        	 if (rowsAffected > 0) {
                System.out.println("User added successfully!");
                int userid = Database.getUserID(username, password);
                Database.addDog(userid);
                return true;
            } else {
                System.out.println("Failed to add User.");
                return false;
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
        return false;
	}
	
	public static void changeAttribute(Attribute attribute, int dogid) {

		try {
			Connection connection = databaseConnector.connect();
			Statement statement = connection.createStatement ();
			
			PreparedStatement preppedStatement = connection.prepareStatement("UPDATE idealdogs SET " + attribute.getClass().getSimpleName().toLowerCase() + "id = " + attribute.getWeight() + " WHERE dogid = " + dogid + ";");
			
			int rowsAffected = preppedStatement.executeUpdate();
	    	 if (rowsAffected > 0) {
	            System.out.println("Attribute updated successfully!");
	        } else {
	            System.out.println("Failed to update attribute ");
	        }
//			System.out.println("dogid: " + dogid);
//			statement.executeQuery("UPDATE idealdogs SET " + attribute.getClass().getSimpleName().toLowerCase() + "id = " + attribute.getWeight() + " WHERE dogid = " + dogid + ";");
			connection.close();
		}
		
		catch (SQLException e) {
 			System.out.println ("Connection failure.") ;
 			e.printStackTrace () ;
       }
		
		/*
		
		 try {
	    	 connection = databaseConnector.connect();
	    	 preparedStatement = connection.prepareStatement("DELETE FROM "+ tablename +" WHERE idealdogid = " + dogid + " AND tagid  = " + tagid + ";");
	    	 int rowsAffected = preparedStatement.executeUpdate();
	    	 if (rowsAffected > 0) {
	            System.out.println("Dogtag remove successfully!");
	        } else {
	            System.out.println("Failed to remove Dogtag ");
	        }
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	      
	    } */
		
}
	
//	public static void onApplicationClose(User user, ArrayList<Dog> doglist){
//		Database.updateAllAdoptedDogs(doglist);
//		for (Dog d : user.getLikedDogs()) {
//			Database.addLikedDog(d.getId(), user.getUserID());
//		} 
//		for(Tag t : user.getDog().getTags()) {
//			Database.addDogTags(user.getDog().getId(), Database.getTagID(t.getTagName()));
//		}
//	}

	
}






class DatabaseConnector {
    public Connection connect() {
        // Code to establish a database connection
        try{
        
        	
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




