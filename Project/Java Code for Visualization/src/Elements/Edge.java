package Elements;

import Algorithm.Drawable;
import javafx.scene.control.Label;
import javafx.scene.shape.Shape;

public abstract class Edge implements Comparable<Edge>, Drawable {

	public Shape line;
	public Label weightLabel;
	private Vertex begin;
	private Vertex end;
	private int weight;
	public Edge(Vertex v, Vertex u, int weight)
	{
		this.begin = v;
		this.end = u;
		this.weight = weight;
		this.weightLabel = new Label(String.valueOf(weight));
		
	}
	public Shape getLine() {
		return line;
	}
	public void setLine(Shape line)
	{
		this.line = line;
	}
	public double getWeight()
	{
		return this.weight;
	}
	
	public Vertex getBegin()
	{
		return begin;
	}
	
	public Vertex getEnd(Vertex vertex)
	{
		return begin.equals(vertex) ? end : begin;
	}
	
	public Vertex getEnd() {
		return end;
	}
	
	// Used to distinguish between un and directed
	public abstract boolean startsFrom(Vertex v);
	
	@Override
	public int compareTo(Edge that) {
		// TODO Auto-generated method stub
		if (this.getWeight() < that.getWeight())
			return -1;
		
		return this.getWeight() == that.getWeight()? 0 : 1;
	}
}
