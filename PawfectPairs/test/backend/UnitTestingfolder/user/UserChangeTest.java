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


User user;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		AppData.getInstance();
		

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);

		user = new User("1", "1");

    	

	}

	@AfterEach
	void tearDown() throws Exception {
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
