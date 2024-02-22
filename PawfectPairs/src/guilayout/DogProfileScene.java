package guilayout;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.user.User;
import guilayout.Components;
import guicontrol.DogProfileController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
    
//    private int l = 1080; 
//    private int w = 1920; 

//    private Label bioLabel;
//    private Label tagsLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	//root is v box
		VBox root = new VBox();
		root.setSpacing(15);
		root.setAlignment(Pos.CENTER);
		

        primaryStage.setTitle("Pawfect Pairs");
        
        UserProfile userProfile = UserProfile.getInstance();
        LoginScene loginScene = LoginScene.getInstance();
        PosterProfileScene posterProfile = PosterProfileScene.getInstance();
        LikedDogScene likedDog = LikedDogScene.getInstance();
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
        biographyText.setPrefWidth(600);
        // tags box - TO BE IMPLEMENTED -
        
        
        // nav tab
        HBox navTab = Components.navTab(userProfile,likedDog,instance,primaryStage);
      
       // bottom likes tab
        HBox bottomTab = new HBox(); 
     
        Button leftArrowButton = Components.button("←");
        leftArrowButton.setOnAction(event -> {
            profileController.changeProfile(-1);
            profileController.displayCurrentPetProfile();
        });
        leftArrowButton.setStyle("-fx-background-color: #0a0f40; -fx-text-fill: white;");
        
        Button rightArrowButton = Components.button("→");
        rightArrowButton.setOnAction(event -> {
            profileController.changeProfile(1);
            profileController.displayCurrentPetProfile();
        });
        rightArrowButton.setStyle("-fx-background-color: #0a0f40; -fx-text-fill: white;");
        
        Button likeButton = Components.button("♥");
        likeButton.setOnAction(e -> {
            Dog dog = profileController.getCurrentDog();
            dog.setAdopted(true);
            User user = loginScene.sendUserInfo();
            user.addLikedDogs(dog);
         		   
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
        
         // add to root vbox
        root.getChildren().addAll(navTab, petImageView, primaryInfoLabel, posterLink, secondaryInfo, biographyText, bottomTab);
        
		profileController = new DogProfileController(primaryInfoLabel, sizeLabel, energyLabel, petImageView, biographyText, posterLink);
		

      // Display the initial pet profile
      profileController.displayCurrentPetProfile();
      StackPane stackPane = new StackPane(root);
      stackPane.setAlignment(javafx.geometry.Pos.CENTER);
      
      Scene scene = new Scene(stackPane, Components.screenWidth, Components.screenHeight);

      primaryStage.setScene(scene);
      
      primaryStage.setMaximized(true);
      primaryStage.show();
		
		
    }
    }
