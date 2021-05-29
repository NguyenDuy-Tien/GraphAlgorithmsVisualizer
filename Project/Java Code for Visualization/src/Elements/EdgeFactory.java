package Elements;

public class EdgeFactory {
	public static final String UNDIRECTED = "undirected";
	public static final String DIRECTED = "directed";
	
	public static Edge create(String EdgeType, Vertex begin, Vertex end, int weight)
	{
		EdgeType = EdgeType.toLowerCase();
		
		switch (EdgeType)
		{
			case DIRECTED:
				return new DirectedEdge(begin, end, weight);
			default: 
				return new UndirectedEdge(begin, end, weight);
		}
	}

}
