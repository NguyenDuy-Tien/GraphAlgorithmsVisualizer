package Application;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import Algorithm.Edge;
import Algorithm.Vertex;
//import Elements.VertexFX;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

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
    @FXML
    private Group canvasGroup;
//	List<Vertex> circles = new ArrayList<>();
//    List<Edge> mstEdges = new ArrayList<>(), realEdges = new ArrayList<>();
	
    List<Algorithm.Edge> mstEdges = new ArrayList<Edge>();
    List<Shape> edges = new ArrayList<>();
    boolean addNode = true, addEdge = false, calculate = false,
            calculated = false, playing = false, paused = false, pinned = false;
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
                    Vertex circle = new Vertex(ev.getX(), ev.getY(), 1.2, String.valueOf(nNode));
//                    canvasGroup.getChildren().add(circle);

//                    circle.setOnMousePressed(mouseHandler);
//                    circle.setOnMouseReleased(mouseHandler);
//                    circle.setOnMouseDragged(mouseHandler);
//                    circle.setOnMouseExited(mouseHandler);
//                    circle.setOnMouseEntered(mouseHandler);

//                    ScaleTransition tr = new ScaleTransition(Duration.millis(100), circle);
//                    tr.setByX(10f);
//                    tr.setByY(10f);
//                    tr.setInterpolator(Interpolator.EASE_OUT);
//                    tr.play();

                }
            }
        }
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
