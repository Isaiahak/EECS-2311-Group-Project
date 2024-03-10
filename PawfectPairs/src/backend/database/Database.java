package backend.database;

import java.sql.*;
import java.sql.Connection.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeMap;

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
	
	//Sidney and Edson's stuff :)
	
	public static void deleteAppointment (int posterID, int dogID) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = databaseConnector.connect();// Assuming you have a method to get the database connection
            String query = "DELETE FROM datesbooked WHERE \"posterID\" = ? AND \"dogID\" = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, posterID);
            statement.setInt(2, dogID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	
	public static Dog getDogById(int dogId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Dog dog = null;

        try {
            connection = databaseConnector.connect();
            String sql = "SELECT * FROM dog WHERE dogid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, dogId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    dog = new Dog(resultSet.getString("dogname"), resultSet.getInt("dogid"), resultSet.getInt("ageid"),
                            resultSet.getInt("energylevelid"), resultSet.getInt("sizeid"), resultSet.getInt("sexid"),
                            resultSet.getInt("posterid"), resultSet.getBoolean("adopted"), resultSet.getString("imagePath"),
                            resultSet.getString("biography"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dog;
    }
	
	 public static TreeMap<Integer, Date> getUserAppointments(int userID) {
		 Connection connection = databaseConnector.connect();
	        TreeMap<Integer, Date> appointments = new TreeMap<>();
	        try {

	        String query = "SELECT \"dogID\", date FROM datesbooked WHERE \"userID\" = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	            // Set the userID parameter
	            preparedStatement.setInt(1, userID);

	            // Execute the query
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                // Iterate over the result set and populate the TreeMap
	                while (resultSet.next()) {
	                    int dogID = resultSet.getInt("dogID");
	                    Date date = resultSet.getDate("date");
	                    appointments.put(dogID, date);
	                }
	            }
	 
	        
	        return appointments;
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	        
	    }

	 // Method to check if a date exists in the table
	    public static boolean isDateExists(int  dogID,int userID, Connection connection) {
	    	
	        PreparedStatement preparedStatement = null;
	        String query = "SELECT COUNT(*) FROM datesbooked WHERE \"dogID\" = ? AND \"userID\" = ?";
	        try {
	        	connection = databaseConnector.connect();
	        	preparedStatement = connection.prepareStatement(query);
	        	
	            
	        	preparedStatement.setInt(1, dogID);
	        	preparedStatement.setInt(2, userID);
	           

	            // Execute the query
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                // If any rows are returned, the date exists
	                if (resultSet.next()) {
	                    int count = resultSet.getInt(1);
	                    System.out.println("Date and Poster found successfully!");
	                    return count > 0;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false; // Return false if an exception occurs or no rows are returned
	    }

	    public static boolean addBookedDate(int posterID, int dogID, Date date, int userID) {
	        
	    	Connection connection = databaseConnector.connect();
	        PreparedStatement preparedStatement = null ;
	        
	        
	        try {
	            //String sql = "INSERT INTO datesbooked (\"posterID\", \"dogID\", \"date\", \"userID\") VALUES (?, ?, ?, ?)";
	            String sql = "INSERT INTO datesbooked (\"posterID\", \"dogID\", \"date\", \"userID\") VALUES (?, ?, ?,?)";
	     
	            connection = databaseConnector.connect();
	        	 
	        	 preparedStatement = connection.prepareStatement(sql);
	        	 if (!(isDateExists(dogID, userID,connection)==true)) {
	            // Convert Calendar to java.util.Date
	            preparedStatement.setInt(1, posterID);
	            preparedStatement.setInt(2, dogID);
	            preparedStatement.setDate(3, date);
	            preparedStatement.setInt(4, userID );
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Date added successfully!");
	                ;
	                return true;
	            } else {
	                System.out.println("Failed to add Date.");
	                return false;
	            }}
	        	 else {
	        		 System.out.println("Date is already there.");
	        	 }
	            
	           /* if (!(isDateExists(date, posterID, connection)==true)) {
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, posterID);
	            preparedStatement.setInt(2, dogID);
	           // preparedStatement.setDate(3, date);
	            //preparedStatement.setInt (4, userID);
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Date added successfully!");
	                //int userid = Database.getUserID(username, password,date, userID);
	                //Database.addDog(userid);
	                return true;
	            } else {
	                System.out.println("Failed to add User.");
	                return false;
	            }
	            }
	            else {
	            	System.out.println("Date is already there. Me don't understand what ur doing please stop :)");
	            }*/
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (preparedStatement != null) {
	                    preparedStatement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return false;
	    }
	    
	    
	    

	    static class DatabaseConnector {
	        public Connection connect() {
	            // Code to establish a database connection
	            try {
	                Class.forName("org.postgresql.Driver"); // Replace with your database driver
	                
	                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/paw", "postgres", "123");
	                // System.out.println( "Connected to the PostgreSQL server successfully.");
	                
	                return connection; 
	            } catch (ClassNotFoundException | SQLException e) {
	                System.out.println("Connection failed");
	                e.printStackTrace();
	            } 
	            return null;
	        }   
	    }
	    
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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




