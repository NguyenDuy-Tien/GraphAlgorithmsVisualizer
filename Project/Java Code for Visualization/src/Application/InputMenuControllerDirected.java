package Application;

import java.net.URL;
import java.util.ResourceBundle;

import Elements.EdgeFactory;
import javafx.collections.FXCollections;

public class InputMenuControllerDirected extends InputMenuController{
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] algo = {"Dijkstra"};
		
		AlgorithmBox.getItems().clear();
		AlgorithmBox.setItems(FXCollections.observableArrayList(algo)); 
		edgeDirection = EdgeFactory.DIRECTED;
		graphData.setText("");
	}
}
