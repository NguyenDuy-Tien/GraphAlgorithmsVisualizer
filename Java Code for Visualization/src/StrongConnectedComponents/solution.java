package StrongConnectedComponents;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class solution {
	private int nEdges, nPoints;
	private double maxx = Double.NEGATIVE_INFINITY;
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
	class QNode { 
	    int key; 
	    QNode next; 
	  
	    // constructor to create a new linked list node 
	    public QNode(int key) 
	    { 
	        this.key = key; 
	        this.next = null; 
	    } 
	} 
	class Queue { 
	    QNode front, rear; 
	  
	    public Queue() 
	    { 
	        this.front = this.rear = null; 
	    } 
	  
	    // Method to add an key to the queue. 
	    void enqueue(int key) 
	    { 
	  
	        // Create a new LL node 
	        QNode temp = new QNode(key); 
	  
	        // If queue is empty, then new node is front and rear both 
	        if (this.rear == null) { 
	            this.front = this.rear = temp; 
	            return; 
	        } 
	  
	        // Add the new node at the end of queue and change rear 
	        this.rear.next = temp; 
	        this.rear = temp; 
	    } 
	  
	    // Method to remove an key from queue. 
	    void dequeue() 
	    { 
	        // If queue is empty, return NULL. 
	        if (this.front == null) 
	            return; 
	  
	        // Store previous front and move front one node ahead 
	        QNode temp = this.front; 
	        this.front = this.front.next; 
	  
	        // If front becomes NULL, then change rear also as NULL 
	        if (this.front == null) 
	            this.rear = null; 
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

	public void Process() throws IOException
	{
		Reader scanner = new Reader();
		nPoints = scanner.nextInt();
		nEdges = scanner.nextInt();
		DiGraph Graph = new DiGraph(nPoints);
		for (int i = 0; i < nEdges; i++)
		{
			int u, v;
			double w;
			u = scanner.nextInt();
			v = scanner.nextInt();
			Graph.AddEdge(u - 1,v - 1);
		}
		StrongConnectedComponents SCC = new StrongConnectedComponents(Graph);
		System.out.println(SCC.count());
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		solution s = new solution();
		s.Process();
	}

}
