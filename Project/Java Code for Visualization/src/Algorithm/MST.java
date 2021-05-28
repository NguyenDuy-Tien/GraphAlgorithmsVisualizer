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
}