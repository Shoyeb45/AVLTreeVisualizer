package application;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class BinarySearchTree {
	private BSTNode root;
	private Pane pane;
	
	BinarySearchTree(Pane newPane) {
		pane = newPane;
		root = null;
	}
	public void insert(int val) {
		if(!this.contains(this.root, val)) {
			this.insertUtil(this.root, val);
		}
	}
	
	
	
	/*
	 * Private method for checking if the node is present in Binary Search Tree or not
	 * @param 
	 * */
	private boolean contains(BSTNode root, int val) {
		if(root == null) {
			return false;
		}
		
		if(root.value == val) {
			return true;
		} 
		else if(root.value < val) {
			return contains(root.right, val);
		} else {
			return contains(root.left, val);
		}
	}
	
	/*
	 * Utility Method for inserting new node in given position
	 * 
	 * */
	private BSTNode insertUtil(BSTNode root, int val) {
		if(root == null) {
			BSTNode newBSTNode = new BSTNode(val, 400, 100);
			Text text = new Text(String.valueOf(val));
		
			Bounds bounds = text.getBoundsInLocal();
	        double textWidth = bounds.getWidth();
	        double textHeight = bounds.getHeight();

	        // Set the text position, centering it in the circle
	        text.setX(newBSTNode.circle.getCenterX() - textWidth / 2);
	        text.setY(newBSTNode.circle.getCenterY() + textHeight / 4); // Adjust for text's vertical height
	        
			pane.getChildren().addAll(text, newBSTNode.circle);
			return newBSTNode;
		}
		
		if(root.value < val) {
			root.right = insertUtil(root.right, val);
			return root;
		} else {
			root.left = insertUtil(root.left, val);
		}
		return root;
	}
}
