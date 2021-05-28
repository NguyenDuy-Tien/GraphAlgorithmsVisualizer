package Algorithm;

import Elements.*;
import java.util.Queue;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

public class Dijkstra extends Algorithm
{
	public Dijkstra(Graph g)
	{
		this._graph = g;
		
		this.changeRoot(g.get_vertices().get(0));

		this.changeDestination(g.get_vertices().get(g.get_vertices().size()-1));
		 
		this._minDistTo = new HashMap<>();
	}
	
	public void changeRoot(Vertex newRoot)
	{
		this._root = newRoot;
		
	}
	
	public void changeDestination(Vertex newDest)
	{
		this._dest = newDest;
	}
	
	private void reset()
	{
		this._untravelledEdges.clear();
		this._untravelledEdges.addAll(this._graph.getEdgesFrom(this._root));
	}
	
	public boolean isDone()
	{
		return this._untravelledEdges.size() == 0 ;
	}
	
	public void runOne()
	{
		if (this.isDone())
			return;
	}
	
	private Vertex _root;
	private Vertex _dest;
	private Queue<Edge> _untravelledEdges;
	private HashMap<Vertex, Double> _minDistTo;
}