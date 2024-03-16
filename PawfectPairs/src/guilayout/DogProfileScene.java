package guilayout;

import backend.database.Database;
import backend.dog.Dog;
import backend.wallet.Wallet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DogProfileScene extends PrimaryScene{

	private static DogProfileScene instance;
	private ImageView petImageView;
	private Label sizeLabel;
	private Label energyLabel;
	private Label primaryInfoLabel;
	private Label biographyText;
	private Hyperlink posterLink;
	private StackPane tagsPane;
	private Stage stage;
	private OutOfDogsScene outOfDogs;
	private Wallet wallet;

	public static DogProfileScene getInstance() {
		if (instance == null) {
			instance = new DogProfileScene();		
		}
		return instance;
	}
	private DogProfileScene(){

	}

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	Components.updateCurrentScene("dogProfiles");
//    	mainContainer = new VBox();
		initailizePrimaryScene(primaryStage);
		
		mainContainer.setSpacing(20);
		mainContainer.setAlignment(Pos.CENTER);
		mainContainer.setPadding(new Insets(0, 5, 5, 5));
		primaryStage.setTitle("Pawfect Pairs");
		PosterProfileScene posterProfile = PosterProfileScene.getInstance();
		outOfDogs = OutOfDogsScene.getInstance();
		HBox primaryControlTab = new HBox();
		petImageView = Components.imageView(500, 500);

		tagsPane = new StackPane();
		stage = primaryStage;
		

		Button passButton = Components.button("❌");
		passButton.getStyleClass().add("pass-button");
		passButton.setOnAction(event -> {
			user.addPassedDogs(allDogs.peek());
			if (allDogs.size() == 1) {
				changeProfile();
				outOfDogs.start(primaryStage);
			} else {
				changeProfile();
				displayCurrentPetProfile();
			}
		});

		Button likeButton = Components.button("♥");
		likeButton.getStyleClass().add("like-button");
		likeButton.setOnAction(e -> {
			allDogs.peek().setAdopted(true);
			user.addLikedDogs(allDogs.peek());
			if (allDogs.size() == 1) {
				changeProfile();
				outOfDogs.start(primaryStage);
			} else {
				changeProfile();
				displayCurrentPetProfile();
			}
		});

		primaryControlTab.getChildren().addAll(likeButton, petImageView, passButton);
		primaryControlTab.setSpacing(20);
		primaryControlTab.setAlignment(Pos.CENTER);
		
		primaryInfoLabel = Components.largeLabel(); // Name, Age, Sex
		sizeLabel = Components.mediumLabel();
		energyLabel = Components.mediumLabel();
		
		HBox secondaryInfo = new HBox();
		secondaryInfo.setAlignment(Pos.CENTER);
		secondaryInfo.setSpacing(10);
		secondaryInfo.getChildren().addAll(sizeLabel, energyLabel);


		biographyText = Components.smallLabel();
		biographyText.setPrefWidth(900);

		posterLink = Components.hyperlink();
		posterLink.setOnAction(event -> {
			try {
				posterProfile.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		mainContainer.getChildren().addAll(primaryControlTab, primaryInfoLabel, posterLink, secondaryInfo, biographyText, tagsPane);
		displayCurrentPetProfile();
//		this.root.getChildren().add(mainContainer); // add root node to main stack pane (with styles)
    	       
		if (allDogs.size() == 0) {
			outOfDogs.start(stage);
		} else {
			primaryStage.show();
		}

		primaryStage.setOnCloseRequest(event -> {
			System.out.println("Window is closing. Perform cleanup if needed.");

			Database.onApplicationClose(user, allDogs, appData.getAppointmentManager());
		});
	}

	public void displayCurrentPetProfile() {

		if (allDogs.size() == 0) {
			outOfDogs.start(stage);
		} else {
			Dog currentProfile = allDogs.peek();

			petImageView.setImage(new Image(currentProfile.getImagePath()));
			primaryInfoLabel.setText(currentProfile.getName() + ", " + currentProfile.getAge() + " years, " + currentProfile.getSex());
			sizeLabel.setText("Size: " + currentProfile.getSize());
			energyLabel.setText("Energy Level: " + currentProfile.getEnergyLevel());
			biographyText.setText(currentProfile.getBiography());

			posterLink.setText(posterList.get(currentProfile.getPosterId()).getDisplayName());
			PosterProfileScene posterProfile = PosterProfileScene.getInstance();
			posterProfile.setCurrentPoster(posterList.get(currentProfile.getPosterId()));

			posterLink.setOnAction(event -> {
				try {
					posterProfile.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});


			tagsPane.getChildren().clear();

			tagsPane.getChildren().add(Components.createTags(currentProfile.getTags()));
		}
	}

	public void changeProfile() {
		 allDogs.remove();
	}

	public Dog getCurrentProfile() {
		return this.allDogs.peek();
	}
}


    




