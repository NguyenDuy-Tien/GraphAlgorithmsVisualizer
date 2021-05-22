package Elements;

import java.util.ArrayList;
import java.util.Vector;

public class Graph {
	private int number_of_vertices;
	private int number_of_edges;
	private Vector<Vector<Edge>> adjacency_list;
	private Vector<Edge> list_of_edges;
	public Graph(int number_of_vertices)
	{
		this.number_of_vertices = number_of_vertices;
		adjacency_list = new Vector<Vector<Edge>>();
		list_of_edges = new Vector<Edge>();
		for (int i = 0; i < this.number_of_vertices; i++)
		{
			adjacency_list.add(new Vector<Edge>());
		}
	}
	
	public int number_of_vertices()
	{
		return this.number_of_vertices;
	}
	
	public int number_of_edges()
	{
		return this.number_of_edges;
	}
	
	public void addDirectedEdge(Edge e)
	{
		try {
			//draw an edge with arrow here
			Thread.sleep(200);
		}
		catch (Exception exception)
		{
			
		}
		this.list_of_edges.add(e);
		int v = e.begin();
		this.adjacency_list.get(v).add(e);
	}
	
	public void addUndirectedEdge(Edge e)
	{
		try {
			//draw an edge without arrow here
			Thread.sleep(200);
		}
		catch (Exception exception)
		{
			
		}
		this.list_of_edges.add(e);
		int v = e.begin();
		int w = e.other(v);
		this.adjacency_list.get(v).add(e);
		this.adjacency_list.get(w).add(e);
	}
	
	public Iterable<Edge> adjacency_list(int vertex)
	{
		return adjacency_list.get(vertex);
	}
	
	public Iterable<Edge> list_of_edges()
	{
		return this.list_of_edges;
	}
}
