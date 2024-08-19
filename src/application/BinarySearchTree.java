package application;

import javafx.util.Duration;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BinarySearchTree {
	
	private static final int START_Y = 50;
    private static final int DELTA_X = 40; // Increased to avoid overlap
    private static final int DELTA_Y = 45; // Increased to ensure proper spacing
	private static final double K = 10; // Distance of chord from center, to calculate the start of edges
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
			BSTNode newNode = new BSTNode(val, pane.getWidth() / 2,  START_Y);
			root = newNode;
			pane.getChildren().addAll(newNode.circle, newNode.text);
			return;
		}
		if(!this.contains(this.root, val)) {
			this.insertUtil(this.root, val, root.circle.getCenterX(), root.circle.getCenterY(), false);
		}
	}
	
	
	/*
	 * Method for in-order traversal
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
		
		Duration[] delay = {Duration.seconds(1)};
		Timeline timeline = new Timeline();
		this.utilInorder(root, timeline, delay);
		timeline.play();
	}
	
	/*
	 * Method for clearing binary search tree
	 * */
	public void clear() {
		root = null;
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
			double newCenterX, newCenterY = centerY + DELTA_Y; 
			
			BSTNode newNode;
			double dist = Math.sqrt(Math.pow(BSTNode.RADIUS, 2) - Math.pow(K, 2));
			
			
			double lineStartX = 0, lineStartY = centerY + K;
			
			if(isLeft) {
				newCenterX = centerX - DELTA_X;
				newNode = new BSTNode(val, newCenterX, newCenterY);
				lineStartX = centerX - dist;
			} else {
				newCenterX = centerX + DELTA_X;
				newNode = new BSTNode(val, newCenterX, newCenterY);
				lineStartX = centerX + dist;
			}
			
			double lineEndX = newCenterX, lineEndY = newCenterY - 20;
			
			Line line = new Line(lineStartX, lineStartY, lineStartX, lineStartY);	
			pane.getChildren().addAll(newNode.circle, newNode.text, line);
			
			nodeAnimationOfInsertion(newNode, line, lineEndX, lineEndY);
			return newNode;
		}
		
		if(root.value < val) {
			root.right = insertUtil(root.right, val, root.circle.getCenterX(), root.circle.getCenterY(), false);
		} else {
			root.left = insertUtil(root.left, val, root.circle.getCenterX(), root.circle.getCenterY(), true);
		}
		return root;
	}
	
	/*
	 * Method for node animation at insertion
	 * */
	private void nodeAnimationOfInsertion(BSTNode newNode, Line line , double endX, double endY) {
		ScaleTransition nodeAppearance = new ScaleTransition(Duration.seconds(0.5), newNode.circle);
        nodeAppearance.setFromX(0.1);
        nodeAppearance.setFromX(0.1);
        nodeAppearance.setToX(1);
        nodeAppearance.setToY(1);
        
        Timeline timeline = new Timeline();

	    KeyFrame keyFrame = new KeyFrame(
	            Duration.seconds(1), // Animation duration
	            new KeyValue(line.endXProperty(), endX),
	            new KeyValue(line.endYProperty(), endY)
	    );
	    
	    timeline.getKeyFrames().add(keyFrame);
	    SequentialTransition sq = new SequentialTransition();
	    sq.getChildren().addAll(nodeAppearance, timeline);
	    sq.play();
	}
	
	
	
	
	/*
	 * Utility method for in-order traversal of binary search tree
	 * */
	private void utilInorder(BSTNode root, Timeline timeline, Duration[] delay) {
		if(root == null) {
			return;
		}
		
		utilInorder(root.left, timeline, delay);
		
		KeyFrame glowFrame = new KeyFrame(
					delay[0],
					event -> applyGlowEffect(root)
				);
				
		KeyFrame resetFrame = new KeyFrame(
					delay[0].add(Duration.seconds(1)), 
					event -> resetNodeEffect(root)
				);
				
		timeline.getKeyFrames().addAll(glowFrame, resetFrame);
		delay[0] = delay[0].add(Duration.seconds(2));
		
		utilInorder(root.right, timeline, delay);
	}
	
	/*
	 * for glow effect
	 * */
	private void applyGlowEffect(BSTNode node) {
        node.circle.setStroke(Color.RED);
        Glow glow = new Glow(1.0);
        node.circle.setEffect(glow);
    }
	
	/*
	 * For resetting the style of node
	 * */
	private void resetNodeEffect(BSTNode root) {
		root.circle.setStroke(Color.BLACK);
		root.circle.setEffect(null);
	}
}	

