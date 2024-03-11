package backend.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;

import backend.dog.Dog;
import backend.dog.trait.Age;
import backend.dog.trait.Attribute;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;
import backend.wallet.Wallet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
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
				                String phone = resultSet.getString("phone");
				                String email = resultSet.getString("email");
			                
			                poster = new Poster(score, displayName, posterId, phone, email); 
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
	
	public static Hashtable<Integer, ArrayList<Dog>> getAllDogs(User user, Set<Integer> posterIds){
		

		Hashtable<Integer, ArrayList<Dog>> dogProfiles = new Hashtable<Integer, ArrayList<Dog>>();
		for (Integer id : posterIds){
				 ArrayList<Dog> queue = new ArrayList<Dog>();
				 dogProfiles.put(id, queue);// populate the outer hashtable with poster id's
		}		 
		Connection connection = databaseConnector.connect();
		try {	
	         Statement statement = connection.createStatement () ;
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
			    
			    
			    dog.calculateScore(user.getTagPreferences()); // initialise dog score
//				    System.out.println("key set =" + dogProfiles.keySet().toString());
			    
//			    for(Dog d : queue) {
//		        	 System.out.println(d.getName() + d.getPosterId());
//		         }
			    
			  
			    
			   dogProfiles.get(posterId).add(dog);

	        
         	 }
	         
            connection.close () ;

           }
         	catch (SQLException e) {
        	 	System.out.println ("Connection failure.") ;
        	 	e.printStackTrace () ;
           }
         	return dogProfiles;
	}
	

//	public static Dog getADog(int userid){
////		gets the users ideal dog including its tags
//	        Dog dog = null;
//	        
//
//	        try{
//	        Connection connection = databaseConnector.connect();
//	        Statement statement = connection.createStatement () ;
//	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM idealdogs WHERE idealdogs.dogid = " + userid + ";") ;
//	        while (resultSet.next()) {
//	        	// only add a dog if adoption = false and its id is not negative (if negative, its a dummy dog)
//			    dog = new Dog(resultSet.getString("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),  resultSet.getInt("energylevelid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"));  
//			    dog.setTags(Database.getIdealDogTags(dog.getId(),connection));
//	         }
//	             connection.close () ;
//
//	           }
//	         catch (SQLException e) {
//	        	 	System.out.println ("Connection failure.") ;
//	        	 	e.printStackTrace () ;
//	          }
//	         return dog;
//	}
//	
	
	
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
//	public static void addDog(int userID) {
//		
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		
//        try {
//        	 connection = databaseConnector.connect();
//        	 String sql = "INSERT INTO idealdogs (dogname, ageid, energylevelid, sizeid, sexid,dogid) VALUES (?, ?, ? , ?, ?, ?)";
//        	 preparedStatement = connection.prepareStatement(sql);
//        	 preparedStatement.setString(1, "idealDog");  
//        	 preparedStatement.setInt(2,0);
//        	 preparedStatement.setInt(3,0);
//        	 preparedStatement.setInt(4,0);
//        	 preparedStatement.setInt(5,0);
//        	 preparedStatement.setInt(6, userID);
//	 
//        	 int rowsAffected = preparedStatement.executeUpdate();
//        	 if (rowsAffected > 0) {
//                System.out.println("Dog added successfully!");
//            } else {
//                System.out.println("Failed to add Dog");
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
	  
