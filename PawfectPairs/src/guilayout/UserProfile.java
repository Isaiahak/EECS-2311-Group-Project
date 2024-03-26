package guilayout;

import backend.dog.trait.Attribute;
import backend.wallet.Wallet;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import backend.tag.Tag;

import static guilayout.Components.showAlert;


public class UserProfile extends PrimaryScene{
	private ArrayList<Attribute> oldAgePreferences;
	private ArrayList<Attribute> oldSexPreferences;
	private ArrayList<Attribute> oldSizePreferences;
	private ArrayList<Attribute> oldEnergyLevelPreferences;
	private Hashtable<Integer, Tag> oldTagPreferences;
	private Wallet wallet;
	double SingleMaxWalletDepositLimit=1000;//in dollars
	double ValueSelectedInScrollBar=0;
	private static UserProfile instance;

	public static UserProfile getInstance() {
		if (instance == null) {
			instance = new UserProfile();		
		}
		return instance;
	}
	
	private UserProfile() {
		
	}

	public static void main(String[] args) {
        launch(args);
    }

	@Override
    public void start(Stage primaryStage) {
		Components.updateCurrentScene("userProfile");

		initailizePrimaryScene(primaryStage);
		
    	HashMap<Integer, Tag> tags = appData.getallTags();
    	HashMap<Integer,ArrayList<Attribute>> allAttributes = appData.getAllAttributes();
    	wallet=user.getWallet();

    	oldSexPreferences = user.getCopyOfSexPreferences(user.getSexPreferences());
		oldAgePreferences = user.getCopyOfAgePreferences(user.getAgePreferences());
		oldEnergyLevelPreferences = user.getCopyOfEnergyLevelPreferences(user.getEnergyLevelPreferences());
		oldSizePreferences = user.getCopyOfSizePreferences(user.getSizePreferences());
		oldTagPreferences = user.getCopyOfTagPreferences(user.getTagPreferences());

    	VBox preferences = new VBox();
    	preferences.setAlignment(javafx.geometry.Pos.CENTER);

    	Label preferencesTitle = Components.mediumLabel("Tags", Pos.CENTER);
    	

    	preferences.getChildren().addAll(
    			preferencesTitle
    			);

    	GridPane tagsGrid =  Components.createTags(tags,user);
    	tagsGrid.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	VBox attributes = new VBox();
    	attributes.setAlignment(javafx.geometry.Pos.CENTER);

    	Label attributesTitle = Components.mediumLabel("Attributes", Pos.CENTER);
    	
    	Label sexAttributesTitle = Components.smallLabel("Sex", Pos.BASELINE_LEFT);
    	GridPane sexAttributeGrid = Components.createAttribute(user.getSexPreferences(), 1,allAttributes);
    	sexAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	
    	
    	Label sizeAttributesTitle = Components.smallLabel("Size",  Pos.BASELINE_LEFT);
    	GridPane sizeAttributeGrid = Components.createAttribute(user.getSizePreferences(), 3,allAttributes);
    	sizeAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	
    	  	
    	Label energyLevelAttributesTitle = Components.smallLabel("EnergyLevel",  Pos.BASELINE_LEFT);
    	GridPane energyLevelAttributeGrid = Components.createAttribute(user.getEnergyLevelPreferences(),2,allAttributes);
    	energyLevelAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	 	
    	
    	Label ageAttributesTitle = Components.smallLabel("Age",  Pos.BASELINE_LEFT);
    	GridPane ageAttributeGrid = Components.createAttribute(user.getAgePreferences(), 0,allAttributes);
    	ageAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	
    	

    	attributes.getChildren().addAll(
    	attributesTitle,
    	sizeAttributesTitle,
    	sizeAttributeGrid,
    	sexAttributesTitle,
		sexAttributeGrid,
		ageAttributesTitle,
		ageAttributeGrid,
		energyLevelAttributesTitle,
		energyLevelAttributeGrid
    	);

    	

		Button deposit = Components.button("Deposit funds into your wallet");
/*	//GETTING RID OF SCROLL BAR	
		ScrollBar scrollBar = new ScrollBar();
        //scrollBar.setStyle("-fx-pref-width: 1;");
       scrollBar.setStyle("-fx-pref-height: 20;");
        VBox scrollContainer = new VBox();   
        scrollContainer.setMargin(scrollBar, new Insets(20, 100, 20, 100));  // top, right, bottom, left
        scrollContainer.getChildren().addAll(scrollBar);
        // Set the range of the scroll bar
        scrollBar.setMin(0);
        scrollBar.setMax(SingleMaxWalletDepositLimit);//for now arbitrarily set max single deposit limit to 1000
*/        
        Label currentFunds =Components.mediumLabel();
        currentFunds.setText("Your current balance: "+ wallet.getBalance());
        /*	//GETTING RID OF SCROLL BAR	
//        // Add listener to capture value changes
//        scrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
//            valueLabel.setText("Selected Value: " + String.format("%.2f", newValue));
//            ValueSelectedInScrollBar=Double.parseDouble(String.format("%.2f", newValue));
//        });
*/
		TextField amount = new TextField();

        deposit.setOnAction(event -> {
        	boolean isValid=Components.checkInput(amount.getText());
        	if (!isValid)
    		{
				showAlert("Cannot enter non-numeric values ", "Please enter a number", AlertType.ERROR);
			amount.clear();
    		amount.setText("");}

    		else 
    		{
    			try {
					boolean result = (BigDecimal.valueOf(Double.valueOf(amount.getText())).scale() > 2);
					if(!result) {
						wallet.deposit(Double.parseDouble(amount.getText()));
						currentFunds.setText("Your current balance "+ wallet.getBalance());
					}
					else{
						showAlert("invalid dollar amount","please try entering a correct dollar amount.",Alert.AlertType.ERROR);
					}


				} catch (IllegalArgumentException e) {
					// TODO: handle exception
					showAlert("Cannot enter a negative number", "Please enter a non-negative number", AlertType.ERROR);
	    			amount.clear();
				}
    			

    		}

        	});
        
        Button signOutButton = new Button("sign out");
        
		VBox allWalletUserComponents = new VBox();
		allWalletUserComponents.setSpacing(30);
		allWalletUserComponents.getChildren().addAll( amount, currentFunds, deposit);
		allWalletUserComponents.setAlignment(Pos.TOP_CENTER);
		 // Set padding for the VBox (top, right, bottom, left)
		allWalletUserComponents.setPadding(new javafx.geometry.Insets(20, 10, 20, 10));
		
		//END OF WALLET UI STUFF
		mainContainer.getChildren().addAll(
				preferences,
				tagsGrid,
				attributes,
				allWalletUserComponents,
				signOutButton
		);

		primaryStage.show();
		

	}

	public ArrayList<Attribute> getOldEnergyLevelPreferences() {
		return oldEnergyLevelPreferences;
	}

	public ArrayList<Attribute> getOldSizePreferences() {
		return oldSizePreferences;
	}

	public ArrayList<Attribute> getOldSexPreferences() {
		return oldSexPreferences;
	}

	public ArrayList<Attribute> getOldAgePreferences() {
		return oldAgePreferences;
	}

	public Hashtable<Integer, Tag> getOldTagPreferences(){
		return oldTagPreferences;
	}
}