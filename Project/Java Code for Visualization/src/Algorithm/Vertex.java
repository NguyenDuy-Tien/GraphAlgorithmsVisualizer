package Algorithm;

import java.awt.Point;
import java.util.Vector;

public class Vertex implements Drawable{
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
