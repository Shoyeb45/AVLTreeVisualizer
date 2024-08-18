package application;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
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
	
	/*
	 * Method to insert the value in binary search tree
	 * */
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
	 * Method for inorder traversal
	 * */
	public void inorder() {
		if(root == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("BST is empty");
			alert.setContentText("Can't perform inorder traversal because binary search tree is empty");
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
		
		this.utilInorder(root);
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
			double newCenterX, newCenterY; 
			
			BSTNode newNode;
			double dist = Math.sqrt(Math.pow(20, 2) - Math.pow(10, 2));
			
			double lineStartX = 0, lineStartY = centerY + 10;
			if(isLeft) {
				newCenterX = centerX - 40;
				newCenterY = centerY + 45;
				newNode = new BSTNode(val, newCenterX, newCenterY);
				lineStartX = centerX - dist;
			} else {
				newCenterX = centerX + 40;
				newCenterY = centerY + 45;
				newNode = new BSTNode(val, newCenterX, newCenterY);
				lineStartX = centerX + dist;
			}
			
			double lineEndX = newCenterX, lineEndY = newCenterY - 20;
			
			Line line = new Line(lineStartX, lineStartY, lineStartX, lineStartY);
			
			pane.getChildren().addAll(newNode.circle, newNode.text, line);
			
			ScaleTransition nodeAppearance = new ScaleTransition(Duration.seconds(0.5), newNode.circle);
            nodeAppearance.setFromX(0.1);
            nodeAppearance.setFromX(0.1);
            nodeAppearance.setToX(1);
            nodeAppearance.setToY(1);
            
			Timeline timeline = new Timeline();

		    KeyFrame keyFrame = new KeyFrame(
		            Duration.seconds(1), // Animation duration
		            new KeyValue(line.endXProperty(), lineEndX),
		            new KeyValue(line.endYProperty(), lineEndY)
		    );

		    timeline.getKeyFrames().add(keyFrame);
		    SequentialTransition sq = new SequentialTransition();
		    sq.getChildren().addAll(nodeAppearance, timeline);
		    sq.play();
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
	
	/*
	 * Utility method for inorder traversal of binary search tree
	 * */
	private void utilInorder(BSTNode root) {
		if(root == null) {
			return;
		}
		
		utilInorder();
	}
}
