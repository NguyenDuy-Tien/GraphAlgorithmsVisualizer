package Application;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Algorithm.Algorithm;
import Algorithm.Graph;
import Algorithm.Prim;
import Elements.Arrow;
import Elements.DirectedEdge;
import Elements.Edge;
import Elements.EdgeFactory;
import Elements.UndirectedEdge;
import Elements.Vertex;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class GraphController implements Initializable{

	private Stage PrimaryStage;
	private static String AlgorithmName;
	private Algorithm algorithm;
	
	@FXML
	private AnchorPane anchorRoot;
	@FXML
	private Label textAlgorithm;
	@FXML
	private RadioButton addNodeButton;
	@FXML
	private RadioButton addEdgeButton;
	@FXML
	private JFXButton backButton;
	@FXML
	private JFXButton resetButton;
	@FXML
	private JFXButton clearButton;
	@FXML
	private JFXButton runOneButton;
	@FXML
	private JFXButton runAllButton;
    @FXML
    private Group canvasGroup;
    @FXML 
    private Pane viewer;
    @FXML
	private ToggleGroup addType;
    @FXML
    private Label sourceText = new Label("Source ");
    @FXML
    private Arrow arrow;
    
    boolean menuBool = false;
    ContextMenu globalMenu;
	Vertex selectedVertex = null;

    Graph graph = new Graph();
	private String edgeDirection;
	
    boolean addNode = true, addEdge = false, calculate = false,
            calculated = false, playing = false, paused = false, pinned = false;
    
    int nNode = 0;
    
	public GraphController() {
	}

	public void handle(MouseEvent ev) {
        if (addNode) {
            if (nNode == 1) {
                addNodeButton.setDisable(false);
            }
            if (nNode == 2) {
                addEdgeButton.setDisable(false);
                AddNodeHandle(null);
            }

            if (!ev.getSource().equals(canvasGroup)) {
                if (ev.getEventType() == MouseEvent.MOUSE_RELEASED && ev.getButton() == MouseButton.PRIMARY) 
                {
                    nNode++;
                    Vertex vertex = new Vertex(ev.getX(), ev.getY(), 12.0);
                    canvasGroup.getChildren().addAll(vertex.drawableObjects());
                    graph.addVertex(vertex);
                    
                    vertex.setOnMousePressed(mouseHandler);
                    vertex.setOnMouseReleased(mouseHandler);
                    vertex.setOnMouseDragged(mouseHandler);
                    vertex.setOnMouseExited(mouseHandler);
                    vertex.setOnMouseEntered(mouseHandler);
                }
            }
        }
    }

	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            Vertex circle = (Elements.Vertex) mouseEvent.getSource();
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (!circle.isSelected) {
                    if (selectedVertex != null) {
                    	
                    	// Create new Edge if there does not exist an edge
                    	// in the direction chosen between two vertices
                        if (addEdge && !graph.isExistsEdge(selectedVertex, circle)) {
                            
                            //Adds the edge between two selected nodes
                            Edge newEdge = EdgeFactory.create(edgeDirection, selectedVertex, circle, 0);
                        	canvasGroup.getChildren().add(newEdge);
                    	
                            TextInputDialog dialog = new TextInputDialog("0");
                            dialog.setTitle(null);
                            dialog.setHeaderText("Enter Weight of the Edge :");
                            dialog.setContentText(null);

                            Optional<String> result = dialog.showAndWait();
                            
                            if (result.isPresent()) {
                            	newEdge.setWeight(Integer.parseInt(result.get()));
                            } 
                            else {
                            	newEdge.setWeight(0);
                            }

                            graph.addEdge(newEdge);
                                
                       }
                   if (addNode || addEdge) {
                        selectedVertex.isSelected = false;
                        selectedVertex.changeColorVertex(Color.BLACK);
                    }
                    selectedVertex = null;
                    return;
                }

                    circle.draw(Color.RED);
                    circle.isSelected = true;
                    selectedVertex = circle;
                    
                } 
                else {
                    circle.isSelected = false;
                    circle.draw(Color.BLACK);
                    selectedVertex = null;
                }

            }
        }
    };

	
    

    // Binding function for AddNode button
    @FXML
	public void AddNodeHandle(ActionEvent event) {
        addNode = true;
        addEdge = false;
        addNodeButton.setSelected(true);
        addEdgeButton.setSelected(false);
       
    }

    // Binding function for AddEdge button
    @FXML
    public void AddEdgeHandle(ActionEvent event) {
        addNode = false;
        addEdge = true;
        addNodeButton.setSelected(false);
        addEdgeButton.setSelected(true);
    }

    
    @FXML
    public void ClearHandle(ActionEvent event) {
        menuBool = false;
        selectedVertex = null;
        System.out.println("IN CLEAR:" + graph.get_vertices().size());
        
        canvasGroup.getChildren().removeAll(graph.drawableObjects());

        addNodeButton.setDisable(false);
        addEdgeButton.setDisable(false);
        AddNodeHandle(null);
    }

    // Reset Handle for handling Reset Button
    // Clear the graph - Lam
    @FXML
    public void ResetHandle(ActionEvent event) {
        ClearHandle(null);
        
        graph.resetGraph();
        nNode = 0;
        canvasGroup.getChildren().clear();
        canvasGroup.getChildren().addAll(viewer);
        selectedVertex = null;
        
        addNode = true;
        addEdge = false;

        addNodeButton.setSelected(true);
        addEdgeButton.setSelected(false);
        
        addEdgeButton.setDisable(true);
        addNodeButton.setDisable(false);
        clearButton.setDisable(true);
    }
    
    public void runOne() {
//		algorithm.runOne();
	}
    
    public void setup(String newAlgorithmName, Graph inputGraph, Stage primaryStage)
    {
    	AlgorithmName = newAlgorithmName;
    	textAlgorithm.setText(newAlgorithmName);
    	
    	this.PrimaryStage = primaryStage;
    	
		for(Vertex vertex : inputGraph.get_vertices()) {
            graph.addVertex(vertex);
            vertex.setOnMousePressed(mouseHandler);
            vertex.setOnMouseReleased(mouseHandler);
            vertex.setOnMouseDragged(mouseHandler);
            vertex.setOnMouseExited(mouseHandler);
            vertex.setOnMouseEntered(mouseHandler);
		}
		
        canvasGroup.getChildren().addAll(graph.drawableObjects());   
    }
    
    public void loadNextScene()
    {
		Parent root;
    	try {
    		root = FXMLLoader.load(getClass().getResource("InputMenu.fxml"));
    		PrimaryStage.setScene(new Scene(root));
		} 
    	catch (Exception e2) {
			// TODO: handle exception
		}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("Initialize drawing graph");

		if(graph.get_edges().size() >= 2) {
			addEdgeButton.setDisable(false);
		}
		
		clearButton.setDisable(true);
		
		//Back Button pressed
		backButton.setOnAction(e-> {loadNextScene();});
	}
}
