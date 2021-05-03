package Algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Elements.Edge;
import Elements.Graph;

public class KruskalMST {
	private PriorityQueue<Edge> pq;
	private ArrayList<Edge> mst;
	
	public KruskalMST(Graph G)
	{
		mst = new ArrayList<Edge>();
		pq = new PriorityQueue<Edge>();
	}

}
