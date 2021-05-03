package old;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Vector;

public class COMMST {
	private int nEdges, nPoints;
	static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
	public class UndirectedEdge implements Comparable<UndirectedEdge>
	{
		int first; //u
		int second; //v
		double weight;
		public UndirectedEdge(int u, int v)
		{
			this.first = u;
			this.second = v;
			this.weight = 0.0;
		}
		public UndirectedEdge(int fr, int t, double w)
		{
			this.first = fr;
			this.second = t;
			this.weight = w;
		}
		public int compareTo(UndirectedEdge e)
		{
			if (this.weight() > e.weight())
			{
				return 1;
			}
			else
			{
				if (this.weight() == e.weight())
				{
					return 0;
				}
			}
			return -1;
		}
		public double weight()
		{
			return this.weight;
		}
		public int either()
		{
			return this.first;
		}
		public int other(int v)
		{
			if (first!=v)
			{
				return first;
			}
			return this.second;
		}
		public String toString()
		{
			return (first + 1) + " - " + (second + 1) + " : " + weight;
		}
	}
	public class UndirectedGraph
	{
		int V; //number of vertices
		int E; //number of edges
		Vector<Vector<Integer>> adjP;
		
		public UndirectedGraph(int nP)
		{
			this.V = nP;
			this.E = 0;
			
			adjP = new Vector<Vector<Integer>>(nP);
			adjP.add(0, new Vector<Integer>());
			for (int i = 1; i < V; i++)
			{
				adjP.add(i, new Vector<Integer>());
			}
		}
		public int V()
		{
			return this.V;
		}
		public int E()
		{
			return this.E;
		}
		public void AddEdge(UndirectedEdge e)
		{
			int u = e.either();
			int v = e.other(u);
			adjP.get(u).add(v);
			adjP.get(v).add(u);
			this.E++;
		}
		public Iterable<Integer> adjP(int v)
		{
			return adjP.get(v);
		}
		
		public int degree(int v)
		{
			return adjP.get(v).size();
		}
	}
	public class DFS
	{
		private boolean[] marked;
		private int[] ID;
		private int noofCC;
		public DFS(UndirectedGraph G, int source)
		{
			ID = new int[G.V()];
			marked = new boolean[G.V()];
			dfs(G, source);
		}
		public DFS(UndirectedGraph G)
		{
			ID = new int[G.V()];
			marked = new boolean[G.V()];
			for (int i = 0; i < G.V(); i++)
			{
				if (!marked[i])
				{
					noofCC++;
					dfs(G,i);
				}
			}
		}
		private void dfs(UndirectedGraph G, int source)
		{
			marked[source] = true;
			ID[source] = noofCC;
			for (Integer e: G.adjP(source))
			{
				if (!marked[e])
				{
					dfs(G,e);
				}
			}
		}
		public boolean connected(int u, int v)
		{
			return (ID[u] == ID[v]);
		}
		public int totalofCC()
		{
			return noofCC;
		}
	}
	public class WUGraph
	{
		private int V;
		private int E;
		private Vector<Vector<UndirectedEdge>> adj;
		private Vector<HashMap<Integer, Double>> dist;
		private Vector<UndirectedEdge> preset;
		public WUGraph(int NP)
		{
			this.V = NP;
			this.E = 0;
			dist = new Vector<HashMap<Integer, Double>>();
			adj = new Vector<Vector<UndirectedEdge>>();
			for (int i = 0; i < V; i++)
			{
				dist.add(i, new HashMap<Integer, Double>());
				adj.add(i,new Vector<UndirectedEdge>());
			}
			preset = new Vector<UndirectedEdge>();
		}
		public Vector<UndirectedEdge> pre()
		{
			return this.preset;
		}
		public double dist(int u, int v)
		{
			if (dist.get(u).containsKey(v))
			{
				return dist.get(u).get(v);
			}
			return Double.POSITIVE_INFINITY;
		}
		public int V()
		{
			return V;
		}
		public int E()
		{
			return E;
		}
		public void AddList(UndirectedEdge e)
		{
			preset.add(e);
		}
		public void addEdge(UndirectedEdge e)
		{
			int v = e.either();
			int w = e.other(v);
			adj.get(v).add(e);
			dist.get(v).put(w, e.weight());
			dist.get(w).put(v, e.weight());
			adj.get(w).add(e);
			this.E++;
		}
		public Iterable<UndirectedEdge> adj(int v)
		{
			return adj.get(v);
		}
		public Iterable<UndirectedEdge> edges()
		{
			Vector<UndirectedEdge> list = new Vector<UndirectedEdge>();
			for (int i = 0; i < this.V(); i++)
			{
				for (UndirectedEdge e : this.adj(i))
				{
				//	if (e.other(i) > i)
					{
						list.add(e);
					}
				}
			}
			return list;
		}
	}
	public class Bipartite
	{
		private boolean[] marked;
		private boolean[] color;
		private boolean isBipartie;
		public Bipartite(WUGraph G)
		{
			isBipartie = true;
			marked = new boolean[G.V()];
			color = new boolean[G.V()];
			for (int i = 0; i < G.V(); i++)
			{
				if (!marked[i])
				{
					DFS(G, i);
				}
			}
		}
		private void DFS(WUGraph G, int v)
		{
			marked[v] = true;
			for (UndirectedEdge e : G.adj(v))
			{
				int w = e.other(v);
				if (!marked[w])
				{
					color[w] = !color[v];
					DFS(G, w);
				}
				else if (color[w] == color[v])
				{
					isBipartie = false;
					return;
				}
			}
		}
		public boolean Bipartie()
		{
			return this.isBipartie;
		}
	}
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
	public class KruskalMST{
		private Vector<UndirectedEdge> mst;
		private int[] root;
		private int rec;
		public int rec()
		{
			return rec;
		}
		public KruskalMST(WUGraph G)
		{
			rec = 0;
			mst = new Vector<UndirectedEdge>();
			PriorityQueue<UndirectedEdge> pq = new PriorityQueue<UndirectedEdge>();
			for (UndirectedEdge e : G.edges())
			{
				//System.out.println(e);
				pq.add(e);
			}
			root = new int[G.V()];
			UnionFind UF = new UnionFind(G.V());
			for (int i = 0; i < G.V(); i++)
			{
				root[i] = i;
			}
			for (UndirectedEdge e : G.pre())
			{
				int u = e.either();
				if (UF.connected(u, e.other(u)))
				{
					rec = 1;
					continue;
				}
				UF.union(u, e.other(u));
				mst.add(e);
			}
			while ((!pq.isEmpty()) && (mst.size() < G.V() - 1))
			{
				UndirectedEdge e = pq.poll();
				int v = e.either();
				int u = e.other(v);
				if (UF.connected(v, u))
				{
					continue;
				}
				UF.union(u, v);
				mst.add(e);
				break;
			}
		}
		
