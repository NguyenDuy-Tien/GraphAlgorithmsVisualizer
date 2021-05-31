package Application;

import Algorithm.*;
import Elements.*;

public final class GraphPropertyHolder {
	private Graph  graph;
	private String edgeDirection;
	private String algorithmName;
	private final static GraphPropertyHolder singleton = new GraphPropertyHolder();

	public static Graph getGraph()			{return singleton.graph;}
	public static String getEdgeDirection()	{return singleton.edgeDirection;}
	public static String getAlgorithmName() {return singleton.algorithmName;}
	
	public static void setGraph(Graph g)				{singleton.graph = g;}
	public static void setEdgeDirection(String direction)	{singleton.edgeDirection = direction;}
	public static void setAlgorithmName(String algoName)	{singleton.algorithmName = algoName;}
}

