package guilayout;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.database.Database;
import backend.dog.Dog;
import backend.dog.trait.Age;
import backend.dog.trait.Attribute;
import backend.dog.trait.EnergyLevel;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Components{
	/*
	 * GUI components contained here to streamline GUI process and create more modular, thematic GUI parts
	 */
	
//	public static int screenHeight = 1024;
//	public static int screenWidth = 1280; 
	
	public static int screenHeight =  (int) Screen.getPrimary().getVisualBounds().getHeight();
	public static int screenWidth =  (int) Screen.getPrimary().getVisualBounds().getWidth(); 
	
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
	
	public static HBox navTab(UserProfile userScene, LikedDogScene likedDog, DogProfileScene dogProfile, BookedAppointmentScene appointmentScene, Stage stage) { //create a navigation tab: settings, schedule, messages, etc
		// settings hBox
	    HBox navTab = new HBox();
	    navTab.setStyle("-fx-background-color: #f5f5f5;");
	    navTab.setSpacing(20);

	    Button settingsButton = Components.button("⚙ Settings ⚙");
	    Button dogProfileButton = Components.button("🐕 Dog Profiles 🐕");
	    Button likedDogButton = Components.button("♥ Liked Dogs  🐶");
	    Button appointmentsButton = Components.button("📅 Appointments 📅");

	    settingsButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    likedDogButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    dogProfileButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    appointmentsButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

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

	    navTab.getChildren().addAll(settingsButton, dogProfileButton, likedDogButton, appointmentsButton);

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
	
	public static Label tagLabel(String tag,Tag labelTag, Dog dog, ArrayList<Tag> dogTags) {
		
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
		
		
		if(dogTags.contains(labelTag) == true) {
			
			label.setStyle(highLightedStyle);
		}
		else {
			label.setStyle(defaultStyle);
		}
		
		label.setOnMouseClicked(event -> {
            // Toggle background color on click
            if (label.getStyle().equals(defaultStyle)) {
            	label.setStyle(highLightedStyle); // highlight if not highlighted
            	if(dog.getTags().contains(labelTag) == false) {
            		dog.getTags().add(labelTag);
            		Database.setDogTags(dog.getTags(), dog.getId());
            	}
            	
            	
            } else {
            	if(dog.getTags().contains(labelTag) == true) {
            		dog.getTags().remove(labelTag);
            	}
            	label.setStyle(defaultStyle);
            	Database.removeDogTags(dog.getId(), Database.getTagID(labelTag.getTagName()), "idealdogtag");
            	
            	
            }
        });
		
		
		return label;
	}
	
	public static GridPane createTags(ArrayList<Tag> tags, Dog dog) {
		GridPane gridPane = new GridPane();
		int row = 0;
        int col = 0;
        
        int maxRows = 5;
        
        int i = 0; // current index
        
        ArrayList<Tag> dogTags = Database.getDogTags(dog.getId());
        
		for(Tag t : tags) {
			
			Label label = tagLabel(t.getTagName(),t, dog, dogTags);
			
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
	
	public static GridPane createTags(ArrayList<Tag> tags) { // non highlightable tags
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10); // Set horizontal gap
        gridPane.setVgap(10); // Set vertical gap
		int row = 0;
        int col = 0;
        
        int maxRows = 6;
        
        int i = 0; // current index
        
		for(Tag t : tags) {
			
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
	
	public static Label attributeLabel(String name, GridPane gridPane,Attribute dogAttribute, Dog dog, int weight) {
		Label label = new Label(name);
		label.setFont(Font.font(font, fontSm));
		label.setWrapText(true);
		label.maxWidth(50);
		ObservableList<Node> labels = gridPane.getChildren();
		
		String defaultStyle = 
				"-fx-background-color: #e1fcf6;" + // Background color
		        "-fx-padding: 10px;" + // Padding
		        "-fx-border-radius: 10px;" + // Border radius
//		        "-fx-border-color: navy;" + // Border color
//		        "-fx-font-size: 14px;" + // Font size
		        "-fx-alignment: center;"; // Text alignment;
		
		String highLightedStyle = 
				"-fx-background-color: #98f5e1;" + // Background color
		        "-fx-padding: 10px;" + // Padding
		        "-fx-border-radius: 10px;" + // Border radius
//		        "-fx-border-color: navy;" + // Border color
//		        "-fx-font-size: 14px;" + // Font size
		        "-fx-alignment: center;"; // Text alignment;
		
		//function to be able to turn the label highlighted when loading them if dog attribute matches.
		if(dogAttribute.getWeight() == weight) {
			label.setStyle(highLightedStyle);
		}
		else {
			label.setStyle(defaultStyle);
		}
		
		label.setOnMouseClicked(event -> {	
            if (label.getStyle().equals(defaultStyle)) {
            	for(Node l : labels) {
            		if (l.getStyle().equals(highLightedStyle)) {
            			l.setStyle(defaultStyle);
            		}
            	}
            	label.setStyle(highLightedStyle); 
            	dogAttribute.setName(label.getText());
            	Database.changeAttribute(dogAttribute, dog.getId(), weight);
            } 
        });
		
		
		return label;
	}
	
	public static GridPane createAttribute(Attribute dogAttribute, Dog dog) {
		GridPane gridPane = new GridPane();
		String[] names = dogAttribute.getNames();
		for(int j = 0; j < names.length; j++) {
			// Add the label to the grid
			Label label = attributeLabel(names[j], gridPane, dogAttribute, dog, j );
            gridPane.add(label, j,0);
            
		} 
		return gridPane;
	}
	
	//Sidney, Edson and Connor were here :) 
	public static HBox appointmentView(Dog dog,Date date, Stage primaryStage) {
        ImageView img = Components.imageView(200, 200);
        img.setImage(new Image(dog.getImagePath()));

        Label primaryInfoLabel = Components.mediumLabel(dog.getName() + ", " + dog.getAge() + " years, " + dog.getSex(), Pos.CENTER);
        Label appointmentDate = Components.mediumLabel("Appointment Date: " + date.toString(),Pos.CENTER);

        Hyperlink rescheduleLink = hyperlinkToReschedule(dog, primaryStage);
        
        Hyperlink cancelLink = hyperlinkToCancelAppointment(dog, primaryStage);

        VBox info = new VBox(primaryInfoLabel, appointmentDate, rescheduleLink, cancelLink);
        HBox HBox = new HBox(img, info);
        HBox.setAlignment(Pos.CENTER);
        HBox.setSpacing(50);

        return HBox;
    }
	
	public static HBox likedDogView(Dog dog, Stage primaryStage) {

		ImageView img = Components.imageView(200, 200);
		img.setImage(new Image(dog.getImagePath()));
		
		Label primaryInfoLabel = Components.mediumLabel(dog.getName() + ", " + dog.getAge() + " years, " + dog.getSex(),Pos.CENTER); 
		
		Hyperlink posterLink = hyperlinkToPosterProfile(dog, primaryStage);
		Hyperlink appointmentLink = hyperlinkToAppointment(dog, primaryStage);
		
		
		VBox info = new VBox(
				primaryInfoLabel,
				posterLink, appointmentLink
				);
		
		HBox HBox = new HBox(img, info);
		HBox.setAlignment(Pos.CENTER);
		HBox.setSpacing(50);
		
		
		return HBox;
		
		
	}
	
	public static Hyperlink hyperlinkToPosterProfile(Dog dog, Stage primaryStage) {
		Hyperlink posterLink = Components.hyperlink();
		posterLink.setText(Database.getPosterById(dog.getPosterId()).getDisplayName());
		
		Poster poster = Database.getPosterById(dog.getPosterId());
		PosterProfileScene posterProfile = PosterProfileScene.getInstance();
		
	
		posterLink.setOnAction(event -> {
        	try {
        		posterProfile.setCurrentPoster(poster);
				posterProfile.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
		
		return posterLink;
	}
	
	public static Hyperlink hyperlinkToCancelAppointment(Dog dog, Stage primaryStage) {
		AppointmentManager userManager = AppointmentScene.getInstance().getUserAppointments();
		ArrayList<Appointment> userAppointments = userManager.getUserAppointments();
		
		
		Hyperlink appointmentLink = Components.hyperlink();
		appointmentLink.setText("Cancel");//Database.getPosterById(dog.getPosterId())
		
		Poster poster = Database.getPosterById(dog.getPosterId());
		//PosterProfileScene posterProfile = PosterProfileScene.getInstance();
		//Dog selectedDog = Database.getADog(dog.getId());
		AppointmentScene appointmentPage = AppointmentScene.getInstance();
		
	
		appointmentLink.setOnAction(event -> {
        	try {
        		
        		for (Appointment appointment : userAppointments) {
        	        if (appointment.getDogID() == dog.getId()) {
        	        	userManager.removeAppointment(appointment);
        	        	BookedAppointmentScene bookedPage = BookedAppointmentScene.getInstance();
                		bookedPage.start(primaryStage);
        	        	
        	        }
        		}
        		//Database.deleteAppointment(poster.getUniqueId(),dog.getId());
        		
        		//BookedAppointmentScene bookedPage = BookedAppointmentScene.getInstance();
        		//bookedPage.start(primaryStage);
        		
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
		
		return appointmentLink;
	}
	public static Hyperlink hyperlinkToReschedule(Dog dog, Stage primaryStage) {
		AppointmentManager userManager = AppointmentScene.getInstance().getUserAppointments();
		ArrayList<Appointment> userAppointments = userManager.getUserAppointments();
		
		Hyperlink appointmentLink = Components.hyperlink();
		appointmentLink.setText("Reschedule");//Database.getPosterById(dog.getPosterId())
		
		Poster poster = Database.getPosterById(dog.getPosterId());
		//PosterProfileScene posterProfile = PosterProfileScene.getInstance();
		Dog selectedDog = Database.getADog(dog.getId()); //need to change to local Call
		AppointmentScene appointmentPage = AppointmentScene.getInstance();
		
	
		appointmentLink.setOnAction(event -> {
        	try {
        		
        		for (Appointment appointment : userAppointments) {
        	        if (appointment.getDogID() == dog.getId()) {
        	        	userManager.removeAppointment(appointment);
        	        	appointmentPage.setCurrentPosterDog(poster,dog);
                		appointmentPage.start(primaryStage);
        	        	
        	        }
        		}
        		//Database.deleteAppointment(poster.getUniqueId(),dog.getId());
        		//appointmentPage.setCurrentPosterDog(poster,dog);
        		//appointmentPage.updateMeetWithLabel(poster, selectedDog);
        		//appointmentPage.start(primaryStage);
        		
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
		
		return appointmentLink;
	}
	
	
	public static Hyperlink hyperlinkToAppointment(Dog dog, Stage primaryStage) {
		Hyperlink appointmentLink = Components.hyperlink();
		appointmentLink.setText("Meet me!");//Database.getPosterById(dog.getPosterId())
		
		Poster poster = Database.getPosterById(dog.getPosterId());
		//PosterProfileScene posterProfile = PosterProfileScene.getInstance();
		//Dog selectedDog = Database.getADog(dog.getId());
		AppointmentScene appointmentPage = AppointmentScene.getInstance();
		
	
		appointmentLink.setOnAction(event -> {
        	try {
        		appointmentPage.setCurrentPosterDog(poster,dog);
        		//appointmentPage.updateMeetWithLabel(poster, selectedDog);
        		appointmentPage.start(primaryStage);
        		
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
		
		return appointmentLink;
	}

	public static HBox posterDogView(Dog dog) {

		ImageView img = Components.imageView(200, 200);
		img.setImage(new Image(dog.getImagePath()));
		
		Label primaryInfoLabel = Components.mediumLabel(dog.getName() + ", " + dog.getAge() + " years, " + dog.getSex(),Pos.CENTER); 
//		Hyperlink posterLink = Components.hyperlink();
		
//		posterLink.setText(dog.getPoster().getDisplayName());
		
		
		VBox info = new VBox(
				primaryInfoLabel
//				posterLink
				);
		HBox ret = new HBox(img, info);
		ret.setSpacing(50);
//		ret.setAlignment(Pos.CENTER);
		
		return ret ;
//		posterLink.setOnAction(event -> { // implement later
//        	try {
//				posterProfile.start(primaryStage);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        });
		
	}

}
