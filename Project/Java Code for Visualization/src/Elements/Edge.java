package Elements;



import Algorithm.Drawable;
import javafx.animation.StrokeTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

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
		if (begin.equals(vertex))
		{
			return end;
		}
		return begin;
	}
	public Vertex getEnd() {
		return end;
	}
	
	// Used to distinguish between un and directed
	public abstract boolean startsFrom(Vertex v);
	
	@Override
	public boolean equals(Object o)
	{
		if (o == this)
		{
			return true;
		}
		if (!(o instanceof Edge))
		{
			return false;
		}
		Edge e = (Edge) o;
		return ((e.getBegin().equals(this.getBegin())) 
				&& (e.getEnd(e.getBegin()).equals(this.getEnd(this.getBegin())))
				&& (e.getWeight() == this.getWeight()));
	}
	
	@Override
	public int compareTo(Edge that) {
		// TODO Auto-generated method stub
		if (this.getWeight() < that.getWeight())
		{
			return -1;
		}
		if (this.getWeight() == that.getWeight())
		{
			return 0;
		}
		return 1;
	}
	public void changeColorEdge(Color color) {
		StrokeTransition strokeTransition = new StrokeTransition(Duration.millis(100), this.line);
		strokeTransition.setToValue(color);
        strokeTransition.play();
	}

}
