package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class BSTNode {
	public int value;
	public BSTNode left;
	public BSTNode right;
	
	public Circle circle;
	
	BSTNode(int data, double centerX, double centerY) {
		value = data;
		left = right = null;
		circle = new Circle(20, Color.LIGHTBLUE);
		
		// Setting center of circle
		circle.setCenterX(centerX);
		circle.setCenterY(centerY);
		circle.setStroke(Color.BLACK);
		
	}
}
