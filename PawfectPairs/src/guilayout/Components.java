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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Components{
	/*
	 * GUI components contained here to streamline GUI process and create more modular, thematic GUI parts
	 */

	private static String currentScene = "";

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

	public static void updateCurrentScene(String newCurrentScene) {
		currentScene = newCurrentScene;

	}

	public static HBox navTab(UserProfile userScene,LikedDogScene likedDog, DogProfileScene dogProfile, SponsoredDogsScene sponsoredDogs, BookedAppointmentScene appointmentScene,Stage stage, AppData appData) {
		//create a navigation tab: settings, schedule, messages, etc
		// settings hBox
		HBox navTab = new HBox();
		//		navTab.setStyle("-fx-padding:" + screenWidth/5 + ";");

		navTab.getStyleClass().add("navbar-container");
		//		navTab.setStyle("-fx-background-color: #f5f5f5;");
		//		navTab.setSpacing(20);

		Label settingsButton = new Label("⚙ Settings ⚙");
		Label dogProfileButton = new Label("🐕 Dog Profiles 🐕");
		Label likedDogButton = new Label("♥ Liked Dogs  🐶");
		Label appointmentsButton = new Label("📅 Appointments 📅");
		Label sponsoredDogButton = new Label("💸 Sponsored Dogs  💸");

		String paddingStyle = "-fx-padding: 10 " + (screenWidth*0.03) + " 10 " + (screenWidth * 0.03) + ";";
		settingsButton.getStyleClass().addAll("nav-button", "medium");
		settingsButton.setStyle(paddingStyle);
		dogProfileButton.getStyleClass().addAll("nav-button", "medium");
		dogProfileButton.setStyle(paddingStyle);
		likedDogButton.getStyleClass().addAll("nav-button", "medium");
		likedDogButton.setStyle(paddingStyle);
		appointmentsButton.getStyleClass().addAll("nav-button", "medium");
		appointmentsButton.setStyle(paddingStyle);
		sponsoredDogButton.getStyleClass().addAll("nav-button", "medium");
		sponsoredDogButton.setStyle(paddingStyle);

		//		// set hightlight on current page button  
		switch(currentScene) {
		case "userProfile":
			settingsButton.setId("highlighted-nav");
			break;

		case "likedDogs":
			likedDogButton.setId("highlighted-nav");
			break;

		case "dogProfiles":
			dogProfileButton.setId("highlighted-nav");
			break;
		case "appointments":
			appointmentsButton.setId("highlighted-nav");
		case "sponsoredDogs":
			sponsoredDogButton.setId("highlighted-nav");
			break;


		default:
			break; // do nothing
		}

		if(currentScene == "userProfile") { // if we are currently on userProfile, we should update dog scores when we change to a different page 

			settingsButton.setOnMouseClicked(event -> {
				appData.updateDogScores();
				userScene.start(stage);
			});
			likedDogButton.setOnMouseClicked(event -> {
				appData.updateDogScores();
				likedDog.start(stage);
			});
			dogProfileButton.setOnMouseClicked(event -> {
				appData.updateDogScores();

				dogProfile.start(stage);
			});
			appointmentsButton.setOnMouseClicked(event -> {
				appData.updateDogScores();
				appointmentScene.start(stage);
			});
			sponsoredDogButton.setOnMouseClicked(event -> {
				appData.updateDogScores();
				likedDog.start(stage);
			});

		}else {
			settingsButton.setOnMouseClicked(event -> {
				userScene.start(stage);
			});
			likedDogButton.setOnMouseClicked(event -> {
				likedDog.start(stage);
			});
			dogProfileButton.setOnMouseClicked(event -> {
				dogProfile.start(stage);
			});
			appointmentsButton.setOnMouseClicked(event -> {
				appointmentScene.start(stage);
			});
			sponsoredDogButton.setOnMouseClicked(event -> {
				sponsoredDogs.start(stage);
			});
		}



		navTab.getChildren().addAll(settingsButton, dogProfileButton, likedDogButton, sponsoredDogButton, appointmentsButton);

		navTab.setAlignment(Pos.TOP_CENTER);
		return navTab; 
	}

	public static Hyperlink hyperlink() {
		Hyperlink hyperlink = new Hyperlink();


		hyperlink.getStyleClass().add("hyperlink");

		return hyperlink;

	}

	public static Label largeLabel(String text, Pos pos) {
		Label label = new Label(text);
		label.setAlignment(pos);
		//		label.setFont(Font.font(font, fontLg));
		label.getStyleClass().addAll("label","large");


		return label;
	}

	public static Label mediumLabel(String text, Pos pos) {
		Label label = new Label(text);
		label.setAlignment(pos);
		label.getStyleClass().addAll("label","medium");


		return label;
	}

	public static Label smallLabel(String text, Pos pos) {
		Label label = new Label(text);
		label.setAlignment(pos);
		label.getStyleClass().addAll("label","small");
		label.setWrapText(true);


		return label;
	}

	public static Label tinyLabel(String text, Pos pos) {
		Label label = new Label(text);
		label.setAlignment(pos);
		label.getStyleClass().addAll("label","tiny");
		label.setWrapText(true);


		return label;
	}

	public static Label largeLabel() {
		Label label = new Label();
		label.getStyleClass().addAll("label","large");


		return label;
	}

	public static Label mediumLabel() {
		Label label = new Label();
		label.getStyleClass().addAll("label","medium");
		return label;
	}

	public static Label smallLabel() {
		Label label = new Label();
		label.getStyleClass().addAll("label","small");
		label.setWrapText(true);

		return label;
	}

	public static ImageView imageView(int w, int l) {
		ImageView imageView = new ImageView();

		imageView.setFitHeight(l);
		imageView.setFitWidth(w);

		imageView.setPreserveRatio(true);
		
		Rectangle clip = new Rectangle(w, l);
		clip.setArcWidth(w * 0.1); 
        clip.setArcHeight(l * 0.1);
        imageView.setClip(clip);
        

		return imageView;

	}

	public static Label tagLabel(String tag,Tag labelTag, User user) {
		// tags in the user profile to change preferences
		Label label = new Label(tag);
		label.getStyleClass().addAll("tag-label", "label", "small");
		label.setWrapText(true);
		label.maxWidth(50);

		//function to be able to turn the label highlighted when loading them if in the dog tags list.


		if(user.getTagPreferences().contains(labelTag) == true) {

			label.setId("highlighted-tag-label");
		}


		label.setOnMouseClicked(event -> {
			// Toggle background color on click
			if (label.getId() == null) {
				label.setId("highlighted-tag-label"); // highlight if not highlighted
				if(user.getTagPreferences().contains(labelTag) == false) {
					user.getTagPreferences().put(labelTag.getTagId(),labelTag);
				}


			} else {
				if(user.getTagPreferences().contains(labelTag) == true) {
					user.getTagPreferences().remove(labelTag);
				}
				label.setId(null);         	
			}
		});


		return label;
	}

	public static GridPane createTags(HashMap<Integer, Tag> tags, User user) {
		GridPane gridPane = new GridPane();
		int row = 0;
		int col = 0;

		int maxRows = 6;

		gridPane.setHgap(10); 
		gridPane.setVgap(10); 


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
		label.setWrapText(true);

		label.maxWidth(100);

		label.getStyleClass().addAll("dog-tag-label", "small", "label");
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

		int maxRows = 10;

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
			star.getStyleClass().add("star");

			if(j < num) {
				//color it
				star.setId("star-active");
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


		//function to be able to turn the label highlighted when loading them if dog attribute matches.

		label.getStyleClass().addAll("attribute-label", "label", "modest");

		for (Attribute attribute : userAttributeList) {
			if(attribute.getWeight() == weight) {
				label.setId("highlighted-attribute-label");
			}
		}

		label.setOnMouseClicked(event -> {
			if (label.getId() == null) {
				label.setId("highlighted-attribute-label");
				if(!userAttributeList.contains(allAttributes.get(attributeType).get(weight))) {
					userAttributeList.add(allAttributes.get(attributeType).get(weight));
				}
			}else{
				if(userAttributeList.size() >= 2) {
					label.setId(null);
					userAttributeList.remove(allAttributes.get(attributeType).get(weight));
				}
			}
		});
		return label;
	}


	public static Button calendarButton(String text) {

		Button button = new Button(text);

		button.getStyleClass().addAll("calendar-button", "medium");

		return button;

	}

	public static StackPane calendarCell(String day) {
		StackPane cell = new StackPane();
		Label cellText = new Label();
		cellText.setText(day);
		cellText.getStyleClass().add("small");
		cell.setPrefSize(95, 95);
		StackPane.setAlignment(cellText, Pos.TOP_RIGHT);

		cell.getStyleClass().add("calendar-cell");

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
		HBox.setAlignment(Pos.CENTER_LEFT);
		HBox.setSpacing(50);
		HBox.setLayoutX(0.35);

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
			
			
			if (numClicks.get()>=1) {


				// Additional actions like removing the recurring payment and showing an alert
				appdata.getUser().getWallet().removeRecurringPayment(d.getId());
				page.start(stage);
				showAlert("You have successfully cancelled your sponsorship", "We're sorry to see you go :(", AlertType.CONFIRMATION);

			}
			else 
			{
				numClicks.set(numClicks.get()+1);
				//slantMoveAnimation(cancelButton);
				//bounce(cancelButton);
				//addBouncingButton(cancelButton, 10);
				
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
	 public static void addBouncingButton(Button button, double durationSeconds) {

	        TranslateTransition transition = new TranslateTransition(Duration.seconds(durationSeconds), button);
	        transition.setCycleCount(Animation.INDEFINITE);
	        transition.setAutoReverse(true);
	        transition.setInterpolator(Interpolator.EASE_BOTH);

	        // Calculate the bounds of the container
	        double maxX = Components.screenWidth - button.getWidth();
	        double maxY = Components.screenHeight - button.getHeight();

	        // Set the bounce boundaries based on the container bounds
	        transition.setByX(maxX);
	        transition.setByY(maxY);

	        transition.play();
	    }
	public static void bounce (Button button) {
//		// Create a curved path (arc)
//		Path path = new Path();
//		path.getElements().add(new MoveTo(50, 50)); // Starting point
//		path.getElements().add(new CubicCurveTo(150, 0, 250, 150, 350, 50)); // Cubic curve

		 
        // Call the method to create the logo shape
        Path path = createPath();		
        
		PathTransition pathTransition = new PathTransition();// Create a PathTransition
		pathTransition.setDuration(Duration.seconds(8)); // Duration for one bounce cycle
		pathTransition.setPath(path);
		pathTransition.setNode(button);
		// pathTransition.setCycleCount(PathTransition.INDEFINITE); // Indefinite bounce
		pathTransition.setCycleCount(1);

		// Set the position of the button back to (0, 0) at the end of the animation
		pathTransition.setOnFinished(event -> {
			button.setTranslateX(0);
			button.setTranslateY(0);
		});

		// Start the animation
		pathTransition.play();
	}
	
	
	public static Path createPath() {
		  double startX = 0;
	        double startY = 10 * Math.pow(startX, 4) - 5 * Math.pow(startX, 3) - 5 * Math.pow(startX, 2);

	        double controlX1 = 1;
	        double controlY1 = 10 * Math.pow(controlX1, 4) - 5 * Math.pow(controlX1, 3) - 5 * Math.pow(controlX1, 2);

	        double controlX2 = -1;
	        double controlY2 = 10 * Math.pow(controlX2, 4) - 5 * Math.pow(controlX2, 3) - 5 * Math.pow(controlX2, 2);

	        double endX = -2;
	        double endY = 10 * Math.pow(endX, 4) - 5 * Math.pow(endX, 3) - 5 * Math.pow(endX, 2);

	        // Create a Path
	        Path path = new Path();

	        // Define the quartic curve using CubicCurveTo
	        path.getElements().add(new MoveTo(startX, startY));
	        path.getElements().add(new CubicCurveTo(
	                // Control point 1
	                controlX1, controlY1,
	                // Control point 2
	                controlX2, controlY2,
	                // Ending point
	                endX, endY
	        ));
		
		return path;
	}
	
	public static void dogAttributeDisplay(HBox parent, String emoji, int weight) {
		int j = 0;
		parent.getChildren().clear();
		for (int i = 0; i < 5; i ++) {
			//generate a star
			Label icon = new Label(emoji);
			icon.getStyleClass().add("dog-attribute-label");

			if(j < weight + 1) {
				//color it
				icon.setId("dog-attribute-label-active");
				j++;
			}
			parent.getChildren().add(icon);
			parent.setAlignment(Pos.CENTER);
		}
	}
	
	public static void slantMoveAnimation(Button button) {
		// Create Timeline for the animation
		Timeline timeline = new Timeline();

		// Define keyframes for the animation
		timeline.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(button.translateYProperty(), 0)),
				new KeyFrame(Duration.ZERO, new KeyValue(button.translateXProperty(), 0)),
				new KeyFrame(Duration.seconds(0.5), new KeyValue(button.translateXProperty(), -50)),

				new KeyFrame(Duration.seconds(0.5), new KeyValue(button.translateYProperty(), -50)),
				new KeyFrame(Duration.seconds(1), new KeyValue(button.translateYProperty(), 0)),
				new KeyFrame(Duration.seconds(1), new KeyValue(button.translateXProperty(), 0))

				);

		/*  // Set cycle count to indefinite for continuous bouncing
	        timeline.setCycleCount(Timeline.INDEFINITE);
		 */
		timeline.setCycleCount(3);

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

		Boolean valid=checkInput(inputText);
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
