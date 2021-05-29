package Elements;


import java.util.Vector;

import Algorithm.Drawable;
import javafx.animation.StrokeTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Vertex extends Circle implements Drawable{
	public static int count = 0;
	private int ID;
	public boolean isSelected = false;
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
