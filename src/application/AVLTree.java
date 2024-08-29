package application;

import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AVLTree {
	
	/**
	 * Constant Values
	 */
	public final static int START_Y = 50; // Initial coordinate of AVL tree node in y-axis 
    public final static int DELTA_X = 50; // Horizontal spacing
    public final static int DELTA_Y = 50; // Vertical spacing between levels
	public final static double K = 13;    // Distance of chord from center, to calculate the start of edges
	public final static double DIST = Math.sqrt(Math.pow(AVLNode.RADIUS, 2) - Math.pow(K, 2));  // Distance of line from the edge point starting to perpendicular bisector
	
	
	/**
	 * Attributes of AVL Tree Class
	 */
	public double startX;
	public AVLNode root; 
	public AVLNode lastNode;
	public Pane pane;

	/**
	 * Constructor sets root to null and initializes pane
	 * @param newPane : pane for holding Tree
	 */
	AVLTree(Pane newPane) {
		pane = newPane;
		root = null;
	}
	
	/**
	 * Method to insert the value in AVL tree
	 * @param val : value that needs to be inserted in Tree
	 * @return none
	 */
	public void insert(int val) {
		
		// When AVL tree is empty
		if(root == null) { 
			this.startX = pane.getWidth() / 2; // At middle of pane
			AVLNode newNode = new AVLNode(val, startX,  START_Y); // insert at the center of the pane
			root = newNode;
			pane.getChildren().addAll(newNode.circle, newNode.text); // Add node to pane
			root.addLineToPane(pane); // Add both the left and right lines to pane
			lastNode = root;
			return; 
		}

		if(!this.contains(this.root, val)) {
			root = insertUtil(this.root, val, root.circle.getCenterX(), root.circle.getCenterY(), false); // Helper method to insert the new value
			resizeTree(); // For visually balancing the AVL Tree
		} else {
			Controller.showAlert(val + " is already present is AVL tree.", "Duplicate value found", AlertType.INFORMATION);
		}
	}
	
	/**
	 * Method for searching given value
	 * @param val : Value that needs to be searched in Tree
	 * @return none
	 */
	public void search(int val) {
		Duration[] delay = {Duration.seconds(1)};
		Timeline timeline = new Timeline();

		// value is found
		if(this.utilSearch(root, val, timeline, delay)) {
			timeline.setOnFinished(event -> {
				Controller.showAlert(val + " found in AVL tree", "Value found", AlertType.CONFIRMATION);
			});
			timeline.play();
		} 
		// value not found
		else {
			timeline.setOnFinished(event -> {
				Controller.showAlert(val + " not found in AVL tree", "Value not found", AlertType.INFORMATION);
			});
			timeline.play();
		}
	}
	
	

	/**
	 * Method for deleting a particular node
	 * @param val : Value to be removed
	 * @return none
	 */
	public void remove(int val) {

		// If the Tree is empty
		if(root == null) {
			Controller.showAlert("Cannot delete from an empty AVL tree", "Empty AVL", AlertType.WARNING);
			return;
		} else {
			root = this.utilRemove(this.root, val, null);
			PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
			pauseTransition.play();
			pauseTransition.setOnFinished(event -> {
				resizeTree();
			});
		}
	}
	
	/**
	 * Method for in-order traversal
	*/
	public void inorder() {

		// If tree is empty
		if(root == null) {
			Controller.showAlert("Can't perform inorder traversal because AVL tree is empty", "AVL is empty", AlertType.INFORMATION);
			return;
		}
		
		// ArrayList for storing the values
		ArrayList<Integer> trav = new ArrayList<>();

		Duration[] delay = {Duration.seconds(1)};
		Timeline timeline = new Timeline();

		// Calling utility method for actual traversal and animation
		this.utilInorder(root, timeline, delay, trav);
		timeline.play(); // Play the animation

		// Once animation is finished show traversal series
		timeline.setOnFinished(event -> {
			this.showTraversalResult(this.makeLabel(trav), "Inorder Traversal: ");
		});
		
	}
	
	
	
	/**
	 * Method for pre-order traversal
	 */
	public void preorder() {

		// If tree is empty
		if(root == null) {
			Controller.showAlert("Can't perform preorder traversal because AVL tree is empty", "AVL is empty", AlertType.INFORMATION);
			return;
		}

		// ArrayList for storing the values
		ArrayList<Integer> trav = new ArrayList<>();
		Duration[] delay = {Duration.seconds(1)};
		Timeline timeline = new Timeline();

		// Calling utility method for actual traversal and animation
		this.utilPreorder(root, timeline, delay, trav);
		timeline.play(); // Play the animation

		// Once animation is finished show traversal series
		timeline.setOnFinished(event -> {
			this.showTraversalResult(this.makeLabel(trav), "Preorder Traversal: ");
		});
		
	}
	
	/**
	 * Method for post-order traversal
	 */
	public void postorder() {

		// If tree is empty
		if(root == null) {
			Controller.showAlert("Can't perform postorder traversal because AVL tree is empty", "AVL is empty", AlertType.INFORMATION);
			return;
		}

		// ArrayList for storing the values
		ArrayList<Integer> trav = new ArrayList<>();
		
		Duration[] delay = {Duration.seconds(1)};
		Timeline timeline = new Timeline();

		// Calling utility method for actual traversal and animation
		this.utilPostorder(root, timeline, delay, trav);
		timeline.play(); // Play the animation

		// Once animation is finished show traversal series
		timeline.setOnFinished(event -> {
			this.showTraversalResult(this.makeLabel(trav), "Postorder Traversal: ");
		});
	}
	
	/**
	 * Method for level-order traversal
	 * @param label : label for showing the traversal 
	 */
	public void levelorder() {

		// If tree is empty
		if(root == null) {
			Controller.showAlert("Can't perform postorder traversal because AVL tree is empty", "AVL is empty", AlertType.INFORMATION);
			return;
		}

		// ArrayList for storing the values
		ArrayList<ArrayList<Integer>> trav = new ArrayList<>();

		Duration[] delay = {Duration.seconds(1)};
		Timeline timeline = new Timeline();

		// Calling utility method for actual traversal and animation
		this.utilLevelorder(root, timeline, delay, trav);
		
		StringBuilder str = new StringBuilder("");
		for(ArrayList<Integer> it: trav) {

			for(int i = 0; i < it.size(); i++) {
				if(i == 0) {
					str.append("[ ").append(it.get(i)).append(" ");
				}
				else if(i == it.size() - 1) {
					str.append(it.get(i)).append(" ]");
				} 
				else {
					str.append(it.get(i) + " ");
				}
			}
			if(it.size() == 1) {
				str.append("]");
			}
			str.append(" ");
		}

		
		// Once animation is finished show traversal series
		timeline.setOnFinished(event -> {
			this.showTraversalResult(str.toString(), "Level Order:");
		});
		timeline.play(); // Play the animation
	}
	
	/**
	 * Method for level-order traversal
	 * @param label : label for showing the traversal 
	 */
	public void dfs() {
		if(root == null) {
			Controller.showAlert("Can't perform dfs traversal because AVL tree is empty", "AVL is empty", AlertType.INFORMATION);
			return;
		}
		// ArrayList for storing the values
		ArrayList<Integer> trav = new ArrayList<>();
				
		Duration[] delay = {Duration.seconds(1)};
		Timeline timeline = new Timeline();

		// Calling utility method for actual traversal and animation
		this.utilDfs(root, timeline, delay, trav);
		timeline.play(); // Play the animation

		// Once animation is finished show traversal series
		timeline.setOnFinished(event -> {
			this.showTraversalResult(this.makeLabel(trav), "DFS Traversal: ");
		});		
	}
	
	/**
	 * Method for clearing AVL tree
	 * */
	public void clear() {
		root = null;
	}

	/**
	 * Private method for checking if the node is present in AVL Tree or not
	 * @param root,val 
	 */
	private boolean contains(AVLNode root, int val) {
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
	 /**
	  * Utility Method for inserting new node in given position
	  * @param root    : Root node of Tree
	  * @param val     : Value to be inserted
	  * @param centerX : x-coordinate of center of circle of current node
	  * @param centerY : y-coordinate of center of circle of current node
	  * @param isLeft  : boolean value to determine the left or right subtree of node to be inserted  
	  * @return AVLNode
	  */
	private AVLNode insertUtil(AVLNode root, int val, double centerX, double centerY, boolean isLeft) {
		
		if(root == null) {
			double newCenterX, newCenterY = centerY + DELTA_Y;  // Center of new node
			AVLNode newNode;
			
			if(isLeft) {
				newCenterX = centerX - DELTA_X; // Insert the node at left side of parent node
			} else {
				newCenterX = centerX + DELTA_X; // Insert the node at right side of parent node
			}
			
			newNode = new AVLNode(val, newCenterX, newCenterY);      // Creating new node
			pane.getChildren().addAll(newNode.circle, newNode.text); // Add to the pane
			nodeAppearance(newNode);								 // Call function to show the node appearance animation
			newNode.addLineToPane(pane);							 // Add line to the pane
			lastNode = newNode;										
			
			return newNode;
		}
		
		if(root.value < val) {
			// visit right Subtree
			root.right = insertUtil(root.right, val, root.circle.getCenterX(), root.circle.getCenterY(), false);  
		} 
		else {
			// Visit left subtree
			root.left = insertUtil(root.left, val, root.circle.getCenterX(), root.circle.getCenterY(), true); 
		}
		
		this.updateNode(root);
		
		return this.balanceTree(root);
	}
	
	/**
	 * Method for updating height and balancing factor of AVL tree
	 */
	private void updateNode(AVLNode node) {
		int leftHeight = -1, rightHeight = -1;
		if(node.left != null) {
			leftHeight = node.left.height;
		}
		
		if(node.right != null) {
			rightHeight = node.right.height;
		}
		
		node.height = 1 + Math.max(leftHeight, rightHeight);
		node.balancingFactor = rightHeight - leftHeight;
	}
	
	
	/**
	 * Method for balancing the AVL tree
	 */
	private AVLNode balanceTree(AVLNode node) {
		
		// Left subtree has more nodes
		if(node.balancingFactor == -2) {
			// Left-Left Case
			if(node.left.balancingFactor < 0) {
				return this.leftLeftCase(node);
			} 
			// Left-Right case
			else {
				return this.leftRightCase(node);
			}
		}
		
		// Right subtree has more nodes
		else if(node.balancingFactor == 2) {
			// Right-Right Case
			if(node.right.balancingFactor > 0) {
				return this.rightRightCase(node);
			} 
			// rightLeftCase			
			else {
				return this.rightLeftCase(node);
			}
		}
		return node;
	}
	
	/**
	 * Method to handle left-left un-balancing case
	 */ 
	private AVLNode leftLeftCase(AVLNode node) {
		return rightRotate(node);
	}
	
	/**
	 * Method to handle left-right un-balancing case
	 */
	private AVLNode leftRightCase(AVLNode node) {
		node.left = this.leftRotate(node.left);
		return leftLeftCase(node);
	}
	
	/**
	 * Method to handle right-right un-balancing case
	 */
	private AVLNode rightRightCase(AVLNode node) {
		return leftRotate(node);
	}
	
	/**
	 * Method to handle right-left un-balancing case
	 */
	private AVLNode rightLeftCase(AVLNode node) {
		node.right = this.rightRotate(node.right);
		return rightRightCase(node);
	}
	
	/**
	 * Method to perform right rotation
	 */
	private AVLNode rightRotate(AVLNode node) {
		AVLNode child = node.left;
		node.left = child.right;
		child.right = node;
		
		// Update the re-positioned nodes
		this.updateNode(node);
		this.updateNode(child);
		
		return child;
	}
	
	/**
	 * Method to perform left rotation
	 */
	private AVLNode leftRotate(AVLNode node) {
		AVLNode child = node.right;
		node.right = child.left;
		child.left = node;
		
		// Update the re-positioned nodes
		this.updateNode(node);
		this.updateNode(child);
				
		return child;
	}
	/**
	 * Method for animation of node appearance at insertion
	 * @param newNode : node to show the appearance animation
	 */
	private void nodeAppearance(AVLNode newNode) {
		ScaleTransition nodeAppearance = new ScaleTransition(Duration.seconds(0.5), newNode.circle);
        nodeAppearance.setFromX(0.1);
        nodeAppearance.setToX(1);
        nodeAppearance.setToY(1);
        nodeAppearance.play();
	}
	
	/**
	 * Method for animation of line growing
	 * @param line : The line which needs to be shown as growing
	 * @param endX : x-point of end point of line
	 * @param endY : y-point of end point of line
	 */
	private void lineGrowing(Line line, double endX, double endY) {
		Timeline timeline = new Timeline();

	    KeyFrame keyFrame = new KeyFrame(
	            Duration.seconds(1), // Animation duration
	            new KeyValue(line.endXProperty(), endX),
	            new KeyValue(line.endYProperty(), endY)
	    );
	    
	    timeline.getKeyFrames().add(keyFrame);
	    timeline.play();
	}
	
	/**
	 * Method for re-adjusting the centers of nodes after newly inserted node
	 */	 
	private void resizeTree() {
		
		double startingPoint = this.startX;  // Center of the pane
		this.resizeWidths(root);  // Update the left and right width of every node
		
		if(root != null) {

			// If left side has more nodes, then update the starting point to leftWidth 
			if(this.root.leftWidth > startingPoint) {
				startingPoint = this.root.leftWidth;
			}
			// If right side has more nodes 
			else if(this.root.rightWidth > startingPoint) {
				startingPoint = Math.max(this.root.leftWidth, 2 * startingPoint - this.root.rightWidth);
			}
			
			// Assign new positions
			root.updatePositions(startingPoint, START_Y);
			
			// First set to the left subtree
			this.setNewPositions(root.left, startingPoint, START_Y + DELTA_Y, (byte)-1, startingPoint - DIST, START_Y + K, root.leftEdge);		
			// Then set new positions to right subtree		
			this.setNewPositions(root.right, startingPoint, START_Y + DELTA_Y, (byte)1, startingPoint + DIST, START_Y + K, root.rightEdge);
		}
	}

	/**
	 * Method for updating the centers of circle with new positions 
	 * @param node		 : Current node whose position has to be updated
	 * @param xPosition  : New x-coordinate of center of circle of node
	 * @param yPosition  : New y-coordinate of center of circle of node
	 * @param side		 : To determine which side we are currently
	 * @param lineStartX : Starting x-coordinate of line
	 * @param lineStartY : Starting y-coordinate of line
	 * @param line		 : Edge of parent to child
	 */
	private void setNewPositions(AVLNode node, double xPosition, double yPosition, byte side, double lineStartX, double lineStartY, Line line) {
	    if (node == null) {
	        return;
	    }
	    
	    if (side == -1) {
	        // Position the node based on the left width
	        xPosition = xPosition - node.rightWidth;
	    } else if (side == 1) {
	        // Position the node based on the right width
	        xPosition = xPosition + node.leftWidth;
	    }
	    
	    // Update the center of circle based on new positions
	    node.updatePositions(xPosition, yPosition);
	    
	    // make lines visible
	    AVLNode.showLine(line);
	    
	    // Show animation for growing line from start -> end, only for newly inserted node    
	    if(node == lastNode) {
	    	lineGrowing(line, xPosition, yPosition - AVLNode.RADIUS);	    	
	    } else {
	    	line.setEndX(xPosition);
	    	line.setEndY(yPosition - AVLNode.RADIUS);
	    }
	    

	    // Recursively set positions for left and right children
	    setNewPositions(node.left, xPosition, yPosition + DELTA_Y, (byte)-1, xPosition - DIST, yPosition + K, node.leftEdge);
	    setNewPositions(node.right, xPosition, yPosition + DELTA_Y, (byte)1, xPosition + DIST, yPosition + K, node.rightEdge);
	}
	

	/**
	 * Method to calculate the {@code leftWidth} and {@code rightWidth} of all the nodes 
	 * @param node 
	 * @return the left and right width of current node
	 */
	private double resizeWidths(AVLNode node) {
		if (node == null) {
			return 0;
		}
		
		// Calculate widths for left and right subtrees
		node.leftWidth = Math.max(resizeWidths(node.left), DELTA_X / 2);
		node.rightWidth = Math.max(resizeWidths(node.right), DELTA_X / 2);
		
		// Return the combined width
		return node.leftWidth + node.rightWidth;
	}


	/**
	 * Utitlity method to search for a value in a tree.
	 * @param root 		: Root node of tree 
	 * @param val	    : Value to be saerched in Tree
	 * @param timeline  : To show the animation of glow
	 * @param delay		: To make delay after each node
	 * @return {@code true} if the value is present else {@code false}
	 */
	private boolean utilSearch(AVLNode root, int val, Timeline timeline, Duration[] delay) {
		if(root == null) {
			// return false - value is not present
			return false;
		}
		
		if(val == root.value) {
			// Value found
			highlightNode(root, delay[0], timeline, Color.GREEN);
			delay[0] = delay[0].add(Duration.seconds(2));
			return true;
		} else if(val > root.value) {
			// If the value is greater than current node value, then check right subtree 
			highlightNode(root, delay[0], timeline, Color.RED);
			delay[0] = delay[0].add(Duration.seconds(2));
			return utilSearch(root.right, val, timeline, delay);
		} else {
			// If the value is lesser than current node value, then check left subtree 
			highlightNode(root, delay[0], timeline, Color.RED);
			delay[0] = delay[0].add(Duration.seconds(2));
			return utilSearch(root.left, val, timeline, delay);
		}
	}
	
	/**
	 * Utility method for deleting a node from Tree
	 * @param node : Root node of Tree
	 * @param val  : Value which needs to be deleted from tree
	 * @param line : Line which needs to be removed after deleting a node
	 * @return
	 */
	private AVLNode utilRemove(AVLNode node, int val, Line line) {
		if(node == null) {
			Controller.showAlert(val + " is not present in AVL.", "Value not present", AlertType.INFORMATION);
			return node;
		}
		
		if(val > node.value) {
		// If the value is greater than current node value, then check right subtree 
			node.right = utilRemove(node.right, val, node.rightEdge);
		} else if(val < node.value) {
		// If the value is lesser than current node value, then check left subtree 
			node.left = utilRemove(node.left, val, node.leftEdge);
		} else {
		// If the current node value is same as value - node found

			// When node to be removed is leaf node
			if(node.right == null && node.left == null) {
				this.nodeDisappearance(node); // Play animation of node disappearance
				if(line != null) {
					line.setStrokeWidth(0);	  // Make line invisible 
				}
				return null;
			}

			// When node to be removed has one child node - only at left
			else if(node.right == null) {
				this.nodeDisappearance(node);	// Play animation of node disappearance
				if(line != null) {
					line.setStrokeWidth(0);	    // Make line invisible				
				}
				node.leftEdge.setStrokeWidth(0);
				return node.left;
			}
			// When node to be removed has one child node - only at right
			else if(node.left == null) {
				
				this.nodeDisappearance(node);	// Play animation of node disappearance
				if(line != null) {
					line.setStrokeWidth(0);		// Make line invisible			
				}
				node.rightEdge.setStrokeWidth(0);
				return node.right;
			}
			// When node to be removed has two child nodes
			else {
				AVLNode inorderPredecessor = node.left;
				
				Duration delay = Duration.seconds(1);
				Timeline timeline = new Timeline();
				
				// Look for largest value in left subtree of current node
				while(inorderPredecessor.right != null) {
					highlightNode(inorderPredecessor, delay, timeline, Color.GREEN);
					inorderPredecessor = inorderPredecessor.right;
					
					delay = delay.add(Duration.seconds(2));
				}
				highlightNode(inorderPredecessor, delay, timeline, Color.RED); 
				delay = delay.add(Duration.seconds(2));
				
				// Change the value of node which has to be deleted by it's inorder predecessor
				node.value = inorderPredecessor.value;

				// When animation get finished
				timeline.setOnFinished(event -> {

					// Animation for fading of text
					FadeTransition ft = new FadeTransition(Duration.seconds(1), node.text);
					ft.setFromValue(1.0);
					ft.setToValue(0.0);
					
					ft.setOnFinished(event2 -> {
						node.text.setText(String.valueOf(node.value));
						node.text.setOpacity(1);

						// Animation for text appearing 
						ScaleTransition textAppearance = new ScaleTransition(Duration.seconds(1), node.text);
						textAppearance.setFromX(0.0);
						textAppearance.setToX(1);
						textAppearance.setToY(1);
						
						textAppearance.play();
						
					});
					ft.play(); 
					
					PauseTransition pt = new PauseTransition(Duration.seconds(1));
					
					// After 1 second of pause
					pt.setOnFinished(event2 -> {
						node.left = utilRemove(node.left, node.value, node.leftEdge);	
						resizeTree();
					});
					pt.play();
				});
				timeline.play();
				
				this.updateNode(node);
			}
		}
		
		this.updateNode(node);
		return this.balanceTree(node);
	}
	

	 /**
	  * Method for showing disappearance of node
	  * @param node : The node which has to be show disappeared
	  */
	private void nodeDisappearance(AVLNode node) {
		ScaleTransition nodeDisappear = new ScaleTransition(Duration.seconds(1), node.circle);
        nodeDisappear.setFromX(1);
        nodeDisappear.setFromX(1);
        nodeDisappear.setToX(0);
        nodeDisappear.setToY(0);
        
        FadeTransition fadeEffect = new FadeTransition(Duration.seconds(1), node.circle);
        fadeEffect.setFromValue(1.0);
        fadeEffect.setToValue(0.0);
        
        nodeDisappear.play();
        
        ParallelTransition parallelTransition = new ParallelTransition(nodeDisappear, fadeEffect);
        parallelTransition.setOnFinished(event -> {
        	pane.getChildren().remove(node.circle);
        });
        
        pane.getChildren().remove(node.text);
        parallelTransition.play();
	}
	

	 /**
	  * Utility method for in-order traversal of AVL tree
	  * @param root		 : Root node of tree
	  * @param timeline  : To show the animation of glow
	  * @param delay	 : To make delay after each node
	  * @param trav      : ArrayList for storing node value
	  */
	private void utilInorder(AVLNode root, Timeline timeline, Duration[] delay, ArrayList<Integer> trav) {
		if(root == null) {
			return;
		}
		
		utilInorder(root.left, timeline, delay, trav); // Visit left subtree first
		
		trav.add(root.value);
		highlightNode(root, delay[0], timeline, Color.RED);
		delay[0] = delay[0].add(Duration.seconds(2));
		
		utilInorder(root.right, timeline, delay, trav); // Then visit right subtree
	}
	
	/**
	  * Utility method for pre-order traversal of AVL tree
	  * @param root		 : Root node of tree
	  * @param timeline  : To show the animation of glow
	  * @param delay	 : To make delay after each node
	  * @param trav      : ArrayList for storing node value
	  */
	private void utilPreorder(AVLNode root, Timeline timeline, Duration[] delay, ArrayList<Integer> trav) {
		if(root == null) {
			return;
		}
		
		trav.add(root.value);
		highlightNode(root, delay[0], timeline, Color.RED);
		delay[0] = delay[0].add(Duration.seconds(2));

		utilPreorder(root.left, timeline, delay, trav);		// Visit left subtree first
		utilPreorder(root.right, timeline, delay, trav);	// Then visit right subtree first
	}
	
	/**
	  * Utility method for Post-order traversal of AVL tree
	  * @param root		 : Root node of tree
	  * @param timeline  : To show the animation of glow
	  * @param delay	 : To make delay after each node
	  * @param trav      : ArrayList for storing node value
	  */
	private void utilPostorder(AVLNode root, Timeline timeline, Duration[] delay, ArrayList<Integer> trav) {
		if(root == null) {
			return;
		}
		utilPostorder(root.left, timeline, delay, trav);   // Visit left subtree first
		utilPostorder(root.right, timeline, delay, trav);  // Then visit right subtree first
		
		trav.add(root.value);
		highlightNode(root, delay[0], timeline, Color.RED);
		delay[0] = delay[0].add(Duration.seconds(2));
	}
	

	/**
	  * Utility method for level-order traversal of AVL tree
	  * @param root		 : Root node of tree
	  * @param timeline  : To show the animation of glow
	  * @param delay	 : To make delay after each node
	  * @param trav      : ArrayList for storing node value
	  */
	private void utilLevelorder(AVLNode root, Timeline timeline, Duration[] delay, ArrayList<ArrayList<Integer>> trav) {
		// Using BFS for level-order traversal
		Queue<AVLNode> q = new LinkedList<>();
		
		// Process root node
		q.add(root);

		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(root.value);
		trav.add(temp);

		highlightNode(root, delay[0], timeline, Color.RED);
		delay[0] = delay[0].add(Duration.seconds(2));

		// Perform BFS
		while(!q.isEmpty()) {
			
			int k = q.size();
			temp = new ArrayList<Integer>();
			
			for(int i = 1; i <= k; i++) {
				AVLNode node = q.poll();
				if(node.left != null) {
					temp.add(node.left.value);
					q.add(node.left);

					highlightNode(node.left, delay[0], timeline, Color.RED);
					delay[0] = delay[0].add(Duration.seconds(2));
				}
				
				if(node.right != null) {
					temp.add(node.right.value);
					q.add(node.right);
					
					highlightNode(node.right, delay[0], timeline, Color.RED);
					delay[0] = delay[0].add(Duration.seconds(2));
				}
			}
			trav.add(temp);
		}
	}
	
	/**
	  * Utility method for level-order traversal of AVL tree
	  * @param root		 : Root node of tree
	  * @param timeline  : To show the animation of glow
	  * @param delay	 : To make delay after each node
	  * @param trav      : ArrayList for storing node value
	  */
	private void utilDfs(AVLNode root, Timeline timeline, Duration[] delay, ArrayList<Integer> trav) {
		trav.add(root.value);
		this.highlightNode(root, delay[0], timeline, Color.RED);
		delay[0] = delay[0].add(Duration.seconds(2));
		
		if(root.left != null) {
			utilDfs(root.left, timeline, delay, trav);
		}
		
		if(root.right != null) {
			utilDfs(root.right, timeline, delay, trav);
		}
		this.highlightNode(root, delay[0], timeline, Color.RED);
		delay[0] = delay[0].add(Duration.seconds(2));

	}
	
	 /**
	  * Method to show current node highlighted 
	  * @param root     : Node which need to be highlighted
	  * @param delay	: To make delay after each node
	  * @param timeline : To show the animation of glow
	  * @param color    : Color of border effect
	  */
	private void highlightNode(AVLNode root, Duration delay, Timeline timeline, Color color) {
		KeyFrame glowFrame = new KeyFrame(
				delay,
				event -> applyGlowEffect(root, color)
			);
			
	KeyFrame resetFrame = new KeyFrame(
				delay.add(Duration.seconds(1)), 
				event -> resetNodeEffect(root)
			);
			
	timeline.getKeyFrames().addAll(glowFrame, resetFrame);
	}
	
	
	/**
	 * Method for making label for traversals
	 * @param trav : Traversal series
	 * @param head : It's heading like, inorder, preorder, postorder..
	 * @return
	 */
	private String makeLabel(ArrayList<Integer> trav) {
		StringBuilder ans = new StringBuilder("");
		
		for(int x : trav) {
			ans.append(x).append(" ");
		}
		return String.valueOf(ans);
	}

	/**
	 * Method for showing glow effect
	 * @param node  : Node which needs to be show as glow effect on Stroke 
	 * @param color : Color of glow
	 */
	private void applyGlowEffect(AVLNode node, Color color) {
        node.circle.setStroke(color);
        Glow glow = new Glow(1.0);
        node.circle.setEffect(glow);
    }
	
	/**
	 * For resetting the style of node
	 * @param root
	 */
	private void resetNodeEffect(AVLNode root) {
		root.circle.setStroke(Color.BLACK);
		root.circle.setEffect(null);
	}
	/**
	 * Method to show the new window of traversal series
	 * @param 
	 */
	private void showTraversalResult(String label, String travTitle) {
        Stage resultStage = new Stage();
        resultStage.setTitle("Traversal Result");
        
        Label title = new Label(travTitle);
        AnchorPane layout = new AnchorPane();
        Scene scene = new Scene(layout, 400, 200);
        title.setLayoutX(115);
        title.setLayoutY(10);
      
        title.setFont(Font.font("Alice", FontWeight.BOLD, 20));
        
        Label resultLabel = new Label(label);
        resultLabel.setLayoutX(12);
        resultLabel.setLayoutY(50);
        resultLabel.setFont(Font.font("Alice", FontWeight.NORMAL, 16));
        resultLabel.setWrapText(true);
        layout.getChildren().addAll(title, resultLabel);

        resultStage.setResizable(false);
        resultStage.setScene(scene);
        resultStage.show();
    }

}	