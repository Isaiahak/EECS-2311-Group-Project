package guilayout;
import java.util.ArrayList;

import backend.database.Database;
import backend.dog.Dog;

import backend.poster.Poster;
import backend.user.User;
import guilayout.Components;
import guicontrol.AppData;
import guicontrol.DogProfileController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class DogProfileScene extends Application {
	private static DogProfileScene instance;

	
	public static DogProfileScene getInstance() {
		if (instance == null) {
			instance = new DogProfileScene();		
		}
		return instance;
	}
	

    private DogProfileController profileController;

    private ImageView petImageView;
    private Label sizeLabel;
    private Label energyLabel;
    private Label primaryInfoLabel; 
    private TextFlow biographyText;
    private Hyperlink posterLink;
    
    User user;
    ArrayList<Dog> posterDogs;
    
//    private int l = 1080; 
//    private int w = 1920; 

//    private Label bioLabel;
//    private Label tagsLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	posterDogs = AppData.getInstance().getDogProfiles();//TEMP
    	
    	user = AppData.getInstance().getUser();
    	
    	//root is v box
		VBox root = new VBox();
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		

        primaryStage.setTitle("Pawfect Pairs");
        
        
        
        UserProfile userProfile = UserProfile.getInstance();
        PosterProfileScene posterProfile = PosterProfileScene.getInstance();
        LikedDogScene likedDog = LikedDogScene.getInstance();
        OutOfDogsScene outOfDogs = OutOfDogsScene.getInstance();
       
        
        petImageView = Components.imageView(500,500);
     
        
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
        Label biographyText = Components.smallLabel(); 
        biographyText.setPrefWidth(900);
        // tags box - TO BE IMPLEMENTED -
        
        
        // nav tab
        HBox navTab = Components.navTab(userProfile, likedDog, DogProfileScene.getInstance(), primaryStage);
      
       // bottom likes tab
        HBox bottomTab = new HBox(); 
     
        Button leftArrowButton = Components.button("←");
        leftArrowButton.setOnAction(event -> {
        	if(profileController.getDogListSize() == 0) {
        		outOfDogs.start(primaryStage);
	            profileController.changeProfile(-1);
	            profileController.displayCurrentPetProfile();
        	}
        	else {
        		profileController.changeProfile(-1);
 	            profileController.displayCurrentPetProfile();
        	}
        });
        leftArrowButton.setStyle("-fx-background-color: #0a0f40; -fx-text-fill: white;");
        
        Button rightArrowButton = Components.button("→");
        rightArrowButton.setOnAction(event -> {
        	if(profileController.getDogListSize() == 0) {
        		outOfDogs.start(primaryStage);
        	}
        	else {
        		profileController.changeProfile(1);
	            profileController.displayCurrentPetProfile();
        	}
        });
        rightArrowButton.setStyle("-fx-background-color: #0a0f40; -fx-text-fill: white;");
        
        Button likeButton = Components.button("♥");
        likeButton.setOnAction(e -> {
        
        	
            Dog dog = profileController.getCurrentDog();
            dog.setAdopted(true);
            Database.setDogAdopted(dog);
      
            user.addLikedDogs(dog);
            Database.addLikedDog(dog.getId(), user.getUserID());
            
            profileController.changeProfile(1);
           
         		   
         });
        likeButton.setStyle("-fx-background-color: #db2a4d; -fx-text-fill: white; -fx-font-size: 60;");
        
        bottomTab.getChildren().addAll(leftArrowButton, likeButton, rightArrowButton); 
        bottomTab.setSpacing(20);
        bottomTab.setAlignment(Pos.CENTER);
        
       
        
        // poster link
        posterLink = Components.hyperlink();
        posterLink.setOnAction(event -> {
        	try {
				posterProfile.start(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        
        // add dog tags
        
        
       	StackPane tagsPane = new StackPane();
        
        
         // add to root vbox
        root.getChildren().addAll(navTab, petImageView, primaryInfoLabel, posterLink, secondaryInfo, biographyText, tagsPane, bottomTab);
        
        
        ArrayList<Dog> dogProfiles = Database.getAllDogs();
        
		profileController = new DogProfileController(primaryInfoLabel, sizeLabel, energyLabel, petImageView, biographyText, posterLink, dogProfiles, tagsPane, primaryStage);
		

      // Display the initial pet profile
      profileController.displayCurrentPetProfile();
      StackPane stackPane = new StackPane(root);
      stackPane.setAlignment(javafx.geometry.Pos.CENTER);
      
      ScrollPane scrollPane = new ScrollPane(stackPane);
      scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
      scrollPane.setFitToWidth(true);
      
      Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);

      primaryStage.setScene(scene);
      
//      primaryStage.setMaximized(true);
      primaryStage.show();
      
//      primaryStage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    
//    	    Database.onApplicationClose(user, posterDogs);
//    	});
		
		
    }
    }




