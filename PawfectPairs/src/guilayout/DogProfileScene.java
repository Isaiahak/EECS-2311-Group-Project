	package guilayout;

	import backend.database.Database;
	import backend.dog.Dog;
	import backend.wallet.Wallet;
	import javafx.animation.KeyFrame;
	import javafx.animation.Timeline;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.image.*;
	import javafx.scene.layout.*;
	import javafx.scene.shape.Rectangle;
	import javafx.stage.Stage;
	import javafx.util.Duration;

public class DogProfileScene extends PrimaryScene{

	private static DogProfileScene instance;
	private ImageView petImageView;
	private Label sizeLabel;
	private HBox sizeIcon;
	private HBox energyIcon;
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
		initailizePrimaryScene(primaryStage);
		
		PosterProfileScene posterProfile = PosterProfileScene.getInstance();
		outOfDogs = OutOfDogsScene.getInstance();
		HBox primaryControlTab = new HBox();
		petImageView = Components.imageView(500, 500);
	

		tagsPane = new StackPane();
		stage = primaryStage;
		

		Button passButton = Components.button("❌");
		passButton.getStyleClass().add("pass-button");
		Timeline passTimeline = new Timeline(new KeyFrame(Duration.seconds(3), passEvent -> {
			passButton.setText("❌");
			// Re-enable the button
			passButton.setDisable(false);

			user.addPassedDogs(allDogs.peek());
			if (allDogs.size() == 1) {
				changeProfile();
				outOfDogs.start(primaryStage);
			} else {
				changeProfile();
				displayCurrentPetProfile();
			}
		}));
		passButton.setOnAction(event -> {
			if (passButton.getText().equals("❌")) {
				// Disable the button to prevent multiple clicks
				//passButton.setDisable(true);

				// Change the button's text to undo sign
				passButton.setText("↩");


				passTimeline.playFromStart();
			} else {
				passButton.setText("❌");
				passTimeline.stop();
			}

		});

		Button likeButton = Components.button("♥");
		likeButton.getStyleClass().add("like-button");
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
			likeButton.setText("♥");
			// Add your existing logic here
			user.addLikedDogs(allDogs.peek());
			if (allDogs.size() == 1) {
				changeProfile();
				outOfDogs.start(primaryStage);
			} else {
				changeProfile();
				displayCurrentPetProfile();
			}
		}));
		likeButton.setOnAction(e -> {
			if (likeButton.getText().equals("♥")) {

				// Change the button's text to undo sign
				likeButton.setText("↩");

				// Schedule the button's appearance change back to ♥ after 5 seconds
				timeline.playFromStart();
			} else {
				// If the button is clicked while showing the undo sign, revert to ♥ immediately
				likeButton.setText("♥");
				timeline.stop();
			}
		});

		primaryControlTab.getChildren().addAll(likeButton, petImageView, passButton);
		primaryControlTab.getStyleClass().add("dog-picture-container");
		primaryControlTab.setPadding(new Insets(10));
		primaryControlTab.setSpacing(20);
		primaryControlTab.setPrefWidth(Components.screenWidth * 0.35);
		primaryControlTab.setAlignment(Pos.CENTER);

		
		primaryInfoLabel = Components.largeLabel(); // Name, Age, Sex
		sizeIcon =  new HBox();
		energyIcon = new HBox();
		sizeLabel = Components.mediumLabel();
		energyLabel = Components.mediumLabel();
		
		HBox secondaryInfo = new HBox();
		secondaryInfo.setAlignment(Pos.CENTER);
		secondaryInfo.setSpacing(60);
		secondaryInfo.getChildren().addAll(sizeLabel, sizeIcon, energyLabel, energyIcon);


		biographyText = Components.smallLabel();
		biographyText.setPrefWidth(Components.screenWidth * 0.6);
		biographyText.setWrapText(true);

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
    	       
		mainContainer.setSpacing(5);
		
		if (allDogs.size() == 0) {
			outOfDogs.start(stage);
		} else {
			primaryStage.show();
		}

		primaryStage.setOnCloseRequest(event -> {
			System.out.println("Window is closing. Perform cleanup if needed.");
			
			

			Database.onApplicationClose(user, allDogs, appData.getAppointmentManager(), appData.getOkToClose());
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

			Components.dogAttributeDisplay(sizeIcon, "🐕", currentProfile.getSize().getWeight());
			
			energyLabel.setText("Energy Level: " + currentProfile.getEnergyLevel());
			Components.dogAttributeDisplay(energyIcon, "⚡", currentProfile.getEnergyLevel().getWeight());
			
			biographyText.setText(currentProfile.getBiography());

			posterLink.setText(posterList.get(currentProfile.getPosterId()).getDisplayName());
			PosterProfileScene posterProfile = PosterProfileScene.getInstance();
			
			

			posterLink.setOnAction(event -> {
				try {
					posterProfile.setCurrentPoster(posterList.get(currentProfile.getPosterId()));
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


    



