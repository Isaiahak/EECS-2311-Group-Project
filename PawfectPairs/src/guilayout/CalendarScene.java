package guilayout;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.PriorityQueue;

import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.user.User;
import guicontrol.AppData;

public class CalendarScene extends PrimaryScene {

	private static final int DAYS_IN_WEEK = 7;
	private static final int WEEKS_IN_MONTH = 6;

	private LocalDate currentDate = LocalDate.now();
	private LocalDate todaysDate = LocalDate.now();
	private static CalendarScene instance;
	private Label meetWithLabel = Components.mediumLabel();
	private Label successLabel = Components.mediumLabel();
	private StackPane oldSelectedButton = new StackPane(); 
	private LocalDate currentSelectedDate; 

	private 		Month month= currentDate.getMonth(); 

	Poster currentPoster; 
	Dog currentDog;
	Date appointment;
	User user;
	AppointmentManager userAppointments;
	ArrayList <Appointment> appointments = new ArrayList<>();
	ArrayList <Appointment> existingAppointment = new ArrayList<>();
	Appointment currentAppointment;

	ArrayList <Appointment> otherUsersAppointments;

	private GridPane calendarGrid;

	private CalendarScene(){
	}

	// Method to get the single instance of AppointmentScene
	public static CalendarScene getInstance() {
		if (instance == null) {
			instance = new CalendarScene();
		}
		return instance;
	}

	private void retrieveAppDataInfo () {
		user = appData.getUser();
		userAppointments = appData.getAppointmentManager();
		otherUsersAppointments = appData.getOtherUsersAppointments();

	}
	private void pageSetUp (Stage stage, Label titleLabel) {
		stage.setTitle("Calendar");
		meetWithLabel.setAlignment(Pos.CENTER);

		// Title label to display the current month and year
		titleLabel.setDisable(true);

		calendarGrid = new GridPane();
		calendarGrid.setAlignment(Pos.CENTER);
		calendarGrid.setHgap(5);
		calendarGrid.setVgap(5);

	}

	@Override
	public void start(Stage stage) {
		Components.updateCurrentScene("none");
		Label titleLabel = Components.largeLabel(getFormattedTitle(), Pos.CENTER);

		initailizePrimaryScene(stage);
		retrieveAppDataInfo ();

		pageSetUp(stage, titleLabel);

		for(Appointment app : userAppointments.getUserAppointments()) {

			if(app.getDogID() == currentDog.getId()) {
				if(existingAppointment==null ||existingAppointment.isEmpty()||!existingAppointment.contains(app))
					existingAppointment.add(app); 

				
			}		
		}

		createCalendar();

		HBox navigation =  CreateNavigationButtons (titleLabel);

		Button confirmationButton = Components.calendarButton("Confirm Appointment");
		confirmationButton.setOnAction(event -> {
			
				handleConfirmButtonClick();

		});

		successLabel.setText("Waiting on a date");

		mainContainer.getChildren().addAll(titleLabel, calendarGrid, navigation, meetWithLabel, successLabel, confirmationButton);

		updateCalendar();

		stage.show();
	}

	private boolean leapYear (LocalDate date) {
		// Get the YearMonth object for the LocalDate
		YearMonth yearMonth = YearMonth.from(date);

		// Check if the year of the YearMonth is a leap year
		boolean isLeapMonth = yearMonth.isLeapYear();
		return isLeapMonth;
	}
	private void createCalendar() {
		// Clear existing calendar
		StackPane dayButton;

		LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
		int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1=Monday, ..., 7=Sunday
		Boolean leapMonth=leapYear(firstDayOfMonth);

		// Add buttons for each day of the month
		for (int row = 0; row < WEEKS_IN_MONTH; row++) {
			for (int col = 0; col < DAYS_IN_WEEK; col++) {
				int cellNumber = row * DAYS_IN_WEEK + col + 1;
				LocalDate buttonDate = firstDayOfMonth.plusDays((row * DAYS_IN_WEEK) + col - dayOfWeek + 1);
				int buttonText = buttonDate.getDayOfMonth();
				dayButton = Components.calendarCell(Integer.toString(buttonText));

				if (!alreadyBookedByThisUser(buttonDate)&&cellNumber>0&& buttonDate.getMonth()==month&&
						buttonText<=month.length(leapMonth)&&buttonDate.isAfter(firstDayOfMonth.minusDays(1))&&
						buttonDate.isAfter(todaysDate)){
					if(!appData.isDateAlreadyBooked(currentDog.getId(), currentDog.getPosterId(), buttonDate)) {
						StackPane dayButtonCopy = dayButton; 
						activeButtonBehaviour ( dayButton,  dayButtonCopy,  buttonDate);

					}
					else {
						setBusyForOtherUserAppointments(dayButton);

					}
				}

				else if (alreadyBookedByThisUser(buttonDate)){
					populateExistingAppointments ( buttonDate,  dayButton );
				}
				else 
					dayButton.setId("inactive-calendar-cell");


				calendarGrid.add(dayButton, col, row);

			}
		}

	} 
	
