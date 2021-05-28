package Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Elements.DirectedEdge;
import Elements.Edge;
import Elements.UndirectedEdge;
import Elements.Vertex;

public class Graph {
	private List<Vertex> list_of_vertices;
	private List<Edge> list_of_edges;
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
	public List<Vertex> get_vertices() {
		return list_of_vertices;
	}
	public List<Edge> get_edges() {
		return list_of_edges;
	}
	
	public Iterable<Edge> adjacent_edges_of_vertex(Vertex v)
	{
		Vector<Edge> adj = new Vector<Edge>();
		// Find the edges that start from this vertex
		for (Edge e: this.list_of_edges)
			if (e.startsFrom(v))
				// then add it to the list
				adj.add(e);
		
		return adj;
	}
	
	public List<Edge> getEdgesFrom(Vertex v)
	{
		List<Edge> adj = new ArrayList<Edge>();
		
		// Find the edges that start from this vertex
		for (Edge e: this.list_of_edges)
			if (e.startsFrom(v))
					// then add it to the list
					adj.add(e);
	
		return adj;
	}
	
	public Iterable<Vertex> adjacent_vertices_of_vertex(Vertex v)
	{
		Vector<Vertex> adj = new Vector<Vertex>();
		// Find the edges that start from this vertex
		for (Edge e: this.list_of_edges)
			if (e.startsFrom(e.getBegin()))
				// then add the other endpoint to the list
				adj.add(v);
		
		return adj;
	}
	public void resetGraph() {
		this.list_of_vertices.get(0).resetCount();
		list_of_edges.clear();
		list_of_vertices.clear();
	}
}
