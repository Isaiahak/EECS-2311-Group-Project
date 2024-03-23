package guicontrol;

import java.time.LocalDate;
import java.util.*;

import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.database.Database;
import backend.dog.Dog;
import backend.dog.trait.Age;
import backend.dog.trait.Attribute;
import backend.dog.trait.EnergyLevel;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;
import guilayout.UserProfile;
public class AppData {
	
	private User user;//comment
	
	private Hashtable<Integer, ArrayList<Dog>> allDogs; 

	private Hashtable<Integer, ArrayList<Dog>> dogProfileHashtable; // posterid, dogs
	private HashMap<Integer, Tag> allTags;
	private Hashtable<Integer,Poster> posterProfiles; // poster profiles by id 
	private PriorityQueue<Dog> sortedDogProfiles;
	private static AppData instance;
	private AppointmentManager appointmentManager;
	private ArrayList<Appointment> otherUsersAppointments;
	private HashMap<Integer, ArrayList<Attribute>> allAttributes;


	public void initializeWallet (int userid, String password) {
		this.user.setWallet(Database.getWallet(userid, password));
		this.user.getWallet().makeRecurringPayments(posterProfiles);
	}

	public AppointmentManager getAppointmentManager() {
		return appointmentManager;
	}

	public void setAppointmentManager(AppointmentManager appointmentManager) {
		this.appointmentManager = appointmentManager;
	}

	public PriorityQueue<Dog> getSortedDogProfiles() {
		return sortedDogProfiles;
	}
	
	public Hashtable<Integer,Poster> getPosterProfiles(){
		return posterProfiles;
	}

	public void setSortedDogProfiles(PriorityQueue sortedDogProfiles) {
		this.sortedDogProfiles = sortedDogProfiles;
	}

	public static AppData getInstance() {
	if (instance == null)
		instance = new AppData();
	return instance;
	}
	
	public void setUser(String user, String password) {
		this.user = Database.getUser(user, password);
	}

	public User getUser() {
		return this.user;
	}

	public  Hashtable<Integer, ArrayList<Dog>> getDogProfiles() {
		return this.dogProfileHashtable;
	}

	public void setDogProfiles() {
		
		this.dogProfileHashtable = Database.getAllDogs(user, this.posterProfiles.keySet());
	}
	
	public void setAllDogs() {
		
		this.allDogs = Database.getAllDogsNoPreferences(user, this.posterProfiles.keySet());
	}
	
	public HashMap<Integer,Tag> getallTags(){
		return this.allTags;
	}
	
	public void setAllTags(){
		this.allTags = Database.getAllTags();
	}
	
	public void setPosters() {
		// create all local poster objects, set their dogs in their dog list using na aggregate relationship	
		this.posterProfiles = Database.getAllPosters();

	}
	
	public Hashtable<Integer, Poster> getPosters(){
		return posterProfiles;
	}

	public void updateDogScores() {

	// perform check on if the user's preferences have changed before updating scores	
		if(this.user.arePreferencesEqual(UserProfile.getInstance().getOldTagPreferences()) == false || this.user.areAttributesEqual(UserProfile.getInstance().getOldSexPreferences(),
				UserProfile.getInstance().getOldAgePreferences(),
				UserProfile.getInstance().getOldSizePreferences(),
				UserProfile.getInstance().getOldEnergyLevelPreferences()) == false){
			Database.addUserDog(user.getLikedDogs(), user.getUserID(),"userdogs");
			Database.addUserDog(user.getPassedDogs(), user.getUserID(),"userpasseddogs");
			Database.deletePreferenceTagsFromUser(user.getUserID());
			Database.addPreferenceTagsToUser(user.getTagPreferences(), user.getUserID());
			Database.deleteUserAttributePreferences(user.getUserID());
			Database.addUserAttributePreferences(user.getAgePreferences(), user.getUserID());
			Database.addUserAttributePreferences(user.getSexPreferences(),  user.getUserID());
			Database.addUserAttributePreferences(user.getEnergyLevelPreferences(), user.getUserID());
			Database.addUserAttributePreferences(user.getSizePreferences(), user.getUserID());
			setDogProfiles();
			setPosterDogLists();
			initializeDogProfilesSorted();
		}
	}
	
	public void initializeDogProfilesSorted() {  // to be optimized
		PriorityQueue<Dog> dogList = new PriorityQueue<Dog>();
		
		for (ArrayList<Dog> dogs : dogProfileHashtable.values()) {
			dogList.addAll(dogs);
		}
		setSortedDogProfiles(dogList);
	}

	public void setPosterDogLists() {
		// loop through dogProfiles and add to posters
		for(Poster p : this.posterProfiles.values()) {
			p.setDogList(this.allDogs.get(p.getUniqueId()));

		}
	}

	public void initializeAttributes(){
		this.allAttributes = new HashMap<Integer, ArrayList<Attribute>>();
		
		Attribute attEx = new Sex(0);
		ArrayList<Attribute> sexList = new ArrayList<Attribute>();
		for(int i = 0 ; i < attEx.getNames().length; i++) {
			sexList.add(new Sex(i));
		}
		this.allAttributes.put(attEx.getType(), sexList);
		
		attEx = new Size(0);
		ArrayList<Attribute> sizeList = new ArrayList<Attribute>();
		for(int i = 0 ; i < attEx.getNames().length; i++) {
			sizeList.add(new Size(i));
		}
		this.allAttributes.put(attEx.getType(), sizeList);
		
		attEx = new EnergyLevel(0);
		ArrayList<Attribute> energyLevelList = new ArrayList<Attribute>();
		for(int i = 0 ; i < attEx.getNames().length; i++) {
			energyLevelList.add(new EnergyLevel(i));
		}
		this.allAttributes.put(attEx.getType(), energyLevelList);
		
		attEx = new Age(0);
		ArrayList<Attribute> ageList = new ArrayList<Attribute>();
		for(int i = 0 ; i < attEx.getNames().length; i++) {
			ageList.add(new Age(i));
		}
		
		this.allAttributes.put(attEx.getType(), ageList);
		
	}
	public HashMap<Integer, ArrayList<Attribute>> getAllAttributes(){
		return this.allAttributes;
		
	}
	private void setOtherUsersAppointments() {
		this.otherUsersAppointments = Database.getOtherUserAppointments(user.getUserID()); 
	}

	public void onStart(String username, String pass) {
		getInstance(); 	
		
		setUser(username, pass); // sets user, dog liked list, ideal dog attribtues
		
		initializeWallet(getUser().getUserID(), pass);
		
		setAllTags();
		
		setPosters();
		
		setAllDogs();
		
		setDogProfiles(); 
		
		setPosterDogLists();
			
		initializeDogProfilesSorted();

		setAppointmentManager(new AppointmentManager(user.getUserID(), Database.getUserAppointments(user.getUserID())));
		
		setOtherUsersAppointments();

		this.user.getWallet().setRecurringPayments(Database.getRecurringPayment(user.getUserID()));

	}

	public Hashtable<Integer, ArrayList<Dog>> getAllDogs() {
		return this.allDogs;
	}

	public ArrayList<Appointment> getOtherUsersAppointments() {
		return this.otherUsersAppointments;
	}
	
	public boolean isDateAlreadyBooked(int dogId, int posterId, LocalDate appDate) {
		
		for(Appointment otherApp: this.otherUsersAppointments ) {
			if(otherApp.getPosterID() == posterId && otherApp.getDogID() == dogId && otherApp.getLocalDate().equals(appDate)) 
				return true;

		}
		return false;
	}
}
