package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	private TextField rootVal;
	
	@FXML
	private Button insertBtn, searchBtn, deleteBtn;
	
	public void insertInBST() {
		System.out.println("Insert: " + rootVal.getText());
	}
	
	public void searchInBST() {
		System.out.println("Search: " + rootVal.getText());
	}
	
	public void deleteInBST() {
		System.out.println("Delete: " + rootVal.getText());
	}
}
