package guilayout;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.database.Database;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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
					user.getTagPreferences().remove(labelTag.getTagId());
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
//		label.setWrapText(true);

		label.maxWidth(100);

		label.getStyleClass().addAll("dog-tag-label", "tiny", "label");
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
//			GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);

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

	public static HBox appointmentView (Dog dog, Date localDate, Stage
			primaryStage, Hashtable < Integer, Poster > poster, AppData appData, Appointment selectedDate){
		ImageView img = Components.imageView(200, 200);
		img.setImage(new Image(dog.getImagePath()));

		Label primaryInfoLabel = Components.mediumLabel(dog.getName() + ", " + dog.getAge() + " years, " + dog.getSex(), Pos.CENTER);
		Label appointmentDate = Components.mediumLabel("Appointment Date: " + localDate.toString(), Pos.CENTER);

		Hyperlink rescheduleLink = hyperlinkToReschedule(dog, primaryStage, poster, appData, selectedDate);

		Hyperlink cancelLink = hyperlinkToCancelAppointment(dog, primaryStage, poster, appData, localDate);

		VBox info = new VBox(primaryInfoLabel, appointmentDate, rescheduleLink, cancelLink);
		HBox HBox = new HBox(img, info);
		HBox.setAlignment(Pos.CENTER);
		HBox.setSpacing(50);

		return HBox;
	}

	public static HBox likedDogView(Dog dog, Stage primaryStage, AppData appData) {
        Hashtable<Integer, Poster> poster = appData.getPosters();

		ImageView img = Components.imageView(200, 200);
		img.setImage(new Image(dog.getImagePath()));
		
		Label primaryInfoLabel = Components.mediumLabel(dog.getName() + ", " + dog.getAge() + " years, " + dog.getSex(), Pos.CENTER);

		VBox info = new VBox(); 
		
		if(dog.getAdopted() == true) {
	        ColorAdjust colorAdjust = new ColorAdjust();
	        colorAdjust.setSaturation(-1); // Set saturation to -1 to make it grayscale
	        img.setEffect(colorAdjust);
	        
	        Label adoptedLabel = new Label("ADOPTED!"); 
	        adoptedLabel.getStyleClass().add("moderate label");
	        
	        info = new VBox(
					primaryInfoLabel,
					adoptedLabel
					);
	       
	        
		}else {
			Hyperlink posterLink = hyperlinkToPosterProfile(dog, primaryStage, poster);
			Hyperlink sponsorLink = hyperLinkToSponsor(dog, primaryStage);
			Hyperlink appointmentLink = hyperlinkToAppointment(dog, primaryStage, poster);
			Hyperlink adoptionLink = hyperLinkToAdopt(dog,primaryStage);
			 info = new VBox(
						primaryInfoLabel,
						posterLink,
						appointmentLink,
						sponsorLink,
						adoptionLink
						);
		}



        Button unlikeButton = new Button("Unlike");
        HBox HBox = new HBox(img, info);
        if (!dog.getAdopted()) {
            HBox.getChildren().add(unlikeButton);
        }

        HBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setSpacing(50);
        HBox.setLayoutX(0.5);

        unlikeButton.setOnAction(event -> {
            // Remove the dog from the likedDogs list

            appData.getUser().removeUnlikedDog(dog);
           
            Database.removeLikedDog(dog.getId(), appData.getUser().getUserID());
            
            PriorityQueue<Dog> doglist = appData.getSortedDogProfiles();
            
            doglist.add(dog);
        	
        	
            // Remove the entire likedDogView from the UI
			((VBox) unlikeButton.getParent().getParent()).getChildren().remove(unlikeButton.getParent());
			
			AppointmentManager userManager = AppData.getInstance().getAppointmentManager();
			ArrayList<Appointment> userAppointments = userManager.getUserAppointments();
			
			Iterator<Appointment> iterator = userAppointments.iterator();
			while (iterator.hasNext()) {
			    Appointment appointment = iterator.next();
			    if (appointment.getDogID() == dog.getId()) {
			        iterator.remove(); // Remove the current appointment from the list
			        userManager.removeAppointment(appointment);
			    }
			}
			AppData.getInstance().getUser().getWallet().removeRecurringPayment(dog.getId());
			
			
			//updating likedDogPage whenever the unlike button is clicked
			LikedDogScene likedDogPage = LikedDogScene.getInstance();
			try {
				likedDogPage.start(primaryStage);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
        });

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
		primaryStage, Hashtable < Integer, Poster > posterList){
		
		Hyperlink posterLink = Components.hyperlink();
		posterLink.setText(posterList.get(dog.getPosterId()).getDisplayName());
		PosterProfileScene posterProfile = PosterProfileScene.getInstance();
		posterLink.setOnAction(event -> {
			try {
				posterProfile.setCurrentPoster(posterList.get(dog.getPosterId()));
				posterProfile.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return posterLink;
	}
	public static Hyperlink hyperlinkToCancelAppointment (Dog dog, Stage
			primaryStage, Hashtable < Integer, Poster > poster, AppData appData, Date localDate){
		AppointmentManager userManager = AppData.getInstance().getAppointmentManager();
		ArrayList<Appointment> userAppointments = userManager.getUserAppointments();

		Hyperlink appointmentLink = Components.hyperlink();
		appointmentLink.setText("Cancel");
		CalendarScene appointmentPage = CalendarScene.getInstance();
		appointmentLink.setOnAction(event -> {
			try {

				for (Appointment appointment : userAppointments) {
					if (appointment.getDogID() == dog.getId()&&appointment.getDate().equals(localDate)) {
						RemoveAppointmentFromUser(appointment, userAppointments, userManager,appData);
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
	
	
private static void RemoveAppointmentFromUser (Appointment appointment, ArrayList<Appointment> userAppointments,AppointmentManager userManager, AppData appData ) {
	userManager.removeAppointment(appointment);
	ArrayList <Appointment> newExist= CalendarScene.getInstance().getExistingAppointment();
	newExist.remove(appointment);
	CalendarScene.getInstance().setExistingAppointment(newExist);
	AppointmentManager RemoveApp= appData.getAppointmentManager();
	RemoveApp.removeAppointment(appointment);
	 appData.setAppointmentManager(RemoveApp);
	
}

public static ArrayList<Appointment> deepCopyUserAppointments(ArrayList<Appointment> originalList) {
    ArrayList<Appointment> copyList = new ArrayList<>();
    for (Appointment appointment : originalList) {
        copyList.add(new Appointment(appointment)); // Create a copy of each appointment and add to the new list
    }
    return copyList;
}
	public static Hyperlink hyperlinkToReschedule (Dog dog, Stage primaryStage, Hashtable < Integer, Poster > poster, AppData appData, Appointment selectedDate)
	{
		AppointmentManager userManager = AppData.getInstance().getAppointmentManager();
		ArrayList<Appointment> userAppointments = userManager.getUserAppointments();
        ArrayList<Appointment> deepCopyList = deepCopyUserAppointments(userAppointments);

		Hyperlink appointmentLink = Components.hyperlink();
		appointmentLink.setText("Reschedule");
		Poster selectedPoster = poster.get(dog.getPosterId());
		CalendarScene appointmentPage = CalendarScene.getInstance();

		appointmentLink.setOnAction(event -> {
			try {
				for (Appointment appointment : deepCopyList) {
					if (appointment.getDogID() == dog.getId()) {
						if(appointment.equals(selectedDate)) {
						RemoveAppointmentFromUser(appointment, userAppointments, userManager,appData);
						appointmentPage.setCurrentPosterDog(selectedPoster, dog);
						appointmentPage.start(primaryStage);
						showAlert("Rebooking","Please select an alternate appointment, your previous appointment has been cancelled.",AlertType.INFORMATION);
						break;
						}
						
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
	
	//Adopt Hyperlink
	public static Hyperlink hyperLinkToAdopt (Dog dog, Stage primaryStage){
		
		Hyperlink adoptLink = Components.hyperlink();
		adoptLink.setText("Adopt " + dog.getName() + "!");	
		adoptLink.setOnAction(event -> {
			try {
				if (dog.getAdopted()==false)
				{
					
					showAlert("Dog has been adopted", dog.getName() + " is thankful for you!", AlertType.INFORMATION);
					AppointmentManager userManager = AppData.getInstance().getAppointmentManager();
					ArrayList<Appointment> userAppointments = userManager.getUserAppointments();
					User user = AppData.getInstance().getUser();
					Iterator<Appointment> iterator = userAppointments.iterator();
					while (iterator.hasNext()) {
					    Appointment appointment = iterator.next();
					    if (appointment.getDogID() == dog.getId()) {
					        iterator.remove(); // Remove the current appointment from the list
					        userManager.removeAppointment(appointment);
					    }
					}
					AppData.getInstance().getUser().getWallet().removeRecurringPayment(dog.getId());
					
					ArrayList<Dog> likedDogList = AppData.getInstance().getUser().getLikedDogs();
					for (Dog d:likedDogList) {
						if (d.getId()==dog.getId()) {
							d.setAdopted(true);
							user.addToAdoptedDogs(d);
						}
					}
					LikedDogScene likedPage = LikedDogScene.getInstance();
					likedPage.start(primaryStage);
					
					
				}
				else {
					dog.setAdopted(false);	
					
					showAlert("Dog cannot be adopted", dog.getName() + " is already adopted", AlertType.ERROR);
				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return adoptLink;
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
	
	public static VBox scoreSlider(User user, Poster poster,Stage primaryStage) {
	    Label valueLabel = new Label("0");
	    valueLabel.setStyle("-fx-font-size: 20px;");

	    Slider slider = new Slider(0, 10, 0);
	    slider.setBlockIncrement(1);
	    slider.setShowTickMarks(true);
	    slider.setMajorTickUnit(1);
	    slider.setMinorTickCount(0);
	    slider.setSnapToTicks(true);
	    slider.setStyle("-fx-padding: 10 0 0 0;");

	    VBox slide = new VBox(10);

	    Label scoreLabel = new Label("Your Score: ");
	    scoreLabel.setStyle("-fx-font-size: 20px;");
	    Label OutofLabel = new Label("/10");
	    OutofLabel.setStyle("-fx-font-size: 20px;");
	    HBox scoreBox = new HBox(scoreLabel, valueLabel, OutofLabel);

	    slider.valueProperty().addListener((obs, oldVal, newVal) -> {
	        valueLabel.setText(String.valueOf((int) newVal.doubleValue()));
	    });

	    Button saveButton = new Button("Set Score");
	    saveButton.setOnAction(event -> {
	    	ArrayList<Poster> postersRatedByUser = user.getPostersRatedByUser();
	        //int posterId = poster.getUniqueId();
	        
	        if (!postersRatedByUser.contains(poster)) {
	            int sliderValue = (int) slider.getValue();
	            user.addToPostersRatedByUser(poster);
	            poster.setScore(((poster.getScore() * poster.getNumberofRatings()) + sliderValue) / (poster.getNumberofRatings() + 1));
	            poster.setNumberofRatings(poster.getNumberofRatings() + 1);
	            
	            PosterProfileScene posterPage = PosterProfileScene.getInstance();
	            try {
	                posterPage.start(primaryStage);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } else {
	            showAlert("Unable to rate poster", poster.getDisplayName() + " has already been rated", AlertType.ERROR);
	        }
	    	
	    });

	    slide.getChildren().addAll(slider, scoreBox, saveButton);
	    return slide;
	}


	public static HBox sponsoredDogView (Dog d, Stage stage, Hashtable < Integer, Poster > poster, AppData
			appdata, SponsoredDogsScene page, Double paymentAmount){
		ImageView img = Components.imageView(200, 200);
		img.setImage(new Image(d.getImagePath()));

		Label primaryInfoLabel = Components.mediumLabel(d.getName() + ", " + d.getAge() + " years, " + d.getSex(), Pos.CENTER);
		Label sponsorshipAmount = Components.smallLabel("Sponsorship amount: " + paymentAmount,Pos.CENTER);
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
				sponsorshipAmount,
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

			appdata.getUser().getWallet().removeRecurringPayment(d.getId());
			setIsClicked(true);
			try {
				makePayment(appdata, howOftenBox.getValue(), howMuchMoney,howOftenBox,d);

				info.getChildren().removeAll(howMuch, howMuchMoney,howOften, howOftenBox, SetNewRecurring);
			} catch (FundsTooLow e) {
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

		}
	}
	
	
}