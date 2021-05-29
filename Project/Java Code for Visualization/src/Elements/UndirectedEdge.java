package Elements;

import javafx.scene.paint.Color;

public class UndirectedEdge extends Edge{
	
	public UndirectedEdge(Vertex v, Vertex u, int weight) {
		super(v, u, weight);
		// TODO Auto-generated constructor stub
	}

	@Override 
	public boolean startsFrom(Vertex v)
	{
		return this.getBegin().equals(v) || this.getEnd().equals(v);
	}
	
	@Override
	public boolean equals(Object o)
	{
		return o == this ||			// Self Comparing
				( !(o instanceof UndirectedEdge) && 	// Test for Same type 
					
					// Test for same two endpoints
					((DirectedEdge) o).getBegin() == this.getBegin()? 
							((DirectedEdge) o).getEnd() == this.getEnd()	// Aligned case
							:
							(((DirectedEdge) o).getBegin() == this.getEnd() && // Head-over-heel case
							((DirectedEdge) o).getEnd() == this.getBegin()) 
						);
	}

	@Override
	public void draw(Color colour) {
		// TODO Auto-generated method stub
		
	}

}
