package backend.database;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.LocalDate;
import java.time.ZoneId;

import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.dog.Dog;
import backend.dog.trait.*;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;
import backend.wallet.RecurringPayment;
import backend.wallet.Wallet;
import guicontrol.AppData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.util.List.copyOf;

/*
 * Public class to centralize all communications to and from database
 */
public class Database {

	
	public static Connection connect() {
		try {
			Class.forName("org.postgresql.Driver"); // Replace with your database driver
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres", "1234"); // zainab

			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finaldb2", "postgres", "123"); // connor (sorry katya)
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/posterscoreupdate", "postgres", "12345"); // connor (sorry katya)

//			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/thebestoneyet", "postgres", "321123"); // isaiah
			//System.out.println( "Connected to the PostgreSQL server successfully.");
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Appointment Methods
	 */
	public static void deleteAppointment(int userID) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = Database.connect();// Assuming you have a method to get the database connection
			String query = "DELETE FROM datesbooked WHERE userid = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, userID);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteAppointmentForDog(Dog d) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = Database.connect();// Assuming you have a method to get the database connection
			String query = "DELETE FROM datesbooked WHERE dogid = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, d.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setUserAppointments(AppointmentManager appointmentManager) {
		if (appointmentManager.getUserAppointments().size() > 0) {
			Connection connection = null;
			try {
				connection = Database.connect();
				Statement statement = connection.createStatement();
				StringBuilder query = new StringBuilder("INSERT INTO datesbooked (userid, dogid, posterid, date) VALUES ");
				ArrayList<Appointment> appList = appointmentManager.getUserAppointments();
				for (int i = 0; i < appList.size(); i++) {
					if (i != 0) {
						query.append(",");
					}
					query.append("( " + appList.get(i).getUserID() + ", " + appList.get(i).getDogID() + "," + appList.get(i).getPosterID() + ", '" + appList.get(i).getDate() + "' )");
				}
				query.append(";");
				statement.addBatch(query.toString());
				statement.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<Appointment> getUserAppointments(int userID) {
		Connection connection = Database.connect();
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
					appointments.add(new Appointment(posterID, dogID, date, userID));

				}
			}


			return appointments;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static boolean isDateExists(int dogID, int userID, Connection connection) {

		PreparedStatement preparedStatement = null;
		String query = "SELECT COUNT(*) FROM datesbooked WHERE \"dogID\" = ? AND \"userID\" = ?";
		try {
			connection = Database.connect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, dogID);
			preparedStatement.setInt(2, userID);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<Appointment> getOtherUserAppointments(int userId) {
		Connection connection = Database.connect();
		ArrayList<Appointment> appointments = new ArrayList<>();

		try {
			String query = "SELECT dogid,posterid,date FROM datesbooked WHERE NOT userid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// Set the userID parameter
			preparedStatement.setInt(1, userId);

			// Execute the query
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Iterate over the result set and populate the TreeMap
				while (resultSet.next()) {
					int dogID = resultSet.getInt("dogid");
					int posterID = resultSet.getInt("posterid");
					Date date = resultSet.getDate("date");
					appointments.add(new Appointment(posterID, dogID, date, userId));
				}
			}


			return appointments;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Dog Methods
	 */
	public static void setDogAdopted(Dog d) {
		if (d.getAdopted() == true) {
			try {
				Connection connection = Database.connect();
				Statement statement = connection.createStatement();
				statement.executeUpdate("UPDATE dog SET adopted = TRUE WHERE dogid = " + d.getId());
				connection.close();
			} catch (SQLException e) {
				System.out.println("Connection failure.");
				e.printStackTrace();
			}
		}
	}

	public static Hashtable<Integer, Tag> getDogTags(int dogId, Statement statement) {
		Hashtable<Integer, Tag> tags = new Hashtable<Integer, Tag>();
		// get all tags in dogtag data table associated with the dog id
		try {
//			Statement statement = connection.createStatement() ;
			ResultSet resultSet = statement.executeQuery("SELECT tags.tagname, tags.tagid FROM tags JOIN dogtag ON tags.tagid = dogtag.tagid WHERE dogtag.dogid = " + dogId + ";");

			while (resultSet.next()) {
				tags.put(resultSet.getInt("tagid"), new Tag(resultSet.getString("tagname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tags;
	}

	public static Hashtable<Integer, ArrayList<Dog>> getAllDogs(User user, Set<Integer> posterIds) {


		Hashtable<Integer, ArrayList<Dog>> dogProfiles = new Hashtable<Integer, ArrayList<Dog>>();
		for (Integer id : posterIds) {
			ArrayList<Dog> queue = new ArrayList<Dog>();
			dogProfiles.put(id, queue);// populate the outer hashtable with poster id's
		}
		Connection connection = Database.connect();
		try {
			Statement statement = connection.createStatement();
			Statement statement2 = connection.createStatement();
//		         ResultSet resultSet = statement.executeQuery ("SELECT * FROM dog WHERE dog.dogid NOT IN (SELECT userdogs.dogid FROM userdogs WHERE userdogs.userid = "+ user.getUserID() + " ) AND adopted = false;");
			ResultSet resultSet = statement.executeQuery
					("SELECT * FROM dog WHERE dog.adopted =  'FALSE' AND " +
							"dog.dogid NOT IN (SELECT userdogs.dogid FROM userdogs WHERE userdogs.userid = " + user.getUserID() + " ) " +
							"AND dog.dogid NOT IN (SELECT userpasseddogs.dogid FROM userpasseddogs WHERE userpasseddogs.userid = " + user.getUserID() + ")" +
							"AND dog.ageid IN (SELECT userattributepreferences.attributeid FROM userattributepreferences WHERE userattributepreferences.userid = " + user.getUserID() + " AND userattributepreferences.attributetype = 0) " +
							"AND dog.sizeid IN (SELECT userattributepreferences.attributeid FROM userattributepreferences WHERE userattributepreferences.userid = " + user.getUserID() + " AND userattributepreferences.attributetype = 3) " +
							"AND dog.sexid IN (SELECT userattributepreferences.attributeid FROM userattributepreferences WHERE userattributepreferences.userid = " + user.getUserID() + " AND userattributepreferences.attributetype = 1) " +
							"AND dog.energylevelid IN (SELECT userattributepreferences.attributeid FROM userattributepreferences WHERE userattributepreferences.userid = " + user.getUserID() + " AND userattributepreferences.attributetype = 2)"
									);
			while (resultSet.next()) {
				// only add a dog if adoption = false and its id is not negative (if negative, its a dummy dog)

				String dogName = resultSet.getString("dogname");
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

			connection.close();

		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return dogProfiles;
	}
	
	public static Hashtable<Integer, ArrayList<Dog>> getAllDogsNoPreferences(User user, Set<Integer> posterIds) {


		Hashtable<Integer, ArrayList<Dog>> dogProfiles = new Hashtable<Integer, ArrayList<Dog>>();
		for (Integer id : posterIds) {
			ArrayList<Dog> queue = new ArrayList<Dog>();
			dogProfiles.put(id, queue);// populate the outer hashtable with poster id's
		}
		Connection connection = Database.connect();
		try {
			Statement statement = connection.createStatement();
			Statement statement2 = connection.createStatement();
//		         ResultSet resultSet = statement.executeQuery ("SELECT * FROM dog WHERE dog.dogid NOT IN (SELECT userdogs.dogid FROM userdogs WHERE userdogs.userid = "+ user.getUserID() + " ) AND adopted = false;");
			ResultSet resultSet = statement.executeQuery
					("SELECT * FROM dog");
			while (resultSet.next()) {
				// only add a dog if adoption = false and its id is not negative (if negative, its a dummy dog)

				String dogName = resultSet.getString("dogname");
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

			connection.close();

		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return dogProfiles;
	}

	public static void updateAllAdoptedDogs(ArrayList<Dog> doglist) {
		for (Dog d : doglist) {
			if (d.getAdopted() == true) {
				try {
					Connection connection = Database.connect();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("UPDATE dog SET adopted = TRUE WHERE dogid = " + d.getId());
					connection.close();
				} catch (SQLException e) {
					System.out.println("Connection failure.");
					e.printStackTrace();
				}

			}
		}


	}

	public static ArrayList<Dog> getUsersLikedOrPassedDogs(int userID, String table) {
		ArrayList<Dog> list = new ArrayList<>();
		try {
			Connection connection = Database.connect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM dog JOIN " + table + " ON dog.dogid = " + table + ".dogid WHERE " + table + ".userid = " + userID + ";");
			while (resultSet.next()) {
				String dogName = resultSet.getString("dogname");
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
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return list;
	}

	public static void removeLikedDog(int dogID, int userID) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = Database.connect();
			preparedStatement = connection.prepareStatement("DELETE FROM userdogs WHERE dogid = " + dogID + "AND userid  =" + userID + ";");
			preparedStatement.setInt(1, dogID);
			preparedStatement.setInt(2, userID);
			int rowsAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}


	/*
	 * Poster Methods
	 */

	public static Hashtable<Integer, Poster> getAllPosters() {
		Poster poster = null;
		Hashtable<Integer, Poster> posters = new Hashtable<Integer, Poster>();
		Connection connection = Database.connect();
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
				int numberofratings = resultSet.getInt("numberofratings");
				poster = new Poster(score, displayName, posterId, phone, email, balance);
				poster.setNumberofRatings(numberofratings);
				posters.put(posterId, poster);
			}
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return posters;
	}

	public static ArrayList<Dog> getPosterDogs(int posterId) {

		ArrayList<Dog> dogProfiles = new ArrayList<>();

		try {
			Connection connection = Database.connect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM dog WHERE dog.posterid = " + posterId + ";");
			while (resultSet.next()) {
				// only add a dog if adoption = false and its id is not negative (if negative, its a dummy dog)
				String dogName = resultSet.getString("dogname");
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
			connection.close();

		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return dogProfiles;
	}

	public static void addUserDog(ArrayList<Dog> dogList, int userID, String table) {
		if (dogList.size() > 0) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = Database.connect();
				Statement statement = connection.createStatement();
				StringBuilder query = new StringBuilder("INSERT INTO " + table + " (dogid, userid) VALUES ");
				for (int i = 0; i < dogList.size(); i++) {
					if (i != 0) {
						query.append(", ");
					}
					query.append("( " + dogList.get(i).getId() + ", " + userID + ")");
				}

				query.append(" ON CONFLICT (userid,dogid) DO NOTHING;");

				statement.addBatch(query.toString());
				statement.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Tag Methods
	 */

	public static HashMap<Integer, Tag> getAllTags() {

		HashMap<Integer, Tag> tags = new HashMap<Integer, Tag>();

		try {
			Connection connection = Database.connect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM tags");
			while (resultSet.next()) {
				tags.put(resultSet.getInt("tagid"), new Tag(resultSet.getString("tagname"), resultSet.getInt("tagid")));

			}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return tags;
	}

	/*public static void addPreferenceTagsToUser(Hashtable<Integer,Tag> tags, int userId){
		
		if(tags.values().size() > 0) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
	        try {
	        	 connection = Database.connect();
				 Statement statement = connection.createStatement();
				 Collection<Tag> tagList = tags.values();
				 Iterator<Tag> iterator = tagList.iterator();
				 ArrayList<Tag> tagsList = new ArrayList<>();
				 while(iterator.hasNext()){
					 tagsList.add(iterator.next());
				 }
				StringBuilder query = new StringBuilder("INSERT INTO usertagpreferences (userid, tagid) VALUES ");
	        	 for(int i = 0; i < tagsList.size();i++) {
					 if (i != 0){
						 query.append(", ");
					 }
					 query.append("( " + userId + ", " + tagsList.get(i).getTagId() +" )");
			    }
	
				 query.append(";");
				 
				 statement.addBatch(query.toString());
				 statement.executeBatch();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	          
	        }
	}}*/

	public static void addPreferenceTagsToUser(Hashtable<Integer, Tag> tags, int userId) {
		/*
		 * Add tags to user preferences in the database
		 */
		if (tags.values().size() > 0) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try {
				connection = Database.connect();
				preparedStatement = connection.prepareStatement("INSERT INTO usertagpreferences (userid, tagid) VALUES (?, ?)");

				// Set auto-commit to false for batch processing
				connection.setAutoCommit(false);

				for (Tag tag : tags.values()) {
					// Check if the tagid exists in the tags table
					if (isTagExists(connection, tag.getTagId())) {
						preparedStatement.setInt(1, userId);
						preparedStatement.setInt(2, tag.getTagId());
						preparedStatement.addBatch();
					}
				}

				// Execute the batch
				preparedStatement.executeBatch();

				// Commit the transaction
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					if (connection != null) {
						connection.rollback(); // Rollback the transaction in case of an error
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} finally {
				// Close resources
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.setAutoCommit(true); // Reset auto-commit to true
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static boolean isTagExists(Connection connection, int tagId) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean exists = false;

		try {
			preparedStatement = connection.prepareStatement("SELECT 1 FROM tags WHERE tagid = ?");
			preparedStatement.setInt(1, tagId);
			resultSet = preparedStatement.executeQuery();
			exists = resultSet.next(); // Check if the result set contains any rows
		} finally {
			// Close resources
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}

		return exists;
	}

	public static void deletePreferenceTagsFromUser(int userId) {
		try {
			Connection connection = Database.connect();
			Statement statement = connection.createStatement();
			PreparedStatement preppedStatement = connection.prepareStatement("DELETE FROM usertagpreferences WHERE userid = " + userId + ";");
			preppedStatement.execute();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}

	public static int getTagID(String tagname) {
		int tagid = 0;
		try {
			Connection connection = Database.connect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT tagid FROM tags WHERE tagname = '" + tagname + "'");
			while (resultSet.next()) {
				tagid = resultSet.getInt("tagid");
			}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}

		return tagid;


	}

	/*
	 * User Methods
	 */

	public static String usernameChecker(String username){
		Connection connection = null;
		String result = "";
		try{
			connection = Database.connect();
			Statement statement = connection.createStatement();
			String query = "SELECT username FROM users WHERE username = '" + username + "' ;";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				result = resultSet.getString("username");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String passwordChecker(String username, String password){
		Connection connection = null;
		String result = "";
		try{
			connection = Database.connect();
			Statement statement = connection.createStatement();
			String query = "SELECT username FROM users WHERE username = '" + username + "' AND userpassword = '" + password + "' ;";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				result = resultSet.getString("username");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static User getUser(String username, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		User user = null;

		try {
			connection = Database.connect();

			String sql = "SELECT * FROM users WHERE username = ? AND userpassword = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);


			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = new User(resultSet.getString("username"), resultSet.getString("userpassword"));
				user.setUserID(resultSet.getInt("userid"));
				user.setEmail(resultSet.getString("email"));

				for (Dog d : Database.getUsersLikedOrPassedDogs(user.getUserID(), "userdogs")) {
					user.addLikedDogs(d);
				}
				for (Dog d : Database.getUsersLikedOrPassedDogs(user.getUserID(), "userpasseddogs")) {
					user.addPassedDogs(d);
				}
			}
			int userId = user.getUserID();

			user.setAgePreferences(getUsersPreferredAttributes(userId, 0));
			user.setSexPreferences(getUsersPreferredAttributes(userId, 1));
			user.setSizePreferences(getUsersPreferredAttributes(userId, 3));
			user.setEnergyLevelPreferences(getUsersPreferredAttributes(userId, 2));

			user.setTagPreferences(getUsersPreferredTags(userId));


		} catch (SQLException e) {
			e.printStackTrace();

		}

		return user;
	}

	public static ArrayList<Attribute> getUsersPreferredAttributes(int userid, int attType) {

		ArrayList<Attribute> newList = new ArrayList<Attribute>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = Database.connect();
			String sql = "SELECT * FROM userattributepreferences WHERE userid = " + userid + " AND attributetype = " + attType + ";";
			preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				switch (attType) {
					case (0):
						newList.add(new Age(resultSet.getInt("attributeid")));
						break;
					case (1):
						newList.add(new Sex(resultSet.getInt("attributeid")));
						break;
					case (2):
						newList.add(new EnergyLevel(resultSet.getInt("attributeid")));
						break;
					case (3):
						newList.add(new Size(resultSet.getInt("attributeid")));
						break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return newList;

	}

	public static Hashtable<Integer, Tag> getUsersPreferredTags(int userId) {
		Hashtable<Integer, Tag> tags = new Hashtable<Integer, Tag>();
		Connection connection = null;
		// get all tags in dogtag data table associated with the dog id
//	
		try {
			connection = Database.connect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT tags.tagid, tags.tagname FROM tags JOIN usertagpreferences ON tags.tagid = usertagpreferences.tagid WHERE usertagpreferences.userid = " + userId + ";");

			while (resultSet.next()) {
				tags.put(resultSet.getInt("tagid"), new Tag(resultSet.getString("tagname"), resultSet.getInt("tagid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tags;
	}
public static boolean updateUsernamePassword (String newUsername, String newPassword, int userid) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	PreparedStatement preparedStatement2 = null;
	try {
		connection = Database.connect();
		String sql = "UPDATE users SET username=?, userpassword=?, userid=? WHERE userid = "+userid+"; ";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, newUsername);
		preparedStatement.setString(2, newPassword);
		preparedStatement.setInt(3, userid);
		int rowsAffected = preparedStatement.executeUpdate();

		if (rowsAffected > 0) {
			System.out.println("User updated successfully!");
			return true;

		}
		else {
			System.out.println("Failed to update User.");
			return false;
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

	

	public static boolean addUser(String username, String password, HashMap<Integer, ArrayList<Attribute>> allAttributes) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		try {
			connection = Database.connect();
			String sql = "INSERT INTO users (username, userpassword, balance) VALUES (?, ?, ?) ON CONFLICT (username) DO NOTHING; ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setDouble(3, 0);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User added successfully!");
				sql = "SELECT userid FROM users WHERE username = '" + username + "' AND userpassword = '" + password + "';";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				if (result.next()) {
					int userid = result.getInt("userid");
					String sql2 = "INSERT INTO userattributepreferences (userid, attributetype, attributeid) VALUES ";
					for (int type = 0; type < allAttributes.keySet().size(); type++) {
						for (int weight = 0; weight < allAttributes.get(type).size(); weight++) {
							sql2 += "(" + userid + "," + type + "," + weight + "),";
						}
					}


					sql2 = sql2.substring(0, sql2.length() - 1);
					preparedStatement2 = connection.prepareStatement(sql2);
					preparedStatement2.executeUpdate();
					return true;
				}
			} else {
				System.out.println("Failed to add User.");
				return false;
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

	public static void deleteUserAttributePreferences(int userId) {
		try {
			Connection connection = Database.connect();
			Statement statement = connection.createStatement();
			PreparedStatement preppedStatement = connection.prepareStatement("DELETE FROM userattributepreferences WHERE userid = " + userId + ";");
			preppedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}

	public static void addUserAttributePreferences(ArrayList<Attribute> atts, int userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = Database.connect();
			Statement statement = connection.createStatement();
			StringBuilder query = new StringBuilder("INSERT INTO userattributepreferences (userid, attributetype, attributeid) VALUES ");
			for (int i = 0; i < atts.size(); i++) {
				if (i != 0)
					query.append(", ");
				query.append("(" + userId + "," + atts.get(i).getType() + "," + atts.get(i).getWeight() + ")");
			}
			query.append(";");
			statement.addBatch(query.toString());
			statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
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
			connection = Database.connect();

			//	        Statement statement = connection.createStatement ();
			//	        ResultSet resultSet = statement.executeQuery ("SELECT * FROM users WHERE username = " + username + " AND userpassword  = " + password + ";") ;

			String sql = "SELECT * FROM users WHERE userid = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, userid);


			ResultSet resultSet = preparedStatement.executeQuery();
			//	public Wallet(double balance, boolean recurringPayment, int frequency, int userid,	Map<Integer, Double> posterWallets, int recurringAmount) {
			if (resultSet.next()) {
				//	public Wallet(double balance, boolean recurringPayment, int frequency, int userid,int recurringAmount, int posterToSponsorPending, int recurringPoster) {

				wallet = new Wallet(resultSet.getDouble("balance"), resultSet.getInt("userid"));
			}

			// get recurring payments

			String sql2 = "SELECT * FROM userpayments WHERE userid = " + userid;
			preparedStatement = connection.prepareStatement(sql2);

			while (resultSet.next()) {
				wallet.addRecurringPayment(
						new RecurringPayment(resultSet.getDouble("paymentamount"),
								resultSet.getInt("daysbetweenpayment"),
								resultSet.getInt("dogid"),
								resultSet.getInt("posterid"),
								resultSet.getString("lastpaymentdate")
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return wallet;
	}

	public static void updateWallet(User user) {
		Connection connection = null;

		try {
			connection = Database.connect();
			Statement statement = connection.createStatement();
			String query = "UPDATE users SET balance = " + user.getWallet().getBalance() + " WHERE userid = " + user.getUserID() + ";";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}


	public static void addRecurringPayments(User user, HashMap<Integer, RecurringPayment> p) {
		if (p.size() > 0) {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try {
				connection = Database.connect();
				Statement statement = connection.createStatement();
				Collection<RecurringPayment> recurringPayments = p.values();
				ArrayList<RecurringPayment> recurringPaymentsList = new ArrayList<>();
				Iterator<RecurringPayment> iterator = recurringPayments.iterator();
				while (iterator.hasNext()) {
					recurringPaymentsList.add(iterator.next());
				}
				StringBuilder query = new StringBuilder("INSERT INTO userpayments (userid, paymentamount, daysbetweenpayment, dogid, lastpaymentdate, posterid) VALUES ");
				for (int i = 0; i < recurringPaymentsList.size(); i++) {
					if (i != 0) {
						query.append(", ");
					}
					query.append("(" + user.getUserID() + ", " + recurringPaymentsList.get(i).getPaymentAmount() + ", " + recurringPaymentsList.get(i).getDaysBetweenPayments() + ", " + recurringPaymentsList.get(i).getDogId() + ", '" + recurringPaymentsList.get(i).getLastPaymentDateToString() + "', " + recurringPaymentsList.get(i).getPosterId() + ") ");
				}
				query.append(";");

				statement.addBatch(query.toString());
				statement.executeBatch();
			} catch (SQLException e) {
				// Handle SQLException
			}
		}
	}

	public static void deleteRecurringPayments(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Wallet wallet = null;

		try {
			connection = Database.connect();

			String sql = "DELETE FROM userpayments WHERE userid = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, user.getUserID());


			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}
	
	public static void deleteRecurringPaymentsForDog(Dog dog) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Wallet wallet = null;

		try {
			connection = Database.connect();

			String sql = "DELETE FROM userpayments WHERE dogid = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, dog.getId());


			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public static HashMap<Integer, RecurringPayment> getRecurringPayment(int userid){
		Connection connection = null;
		HashMap<Integer,RecurringPayment> map = new HashMap<>();
		try {
			connection = Database.connect();
			String query = "SELECT * FROM userpayments WHERE userid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// Set the userID parameter
			preparedStatement.setInt(1, userid);

			// Execute the query
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Iterate over the result set and populate the TreeMap
				while (resultSet.next()) {
					RecurringPayment payment = new RecurringPayment(resultSet.getDouble("paymentamount"),resultSet.getInt("daysbetweenpayment"),resultSet.getInt("dogid"),resultSet.getInt("posterid"),resultSet.getString("lastpaymentdate"));
					map.put(resultSet.getInt("dogid"),payment);
				}
			}


			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null;
	}
	
	// delete posters rated (DB)
	public static void deletePostersRatedByUser(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Wallet wallet = null;

		try {
			connection = Database.connect();

			String sql = "DELETE FROM userpostersrated WHERE userid = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, user.getUserID());


			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}
	
	
	// Add posters rated (local to DB)
		public static void addPosterRatedByUser(int userId, int posterId) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;

		    try {
		        connection = Database.connect();
		        
		        // Insert the record if it doesn't already exist
		        String insertQuery = "INSERT INTO userpostersrated (userid, posterid) "
		                           + "SELECT ?, ? "
		                           + "WHERE NOT EXISTS (SELECT 1 FROM userpostersrated WHERE userid = ? AND posterid = ?)";
		        preparedStatement = connection.prepareStatement(insertQuery);
		        preparedStatement.setInt(1, userId);
		        preparedStatement.setInt(2, posterId);
		        preparedStatement.setInt(3, userId);
		        preparedStatement.setInt(4, posterId);
		        preparedStatement.executeUpdate();
		    } catch (SQLException e) {
		        System.out.println("Error adding poster rated by user.");
		        e.printStackTrace();
		    } finally {
		        // Close resources in the finally block
		        if (preparedStatement != null) {
		            try {
		                preparedStatement.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (connection != null) {
		            try {
		                connection.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}

	
	// upload posters rated by user (DB to local)
	public static void setPostersRatedByUser(User user) {
		Connection connection = null;
		HashMap<Integer,RecurringPayment> map = new HashMap<>();
		try {
			connection = Database.connect();
			String query = "SELECT * FROM userpostersrated WHERE userid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// Set the userID parameter
			preparedStatement.setInt(1, user.getUserID());

			// Execute the query
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Iterate over the result set and populate the TreeMap
				while (resultSet.next()) {
					user.addToPostersRatedByUser(resultSet.getInt("posterid"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	// update posters score (local to DB)
	public static void updatePosterScore(int posterId, double newScore, int newNumberOfRatings) {
	    try {
	        Connection connection = Database.connect();
	        Statement statement = connection.createStatement();
	        statement.executeUpdate("UPDATE poster SET score = " + newScore + " WHERE poster_id = " + posterId + ";");
	        statement.executeUpdate("UPDATE poster SET numberofratings = " + newNumberOfRatings + " WHERE poster_id = " + posterId + ";");
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Connection failure.");
	        e.printStackTrace();
	    }
	}
	
	public static ArrayList <Integer> getPosterRatedbyUser(int userid){
		ArrayList<Integer> ratedposterlist = new ArrayList<>();

	    try {
	        Connection connection = Database.connect();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT posterid FROM userpostersrated WHERE userid = " + userid);

	        while (resultSet.next()) {
	            int posterId = resultSet.getInt("posterid");
	            ratedposterlist.add(posterId);
	        }

	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Error retrieving posters rated by user.");
	        e.printStackTrace();
	    }
	    return ratedposterlist;
		
	}
	public static ArrayList<String> getallUserNames() {
		ArrayList<String> usernames = new ArrayList<>();

	    try {
	        Connection connection = Database.connect();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT username FROM users;");

	        while (resultSet.next()) {
	            String name = resultSet.getString("username");
	            usernames.add(name);
	        }

	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Error retrieving usernames.");
	        e.printStackTrace();
	    }
	    return usernames;
	}

	/*
	 * Cleanup Methods
	 */
	

	public static void onApplicationClose(User user, PriorityQueue<Dog> doglist, AppointmentManager appointmentManager, Boolean okToClose) {
		if(okToClose==true) {
//			Database.updateAllAdoptedDogs(doglist); // sets dogs to be adopted
			Database.addUserDog(user.getLikedDogs(), user.getUserID(), "userdogs");
			Database.addUserDog(user.getPassedDogs(), user.getUserID(), "userpasseddogs");

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
			Database.addUserAttributePreferences(user.getSizePreferences(), userId);
			Database.deleteRecurringPayments(user);
			Database.addRecurringPayments(user, user.getWallet().getRecurringPayments());
			Database.updateWallet(user);
			ArrayList<Dog> dogListUser = user.getLikedDogs();
		  for (Dog d : dogListUser) {
			if (d.getAdopted()==true) {
				Database.setDogAdopted(d);
				Database.deleteAppointmentForDog(d);
				Database.deleteRecurringPaymentsForDog(d);
				
			}
		}
		  ArrayList<Integer> ratedPosters = user.getPostersRatedByUser();
		  Hashtable<Integer, Poster> posterlist=AppData.getInstance().getPosters();
		  for (Dog d : dogListUser) {
			  if (ratedPosters.contains(d.getPosterId())) {
				  Poster poster = posterlist.get(d.getPosterId());
				  Database.updatePosterScore(d.getPosterId(), poster.getScore(), poster.getNumberofRatings());
				  Database.addPosterRatedByUser(user.getUserID(),d.getPosterId());
			  }
		  }
			
			  
		  



	}
}
}