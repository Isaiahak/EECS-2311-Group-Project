package guilayout;

import java.util.PriorityQueue;

import backend.dog.Dog;
import backend.poster.Poster;
import backend.wallet.RecurringPayment;
import backend.wallet.Wallet.FundsTooLow;
import guicontrol.AppData;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DonateScene extends PrimaryScene {
	private static DonateScene instance;
	private Dog currentDog;
	private Label currentFunds;
	ComboBox<String>  howOftenBox;
	TextField howMuchMoney;
	public static DonateScene getInstance() {
		if (instance == null)
			instance = new DonateScene();
		return instance;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Components.updateCurrentScene("none");
		initailizePrimaryScene(stage);
		

		wallet=user.getWallet();
		user = appData.getUser();		

		Label dogLabel = Components.largeLabel(currentDog.getName() + " appreciates you ♥", Pos.CENTER); 
		ImageView image = Components.imageView(500,500); 
		image.setImage(new Image(currentDog.getImagePath()));

		Label howMuch = Components.mediumLabel("How much would you like to donate?", Pos.CENTER);
		howMuchMoney = new TextField();

		Label howOften = Components.mediumLabel("How often would you like to donate?", Pos.CENTER);
		howOftenBox = new ComboBox<>(FXCollections.observableArrayList("Once", "Weekly", "Biweekly", "Monthly"));

		howOftenBox.setValue("Once");
		currentFunds = Components.largeLabel("Current balance: " + String.format("%.2f",  wallet.getBalance()), Pos.CENTER);
		Button donateButton = new Button("Donate");

		donateButton.setOnAction(event -> {
			try {

				Components.makePayment(appData, howOftenBox.getValue(), howMuchMoney,howOftenBox, currentDog);
				currentFunds.setText("Your current balance "+String.format("%.2f",  appData.getUser().getWallet().getBalance()));

			} catch (FundsTooLow e) {
				e.printStackTrace();
			}
		});

		mainContainer.getChildren().addAll(
				dogLabel,
				image,
				currentFunds,
				howMuch,
				howMuchMoney,
				howOften,
				howOftenBox,
				donateButton
				);

		stage.show();

	}


	public static boolean checkInput (String inputText) {
		boolean Numberwithdecimal = false;
		String whyFalse="";
		int numofDecimalPoint=0;
		for (char c : inputText.toCharArray()) {
			if (Character.isDigit(c)) {
				Numberwithdecimal= true;
				whyFalse+="not a digit";

			}
			else if(c == '.') {
				Numberwithdecimal=true;
				numofDecimalPoint++;
			}

		}
		if (numofDecimalPoint>1) {
			Numberwithdecimal=false;
			whyFalse+="more than one .";

		}
		return Numberwithdecimal;
	}
	

	private void showAlert(String title, String message, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);


		alert.showAndWait();
	}

	public void setCurrentDog(Dog d) {
		this.currentDog = d;
	}
}