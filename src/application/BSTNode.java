package application;

import javafx.scene.shape.Circle;

public class BSTNode {
	public int value;
	public BSTNode left;
	public BSTNode right;
	
	public Circle circle;
	
	BSTNode(int data) {
		value = data;
		left = right = null;
		
	}
}
