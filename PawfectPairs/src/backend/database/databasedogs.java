package backend.database;

import java.sql.*;

public class databasedogs {
	public static void main ( String [] args ) {
		 String dbName = "pawfectpairsdb" ;
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
		 }
	}

}
