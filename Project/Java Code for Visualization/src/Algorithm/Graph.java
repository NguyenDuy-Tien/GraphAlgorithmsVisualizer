package Algorithm;

import java.util.ArrayList;
import java.util.Vector;

public class Graph {
	private int number_of_vertices;
	private int number_of_edges;
	private Vector<Vertex> list_of_vertices;
	private Vector<Edge> list_of_edges;
	private Algorithm algorithm;
	public Graph()
	{
		list_of_edges = new Vector<Edge>();
		list_of_vertices = new Vector<Vertex>();
	}
	public void addEdge(Edge e)
	{
		this.list_of_edges.add(e);
	}
	public void removeEdge(Edge e)
	{
		this.list_of_edges.remove(e);
	}
	public void changeAlgorithm(Algorithm newAlgorithm)
	{
		this.algorithm = newAlgorithm;
	}
	public void addVertex(Vertex v)
	{
		this.list_of_vertices.add(v);
	}
	public void runOne()
	{
		this.algorithm.runOne();
	}
	public void runAll()
	{
		this.algorithm.runAll();
	}
	public void removeVertex(Vertex v)
	{
		for (Edge e: this.list_of_edges)
		{
			if (e.getBegin().equals(v) || e.getEnd(e.getBegin()).equals(v))
			{
				this.list_of_edges.remove(e);
			}
		}
		this.list_of_vertices.remove(v);
	}
	public Iterable<Edge> adjacent_list_of_vertex(Vertex v)
	{
		Vector<Edge> adj = new Vector<Edge>();
		for (Edge e: this.list_of_edges)
		{
			if (e instanceof DirectedEdge)
			{
				if (e.getBegin().equals(v))
				{
					adj.add(e);
				}
			}
			if (e instanceof UndirectedEdge)
			{
				if (e.getBegin().equals(v)||e.getEnd(e.getBegin()).equals(v))
				{
					adj.add(e);
				}
			}
		}
		return adj;
	}
}
