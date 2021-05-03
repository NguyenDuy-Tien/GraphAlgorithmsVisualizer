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

import StrongConnectedComponents.solution.DepthFirstOrder;
import StrongConnectedComponents.solution.DiGraph;

public class solution {
	private int nEdges, nPoints;
	private boolean[] marked;
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
	public class WeightedDirectedEdge
	{
		int from; //starting point
		int to; //endpoint
		double weight;
		public WeightedDirectedEdge(int fr, int t, double w)
		{
			this.from = fr;
			this.to = t;
			this.weight = w;
		}
		public double weight()
		{
			return this.weight;
		}
		public int from()
		{
			return this.from;
		}
		public int to()
		{
			return this.to;
		}
		public String toString()
		{
			return from + " -> " + to + " : " + weight;
		}
	}
	public class EWDiGraph
	{
		int V; //number of vertices
		int E; //number of edges
		Vector<Vector<WeightedDirectedEdge>> adj;
		public EWDiGraph(int nP)
		{
			this.V = nP;
			this.E = 0;
			adj = new Vector<Vector<WeightedDirectedEdge>>(nP+1);
			adj.add(0, new Vector<WeightedDirectedEdge>());
			for (int i = 1; i <= V; i++)
			{
				adj.add(i, new Vector<WeightedDirectedEdge>());
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
		public void AddEdge(WeightedDirectedEdge e)
		{
			adj.get(e.from()).add(e);
			this.E++;
		}
		public Iterable<WeightedDirectedEdge> adj(int v)
		{
			return adj.get(v);
		}
		public void outString()
		{
			for (int i = 1; i <= V; i++)
			{
				for (WeightedDirectedEdge e : adj(i))
				{
					System.out.println(e);
				}
			}
		}
	}
	public class ShortestPath
	{
		WeightedDirectedEdge[] edgeTo;
		double[] distTo;
		HashMap<Integer, Double> dist;
		double maxconst = Double.POSITIVE_INFINITY;
		PriorityQueue<Integer> pq;
		public ShortestPath(EWDiGraph G, int v)
		{
			distTo = new double[G.V()+1];
			edgeTo = new WeightedDirectedEdge[G.V()+1];
			marked[v] = true;
			dist = new HashMap<Integer, Double>();
			pq = new PriorityQueue<Integer>((a, b) -> dist.get(a).compareTo(dist.get(b)));
			for (int i = 0; i <= G.V(); i++)
			{
				distTo[i] = maxconst;
				dist.put(i, maxconst);
			}
			distTo[v] = 0.0;
			dist.put(v,0.0);
			pq.add(v);
			while (!pq.isEmpty())
			{
				Relax(G, pq.poll());
			}
		}
		double distTo(int v)
		{
			if (distTo[v]!=maxconst)
			{	
				return distTo[v];
			}
			return -1;
		}
		boolean hasPathto(int v)
		{
			return true;
		}
		void Relax(EWDiGraph G, int v)
		{
			for (WeightedDirectedEdge e: G.adj(v))
			{
				int w = e.to();
				if (distTo[w] > distTo[v] + e.weight())
				{
					distTo[w] = distTo[v] + e.weight();
					edgeTo[w] = e;
					marked[w] = true;
					pq.add(w);
					
					dist.put(w, distTo[w]);
				}
			}
		}
		
	}
	public class DirectedEdge
	{
		int from; //starting point
		int to; //endpoint
		double weight;
		public DirectedEdge(int fr, int t, double w)
		{
			this.from = fr;
			this.to = t;
			this.weight = w;
		}
		public double weight()
		{
			return this.weight;
		}
		public int from()
		{
			return this.from;
		}
		public int to()
		{
			return this.to;
		}
		public String toString()
		{
			return from + " -> " + to + " : " + weight;
		}
	}
	public class StrongConnectedComponents
	{
		private boolean[] marked; //reached or not
		private int[] ID; //which component
		private int count;
		public StrongConnectedComponents(DiGraph G)
		{
			DepthFirstOrder order = new DepthFirstOrder(G.reverse());
			marked = new boolean[G.V()];
			ID = new int[G.V()];
			count = 0;
			
			for (int i: order.ReversePostOrder())
			{
				//System.out.print(i + " ");
				if (!marked[i])
				{
					this.dfs(G,i);
					//System.out.println("dfs(" + i +")");
					count++;
					//System.out.println();
				}
			}
		}
		private void dfs(DiGraph G, int v)
		{
			marked[v] = true;
			ID[v] = count;
			for (int w: G.adj(v))
			{
				if (!marked[w])
				{
					dfs(G, w);
				}
			}
		}
		public boolean Connected(int v, int w)
		{
			return ID[w] == ID[v];
		}
		public int id(int v)
		{
			return ID[v];
		}
		public int count()
		{
			return count;
		}
	}

	public class DepthFirstOrder
	{
		private boolean[] marked;
		private	LinkedList<Integer> PreOrder;
		private LinkedList<Integer> PostOrder;
		private Stack<Integer> ReversePostOrder;
		public DepthFirstOrder(DiGraph G)
		{
			PreOrder = new LinkedList<Integer>();
			PostOrder = new LinkedList<Integer>();
			ReversePostOrder = new Stack<Integer>();
			marked = new boolean[G.V()];
			for (int i = 0; i < G.V(); i++)
			{
				//System.out.println(i + " " + marked[i]);
				if (!marked[i])
				{
					dfs(G, i);
					//System.out.println();
				}
			}
			
		}
		private void dfs(DiGraph G, int v)
		{
			PreOrder.add(v);
			//ReversePostOrder.push(v);
			//System.out.println(v + " ");
			marked[v] = true;
			for (int w : G.adj(v))
			{
				if (!marked[w])
				{
					
					dfs(G, w);
				}
			}
			PostOrder.add(v);
			ReversePostOrder.push(v);
			//System.out.println(v);
			
		}
		public Iterable<Integer> PostOrder()
		{
			return PostOrder;
		}
		public Iterable<Integer> ReversePostOrder()
		{
			LinkedList<Integer> rev = new LinkedList<Integer>();
			for (int i : PostOrder())
			{
				rev.add(0, i);
			}
			return rev;
		}
	} 
	public class DiGraph
	{
		int V; //number of vertices
		int E; //number of edges
		Vector<Vector<Integer>> adj;
		public DiGraph(int nP)
		{
			this.V = nP;
			this.E = 0;
			adj = new Vector<Vector<Integer>>(nP);
			for (int i = 0; i < V; i++)
			{
				adj.add(i, new Vector<Integer>());
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
		public void AddEdge(int u, int v)
		{
			adj.get(u).add(v);
			this.E++;
		}
		public Iterable<Integer> adj(int v)
		{
			return adj.get(v);
		}
		public void outString()
		{
			for (int i = 0; i < V; i++)
			{
				for (Integer e : adj(i))
				{
					System.out.println(e);
				}
			}
		}
		public DiGraph reverse()
		{
			DiGraph Rev = new DiGraph(V);
			for (int i = 0; i < V; i++)
			{
				for (int e : adj(i))
				{
					Rev.AddEdge(e,i);
				}
			}
			return Rev;
		}
	}
	public void Process() throws IOException
	{
		Reader scanner = new Reader();
		nPoints = scanner.nextInt();
		nEdges = scanner.nextInt();
		marked = new boolean[nPoints+10];
		EWDiGraph Graph = new EWDiGraph(nPoints);
		for (int i = 0; i < nEdges; i++)
		{
			int u, v;
			double w;
			u = scanner.nextInt();
			v = scanner.nextInt();
			w = scanner.nextDouble();
			WeightedDirectedEdge e = new WeightedDirectedEdge(u, v, w);
			Graph.AddEdge(e);
		}
		int fr,t;
		fr = scanner.nextInt();
		t = scanner.nextInt();
		ShortestPath SP = new ShortestPath(Graph,fr);
		//Graph.outString();
		System.out.println((long)SP.distTo(t));
		//System.out.println((double)Double.POSITIVE_INFINITY - 1);
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		solution s = new solution();
		s.Process();
	}

}