	private void activeButtonBehaviour (StackPane dayButton, StackPane dayButtonCopy, LocalDate buttonDate)  {

		SimpleIntegerProperty numClicks = new SimpleIntegerProperty(0);
		dayButton.setOnMouseClicked(event -> {
			numClicks.set(numClicks.get()+1);
			if( numClicks.get()<=1){
				successLabel.setText(buttonDate.toString());
				currentSelectedDate = buttonDate; 
				if(dayButtonCopy.getId() == null) {
					oldSelectedButton.setId("highlighted-calendar-cell");
					oldSelectedButton = dayButtonCopy; 
					dayButtonCopy.setId(("highlighted-calendar-cell"));
				}
				else 
					successLabel.setText("Already booked");
			}});
	}
	private void setBusyForOtherUserAppointments(StackPane dayButton) {
		dayButton.setId("inactive-calendar-cell");
		Label otherExistingAppointment = Components.tinyLabel(currentDog.getName() + " is busy",Pos.CENTER);
		otherExistingAppointment.getStyleClass().add("busy");
		StackPane.setAlignment(otherExistingAppointment, Pos.CENTER);
		dayButton.getChildren().add(otherExistingAppointment);
	}

	private void populateExistingAppointments (LocalDate buttonDate, StackPane dayButton ) {
		if(alreadyBookedByThisUser(buttonDate)){
			Label existingAppointmentLabel = Components.tinyLabel("Date with " + currentDog.getName(),Pos.CENTER);
			existingAppointmentLabel.getStyleClass().add("your-booking");
			StackPane.setAlignment(existingAppointmentLabel, Pos.CENTER);
			dayButton.getChildren().add(existingAppointmentLabel);
		} 

	}

	public boolean alreadyBookedByThisUser(LocalDate buttonDate) {
		if(existingAppointment==null||existingAppointment.isEmpty())
			return false;
		else if((!existingAppointment.isEmpty() && containsDate(buttonDate))) 
			return true;
		else return false;

	}
	public boolean containsDate (LocalDate buttonDate) {
		boolean contains=false;
		if (existingAppointment!=null&&!existingAppointment.isEmpty()) {
			for (Appointment appointment : existingAppointment) {

				if(appointment!=null&&appointment.getDate().toLocalDate().equals(buttonDate)) {
					System.out.println("contains true");

					return true;
				}
			}
		}
		return contains;
	}

	private void handleConfirmButtonClick() {
		java.util.Date utilDate = Date.from(currentSelectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		if (!alreadyBookedByThisUser(currentSelectedDate)&&!Appointment.checkIfBefore(currentSelectedDate)) {
			currentAppointment = new Appointment(currentPoster.getUniqueId(),currentDog.getId(), sqlDate,user.getUserID());

			//adding to the DB
			if (currentAppointment != null && (userAppointments.appointmentExists(currentAppointment)) ) {
				userAppointments.removeAppointment(currentAppointment);
			}

			if ((existingAppointment==null||existingAppointment.isEmpty()||!existingAppointment.contains(currentAppointment))&&
					!userAppointments.contains(currentAppointment)) {
				existingAppointment.add(currentAppointment); 
				//System.out.println(existingAppointment.contains(currentAppointment)+" added to exist");
				userAppointments.addAppointment(currentAppointment);
				successLabel.setText("Date added successfully!");
				updateCalendar();}

			
		}
		else 
		{
			successLabel.setText("Date not added, please choose a valid date");

		}

	}

	
	public static void main(String[] args) {
		launch(args);
	}
	public void setCurrentPosterDog(Poster poster, Dog dog) { //dog obbject is null why?
		this.currentPoster = poster; // set current poster

		this.currentDog = dog;
		meetWithLabel.setText("Meet with " + dog.getName() + " and " + poster.getDisplayName());


	}

	private String getFormattedTitle() {
		return currentDate.getMonth().toString() + " " + currentDate.getYear();
	}

	private HBox CreateNavigationButtons (Label titleLabel){
		Button prevMonthButton = Components.calendarButton("←");

		prevMonthButton.setOnAction(event -> {
			if(!currentDate.minusMonths(1).isBefore(todaysDate)) {
				currentDate = currentDate.minusMonths(1);
				month = currentDate.getMonth();
				updateCalendar();
				titleLabel.setText(getFormattedTitle());
			}
		});

		Button nextMonthButton = Components.calendarButton("→");

		nextMonthButton.setOnAction(event -> {
			currentDate = currentDate.plusMonths(1);
			month = currentDate.getMonth();
			updateCalendar();
			titleLabel.setText(getFormattedTitle());
		});
		HBox navigation = new HBox(prevMonthButton, nextMonthButton);
		navigation.setAlignment(Pos.CENTER);
		navigation.setSpacing(50);

		return navigation;
	}

	private void updateCalendar() {
		calendarGrid.getChildren().clear();
		createCalendar();
	}



	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment getCurrentAppointment() {
		return currentAppointment;
	}

	public void setCurrentAppointment(Appointment currentAppointment) {
		this.currentAppointment = currentAppointment;
	}

	public void setExistingAppointment(ArrayList <Appointment> existingAppointment) {
		this.existingAppointment=existingAppointment;
	}
	public ArrayList <Appointment> getExistingAppointment() {
		return this.existingAppointment;
	}




}