package guilayout;

import backend.user.User;
import javafx.application.Application; 
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserProfile extends Application{
	
	private static UserProfile instance;
	
	public static UserProfile getInstance() {
		if (instance == null) {
			instance = new UserProfile();		
		}
		return instance;
	}

  public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	LoginScene loginScene  = LoginScene.getInstance();
    	DogProfileScene dogProfileScene = DogProfileScene.getInstance();    	
    	User user = loginScene.sendUserInfo();	
        primaryStage.setTitle("User Settings");

        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create scroll bars for preferences
        ScrollBar scrollBar1 = createScrollBar("Preference 1:");
        ScrollBar scrollBar2 = createScrollBar("Preference 2:");
        ScrollBar scrollBar3 = createScrollBar("Preference 3:");

        // Add scroll bars and labels to the grid
        grid.add(scrollBar1, 0, 0);
        grid.add(scrollBar2, 0, 1);
        grid.add(scrollBar3, 0, 2);

        // Display labels to show the selected preferences
        Label label1 = new Label("Preference 1 Value: ");
        Label label2 = new Label("Preference 2 Value: ");
        Label label3 = new Label("Preference 3 Value: ");

        grid.add(label1, 1, 0);
        grid.add(label2, 1, 1);
        grid.add(label3, 1, 2);

        // Event handling for scroll bars
        addScrollBarListener(scrollBar1, label1);
        addScrollBarListener(scrollBar2, label2);
        addScrollBarListener(scrollBar3, label3);
        
        Button backButton = new Button("Back");
        grid.add(backButton, 10, 0);
        backButton.setOnAction(e -> {
            dogProfileScene.start(primaryStage);
        });
        
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ScrollBar createScrollBar(String label) {
        Label preferenceLabel = new Label(label);
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setMin(0);
        scrollBar.setMax(100);
        scrollBar.setValue(50);
        scrollBar.setUnitIncrement(1);

        return scrollBar;
    }

    private void addScrollBarListener(ScrollBar scrollBar, Label label) {
        scrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText("Value: " + String.format("%.2f", newValue));
        });
    }
}
