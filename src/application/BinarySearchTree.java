package application;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BinarySearchTree {
	private BSTNode root;
	private Pane pane;
	
	BinarySearchTree(Pane newPane) {
		pane = newPane;
		root = null;
	}
	public void insert(int val) {
		if(root == null) {
			BSTNode newNode = new BSTNode(val, pane.getWidth() / 2,  50);
			root = newNode;
			pane.getChildren().addAll(newNode.circle, newNode.text);
			return;
		}
		if(!this.contains(this.root, val)) {
			this.insertUtil(this.root, val, root.circle.getCenterX(), root.circle.getCenterY(), false);
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
	private BSTNode insertUtil(BSTNode root, int val, double centerX, double centerY, boolean isLeft) {
		if(root == null) {
			double newCenterX; 
			double newCenterY;
			BSTNode newNode;
			Line line = new Line();
//			line.setStrokeWidth(3);
			double dist = Math.sqrt(Math.pow(20, 2) - Math.pow(10, 2));
			
			if(isLeft) {
				newCenterX = centerX - 40;
				newCenterY = centerY + 45;
				newNode = new BSTNode(val, newCenterX, newCenterY);
				line.setStartX(centerX - dist);
				line.setStartY(centerY + 10); 
				double newX = (centerX - dist) - 15 * Math.cos(Math.toRadians(45));
				double newY = (centerY + 6) + 15 * Math.sin(Math.toRadians(45));
//				line.setEndX(newCenterX);
//				line.setEndY(newCenterY - 20); 
			} else {
				newCenterX = centerX + 40;
				newCenterY = centerY + 45;
				newNode = new BSTNode(val, newCenterX, newCenterY);
				line.setStartX(centerX + dist);
				line.setStartY(centerY + 10); 
				
				double newX = (centerX + dist) - 100 * Math.cos(Math.toRadians(45));
				double newY = (centerY + 6) + 100 * Math.sin(Math.toRadians(45));
//				line.setEndX(newX);
//				line.setEndY(newY);
			}
			line.setEndX(newCenterX);
			line.setEndY(newCenterY - 20); 
	        pane.getChildren().addAll(newNode.circle, newNode.text, line);
			return newNode;
		}
		
		if(root.value < val) {
			root.right = insertUtil(root.right, val, root.circle.getCenterX(), root.circle.getCenterY(), false);
			return root;
		} else {
			root.left = insertUtil(root.left, val, root.circle.getCenterX(), root.circle.getCenterY(), true);
		}
		return root;
	}
}
