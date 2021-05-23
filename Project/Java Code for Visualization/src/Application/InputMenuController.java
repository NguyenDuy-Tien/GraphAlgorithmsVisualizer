package Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class InputMenuController implements Initializable{
	@FXML
	ToggleGroup GraphType;
	@FXML
	RadioButton directedBox, undirectedBox;
	@FXML
	ComboBox AlgorithmBox;
	public static void showAlert(String title, String message)
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(title);
		alert.setContentText(message);
		alert.showAndWait();
	}
	public void validate()
	{
		boolean valid = false;
		if (directedBox.isSelected())
		{
			if(AlgorithmBox.getSelectionModel().getSelectedItem() == null) {
				showAlert("Algorithm not found", "Please choose algorithm");
				valid = false;
			}
			else if (AlgorithmBox.getSelectionModel().getSelectedItem().toString().contains("Kruskal")||
				AlgorithmBox.getSelectionModel().getSelectedItem().toString().contains("Prim"))
			{
				showAlert("Invalid algorithm", "Only Dijkstra can be implemented with directed graph");
				valid = false;
			}
			else valid = true;
		}
		if (undirectedBox.isSelected())
		{
			if(AlgorithmBox.getSelectionModel().getSelectedItem() == null) {
				showAlert("Algorithm not found", "Please choose algorithm");
				valid = false;
			}
			else valid = true;
		}
		if(valid == true) loadNextScene();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String[] algo = {"Dijkstra", "Kruskal MST - for undirected only", "Prim MST - for undirected only"};
		AlgorithmBox.getItems().clear();
		AlgorithmBox.setItems(FXCollections.observableArrayList(algo));
		
	}
	 void loadNextScene() {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphAction.fxml"));
	            Parent root = loader.load();
	            Scene newScene = new Scene(root);
	            InputMenu.primaryStage.setScene(newScene);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
