package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {
	private AVLTree avl; // avl object to hold the binary search tree
	@FXML
	private TextField rootVal; // TextField for getting text from user
	
	@FXML
	private Button insertBtn, searchBtn, deleteBtn, inorderBtn, preorderBtn, postorderBtn, levelorderBtn, clearBtn, dfsBtn; // Buttons to do operations
	
	@FXML
	private Pane pane;  // Pane to hold the binary search tree
	
	@FXML
	private AnchorPane myAnchorPane;

	/**
	 * Method for inserting value in Tree
	 */
	public void insertInAVL() {
		try {
			avl.insert(Integer.parseInt(rootVal.getText())); // Calling insert function in bst
			rootVal.clear();
		} catch(NumberFormatException nfe) {
			showAlert("Please input an integer number.", "Invalid Input", AlertType.ERROR);
		}
	}	
	
	/**
	 * Method for searching value in Tree
	 */
	public void searchInAVL() {
		try {
			avl.search(Integer.parseInt(rootVal.getText())); // Calling search function in binary search tree
			rootVal.clear();
		} catch(NumberFormatException nfe) {
			showAlert("Please input an integer number.", "Invalid Input", AlertType.ERROR);
		}
	}
	
	/**
	 * Method for deleting value in Tree
	 */
	public void deleteInAVL() {
		try {
			avl.remove(Integer.parseInt(rootVal.getText())); // Calling search function in binary search tree
			rootVal.clear();
		} catch(NumberFormatException nfe) {
			showAlert("Please input an integer number.", "Invalid Input", AlertType.ERROR);
		}
		
		
	}

	@Override
	/**
	 * For initializing tree
	 * @param arg0
	 * @param arg1
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		avl = new AVLTree(pane); // initializing empty AVL Tree
	}
	
	/**
	 * Method associated with in-order button
	 */
	public void inorder() {
		avl.inorder();
	}
	
	/**
	 * Method associated with pre-order button
	 */
	public void preorder() {
		avl.preorder();
	}
	
	/**
	 * Method associated with post-order button
	 */
	public void postorder() {
		avl.postorder();
	}

	/**
	 * Method associated with level-order button
	 */
	public void levelorder() {
		avl.levelorder();
	}
	
	/**
	 * Method associated with Depth First Search(DFS) button
	 */
	public void dfs() {
		avl.dfs();
	}

	/**
	 * Method for clearing pane 
	 */
	public void clearAVL() {
		pane.getChildren().clear(); // To clear all the circle and text
		avl.clear(); 			    // To empty the AVL Tree
	}

	/**
	 * Method to create and show alert
	 * @param msg   : Message to show on alert description
	 * @param title : Title of alert box
	 * @param type  : Type of alert box, like, ERROR, INFORMATION, WARNING
	 */
	public static void showAlert(String msg, String title, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(msg);
		alert.setHeaderText(null);
		alert.show();
	}
	
	
}
