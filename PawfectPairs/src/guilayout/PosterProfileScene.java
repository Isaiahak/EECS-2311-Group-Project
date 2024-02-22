package guilayout;

import java.util.ArrayList;
import java.util.List;

import backend.dog.Dog;
import backend.poster.Poster;
//import guicontrol.PosterProfileController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;  

public class PosterProfileScene extends Application {
	String name="doglover123";
	int rating=5;
	int id = 0;
	Poster poster1= new Poster(rating, name, id);
	int width =550;
	int height =500;
	//        Dog dog = new Dog("Buddy", 1, 3, new EnergyLevel(1), new Size(2), "M", null);
	
	int weight =1;
	int weight2=2;
	int weight3=0;

	private static PosterProfileScene instance; 
	    

	Dog dog1 = new Dog("Charles", 1, 2, 1, 2, 1, new Poster(5, "doglover123", 12), false, "file:src/guilayout/dog.png", "Charles is involed in some shady stuff. Don't ask questions you don't want to know the answers to.");
	Dog dog2 = new Dog("Sandy", 2, 3, 1, 2, 0, new Poster(5, "doglover123", 12), false,"file:src/guilayout/dog1.png", "Sandy looks like she knows something you don't... but that's what makes her so loveable!");
	Dog dog3 = new Dog("Chuckles", 3, 3, 0, 0, 1, new Poster(5, "doglover123", 12), false,"file:src/guilayout/dog4.png", "Lazy and old Chuckles is the perfect lap dog for anyone who doesn't like to move.");


	 @Override  
	    public void start(Stage primaryStage) throws Exception {  
	    	DogProfileScene dogProfileScene = DogProfileScene.getInstance();
	    	LoginScene login = LoginScene.getInstance();

	    	
//	    	PosterProfileController instance;

	        // TODO Auto-generated method stub  
		 BorderPane base=new BorderPane();  
		   // base.setPadding(new Insets(10, 20, 10, 20));
		    base.setPadding(new Insets(20, 20, 20, 20));

		 //base.setAlignment(Pos.CENTER);
		// GridPane base = new GridPane();
	        //Label l = new Label("");

	      Label text1 = new Label("Rating: "+ poster1.getScore()); 
	      Label text2 = new Label("Name: "+ poster1.getDisplayName()); 
	      HBox PosterInfo = new HBox(10);
	      PosterInfo.setAlignment(Pos.CENTER);
	      PosterInfo.getChildren().add(text2);
	      PosterInfo.getChildren().add(text1);

	      base.setTop(PosterInfo);
	      
	      ImageView petImage1 = new ImageView();
	      petImage1.setFitHeight(150);
	      petImage1.setFitWidth(150);
	      ImageView petImage2 = new ImageView();
	      petImage2.setFitHeight(150);
	      petImage2.setFitWidth(150);
	      ImageView petImage3 = new ImageView();
	      petImage3.setFitHeight(150);
	      petImage3.setFitWidth(150);
	      ImageView petImage4 = new ImageView();
	      petImage4.setFitHeight(150);
	      petImage4.setFitWidth(150);
	      
	      Button dog1button= new Button (dog1.toString());
	      HBox hbBtn = new HBox(10);
	      hbBtn.setAlignment(Pos.CENTER);
	      petImage1.setImage(new Image(dog1.getImagePath()));
	      hbBtn.getChildren().add(petImage1);

	      
	      Button dog2button= new Button (dog2.toString());
	      HBox hbBtn2 = new HBox(10);
	      hbBtn2.setAlignment(Pos.CENTER);
	      petImage2.setImage(new Image(dog2.getImagePath()));
	      hbBtn.getChildren().add(petImage2);

	      
	      Button dog3button= new Button (dog3.toString());
	      HBox hbBtn3 = new HBox(10);
	      hbBtn3.setAlignment(Pos.CENTER);
	      petImage3.setImage(new Image(dog3.getImagePath()));
	      hbBtn.getChildren().add(petImage3);
	      

	      Button returnButton= new Button ("return");
	      HBox returnbox = new HBox(width);
	      returnbox.setAlignment(Pos.CENTER);
	      returnbox.getChildren().add(returnButton);

			base.setRight(hbBtn2);
//			base.setCenter(hbBtn4);
			base.setLeft(hbBtn);
			base.setRight(hbBtn3);
			base.setBottom(returnButton);
			PosterInfo.setPadding(new Insets(10, 10, 10, 10));

	      Scene scene = new Scene(base, width, height);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	        
	        dog1button.setOnAction(e -> {
	        	//DogProfileScene.main();
               // dogProfileScene.start(primaryStage);

	        });
	        
	        dog2button.setOnAction(e -> {
	        	//DogProfileScene.main();
                //dogProfileScene.start(primaryStage);

	        });
	        
	        dog3button.setOnAction(e -> {
	        	//DogProfileScene.main();
                //dogProfileScene.start(primaryStage);

	        });
	        

	        returnButton.setOnAction(e -> {
	        	//DogProfileScene.main();
                dogProfileScene.start(primaryStage);//right now returns to login page

	        });
	        
	    }  
	 
	 public static PosterProfileScene getInstance() {
			if (instance == null) {
				instance = new PosterProfileScene();		
			}
			return instance;
		}
	 public static void main(String[] args) {
	        launch(); // launch THIS class
	    }
}

