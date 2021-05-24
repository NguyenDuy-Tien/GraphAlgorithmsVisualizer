package Application;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


import Algorithm.Edge;
import Elements.Arrow;
import Elements.EdgeFX;
import Elements.VertexFX;

import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class GraphController implements Initializable{

	@FXML
	private AnchorPane anchorRoot;
	
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
    @FXML
    private Group canvasGroup;
    @FXML 
    private Pane viewer;
    @FXML
	private ToggleGroup addType;
    @FXML
    private Label weight;
    @FXML
    private Line edgeLine;
    @FXML
    private Arrow arrow;
    
    boolean menuBool = false;
    ContextMenu globalMenu;
//	List<Vertex> circles = new ArrayList<>();
    List<EdgeFX> mstEdges = new ArrayList<>(), realEdges = new ArrayList<>();
	VertexFX selectedVertexFX = null;
    List<Shape> edges = new ArrayList<>();
    boolean addNode = true, addEdge = false, calculate = false,
            calculated = false, playing = false, paused = false, pinned = false;
    private boolean directed = InputMenuController.directed, undirected = InputMenuController.undirected;
    List<Label> distances = new ArrayList<Label>(); //visitTime = new ArrayList<>(), lowTime = new ArrayList<Label>();
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
                if (ev.getEventType() == MouseEvent.MOUSE_RELEASED && ev.getButton() == MouseButton.PRIMARY) {
//                    if (menuBool == true) {
//                        System.out.println("here" + ev.getEventType());
//                        menuBool = false;
//                        return;
//                    }
                    nNode++;
                    VertexFX circle = new VertexFX(ev.getX(), ev.getY(), 1.0, String.valueOf(nNode));
                    canvasGroup.getChildren().add(circle);

                    circle.setOnMousePressed(mouseHandler);
                    circle.setOnMouseReleased(mouseHandler);
                    circle.setOnMouseDragged(mouseHandler);
                    circle.setOnMouseExited(mouseHandler);
                    circle.setOnMouseEntered(mouseHandler);

                    ScaleTransition tr = new ScaleTransition(Duration.millis(100), circle);
                    tr.setByX(10f);
                    tr.setByY(10f);
                    tr.setInterpolator(Interpolator.EASE_OUT);
                    tr.play();

                }
            }
        }
    }
	public boolean isExistsEdgeFX(VertexFX begin, VertexFX end) {
		for(EdgeFX edgeFX : realEdges) {
			if(edgeFX.edge.getBegin() == begin.vertex) {
				return true;
			}
		}
		return false;
	}
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            VertexFX circle = (Elements.VertexFX) mouseEvent.getSource();
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseButton.PRIMARY) {

                if (!circle.isSelected) {
                    if (selectedVertexFX != null) {
                        if (addEdge && !isExistsEdgeFX(selectedVertexFX, circle)) {
                            weight = new Label();
                            System.out.println("Adding Edge");
                            //Adds the edge between two selected nodes
                            if (undirected) {
                                edgeLine = new Line(selectedVertexFX.point.x, selectedVertexFX.point.y, circle.point.x, circle.point.y);
                                canvasGroup.getChildren().add(edgeLine);
                                edgeLine.setId("line");
                            } else if (directed) {
                                arrow = new Arrow(selectedVertexFX.point.x, selectedVertexFX.point.y, circle.point.x, circle.point.y);
                                canvasGroup.getChildren().add(arrow);
                                arrow.setId("arrow");
                            }

                            //Adds weight between two selected nodes
                                weight.setLayoutX(((selectedVertexFX.point.x) + (circle.point.x)) / 2);
                                weight.setLayoutY(((selectedVertexFX.point.y) + (circle.point.y)) / 2);

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
                            EdgeFX temp = null;
                            if (undirected) {
                                temp = new EdgeFX(selectedVertexFX.vertex, circle.vertex, Integer.valueOf(weight.getText()), edgeLine, weight);
                                mstEdges.add(temp);

//                                selectedVertexFX.vertex.adjacents.add(new Edge(selectedVertexFX.vertex, circle.vertex, Double.valueOf(weight.getText()), edgeLine, weight));
//                                circle.vertex.adjacents.add(new Edge(circle.vertex, selectedVertexFX.vertex, Double.valueOf(weight.getText()), edgeLine, weight));
                                edges.add(edgeLine);
//                                realEdges.add(selectedVertexFX.node.adjacents.get(selectedVertexFX.node.adjacents.size() - 1));
//                                realEdges.add(circle.node.adjacents.get(circle.node.adjacents.size() - 1));
                                line_arrow = edgeLine;

                            } else if (directed) {
                                temp = new EdgeFX(selectedVertexFX.vertex, circle.vertex, Double.valueOf(weight.getText()), arrow, weight);
//                                selectedVertexFX.vertex.adjacents.add(temp);
//                                circle.node.revAdjacents.add(new Edge(circle.node, selectedVertexFx.node, Integer.valueOf(weight.getText()), arrow));
                                edges.add(arrow);
                                line_arrow = arrow;
                                realEdges.add(temp);
                            }

//                            RightClickMenu rt = new RightClickMenu(temp);
//                            ContextMenu menu = rt.getMenu();
//                            if (weighted) {
//                                rt.changeId.setText("Change Weight");
//                            } else if (unweighted) {
//                                rt.changeId.setDisable(true);
//                            }
//                            final Shape la = line_arrow;
//                            line_arrow.setOnContextMenuRequested(e -> {
//                                System.out.println("In Edge Menu :" + menuBool);
//
//                                if (menuBool == true) {
//                                    globalMenu.hide();
//                                    menuBool = false;
//                                }
//                                if (addEdge || addNode) {
//                                    globalMenu = menu;
//                                    menu.show(la, e.getScreenX(), e.getScreenY());
//                                    menuBool = true;
//                                }
//                            });
//                            menu.setOnAction(e -> {
//                                menuBool = false;
//                            });
                       }
                        if (addNode || (calculate && !calculated) || addEdge) {
                            selectedVertexFX.isSelected = false;
                            FillTransition ft1 = new FillTransition(Duration.millis(300), selectedVertexFX, Color.RED, Color.BLACK);
                            ft1.play();
                        }
                        selectedVertexFX = null;
                        return;
                    }

                    FillTransition ft = new FillTransition(Duration.millis(300), circle, Color.BLACK, Color.RED);
                    ft.play();
                    circle.isSelected = true;
                    selectedVertexFX = circle;

                    // WHAT TO DO WHEN SELECTED ON ACTIVE ALGORITHM
//                    if (calculate && !calculated) {
//                        if (bfs) {
//                            algo.newBFS(circle.node);
//                        } else if (dfs) {
//                            algo.newDFS(circle.node);
//                        } else if (dijkstra) {
//                            algo.newDijkstra(circle.node);
//                        }
//
//                        calculated = true;
//                    } else if (calculate && calculated && !articulationPoint & !mst && !topSortBool) {
//
//                        for (VertexFx n : circles) {
//                            n.isSelected = false;
//                            FillTransition ft1 = new FillTransition(Duration.millis(300), n);
//                            ft1.setToValue(Color.BLACK);
//                            ft1.play();
//                        }
//                        List<Node> path = algo.getShortestPathTo(circle.node);
//                        for (Node n : path) {
//                            FillTransition ft1 = new FillTransition(Duration.millis(300), n.circle);
//                            ft1.setToValue(Color.BLUE);
//                            ft1.play();
//                        }
//                    }
                } else {
                    circle.isSelected = false;
                    FillTransition ft1 = new FillTransition(Duration.millis(300), circle, Color.RED, Color.BLACK);
                    ft1.play();
                    selectedVertexFX = null;
                }

            }
        }

    };

	

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



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}
