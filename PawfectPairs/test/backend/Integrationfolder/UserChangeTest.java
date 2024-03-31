import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.database.Database;
import backend.dog.trait.Attribute;
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
  private static  AppData appdata;
    //@Mock
    //private Label mockLabel;
    private Label label;
    private User user;
    private static String[] oldPassword;
    private static Boolean[] existedInDbBefore;
    private static HashMap<Integer, ArrayList<Attribute>> AllAttributes;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        appData.getInstance();
      appdata.initializeAttributes();
AllAttributes=appdata.getAllAttributes();
        oldPassword = new String[2];
        existedInDbBefore = new Boolean[2];

        // Set up test users
        setUpUser("1");
        setUpUser("2");
    }

    private static void setUpUser(String username) {
        if (!Database.usernameChecker(username).equals(username)) {
            Database.addUser(username, username, AllAttributes);
            existedInDbBefore[Integer.parseInt(username) - 1] = false;
        } else {
            oldPassword[Integer.parseInt(username) - 1] = Database.getPassword(username);
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

    @BeforeEach
    void setUp() throws Exception {
        user=new User("1", "1");
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testChangeToOtherUserFalse() {
        String inputUsername="1"; 
        String inputPasswordString="q3";
        boolean check = ChangeProfileDetails.otherUsersUsername(inputUsername, user);
        assertFalse(check);
    }

    @Test
    void testChangeToOtherUserTrue() {
        String inputUsername="1"; 
        String inputPasswordString="q3";
        boolean check = ChangeProfileDetails.otherUsersUsername(inputUsername, user);
        assertFalse(check);
    }
}