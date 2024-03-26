package guilayout;

import javafx.application.Application;
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
   
    
    Poster currentPoster; 
    Dog currentDog;
    Date appointment;
    User user;
    AppointmentManager userAppointments;
    ArrayList <Appointment> appointments = new ArrayList<>();
    Appointment existingAppointment;
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

    @Override
    public void start(Stage stage) {
    	Components.updateCurrentScene("none");
    	
    	initailizePrimaryScene(stage);
    	
        stage.setTitle("Calendar");
        //meetWithLabel.setText("Meet with " + currentDog.getName() + " and " + currentPoster.getDisplayName());
        meetWithLabel.setAlignment(Pos.CENTER);
        
        user = appData.getUser();
        userAppointments = appData.getAppointmentManager();
        otherUsersAppointments = appData.getOtherUsersAppointments();

        // Title label to display the current month and year
        Label titleLabel = Components.largeLabel(getFormattedTitle(), Pos.CENTER);
        titleLabel.setDisable(true);

        calendarGrid = new GridPane();
        calendarGrid.setAlignment(Pos.CENTER);
        calendarGrid.setHgap(5);
        calendarGrid.setVgap(5);

        for(Appointment app : userAppointments.getUserAppointments()) {
        	
        	if(app.getDogID() == currentDog.getId()) {
//        		System.out.println(app.getDogID() + " curr: " + currentDog.getId() );
        		
        		
        		
        		existingAppointment = app; 
        		
        		break;
        	}else {
        		existingAppointment = null; 
        	}
        }

        createCalendar();

        Button prevMonthButton = Components.calendarButton("←");
        prevMonthButton.setOnAction(event -> {
        	if(!currentDate.minusMonths(1).isBefore(todaysDate)) {
            currentDate = currentDate.minusMonths(1);
            updateCalendar();
            titleLabel.setText(getFormattedTitle());
            }
        });

        Button nextMonthButton = Components.calendarButton("→");
        nextMonthButton.setOnAction(event -> {
            currentDate = currentDate.plusMonths(1);
            updateCalendar();
            titleLabel.setText(getFormattedTitle());
        });
        
        
        Button confirmationButton = Components.calendarButton("Confirm Appointment");
        confirmationButton.setOnAction(event -> {
        	handleConfirmButtonClick();
            
        });
        
        HBox navigation = new HBox(prevMonthButton, nextMonthButton);
        navigation.setAlignment(Pos.CENTER);
        navigation.setSpacing(50);
        
        successLabel.setText("Waiting on a date");
        

        mainContainer.getChildren().addAll(titleLabel, calendarGrid, navigation, meetWithLabel, successLabel, confirmationButton);

        updateCalendar();

        stage.show();
    }

    private String getFormattedTitle() {
        return currentDate.getMonth().toString() + " " + currentDate.getYear();
    }
    
    private void updateCalendar() {
    	calendarGrid.getChildren().clear();
    	createCalendar();
    }

    private void createCalendar() {
        // Clear existing calendar
    	StackPane dayButton;

        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1=Monday, ..., 7=Sunday

        // Add buttons for each day of the month
        for (int row = 0; row < WEEKS_IN_MONTH; row++) {
            for (int col = 0; col < DAYS_IN_WEEK; col++) {
                int dayOfMonth = row * DAYS_IN_WEEK + col + 1;
                LocalDate buttonDate = firstDayOfMonth.plusDays((row * DAYS_IN_WEEK) + col - dayOfWeek + 1);
                int buttonText = buttonDate.getDayOfMonth();
                dayButton = Components.calendarCell(Integer.toString(buttonText));
                
                if (dayOfMonth > 0 && dayOfMonth <= currentDate.lengthOfMonth() && buttonDate.isAfter(firstDayOfMonth.minusDays(1))) {
                	if(!appData.isDateAlreadyBooked(currentDog.getId(), currentDog.getPosterId(), buttonDate)) {
	                	StackPane dayButtonCopy = dayButton; 
	//                	LocalDate buttonDateCopy = buttonDate;
	                	
	                    dayButton.setOnMouseClicked(event -> {
	                    	successLabel.setText(buttonDate.toString());
	                    	currentSelectedDate = buttonDate; 
	                    	if(dayButtonCopy.getId() == null) {
	            				oldSelectedButton.setId("highlighted-calendar-cell");
	            				oldSelectedButton = dayButtonCopy; 
	            				dayButtonCopy.setId(("highlighted-calendar-cell"));
	            			}});
	                    
	                    if(existingAppointment != null && buttonDate.equals(existingAppointment.getDate().toLocalDate())) {
	                    	Label existingAppointmentLabel = Components.tinyLabel("Date with " + currentDog.getName(),Pos.CENTER);
	                    	existingAppointmentLabel.getStyleClass().add("your-booking");
	                    	StackPane.setAlignment(existingAppointmentLabel, Pos.CENTER);
	                    	dayButton.getChildren().add(existingAppointmentLabel);
	                    } 
	                   

                	}else {
                		dayButton.setId("inactive-calendar-cell");
                		Label otherExistingAppointment = Components.tinyLabel(currentDog.getName() + " is busy",Pos.CENTER);
                		otherExistingAppointment.getStyleClass().add("busy");
                    	StackPane.setAlignment(otherExistingAppointment, Pos.CENTER);
                    	dayButton.getChildren().add(otherExistingAppointment);
                	}
                }
                else {
                	dayButton.setId("inactive-calendar-cell");
                }
                
//                calendarGrid.getChildren().add(dayButton);
                calendarGrid.add(dayButton, col, row);
                
            }
        }
    	  
    }

    
    private void handleConfirmButtonClick() {
        java.util.Date utilDate = Date.from(currentSelectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        currentAppointment = new Appointment(currentPoster.getUniqueId(),currentDog.getId(), sqlDate,user.getUserID());
        
        //adding to the DB
        if (currentAppointment != null && (userAppointments.appointmentExists(currentAppointment)) ) {
        	userAppointments.removeAppointment(currentAppointment);
        }
        
        if(!checkIfBefore()) {
        
        userAppointments.addAppointment(currentAppointment);
    	existingAppointment = currentAppointment; 
    	successLabel.setText("Date added successfully!");
    	updateCalendar();}
        else 
        {
        	successLabel.setText("Date not added, please choose a future date");

        }
    }

    public boolean checkIfBefore () {
        java.util.Date utilDate = Date.from(currentSelectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

   
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    
    	java.sql.Date nowSQL=Date.valueOf(LocalDate.now());
        LocalDate nowLocal = LocalDate.now();
        LocalDate appointmentDate = sqlDate.toLocalDate();
        return sqlDate.before(nowSQL)&&appointmentDate.getMonthValue()==nowLocal.getMonthValue();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void setCurrentPosterDog(Poster poster, Dog dog) { //dog obbject is null why?
		 this.currentPoster = poster; // set current poster

		 this.currentDog = dog;
		 meetWithLabel.setText("Meet with " + dog.getName() + " and " + poster.getDisplayName());
		 
		 
		 //updateMeetWithLabel(poster, dog);
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
	
	public void setExistingAppointment(Appointment app) {
		this.existingAppointment = app;
	}
	
	
	
    
   /* public void updateMeetWithLabel(Poster poster, Dog dog) {
    	setCurrentPosterDog(poster,dog);
        if (currentDog != null && currentPoster != null) {
            meetWithLabel.setText("Meet with " + dog.getName() + " and " + poster.getDisplayName());
        } else {
            meetWithLabel.setText("Meet with the selected Dog"); // Clear the label if either poster or dog is null
        }
    }*/
    
}