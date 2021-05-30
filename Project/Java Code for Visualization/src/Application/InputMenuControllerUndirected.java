package Application;

import Elements.*;
import java.net.URL;
import java.util.ResourceBundle;

import Application.InputMenuController;
import javafx.collections.FXCollections;

public class InputMenuControllerUndirected extends InputMenuController{
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] algo = {"Dijkstra", "Kruskal MST", "Prim MST"};
		
		AlgorithmBox.getItems().clear();
		AlgorithmBox.setItems(FXCollections.observableArrayList(algo)); 
		edgeDirection = EdgeFactory.UNDIRECTED;
		graphData.setText("");
	}
}
