package Algorithm;

import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public interface Drawable {
	public void draw(Color colour);
	
	// Return a collection of drawable objects to the caller
	// So that the caller knows what to render
	public Collection<Node>  drawableObjects();
}
