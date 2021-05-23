package Application;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Algorithm.Edge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.shape.Shape;

public class GraphController implements Initializable{

	@FXML
	private TextField textAlgorithm;
	@FXML
	private RadioButton addNodeButton;
	@FXML
	private RadioButton addEdgeButton;
	@FXML
	private Button backButton;
	@FXML
	private Button resetButton;
	@FXML
	private Button clearButton;
	@FXML
	private Button pauseButton;
//	List<Vertex> circles = new ArrayList<>();
//    List<Edge> mstEdges = new ArrayList<>(), realEdges = new ArrayList<>();
	
    List<Algorithm.Edge> mstEdges = new ArrayList<Edge>();
    List<Shape> edges = new ArrayList<>();
    boolean addNode = true, addEdge = false, calculate = false,
            calculated = false, playing = false, paused = false, pinned = false;
    List<Label> distances = new ArrayList<Label>(); //visitTime = new ArrayList<>(), lowTime = new ArrayList<Label>();
	
	public GraphController() {
		
	}

	

	public void AddNodeHandle(ActionEvent event) {
        addNode = true;
        addEdge = false;
        calculate = false;
        addNodeButton.setSelected(true);
        addEdgeButton.setSelected(false);
       
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
