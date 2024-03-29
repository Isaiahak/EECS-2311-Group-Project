	package guilayout;

	import backend.database.Database;
	import backend.dog.Dog;
	import backend.wallet.Wallet;
import guicontrol.AppData;
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
	private Dog lastRemovedDog;

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
    	lastRemovedDog = null;
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
			
			user.addPassedDogs(allDogs.peek());
			lastRemovedDog = allDogs.peek();
			System.out.println("Passed: "+ lastRemovedDog.getName());
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
        	
        	user.addLikedDogs(allDogs.peek());
            lastRemovedDog = allDogs.peek(); // Remove the dog from the priority queue
            System.out.println("Liked: "+ lastRemovedDog.getName());
            if (lastRemovedDog != null) {
                //user.addLikedDogs(lastRemovedDog);
                if (allDogs.size() == 1) {
    				changeProfile();
    				outOfDogs.start(primaryStage);
    			} else {
    				changeProfile();
    				displayCurrentPetProfile();
    			}
            }
        });
		
        
		Button undoButton = Components.button("undo");
		undoButton.getStyleClass().add("undo-button");
		undoButton.setOnAction(e -> {
		    if (getLastRemovedDog() != null) {
		        // Determine whether the last action was a like or pass
		        if (user.getLikedDogs().contains(lastRemovedDog)) {
		            user.getLikedDogs().remove(lastRemovedDog); // Remove the dog from liked dogs list
		        } else if (user.getPassedDogs().contains(lastRemovedDog)) {
		            user.getPassedDogs().remove(lastRemovedDog); // Remove the dog from passed dogs list
		        }

		        // Add the last removed dog back to the priority queue
		        allDogs.add(getLastRemovedDog());
		        System.out.println("Undo: "+ lastRemovedDog.getName());
		        
		        changeProfile();
		        // Redisplay the profile
		       // displayCurrentPetProfile();
//		        displayUndidDogProfile();
		        displayLastRemovedProfile();
		    }
		});

		primaryControlTab.getChildren().addAll(likeButton, petImageView, passButton, undoButton);
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
	
	public void displayLastRemovedProfile() {

		if (allDogs.size() == 0) {
			outOfDogs.start(stage);
		} else {
			Dog currentProfile = lastRemovedDog;

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
	
	// Getter method to retrieve the last removed dog
    public Dog getLastRemovedDog() {
        return lastRemovedDog;
    }
}


/*	package guilayout;

	import java.util.PriorityQueue;

import backend.database.Database;
	import backend.dog.Dog;
	import backend.wallet.Wallet;
import guicontrol.AppData;
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
	private Dog lastRemovedDog;
	private Dog undoDog;

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
    	lastRemovedDog = null;
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
			if (undoDog == null) {
			user.addPassedDogs(allDogs.peek());
			lastRemovedDog = allDogs.peek();
			}
			else {
				user.addPassedDogs(undoDog);
				lastRemovedDog = null;
				undoDog = null;
			}
			if (allDogs.size() == 1) {
				changeProfile();
				outOfDogs.start(primaryStage);
			} else {
				if (undoDog == null) {
    				changeProfile();
    				}
    				else {
    					allDogs.remove(undoDog);
    				}
				displayCurrentPetProfile();
			}
		});
		
		
		Button likeButton = Components.button("♥");
        likeButton.getStyleClass().add("like-button");
        likeButton.setOnAction(e -> {
        	if (undoDog == null) {
        		user.addLikedDogs(allDogs.peek());
                lastRemovedDog = allDogs.peek(); // Remove the dog from the priority queue
    			}
    			else {
    				user.addLikedDogs(undoDog);
    				lastRemovedDog = null;
    				 undoDog = null;
    			}
        	user.addLikedDogs(allDogs.peek());
            lastRemovedDog = allDogs.peek(); // Remove the dog from the priority queue
            if (lastRemovedDog != null) {
                //user.addLikedDogs(lastRemovedDog);
                if (allDogs.size() == 1) {
    				changeProfile();
    				outOfDogs.start(primaryStage);
    			} else {
    				if (undoDog == null) {
    				changeProfile();
    				}
    				else {
    					allDogs.remove(undoDog);
    				}
    				displayCurrentPetProfile();
    			}
            }
        });
		
        
		Button undoButton = Components.button("undo");
		undoButton.getStyleClass().add("undo-button");
		undoButton.setOnAction(e -> {
		    if (getLastRemovedDog() != null) {
		        // Determine whether the last action was a like or pass
		        if (user.getLikedDogs().contains(lastRemovedDog)) {
		            user.getLikedDogs().remove(lastRemovedDog); // Remove the dog from liked dogs list
		        } else if (user.getPassedDogs().contains(lastRemovedDog)) {
		            user.getPassedDogs().remove(lastRemovedDog); // Remove the dog from passed dogs list
		        }

		        // Add the last removed dog back to the priority queue
		        
		        
		        
		        //allDogs.add(getLastRemovedDog());
		        System.out.println("Undo: "+ lastRemovedDog.getName());
		        undoDog = lastRemovedDog;
		        
		        //changeProfile();
		        // Redisplay the profile
		        displayCurrentPetProfile();
		    }
		});

		primaryControlTab.getChildren().addAll(likeButton, petImageView, passButton, undoButton);
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
			if (undoDog == null) {
				currentProfile = allDogs.peek();
			}
			else {
				currentProfile = lastRemovedDog;
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
	

	public void changeProfile() {
		 allDogs.remove();
	}

	public Dog getCurrentProfile() {
		return this.allDogs.peek();
	}
	
	// Getter method to retrieve the last removed dog
    public Dog getLastRemovedDog() {
        return lastRemovedDog;
    }
    

}*/


    



