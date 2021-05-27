package Algorithm;

import Elements.*;

import java.util.Map;
import java.util.ArrayList;


public class Dijkstra extends Algorithm
{
	public Dijkstra(Graph g)
	{
		this._graph = g;
		 
		this.changeRoot(g.get_vertices().get(0));

		this.changeDestination(g.get_vertices().get(g.get_vertices().size()-1));
	}
	
	public void changeRoot(Vertex newRoot)
	{
		this._root = newRoot;
	}
	
	public void changeDestination(Vertex newDest)
	{
		this._dest = newDest;
	}
	
	public boolean isDone()
	{
		return true;
	}
	
	public void runOne()
	{
		if (this.isDone())
			return;
		
	}
	
	private Vertex _root;
	private Vertex _dest;
	private Map<Vertex, ArrayList<Edge>> _edgeTo;
	private Map<Double, Vertex> _state;
}