package Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;

import Elements.DirectedEdge;
import Elements.Edge;
import Elements.EdgeFactory;
import Elements.Graph;
import Elements.UndirectedEdge;
import Elements.Vertex;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InputMenuController implements Initializable{
	
	// THESE PROTECTED FIELDS WILL BE SET
	// BY THEIR SUBCLASS
	//@FXML
	//protected ToggleGroup GraphType;
	private Stage PrimaryStage;
	
	@FXML
	protected RadioButton directedBox, undirectedBox;
	@FXML
	protected ComboBox<String> AlgorithmBox;
	@FXML
	protected TextArea graphData;
	@FXML
	protected JFXButton selectFileButton;
	@FXML
	protected Label filePathLabel;
	@FXML
	private ToggleGroup GraphType;
	// What type of Edge we're creating for the graph
	//protected String edgeDirection = new String();
	private HashMap<RadioButton, EventHandler<ActionEvent>> buttonGraphTypeAction;
	
	
	// Get a Graph from text input
	// A Graph in Text input has the form:
	// vertice_num edge_num
	// begin1 end1 weight1
	// begin2 end2 weight2
	// ...
	// Invalid input will give you a null object, so be careful!
	EventHandler<ActionEvent> directedHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			AlgorithmBox.getItems().clear();
			String[] algo = {"Dijkstra"};
			AlgorithmBox.setItems(FXCollections.observableArrayList(algo));
			graphData.setText("");
			GraphPropertyHolder.setEdgeDirection("directed");
		}
	};
	
	EventHandler<ActionEvent> undirectedHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			AlgorithmBox.getItems().clear();
			String[] algo = {"Dijkstra", "Kruskal MST", "Prim MST"};
			AlgorithmBox.setItems(FXCollections.observableArrayList(algo));
			graphData.setText("");
			GraphPropertyHolder.setEdgeDirection("undirected");
		}
	};
	
	// Get data from graphData 
	// And then put it in the GraphPropertyHolder
	public boolean getGraphFromInput(String graphText, String EdgeType) {
		// Split data from String Input
		String[] splitdata = graphText.split("\\s+");
		int number_of_vertices = Integer.valueOf(splitdata[0]);
		int number_of_edges = Integer.valueOf(splitdata[1]);
		if (splitdata.length > (3*number_of_edges + 2))
		{
			showAlert("Invalid data.", "(First line has 2 number N,M - number of vertices and number of edges; the next M lines each have a combination u, v, w: begin - end - weight)");
			return false;
		}
		
		// Add Vertices to the graph and
		// Randomize their positions on the screen
		Graph graph = new Graph();
		for(int i = 0; i < number_of_vertices; ++i) {
			Random random = new Random();
			Vertex vertex = new Vertex((random.nextDouble() * 1000000) % 820 , (random.nextDouble() * 1000000) % 580, 18.0);
			graph.addVertex(vertex);
			
		}
		
		// Adding edges to the graph
		for (int i = 0; i < number_of_edges; i++){
			int u, v;
			int w;
			u = Integer.valueOf(splitdata[2 + 3*i]);
			v = Integer.valueOf(splitdata[3 + 3*i]);
			w = Integer.valueOf(splitdata[4 + 3*i]);
			Vertex beginVertex = graph.get_vertices().get(u - 1);
			Vertex endVertex = graph.get_vertices().get(v - 1);
			if(! graph.isExistsEdge(beginVertex, endVertex)) 
					graph.addEdge(EdgeFactory.create(EdgeType, beginVertex, endVertex, w));
			else {
				showAlert("Invalid data", "Duplicate edge:  " 
							+ beginVertex.getID() + " "+ endVertex.getID());
				graph.resetGraph();
				return false;
			}
		}
		
		GraphPropertyHolder.setGraph(graph);
		return true;
	}
	
	
	// Get the graph input from a file
	// Write it to the graphData TextBox
	// The format of the file is described at getGraphFromInput
	public void getInputFromFile(File inputFile) {
		StringBuilder string = new StringBuilder();
		Scanner reader;
		try {
			reader = new Scanner(inputFile);
			while(reader.hasNextLine()) {
				String dataString = reader.nextLine();
				string.append(dataString).append("\n");
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally
		{
			// Should there be close here?
			// But Eclipse keeps popup error
			//reader.close();
		}
		graphData.setText(string.toString());
	}
	
	// Popup some stupid alerts on the screen
	public static void showAlert(String title, String message)
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(title);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public void chooseFile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(null);
		if(file != null) {
			getInputFromFile(file);
		}
	}
	
	
	
	// Binding Function for START DRAWING button
	public void validate()
	{
		boolean valid = true;	// used to print graphTexts validity 
		if (AlgorithmBox.getSelectionModel().getSelectedItem() == null) {
			showAlert("Algorithm not selected", "Please choose algorithm");
			return;
		}
		try {
			String graphText = graphData.getText().trim();
			if (!graphText.equals(""))
			{
				//TODO: Pass this input 
				// to GraphPropertyHolder 
				if(! getGraphFromInput(graphText, GraphPropertyHolder.getEdgeDirection())){
					valid = false;
				}
			}
			
			System.out.println("GraphText: " + graphText + valid);
			if(valid) this.loadNextScene();
		}
		catch (Exception exception)
		{
			showAlert("Invalid data", "(First line has 2 number N,M - number of vertices and number of edges; the next M lines each have a combination u, v, w: begin - end - weight)");
			exception.printStackTrace();
		}
	}
	
	// Create a chain of responsibilities between classes
	// After one Scene is done, load next scene
	public void loadNextScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphAction.fxml"));
        Parent root;
        GraphPropertyHolder.setAlgorithmName(AlgorithmBox.getSelectionModel().getSelectedItem());
        GraphPropertyHolder.setEdgeDirection(((RadioButton)GraphType.getSelectedToggle()).getText());
		try {
			root = loader.load();
			Main.primaryStage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Set default option of edge types and algorithms available:
		undirectedBox.setSelected(true);
		undirectedHandler.handle(null);	// Do a "Fake" click. I hope this work
		//AlgorithmBox.getItems().clear();
		//String[] algo = {"Dijkstra", "Kruskal MST", "Prim MST"};
		//AlgorithmBox.setItems(FXCollections.observableArrayList(algo));
		//graphData.setText("");
		
		directedBox.setOnAction(directedHandler);
		undirectedBox.setOnAction(undirectedHandler);
	}
}
