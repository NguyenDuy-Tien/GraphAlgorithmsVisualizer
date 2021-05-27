package Application;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;

import Algorithm.Graph;
import Elements.DirectedEdge;
import Elements.Edge;
import Elements.UndirectedEdge;
import Elements.Vertex;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class InputMenuController implements Initializable{
	@FXML
	ToggleGroup GraphType;
	@FXML
	RadioButton directedBox, undirectedBox;
	@FXML
	ComboBox AlgorithmBox;
	@FXML
	TextArea graphData;
	public static Graph graph = new Graph();
	public static boolean undirected = false, directed = false;
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
		boolean valid = true;
		if(!graph.get_vertices().isEmpty()) {
			graph.resetGraph();
		}
		if (directedBox.isSelected())
		{
			undirected = false;
			directed = true;
			if (AlgorithmBox.getSelectionModel().getSelectedItem() == null) {
				valid = false;
				showAlert("Algorithm not selected", "Please choose algorithm");
			}
			else if (AlgorithmBox.getSelectionModel().getSelectedItem().toString().contains("Kruskal")||
					AlgorithmBox.getSelectionModel().getSelectedItem().toString().contains("Prim"))
			{
				showAlert("Invalid algorithm", "Only Dijkstra can be implemented with directed graph");
				valid = false;
			}
		}
		if (undirectedBox.isSelected())
		{
			directed = false;
			undirected = true;
			if (AlgorithmBox.getSelectionModel().getSelectedItem() == null) {
				valid = false;
				showAlert("Algorithm not selected", "Please choose algorithm");
			}
			else valid = true;
		}
		
		try {
			String graphText = graphData.getText().trim();
			if (!graphText.equals(""))
			{
				String[] splitdata = graphText.split("\\s+");
				int number_of_vertices = Integer.valueOf(splitdata[0]);
				int number_of_edges = Integer.valueOf(splitdata[1]);
				if (splitdata.length > (3*number_of_edges + 2))
				{
					showAlert("Invalid data.", "(First line has 2 number N,M - number of vertices and number of edges; the next M lines each have a combination u, v, w: begin - end - weight)");
				}
				graph = new Graph();
				for(int i = 0; i < number_of_vertices; i++) {
					Random random = new Random();
					Vertex vertex = new Vertex(random.nextDouble() * 500 , random.nextDouble() * 400 - random.nextDouble()*40, 12.0);
					graph.addVertex(vertex);
				}
				for (int i = 0; i < number_of_edges; i++){
					int u, v;
					double w;
					u = Integer.valueOf(splitdata[2 + 3*i]);
					v = Integer.valueOf(splitdata[3 + 3*i]);
					w = Integer.valueOf(splitdata[4 + 3*i]);
					Vertex beginVertex = graph.get_vertices().elementAt(u - 1);
					Vertex endVertex = graph.get_vertices().elementAt(v - 1);
					if(undirected) {
						UndirectedEdge edge = new UndirectedEdge(beginVertex, endVertex, w);
						graph.addEdge(edge);
					}
					else if(directed) {
						DirectedEdge edge = new DirectedEdge(beginVertex, endVertex, w);
						graph.addEdge(edge);
					}
				}
				
			}
			System.out.println("GraphText: " + graphText + valid);
			if(valid == true) this.loadNextScence();
		}
		catch (Exception exception)
		{
			showAlert("Invalid data", "(First line has 2 number N,M - number of vertices and number of edges; the next M lines each have a combination u, v, w: begin - end - weight)");
			exception.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String[] algo = {"Dijkstra", "Kruskal MST - for undirected only", "Prim MST - for undirected only"};
		AlgorithmBox.getItems().clear();
		AlgorithmBox.setItems(FXCollections.observableArrayList(algo));
		graphData.setText("");
	}
	public void loadNextScence() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphAction.fxml"));
        Parent root;
		try {
			root = loader.load();
			Scene newScene = new Scene(root);
	        InputMenu.primaryStage.setScene(newScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
