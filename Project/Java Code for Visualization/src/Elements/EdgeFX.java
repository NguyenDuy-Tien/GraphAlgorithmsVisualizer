package Elements;

import Algorithm.Edge;
import Algorithm.Vertex;
import javafx.scene.control.Label;
import javafx.scene.shape.Shape;

public class EdgeFX {
	public Edge edge;
	public Shape line;
	public Label weightLabel;
	public Shape getLine() {
		return line;
	}
	public EdgeFX(Edge edge, Shape line, Label weightLabel) {
		this.edge = edge;
		this.line = line;
		this.weightLabel = weightLabel;
	}
	public EdgeFX(Vertex begin, Vertex end, double weight, Shape line, Label weightLabel) {
		this.edge = new Edge(begin, end, weight) {
			@Override
			public void draw(int colour) {
				// TODO Auto-generated method stub
				
			}
		};
		this.line = line;
		this.weightLabel = weightLabel;
	}

}
