package guilayout;

import backend.user.User;
import backend.database.Database;
import backend.dog.Dog;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

public class BookedAppointmentScene extends Application {

    User user;

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
        user = AppData.getInstance().getUser();

        VBox root = new VBox();
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setSpacing(20);

        HBox navTab = Components.navTab(UserProfile.getInstance(), LikedDogScene.getInstance(), DogProfileScene.getInstance(), BookedAppointmentScene.getInstance(), stage);

        Label appointmentsLabel = Components.largeLabel("Your Booked Appointments", Pos.CENTER);

        // Get user appointments from the database
        TreeMap<Integer, Date> userAppointments = Database.getUserAppointments(user.getUserID());

        VBox appointmentsDisplay = new VBox();

        // Display user appointments
        if (userAppointments != null) {
            for (Integer dogID : userAppointments.keySet()) {
                Dog dog = Database.getDogById(dogID); // Fetch dog details
                if (dog != null) {
                    Date appointmentDate = userAppointments.get(dogID);
                    appointmentsDisplay.getChildren().add(Components.appointmentView(dog, appointmentDate, stage));
                } else {
                    System.out.println("Dog with ID " + dogID + " not found.");
                }
            }
        } else {
            appointmentsDisplay.getChildren().add(new Label("No appointments found."));
        }
        
        appointmentsDisplay.setAlignment(javafx.geometry.Pos.CENTER);

        root.getChildren().addAll(
                navTab,
                appointmentsLabel,
                appointmentsDisplay
        );

        StackPane stackPane = new StackPane(root);
        stackPane.setAlignment(javafx.geometry.Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(stackPane);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);

        stage.setScene(scene);
        stage.setTitle("Pawfect Pairs");
        stage.show();
    }
}
