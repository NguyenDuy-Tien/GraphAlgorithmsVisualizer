package Elements;

public class UndirectedEdge extends Edge{
	
	public UndirectedEdge(Vertex v, Vertex u, int weight) {
		super(v, u, weight);
		// TODO Auto-generated constructor stub
	}

	@Override 
	public boolean startsFrom(Vertex v)
	{
		return this.getBegin().equals(v);
	}

	@Override
	public void draw(int colour) {
		// TODO Auto-generated method stub
		
	}

}
