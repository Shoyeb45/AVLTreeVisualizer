package application;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BSTNode {
	
	/**
	 * Constants
	 */
	public static final double RADIUS = 20; // Radius of node
	
	/**
	 * Attributes of BSTNode class
	 */
	public int value; 		  // To hold the data in node
	public BSTNode left;      // To hold the left child of node
	public BSTNode right;     // To hold the right child of node
	public Circle circle;     // Circle for enclosing the value inside it
	public double leftWidth;  // To adjust according to number of nodes in left sub-tree
	public double rightWidth; // To adjust according to number of nodes in left sub-tree
	public Text text; 		  // Text object for enclosing the value inside the circle
	public Line rightEdge;    // Line to show the pointer between node and left sub-tree
	public Line leftEdge;     // Line to show the pointer between node and right sub-tree
	public int height;		  // Height of current node
	public int balancingFactor; // Balancing factor of node: defined as rightHeight-leftHeight
	
	/**
	 * Constructor to initialize a BSTNode
	 * @param data     : Value of the node
	 * @param centerX  : x-coordinate of center of circle of node
	 * @param centerY  : y-coordinate of center of circle of node
	 */
	BSTNode(int data, double centerX, double centerY) {
		
		value = data;						      // Assigning value to given value(data)
		left = right = null;				      // Assigning left and right pointer to null
		circle = new Circle(RADIUS);		      // Assigning new Circle object to circle with radius RADIUS
		text = new Text(String.valueOf(data));    // Assigning new Text object to text with data value
		leftWidth = rightWidth = 0;			      // For new node the left width and right width will be 0
		rightEdge = new Line();				      // Assigning new Line object to rightEdge(i.e., edge connecting left and current node)
		leftEdge = new Line();                    // Assigning new Line object to leftEdge(i.e., edge connecting right and current node)
		
		
		// circle :-
		circle.setCenterX(centerX);           	  // Setting x-coordinate of center
		circle.setCenterY(centerY);			  	  // Setting y-coordinate of center	
		circle.setStroke(Color.BLACK);        	  // Setting stroke color to black of circle
		circle.setStrokeWidth(2);             	  // Setting stroke width of circle
		circle.setFill(Color.LIGHTBLUE);      	  // Setting background color of circle
		
		// text :- 
		Bounds bounds = text.getBoundsInLocal();  // Getting bound parameters for setting the text in center of circle
        double textWidth = bounds.getWidth();     // Getting width of text
        double textHeight = bounds.getHeight();   // Getting height of text
        text.setX(centerX - textWidth / 2);		  // Setting x-coordinate of text in center of circle	
        text.setY(centerY + textHeight / 4);	  // Setting y-coordinate of text in center of circle
        
	}
	
	/**
	 * Method for updating positions with new coordinates-(x, y)
	 * @param x : x-coordinate of center of circle of node
	 * @param y : y-coordinate of center of circle of node
	 */
	public void updatePositions(double x, double y) {
		this.circle.setCenterX(x);   // Set new x-coordinate
		this.circle.setCenterY(y);   // Set new y-coordinate
		
		// Set text
		Bounds bounds = this.text.getBoundsInLocal();
		double textW = bounds.getWidth(), textH = bounds.getHeight();
		
		text.setX(x - textW / 2);
		text.setY(y + textH / 4);
		
		
		// Set the coordinates of lines, the starting and ending position will be same to show the growing animation of line
		// For left edge
		leftEdge.setStartX(x - BinarySearchTree.DIST);
        leftEdge.setStartY(y + BinarySearchTree.K);
        leftEdge.setEndX(x - BinarySearchTree.DIST);
        leftEdge.setEndY(y + BinarySearchTree.K);
        
        // For right edge
        rightEdge.setStartX(x + BinarySearchTree.DIST);
        rightEdge.setStartY(y + BinarySearchTree.K);
        rightEdge.setEndX(x + BinarySearchTree.DIST);
        rightEdge.setEndY(y + BinarySearchTree.K);
        leftEdge.setStrokeWidth(0);
        rightEdge.setStrokeWidth(0);
	}
	
	/**
	 * Method to make line visible
	 * @param line : Line which needs to be made visible
	 */
	public static void showLine(Line line) {
		line.setStrokeWidth(2);
	}
	
	/**
	 * Method for adding left and right edge of node to the pane
	 * @param pane : Pane which will hold the Tree
	 */
	public void addLineToPane(Pane pane) {
		pane.getChildren().addAll(this.rightEdge, this.leftEdge);
	}
}
