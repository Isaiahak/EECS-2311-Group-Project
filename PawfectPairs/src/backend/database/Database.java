package backend.database;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.dog.Dog;
import backend.dog.trait.*;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;
import backend.wallet.RecurringPayment;
import backend.wallet.Wallet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Public class to centralize all communications to and from database
 */
public class Database {

	private static DatabaseConnector databaseConnector = new DatabaseConnector();

	/*
	 * Appointment Methods
	 */
	public static void deleteAppointment (int userID) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = databaseConnector.connect();// Assuming you have a method to get the database connection
			String query = "DELETE FROM datesbooked WHERE userid = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, userID);
			statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setUserAppointments(AppointmentManager appointmentManager){
		Connection connection = null;
		try {
			connection = databaseConnector.connect();
			Statement statement = connection.createStatement();
			StringBuilder query = new StringBuilder("INSERT INTO datesbooked (userid, dogid, posterid, date) VALUES ");
			ArrayList<Appointment> appList = appointmentManager.getUserAppointments();
			for(int i = 0; i < appList.size(); i++){
				if(i != 0 || i != appList.size()){
					query.append(",");
				}
				query.append( "( " + appList.get(i).getUserID() +", " + appList.get(i).getDogID() + "," + appList.get(i).getPosterID()+ "," + appList.get(i).getDate() + ")");
			}
			statement.addBatch(query.toString());
			statement.executeBatch();
		}
		catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static ArrayList<Appointment> getUserAppointments(int userID) {
		Connection connection = databaseConnector.connect();
		ArrayList<Appointment> appointments = new ArrayList<>();
		try {
		String query = "SELECT dogid,posterid,date FROM datesbooked WHERE userid = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
			// Set the userID parameter
			preparedStatement.setInt(1, userID);

			// Execute the query
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Iterate over the result set and populate the TreeMap
				while (resultSet.next()) {
					int dogID = resultSet.getInt("dogid");
					int posterID = resultSet.getInt("posterid");
					Date date = resultSet.getDate("date");
					appointments.add(new Appointment(posterID,dogID,date,userID));
				}
			}


		return appointments;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static boolean isDateExists(int  dogID,int userID, Connection connection) {

		PreparedStatement preparedStatement = null;
		String query = "SELECT COUNT(*) FROM datesbooked WHERE \"dogID\" = ? AND \"userID\" = ?";
		try {
			connection = databaseConnector.connect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, dogID);
			preparedStatement.setInt(2, userID);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					System.out.println("Date and Poster found successfully!");
					return count > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean addBookedDate(int posterID, int dogID, Date date, int userID) {

		Connection connection = databaseConnector.connect();
		PreparedStatement preparedStatement = null ;


		try {
			 String sql = "INSERT INTO datesbooked (\"posterID\", \"dogID\", \"date\", \"userID\") VALUES (?, ?, ?,?)";

			 connection = databaseConnector.connect();

			 preparedStatement = connection.prepareStatement(sql);
			 if (!(isDateExists(dogID, userID,connection)==true)) {
				 preparedStatement.setInt(1, posterID);
				 preparedStatement.setInt(2, dogID);
				 preparedStatement.setDate(3, date);
				 preparedStatement.setInt(4, userID );
				 int rowsAffected = preparedStatement.executeUpdate();
				 if (rowsAffected > 0) {
					//System.out.println("Date added successfully!");
					;
					return true;
				 }
				 else {
					//System.out.println("Failed to add Date.");
					return false;
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
		return false;
	}

	/*
	 * Dog Methods
	 */
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
	        		 ("SELECT * FROM dog WHERE " +
							 "dog.dogid NOT IN (SELECT userdogs.dogid FROM userdogs WHERE userdogs.userid = "+ user.getUserID() + " ) " +
							 "AND dog.dogid NOT IN (SELECT userpasseddogs.dogid FROM userpasseddogs WHERE userpasseddogs.userid = "+ user.getUserID() + ")" +
							 "AND dog.ageid IN (SELECT userattributepreferences.attributeid FROM userattributepreferences WHERE userattributepreferences.userid = " + user.getUserID() + " AND userattributepreferences.attributetype = 0) " +
							 "AND dog.sizeid IN (SELECT userattributepreferences.attributeid FROM userattributepreferences WHERE userattributepreferences.userid = " + user.getUserID() + " AND userattributepreferences.attributetype = 3) " +
							 "AND dog.sexid IN (SELECT userattributepreferences.attributeid FROM userattributepreferences WHERE userattributepreferences.userid = " + user.getUserID() + " AND userattributepreferences.attributetype = 1) " +
							 "AND dog.energylevelid IN (SELECT userattributepreferences.attributeid FROM userattributepreferences WHERE userattributepreferences.userid = " + user.getUserID() + " AND userattributepreferences.attributetype = 2);");
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

	public static ArrayList<Dog> getUsersLikedOrPassedDogs(int userID, String table){
		ArrayList<Dog> list = new ArrayList<>();
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

	public static void removeLikedDog(int dogID,int userID){

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = databaseConnector.connect();
			preparedStatement = connection.prepareStatement("DELETE FROM userdogs WHERE dogid = " + dogID + "AND userid  =" + userID + ";");
			preparedStatement.setInt(1, dogID);
			preparedStatement.setInt(2, userID);
			int rowsAffected = preparedStatement.executeUpdate();
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
	 * Poster Methods
	 */

	public static  Hashtable<Integer,Poster> getAllPosters() {
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
				double balance = resultSet.getDouble("balance");
				poster = new Poster(score, displayName, posterId, phone, email, balance);
				posters.put(posterId, poster);
			}
		}
		catch(SQLException e) {
			System.out.println ("Connection failure.") ;
			e.printStackTrace () ;
		}
		return posters;
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
	 * Tag Methods
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

	public static void addPreferenceTagsToUser(Hashtable<Integer,Tag> tags, int userId){
		/*
		 * Add tags to user preferences in database
		 */
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	 connection = databaseConnector.connect();
        	 for(Integer t : tags.keySet()) {
		    	 String sql = "INSERT INTO usertagpreferences (userid, tagid) VALUES (?, ?)";
		    	 preparedStatement = connection.prepareStatement(sql);
		    	 
		    	 preparedStatement.setInt(1, userId);
		    	 preparedStatement.setInt(2, t);
		    	 
		    	 int rowsAffected = preparedStatement.executeUpdate();
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

	public static void deletePreferenceTagsFromUser(int userId) {
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

	/*
	 * User Methods
	 */

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
        
		    for (Dog d : Database.getUsersLikedOrPassedDogs(user.getUserID(),"userdogs")) {
		    	user.addLikedDogs(d);
		    }
		    for (Dog d : Database.getUsersLikedOrPassedDogs(user.getUserID(),"userpasseddogs")) {
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
					 	switch(attType){
							case(0):
								 newList.add(new Age(resultSet.getInt("attributeid")));
							 break;
							case(1):
								newList.add(new Sex(resultSet.getInt("attributeid")));
								break;
							case(2):
								newList.add(new EnergyLevel(resultSet.getInt("attributeid")));
								break;
							case(3):
								newList.add(new Size(resultSet.getInt("attributeid")));
								break;
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
		ResultSet resultSet = statement.executeQuery ("SELECT tags.tagid, tags.tagname FROM tags JOIN usertagpreferences ON tags.tagid = usertagpreferences.tagid WHERE usertagpreferences.userid = " + userId + ";");
		
		while (resultSet.next()) {	 
			tags.put(resultSet.getInt("tagid"),new Tag(resultSet.getString("tagname")));
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	return tags;
}

	public static boolean addUser(String username, String password, HashMap<Integer,ArrayList<Attribute>> allAttributes) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
        try {
        	 connection = databaseConnector.connect();
        	 String sql = "INSERT INTO users (username, userpassword, balance) VALUES (?, ?, ?)";
        	 preparedStatement = connection.prepareStatement(sql);
        	 preparedStatement.setString(1, username);
        	 preparedStatement.setString(2, password);
        	 preparedStatement.setDouble(3, 0);
        	 int rowsAffected = preparedStatement.executeUpdate();
        	 if (rowsAffected > 0) {
                System.out.println("User added successfully!");
				sql = "SELECT userid FROM users WHERE username = " + username + " AND password = " + password + ";";
                int userid = connection.prepareStatement(sql).getResultSet().getInt("userid");
                String sql2 = "INSERT INTO userattributepreferences (userid, attributetype, attributeid) VALUES ";
                for(int type = 0; type < allAttributes.keySet().size(); type++) {
                	for(int weight = 0; weight < allAttributes.get(type).size(); weight++) {
                		sql2 += "(" + userid + "," + type + "," + weight +"),";
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
	 * Wallet Methods
	 */

	public static Wallet getWallet(int userid, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Wallet wallet = null;

		try {
			connection = databaseConnector.connect();

			//	        Statement statement = connection.createStatement ();
			//	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM users WHERE username = " + username + " AND userpassword  = " + password + ";") ;

			String sql = "SELECT * FROM users WHERE userid = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1,  userid);


			ResultSet resultSet = preparedStatement.executeQuery();
			//	public Wallet(double balance, boolean recurringPayment, int frequency, int userid,	Map<Integer, Double> posterWallets, int recurringAmount) {
			if (resultSet.next()) {
				//	public Wallet(double balance, boolean recurringPayment, int frequency, int userid,int recurringAmount, int posterToSponsorPending, int recurringPoster) {

				wallet = new Wallet(resultSet.getDouble("balance"),resultSet.getInt("userid"));
				
				//				for (Dog d : Database.getLikedDogs(user.getUserID())) {
				//					user.addLikedDogs(d);
				//				}
			}
			
			// get recurring payments
			
			String sql2 = "SELECT * FROM userpayments WHERE userid = " + userid;
			preparedStatement = connection.prepareStatement(sql2);
			
			while(resultSet.next()) {
				wallet.addRecurringPayment(
						new RecurringPayment(resultSet.getDouble("paymentamount"),
						resultSet.getInt("daysbetweenpayment"),
						resultSet.getInt("dogid"),
						resultSet.getInt("posterid"),
						resultSet.getString("lastpaymentdate")
						)
				);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();

		}

		return wallet; 	
	}
	
	public static void addRecurringPayments(User user, RecurringPayment p) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Wallet wallet = null;

		try {
			connection = databaseConnector.connect();
			
			
				String sql2 = "INSERT INTO userpayments (userid, paymentamount,daysbetweenpayment,dogid,lastpaymentdate,posterid) VALUES (?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.setInt(1, user.getUserID());
				preparedStatement.setDouble(2, p.getPaymentAmount());
				preparedStatement.setInt(3, p.getDaysBetweenPayments());
				preparedStatement.setInt(4, p.getDogId());
				preparedStatement.setString(5, p.getLastPaymentDateToString());
				preparedStatement.setInt(6, p.getPosterId());
				
				preparedStatement.executeUpdate();
//			}
			

		} 
		catch (SQLException e) {
			
			e.printStackTrace();

		}

	}
	
	public static void deleteRecurringPayments(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Wallet wallet = null;

		try {
			connection = databaseConnector.connect();

			//	        Statement statement = connection.createStatement ();
			//	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM users WHERE username = " + username + " AND userpassword  = " + password + ";") ;

			String sql = "DELETE FROM userpayments WHERE userid = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1,  user.getUserID());


			preparedStatement.executeUpdate();
			
			}

		catch (SQLException e) {
			
			e.printStackTrace();

		}

	}

	/*
	 * Cleanup Methods
	 */
	public static void onApplicationClose(User user, PriorityQueue<Dog> doglist, AppointmentManager appointmentManager){
//		Database.updateAllAdoptedDogs(doglist); // sets dogs to be adopted
		ArrayList<Dog> likedDogs = Database.getUsersLikedOrPassedDogs(user.getUserID(),"userdogs");
		for (Dog d : user.getLikedDogs()) {
			if(likedDogs.contains(d) == false)
				Database.addUserDog(d.getId(), user.getUserID(),"userdogs");
		}
		ArrayList<Dog> passedDogs = Database.getUsersLikedOrPassedDogs(user.getUserID(),"userpasseddogs");
		for (Dog d : user.getPassedDogs()) {
			if(passedDogs.contains(d) == false)
				Database.addUserDog(d.getId(), user.getUserID(),"userpasseddogs");
		}
		// TO DO: update user's attribute preferences and tag preferences :)
		int userId = user.getUserID();
		Database.deletePreferenceTagsFromUser(userId);
		Database.addPreferenceTagsToUser(user.getTagPreferences(), userId);
		Database.deleteAppointment(userId);
		Database.setUserAppointments(appointmentManager);
		Database.deleteUserAttributePreferences(userId);
		Database.addUserAttributePreferences(user.getAgePreferences(), userId);
		Database.addUserAttributePreferences(user.getSexPreferences(), userId);
		Database.addUserAttributePreferences(user.getEnergyLevelPreferences(), userId);
		Database.addUserAttributePreferences( user.getSizePreferences(), userId);
		Database.deleteRecurringPayments(user);

		for(RecurringPayment p : user.getWallet().getRecurringPayments().values()) {
			Database.addRecurringPayments(user, p);
		}
	}
}


class DatabaseConnector {
    public Connection connect() {
        try{
        	Class.forName("org.postgresql.Driver"); // Replace with your database driver
        	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/pawsome", "postgres", "321123");
			//System.out.println( "Connected to the PostgreSQL server successfully.");
        	return connection;
        }
		catch(ClassNotFoundException | SQLException e){
        	System.out.println("Connection failed");
            e.printStackTrace();
        } 
		return null;
    }
}