package backend.database;

import java.sql.*;
import java.sql.Connection.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashMap;

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
 * Public class to centralize all communications to and from database
 */
public class Database {
	
	private static DatabaseConnector databaseConnector = new DatabaseConnector();
	
	public static  Hashtable<Integer,Poster> getAllPosters(){
		 Poster poster = null; 
		 Hashtable<Integer, Poster> posters = new Hashtable<Integer, Poster>();
		 
			 	Connection connection = databaseConnector.connect();
			    String sql = "SELECT * FROM poster";
			   
			        try {
			            Statement preparedStatement = connection.createStatement();
			            ResultSet resultSet = preparedStatement.executeQuery(sql);
			            	while (resultSet.next()) {
				                String displayName = resultSet.getString("displayName");
				                int posterId = resultSet.getInt("poster_id");
				                int score = resultSet.getInt("score");
			                
			                poster = new Poster(score, displayName, posterId); 
			                posters.put(posterId, poster);
			            }
			        }catch (SQLException e) {
				        	 	System.out.println ("Connection failure.") ;
				        	 	e.printStackTrace () ;
				          }

			                
			    return posters;
		}

	
	
	
	/*
	 * DOG METHODS
	 */
	
	public static Hashtable<Integer, PriorityQueue<Dog>> getAllDogs(User user, Set<Integer> posterIds){
		

		 Hashtable<Integer, PriorityQueue<Dog>> dogProfiles = new Hashtable<Integer, PriorityQueue<Dog>>();

		 Connection connection = databaseConnector.connect();
		 try {
			 for (Integer id : posterIds){
				 
				 PriorityQueue<Dog> queue= new PriorityQueue<Dog>();
				 dogProfiles.put(id, queue);// populate the outer hashtable with poster id's
		         Statement statement = connection.createStatement ();
		         Statement statement2 = connection.createStatement();
//		         ResultSet resultSet = statement.executeQuery ("SELECT * FROM dog WHERE dog.dogid NOT IN (SELECT userdogs.dogid FROM userdogs WHERE userdogs.userid = "+ user.getUserID() + " ) AND adopted = false;");
		         ResultSet resultSet = statement.executeQuery 
		        		 ("SELECT * FROM dog WHERE dog.dogid NOT IN (SELECT userdogs.dogid FROM userdogs WHERE userdogs.userid = "+ user.getUserID() + " ) "
		         		+ "AND dog.dogid NOT IN (SELECT userpasseddogs.dogid FROM userpasseddogs WHERE userpasseddogs.userid = "+ user.getUserID() + " );");
		         while (resultSet.next()) {
		        	// only add a dog if adoption = false and its id is not negative (if negative, its a dummy dog)	
		        	 
					String dogName = resultSet.getString ("dogname");
					int dogId = resultSet.getInt("dogid"); 
					int ageId = resultSet.getInt("ageid");  
					int energyId = resultSet.getInt("energylevelid"); 
					int sizeId = resultSet.getInt("sizeid"); 
					int sexId = resultSet.getInt("sexid"); 
					int posterId = resultSet.getInt("posterid"); 
					boolean adoptedBool = resultSet.getBoolean("adopted"); 
					String imagePath = resultSet.getString("imagePath");
					String biography = resultSet.getString("biography");
					Dog dog = new Dog(
							dogName, 
							dogId,
							ageId,
							energyId,
							sizeId,
							sexId,
							posterId,
							adoptedBool,
							imagePath,
							biography
					
						    );

				    
				    dog.setTags(getDogTags(dogId, statement2));
				    
				    dog.calculateScore(user.getDog().getTags()); // initialise dog score
//				    System.out.println("key set =" + dogProfiles.keySet().toString());
				    
				    queue.add(dog);

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
	

	public static Dog getADog(int userid){
//		gets the users ideal dog including its tags
	        Dog dog = null;
	        

	        try{
	        Connection connection = databaseConnector.connect();
	        Statement statement = connection.createStatement () ;
	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM idealdogs WHERE idealdogs.dogid = " + userid + ";") ;
	        while (resultSet.next()) {
	        	// only add a dog if adoption = false and its id is not negative (if negative, its a dummy dog)
			    dog = new Dog(resultSet.getString("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energylevelid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"));  
			    dog.setTags(Database.getIdealDogTags(dog.getId(),connection));
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
					ResultSet resultSet = statement.executeQuery ("UPDATE dog SET adopted = TRUE WHERE dogid = " + d.getId());
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
			String dogName = resultSet.getString ("dogname");
			int dogId = resultSet.getInt("dogid"); 
			int ageId = resultSet.getInt("ageid");  
			int energyId = resultSet.getInt("energylevelid"); 
			int sizeId = resultSet.getInt("sizeid"); 
			int sexId = resultSet.getInt("sexid"); 
//			int posterId = resultSet.getInt("posterid"); 
			boolean adoptedBool = resultSet.getBoolean("adopted"); 
			String imagePath = resultSet.getString("imagePath");
			String biography = resultSet.getString("biography");
			Dog dog = new Dog(
					dogName, 
					dogId,
					ageId,
					energyId,
					sizeId,
					sexId,
					posterId,
					adoptedBool,
					imagePath,
					biography
			
				    );
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

	public static void addUserDog(int dogID,int userID, String table){
	
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
	        try {
	        	 connection = databaseConnector.connect();
	        	 String sql = "INSERT INTO "+table+" (dogid, userid) VALUES (?, ?)";
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
	
	
	public static Hashtable<Integer, Tag> getDogTags(int dogId, Statement statement){
		Hashtable <Integer, Tag> tags = new Hashtable<Integer, Tag>();
		
		// get all tags in dogtag data table associated with the dog id
//		
		try {
//			Statement statement = connection.createStatement() ;
			ResultSet resultSet = statement.executeQuery("SELECT tags.tagname, tags.tagid FROM tags JOIN dogtag ON tags.tagid = dogtag.tagid WHERE dogtag.dogid = " + dogId + ";");
			
			while (resultSet.next()) {	 
				tags.put(resultSet.getInt("tagid"),new Tag(resultSet.getString("tagname")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tags;
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

	public static ArrayList<Dog> getUsersDogs(int userID, String table){
		ArrayList<Dog> list = new ArrayList<Dog>();
		
	    try {
	    	Connection connection = databaseConnector.connect();
	    	Statement statement = connection.createStatement();
	    	 ResultSet resultSet = statement.executeQuery("SELECT * FROM dog JOIN " + table + " ON dog.dogid = " + table + ".dogid WHERE " + table + ".userid = " + userID +";");
			 while (resultSet.next()) {	
				String dogName = resultSet.getString ("dogname");
				int dogId = resultSet.getInt("dogid"); 
				int ageId = resultSet.getInt("ageid");  
				int energyId = resultSet.getInt("energylevelid"); 
				int sizeId = resultSet.getInt("sizeid"); 
				int sexId = resultSet.getInt("sexid"); 
				int posterId = resultSet.getInt("posterid"); 
				boolean adoptedBool = resultSet.getBoolean("adopted"); 
				String imagePath = resultSet.getString("imagePath");
				String biography = resultSet.getString("biography");
				Dog dog = new Dog(
						dogName, 
						dogId,
						ageId,
						energyId,
						sizeId,
						sexId,
						posterId,
						adoptedBool,
						imagePath,
						biography
				
					    );
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
				statement.executeUpdate("UPDATE dog SET adopted = TRUE WHERE dogid = " + d.getId());
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
	
	public static HashMap<Integer, Tag> getAllTags(){

	        HashMap <Integer, Tag> tags = new HashMap<Integer, Tag>();

	        try{
	        Connection connection = databaseConnector.connect();
	        Statement statement = connection.createStatement () ;
	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM tags") ;
	        while (resultSet.next()) {
	        	tags.put(resultSet.getInt("tagid"),new Tag(resultSet.getString("tagname"), resultSet.getInt("tagid")));
	                 
	         }
	             connection.close () ;

	           }
	         catch (SQLException e) {
	        	 	System.out.println ("Connection failure.") ;
	        	 	e.printStackTrace () ;
	          }
	         return tags;
		}
	  
	public static Hashtable<Integer, Tag> getIdealDogTags(int dogId, Connection connection){
		Hashtable <Integer, Tag> tags = new Hashtable<Integer, Tag>();
		
		// get all tags in dogtag data table associated with the dog id
//		
		try {
			Statement statement = connection.createStatement () ;
			ResultSet resultSet = statement.executeQuery ("SELECT tags.tagname, tags.tagid FROM tags JOIN idealdogtag ON tags.tagid = idealdogtag.tagid WHERE idealdogtag.idealdogid = " + dogId + ";");
			
			while (resultSet.next()) {	 
				tags.put(resultSet.getInt("tagid"),new Tag(resultSet.getString("tagname")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return tags;
	}

//	public static void addDogTags(int dogid, int tagid, String tablename){
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		
//        try {
//        	 connection = databaseConnector.connect();
//        	 String sql = "INSERT INTO "+ tablename + " (dogid, tagid) VALUES (?, ?)";
//        	 preparedStatement = connection.prepareStatement(sql);
//        	 preparedStatement.setInt(1, dogid);
//        	 preparedStatement.setInt(2, tagid);
//        	 int rowsAffected = preparedStatement.executeUpdate();
//        	 if (rowsAffected > 0) {
//                System.out.println("DogTag relationship added successfully!");
//            } else {
//                System.out.println("Failed to add DogTag relationship.");
//            }
//        } 
//        catch (SQLException e) {
//            e.printStackTrace();
//          
//        } 
//        finally {
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } 
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//	}
	
	public static void addIdealDogTags(int dogid, Hashtable<Integer,Tag> tags){
		/*
		 * Add tags to ideal dog in database
		 */
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	 connection = databaseConnector.connect();
        	 for(Integer t : tags.keySet()) {
		    	 String sql = "INSERT INTO idealdogtag (idealdogid, tagid) VALUES (?, ?)";
		    	 preparedStatement = connection.prepareStatement(sql);
		    	 
		    	 preparedStatement.setInt(1, dogid);
		    	 preparedStatement.setInt(2, Database.getTagID(tags.get(t).getTagName()));
		    	 
		    	 int rowsAffected = preparedStatement.executeUpdate();
		    	 if (rowsAffected > 0) {
		            System.out.println("IdealDogTag relationship added successfully!");
		        } else {
		            System.out.println("Failed to add IdealDogTag relationship.");
		        }
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
//	public static void setDogTags(ArrayList<Tag> tags, int dogid) {
//		for (Tag t : tags) {
//			Database.addDogTags(dogid,Database.getTagID(t.getTagName()),"idealdogtag");
//		}
//	}
	
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
    	
//        Statement statement = connection.createStatement ();
//        ResultSet resultSet = statement.executeQuery ("SELECT * FROM users WHERE username = " + username + " AND userpassword  = " + password + ";") ;
        
        String sql = "SELECT * FROM users WHERE username = ? AND userpassword = ?";
        
        preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setString(1,  username);
        preparedStatement.setString(2, password);
        
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
        user = new User(resultSet.getString("username"),resultSet.getString("userpassword"));
        user.setUserID(resultSet.getInt("userid"));
        user.setEmail(resultSet.getString("email"));
        
		    for (Dog d : Database.getUsersDogs(user.getUserID(),"userdogs")) {
		    	user.addLikedDogs(d);
		    }
		    for (Dog d : Database.getUsersDogs(user.getUserID(),"userpasseddogs")) {
		    	user.addPassedDogs(d);
		    }
        }
        user.setDog(Database.getADog(user.getUserID()));
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
	
	public static void changeAttribute(Attribute attribute, int dogid, int weight) {

		try {
			Connection connection = databaseConnector.connect();
			Statement statement = connection.createStatement ();
			
			PreparedStatement preppedStatement = connection.prepareStatement("UPDATE idealdogs SET " + attribute.getClass().getSimpleName().toLowerCase() + "id = " + weight + " WHERE dogid = " + dogid + ";");
			
			preppedStatement.executeUpdate();
	    	
			connection.close();
		}
		
		catch (SQLException e) {
 			System.out.println ("Connection failure.") ;
 			e.printStackTrace();
       }
	}
	
	public static void deleteOldTags(int dogid) {
		try {
			Connection connection = databaseConnector.connect();
			Statement statement = connection.createStatement ();
			
			PreparedStatement preppedStatement = connection.prepareStatement("DELETE FROM idealdogtag WHERE idealdogid = " + dogid + ";");
			
			preppedStatement.execute();
	    	
			connection.close();
		}
		
		catch (SQLException e) {
 			System.out.println ("Connection failure.") ;
 			e.printStackTrace () ;
       }
	}
	
	
	public static void onApplicationClose(User user){
		
		
		ArrayList<Dog> likedDogs = Database.getUsersDogs(user.getUserID(),"userdogs");
		for (Dog d : user.getLikedDogs()) {
			if(likedDogs.contains(d) == false) 
				Database.addUserDog(d.getId(), user.getUserID(),"userdogs");	
		}
		
		ArrayList<Dog> passedDogs = Database.getUsersDogs(user.getUserID(),"userpasseddogs");
		for (Dog d : user.getPassedDogs()) {
			if(passedDogs.contains(d) == false) 
				Database.addUserDog(d.getId(), user.getUserID(),"userpasseddogs");	
		}
		
		Dog dog = user.getDog();
		
		
		// update user's ideal dog tags
		Database.deleteOldTags(dog.getId());
		Database.addIdealDogTags(dog.getId(),dog.getTags());
		
		// update user's ideal dog attributes
		Database.changeAttribute(dog.getSex(),dog.getId(),dog.getSex().getWeight()); 
		Database.changeAttribute(dog.getSize(),dog.getId(),dog.getSize().getWeight());  
		Database.changeAttribute(dog.getAge(),dog.getId(),dog.getAge().getWeight());
		Database.changeAttribute(dog.getEnergyLevel(),dog.getId(),dog.getEnergyLevel().getWeight());	
	}
	
}


class DatabaseConnector {
    public Connection connect() {
        // Code to establish a database connection
        try{
        
        	
        	Class.forName("org.postgresql.Driver"); // Replace with your database driver
        	
        	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/paw", "postgres", "123");
//        	System.out.println( "Connected to the PostgreSQL server successfully.");
        	
        	return connection; 


        } catch (ClassNotFoundException | SQLException e) {
        	
        	System.out.println("Connection failed");
            e.printStackTrace();
            
        } 
		return null;
		
    }
        
}