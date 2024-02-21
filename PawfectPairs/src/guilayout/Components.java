package guilayout;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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

	
	public static Button button(String text) {
		Button button = new Button(text);
//		button.setAlignment(pos);
		
		button.setFont(Font.font(font, fontMd));
		
		return button;
	}
	
	public static HBox navTab(UserProfile userScene, Stage stage) { //create a navigation tab: settings, schedule, messages, etc
		// settings hBox
        HBox navTab = new HBox();
        navTab.setStyle("-fx-background-color: #f5f5f5;");
        
        Button settingsButton = Components.button("Settings ⚙");
        settingsButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        settingsButton.setOnAction(event -> {
        	userScene.start(stage);
        });
        
        navTab.getChildren().addAll(settingsButton);
        
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

	
}
