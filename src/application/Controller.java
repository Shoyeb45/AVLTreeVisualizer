package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {
	private BinarySearchTree bst;
	@FXML
	private TextField rootVal;
	
	@FXML
	private Button insertBtn, searchBtn, deleteBtn;
	
	@FXML
	private Pane pane;
	public void insertInBST() {
		System.out.println("Insert: " + rootVal.getText());
		bst.insert(Integer.parseInt(rootVal.getText()));
	}	
	
	public void searchInBST() {
		System.out.println("Search: " + rootVal.getText());
	}
	
	public void deleteInBST() {
		System.out.println("Delete: " + rootVal.getText());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bst = new BinarySearchTree(pane);
	}
}
