package Algorithm;

import java.util.List;
import Elements.*;

public abstract class MST extends Algorithm
{
	protected List<Edge> _currentEdges;
	
	public double totalWeight()
	{
		double result = 0;
		for (Edge e: this._currentEdges)
			result += e.getWeight();
		return result;
	}
	
	public String toString()
	{
		StringBuilder res = new StringBuilder();
		res.append("Current total weight:\n "+ this.totalWeight() + "\nList of known edges:\n");
		for (Edge e: this._currentEdges)
		{
			res.append(e.getBegin().getID() + " <-> " + e.getEnd().getID() + "\n");
		}
		return res.toString();
	}
}