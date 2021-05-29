package Application;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Algorithm.Graph;
import Elements.Arrow;
import Elements.DirectedEdge;
import Elements.Edge;
import Elements.UndirectedEdge;
import Elements.Vertex;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.StrokeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

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
    private Label weight;
    @FXML
    private Label vertexId;
    @FXML
    private Line edgeLine;
    @FXML
    private Arrow arrow;
    
    boolean menuBool = false;
    ContextMenu globalMenu;
    List<Vertex> circles = new ArrayList<>();
//    List<Edge> mstEdges = new ArrayList<>(), realEdges = new ArrayList<>();
	Vertex selectedVertex = null;
    List<Shape> edges = new ArrayList<>();
    boolean addNode = true, addEdge = false, calculate = false,
            calculated = false, playing = false, paused = false, pinned = false;
    private boolean directed = InputMenuController.directed, undirected = InputMenuController.undirected;
    List<Label> distances = new ArrayList<Label>(); 
    int nNode = 0;
    Graph graph = new Graph();
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
                if (ev.getEventType() == MouseEvent.MOUSE_RELEASED && ev.getButton() == MouseButton.PRIMARY) {
                    nNode++;
                    Vertex vertex = new Vertex(ev.getX(), ev.getY(), 12.0);
                    graph.addVertex(vertex);
                    vertexId = new Label();
                    vertexId.setFont(Font.font("Helvetica", FontWeight.BOLD, 11.6));
                    vertexId.setTextFill(Color.ORANGERED);
                    canvasGroup.getChildren().add(vertexId);
                    vertexId.setLayoutX(ev.getX() - 6);
                    vertexId.setLayoutY(ev.getY() - 6);
                    vertexId.setText(String.valueOf(vertex.getID()));
                    canvasGroup.getChildren().add(vertex);
                    circles.add(vertex);
                    
                    vertex.setOnMousePressed(mouseHandler);
                    vertex.setOnMouseReleased(mouseHandler);
                    vertex.setOnMouseDragged(mouseHandler);
                    vertex.setOnMouseExited(mouseHandler);
                    vertex.setOnMouseEntered(mouseHandler);

//                    ScaleTransition tr = new ScaleTransition(Duration.millis(100), circle);
//                    tr.setByX(10f);
//                    tr.setByY(10f);
//                    tr.setInterpolator(Interpolator.EASE_OUT);
//                    tr.play();

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
                        if (addEdge && !graph.isExistsEdge(selectedVertex, circle)) {
                            weight = new Label();
                            weight.setFont(new Font(10.6));
                            System.out.println("Adding Edge");
                            
                            // Rename cho de hieu va de su dung
                            double startX = selectedVertex.getPosition().getX();
                        	double startY = selectedVertex.getPosition().getY();
                        	double endX = circle.getPosition().getX();
                        	double endY = circle.getPosition().getY();
                        	
                            //Adds the edge between two selected nodes
                        	if (undirected) {
                                edgeLine = new Line(startX, startY, endX, endY);
                                canvasGroup.getChildren().add(edgeLine);
                                edgeLine.setId("line");
                                //Position weight label between two Undirected nodes
                                weight.setLayoutX((startX + endX) / 2);
                                weight.setLayoutY((startY + endY) / 2);
                                
                            } else if (directed) {
                            	double diffX = (startX - endX)/50;
                            	double diffY = (startY - endY)/50;
                            	double ratioXY = Math.abs(diffX / diffY);
                                arrow = new Arrow(startX -diffX/ratioXY, startY + diffY*ratioXY, 
                                		endX -diffX/ratioXY, endY + diffY*ratioXY);
                                canvasGroup.getChildren().add(arrow);
                                arrow.setId("arrow");
                                weight.setLayoutX((startX + endX - 2*diffX/ratioXY) / 2 );
                                weight.setLayoutY((startY + endY + 2*diffY*ratioXY) / 2 );
                                
                            }


                                TextInputDialog dialog = new TextInputDialog("0");
                                dialog.setTitle(null);
                                dialog.setHeaderText("Enter Weight of the Edge :");
                                dialog.setContentText(null);

                                Optional<String> result = dialog.showAndWait();
                                if (result.isPresent()) {
                                    weight.setText(result.get());
                                } else {
                                    weight.setText("0");
                                }
                                canvasGroup.getChildren().add(weight);
                                
                            Shape line_arrow = null;
                            Edge temp = null;
                            if (undirected) {
                                temp = new UndirectedEdge(selectedVertex, circle, Integer.valueOf(weight.getText()));
                                temp.setLine(line_arrow);
                                graph.addEdge(temp);

                                edges.add(edgeLine);
//                                realEdges.add(selectedVertexFX.node.adjacents.get(selectedVertexFX.node.adjacents.size() - 1));
//                                realEdges.add(circle.node.adjacents.get(circle.node.adjacents.size() - 1));
                                line_arrow = edgeLine;

                            } else if (directed) {
                                temp = new DirectedEdge(selectedVertex, circle, Integer.valueOf(weight.getText()));
//                                selectedVertexFX.vertex.adjacents.add(temp);
//                                circle.node.revAdjacents.add(new Edge(circle.node, selectedVertexFx.node, Integer.valueOf(weight.getText()), arrow));
                                
                                edges.add(arrow);
                                line_arrow = arrow;
                                temp.setLine(line_arrow);
                                graph.addEdge(temp);
                            }
                       }
                       if (addNode || (calculate && !calculated) || addEdge) {
                            selectedVertex.isSelected = false;
                            selectedVertex.changeColorVertex(Color.BLACK);
                        }
                        selectedVertex = null;
                        return;
                    }

                    circle.changeColorVertex(Color.RED);
                    circle.isSelected = true;
                    selectedVertex = circle;
                    
                } else {
                    circle.isSelected = false;
                    circle.changeColorVertex(Color.BLACK);
                    selectedVertex = null;
                }

            }
        }

    };

	
    @FXML
	public void AddNodeHandle(ActionEvent event) {
        addNode = true;
        addEdge = false;
        calculate = false;
        addNodeButton.setSelected(true);
        addEdgeButton.setSelected(false);
       
    }
    @FXML
    public void AddEdgeHandle(ActionEvent event) {
        addNode = false;
        addEdge = true;
        calculate = false;
        addNodeButton.setSelected(false);
        addEdgeButton.setSelected(true);
    }

    @FXML
    public void ClearHandle(ActionEvent event) {
        menuBool = false;
        selectedVertex = null;
        calculated = false;
        System.out.println("IN CLEAR:" + circles.size());
        for (Vertex v : circles) {
            v.isSelected = false;
            v.changeColorVertex(Color.BLACK);
        }
        for (Shape edgeLine : edges) {
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
        playing = false;
        paused = false;
    }

    // Reset Handle for handling Reset Button
    @FXML
    public void ResetHandle(ActionEvent event) {
        ClearHandle(null);
        if(!circles.isEmpty()) {
			circles.get(0).resetCount();
        }
        edges.clear();
        circles.clear();
        graph.resetGraph();
        nNode = 0;
        canvasGroup.getChildren().clear();
        canvasGroup.getChildren().addAll(viewer);
        selectedVertex = null;
        circles = new ArrayList<Vertex>();
        distances = new ArrayList<Label>();
//        visitTime = new ArrayList<Label>();
//        lowTime = new ArrayList<Label>();
        addNode = true;
        addEdge = false;
        calculate = false;
        calculated = false;
        addNodeButton.setSelected(true);
        addEdgeButton.setSelected(false);
        addEdgeButton.setDisable(true);
        addNodeButton.setDisable(false);
        clearButton.setDisable(true);
        playing = false;
        paused = false;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textAlgorithm.setText(AlgorithmName);
		
		System.out.println("Initialize drawing graph");
		
		ResetHandle(null);
		for(Vertex vertex : InputMenuController.graph.get_vertices()) {
			vertexId = new Label();
			vertexId.setFont(Font.font("Helvetica", FontWeight.BOLD, 11.6));
            vertexId.setTextFill(Color.ORANGERED);
            canvasGroup.getChildren().add(vertexId);
            vertexId.setLayoutX(vertex.getPosition().getX() - 6);
            vertexId.setLayoutY(vertex.getPosition().getY() - 6);
            vertexId.setText(String.valueOf(vertex.getID()));
            canvasGroup.getChildren().add(vertex);
            circles.add(vertex);
            vertex.setOnMousePressed(mouseHandler);
            vertex.setOnMouseReleased(mouseHandler);
            vertex.setOnMouseDragged(mouseHandler);
            vertex.setOnMouseExited(mouseHandler);
            vertex.setOnMouseEntered(mouseHandler);
		}
		for(Edge edge : InputMenuController.graph.get_edges()) {
			weight = new Label();
			weight.setFont(new Font(10.6));
			double startX = edge.getBegin().getPosition().getX();
        	double startY = edge.getBegin().getPosition().getY();
        	double endX = edge.getEnd().getPosition().getX();
        	double endY = edge.getEnd().getPosition().getY();
        	
            //Adds the edge between two selected nodes
        	if (undirected) {
                edgeLine = new Line(startX, startY, endX, endY);
                canvasGroup.getChildren().add(edgeLine);
                edgeLine.setId("line");
                //Position weight label between two Undirected nodes
                weight.setLayoutX((startX + endX) / 2);
                weight.setLayoutY((startY + endY) / 2);
                
            } else if (directed) {
            	double diffX = (startX - endX)/50;
            	double diffY = (startY - endY)/50;
            	double ratioXY = Math.abs(diffX / diffY);
                arrow = new Arrow(startX -diffX/ratioXY, startY + diffY*ratioXY, 
                		endX -diffX/ratioXY, endY + diffY*ratioXY);
                canvasGroup.getChildren().add(arrow);
                arrow.setId("arrow");
                weight.setLayoutX((startX + endX - 2*diffX/ratioXY) / 2 );
                weight.setLayoutY((startY + endY + 2*diffY*ratioXY) / 2 );   
            }
                weight.setText(String.valueOf(edge.getWeight()));
                canvasGroup.getChildren().add(weight);
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
