package Algorithm;

import Elements.Graph;

public class AlgorithmFactory {
		
	public static final String DIJKSTRA = "dijkstra",
								KRUSKAL = "kruskal mst",
								PRIM	= "prim mst";
	
		public static Algorithm create(String algorithmName, Graph g)
		{
			algorithmName = algorithmName.toLowerCase();
			
			switch (algorithmName)
			{
				case PRIM:
					return new Prim(g);
				
				case KRUSKAL:
					return new Kruskal(g);
					
				default:
					return new Dijkstra(g);
			}
		}
		
		
}
