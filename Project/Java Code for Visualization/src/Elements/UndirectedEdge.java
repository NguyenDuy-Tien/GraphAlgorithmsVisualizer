package Elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.text.Font;
import javafx.scene.shape.*;


public class UndirectedEdge extends Edge{
	// Default for colorblind people (I'm joking!)
	public UndirectedEdge(Vertex v, Vertex u, int weight) {
		super(v, u, weight, Color.BLACK);
	}

	public UndirectedEdge(Vertex v, Vertex u, int weight, Color color) {
		super(v, u, weight, color);
	}
	
	@Override 
	public boolean startsFrom(Vertex v)
	{
		return this.getBegin().equals(v) || this.getEnd().equals(v);
	}
	
	
	
	// Compare two Undirected Edges
	@Override
	public boolean equals(Object o)
	{
		return o == this ||			// Self Comparing
				( (o instanceof UndirectedEdge) && 	// Test for Same type 
					
					((DirectedEdge) o).getWeight() == this.getWeight() &&	// Test for weight
					
					// Test for same two endpoints
					((DirectedEdge) o).getBegin() == this.getBegin()? 
							((DirectedEdge) o).getEnd() == this.getEnd()	// Aligned case
							:
							(((DirectedEdge) o).getBegin() == this.getEnd() && // Head-over-heel case
							((DirectedEdge) o).getEnd() == this.getBegin()) 
					);
	}

	
	// Draw a line from Begin vertex to End vertex
	@Override
	public void draw(Color colour) {
        // Setup the weight-displaying label to be at the middle of the edge
		weightLabel.setFont(new Font(10.6));
        weightLabel.setLayoutX((this.getBegin().getCenterX() + this.getEnd().getCenterX()) / 2);
        weightLabel.setLayoutY((this.getBegin().getCenterY() + this.getEnd().getCenterY()) / 2);
		
        // Draw the Edge
        this.getElements().add(new MoveTo(this.getBegin().getCenterX(),
											this.getBegin().getCenterY()));
		
		this.getElements().add(new LineTo(this.getEnd().getCenterX(),
											this.getEnd().getCenterY()));
	}

}
