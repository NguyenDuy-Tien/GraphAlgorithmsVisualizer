package Algorithm;

import Elements.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Prim extends MST
{
	public boolean isDone()
	{
		return this._graph.get_vertices().size() == this._currentVertices.size();
	}
	
	public void runOne()
	{
		if (this.isDone())
			return ;
		
		ArrayList<Vertex> mightBeAddedNext = new ArrayList<Vertex>();
		while (true)
		{
			Edge lightestBridge = this._bridges.poll();
			
			// Get two ends of the edges
			mightBeAddedNext.add(lightestBridge.getBegin());		
			mightBeAddedNext.add(lightestBridge.getEnd());

			// Test if we're adding any new vertex to the tree
			mightBeAddedNext.removeAll(this._currentVertices);
			if (mightBeAddedNext.size() == 1)
			{
				// Add the new bridge
				_currentEdges.add(lightestBridge);
				lightestBridge.changeColorEdge(Color.YELLOW);
				// Add the new vertex 
				_currentVertices.addAll(mightBeAddedNext);
				break;
			}
		}
	}
	
	public void changeRoot(Vertex newRoot)
	{
		_currentVertices.clear();
		_currentVertices.add(newRoot);
		
		_currentEdges.clear();
		
		Iterator<Edge> it =  this._graph.adjacent_edges_of_vertex(newRoot).iterator();
		
		while (it.hasNext())
			_currentEdges.add(it.next());
	}
	
	public Prim(Graph g)
	{
		this._graph = g;
		
		this.changeRoot(g.get_vertices().get(0));
	}
	
	private PriorityQueue<Edge> _bridges;
	private ArrayList<Vertex> _currentVertices;
}