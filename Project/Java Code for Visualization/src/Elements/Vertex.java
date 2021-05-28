package Elements;


import java.util.Vector;

import Algorithm.Drawable;
import javafx.scene.control.Label;
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
		if (o == this)
		{
			return true;
		}
		if (!(o instanceof Vertex))
		{
			return false;
		}
		Vertex v = (Vertex) o;
		if (v.getID() == this.getID())
		{
			return true;
		}
		return false;
	}
	@Override
	public void draw(int colour) {
		// TODO Auto-generated method stub
		
	}
}
