package Application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
		if (directedBox.isSelected())
		{
			if (AlgorithmBox.getSelectionModel().getSelectedItem().toString().contains("Kruskal")||
				AlgorithmBox.getSelectionModel().getSelectedItem().toString().contains("Prim"))
			{
				showAlert("Invalid algorithm", "Only Dijkstra can be implemented with directed graph");
			}
		}
		if (undirectedBox.isSelected())
		{
			
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String[] algo = {"Dijkstra", "Kruskal MST - for undirected only", "Prim MST - for undirected only"};
		AlgorithmBox.getItems().clear();
		AlgorithmBox.setItems(FXCollections.observableArrayList(algo));
		
	}
}
