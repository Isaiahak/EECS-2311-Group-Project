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

        Label appointmentsLabel = Components.largeLabel("Your Booked Appointments", Pos.CENTER);

        appointments = appData.getAppointmentManager().getUserAppointments();
        
        mainContainer.getChildren().add(
                appointmentsLabel
        );

        	for (Appointment selectedAppointment : appointments) {
        		ArrayList<Dog> doglist = appData.getUser().getLikedDogs();
                
                Dog appointmentDog = null;
                
                for(Dog dog : doglist){
                    if( dog.getId() == selectedAppointment.getDogID()){ //&& dog.getAdopted()!=true
                       appointmentDog = dog;
                    }
                    
                }
        		mainContainer.getChildren().add(Components.appointmentView(appointmentDog, selectedAppointment.getDate(), stage, appData.getPosters()));
        	}


        stage.show();

    }
}