package guilayout;
import backend.dog.Dog;
import backend.poster.Poster;
import guicontrol.DogProfileController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DogProfileScene extends Application {

    private DogProfileController profileController;

    private ImageView petImageView;
    private Label nameLabel;
    private Label ageLabel;
    private Label sizeLabel;
    private Label energyLabel;
//    private Label bioLabel;
//    private Label tagsLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pet Profile Viewer");

        // Initialize UI components
        petImageView = new ImageView();
        petImageView.setFitHeight(200);
        petImageView.setFitWidth(200);
        petImageView.setPreserveRatio(true);

        nameLabel = new Label();
        ageLabel = new Label();
        sizeLabel = new Label();
        energyLabel = new Label();
//        bioLabel = new Label();
//        tagsLabel = new Label();

        // Initialize layout
        BorderPane root = new BorderPane();

        HBox bottomBox = new HBox();
        profileController = new DogProfileController(nameLabel, ageLabel, sizeLabel, energyLabel);
        bottomBox.getChildren().addAll(createArrowButton("Previous", -1), createArrowButton("Next", 1));
        bottomBox.setSpacing(10);
        bottomBox.setStyle("-fx-alignment: center;");

        VBox infoBox = new VBox();
        infoBox.getChildren().addAll(nameLabel, ageLabel, sizeLabel, energyLabel);
        infoBox.setSpacing(10);

        root.setCenter(infoBox);
        root.setBottom(bottomBox);
        root.setLeft(petImageView);

        // Display the initial pet profile
        profileController.displayCurrentPetProfile();

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    private Button createArrowButton(String text, int direction) {
        Button button = new Button(text);
        button.setOnAction(event -> {
            profileController.changeProfile(direction);
            profileController.displayCurrentPetProfile();
        });
        return button;
    }}
