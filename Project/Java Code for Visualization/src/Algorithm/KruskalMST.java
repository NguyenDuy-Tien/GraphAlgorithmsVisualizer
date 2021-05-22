package Algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import Elements.Edge;
import Elements.Graph;

public class KruskalMST {
	private PriorityQueue<Edge> pq;
	private ArrayList<Edge> mst;
	private int[] root;
	public KruskalMST(Graph G)
	{
		root = new int[G.number_of_vertices()];
		mst = new ArrayList<Edge>();
		pq = new PriorityQueue<Edge>();
		UnionFind UF = new UnionFind(G.number_of_vertices());
		for (Edge e: G.list_of_edges())
		{
			pq.add(e);
		}
		for (int i = 0; i < G.number_of_vertices(); i++)
		{
			root[i] = i;
		}
		while ((!pq.isEmpty()) && (mst.size() < (G.number_of_vertices() - 1)))
		{
			Edge e = pq.poll();
			try {
				//highlight e
				Thread.sleep(200);
			}
			catch (Exception exception)
			{
				
			}
			int u = e.begin();
			int v = e.other(u);
			if (UF.connected(u, v))
			{
				continue;
			}
			UF.union(u, v);
			try
			{
				//change color e
				mst.add(e);
				Thread.sleep(200);
			}
			catch (Exception exception)
			{
				
			}
		}
	}
	public Iterable<Edge> list_of_edges()
	{
		return this.mst;
	}
	public double totalWeight()
	{
		double sum = 0;
		for (Edge e: this.list_of_edges())
		{
			sum = sum + e.weight();
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

		KruskalMST Kruskal = new KruskalMST(G);
		System.out.println(Kruskal.totalWeight());
	}

}
