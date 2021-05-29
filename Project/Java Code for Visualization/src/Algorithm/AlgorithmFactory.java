package Algorithm;

public class AlgorithmFactory {

		public static Algorithm create(String algorithmName, Graph g)
		{
			algorithmName = algorithmName.toLowerCase();
			
			switch (algorithmName)
			{
				case "prim":
					return new Prim(g);
				
				case "kruskal":
					return new Kruskal(g);
					
				default:
					return new Dijkstra(g);
			}
		}
}