		public Iterable<UndirectedEdge> edges()
		{
			return mst;
		}
		public double weight()
		{
			double ans = 0;
			for (UndirectedEdge e: edges())
			{
				ans = ans + e.weight();
				//System.out.println(e);
			}
			return ans;
		}
	}
	public class Bridges{
		private int bridges;
		private int cnt;
		private int N;
		private int[] pre;
		private int[] low;
		public Bridges(WUGraph G)
		{
			low = new int[G.V()];
			pre = new int[G.V()];
			N = G.V();
			
			for (int i = 0; i < G.V(); i++)
			{
				low[i] = -1;
				pre[i] = -1;
			}
			for (int i = 0; i < G.V(); i++)
			{
				if (pre[i] == -1)
				{
					DFS(G, i, i);
				}
			}
		}
		public void DFS(WUGraph G, int u, int v)
		{
			pre[v] = cnt++;
			low[v] = pre[v];
			for (UndirectedEdge e : G.adj(v))
			{
				int w = e.other(v);
				if (pre[w] == -1)
				{
					DFS(G, v, w);
					low[v] = Math.min(low[v], low[w]);
					if (low[w] == pre[w])
					{
						bridges++;
					}
				}
				else if (w!=u)
				{
					low[v] = Math.min(low[v], pre[w]);
				}
			}
		}
		public int Total()
		{
			return bridges;
		}
	}
	public class ArtiPoints{
		private int[] low;
		private int[] pre;
		private int count;
		private int N;
		private boolean[] Cut;
		public ArtiPoints(WUGraph G)
		{
			low = new int[G.V()];
			pre = new int[G.V()];
			N = G.V();
			Cut = new boolean[G.V()];
			for (int i = 0; i < G.V(); i++)
			{
				low[i] = -1;
				pre[i] = -1;
			}
			for (int i = 0; i < G.V(); i++)
			{
				if (pre[i] == -1)
				{
					DFS(G, i, i);
				}
			}
		}
		public void DFS(WUGraph G, int u, int v)
		{
			int child = 0;
			pre[v] = count++;
			low[v] = pre[v];
			for (UndirectedEdge e: G.adj(v))
			{
				int w = e.other(v);
				if (pre[w] == -1)
				{
					child++;
					DFS(G, v, w);
					low[v] = Math.min(low[v], low[w]);
					if (low[w] >= pre[v] && u!=v)
					{
						Cut[v] = true;
					}
				}
				else if (w!=u)
				{
					low[v] = Math.min(low[v], pre[w]);
				}
			}
			if (u == v && child > 1)
			{
				Cut[v] = true;
			}
		}
		public int Total()
		{
			int ans = 0;
			for (int i  = 0; i < N; i++)
			{
				if (Cut[i])
				{
					ans++;
				}
			}
			return ans;
		}
	}
	public void Process() throws IOException
	{
		Reader scanner = new Reader();
		int t = scanner.nextInt();
		for (int ii = 0; ii < t; ii++)
		{
		nPoints = scanner.nextInt();
		nEdges = scanner.nextInt();
		WUGraph Graph = new WUGraph(nPoints);
		for (int i = 0; i < nEdges; i++)
		{
			int u, v;
			double w;
			u = scanner.nextInt();
			v = scanner.nextInt();
			w = scanner.nextDouble();
			UndirectedEdge e = new UndirectedEdge(u - 1, v - 1,w);
			Graph.addEdge(e);
		}
		int m;
		m = scanner.nextInt();
		for (int i = 0; i < m; i++)
		{
			int u, v;
			u = scanner.nextInt();
			v = scanner.nextInt();
			u = u - 1;
			v = v - 1;
			double w = Graph.dist(u, v);
			Graph.AddList(new UndirectedEdge(u, v, w));
		}
		KruskalMST kmst = new KruskalMST(Graph);
		
		
		int k = 0;
		double ans = Double.POSITIVE_INFINITY;
		for (UndirectedEdge e : kmst.edges())
		{
			k++;
			ans = Math.min(e.weight(), ans);
		}
		if (m!=(nPoints - 2)||k!=(nPoints - 1)||kmst.rec()==1)
		{
			System.out.println(-1);
		}
		else
		{
			System.out.println((int)kmst.weight());
		}
		}
	}
	public static void main(String[] args) throws IOException{
		COMMST s = new COMMST();
		s.Process();
	}

}
