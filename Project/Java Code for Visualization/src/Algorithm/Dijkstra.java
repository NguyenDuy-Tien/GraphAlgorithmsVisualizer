package Algorithm;

import Elements.*;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

		this.reset();

	}
	
	public void changeDestination(Vertex newDest)
	{
		this._dest = newDest;
	}
	

	public void reset()	// Petition to delete this. Let's see what the future holds
	{
		// Reset the edges to travel
		this._nextEdge = new LinkedList<>();
		this._nextEdge.addAll(this._graph.getEdgesFrom(this._root));
		
		// Reset the distance measures
		this._minDistTo = new HashMap<>();
		for (final Vertex v: this._graph.get_vertices())
			this._minDistTo.put(v, Double.MAX_VALUE);
		// Initiallize the distance from root to root be 0
		this._minDistTo.put(this._root, 0.0);

	}
	
	public boolean isDone()
	{
		return this._nextEdge.size() == 0 ;
	}
	
	public void runOne()
	{
		if (this.isDone())
			return;

		
		// Get the next Edge waiting to be examined
		Edge edge = this._nextEdge.get(0);
		this._nextEdge.remove(0);
		
		
		Vertex first = edge.getBegin();
		Vertex second = edge.getEnd();
		
		for (int iii = 0; iii < 2; ++iii)
		{
			// Workaround for Un/Directed edges
				// Swapping Vertices
				Vertex temp = first;
				first = second;
				second = temp;
				// Don't do anything if this edge is not valid
				if (!edge.startsFrom(first))
					continue;
			
			// Highlight the vertices and edge of this step
			first.draw(HIGHLIGHT_VERTEX);
			edge.draw(HIGHLIGHT_EDGE);
			
			
			if (this._minDistTo.get(second) > this._minDistTo.get(first) + edge.getWeight())
			{
				// Highlight the vertex as changed value
				this._minDistTo.put(second, this._minDistTo.get(first) + edge.getWeight());
					second.draw(HIGHLIGHT_RE_EVALUATE_VERTEX);

				// Second vertex changed minimum distance value
				// thus we need to re-evaluate all vertices connected to Second
				for (Edge e: this._graph.getEdgesFrom(second))
					if (!this._nextEdge.contains(e))
					{
						this._nextEdge.add(e);
						e.draw(HIGHLIGHT_RE_EVALUATE_EDGE);
						e.getBegin().draw(HIGHLIGHT_RE_EVALUATE_VERTEX);
						e.getEnd().draw(HIGHLIGHT_RE_EVALUATE_VERTEX);
					}
			}
		}
	}
	
	private Vertex _root;
	private Vertex _dest;

	private List<Edge> _nextEdge;
	private Map<Vertex, Double> _minDistTo;

	private final Color HIGHLIGHT_VERTEX = Color.DARKKHAKI;
	private final Color HIGHLIGHT_EDGE = Color.BLUE;
	private final Color HIGHLIGHT_RE_EVALUATE_EDGE = Color.RED;
	private final Color HIGHLIGHT_RE_EVALUATE_VERTEX = Color.DARKRED;

}