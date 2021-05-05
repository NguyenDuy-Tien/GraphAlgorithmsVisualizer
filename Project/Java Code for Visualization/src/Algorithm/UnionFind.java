package Algorithm;

public class UnionFind
{
	private int[] parent;
	private int count;
	private int[] rank;
	public UnionFind(int n)
	{
		rank = new int[n];
		count = n;
		parent = new int[n];
		for (int i = 0; i < n; i++)
		{
			parent[i] = i;
			rank[i] = 0;
		}
	}
	public void union(int p, int q)
	{
		int rootP = find(p);
		int rootQ = find(q);
		if (rank[rootP] > rank[rootQ])
		{
			parent[rootQ] = rootP;
		}
		if (rank[rootQ] > rank[rootP])
		{
			parent[rootP] = rootQ;
		}
		if (rank[rootQ] == rank[rootP])
		{
			parent[rootQ] = rootP;
			rank[rootP]++;
		}
		count--;
	}
	public int find(int q)
	{
		while (q != parent[q])
		{
			parent[q] = parent[parent[q]];
			q = parent[q];
		}
		return q;
	}
	public boolean connected(int p, int q)
	{
		return (find(p) == find(q));
	}
	public int count()
	{
		return count;
	}
}