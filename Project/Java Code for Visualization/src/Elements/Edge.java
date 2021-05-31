package Elements;

import java.util.Arrays;

import java.util.Collection;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import Algorithm.Drawable;
import javafx.scene.control.Label;
import javafx.scene.shape.Path;
/*import javafx.animation.StrokeTransition;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Duration;*/

public abstract class Edge extends Path implements Comparable<Edge>, Drawable {
	
	protected Label weightLabel;
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
		this.weightLabel = new Label();
		this.setWeight(weight);
        System.out.println("Adding Edge");
		this.draw(colour);
	}
	
	public void setWeight(int newWeight)
	{
		this.weight = newWeight;
		this.weightLabel.setText(String.valueOf(this.weight));
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
	
	// DRAWABLE INTERFACES
	public void draw()
	{
		this.draw(Color.BLACK);
	}

	public Collection<Node> drawableObjects()
	{
		return Arrays.asList(this, weightLabel);
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
