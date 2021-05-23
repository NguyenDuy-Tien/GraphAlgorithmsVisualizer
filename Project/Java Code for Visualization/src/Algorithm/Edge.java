package Algorithm;

public abstract class Edge implements Comparable<Edge> {

	private Vertex begin;
	private Vertex end;
	private double weight;
	public Edge(Vertex v, Vertex u, double weight)
	{
		this.begin = v;
		this.end = u;
		this.weight = weight;
	}
	
	public double getWeight()
	{
		return this.weight;
	}
	
	public Vertex getBegin()
	{
		return begin;
	}
	
	public Vertex getEnd(Vertex vertex)
	{
		if (begin.equals(vertex))
		{
			return begin;
		}
		return end;
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if (o == this)
		{
			return true;
		}
		if (!(o instanceof Edge))
		{
			return false;
		}
		Edge e = (Edge) o;
		return ((e.getBegin().equals(this.getBegin())) 
				&& (e.getEnd(e.getBegin()).equals(this.getEnd(this.getBegin())))
				&& (e.getWeight() == this.getWeight()));
	}
	@Override
	public int compareTo(Edge that) {
		// TODO Auto-generated method stub
		if (this.getWeight() < that.getWeight())
		{
			return -1;
		}
		if (this.getWeight() == that.getWeight())
		{
			return 0;
		}
		return 1;
	}
	

}
