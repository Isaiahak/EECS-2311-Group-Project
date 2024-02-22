package guilayout;

import java.util.ArrayList;
import java.util.Arrays;

import backend.dog.Dog;
import backend.dog.Attributes.Age;
import backend.dog.Attributes.Attribute;
import backend.dog.Attributes.EnergyLevel;
import backend.dog.Attributes.Sex;
import backend.dog.Attributes.Size;
import backend.tag.Tag;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Components{
	/*
	 * GUI components contained here to streamline GUI process and create more modular, thematic GUI parts
	 */
	
	public static int screenHeight = 1024;
	public static int screenWidth = 1280; 
	
	private static int fontSm = 20;
	private static int fontMd = 30;
	private static int fontLg = 50; 
	
	private static String font = "Verdana";
	
	
	/*
	 * Collecting lists of all tags and attributes to create dynamically updating GUI components
	 */
	
	public static ArrayList<Attribute> allAttributes = new ArrayList<Attribute>();
	public static ArrayList<Tag> allTags = new ArrayList<Tag>(Arrays.asList(
			new Tag("Friendly"),
			new Tag("Adventerous"),
			new Tag("Skittish"),
			new Tag("Good with Kids"),
			new Tag("Loyal"),
			new Tag("Intelligent"),
			new Tag("Aggressive"),
			new Tag("Special Needs"),
			new Tag("Couch Potato"),
			new Tag("Athletic"),
			new Tag("Hypoallergenic"),
			new Tag("Vocal"),
			new Tag("Calm"),
			new Tag("Playful"),
			new Tag("Protective"),
			new Tag("Shy"),
			new Tag("Smart"),
			new Tag("Independent"),
			new Tag("Affectionate"),
			new Tag("Aggressive"),
			new Tag("Trainable"),
			new Tag("Sociable"),
			new Tag("Quiet"),
			new Tag("Noisy"),
			new Tag("Easygoing"),
			new Tag("Anxious"),
			new Tag("Curious"),
			new Tag("Gentle"),
			new Tag("Stubborn"),
			new Tag("Well-behaved"),
			new Tag("High-maintenance"),
			new Tag("Low-maintenance"),
			new Tag("Good with other pets"),
			new Tag("Allergic"),
			new Tag("Lap dog"),
			new Tag("Water lover"),
			new Tag("Hypoallergenic"),
			new Tag("Therapy dog"),
			new Tag("Service dog"),
			new Tag("Good on a leash"),
			new Tag("Escape artist"),
			new Tag("House trained"),
			new Tag("Mellow"),
			new Tag("Frisbee enthusiast"),
			new Tag("High prey drive"),
			new Tag("Treat motivated"),
			new Tag("Vocal"),
			new Tag("Loves car rides")
			));
			
	
	public static String[] tagColours = {
			"#d9ed92",
			"#b5e48c",
			"#99d98c",
			"#76c893",
			"#52b69a",
			"#34a0a4",
			"#168aad"
		
	};
	
	public static Button button(String text) {
		Button button = new Button(text);
//		button.setAlignment(pos);
		
		button.setFont(Font.font(font, fontMd));
		
		return button;
	}
	
	public static HBox navTab(UserProfile userScene,LikedDogScene likedDog, DogProfileScene dogProfile, Stage stage) { //create a navigation tab: settings, schedule, messages, etc
		// settings hBox
        HBox navTab = new HBox();
        navTab.setStyle("-fx-background-color: #f5f5f5;");
        
        Button settingsButton = Components.button("Settings ⚙");
        Button likedDogButton = Components.button("♥ Liked Dogs  🐶");
        Button dogProfileButton = Components.button("Dog Profiles 🐕");
        settingsButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        likedDogButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        dogProfileButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        
        settingsButton.setOnAction(event -> {
        	userScene.start(stage);
        });
        likedDogButton.setOnAction(event -> {
        	likedDog.start(stage);
        });
        dogProfileButton.setOnAction(event -> {
        	dogProfile.start(stage);
        });
        
        navTab.getChildren().addAll(likedDogButton,dogProfileButton,settingsButton);
        
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
	public static Label mediumLabel(String text) {
		Label label = new Label(text);
//		label.setAlignment(pos);
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
	
	public static Label tagLabel(String tag) {
		Label label = new Label(tag);
		label.setFont(Font.font(font, fontSm));
		label.setWrapText(true);
		
		label.maxWidth(50);
		
		
		
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
		
		
		
		label.setStyle(defaultStyle);
		
		
		label.setOnMouseClicked(event -> {
            // Toggle background color on click
            if (label.getStyle().equals(defaultStyle)) {
            	label.setStyle(highLightedStyle); // highlight if not highlighted
            	//add tag to user here
            	
            } else {
            	label.setStyle(defaultStyle);
            	//remove tag from user
            	
            }
        });
		
		
		return label;
	}
	
	public static GridPane createTags(ArrayList<Tag> tags) {
		GridPane gridPane = new GridPane();
		int row = 0;
        int col = 0;
        
        int maxRows = 5;
        
        int i = 0; // current index
        
		for(Tag t : tags) {
			
			Label label = tagLabel(t.getTagName());
			
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
	
	public static Label attributeLabel(String name) {
		Label label = new Label(name);
		label.setFont(Font.font(font, fontSm));
		label.setWrapText(true);
		
		label.maxWidth(50);
		
		
		
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
		
		
		
		label.setStyle(defaultStyle);
		
		
		label.setOnMouseClicked(event -> {
            // Toggle background color on click
            if (label.getStyle().equals(defaultStyle)) {
            	label.setStyle(highLightedStyle); // highlight if not highlighted
            	//add tag to user here
            	
            } else {
            	label.setStyle(defaultStyle);
            	//remove tag from user
            	
            }
        });
		
		
		return label;
	}
	public static GridPane createAttribute(Dog dog) {
		GridPane gridPane = new GridPane();
		int row = 0;
        int col = 0;
          
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        attributes.add(new Age(0));
        attributes.add(new Size(0));
        attributes.add(new EnergyLevel(0));
        attributes.add(new Sex(0));
        
        int i = 0; // current index
        int maxRows;
		for(Attribute a : attributes ) {
			
			String[] names = a.getNames();
			row = 0;
			for(int j = 0; j < names.length; j++) {
				// Add the label to the grid
				
				Label label = attributeLabel(names[j]);
				
	            gridPane.add(label, row, col);
	            maxRows = names.length;
	            // Increment column and row counters
	            
				i++;
				row++;  	
			} 
			col++;
		}
		
		return gridPane;
		
	}
	
	
	
	
	
	
	
	
	
	public static HBox likedDogView(Dog dog) {
		
		
		
		
		ImageView img = Components.imageView(200, 200);
		img.setImage(new Image(dog.getImagePath()));
		
		Label primaryInfoLabel = Components.mediumLabel(dog.getName() + ", " + dog.getAge() + " years, " + dog.getSex()); 
		Hyperlink posterLink = Components.hyperlink();
		
		posterLink.setText(dog.getPoster().getDisplayName());
		
		
		VBox info = new VBox(
				primaryInfoLabel,
				posterLink
				);
		
		return new HBox(img, info);
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
