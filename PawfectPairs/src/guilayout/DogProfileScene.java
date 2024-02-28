package guilayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.PriorityQueue;

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
    
    AppData appData;
    
//    private int l = 1080; 
//    private int w = 1920; 

//    private Label bioLabel;
//    private Label tagsLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    	appData = AppData.getInstance();     	
    	PriorityQueue<Dog> posterDogs = appData.getSortedDogProfiles();
    	User user = appData.getUser();
    	
    	//root is v box
		VBox root = new VBox();
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		

        primaryStage.setTitle("Pawfect Pairs");
        
        
        
        UserProfile userProfile = UserProfile.getInstance();
        PosterProfileScene posterProfile = PosterProfileScene.getInstance();
        LikedDogScene likedDog = LikedDogScene.getInstance();
        OutOfDogsScene outOfDogs = OutOfDogsScene.getInstance();
       
        
        HBox primaryControlTab = new HBox();
        
        petImageView = Components.imageView(500,500);
        
        
        Button passButton = Components.button("╳");
        passButton.setOnAction(event -> {
        	if(profileController.getDogListSize() == 0) {
        		outOfDogs.start(primaryStage);
        	}
        	else {
        		profileController.changeProfile();
	            profileController.displayCurrentPetProfile();
	            
        	}
        });
        passButton.setStyle("-fx-background-color: #0a0f40; -fx-text-fill: white; -fx-font-size: 60;");
        
        Button likeButton = Components.button("♥");
        likeButton.setOnAction(e -> {
        
        	
            Dog dog = posterDogs.peek();
            dog.setAdopted(true);
            user.addLikedDogs(dog);
            profileController.changeProfile();
            profileController.displayCurrentPetProfile();
           
         		   
         });
        likeButton.setStyle("-fx-background-color: #db2a4d; -fx-text-fill: white; -fx-font-size: 60;");
     
        
        primaryControlTab.getChildren().addAll(likeButton,petImageView,passButton);
        primaryControlTab.setSpacing(20);
        primaryControlTab.setAlignment(Pos.CENTER);
        
        primaryInfoLabel = Components.largeLabel(); // Name, Age, Sex
		
		sizeLabel =  Components.mediumLabel(); // Attributes 
		energyLabel = Components.mediumLabel();
		
		
		if(posterDogs.size() <= 0 ) {
			outOfDogs.start(primaryStage);
		}else {
		
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
//        HBox bottomTab = new HBox(); 
     
        
        
////        bottomTab.getChildren().addAll( likeButton, passButton); 
//        bottomTab.setSpacing(20);
//        bottomTab.setAlignment(Pos.CENTER);
//        
       
        
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
        
        
       	StackPane tagsPane = new StackPane();
        
        
         // add to root vbox
        root.getChildren().addAll(navTab, primaryControlTab, primaryInfoLabel, posterLink, secondaryInfo, biographyText, tagsPane); 
		profileController = new DogProfileController(primaryInfoLabel, sizeLabel, energyLabel, petImageView, biographyText, posterLink, posterDogs, tagsPane, primaryStage, appData.getPosters());
		

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
//		}
		
			}
		}
}
    




