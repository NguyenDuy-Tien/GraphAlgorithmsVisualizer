package Elements;

import Algorithm.Vertex;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class VertexFX extends Circle{
	
	public Vertex vertex;
	public Point point;
	public Label idLabel;
	public boolean isSelected = false;
	
	public VertexFX(double x, double y, double rad, String name) {
		super(x, y, rad);
		point = new Point((int)x, (int)y);
		
		idLabel = new Label(name);
		idLabel.setLayoutX(x - 18);
        idLabel.setLayoutY(y - 18);
	}

}

