package guilayout;

import backend.database.Database;
import backend.dog.Dog;
import backend.user.User;
import backend.wallet.Wallet;
import guicontrol.AppData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


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
	private User user;

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
    	//For undo Button
    	user = AppData.getInstance().getUser();
    	user.setUndoDog(null);
    	user.setLastRemovedDog(null);
    	
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
		passButton.setOnAction(event -> {
			
			if (user.getUndoDog() == null && (allDogs.peek()!= user.getUndoDog())) {
				Dog dog = allDogs.peek();
			
				user.addPassedDogs(dog);
				user.setLastRemovedDog(dog);
				if (allDogs.size() == 0) {
					changeProfile();
					outOfDogs.start(primaryStage);
				} else {
					changeProfile();
					displayCurrentPetProfile();
				}
			}
			else {
				Dog dog = user.getUndoDog();
	        	if (user.getUndoDog() != null) {
			    	}
				user.addPassedDogs(dog);
				if (allDogs.size() == 0) {
					changeProfile();
					outOfDogs.start(primaryStage);
				} else {
					displayCurrentPetProfile();
					user.setLastRemovedDog(null);
					user.setUndoDog(null);
					displayCurrentPetProfile();
				}
			}
			user.setUndoDog(null);
		});
		
		
		Button likeButton = Components.button("♥");
        likeButton.getStyleClass().add("like-button");
        likeButton.setOnAction(e -> {
        	if (user.getUndoDog() == null && (allDogs.peek()!= user.getUndoDog()) ) {
        		Dog dog = allDogs.peek();
        		user.addLikedDogs(dog);
        		user.setLastRemovedDog(dog);
        		if (allDogs.size() == 0) {
        			changeProfile();
        			outOfDogs.start(primaryStage);
        		} else {
        			changeProfile();
        			displayCurrentPetProfile();
    			}
        	}
        	else {
				Dog dog = user.getUndoDog();
	        	if (user.getUndoDog() != null) {
			    	}
				user.addLikedDogs(dog);
				if (allDogs.size() == 0) {
					changeProfile();
					outOfDogs.start(primaryStage);
				} else {
					
					displayCurrentPetProfile();
					user.setLastRemovedDog(null);
					user.setUndoDog(null);
					displayCurrentPetProfile();
				}
			}
        	user.setUndoDog(null);
        });
		
        
		Button undoButton = Components.button("↩");
		undoButton.getStyleClass().add("undo-button");
		undoButton.setOnAction(e -> {
		    if (user.getLastRemovedDog() != null) {
		    	
		        // Determine whether the last action was a like or pass
		    	Dog lastRemovedDog = user.getLastRemovedDog();
		        if (user.getLikedDogs().contains(lastRemovedDog)) {
		            user.getLikedDogs().remove(lastRemovedDog); // Remove the dog from liked dogs list
		        } 
		        if (user.getPassedDogs().contains(lastRemovedDog)) {
		            user.getPassedDogs().remove(lastRemovedDog); // Remove the dog from passed dogs list
		        }

		        // Add the last removed dog back to the priority queue
		        user.setUndoDog(lastRemovedDog);
		        lastRemovedDog = null;
		        
		        displayCurrentPetProfile();
		        
		    }else {
		    	showAlert("Note", "You can only go back to a dog once!", AlertType.ERROR);
		    }
		});

		Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setPrefWidth(20);
		
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        spacer2.setPrefWidth(40);
		BorderPane buttonContainer = new BorderPane();
		
		buttonContainer.setTop(undoButton);
		primaryControlTab.getChildren().addAll(spacer2,likeButton, petImageView, passButton, spacer, buttonContainer);
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

		Dog currentProfile;
		if (allDogs.size() == 0) {
			outOfDogs.start(stage);
		} else {
			
			if (user.getUndoDog() == null) {
				currentProfile = allDogs.peek();
			}
			else {
				currentProfile = user.getUndoDog();
				
			}

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
	
	public static void showAlert(String title, String message, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);


		alert.showAndWait();
	}

	public void changeProfile() {
		 allDogs.remove();
	}

	public Dog getCurrentProfile() {
		return this.allDogs.peek();
	}
	

}


