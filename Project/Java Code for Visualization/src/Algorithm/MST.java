package Algorithm;

import java.util.List;
import Elements.*;

public abstract class MST extends Algorithm
{
	protected List<Edge> _currentEdges;
	protected boolean connected;
	protected int part_of_connected_component[];
	public double totalWeight()
	{
		double result = 0;
		for (Edge e: this._currentEdges)
			result += e.getWeight();
		return result;
	}

	public void checkConnected()
	{
		if (this._graph.get_edges().size() < (this._graph.get_vertices().size() - 1))
		{
			connected = false;
			return;
		}
		part_of_connected_component = new int[this._graph.get_vertices().size()];
		for (int i = 0; i < this._graph.get_vertices().size(); i++)
		{
			part_of_connected_component[i] = i;
		}
		dfs(this._graph.get_vertices().get(0));
		for (int i = 1; i < this._graph.get_vertices().size(); i++)
		{
			if (part_of_connected_component[i]!=part_of_connected_component[i - 1])
			{
				connected = false;
				return;
			}
		}
		connected = true;
	}

	public void dfs(Vertex v)
	{
		for (Vertex u: this._graph.adjacent_vertices_of_vertex(v))
		{
			if (part_of_connected_component[Integer.valueOf(u.getID()) - 1]
					!= part_of_connected_component[Integer.valueOf(v.getID()) - 1])
			{
				part_of_connected_component[Integer.valueOf(u.getID()) - 1]
						= part_of_connected_component[Integer.valueOf(v.getID()) - 1];
				dfs(u);
			}
		}
	}
	public String toString()
	{
		if (!this.connected)
		{
			return "The graph is not connected";
		}
		StringBuilder res = new StringBuilder();
		res.append("Current total weight:\n "+ this.totalWeight() + "\nList of known edges:\n");
		for (Edge e: this._currentEdges)
		{
			res.append(e.getBegin().getID() + " <-> " + e.getEnd().getID() + "\n");
		}
		return res.toString();
	}
}