package Application;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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

	// B U T T O N S !
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
    // // // // //
    ContextMenu globalMenu;		// ???? What is this?
    
    
	public GraphController() {
	}
	

	public void handle(MouseEvent event) 
	{ 
		System.out.println("Clicked"); 
		System.out.println(event.isPrimaryButtonDown());
		System.out.println(!event.getSource().equals(canvasGroup)); 
		//if you LEFT click into a blank space -> create new node
        if (event.getButton() == MouseButton.PRIMARY  &&
        		!event.getSource().equals(canvasGroup)) 
        {
                Vertex vertex = new Vertex(event.getX(), event.getY(), 12.0);
                this.addToGraph(vertex);
        }
        // Right click to un-choose every nodes
        else if (event.isSecondaryButtonDown()) 
    	{
            unhighlight(selectedVertices);
            selectedVertices.clear();
        }
    };
    
	//ADD NODE HANDLER
	// Bind this directly to vertex.setMouseReleased()
	public EventHandler<MouseEvent> addNodeHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
            //if you LEFT click into a blank space -> create new node
            if (event.isPrimaryButtonDown()  &&
            		!event.getSource().equals(canvasGroup)) 
            {
                    Vertex vertex = new Vertex(event.getX(), event.getY(), 12.0);
                    addToGraph(vertex);
            }
            // Right click to un-choose every nodes
            else if (event.isSecondaryButtonDown()) 
            	{
                    unhighlight(selectedVertices);
                    selectedVertices.clear();
                }
            }};
		
	
	
	//ADD EDGE HANDLER
	// Bind this to vertex.setOnMousePressed()
	public EventHandler<MouseEvent> addEdgeHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			Vertex circle = (Vertex) mouseEvent.getSource();
			
			selectedVertices.remove(circle);	// Avoid clicking one vertex twice.
			selectedVertices.add(circle);
			
			if (selectedVertices.size () == 1)	// highlight the clicked vertex
				highlight(selectedVertices);
				
			// Add an edge if there's not one there
			else if (!graph.isExistsEdge(selectedVertices.get(0), selectedVertices.get(1))) 
			{
					//dialog to get weight
					TextInputDialog dialog = new TextInputDialog("0");
                    dialog.setTitle(null);
                    dialog.setHeaderText("Enter Weight of the Edge :");
                    dialog.setContentText(null);

                    Optional<String> result = dialog.showAndWait();
                    int w = Integer.MIN_VALUE;
                    //parse weight
                    if (result.isPresent()) {
                    	w = Integer.parseInt(result.get());
                    } 
                    else {
                    }
                    //draw
                    if (w != Integer.MIN_VALUE)
                    {
                    	Edge newEdge = EdgeFactory.create(edgeDirection, selectedVertices.get(0), selectedVertices.get(1), 0);
                    	addToGraph(newEdge);
                    }
                    
                    unhighlight(selectedVertices);
                    selectedVertices.clear();
			}
			else
			{
				unhighlight(selectedVertices);
				selectedVertices.clear();
			}
		}
		
	};
	

	protected void unhighlight(List<Vertex> v)	{	for (Vertex vtx: v) unhighlight(vtx);	}
	protected void unhighlight(Vertex v)			{	v.draw(Color.BLACK);	}
	
	protected void highlight(List<Vertex> v)	{	for (Vertex vtx: v) highlight(vtx);	}
	protected void highlight(Vertex v)		{	v.draw(Color.RED);	}
    

    // Binding function for AddNode button
	/*
    @FXML
	public void AddNodeHandle(ActionEvent event) {
        addNodeButton.setSelected(true);
        addEdgeButton.setSelected(false);
       
    }

    // Binding function for AddEdge button
    @FXML
    public void AddEdgeHandle(ActionEvent event) {
        addNodeButton.setSelected(false);
        addEdgeButton.setSelected(true);
    }*/
    
    @FXML
    private void ClearHandle(ActionEvent event) {
        System.out.println("IN CLEAR:" + graph.get_vertices().size());
        canvasGroup.getChildren().removeAll(graph.drawableObjects());
    }

    // Reset Handle for handling Reset Button
    // Clear the graph - Lam
    @FXML
    private void ResetHandle(ActionEvent event) {
        ClearHandle(null);

        graph.resetGraph();
        
        /*
        addNodeButton.setSelected(true);
        addEdgeButton.setSelected(false);
        
        addEdgeButton.setDisable(true);
        addNodeButton.setDisable(false);
        */
        clearButton.setDisable(true);
    }
    
    protected void addToGraph(Vertex v)
    {
        canvasGroup.getChildren().addAll(v.drawableObjects());
        graph.addVertex(v);
        v.setOnMousePressed(addEdgeHandler);
        v.setOnMouseReleased(addNodeHandler);
    } 
    
    protected void addToGraph(Edge e)
    {
        canvasGroup.getChildren().addAll(e.drawableObjects());
        graph.addEdge(e);
    }
    
    
    public void runOne() {
//		algorithm.runOne();
	}
    
    public void setup(String newAlgorithmName, Graph inputGraph, Stage primaryStage)
    {
    	AlgorithmName = newAlgorithmName;
    	textAlgorithm.setText(newAlgorithmName);
    	
    	this.PrimaryStage = primaryStage;
    	
		for(Vertex v : inputGraph.get_vertices()) {
			this.addToGraph(v);
		}
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
		//handle = addNodeHandler;
		System.out.println("Initialize drawing graph");
		edgeDirection = EdgeFactory.UNDIRECTED;
		if(graph.get_edges().size() >= 2) {
			addEdgeButton.setDisable(false);
		}
		
		clearButton.setDisable(true);
		
		//Back Button pressed
		backButton.setOnAction(e-> {loadNextScene();});
	}

	
    // What the mouse has selected so far
    private List<Vertex> selectedVertices = new ArrayList<Vertex>();
    
	private Stage PrimaryStage;
	private static String AlgorithmName;
	private Algorithm algorithm;
    private Graph graph = new Graph();
	private String edgeDirection;
}
