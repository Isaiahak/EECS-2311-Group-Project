import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.database.Database;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.user.User;
import backend.wallet.RecurringPayment;
import backend.wallet.Wallet;
import guicontrol.AppData;
import guilayout.LikedDogScene;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;

class DogAdoptionTest {

	private static User user;
	private static Dog dog;
	private static Poster poster;
	private static Wallet userWallet;
	
	@BeforeAll
    public static void setUpClass() {
		user = new User("test", "tests");
		user.setUserID(-1);
		user.setEmail("test@gmail.com");
		
		dog = new Dog("testDog",-1,0,0,0,0,-1,false,".","test");
		user.addLikedDogs(dog);
		
		poster = new Poster(5.0, "testPoster", -1, "000","poster@gmail.com", 0.0);
		ArrayList<Dog> posterDogs = new ArrayList<Dog>();
		posterDogs.add(dog);
		poster.setDogList(posterDogs);
		userWallet =  new Wallet(200, user.getUserID());
		user.setWallet(userWallet);
	}
	@Test
	void testAdoption() {		
		dog.setAdopted(true);
		assertEquals(true,dog.getAdopted());
		dog.setAdopted(false);
					
	}
	@Test
	void testMultipleAdoptions() {
		Dog dog2 = new Dog("testDog2",-2,0,0,0,0,-1,false,".","test");
		user.addLikedDogs(dog2);
		ArrayList<Dog> likedDogList = user.getLikedDogs();
		for (Dog d:likedDogList) {
				d.setAdopted(true);
				user.addToAdoptedDogs(d);				
	}
		assertEquals(true,dog.getAdopted());
		assertEquals(true,dog2.getAdopted());
		dog.setAdopted(false);
		dog2.setAdopted(false);
}
	@Test
	void testAlreadyAdopted() {		
		Dog dog2 = new Dog("testDog2",-2,0,0,0,0,-1,false,".","test");
		user.addLikedDogs(dog2);
		ArrayList<Dog> likedDogList = user.getLikedDogs();
		int counter  =0;
		dog.setAdopted(true);
		dog.setAdopted(true);
		for (Dog d:likedDogList) {
			if (d.getAdopted()==false) {
				counter = counter+1;
				d.setAdopted(true);
				user.addToAdoptedDogs(d);		
			}
	}	
		assertEquals(1,counter);
		dog.setAdopted(false);
					
	}
	
	@Test
	void testSponsorshipsAdoption() {		
		dog.setAdopted(true);
		RecurringPayment payment = new RecurringPayment(100, 7, dog.getId(), poster.getUniqueId());
		userWallet.addRecurringPayment(payment);
		if(dog.getAdopted()==true) {
			user.getWallet().removeRecurringPayment(dog.getId());
		}
		HashMap<Integer, RecurringPayment> recurringList = userWallet.getRecurringPayments();
		assertEquals(0,recurringList.size());			
	}
	
	@Test
	void testAppointmentAdopt() {		
		dog.setAdopted(true);
		String dateString = "2028-03-20";
		ArrayList<Appointment> userappointment = new ArrayList<Appointment> ();
		java.sql.Date sqlDate  = java.sql.Date.valueOf(dateString);
		Appointment appointment = new Appointment(poster.getUniqueId(), dog.getId(), sqlDate, user.getUserID());
		AppointmentManager appointmentManager = new AppointmentManager(user.getUserID(), userappointment);
		
		if (dog.getAdopted()==true) {
		appointmentManager.removeAppointment(appointment);
		}
					
	}
	
	
}
