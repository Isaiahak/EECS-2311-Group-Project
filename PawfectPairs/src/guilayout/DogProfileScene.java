package guilayout;

import backend.dog.Dog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DogProfileScene extends PrimaryScene{

	private static DogProfileScene instance;
	public static DogProfileScene getInstance() {
		if (instance == null) {
			instance = new DogProfileScene();		
		}
		return instance;
	}

    private ImageView petImageView;
    private Label sizeLabel;
    private Label energyLabel;
    private Label primaryInfoLabel; 
    private Label biographyText;
    private Hyperlink posterLink;
	private StackPane tagsPane;
	private Stage stage;
//    private int l = 1080; 
//    private int w = 1920; 

//    private Label bioLabel;
//    private Label tagsLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	initailizePrimaryScene();
    	
    	//root is v box
		VBox root = new VBox();
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Pawfect Pairs");
        PosterProfileScene posterProfile = PosterProfileScene.getInstance();
        OutOfDogsScene outOfDogs = OutOfDogsScene.getInstance();


        HBox primaryControlTab = new HBox();
        
        petImageView = Components.imageView(500,500);

        Button passButton = Components.button("╳");
		passButton.setStyle("-fx-background-color: #0a0f40; -fx-text-fill: white; -fx-font-size: 60;");
        passButton.setOnAction(event -> {
        	Dog dog = posterDogs.get(0);
            user.addPassedDogs(dog);
            if(posterDogs.size() <= 0) {
            	outOfDogs.start(primaryStage);	
            	
            }
            else if(posterDogs.size() == 1) {
            	changeProfile();
        		outOfDogs.start(primaryStage);
        	}
        	else {
	            changeProfile();
	            displayCurrentPetProfile();
        	}
        	
        	

        });

        Button likeButton = Components.button("♥");
		likeButton.setStyle("-fx-background-color: #db2a4d; -fx-text-fill: white; -fx-font-size: 60;");
        likeButton.setOnAction(e -> {
        	
        	Dog dog = posterDogs.get(0);
            dog.setAdopted(true);
            user.addLikedDogs(dog);
            if(posterDogs.size() <= 0) {
            	outOfDogs.start(primaryStage);	
            	
            }
            else if(posterDogs.size() == 1) {
        		changeProfile();
        		outOfDogs.start(primaryStage);	
        	}
        	else {	
	            changeProfile();
	            displayCurrentPetProfile();
        	}
         });

        primaryControlTab.getChildren().addAll(likeButton,petImageView,passButton);
        primaryControlTab.setSpacing(20);
        primaryControlTab.setAlignment(Pos.CENTER);
        
        primaryInfoLabel = Components.largeLabel(); // Name, Age, Sex
		
		sizeLabel =  Components.mediumLabel(); // Attributes 
		energyLabel = Components.mediumLabel();
		
	
		
		//		  Initialize layout
        // attributes hBox
        HBox secondaryInfo = new HBox(); 
        secondaryInfo.setAlignment(Pos.CENTER);
        secondaryInfo.setSpacing(10); 
        secondaryInfo.getChildren().addAll(sizeLabel, energyLabel);
        
        // bio textBox
        biographyText = Components.smallLabel(); 
        biographyText.setPrefWidth(900);
        // tags box - TO BE IMPLEMENTED -
        
        
        // nav tab
        HBox navTab = Components.navTab(userProfile, likedDog, DogProfileScene.getInstance(), primaryStage, "dogProfiles", appData);
      
        
        // poster link
        posterLink = Components.hyperlink();
        posterLink.setOnAction(event -> {
        	try {
				posterProfile.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
        
        // add dog tags


		tagsPane = new StackPane();
        stage = primaryStage;
        
         // add to root vbox


		 
		root.getChildren().addAll(navTab, primaryControlTab, primaryInfoLabel, posterLink, secondaryInfo, biographyText, tagsPane);

		// Display the initial pet profile
		displayCurrentPetProfile();
		StackPane stackPane = new StackPane(root);
		stackPane.setAlignment(javafx.geometry.Pos.CENTER);

		ScrollPane scrollPane = new ScrollPane(stackPane);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setFitToWidth(true);

		Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);

		primaryStage.setScene(scene);
	      
	//  primaryStage.setMaximized(true);
		primaryStage.show();
      
//      primaryStage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    
//    	    Database.onApplicationClose(user, posterDogs);
//    	});
//		}
	}

	public void displayCurrentPetProfile() {
		Dog currentProfile = posterDogs.get(0);
		
		
		petImageView.setImage(new Image(currentProfile.getImagePath()));
		primaryInfoLabel.setText(currentProfile.getName() +", " + currentProfile.getAge() + " years, " + currentProfile.getSex());
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

	public void changeProfile() {
		posterDogs.remove(0); // remove top element
	}
}


    




