import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.tag.Tag;

class Integration {
	String url = "jdbc:postgresql://localhost:5432/paw3";
	String user1 = "postgres";
	String password = "doglover123";
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

	void test () {


		String sql = "INSERT INTO tags (preference, tagname) VALUES (?, ?)";

		// userid | username | userpassword |      email      | likeddogs

		try (Connection conn = DriverManager.getConnection(url, user1, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			Tag test = new Tag(0,"test1");

			pstmt.setInt(1, test.getWeight());

			pstmt.setString(2, test.getTagName());
			// pstmt.setString(3, "1234");
			//pstmt.setString(4, "ajisjd");
			// pstmt.setString(4, null);

			// pstmt.setInt(2, user.getAge());
			//pstmt.setString(3, user.getBreed());
			// ID of the employee you want to retrieve
			String tagval= "test1";
			// SQL query
			sql = "SELECT * FROM employees WHERE TagName = ?";


			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Check if a result is found
				if (resultSet.next()) {
					// Retrieve data from the result set
					int id = resultSet.getInt("preference");
					String name = resultSet.getString("tagName");

					// Create tag object with the retrieved data
					Tag retrieveTag = new Tag(id,name);

					assertEquals(test, retrieveTag, "tag not found with name: \" + targetEmployeeId");
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
//	uploadDog(users);
}


}