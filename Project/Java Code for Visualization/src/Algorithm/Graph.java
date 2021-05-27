package Algorithm;

import java.util.ArrayList;
import java.util.Vector;

import Elements.DirectedEdge;
import Elements.Edge;
import Elements.UndirectedEdge;
import Elements.Vertex;

public class Graph {
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
	public Vector<Vertex> get_vertices() {
		return list_of_vertices;
	}
	public Vector<Edge> get_edges() {
		return list_of_edges;
	}
	public Iterable<Edge> adjacent_edges_of_vertex(Vertex v)
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
	public Iterable<Vertex> adjacent_vertices_of_vertex(Vertex v)
	{
		Vector<Vertex> adj = new Vector<Vertex>();
		for (Edge e: this.list_of_edges)
		{
			if (e instanceof DirectedEdge)
			{
				if (e.getBegin().equals(v))
				{
					adj.add(e.getEnd(v));
				}
			}
			if (e instanceof UndirectedEdge)
			{
				if (e.getBegin().equals(v)||e.getEnd(e.getBegin()).equals(v))
				{
					adj.add(e.getEnd(v));
				}
			}
		}
		return adj;
	}
	public void resetGraph() {
		this.list_of_vertices.elementAt(0).resetCount();
		list_of_edges.clear();
		list_of_vertices.clear();
	}
}
