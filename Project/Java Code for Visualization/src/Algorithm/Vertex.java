package Algorithm;

import java.awt.Point;
import java.util.Vector;

public class Vertex {
	private int ID;
	private Point position;

	public Point getPosition()
	{
		return this.position;
	}
	public int getID()
	{
		return this.ID;
	}
	public void moveTo(Point newPosition)
	{
		this.position = newPosition;
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
}
