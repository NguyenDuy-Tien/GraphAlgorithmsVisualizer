package Elements;

import Algorithm.Drawable;
import javafx.animation.StrokeTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Duration;

public abstract class Edge extends Path implements Comparable<Edge>, Drawable {
	
	private Label weightLabel;
	private Vertex begin;
	private Vertex end;
	private int weight;
	
	public Edge(Vertex v, Vertex u, int weight)
	{
		this(v, u, weight, Color.BLACK);
	}
	
	public Edge(Vertex v, Vertex u, int weight, Color colour)
	{
		this.begin = v;
		this.end = u;
		this.weight = weight;
		this.weightLabel = new Label(String.valueOf(weight));
        System.out.println("Adding Edge");
		
        weightLabel.setFont(new Font(10.6));
		this.draw(colour);
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

/*	public Shape getLine() {
return line;
}
public void changeColorEdge(Color color) {
		StrokeTransition strokeTransition = new StrokeTransition(Duration.millis(100), this.line);
		strokeTransition.setToValue(color);
        strokeTransition.play();
	}
public void setLine(Shape line)
{
this = line;
}*/
