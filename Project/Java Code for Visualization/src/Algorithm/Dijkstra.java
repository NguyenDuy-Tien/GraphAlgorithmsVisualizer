package Algorithm;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

import Elements.Edge;
import Elements.Graph;

public class Dijkstra {
	Edge[] edgeTo;
	double[] distTo;
	private HashMap<Integer, Double> dist;
	PriorityQueue<Integer> pq;
	boolean[] marked;
	public Dijkstra(Graph G, int v)
	{
		distTo = new double[G.number_of_vertices()];
		edgeTo = new Edge[G.number_of_vertices()];
		marked = new boolean[G.number_of_vertices()];
		marked[v] = true;
		dist = new HashMap<Integer, Double>();
		pq = new PriorityQueue<Integer>((a, b) -> dist.get(a).compareTo(dist.get(b)));
		for (int i = 0; i < G.number_of_vertices(); i++)
		{
			dist.put(i, Double.POSITIVE_INFINITY);
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[v] = 0.0;
		dist.put(v, Double.valueOf(0.0));
		pq.add(v);
		while (!pq.isEmpty())
		{
			try {
				//highlight node
				int nodeID = pq.peek();
				Thread.sleep(200);
			}
			catch(Exception exception)
			{
				
			}
			relax(G, pq.poll());
		}
 	}
	public double distTo(int v)
	{
		if (distTo[v] != Double.POSITIVE_INFINITY)
		{
			return distTo[v];
		}
		return -1;
	}
	public boolean reachable(int v)
	{
		if (distTo[v] != Double.POSITIVE_INFINITY)
		{
			return true;
		}
		return false;
	}
	private void relax(Graph G, int v)
	{
		for (Edge e: G.adjacency_list(v))
		{
			try {
				//draw
				
				Thread.sleep(200);
			}
			catch (Exception exception)
			{
				
			}
			int w = e.other(v);
			if (distTo[w] > distTo[v] + e.weight())
			{
				distTo[w] = distTo[v] + e.weight();
				dist.put(w, Double.valueOf(distTo[v] + e.weight()));
				edgeTo[w] = e;
				marked[w] = true;
				pq.add(w);
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		Graph G = new Graph(n);
		for (int i = 0; i < m; i++)
		{
			int v = scanner.nextInt();
			int u = scanner.nextInt();
			double w = scanner.nextDouble();
			Edge e = new Edge(v, u, w);
			//G.addDirectedEdge(e);
			//G.addUndirectedEdge(e);
		}
		Dijkstra SP = new Dijkstra(G, 0);		
		for (int i = 0; i < G.number_of_vertices(); i++)
		{
			System.out.println(SP.distTo(i));
		}
	}
}
