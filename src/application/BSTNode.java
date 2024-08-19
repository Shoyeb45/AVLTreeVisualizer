package application;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class BSTNode {
	public static final double RADIUS = 20;
	public int value;
	public BSTNode left;
	public BSTNode right;
	public Circle circle;
	public Text text;
	
	BSTNode(int data, double centerX, double centerY) {
		value = data;
		left = right = null;
		circle = new Circle(RADIUS, Color.LIGHTBLUE);
		text = new Text(String.valueOf(data));
		
		// Setting center of circle
		circle.setCenterX(centerX);
		circle.setCenterY(centerY);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(2);
		Bounds bounds = text.getBoundsInLocal();
        double textWidth = bounds.getWidth();
        double textHeight = bounds.getHeight();

        text.setX(centerX - textWidth / 2);
        text.setY(centerY + textHeight / 4);
	}
	
	public void updatePositions(double x, double y) {
		this.circle.setCenterX(x);
		this.circle.setCenterX(y);
		Bounds bounds = this.text.getBoundsInLocal();
		double textW = bounds.getWidth(), textH = bounds.getHeight();
		
		text.setX(x - textW / 2);
		text.setY(y - textH);
	}
}
