import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.database.Database;
import backend.user.User;
import guicontrol.AppData;
import guicontrol.Authenticator;
import guicontrol.ChangeProfileDetails;

import guilayout.UserProfile;
import guilayout.Components;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserChangeTest {
Authenticator authenticator;
AppData appdata;
//@Mock
//private Label mockLabel;
private Label label;
private User user;
private static String[] oldPassword;
private static Boolean[] existedInDbBefore;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		AppData.getInstance();
		
		 oldPassword = new String[2];
	        existedInDbBefore = new Boolean[2];

	        // Set up test users
	        setUpUser("1");
	        setUpUser("2");
//		user = new User("1", "1");
//		if (!Database.usernameChecker("1").equals("1"))
//			{Database.addUser(user.getUsername(), user.getPassword(), appdata.getAllAttributes());
//			existedInDbBefore[0]=false;
//			}
//			else { 
//			oldPassword[0]=Database.getPassword("1");
//			existedInDbBefore[0]=true;
//			Database.updateUsernamePassword("1", "1",Database.getUserid("1"));
//		}
//		
//		user2 = new User("2", "2");
//		if (!Database.usernameChecker("2").equals("2"))
//			{Database.addUser(user2.getUsername(), user2.getPassword(), appdata.getAllAttributes());
//			existedInDbBefore[1]=false;
//			}
//			else { 
//			oldPassword[1]=Database.getPassword("2");
//			existedInDbBefore[1]=true;
//			Database.updateUsernamePassword("2", "2",Database.getUserid("2"));
//		}

	}
	   private static void setUpUser(String username) {
	        if (!Database.usernameChecker(username).equals(username)) {
	            Database.addUser(username, username, null);
	            existedInDbBefore[Integer.parseInt(username) - 1] = false;
	        } else {
	            //oldPassword[Integer.parseInt(username) - 1] = Database.getPassword(username);
	            existedInDbBefore[Integer.parseInt(username) - 1] = true;
	            Database.updateUsernamePassword(username, username, Database.getUserid(username));
	        }
	    }
	   @AfterAll
	    static void tearDownAfterClass() throws Exception {
	        // Reset users' passwords if they existed before
		   
	        for (int i = 0; i < 2; i++) {
	            if (existedInDbBefore[i]) {
	                Database.updateUsernamePassword(String.valueOf(i + 1), oldPassword[i], Database.getUserid(""+i));
	            }
	        }
	    }
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//		if (existedInDbBefore[0])
//		{
//			Database.updateUsernamePassword("1", oldPassword[0]);
//	
//		}
//		
//		if (existedInDbBefore[1])
//		{
//			Database.updateUsernamePassword("2", oldPassword[1]);
//	
//		}
//		
//			
//	}

	@BeforeEach
	void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
		
        user=new User("1", "1");

	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testChangeToOtherUserFalse() {
		String inputUsername="1"; 
		String inputPasswordString="q3";
		//assertNull(user);
		boolean check =ChangeProfileDetails.otherUsersUsername(inputUsername, user);
		
		assertFalse(check);
	}

	@Test
	void testChangeToOtherUserTrue() {
		
		String inputUsername="1"; 
		String inputPasswordString="q3";
		boolean check =ChangeProfileDetails.otherUsersUsername(inputUsername, user);
		
		assertFalse(check);
	}
	
//	@Test
//	void testloginFalse() {
//		String inputUsername="1"; 
//		String inputPasswordString="q3";
//		boolean check =Authenticator.authenticateLogIn(inputUsername, inputPasswordString)&&inputUsername.equals(user.getUsername());
//		
//		assertFalse(check);
//	}
//	@Test
//	void testloginTrue() {
//		String inputUsername="1"; 
//		String inputPasswordString="1";
//		boolean check =Authenticator.authenticateLogIn(inputUsername, inputPasswordString)&&inputUsername.equals(user.getUsername());
//		
//		assertTrue(check);
//	}
	
//	@Test
//	void testNewInputs() {
//		appdata.initializeAttributes();
//		appdata.setUser(user.getUsername(), user.getPassword());
//		Database.addUser(user.getUsername(), user.getPassword(), appdata.getAllAttributes());
//		String inputUsername="1"; 
//		String inputPasswordString="22";
//		User user2= new User(inputUsername, inputPasswordString);
//		boolean check =authenticator.checkFieldsAreValid(inputUsername, inputPasswordString)&&!UserProfile.otherUsersUsername(inputUsername);
//		
//		assertFalse(check);
//	}
//	
	
	
	

}
