package guilayout;

import backend.dog.Dog;
import backend.wallet.Wallet;
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
	private OutOfDogsScene outOfDogs;
//    private Button donate = Components.button("Sponsor");
//    DonateScene donatePage = DonateScene.getInstance();//ADDED DONATE PAGE



//    private int l = 1080; 
//    private int w = 1920; 

//    private Label bioLabel;
//    private Label tagsLabel;
    private Wallet wallet;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	initailizePrimaryScene();
    	//wallet=appData.getWallet();
    	//root is v box
		VBox root = new VBox();
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
//    	wallet=user.getWallet();


        primaryStage.setTitle("Pawfect Pairs");
        PosterProfileScene posterProfile = PosterProfileScene.getInstance();
        outOfDogs = OutOfDogsScene.getInstance();



        HBox primaryControlTab = new HBox();
        
        petImageView = Components.imageView(500,500);

        Button passButton = Components.button("╳");
		passButton.setStyle("-fx-background-color: #0a0f40; -fx-text-fill: white; -fx-font-size: 60;");
        passButton.setOnAction(event -> {
            user.addPassedDogs(allDogs.peek());
			if(allDogs.size() == 1) {
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
			allDogs.peek().setAdopted(true);
            user.addLikedDogs(allDogs.peek());
			if(allDogs.size() == 1) {
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
        //BUTTON FOR WALLET / DONATE TO OR SPONSOR AN ANIMAL
        // nav tab
        HBox navTab = Components.navTab(userProfileScene, likedDogsScene, DogProfileScene.getInstance(),sponsoredDogsScene, BookedAppointmentScene.getInstance(),primaryStage, "dogProfiles", appData);
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
		stackPane.setAlignment(Pos.CENTER);
		ScrollPane scrollPane = new ScrollPane(stackPane);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setFitToWidth(true);
		Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);

		primaryStage.setScene(scene);
	      
	//  primaryStage.setMaximized(true);
		if(allDogs.size() == 0){
			outOfDogs.start(stage);
		}
		else{
			primaryStage.show();
		}
      
//      primaryStage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    
//    	    Database.onApplicationClose(user, posterDogs, appData.getAppointmentManager());
//    	});
		}
	

	public void displayCurrentPetProfile() {

		if (allDogs.size() == 0) {
			outOfDogs.start(stage);
		} else {
			Dog currentProfile = allDogs.peek();
//		donate.setOnAction(event -> {
//		  //	wallet=user.getWallet();
//			Poster poster =posterList.get(posterDogs.get(currentProfileIndex).getPosterId());
//			wallet.setPosterToSponsorPending(poster.getUniqueId());
//			donatePage.start(stage);
//
//		});
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


    




