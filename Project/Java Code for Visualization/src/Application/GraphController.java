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

public class GraphController implements Initializable{

	public static String AlgorithmName;
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
    Graph graph = new Graph();
	Vertex selectedVertex = null;
	
	private String edgeDirection = EdgeFactory.DIRECTED;
	
    boolean addNode = true, addEdge = false, calculate = false,
            calculated = false, playing = false, paused = false, pinned = false;
//    private boolean directed = InputMenuController.directed, undirected = InputMenuController.undirected;
    List<Label> distances = new ArrayList<Label>(); 
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
                    
                    graph.addVertex(vertex);
                    
                    canvasGroup.getChildren().add(vertex.getVertexID());
                    
                    
                    canvasGroup.getChildren().add(vertex);
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
                    
                } else {
                    circle.isSelected = false;
                    circle.draw(Color.BLACK);
                    selectedVertex = null;
                }

            }
        }

    };

	
    @FXML
	public void AddNodeHandle(ActionEvent event) {
        addNode = true;
        addEdge = false;
        addNodeButton.setSelected(true);
        addEdgeButton.setSelected(false);
       
    }
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
        for (Vertex v : graph.get_vertices()) {
            v.isSelected = false;
            v.draw(Color.BLACK);
        }
        for (Edge edgeLine : graph.get_edges()) {
                edgeLine.setFill(Color.BLACK);
        }
        canvasGroup.getChildren().remove(sourceText);
        for (Label x : distances) {
            x.setText("Distance : INFINITY");
            canvasGroup.getChildren().remove(x);
        }

        distances = new ArrayList<>();
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
        
        distances = new ArrayList<Label>();
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textAlgorithm.setText(AlgorithmName);
		
		System.out.println("Initialize drawing graph");
		
		ResetHandle(null);
		for(Vertex vertex : InputMenuController.graph.get_vertices()) {
            ///////
            canvasGroup.getChildren().add(vertex.getVertexID());
            canvasGroup.getChildren().add(vertex);
            
            graph.addVertex(vertex);
            vertex.setOnMousePressed(mouseHandler);
            vertex.setOnMouseReleased(mouseHandler);
            vertex.setOnMouseDragged(mouseHandler);
            vertex.setOnMouseExited(mouseHandler);
            vertex.setOnMouseEntered(mouseHandler);
		}
		for(Edge edge : InputMenuController.graph.get_edges()) {	
			double startX = edge.getBegin().getPosition().getX();
        	double startY = edge.getBegin().getPosition().getY();
        	double endX = edge.getEnd().getPosition().getX();
        	double endY = edge.getEnd().getPosition().getY();
        	
            //Adds the edge between two selected nodes
        	if (undirected) {
                Shape edgeLine = new Line(startX, startY, endX, endY);
                edge.setLine(edgeLine);
                canvasGroup.getChildren().add(edgeLine);
                edgeLine.setId("line");
                //Position weight label between two Undirected nodes
                edge.getWeightLabel().setLayoutX((startX + endX) / 2);
                edge.getWeightLabel().setLayoutY((startY + endY) / 2);
                graph.addEdge(edge);
                
            } else if (directed) {
            	double diffX = (startX - endX)/50;
            	double diffY = (startY - endY)/50;
            	double ratioXY = Math.abs(diffX / diffY);
                arrow = new Arrow(startX -diffX/ratioXY, startY + diffY*ratioXY, 
                		endX -diffX/ratioXY, endY + diffY*ratioXY);
                canvasGroup.getChildren().add(arrow);
                arrow.setId("arrow");
                
                edge.setLine(arrow);
                
                edge.getWeightLabel().setLayoutX((startX + endX - 2*diffX/ratioXY) / 2 );
                edge.getWeightLabel().setLayoutY((startY + endY + 2*diffY*ratioXY) / 2 );
                graph.addEdge(edge);
            }
            
                edge.getWeightLabel().setText(String.valueOf(edge.getWeight()));
                canvasGroup.getChildren().add(edge.getWeightLabel());
		}
		if(InputMenuController.graph.get_edges().size() >= 2) {
			addEdgeButton.setDisable(false);
		}
		clearButton.setDisable(true);
		//Back Button pressed
		backButton.setOnAction(e-> {
			try {
				ResetHandle(null);
				Parent root = FXMLLoader.load(getClass().getResource("InputMenu.fxml"));
				Scene scene = new Scene(root);
				InputMenu.primaryStage.setScene(scene);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
	}
}