//	public static Hashtable<Integer, Tag> getIdealDogTags(int dogId, Connection connection){
//		Hashtable <Integer, Tag> tags = new Hashtable<Integer, Tag>();
//		
//		// get all tags in dogtag data table associated with the dog id
////		
//		try {
//			Statement statement = connection.createStatement () ;
//			ResultSet resultSet = statement.executeQuery ("SELECT tags.tagname, tags.tagid FROM tags JOIN idealdogtag ON tags.tagid = idealdogtag.tagid WHERE idealdogtag.idealdogid = " + dogId + ";");
//			
//			while (resultSet.next()) {	 
//				tags.put(resultSet.getInt("tagid"),new Tag(resultSet.getString("tagname")));
//			}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//		return tags;
//	}

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
	
	public static void addPreferenceTagsToUser(Hashtable<Integer,Tag> tags, int userId){
		/*
		 * Add tags to user preferences in database
		 */
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	 connection = databaseConnector.connect();
        	 for(Integer t : tags.keySet()) {
		    	 String sql = "INSERT INTO userattributepreferences (userid, tagid) VALUES (?, ?)";
		    	 preparedStatement = connection.prepareStatement(sql);
		    	 
		    	 preparedStatement.setInt(1, userId);
		    	 preparedStatement.setInt(2, Database.getTagID(tags.get(t).getTagName()));
		    	 
		    	 int rowsAffected = preparedStatement.executeUpdate();
		    	 if (rowsAffected > 0) {
		            System.out.println("User tag preference relationship added successfully!");
		        } else {
		            System.out.println("Failed to add user tag preference relationship.");
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
//	//method for adding the tags to the ideal dog
////	public static void setDogTags(ArrayList<Tag> tags, int dogid) {
////		for (Tag t : tags) {
////			Database.addDogTags(dogid,Database.getTagID(t.getTagName()),"idealdogtag");
////		}
////	}
	
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
	
//	public static void removeDogTags(int dogid,int tagid, String tablename) {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//	    try {
//	    	 connection = databaseConnector.connect();
//	    	 preparedStatement = connection.prepareStatement("DELETE FROM "+ tablename +" WHERE idealdogid = " + dogid + " AND tagid  = " + tagid + ";");
//	    	 int rowsAffected = preparedStatement.executeUpdate();
//	    	 if (rowsAffected > 0) {
//	            System.out.println("Dogtag remove successfully!");
//	        } else {
//	            System.out.println("Failed to remove Dogtag ");
//	        }
//	    } 
//	    catch (SQLException e) {
//	        e.printStackTrace();
//	      
//	    } 
//		
//	}


	
	public static User getUser(String username, String password) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	User user = null;
	
    try {
    	connection = databaseConnector.connect();
        
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
        int userId = user.getUserID();
        
        user.setAgePreferences(getUsersPreferredAttributes(userId, 0));
        user.setSexPreferences(getUsersPreferredAttributes(userId, 1));
        user.setSizePreferences(getUsersPreferredAttributes(userId, 3));
        user.setEnergyLevelPreferences(getUsersPreferredAttributes(userId, 2));
        
        user.setTagPreferences(getUsersPreferredTags(userId));


    } 
    catch (SQLException e) {
        e.printStackTrace();
      
    }
    
	return user; 	
}
	
public static ArrayList<Attribute> getUsersPreferredAttributes(int userid, int attType){
		
		ArrayList<Attribute> newList = new ArrayList<Attribute>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	 connection = databaseConnector.connect();
		    	 String sql = "SELECT * FROM userattributepreferences WHERE userid = " + userid + " AND attributetype = " + attType + ";"; 
		    	 preparedStatement = connection.prepareStatement(sql);
		    	 
		    	 ResultSet resultSet = preparedStatement.executeQuery();
		    	 
		    	 while (resultSet.next()) {
			        	newList.add(new Age(resultSet.getInt("attributeid")));
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
	
        return newList;
		
	}

	public static Hashtable<Integer, Tag> getUsersPreferredTags(int userId){
	Hashtable <Integer, Tag> tags = new Hashtable<Integer, Tag>();
	Connection connection = null;
	// get all tags in dogtag data table associated with the dog id
//	
	try {
		connection = databaseConnector.connect();
		Statement statement = connection.createStatement () ;
		ResultSet resultSet = statement.executeQuery ("SELECT tagid FROM usertagpreferences WHERE userid = " + userId + ";");
		
		while (resultSet.next()) {	 
			tags.put(resultSet.getInt("tagid"),new Tag(resultSet.getString("tagname")));
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	return tags;
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

	public static boolean addUser(String username, String password, HashMap<Integer,ArrayList<Attribute>> allAttributes) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		
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
                String sql2 = "INSERT INTO userattributepreferences (userid, attributetype, attributeid) VALUES ";
                
                for(int i = 0; i < allAttributes.keySet().size(); i++) { // type
                	for(int j = 0; j < allAttributes.get(i).size(); j++) { // weight
                		sql2 += "(" + userid + "," + i + "," + j +"),";
                	}
                }
                
                
                sql2 = sql2.substring( 0, sql2.length() - 1);
                
                preparedStatement2 = connection.prepareStatement(sql2);
                
                preparedStatement2.executeUpdate();
            	 		
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
	
	public static boolean blankWallet(int userid) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = databaseConnector.connect();

	        String sql = "INSERT INTO userfunds (balance, userid, recurringpayment, "
	                + "frequency, recurringamount, postertosponsorpending, recurringposter, "
	                + "oldpaymentdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setInt(1, 0);
	        preparedStatement.setInt(2, userid);
	        preparedStatement.setBoolean(3, false);
	        preparedStatement.setInt(4, 0);
	        preparedStatement.setInt(5, 0);
	        preparedStatement.setInt(6, 0);
	        preparedStatement.setInt(7, 0);
	        preparedStatement.setDate(8, null);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Wallet added successfully!");
	            return true;
	        } else {
	            System.out.println("Failed to add Wallet.");
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in a separate try-catch block to ensure closure even if an exception occurs
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	
	public static void deleteUserAttributePreferences(int userId) {

		try {
			Connection connection = databaseConnector.connect();
			Statement statement = connection.createStatement ();
			
			PreparedStatement preppedStatement = connection.prepareStatement("DELETE FROM userattributepreferences WHERE userid = " + userId + ";");
			
			preppedStatement.executeUpdate();
	    	
			connection.close();
		}
		
		catch (SQLException e) {
 			System.out.println ("Connection failure.") ;
 			e.printStackTrace();
       }
	}
	
	public static void addUserAttributePreferences(ArrayList<Attribute> atts, int userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	 connection = databaseConnector.connect();
        	 for(Attribute att : atts) {
		    	 String sql = "INSERT INTO userattributepreferences (userid, attributetype, attributeid) VALUES (?, ?, ?)";
		    	 preparedStatement = connection.prepareStatement(sql);
		    	 
		    	 preparedStatement.setInt(1, userId);
		    	 preparedStatement.setInt(2, att.getType());
		    	 preparedStatement.setInt(3, att.getWeight());
		    	 
		    	 int rowsAffected = preparedStatement.executeUpdate();
		    	 if (rowsAffected > 0) {
		            System.out.println("User tag preference relationship added successfully!");
		        } else {
		            System.out.println("Failed to add user tag preference relationship.");
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

	public static void deletePreferenceTagsFromUser(Hashtable<Integer, Tag> tags, int userId) {
		try {
			Connection connection = databaseConnector.connect();
			Statement statement = connection.createStatement ();
			
			PreparedStatement preppedStatement = connection.prepareStatement("DELETE FROM usertagpreferences WHERE userid = " + userId + ";");
			
			preppedStatement.execute();
	    	
			connection.close();
		}
		
		catch (SQLException e) {
 			System.out.println ("Connection failure.") ;
 			e.printStackTrace () ;
       }
	}
	
	public static void onApplicationClose(User user, ArrayList<Dog> doglist){
		Database.updateAllAdoptedDogs(doglist); // sets dogs to be adopted 
		
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
		
		// TO DO: update user's attribute preferences and tag preferences :)
		
//		Dog dog = user.getDog();
		
		ArrayList<Attribute> age = user.getAgePreferences();
		ArrayList<Attribute> sex = user.getSexPreferences();
		ArrayList<Attribute> size = user.getSizePreferences();
		ArrayList<Attribute> energyLevel = user.getEnergyLevelPreferences();
		Hashtable<Integer, Tag> tags = user.getTagPreferences();
		
		
		int userId = user.getUserID();
		// update user's preferred dog tags
		
		
		Database.deletePreferenceTagsFromUser(tags, userId);
		Database.addPreferenceTagsToUser(tags, userId);
//		
		// update user's ideal dog attributes
		
		Database.deleteUserAttributePreferences(userId);
		
		Database.addUserAttributePreferences(age, userId);
		Database.addUserAttributePreferences(energyLevel, userId);
		Database.addUserAttributePreferences(size, userId);
		Database.addUserAttributePreferences(sex, userId);
	}
	
	
//	// DB METHODS FOR WALLET AND POSTER INFO
//	public static Wallet getWallet(int userid, String password) {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		Wallet wallet = null;
//
//		try {
//			connection = databaseConnector.connect();
//
//			//	        Statement statement = connection.createStatement ();
//			//	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM users WHERE username = " + username + " AND userpassword  = " + password + ";") ;
//
//			String sql = "SELECT * FROM userfunds WHERE userid = ?";
//
//			preparedStatement = connection.prepareStatement(sql);
//
//			preparedStatement.setInt(1,  userid);
//
//
//			ResultSet resultSet = preparedStatement.executeQuery();
//			//	public Wallet(double balance, boolean recurringPayment, int frequency, int userid,	Map<Integer, Double> posterWallets, int recurringAmount) {
//			if (resultSet.next()) {
//				//	public Wallet(double balance, boolean recurringPayment, int frequency, int userid,int recurringAmount, int posterToSponsorPending, int recurringPoster) {
//
//				wallet = new Wallet(resultSet.getDouble("balance"),resultSet.getBoolean("recurringpayment"), resultSet.getInt("frequency"),resultSet.getInt("userid"), 
//						resultSet.getInt("recurringamount"), resultSet.getInt("posterToSponsorPending"),resultSet.getInt("recurringPoster"));
//				//				for (Dog d : Database.getLikedDogs(user.getUserID())) {
//				//					user.addLikedDogs(d);
//				//				}
//			}
//			//			user.setDog(user.getUserID());
//		} 
//		catch (SQLException e) {
//			e.printStackTrace();
//
//		}
//
//		return wallet; 	
//	}
//
//	public static void setUserOldPaymentDate(LocalDate date, int userid) {
//
//		try {
//			
//			Connection connection = databaseConnector.connect();
//			String query = "UPDATE userfunds SET oldpaymentdate = ? WHERE userid = ?";
//	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//	            // Convert LocalDate to java.sql.Date
//	            java.sql.Date sqlDate = Date.valueOf(date);
//
//	            // Set parameters for the prepared statement
//	            preparedStatement.setDate(1, sqlDate);
//	            preparedStatement.setInt(2, userid);
//
//	            // Execute the update
//	            int affectedRows = preparedStatement.executeUpdate();
//
//	            if (affectedRows > 0) {
//	                System.out.println("User old payment date updated successfully.");
//	            } else {
//	                System.out.println("No rows were affected. User not found?");
//	            }
//	        } finally {
//	            connection.close();
//	        }
//	    } 
//
//		catch (SQLException e) {
//			System.out.println ("Connection failure.") ;
//			e.printStackTrace () ; 
//		}
//	}
//	
////	public static void setPosterWallets(Wallet wallet, int userid) {
////	    try (Connection connection = databaseConnector.connect()) {
////	        String updateQuery = "UPDATE posterfunds SET " +
////	                "balance = ?, " +
////	                "posterid = ?, " +
////	                "userid = ?, " ;
////
////	        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
////	        	Map<Integer, Double> postermap = wallet.getPosterWallets();
////	            // Set parameters for the prepared statement
////	        	for (Integer key : postermap.keySet()) {
////	        	   // System.out.println(key + ": " + postermap.get(key));
////	        	
////	        	preparedStatement.setDouble (1,postermap.get(key));
////	            preparedStatement.setInt(2, key);
////	            preparedStatement.setInt(3, userid);
////	        	}
////	            
////	           
////	            // Execute the update
////	            int affectedRows = preparedStatement.executeUpdate();
////
////	            if (affectedRows > 0) {
////	                System.out.println("Poster Funds information updated successfully.");
////	            } else {
////	                System.out.println("No rows were affected. User not found?");
////	            }
////	        }
////	    } catch (SQLException e) {
////	        e.printStackTrace();
////	    }
////	}
////	public static Map<Integer, Double> getPosterWallets (Wallet wallet, int userid){
////		Map<Integer, Double> posterWallets = new TreeMap<>();
////		
////		try{
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement () ;
////			ResultSet resultSet = statement.executeQuery ("SELECT * FROM posterfunds WHERE userid = '" + userid + "'") ;
////			while (resultSet.next()) {
////				posterWallets.put(resultSet.getInt("posterid"),resultSet.getDouble("balance") );
////			}
////			 
////			  // Statement upon success
////	        System.out.println("Poster Funds information retrieved successfully.");
////
////		}
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ;
////		}
////		return posterWallets;
////		
////	}
////	
////	
////	public static void setWallet(Wallet wallet, int userId) {
////	    try (Connection connection = databaseConnector.connect()) {
////	        String updateQuery = "UPDATE userfunds SET " +
////	                "balance = ?, " +
////	                "recurringpayment = ?, " +
////	                "frequency = ?, " +
////	                "recurringamount = ?, " +
////	                "postertosponsorpending = ?, " +
////	                "recurringposter = ?, " +
////	                "oldpaymentdate = ? " +
////	                "WHERE userid = ?";
////
////	        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
////	            // Set parameters for the prepared statement
////	            preparedStatement.setDouble (1, wallet.getBalance());
////	            preparedStatement.setBoolean(2, wallet.getRecurringPayment());
////	            preparedStatement.setInt(3, wallet.getFrequency());
////	            preparedStatement.setDouble (4, wallet.getRecurringAmount());
////	            preparedStatement.setInt(5, wallet.getPosterToSponsorPending());
////	            preparedStatement.setInt(6, wallet.getRecurringPoster());
////	            
////	            // Convert LocalDate to java.sql.Date for oldpaymentdate
////	            java.sql.Date sqlDate = Date.valueOf(wallet.getOldPaymentDate());
////	            preparedStatement.setDate(7, sqlDate);
////
////	            preparedStatement.setInt(8, userId);
////
////	            // Execute the update
////	            int affectedRows = preparedStatement.executeUpdate();
////
////	            if (affectedRows > 0) {
////	                System.out.println("Wallet information updated successfully.");
////	            } else {
////	                System.out.println("No rows were affected. User not found?");
////	            }
////	        }
////	    } catch (SQLException e) {
////	        e.printStackTrace();
////	    }
////	}
////	public static ArrayList<String> getPosterInfo(int posterid){//index 0 = poster email, index 1 = poster phone
////		ArrayList<String> valReturn= new ArrayList<>();
////		try{
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement () ;
////			// ArrayList<String> finding = new ArrayList<>(List.of("posteremail", "posterphone"));
////			ResultSet resultSet = statement.executeQuery ("SELECT * FROM posterinfo WHERE posterinfoid = '" + posterid + "'") ;
////			while (resultSet.next()) {
////				valReturn.add(resultSet.getString("posteremail"));
////				valReturn.add(resultSet.getString("posterphone"));
////			}
////			connection.close () ;
////
////		}
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ;
////		}
////
////		return valReturn;
////	}
////	public static void setPosterFunds(int newBalance, int posterid) {
////
////		try {
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement ();
////
////			PreparedStatement preppedStatement = connection.prepareStatement("UPDATE posterfunds SET balance = " + newBalance + " WHERE posterid = " + posterid + ";");
////			//	UPDATE public."PosterFunds" SET "Balance" =100 WHERE "PosterId" = 1; 
////			preppedStatement.executeUpdate();
////
////			connection.close();
////		}
////
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ; 
////		}
////	}
////
////	public static int getPosterFunds(int posterid) {
////		int balance=0;
////		try{
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement () ;
////			// ArrayList<String> finding = new ArrayList<>(List.of("posteremail", "posterphone"));
////			ResultSet resultSet = statement.executeQuery ("SELECT * FROM posterfunds WHERE posterid = '" + posterid + "'") ;
////			while (resultSet.next()) {
////				balance=resultSet.getInt("balance");
////			}
////			connection.close () ;
////
////		}
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ;
////		}
////
////		return balance;
////	}
////
////
////	public static void setUserFunds(int newBalance, int userid) {
////
////		try {
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement ();
////
////			PreparedStatement preppedStatement = connection.prepareStatement("UPDATE userfunds SET balance = " + newBalance + " WHERE userid = " + userid + ";");
////			preppedStatement.executeUpdate();
////
////			connection.close();
////		}
////
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ; 
////		}
////	}
////
////	public static void setUserRecurringPayment(boolean recur, int userid) {
////
////		try {
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement ();
////
////			PreparedStatement preppedStatement = connection.prepareStatement("UPDATE userfunds SET recurringpayment = " + recur + " WHERE userid = " + userid + ";");
////			preppedStatement.executeUpdate();
////
////			connection.close();
////		}
////
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ; 
////		}
////	}
////
////	public static boolean getUserRecurringPayment(int userid) {
////		boolean balance=false;
////		try{
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement () ;
////			ResultSet resultSet = statement.executeQuery ("SELECT * FROM userfunds WHERE userid = '" + userid + "'") ;
////			while (resultSet.next()) {
////				balance=resultSet.getBoolean("recurringpayment");
////			}
////			connection.close () ;
////
////		}
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ;
////		}
////
////		return balance;
////	}
////	public static int getUserRecurringAmount(int userid) {
////		int balance=0;
////		try{
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement () ;
////			ResultSet resultSet = statement.executeQuery ("SELECT * FROM userfunds WHERE userid = '" + userid + "'") ;
////			while (resultSet.next()) {
////				balance=resultSet.getInt("recurringamount");
////			}
////			connection.close () ;
////
////		}
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ;
////		}
////
////		return balance;
////	}
////
////	public static int getUserFrequency(int userid) {
////		int frequency=0;
////		try{
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement () ;
////			ResultSet resultSet = statement.executeQuery ("SELECT * FROM userfunds WHERE userid = '" + userid + "'") ;
////			while (resultSet.next()) {
////				frequency=resultSet.getInt("frequency");
////			}
////			connection.close () ;
////
////		}
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ;
////		}
////
////		return frequency;
////	}
////
////	public static void setUserfrequency(int freq, int userid) {
////
////		try {
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement ();
////
////			PreparedStatement preppedStatement = connection.prepareStatement("UPDATE userfunds SET frequency = " + freq + " WHERE userid = " + userid + ";");
////			preppedStatement.executeUpdate();
////
////			connection.close();
////		}
////
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ; 
////		}
////	}
////	
////	public static LocalDate getUserOldPaymentDate(int userid) {
////		LocalDate balance=null;
////		try{
////			
////			
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement () ;
////			ResultSet resultSet = statement.executeQuery ("SELECT * FROM userfunds WHERE userid = '" + userid + "'") ;
////			while (resultSet.next()) {
////				balance=resultSet.getDate("oldpaymentdate").toLocalDate();;
////			}
////			connection.close () ;
////
////		}
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ;
////		}
////
////		return balance;
////	}
////	public static int getUserFunds(int userid) {
////		int balance=0;
////		try{
////			Connection connection = databaseConnector.connect();
////			Statement statement = connection.createStatement () ;
////			ResultSet resultSet = statement.executeQuery ("SELECT * FROM userfunds WHERE userid = '" + userid + "'") ;
////			while (resultSet.next()) {
////				balance=resultSet.getInt("balance");
////			}
////			connection.close () ;
////
////		}
////		catch (SQLException e) {
////			System.out.println ("Connection failure.") ;
////			e.printStackTrace () ;
////		}
////
////		return balance;
////	}
////	
}
//

class DatabaseConnector {
    public Connection connect() {
        // Code to establish a database connection
        try{
        
        	
        	Class.forName("org.postgresql.Driver"); // Replace with your database driver
        	

        	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/paw6", "postgres", "doglover123");

//        	System.out.println( "Connected to the PostgreSQL server successfully.");
        	
        	return connection; 


        } catch (ClassNotFoundException | SQLException e) {
        	
        	System.out.println("Connection failed");
            e.printStackTrace();
            
        } 
		return null;
		
    }
        
}