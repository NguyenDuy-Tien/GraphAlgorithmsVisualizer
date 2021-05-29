package Elements;

import javafx.scene.paint.Color;

public class DirectedEdge extends Edge{

	public DirectedEdge(Vertex v, Vertex u, int weight) {
		super(v, u, weight);
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public boolean startsFrom(Vertex v)
	{
		return this.getBegin().equals(v);
	}
	

	@Override
	public boolean equals(Object o)
	{
		return o == this ||			// Self Comparing
				( !(o instanceof DirectedEdge) && 	// Test for Same type 
					// Test for same two endpoints
					((DirectedEdge) o).getBegin() == this.getBegin() &&
					((DirectedEdge) o).getEnd() == this.getEnd());
	}
	

	@Override
	public void draw(Color colour) {
		// TODO Auto-generated method stub
		
	}

}
