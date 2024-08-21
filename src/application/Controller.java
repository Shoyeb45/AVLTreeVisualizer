package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {
	private BinarySearchTree bst; // bst object to hold the binary search tree
	@FXML
	private TextField rootVal; // TextField for getting text from user
	
	@FXML
	private Button insertBtn, searchBtn, deleteBtn, inorderBtn, preorderBtn, postorderBtn, levelorderBtn; // Buttons to do operations
	
	@FXML
	private Pane pane;  // Pane to hold the binary search tree
	public void insertInBST() {
		try {
			bst.insert(Integer.parseInt(rootVal.getText())); // Calling insert function in bst
			rootVal.clear();
		} catch(NumberFormatException nfe) {
			showAlert("Please input an integer number.", "Invalid Input", AlertType.ERROR);
		}
	}	
	
	public void searchInBST() {
		try {
			bst.search(Integer.parseInt(rootVal.getText())); // Calling search function in binary search tree
			rootVal.clear();
		} catch(NumberFormatException nfe) {
			showAlert("Please input an integer number.", "Invalid Input", AlertType.ERROR);
		}
	}
	
	public void deleteInBST() {
		try {
			bst.remove(Integer.parseInt(rootVal.getText())); // Calling search function in binary search tree
			rootVal.clear();
		} catch(NumberFormatException nfe) {
			showAlert("Please input an integer number.", "Invalid Input", AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bst = new BinarySearchTree(pane); // initializing empty bst
	}
	
	/*
	 * Method associated with inorder button
	 * */
	public void inorder() {
		bst.inorder();
	}
	
	/*
	 * Method associated with preorder button
	 * */
	public void preorder() {
		bst.preorder();
	}
	
	/*
	 * Method associated with postorder button
	 * */
	public void postorder() {
		bst.postorder();
	}
	
	/*
	 * Method associated with levelorder button
	 * */
	public void levelorder() {
		bst.levelorder();
	}
	
	public void clearBST() {
		pane.getChildren().clear(); // To clear all the circle and text
		bst.clear(); // To empty the bst
	}
	
	/*
	 * Method to create and show alert
	 * */
	public static void showAlert(String msg, String title, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(msg);
		alert.setHeaderText(null);
		alert.show();
	}
	
	
}
