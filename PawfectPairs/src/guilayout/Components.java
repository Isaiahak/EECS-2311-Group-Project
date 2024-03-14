package guilayout;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.dog.Dog;
import backend.dog.trait.Attribute;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;
import backend.wallet.RecurringPayment;
import backend.wallet.Wallet.FundsTooLow;
import guicontrol.AppData;
import javafx.animation.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Components{
	/*
	 * GUI components contained here to streamline GUI process and create more modular, thematic GUI parts
	 */

	public static int screenHeight =  (int) Screen.getPrimary().getVisualBounds().getHeight();

	public static int screenWidth =  (int) Screen.getPrimary().getVisualBounds().getWidth(); 

	private static int fontTn = 15;
	private static int fontSm = 20;
	private static int fontMd = 30;
	private static int fontLg = 50;
	private static String font = "Verdana";


	//var for editing sponsorship
	private static boolean clicked;

	/*
	 * Collecting lists of all tags and attributes to create dynamically updating GUI components
	 */

	public static ArrayList<Attribute> allAttributes = new ArrayList<Attribute>();

	public static Button button(String text) {
		Button button = new Button(text);
		button.setFont(Font.font(font, fontMd));

		return button;
	}


	public static HBox navTab(UserProfile userScene,LikedDogScene likedDog, DogProfileScene dogProfile, SponsoredDogsScene sponsoredDogs, BookedAppointmentScene appointmentScene,Stage stage,  String currentScene, AppData appData) {
		//create a navigation tab: settings, schedule, messages, etc
		// settings hBox
		HBox navTab = new HBox();
		navTab.setStyle("-fx-background-color: #f5f5f5;");
		navTab.setSpacing(20);

		Button settingsButton = Components.button("⚙ Settings ⚙");
		Button dogProfileButton = Components.button("🐕 Dog Profiles 🐕");
		Button likedDogButton = Components.button("♥ Liked Dogs  🐶");
		Button appointmentsButton = Components.button("📅 Appointments 📅");
		Button sponsoredDogButton = Components.button("💸 Sponsored Dogs  💸");


		String defaultStyle = "-fx-background-color: #4CAF50; -fx-text-fill: white;";
		String highlightedStyle = "-fx-background-color: #2ed934; -fx-text-fill: white;";


		settingsButton.setStyle(defaultStyle);
		likedDogButton.setStyle(defaultStyle);
		dogProfileButton.setStyle(defaultStyle);
		appointmentsButton.setStyle(defaultStyle);
		sponsoredDogButton.setStyle(defaultStyle);


		// set hightlight on current page button  
		switch(currentScene) {
		case "userProfile":
			settingsButton.setStyle(highlightedStyle);
			break;

		case "likedDogs":
			likedDogButton.setStyle(highlightedStyle);
			break;

		case "dogProfiles":
			dogProfileButton.setStyle(highlightedStyle);
			break;
		case "appointments":
			appointmentsButton.setStyle(highlightedStyle);
		case "sponsoredDogs":
			sponsoredDogButton.setStyle(highlightedStyle);
			break;


		default:
			break; // do nothing
		}

		if(currentScene == "userProfile") { // if we are currently on userProfile, we should update dog scores when we change to a different page 

			settingsButton.setOnAction(event -> {
				appData.updateDogScores();
				userScene.start(stage);
			});
			likedDogButton.setOnAction(event -> {
				appData.updateDogScores();
				likedDog.start(stage);
			});
			dogProfileButton.setOnAction(event -> {
				appData.updateDogScores();

				dogProfile.start(stage);
			});
			appointmentsButton.setOnAction(event -> {
				appData.updateDogScores();
				appointmentScene.start(stage);
			});
			sponsoredDogButton.setOnAction(event -> {
				appData.updateDogScores();
				likedDog.start(stage);
			});

		}else {
			settingsButton.setOnAction(event -> {
				userScene.start(stage);
			});
			likedDogButton.setOnAction(event -> {
				likedDog.start(stage);
			});
			dogProfileButton.setOnAction(event -> {
				dogProfile.start(stage);
			});
			appointmentsButton.setOnAction(event -> {
				appointmentScene.start(stage);
			});
			sponsoredDogButton.setOnAction(event -> {
				sponsoredDogs.start(stage);
			});
		}



		navTab.getChildren().addAll(settingsButton, dogProfileButton, likedDogButton, sponsoredDogButton, appointmentsButton);

		navTab.setAlignment(Pos.TOP_CENTER);
		return navTab; 
	}

	public static Hyperlink hyperlink() {
		Hyperlink hyperlink = new Hyperlink();

		hyperlink.setFont(Font.font(font, fontMd));

		return hyperlink;

	}

	public static Label largeLabel(String text, Pos pos) {
		Label label = new Label(text);
		label.setAlignment(pos);
		label.setFont(Font.font(font, fontLg));


		return label;
	}

	public static Label mediumLabel(String text, Pos pos) {
		Label label = new Label(text);
		label.setAlignment(pos);
		label.setFont(Font.font(font, fontMd));


		return label;
	}

	public static Label smallLabel(String text, Pos pos) {
		Label label = new Label(text);
		label.setAlignment(pos);
		label.setFont(Font.font(font, fontSm));
		label.setWrapText(true);


		return label;
	}

	public static Label tinyLabel(String text, Pos pos) {
		Label label = new Label(text);
		label.setAlignment(pos);
		label.setFont(Font.font(font, fontTn));
		label.setWrapText(true);


		return label;
	}

	public static Label largeLabel() {
		Label label = new Label();
		label.setFont(Font.font(font, fontLg));


		return label;
	}

	public static Label mediumLabel() {
		Label label = new Label();
		label.setFont(Font.font(font, fontMd));

		return label;
	}

	public static Label smallLabel() {
		Label label = new Label();
		label.setFont(Font.font(font, fontSm));
		label.setWrapText(true);

		return label;
	}

	public static ImageView imageView(int w, int l) {
		ImageView imageView = new ImageView();

		imageView.setFitHeight(l);
		imageView.setFitWidth(w);

		imageView.setPreserveRatio(true);

		return imageView;

	}

	public static Label tagLabel(String tag,Tag labelTag, User user) {
		// tags in the user profile to change preferences
		Label label = new Label(tag);
		label.setFont(Font.font(font, fontSm));
		label.setWrapText(true);

		label.maxWidth(50);

		String defaultStyle = 
				"-fx-background-color: #e1fcf6;" + // Background color
						"-fx-padding: 10px;" + // Padding
						//		        "-fx-border-radius: 10px;" + // Border radius
						"-fx-border-color: #e1fcf6;" + // Border color
						//		        "-fx-border-width: 4px;"   +
						"-fx-alignment: center;"; // Text alignment;

		String highLightedStyle = 
				"-fx-background-color: #98f5e1;" + // Background color
						"-fx-padding: 10px;" + // Padding
						//		        "-fx-border-radius: 10px;" + // Border radius
						"-fx-border-color: #98f5e1;" + // Border color
						//		        "-fx-border-width: 4px;"   +
						"-fx-alignment: center;"; // Text alignment;


		//function to be able to turn the label highlighted when loading them if in the dog tags list.


		if(user.getTagPreferences().contains(labelTag) == true) {

			label.setStyle(highLightedStyle);
		}
		else {
			label.setStyle(defaultStyle);
		}

		label.setOnMouseClicked(event -> {
			// Toggle background color on click
			if (label.getStyle().equals(defaultStyle)) {
				label.setStyle(highLightedStyle); // highlight if not highlighted
				if(user.getTagPreferences().contains(labelTag) == false) {
					user.getTagPreferences().put(labelTag.getTagId(),labelTag);
				}


			} else {
				if(user.getTagPreferences().contains(labelTag) == true) {
					user.getTagPreferences().remove(labelTag);
				}
				label.setStyle(defaultStyle);         	
			}
		});


		return label;
	}

	public static GridPane createTags(HashMap<Integer, Tag> tags, User user) {
		GridPane gridPane = new GridPane();
		int row = 0;
		int col = 0;

		int maxRows = 5;

		int i = 0; // current index

		for(Tag t : tags.values()) {

			Label label = tagLabel(t.getTagName(), t, user);

			// Add the label to the grid
			gridPane.add(label, row, col);

			// Increment column and row counters
			row++;
			if (row >= maxRows) {
				row = 0; // Reset column counter
				col++;   // Move to the next col
			}
			i++;
		}

		return gridPane;

	}

	public static Label dogTagLabel(String tag) {
		// tags used to label dogs

		Label label = new Label(tag);
		label.setFont(Font.font(font, fontSm));
		label.setWrapText(true);

		label.maxWidth(100);

		String defaultStyle = 
				"-fx-background-color: #03adfc;" + // Background color
						"-fx-padding: 10px;" + // Padding
						"-fx-border-radius: 10px;" + // Border radius
						"-fx-border-color: #03adfc;" + // Border color
						"-fx-border-width: 4px;"   +
						"-fx-text-fill: #ffffff;" + 
						"-fx-alignment: center;"; // Text alignment;


		label.setStyle(defaultStyle); /// this is for showing the tags on the dog's profile :D
		label.setAlignment(Pos.CENTER);

		return label;
	}

	public static GridPane createTags(Hashtable<Integer, Tag> hashtable) {
		// non highlightable tags
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		int row = 0;
		int col = 0;

		int maxRows = 6;

		int i = 0; // current index

		for(Tag t : hashtable.values()) {

			Label label = dogTagLabel(t.getTagName());

			// Add the label to the grid
			gridPane.add(label, row, col);
			GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);

			// Increment column and row counters
			row++;
			if (row >= maxRows) {
				row = 0; // Reset column counter
				col++;   // Move to the next col
			}
			i++;
		}

		gridPane.setAlignment(Pos.CENTER);

		return gridPane;

	}

	public static HBox generateStars(int num) {
		int j = 0;
		HBox stars = new HBox();
		for (int i = 0; i < 10; i ++) {
			//generate a star
			Label star = new Label("★");
			star.setStyle(
					"-fx-text-fill: #d9d8d7;" + // Background color
							"-fx-padding: 10px;" + // Padding
					"-fx-font-size: 50px"); // Text alignment;);
			if(j < num) {
				//color it
				star.setStyle(
						"-fx-text-fill: #ffd952;" + 
								"-fx-padding: 10px;" + // Padding
						"-fx-font-size: 50px");
				j++;
			}
			stars.getChildren().add(star);
			stars.setAlignment(Pos.CENTER);
		}
		return stars; 
	}

	public static Label attributeLabel(String name, GridPane gridPane, ArrayList<Attribute> userAttributeList, int attributeType, int weight, HashMap<Integer,ArrayList<Attribute>> allAttributes ) {
		Label label = new Label(name);
		label.setFont(Font.font(font, fontSm));
		label.setWrapText(true);
		label.maxWidth(50);
		ObservableList<Node> labels = gridPane.getChildren();

		String defaultStyle = 
				"-fx-background-color: #e1fcf6;" + // Background color
						"-fx-padding: 10px;" + // Padding
						"-fx-border-radius: 10px;" + // Border radius
						"-fx-alignment: center;"; // Text alignment;

		String highLightedStyle = 
				"-fx-background-color: #98f5e1;" + // Background color
						"-fx-padding: 10px;" + // Padding
						"-fx-border-radius: 10px;" + // Border radius
						"-fx-alignment: center;"; // Text alignment;

		//function to be able to turn the label highlighted when loading them if dog attribute matches.

		label.setStyle(defaultStyle);

		for (Attribute attribute : userAttributeList) {
			if(attribute.getWeight() == weight) {
				label.setStyle(highLightedStyle);
			}
		}

		label.setOnMouseClicked(event -> {
			if (label.getStyle().equals(defaultStyle)) {
				label.setStyle(highLightedStyle);
				if(!userAttributeList.contains(allAttributes.get(attributeType).get(weight))) {
					userAttributeList.add(allAttributes.get(attributeType).get(weight));
				}
			}else{
				if(userAttributeList.size() >= 2) {
					label.setStyle(defaultStyle);
					userAttributeList.remove(allAttributes.get(attributeType).get(weight));
				}
			}
		});
		return label;
	}


	public static Button calendarButton(String text) {

		Button button = new Button(text);
		button.setFont(Font.font(font, fontMd));

		button.setStyle(
				"-fx-background-color: #82daf5; -fx-text-fill: #ffffff; -fx-alignment: center;"
				);

		return button;

	}

	public static StackPane calendarCell(String day) {
		StackPane cell = new StackPane();
		Label cellText = new Label();
		cellText.setText(day);
		cellText.setFont(Font.font(font, fontSm));
		cell.setPrefSize(90, 90);
		StackPane.setAlignment(cellText, Pos.TOP_RIGHT);

		String def = "-fx-background-color: #d1d1d1; -fx-text-fill: #0f0f0f; -fx-alignment: top-right;";
		String high = "-fx-background-color: #e0e0e0; -fx-text-fill: #0f0f0f; -fx-alignment: top-right;";
		cell.setStyle(def);

		cell.getChildren().add(cellText);
		return cell;
	}


	public static GridPane createAttribute (ArrayList < Attribute > userAttributeList,int attributeType, HashMap<
			Integer, ArrayList < Attribute >> allAttributes ){
		GridPane gridPane = new GridPane();
		String[] names = allAttributes.get(attributeType).get(0).getNames();
		for (int j = 0; j < names.length; j++) {
			// Add the label to the grid
			Label label = attributeLabel(names[j], gridPane, userAttributeList, attributeType, j, allAttributes);
			gridPane.add(label, j, 0);
		}
		return gridPane;
	}

	//Sidney, Edson and Connor were here :)
	public static HBox appointmentView (Dog dog, Date localDate, Stage
			primaryStage, Hashtable < Integer, Poster > poster){
		ImageView img = Components.imageView(200, 200);
		img.setImage(new Image(dog.getImagePath()));

		Label primaryInfoLabel = Components.mediumLabel(dog.getName() + ", " + dog.getAge() + " years, " + dog.getSex(), Pos.CENTER);
		Label appointmentDate = Components.mediumLabel("Appointment Date: " + localDate.toString(), Pos.CENTER);

		Hyperlink rescheduleLink = hyperlinkToReschedule(dog, primaryStage, poster);

		Hyperlink cancelLink = hyperlinkToCancelAppointment(dog, primaryStage, poster);

		VBox info = new VBox(primaryInfoLabel, appointmentDate, rescheduleLink, cancelLink);
		HBox HBox = new HBox(img, info);
		HBox.setAlignment(Pos.CENTER);
		HBox.setSpacing(50);

		return HBox;
	}

	public static HBox likedDogView (Dog dog, Stage primaryStage, Hashtable < Integer, Poster > poster){

		ImageView img = Components.imageView(200, 200);
		img.setImage(new Image(dog.getImagePath()));

		Label primaryInfoLabel = Components.mediumLabel(dog.getName() + ", " + dog.getAge() + " years, " + dog.getSex(), Pos.CENTER);

		Hyperlink posterLink = hyperlinkToPosterProfile(dog, primaryStage, poster);

		Hyperlink sponsorLink = hyperLinkToSponsor(dog, primaryStage);


		Hyperlink appointmentLink = hyperlinkToAppointment(dog, primaryStage, poster);

		VBox info = new VBox(
				primaryInfoLabel,
				posterLink,
				appointmentLink,
				sponsorLink
				);

		HBox HBox = new HBox(img, info);
		HBox.setAlignment(Pos.CENTER);
		HBox.setSpacing(50);

		return HBox;
	}

	public static Hyperlink hyperLinkToSponsor (Dog dog, Stage primaryStage){
		Hyperlink sponsorLink = Components.hyperlink();
		sponsorLink.setText("Sponsor " + dog.getName() + "!");
		DonateScene donateScene = DonateScene.getInstance();
		sponsorLink.setOnAction(event -> {
			try {
				donateScene.setCurrentDog(dog);
				donateScene.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return sponsorLink;
	}

	public static Hyperlink hyperlinkToPosterProfile (Dog dog, Stage
			primaryStage, Hashtable < Integer, Poster > poster){
		Hyperlink posterLink = Components.hyperlink();
		posterLink.setText(poster.get(dog.getPosterId()).getDisplayName());
		PosterProfileScene posterProfile = PosterProfileScene.getInstance();
		posterLink.setOnAction(event -> {
			try {
				posterProfile.setCurrentPoster(poster.get(dog.getPosterId()));
				posterProfile.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return posterLink;
	}

	public static Hyperlink hyperlinkToCancelAppointment (Dog dog, Stage
			primaryStage, Hashtable < Integer, Poster > poster){
		AppointmentManager userManager = AppData.getInstance().getAppointmentManager();
		ArrayList<Appointment> userAppointments = userManager.getUserAppointments();


		Hyperlink appointmentLink = Components.hyperlink();
		appointmentLink.setText("Cancel");
		CalendarScene appointmentPage = CalendarScene.getInstance();
		appointmentLink.setOnAction(event -> {
			try {

				for (Appointment appointment : userAppointments) {
					if (appointment.getDogID() == dog.getId()) {
						userManager.removeAppointment(appointment);
						CalendarScene.getInstance().setExistingAppointment(null);
						BookedAppointmentScene bookedPage = BookedAppointmentScene.getInstance();
						bookedPage.start(primaryStage);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return appointmentLink;
	}

	public static Hyperlink hyperlinkToReschedule (Dog dog, Stage primaryStage, Hashtable < Integer, Poster > poster)
	{
		AppointmentManager userManager = AppData.getInstance().getAppointmentManager();
		ArrayList<Appointment> userAppointments = userManager.getUserAppointments();
		Hyperlink appointmentLink = Components.hyperlink();
		appointmentLink.setText("Reschedule");
		Poster selectedPoster = poster.get(dog.getPosterId());
		CalendarScene appointmentPage = CalendarScene.getInstance();

		appointmentLink.setOnAction(event -> {
			try {
				for (Appointment appointment : userAppointments) {
					if (appointment.getDogID() == dog.getId()) {
						appointmentPage.setCurrentPosterDog(selectedPoster, dog);
						appointmentPage.start(primaryStage);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return appointmentLink;
	}

	public static Hyperlink hyperlinkToAppointment (Dog dog, Stage
			primaryStage, Hashtable < Integer, Poster > poster ){
		Hyperlink appointmentLink = Components.hyperlink();
		appointmentLink.setText("Meet me!");
		Poster selectedPoster = poster.get(dog.getPosterId());
		CalendarScene appointmentPage = CalendarScene.getInstance();

		appointmentLink.setOnAction(event -> {
			try {
				appointmentPage.setCurrentPosterDog(selectedPoster, dog);
				//appointmentPage.updateMeetWithLabel(poster, selectedDog);
				appointmentPage.start(primaryStage);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return appointmentLink;
	}

	public static HBox posterDogView (Dog dog){
		ImageView img = Components.imageView(200, 200);
		img.setImage(new Image(dog.getImagePath()));
		Label primaryInfoLabel = Components.mediumLabel(dog.getName() + ", " + dog.getAge() + " years, " + dog.getSex(), Pos.CENTER);
		VBox info = new VBox(primaryInfoLabel);
		HBox ret = new HBox(img, info);
		ret.setSpacing(50);
		return ret;
	}

	public static HBox sponsoredDogView (Dog d, Stage stage, Hashtable < Integer, Poster > poster, AppData
			appdata, SponsoredDogsScene page){
		ImageView img = Components.imageView(200, 200);
		img.setImage(new Image(d.getImagePath()));

		Label primaryInfoLabel = Components.mediumLabel(d.getName() + ", " + d.getAge() + " years, " + d.getSex(), Pos.CENTER);

		Hyperlink posterLink = hyperlinkToPosterProfile(d, stage, poster);

		Button cancelButton = button("Cancel Donation :(");
		Button editButton = button("Edit Donation :D"); // to be implemented
		SimpleIntegerProperty numClicks = new SimpleIntegerProperty(0);
		editButton.setId("edit");

		cancelButton.setOnAction(event -> {
			numClicks.set(numClicks.get() + 1);
			cancelButton.setTranslateX(150);
			cancelButton.setTranslateY(150);
			PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
	        
	        // After the pause, move the button back to its original position
	        pauseTransition.setOnFinished(e -> {
	            TranslateTransition translateBack = new TranslateTransition(Duration.seconds(1), cancelButton);
	            translateBack.setToX(0);
	            translateBack.setToY(0);
	            translateBack.play();
	            
	                });
	        pauseTransition.play();

			if (numClicks.get()>1) {
				 

			        // Additional actions like removing the recurring payment and showing an alert
		            appdata.getUser().getWallet().removeRecurringPayment(d.getId());
		            page.start(stage);
		            showAlert("You have successfully cancelled your sponsorship", "We're sorry to see you go :(", AlertType.CONFIRMATION);
		   
			}
		});
		VBox info = new VBox(
				primaryInfoLabel,
				posterLink,
				cancelButton, editButton
				);
		info.setId("infovbox");
		ComboBox<String>  howOftenBox;
		TextField howMuchMoney;
		Label howMuch = Components.mediumLabel("How much would you like to donate?", Pos.CENTER);
		howMuchMoney = new TextField();

		Label howOften = Components.mediumLabel("How often would you like to donate?", Pos.CENTER);
		howOftenBox = new ComboBox<>(FXCollections.observableArrayList("Once", "Weekly", "Biweekly", "Monthly"));

		howOftenBox.setValue("Once");
		howOftenBox.setId("howOftenBox");

		Button SetNewRecurring = new Button("Change amount and frequency :D");
		SetNewRecurring.setId("SetNewRecurring"); // Set the ID of the button to "SetNewRecurring"

		SetNewRecurring.setOnAction(event -> {

			//						try {
			//
			//						//	makePayment(appData, howOftenBox.getValue());
			//
			//						} catch (FundsTooLow e) {
			//							// TODO Auto-generated catch block
			//							e.printStackTrace();
			//						}
			appdata.getUser().getWallet().removeRecurringPayment(d.getId());
			//	public RecurringPayment(double paymentAmount, int daysBetweenPayments, int dogId, int posterId, String lastPaymentDate) { // from db
			//box.setBox(info);
			//box.setButton();
			setIsClicked(true);
			//DonateScene donateScene = new DonateScene();
			//appdata.getUser().getWallet().addRecurringPayment(new RecurringPayment(amountToDonate, daysBetweenPayments, currentDog.getId(), currentDog.getPosterId()));
			try {
				makePayment(appdata, howOftenBox.getValue(), howMuchMoney,howOftenBox,d);
				//info.getChildren().addAll(howMuch, howMuchMoney,howOften, howOftenBox, SetNewRecurring);

				info.getChildren().removeAll(howMuch, howMuchMoney,howOften, howOftenBox, SetNewRecurring);
			} catch (FundsTooLow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});


		editButton.setOnAction(event -> {

			info.getChildren().addAll(howMuch, howMuchMoney,howOften, howOftenBox, SetNewRecurring);

		});

		HBox HBox = new HBox();
		HBox.getChildren().addAll(img, info);
		HBox.setAlignment(Pos.CENTER);
		HBox.setSpacing(50);
		return HBox;
	}

	private static void moveButton(Button button) {
		// Translate the button
		button.setTranslateX(20);
		button.setTranslateY(30);
		//
		//	        // Create a PauseTransition with a 1-second pause
		//	        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
		//
		//	        // Set the action to be performed after the pause
		//	        pauseTransition.setOnFinished(event -> {
		//	            // Reset the button's translation after the pause
		//	            button.setTranslateX(0);
		//	            button.setTranslateY(0);
		//	        });
		//
		//	        // Start the pause transition
		//	        pauseTransition.play();
	}
	public static boolean isClicked() {
		return clicked;
	}
	public static void setIsClicked(boolean val) {
		clicked=val;
	}
	public static void showAlert(String title, String message, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);


		alert.showAndWait();
	}
	
	public static void makePayment(AppData appdata, String duration, TextField howMuchMoney, ComboBox<String> howOftenBox, Dog d) throws FundsTooLow {
		String inputText = howMuchMoney.getText().trim();

		Boolean valid=DonateScene.checkInput(inputText);
		if (!valid)
		{showAlert("Cannot enter non-numeric values ", "Please enter a number", AlertType.ERROR);

		howMuchMoney.clear();
		howMuchMoney.setText("");}

		else if (Double.parseDouble(howMuchMoney.getText())<=0)
		{
			showAlert("Cannot enter a negative number", "Please enter a non-negative number", AlertType.ERROR);
			howMuchMoney.clear();
		}
		else {//do the payments otherwise
			double amountToDonate = Double.parseDouble(howMuchMoney.getText()); 
			Poster poster = appdata.getPosterProfiles().get(d.getPosterId());
			int daysBetweenPayments = 0;


			switch(duration) {
			case "Weekly":
				daysBetweenPayments = 7;
				break;

			case "Biweekly":
				daysBetweenPayments = 14;
				break;

			case "Monthly":
				daysBetweenPayments = 30;
				break;

			default:
				break;

			}
			if (amountToDonate>appdata.getUser().getWallet().getBalance())
				showAlert("Insufficient funds", "Your recurring payment did not go through", AlertType.ERROR);
			else	{
				if(!howOftenBox.getValue().equals("Once")) {
					if(appdata.getUser().getWallet().getRecurringPayments().containsKey(d.getId()))
					{
						showAlert("Recurring Payment Updated", "Your existing recurring payment was updated for this dog", AlertType.INFORMATION);
						appdata.getUser().getWallet().removeRecurringPayment(d.getId());
						appdata.getUser().getWallet().addRecurringPayment(new RecurringPayment(amountToDonate, daysBetweenPayments, d.getId(), d.getPosterId()));

					}
					else
						appdata.getUser().getWallet().addRecurringPayment(new RecurringPayment(amountToDonate, daysBetweenPayments, d.getId(), d.getPosterId()));
					showAlert("Recurring Payment Created", "You have set up recurring payments for this dog", AlertType.INFORMATION);

				}

				appdata.getUser().getWallet().donate(amountToDonate, poster);
				showAlert("Payment went through",d.getName()+" is thankful for you! ♥", AlertType.INFORMATION);
			}



			//System.out.println("poster"+poster.getDisplayName() +"'s balance is"+poster.getBalance());//was for testing

		}
	}
}
