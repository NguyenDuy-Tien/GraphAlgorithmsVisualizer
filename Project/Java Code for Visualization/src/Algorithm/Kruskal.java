package Algorithm;

import Elements.*;
import javafx.scene.paint.Color;

import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal extends MST
{
	public Kruskal(Graph g)
	{
		this._graph = g;
		
		this.reset();
	}
	
	public void reset()
	{
		// Sort the edges by its weight
			this._lightestEdge = new PriorityQueue<Edge>();
			this._lightestEdge.addAll(this._graph.get_edges());
			this._currentEdges = new ArrayList<Edge>();
			// Initial a forest, with one vertex each tree
			List<Vertex> allTheVertices = this._graph.get_vertices();
			this._forests = new LinkedList<List<Vertex>>();
			for (int iii = 0; iii < allTheVertices.size(); ++iii)
			{
				this._forests.add(new LinkedList<Vertex>());
				this._forests.get(iii).add(allTheVertices.get(iii));
			}
	}
	
	public void runOne()
	{
		if (this.isDone())
			return;

		// Find the lightest edge
		// that connects two trees together
		while (true)
		{
			Edge lightestEdge = this._lightestEdge.poll();
			
			// Detect the location of two ends of the edge
			int tree1 = this.forestOf(lightestEdge.getBegin()); 
			int tree2 = this.forestOf(lightestEdge.getEnd());
			
			// Connect two trees
			// Make sure we're not creating any cycle 
			// and then add it to the MST
			if (tree1 != tree2)
			{
				lightestEdge.draw(HIGHLIGHT_EDGE);
				lightestEdge.getBegin().draw(HIGHLIGHT_VERTEX);
				lightestEdge.getEnd().draw(HIGHLIGHT_VERTEX);
				this._currentEdges.add(lightestEdge);
				
				// Merge two trees into one
				for (Vertex v: this._forests.get(tree2))
				{
					this._forests.get(tree1).add(v);
				}
				this._forests.remove(tree2);
				
				break;
			}
		}
	}
	
	public boolean isDone()
	{
		// Done when there's only one tree left - the MST
		return this._forests.size() < 2;
	}
	
	// Find which forest this vertex belongs to
	private int forestOf(Vertex v)
	{
		for (int iii = 0; iii < this._forests.size(); ++iii)
		{
			if (this._forests.get(iii).indexOf(v) != -1)
				return iii;
		}
		
		// If our graph has this vertex v
		// Then it absolutely CANNOT reach this statement
		// But oh well, Java needs this
		return -1;	
	}
	
	
	private final Color HIGHLIGHT_VERTEX = Color.DARKKHAKI;
	private final Color HIGHLIGHT_EDGE = Color.RED;
	
	private PriorityQueue<Edge> _lightestEdge;
	private List<List<Vertex>> _forests;
}