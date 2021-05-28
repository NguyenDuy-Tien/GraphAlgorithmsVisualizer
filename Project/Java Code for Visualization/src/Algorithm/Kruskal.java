package Algorithm;

import Elements.*;

import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class Kruskal extends MST
{
	public Kruskal(Graph g)
	{
		this._graph = g;
		
		// Sort the edges by its weight
		this._lightestEdge = new PriorityQueue<>();
		this._lightestEdge.addAll(g.get_edges());
		
		// Initial a forest, with one vertex each tree
		List<Vertex> allTheVertices = g.get_vertices();
		this._forests = new LinkedList<>();
		for (int iii = 0; iii < allTheVertices.size(); ++iii)
		{
			this._forests.add((LinkedList<Vertex>) Arrays.asList(allTheVertices.get(iii)));
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
			if (tree1 != tree2)
			{
				// and then add it to the MST
				this._currentEdges.add(lightestEdge);
				
				// Merge two trees into one
				this._forests.get(tree1).addAll(this._forests.get(tree2));
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
	
	private PriorityQueue<Edge> _lightestEdge;
	private List<List<Vertex>> _forests;
}