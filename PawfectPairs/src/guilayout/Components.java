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
import guicontrol.AppData;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
	
	
	/*
	 * Collecting lists of all tags and attributes to create dynamically updating GUI components
	 */
	
	public static ArrayList<Attribute> allAttributes = new ArrayList<Attribute>();
	
	public static Button button(String text) {
		Button button = new Button(text);
//		button.setAlignment(pos);
		
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
//		Button editButton = new Button("Edit Donation :D"); // to be implemented
			cancelButton.setOnAction(event -> {
				//System.out.println("before removal "+appdata.getUser().getWallet().getRecurringPaymentsDogsList().toString());
				appdata.getUser().getWallet().removeRecurringPayment(d.getId());
				//System.out.println("after removal "+ appdata.getUser().getWallet().getRecurringPaymentsDogsList().toString());
				// hacky way to reload page :)
				page.start(stage);

			});

			VBox info = new VBox(
					primaryInfoLabel,
					posterLink,
					cancelButton
			);
			HBox HBox = new HBox();
			HBox.getChildren().addAll(img, info);
			HBox.setAlignment(Pos.CENTER);
			HBox.setSpacing(50);
			return HBox;
		}
}
