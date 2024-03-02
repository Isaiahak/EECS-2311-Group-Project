package backend.calendar;

import java.sql.*;
import backend.database.*;
import backend.tag.Tag;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.TreeMap;
public class MainTester {
    private static DatabaseConnector databaseConnector = new DatabaseConnector(); 

    public static void main(String[] args) {
    	Calendar calendar = Calendar.getInstance();
        
        // Set the year, month, and day
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.MARCH); // Note: Months are zero-based in Calendar class
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        //call functions based on dog.getPoster()
     // Convert Calendar to java.util.Date
        java.util.Date utilDate = calendar.getTime();

        // Convert java.util.Date to java.sql.Date
        Date sqlDate = new Date(utilDate.getTime());
        
        //addBookedDate(1,3,sqlDate,4);
        //isDateExists(sqlDate);
        Connection connection = databaseConnector.connect();
        getUserAppointments (4,connection );
        
    }

    public static TreeMap<Integer, Date> getUserAppointments(int userID,Connection connection) {
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
    public static boolean isDateExists(Date date, int  posterID,Connection connection) {
    	
        PreparedStatement preparedStatement = null;
        String query = "SELECT COUNT(*) FROM datesbooked WHERE date = ? AND \"posterID\" = ?";
        try {
        	connection = databaseConnector.connect();
        	preparedStatement = connection.prepareStatement(query);
        	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setInt(2, posterID);

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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = databaseConnector.connect();
            String sql = "INSERT INTO datesbooked (\"posterID\", \"dogID\", \"date\", \"userID\") VALUES (?, ?, ?, ?)";
            
            if (!(isDateExists(date, posterID, connection)==true)) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, posterID);
            preparedStatement.setInt(2, dogID);
            preparedStatement.setDate(3, date);
            preparedStatement.setInt (4, userID);
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
}
