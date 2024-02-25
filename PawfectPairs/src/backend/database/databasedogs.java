package backend.database;

import java.sql.*;
import backend.user.*;

public class databasedogs {
	//public static void main ( String [] args ) {
		/* String dbName = "pawfectpairsdb" ;
		 String dbUser = "postgres" ;
		 String userPassword = "12345" ;
		 String url = "jdbc:postgresql://localhost:5432/" + dbName ;

		 try {
			 Connection connection = DriverManager.getConnection (url, dbUser, userPassword) ;
			 System.out.println ("Connected to the PostgreSQL server successfully.") ;
			 Statement statement = connection.createStatement () ;
			 ResultSet resultSet = statement.executeQuery ("SELECT * FROM CUSTOMERS LIMIT 10") ;

			 // Print result set in table format
			 System.out.println ("Customer Info:") ;
			 System.out.println ("ID | Name | Contact Name | Address |  City  | Postal Code | Country") ;
			 while (resultSet.next ()) {
				 	System.out.println (resultSet.getInt ("customerid") + " | " +
				 	resultSet.getString ("customername") + " | " + resultSet.getString ("contactname") + " | " + resultSet.getString ("address") 
				 	+ " | " + resultSet.getString ("city") + " | " + resultSet.getString ("postalcode") + " | " + resultSet.getString ("country") ) ;
			 }
			 connection.close () ;

		 } catch (SQLException e) {
		 			System.out.println ("Connection failure.") ;
		 			e.printStackTrace () ;
		 }*/
		
		

		    // Method to upload a Dog object to the database
		    public static void uploadDog(User  user) {
		        String url = "jdbc:postgresql://localhost:5432/pawdb";
		        String user1 = "postgres";
		        String password = "12345";

		        String sql = "INSERT INTO tags (preference, tagname) VALUES (?, ?)";
		        
		        // userid | username | userpassword |      email      | likeddogs

		        try (Connection conn = DriverManager.getConnection(url, user1, password);
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

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

		    public static void main(String[] args) {
		        // Create a Dog object
		        User users = new User("userFromJava", "1234");

		        // Upload the Dog object to the database
		        uploadDog(users);
		    }
		

		
	}




