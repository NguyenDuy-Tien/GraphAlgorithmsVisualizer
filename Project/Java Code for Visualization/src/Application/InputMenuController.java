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

import Algorithm.Graph;
import Elements.DirectedEdge;
import Elements.Edge;
import Elements.EdgeFactory;
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
	protected TextField fileLinkField;
	@FXML
	private ToggleGroup GraphType;
	// What type of Edge we're creating for the graph
	protected String edgeDirection;
	
	private HashMap<RadioButton, EventHandler<Event>> buttonGraphTypeAction;
	// Get a Graph from text input
	// A Graph in Text input has the form:
	// vertice_num edge_num
	// begin1 end1 weight1
	// begin2 end2 weight2
	// ...
	// Invalid input will give you a null object, so be careful!
	
	EventHandler<Event> directedHandler = new EventHandler<Event>() {

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			String[] algo = {"Dijkstra"};
			AlgorithmBox.getItems().clear();
			AlgorithmBox.setItems(FXCollections.observableArrayList(algo));
			graphData.setText("");
		}
	};
	
	EventHandler<Event> undirectedHandler = new EventHandler<Event>() {

		@Override
		public void handle(Event event) {
			// TODO Auto-generated method stub
			String[] algo = {"Dijkstra", "Kruskal MST", "Prim MST"};
			AlgorithmBox.getItems().clear();
			AlgorithmBox.setItems(FXCollections.observableArrayList(algo));
			graphData.setText("");
		}
	};
	public Graph getGraphFromInput(String graphText, String EdgeType) {
		// Split data from String Input
		String[] splitdata = graphText.split("\\s+");
		int number_of_vertices = Integer.valueOf(splitdata[0]);
		int number_of_edges = Integer.valueOf(splitdata[1]);
		if (splitdata.length > (3*number_of_edges + 2))
		{
			showAlert("Invalid data.", "(First line has 2 number N,M - number of vertices and number of edges; the next M lines each have a combination u, v, w: begin - end - weight)");
			return null;
		}
		
		// Add Vertices to the graph and
		// Randomize their positions on the screen
		Graph graph = new Graph();
		for(int i = 0; i < number_of_vertices; ++i) {
			Random random = new Random();
			Vertex vertex = new Vertex((random.nextDouble() * 1000000) % 580 , (random.nextDouble() * 1000000) % 450, 12.0);
			graph.addVertex(vertex);
		}
		
		// Adding egdes to the graph
		for (int i = 0; i < number_of_edges; i++){
			int u, v;
			int w;
			u = Integer.valueOf(splitdata[2 + 3*i]);
			v = Integer.valueOf(splitdata[3 + 3*i]);
			w = Integer.valueOf(splitdata[4 + 3*i]);
			Vertex beginVertex = graph.get_vertices().get(u - 1);
			Vertex endVertex = graph.get_vertices().get(v - 1);
			graph.addEdge(EdgeFactory.create(EdgeType, beginVertex, endVertex, w));
		}
		return graph;
	}
	
	// Get the graph input from a file
	// The format of the file is described at getGraphFromInput
	public Graph getInputFromFile(File inputFile) {
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
		return getGraphFromInput(string.toString(), edgeDirection);
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
			fileLinkField.setText(file.getAbsolutePath().toString());
			// TODO: there should be something to catch the input here 
			getInputFromFile(file);
		}
	}
	
	
	
	// Binding Function for START DRAWING button
	public void validate()
	{
		boolean valid = true;
		if (AlgorithmBox.getSelectionModel().getSelectedItem() == null) {
			showAlert("Algorithm not selected", "Please choose algorithm");
			return;
		}
			
		try {
			String graphText = graphData.getText().trim();
			if (!graphText.equals(""))
			{
				getGraphFromInput(graphText, edgeDirection);
			}
			System.out.println("GraphText: " + graphText + valid);
			this.loadNextScene();
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
		try {
			root = loader.load();
			Main.primaryStage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		buttonGraphTypeAction = new HashMap<RadioButton, EventHandler<Event>>();
		buttonGraphTypeAction.put(directedBox,directedHandler);
		buttonGraphTypeAction.put(undirectedBox,undirectedHandler);
		buttonGraphTypeAction.get((RadioButton) GraphType.getSelectedToggle());
	}
}
