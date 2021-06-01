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
		if (this.isDone()||(!this.connected))
			return;
		
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
				if (!this._currentEdges.contains(lightestBridge))
				{
					this._currentEdges.add(lightestBridge);
				}
				
				lightestBridge.draw(HIGHLIGHT_EDGE);
				for (Edge e: this._graph.getEdgesFrom(lightestBridge.getBegin()))
				{
					if (!this._bridges.contains(e))
					{
						this._bridges.add(e);
					}
				}
				for (Edge e: this._graph.getEdgesFrom(lightestBridge.getEnd()))
				{
					if (!this._bridges.contains(e))
					{
						this._bridges.add(e);
					}
				}
				// Add the new vertex 
				_currentVertices.addAll(mightBeAddedNext);
				
				// Highlight that vertex on screen
				mightBeAddedNext.get(0).setFill(HIGHLIGHT_VERTEX);
				break;
			}
		}
	}
	
	public void changeRoot(Vertex newRoot)
	{
		_currentVertices.clear();
		_currentVertices.add(newRoot);
		
		newRoot.setFill(HIGHLIGHT_VERTEX);
		
		_currentEdges.clear();
		
		
		for (Edge e: this._graph.adjacent_edges_of_vertex(newRoot))
		{
			_bridges.add(e);
		}
	}
	
	public Prim(Graph g)
	{
		this._graph = g;
		this._root = g.get_vertices().get(0);
		this._currentVertices = new ArrayList<Vertex>();
		this._bridges = new PriorityQueue<Edge>();
		this._currentEdges = new ArrayList<Edge>();
		this.checkConnected();
		this.reset();
	}
	
	public void reset()
	{
		this.changeRoot(this._root);
	}
	

	
	private Vertex _root;
	private PriorityQueue<Edge> _bridges;
	private ArrayList<Vertex> _currentVertices;
	private final Color HIGHLIGHT_VERTEX = Color.DARKKHAKI;
	private final Color HIGHLIGHT_EDGE = Color.RED;
}