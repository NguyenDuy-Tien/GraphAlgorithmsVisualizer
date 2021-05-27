package Algorithm;

import Elements.*;
import java.util.PriorityQueue;



public class Kruskal extends MST
{
	public Kruskal(Graph g)
	{
		this._graph = g;
		
		this._edgeWeight = new PriorityQueue<>();
		
		this._edgeWeight.addAll(g.get_edges());
	}
	
	public void runOne()
	{
		if (this.isDone())
			return;
		
		
	}
	
	public boolean isDone()
	{
		return this._currentEdges.size() == this._graph.get_vertices().size()-1;
	}
	
	
	private PriorityQueue<Edge> _edgeWeight;
}