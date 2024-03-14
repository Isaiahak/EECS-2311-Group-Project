package guilayout;

import guicontrol.AppData;
import javafx.scene.layout.VBox;

public class GiveVBox {

	private static GiveVBox instance;
	private VBox box;

	public static GiveVBox getInstance() {
		if (instance == null)
			instance = new GiveVBox();
		return instance;
		}

	public VBox getBox() {
		return box;
	}

	public void setBox(VBox box) {
		this.box = box;
	}

	
}
