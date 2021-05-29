package Elements;


import Algorithm.Drawable;
import javafx.animation.StrokeTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Vertex extends Circle implements Drawable{
	public static int count = 0;
	private int ID;
	public boolean isSelected = false;
	private Label vertexID;
	public Vertex(double x, double y, double radius)
	{
		super(x, y, radius);
		count++;
		this.ID = count;
		this.setOpacity(0.2);
	}
	
	public int getID()
	{
		return this.ID;
	}
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Label getVertexID() {
		return vertexID;
	}

	public void setVertexID(Label vertexID) {
		this.vertexID = vertexID;
		vertexID.setFont(Font.font("Helvetica", FontWeight.BOLD, 11.6));
        vertexID.setTextFill(Color.ORANGERED);
        vertexID.setText(String.valueOf(this.getID()));
	}
	public void resetCount() {
		count = 0;
	}
	
	@Override
	public boolean equals(Object o)
	{
		return o == this ||
				(o instanceof Vertex &&
						((Vertex) o).getID() == this.getID());
	}

	public void changeColorVertex(Color color) {
		this.setFill(color);
	}

	@Override
	public void draw(Color colour) {
		this.setFill(colour);
	}
}
