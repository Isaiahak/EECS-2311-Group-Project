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
    
    private String defaultStyle = "-fx-background-color: #d1d1d1; -fx-text-fill: #0f0f0f; -fx-alignment: top-right;";
	private String highlightedStyle = "-fx-background-color: #ccffd1; -fx-text-fill: #0f0f0f; -fx-alignment: top-right;";
	private String inactiveStyle = "-fx-background-color: #b5b5b5; -fx-text-fill: #0f0f0f; -fx-alignment: top-right;";
    
    Poster currentPoster; 
    Dog currentDog;
    Date appointment;
    User user;
    AppointmentManager userAppointments;
    ArrayList <Appointment> appointments = new ArrayList<>();
    Appointment existingAppointment;
    Appointment currentAppointment;
    
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
    	initailizePrimaryScene();
    	
        stage.setTitle("Calendar");
        //meetWithLabel.setText("Meet with " + currentDog.getName() + " and " + currentPoster.getDisplayName());
        meetWithLabel.setAlignment(Pos.CENTER);
        
        user = appData.getUser();
        userAppointments = appData.getAppointmentManager();

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        HBox navTab = Components.navTab(userProfileScene, likedDogsScene, dogProfileScene, sponsoredDogsScene, bookedAppointmentsScene, stage, "appointments", appData);
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
        

        root.getChildren().addAll(navTab, titleLabel, calendarGrid, navigation, meetWithLabel, successLabel, confirmationButton);

        updateCalendar();

        Scene scene = new Scene(root, Components.screenWidth, Components.screenHeight);
        stage.setScene(scene);
        stage.show();
        
	     root.setAlignment(Pos.CENTER);
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
                	StackPane dayButtonCopy = dayButton; 
//                	LocalDate buttonDateCopy = buttonDate;
                	
                    dayButton.setOnMouseClicked(event -> {
                    	successLabel.setText(buttonDate.toString());
                    	currentSelectedDate = buttonDate; 
                    	if(dayButtonCopy.getStyle().equals(defaultStyle)) {
            				oldSelectedButton.setStyle(defaultStyle);
            				oldSelectedButton = dayButtonCopy; 
            				dayButtonCopy.setStyle(highlightedStyle);
            			}});
                    
                    if(existingAppointment != null && buttonDate.equals(existingAppointment.getDate().toLocalDate())) {
                    	Label existingAppointmentLabel = Components.tinyLabel("Date with " + currentDog.getName(),Pos.CENTER);
                    	existingAppointmentLabel.setStyle("-fx-background-color: #82daf5; -fx-text-fill: #0f0f0f; -fx-alignment: top-right;");
                    	StackPane.setAlignment(existingAppointmentLabel, Pos.CENTER);
                    	dayButton.getChildren().add(existingAppointmentLabel);
                    }
                    
//                    calendarGrid.getChildren().add(dayButton);
                }
                
                else {
                    dayButton.setStyle(inactiveStyle);
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
        	System.out.println("removed");
        	userAppointments.removeAppointment(currentAppointment);
        }
        
        
        userAppointments.addAppointment(currentAppointment);
    	existingAppointment = currentAppointment; 
    	successLabel.setText("Date added successfully!");
    	updateCalendar();
    	
        for(Appointment app : userAppointments.getUserAppointments()) {
        	System.out.println(app.getDogID());
        }

        
        
        // Add your logic for handling the selected date (e.g., scheduling appointments)
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
	
	
    
   /* public void updateMeetWithLabel(Poster poster, Dog dog) {
    	setCurrentPosterDog(poster,dog);
        if (currentDog != null && currentPoster != null) {
            meetWithLabel.setText("Meet with " + dog.getName() + " and " + poster.getDisplayName());
        } else {
            meetWithLabel.setText("Meet with the selected Dog"); // Clear the label if either poster or dog is null
        }
    }*/
    
}
