package Elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.text.Font;

public class DirectedEdge extends Edge{

	public DirectedEdge(Vertex v, Vertex u, int weight) {
		super(v, u, weight);
	}
	
	@Override 
	public boolean startsFrom(Vertex v)
	{
		return this.getBegin().equals(v);
	}
	
	@Override
	public boolean equals(Object o)
	{
		return o == this ||			// Self Comparing
				( (o instanceof DirectedEdge) && 	// Test for Same type 
					// Test for same two endpoints
					((DirectedEdge) o).getBegin() == this.getBegin() &&
					((DirectedEdge) o).getEnd() == this.getEnd());
	}
	

	// Draw an arrow from Begin vertex to End vertex
    // Translated to OOP from GraphController which was wrote by Hai or Tien (or both).
	@Override
	public void draw(Color colour) {
        // Draw Arrow Body
        getElements().add(new MoveTo(this.getBegin().getCenterX(), 
        								this.getBegin().getCenterY()));
        
        getElements().add(new LineTo(this.getEnd().getCenterX(), 
										this.getEnd().getCenterY()));
        
        //ArrowHead
        double XDiff = this.getEnd().getCenterX() - this.getBegin().getCenterX();
        double YDiff = this.getEnd().getCenterY() - this.getBegin().getCenterY();
        double angle = Math.atan2(YDiff, XDiff) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        
        // Set arrow wings 
        double arrowHeadSize =  6;
        setFill(Color.BLACK);
        
        // Two arrow wings
        double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + this.getEnd().getCenterX();
        double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + this.getEnd().getCenterY();
        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + this.getEnd().getCenterX();
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + this.getEnd().getCenterY();
        
        // Draw Arrow Head
        getElements().add(new LineTo(x1, y1));
        getElements().add(new LineTo(x2, y2));
        getElements().add(new LineTo(this.getEnd().getCenterX(), 
        								this.getEnd().getCenterY()));
		
        // Setup the weight-displaying label
    	double ratioXY = Math.abs(XDiff / YDiff);
        weightLabel.setFont(new Font(10.6));
        if(XDiff > 0 && YDiff > 0) {
        	weightLabel.setLayoutX((this.getBegin().getCenterX() + this.getEnd().getCenterX())/2 + (YDiff/30));
        	weightLabel.setLayoutY((this.getBegin().getCenterY() + this.getEnd().getCenterY())/2 - (XDiff/30));
        }
        else if(XDiff < 0 && YDiff > 0) {
        	weightLabel.setLayoutX((this.getBegin().getCenterX() + this.getEnd().getCenterX())/2 - (YDiff/30));
        	weightLabel.setLayoutY((this.getBegin().getCenterY() + this.getEnd().getCenterY())/2 + (XDiff/30));
        }
        else if(XDiff > 0 && YDiff < 0) {
        	weightLabel.setLayoutX((this.getBegin().getCenterX() + this.getEnd().getCenterX())/2 - (YDiff/30));
        	weightLabel.setLayoutY((this.getBegin().getCenterY() + this.getEnd().getCenterY())/2 + (XDiff/30));
        }
        else {
        	weightLabel.setLayoutX((this.getBegin().getCenterX() + this.getEnd().getCenterX())/2 + (YDiff/30));
        	weightLabel.setLayoutY((this.getBegin().getCenterY() + this.getEnd().getCenterY())/2 - (XDiff/30));
        }
        
    }
}
