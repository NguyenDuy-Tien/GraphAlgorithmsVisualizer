package Application;

import Algorithm.Algorithm;

public final class GraphPropertyHolder {
	private String algo;
	private String direction;
	private final static GraphPropertyHolder holder = new GraphPropertyHolder();
	public String getAlgo() {
		return algo;
	}
	public void setAlgo(String algo) {
		this.algo = algo;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public static GraphPropertyHolder getHolder()
	{
		return holder;
	}
}
