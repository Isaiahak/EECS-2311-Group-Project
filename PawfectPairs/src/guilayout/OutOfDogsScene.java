package guilayout;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OutOfDogsScene extends Application{

	
	private static OutOfDogsScene instance;
	
	public static OutOfDogsScene getInstance() {
		if (instance == null)
			instance = new OutOfDogsScene();
		return instance;
	}
	private OutOfDogsScene() {
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage stage) {
		VBox root = new VBox();
		root.setSpacing(15);
		root.setAlignment(Pos.TOP_CENTER);
		LikedDogScene likedDog = LikedDogScene.getInstance();
		UserProfile userProfile = UserProfile.getInstance();
		Label pageLabel = Components.largeLabel("Out of Dogs!",Pos.TOP_CENTER);
		stage.setTitle("Pawfect Pairs");	
		HBox navTab = Components.navTab(userProfile, likedDog, DogProfileScene.getInstance(), stage);
		root.getChildren().addAll(navTab,pageLabel);
		StackPane stackPane = new StackPane(root);
		stackPane.setAlignment(javafx.geometry.Pos.CENTER);
		Scene scene = new Scene(stackPane, Components.screenWidth, Components.screenHeight);		 
		stage.setScene(scene);
		stage.show();
		
	}

}
