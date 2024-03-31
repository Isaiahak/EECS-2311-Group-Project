package guilayout;
import guicontrol.AppData;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OutOfDogsScene extends PrimaryScene{
	AppData appData;
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
		Components.updateCurrentScene("dogProfile");
		initailizePrimaryScene(stage);
		appData = AppData.getInstance();
		Label pageLabel = Components.largeLabel("Out of Dogs!",Pos.TOP_CENTER);

		mainContainer.getChildren().add(pageLabel);

		stage.show();
		
	}
}
