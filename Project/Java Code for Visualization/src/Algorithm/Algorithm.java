package Algorithm;


public abstract class Algorithm {

	//public abstract void reset();
	public abstract void runOne();
	
	public void runAll()
	{
		while (!this.isDone())
		{
			this.runOne();
		}
	}
	
	public abstract boolean isDone();
	
	protected Graph _graph;
}
