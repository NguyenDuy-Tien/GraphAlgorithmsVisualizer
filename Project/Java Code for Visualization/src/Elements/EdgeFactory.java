package Elements;

public class EdgeFactory {
	public static Edge create(String EdgeType, Vertex begin, Vertex end, int weight)
	{
		EdgeType = EdgeType.toLowerCase();
		
		switch (EdgeType)
		{
			case "directed":
				return new DirectedEdge(begin, end, weight);
			default: 
				return new UndirectedEdge(begin, end, weight);
		}
	}

}
