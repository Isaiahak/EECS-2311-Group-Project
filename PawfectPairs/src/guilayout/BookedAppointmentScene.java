package guilayout;

import backend.user.User;
import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.dog.Dog;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BookedAppointmentScene extends PrimaryScene {

    User user;
    AppointmentManager userAppointments;
    ArrayList <Appointment> appointments = new ArrayList<>();
    Appointment currentAppointment;

    private static BookedAppointmentScene instance;

    public static BookedAppointmentScene getInstance() {
        if (instance == null) {
            instance = new BookedAppointmentScene();
        }
        return instance;
    }

    private BookedAppointmentScene() {

    }

    @Override
    public void start(Stage stage) {
    	Components.updateCurrentScene("appointments");
        initailizePrimaryScene(stage);
    	
        user = AppData.getInstance().getUser();
        mainContainer.setAlignment(javafx.geometry.Pos.CENTER);
        mainContainer.setSpacing(20);
        VBox.setVgrow(root, Priority.ALWAYS);

        Label appointmentsLabel = Components.largeLabel("Your Booked Appointments", Pos.CENTER);

        appointments = appData.getAppointmentManager().getUserAppointments();

        VBox appointmentsDisplay = new VBox();

        

        
//        if (appointments!= null) {
        	for (Appointment selectedAppointment : appointments) {
//                ArrayList<Dog> doglist = appData.getDogProfiles().get(selectedAppointment.getPosterID());
        		ArrayList<Dog> doglist = appData.getUser().getLikedDogs();
                
                Dog appointmentDog = null;
                
                for(Dog dog : doglist){
                    if( dog.getId() == selectedAppointment.getDogID()){
                       appointmentDog = dog;
                    }
                }
        		//Katya and Isaiah, we might need to chenge this to get dog locally
        		appointmentsDisplay.getChildren().add(Components.appointmentView(appointmentDog, selectedAppointment.getDate(), stage, appData.getPosters()));
        	}

        
        appointmentsDisplay.setAlignment(javafx.geometry.Pos.CENTER);

        mainContainer.getChildren().addAll(
                appointmentsLabel,
                appointmentsDisplay
        );


        stage.setTitle("Pawfect Pairs");
        stage.show();

    }
}