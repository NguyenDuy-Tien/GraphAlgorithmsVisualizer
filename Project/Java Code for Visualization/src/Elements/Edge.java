package Elements;

public class Edge implements Comparable<Edge> {

	private int v;
	private int u;
	private double weight;
	public Edge(int v, int u, double weight)
	{
		this.v = v;
		this.u = u;
		this.weight = weight;
	}
	
	public double weight()
	{
		return this.weight;
	}
	
	public int begin()
	{
		return v;
	}
	
	public int other(int vertex)
	{
		if (v!=vertex)
		{
			return v;
		}
		return u;
	}
	
	public int end()
	{
		return u;
	}
	
	@Override
	public int compareTo(Edge that) {
		// TODO Auto-generated method stub
		if (this.weight() < that.weight())
		{
			return -1;
		}
		if (this.weight() == that.weight())
		{
			return 0;
		}
		return 1;
	}
	

}
