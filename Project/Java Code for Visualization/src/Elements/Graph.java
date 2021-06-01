package Elements;

import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Algorithm.Drawable;
import Elements.*;

public class Graph implements Drawable{
	private List<Vertex> list_of_vertices;
	private List<Edge> list_of_edges;
	
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
	
	public void addVertex(Vertex v)
	{
		this.list_of_vertices.add(v);
	}
	
	public void removeVertex(Vertex v)
	{
		for (Edge e: this.list_of_edges)
		{
			if (e.getBegin().equals(v) || e.getEnd().equals(v))
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

			if (e.startsFrom(v) || (e.getEnd().equals(v) && e instanceof UndirectedEdge))

					// then add it to the list
					adj.add(e);
	
		return adj;
	}
	public boolean isExistsEdge(Vertex begin, Vertex end)
	{
		for (Edge edge: list_of_edges)
		{
			if (edge.getBegin().equals(begin) && edge.getEnd(edge.getBegin()).equals(end))
			{
				return true;
			}
			else if (edge instanceof UndirectedEdge && edge.getBegin().equals(end) && edge.getEnd(edge.getBegin()).equals(begin))
			{
				return true;
			}
		}
		return false;
	}
	
	public Iterable<Vertex> adjacent_vertices_of_vertex(Vertex v)
	{
		Vector<Vertex> adj = new Vector<Vertex>();
		// Find the edges that start from this vertex
		for (Edge e: this.list_of_edges)
		{
			if (e.startsFrom(v) || (e instanceof UndirectedEdge && (e.getBegin().equals(v))||e.getEnd().equals(v)))
			{
				// then add the other endpoint to the list
				adj.add(e.getEnd(v));
			}
		}	
		

		return adj;
	}
	
	// Clear all Edges, Vertices
	// And reset Vertex ID counter
	public void resetGraph() {		
		Vertex.resetCounter();
		list_of_vertices.clear();
		list_of_edges.clear();
	}
	
	public void draw(Color color)
	{
	}
	
	public Collection<Node> drawableObjects()
	{
		ArrayList<Node> allObjects = new ArrayList<Node>();
		for (Edge e: this.list_of_edges)
		{
			allObjects.addAll(e.drawableObjects());
		}
		for (Vertex v: this.list_of_vertices)
		{
			allObjects.addAll(v.drawableObjects());
		}
		return allObjects;
	}
	
}
