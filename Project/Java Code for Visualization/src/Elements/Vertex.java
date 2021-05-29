package Elements;


import java.util.Vector;

import Algorithm.Drawable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Vertex extends Circle implements Drawable{
	public static int count = 0;
	private int ID;
	public Point position;
	public boolean isSelected = false;
	public Vertex(double x, double y, double radius)
	{
		super(x, y, radius);
		count++;
		position = new Point((int)x, (int)y);
		this.ID = count;
		this.setOpacity(0.2);
	}
	
	public Point getPosition()
	{
		return this.position;
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
	

	@Override
	public void draw(Color colour) {
/*		this.setFill(colour);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	*/
	}
}
