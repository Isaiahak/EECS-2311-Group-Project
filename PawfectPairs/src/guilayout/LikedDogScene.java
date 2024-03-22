package guilayout;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.PriorityQueue;

import backend.dog.Dog;


public class LikedDogScene extends PrimaryScene{
	private static LikedDogScene instance;
	private int dogsDisplayed;

	public static LikedDogScene getInstance() {
		if (instance == null) {
			instance = new LikedDogScene();		
		}
		return instance;
	}

	private LikedDogScene() {
		
	}
	
	@Override
	public void start(Stage stage){	
		Components.updateCurrentScene("likedDogs");
		initailizePrimaryScene(stage);
		ObservableList<Dog> smallerDogs = FXCollections.observableArrayList();
		dogsDisplayed = 0;
		
		ArrayList<Dog> likedDogs = user.getLikedDogs();
		initializeSmallerDogList(likedDogs,smallerDogs);
		
		Label likedDogsLabel = Components.largeLabel("Dogs you've Liked: " + likedDogs.size(), Pos.CENTER);
		mainContainer.getChildren().add(likedDogsLabel);
	    
		smallerDogs.addListener(new ListChangeListener<Dog>(){
			@Override
	        public void onChanged(ListChangeListener.Change<? extends Dog> change) {
				while (change.next()) {
					if(change.wasAdded()) {
		                for (int i = dogsDisplayed; i < smallerDogs.size(); i++) {
		                	if (i == 0) {
		                		mainContainer.getChildren().add(Components.likedDogView(smallerDogs.get(i), stage, appData.getPosters()));
		                	}
		                	else {
		                		mainContainer.getChildren().add(Components.likedDogView(smallerDogs.get(i-1), stage, appData.getPosters()));		           
		                	} 	
		                }
					}
				}                 
	        }
		});
		
    	for(Dog d : smallerDogs) {
    		mainContainer.getChildren().add(Components.likedDogView(d, stage, appData.getPosters()));
    	}
    	
    	
    	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
    		@Override 
	        public void handle(MouseEvent e) { 
				if(scrollPane.getVvalue() > 0.8 && smallerDogs.size() != likedDogs.size()) {
					increaseSmallerDogList(likedDogs,smallerDogs);
				}
	        }
    	};
    	
		EventHandler<ScrollEvent> scrollHandler = new EventHandler<ScrollEvent>(){
			
			@Override 
	        public void handle(ScrollEvent e) { 
				if(scrollPane.getVvalue() > 0.8 && smallerDogs.size() != likedDogs.size()) {
					increaseSmallerDogList(likedDogs,smallerDogs);
				}
	        }
		};
		
		scrollPane.addEventFilter(ScrollEvent.ANY, scrollHandler);
		scrollPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseHandler);
		
		
		stage.show();
		
	}
	
	public void increaseSmallerDogList(ArrayList<Dog> allDogs, ObservableList<Dog> currentList){
		int additionalDogs =   (allDogs.size() - currentList.size() >  10 && allDogs.size() - currentList.size() > 0 )? 10 : allDogs.size() - currentList.size();
		int originalSize = currentList.size();
		for(int i = currentList.size(); i < originalSize + additionalDogs; i++) {
			dogsDisplayed = currentList.size();
			currentList.add(allDogs.get(i-1));
		}
	}
	
	public void initializeSmallerDogList( ArrayList<Dog> allDogs, ObservableList<Dog> currentList){
		for(int i = 0; i < 20; i++) {
			dogsDisplayed = currentList.size();
			currentList.add(allDogs.get(i));
		}
	}
	
}
