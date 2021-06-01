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
		double XDiff = this.getEnd().getCenterX() - this.getBegin().getCenterX();
        double YDiff = this.getEnd().getCenterY() - this.getBegin().getCenterY();
        double ratioXY = Math.abs(XDiff / YDiff);
        double startX = this.getBegin().getCenterX() + XDiff/ratioXY/50;
        double startY = this.getBegin().getCenterY() - YDiff*ratioXY/50;
        double endX = this.getEnd().getCenterX() + XDiff/ratioXY/50;
        double endY = this.getEnd().getCenterY() - YDiff*ratioXY/50;
        // Draw Arrow Body
        getElements().add(new MoveTo(startX, 
        								startY));
        
        getElements().add(new LineTo(endX, 
										endY));
        
        //ArrowHead
        
        double angle = Math.atan2(YDiff, XDiff) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        
        // Set arrow wings 
        double arrowHeadSize =  6;
        setFill(Color.BLACK);
        
        // Two arrow wings
        double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
        
        // Draw Arrow Head
        getElements().add(new MoveTo(x1, y1));
        getElements().add(new LineTo(endX, 
				endY));
        getElements().add(new MoveTo(x2, y2));
        getElements().add(new LineTo(endX, 
        								endY));
		
        // Setup the weight-displaying label
        weightLabel.setFont(new Font(10.6));
        if(XDiff > 0 && YDiff > 0) {
        	weightLabel.setLayoutX((startX + endX)/2 + (YDiff*ratioXY/50));
        	weightLabel.setLayoutY((startY + endY)/2 - (XDiff/ratioXY/50));
        }
        else if(XDiff < 0 && YDiff > 0) {
        	weightLabel.setLayoutX((startX + endX)/2 - (YDiff*ratioXY/50));
        	weightLabel.setLayoutY((startY + endY)/2 + (XDiff/ratioXY/50));
        }
        else if(XDiff > 0 && YDiff < 0) {
        	weightLabel.setLayoutX((startX + endX)/2 - (YDiff*ratioXY/50));
        	weightLabel.setLayoutY((startY + endY)/2 + (XDiff/ratioXY/50));
        }
        else {
        	weightLabel.setLayoutX((startX + endX)/2 + (YDiff*ratioXY/50));
        	weightLabel.setLayoutY((startY + endY)/2 - (XDiff/ratioXY/50));
        }
        this.setStroke(colour);
    }
}
