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
	private static int counterID = 0;

	// Instead of THIS ID, we use the get/setId provided by JavaFX
	//private int ID;
	
	// Reset the private auto-incremented counter of Vertex
	public static void resetCounter() {
		counterID = 0;
	}
	
	public boolean isSelected = false;
	private Label vertexID;
	
	public Vertex(double x, double y, double radius)
	{
		this(x, y, radius, Color.BLACK);
	}
	
	public Vertex(double x, double y, double radius, Color color)
	{
		super(x, y, radius);
		counterID++;
		this.setId(String.valueOf(counterID));
		this.setOpacity(0.2);
		this.draw(color);
	}
	
	public String getID()
	{
		return this.getId();
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
	
	
	@Override
	public boolean equals(Object o)
	{
		return o == this ||
				(o instanceof Vertex &&
						((Vertex) o).getID().equals(this.getID()));
	}

	public void changeColorVertex(Color color) {
		this.setFill(color);
	}

	@Override
	public void draw(Color colour) {
		this.setFill(colour);
		this.vertexID = new Label();
		this.vertexID.setLayoutX(this.getCenterX() - 6);
		this.vertexID.setLayoutY(this.getCenterY() - 6);
	}
}
