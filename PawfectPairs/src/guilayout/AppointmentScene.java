package guilayout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.database.Database;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.user.User;
import guicontrol.AppData;

public class AppointmentScene extends Application {

    private static final int DAYS_IN_WEEK = 7;
    private static final int WEEKS_IN_MONTH = 6;

    private LocalDate currentDate = LocalDate.now();
    private static AppointmentScene instance;
    private Label meetWithLabel = new Label();
    private Label successLabel = new Label();
    Poster currentPoster; 
    Dog currentDog;
    Date appointment;
    User user;
    AppointmentManager userAppointments;
    ArrayList <Appointment> appointments = new ArrayList<>();
    Appointment currentAppointment;

    private AppointmentScene() {
    }

    // Method to get the single instance of AppointmentScene
    public static AppointmentScene getInstance() {
        if (instance == null) {
            instance = new AppointmentScene();
        }
        return instance;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Calendar");
        //meetWithLabel.setText("Meet with " + currentDog.getName() + " and " + currentPoster.getDisplayName());
        meetWithLabel.setAlignment(Pos.CENTER);
        
        user = AppData.getInstance().getUser();
        userAppointments = AppData.getInstance().getAppointmentManager();

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        HBox navTab = Components.navTab(UserProfile.getInstance(), LikedDogScene.getInstance(), DogProfileScene.getInstance(), SponsoredDogsScene.getInstance(), BookedAppointmentScene.getInstance(), stage, "appointments", AppData.getInstance());
        // Title label to display the current month and year
        Button titleLabel = new Button(getFormattedTitle());
        titleLabel.setDisable(true);

        GridPane calendarGrid = new GridPane();
        calendarGrid.setAlignment(Pos.CENTER);
        calendarGrid.setHgap(5);
        calendarGrid.setVgap(5);

        
        //testing new code
        
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1=Monday, ..., 7=Sunday

        // Add buttons for each day of the month
        for (int row = 0; row < WEEKS_IN_MONTH; row++) {
            for (int col = 0; col < DAYS_IN_WEEK; col++) {
                int dayOfMonth = row * DAYS_IN_WEEK + col + 1;
                Button dayButton = new Button();
                if (dayOfMonth > 0 && dayOfMonth <= currentDate.lengthOfMonth()) {
                    // Calculate the LocalDate for the current button
                    LocalDate buttonDate = firstDayOfMonth.plusDays((row * DAYS_IN_WEEK) + col - dayOfWeek + 1);
                    
                    // Format the LocalDate as desired (e.g., "MM/dd/yyyy")
                    String buttonText = buttonDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    
                    dayButton.setText(buttonText);
                    dayButton.setOnAction(event -> handleDayButtonClick(buttonDate));
                } else {
                    dayButton.setDisable(true);
                }
                calendarGrid.add(dayButton, col, row);
            }
        }
       /* // Add buttons for each day of the month
        for (int row = 0; row < WEEKS_IN_MONTH; row++) {
            for (int col = 0; col < DAYS_IN_WEEK; col++) {
                int dayOfMonth = row * DAYS_IN_WEEK + col + 1;
                Button dayButton = new Button(String.valueOf(dayOfMonth));
                dayButton.setOnAction(event -> handleDayButtonClick(dayOfMonth));
                calendarGrid.add(dayButton, col, row);
            }
        } */

        Button prevMonthButton = new Button("Previous");
        prevMonthButton.setOnAction(event -> {
            currentDate = currentDate.minusMonths(1);
            updateCalendar(calendarGrid);
            titleLabel.setText(getFormattedTitle());
        });

        Button nextMonthButton = new Button("Next");
        nextMonthButton.setOnAction(event -> {
            currentDate = currentDate.plusMonths(1);
            updateCalendar(calendarGrid);
            titleLabel.setText(getFormattedTitle());
        });
        successLabel.setText("Waiting on a date");

        root.getChildren().addAll(navTab, titleLabel, calendarGrid, prevMonthButton, nextMonthButton,meetWithLabel,successLabel);

        updateCalendar(calendarGrid);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
        
	     root.setAlignment(Pos.CENTER);
    }

    private String getFormattedTitle() {
        return currentDate.getMonth().toString() + " " + currentDate.getYear();
    }

    private void updateCalendar(GridPane calendarGrid) {
        // Clear existing calendar
       /* calendarGrid.getChildren().clear();

        // Get the first day of the month
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1=Monday, ..., 7=Sunday

        // Add buttons for each day of the month
        for (int row = 0; row < WEEKS_IN_MONTH; row++) {
            for (int col = 0; col < DAYS_IN_WEEK; col++) {
                int dayOfMonth = row * DAYS_IN_WEEK + col - dayOfWeek + 2;
                Button dayButton = new Button();
                if (dayOfMonth > 0 && dayOfMonth <= currentDate.lengthOfMonth()) {
                    dayButton.setText(String.valueOf(dayOfMonth));
                    dayButton.setOnAction(event -> handleDayButtonClick(dayOfMonth));
                } else {
                    dayButton.setDisable(true);
                }
                calendarGrid.add(dayButton, col, row);
            }
        } */
    	
    	// Clear existing calendar
        calendarGrid.getChildren().clear();

        // Get the first day of the month
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1=Monday, ..., 7=Sunday

        // Add buttons for each day of the month
        for (int row = 0; row < WEEKS_IN_MONTH; row++) {
            for (int col = 0; col < DAYS_IN_WEEK; col++) {
                int dayOfMonth = row * DAYS_IN_WEEK + col - dayOfWeek + 2;
                Button dayButton = new Button();
                if (dayOfMonth > 0 && dayOfMonth <= currentDate.lengthOfMonth()) {
                    // Calculate the LocalDate for the current button
                    LocalDate buttonDate = firstDayOfMonth.plusDays((row * DAYS_IN_WEEK) + col - dayOfWeek + 1);
                    
                    dayButton.setText(String.valueOf(dayOfMonth));
                    dayButton.setOnAction(event -> handleDayButtonClick(buttonDate));
                } else {
                    dayButton.setDisable(true);
                }
                calendarGrid.add(dayButton, col, row);
            }
        }
    }

   /* private void handleDayButtonClick(int dayOfMonth) {
        System.out.println("Selected day: " + dayOfMonth);
        System.out.println("Dog: "+ currentDog.getName());
        System.out.println("Poster: "+ currentPoster.getDisplayName());
        // Add your logic for handling the selected day (e.g., scheduling appointments)
    }*/
    
    private void handleDayButtonClick(LocalDate date) {
//        System.out.println("Selected date: " + date);
//        System.out.println("Year: " + date.getYear());
//        System.out.println("Month: " + date.getMonth());
//        System.out.println("Day: " + date.getDayOfMonth());
//        System.out.println("Dog: " + currentDog.getName());
//        System.out.println("Poster: " + currentPoster.getDisplayName());
        
        java.util.Date utilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        currentAppointment = new Appointment(currentPoster.getUniqueId(),currentDog.getId(), sqlDate,user.getUserID());
        
        //adding to the DB
        if (!(userAppointments.appointmentExists(currentAppointment))) {
        	//Database.addBookedDate(currentPoster.getUniqueId(), currentDog.getId(), sqlDate,user.getUserID());
        	userAppointments.addAppointment(currentAppointment);
        	successLabel.setText("Date added successfully!");
        	
        }
        else {
        	successLabel.setText("Unfortunately you cannot book another appointment as you have already booked an appointment");
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

	public AppointmentManager getUserAppointments() {
		return userAppointments;
	}

	public void setUserAppointments(AppointmentManager userAppointments) {
		this.userAppointments = userAppointments;
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
