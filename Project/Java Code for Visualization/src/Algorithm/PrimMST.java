package Algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import Elements.Edge;
import Elements.Graph;

public class PrimMST {
	private Edge[] edgeTo;
	private double[] distTo;
	private boolean[] marked;
	private PriorityQueue<Integer> pq;
	
	public PrimMST(Graph G)
	{
		edgeTo = new Edge[G.number_of_vertices()];
		distTo = new double[G.number_of_vertices()];
		marked = new boolean[G.number_of_vertices()];
		for (int i = 0; i < G.number_of_vertices(); i++)
		{
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		pq = new PriorityQueue<Integer>((a,b) -> Double.valueOf(distTo[a]).compareTo(Double.valueOf(distTo[b])));
		distTo[0] = 0.0;
		pq.add(0);
		while (!pq.isEmpty())
		{
			visit(G, pq.poll());
		}
	}
	
	public void visit(Graph G, int v)
	{
		marked[v] = true;
		for (Edge e: G.adjacency_list(v))
		{
			int u = e.other(v);
			if (marked[u]) continue;
			if (e.weight() < distTo[u])
			{
				edgeTo[u] = e;
				distTo[u] = e.weight();
				if (!pq.contains(u))
				{
					pq.add(u);
				}
			}
		}
	}
	
	public Iterable<Edge> edges()
	{
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 1; i < edgeTo.length; i++)
		{
			edges.add(edgeTo[i]);
		}
		return edges;
	}
	
	public double totalWeight()
	{
		double sum = 0.0;
		for (int i = 1; i < edgeTo.length; i++)
		{
			sum = sum + edgeTo[i].weight();
		}
		return sum;
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
			G.addUndirectedEdge(e);
		}
		PrimMST Prim = new PrimMST(G);
		System.out.println(Prim.totalWeight());
	}
}
