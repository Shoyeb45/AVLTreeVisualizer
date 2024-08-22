package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml")); // Loading FXML File
			Parent root = loader.load();
			Scene scene = new Scene(root); // Adding it to scene
			stage.setScene(scene);   // Set scene on stage
			stage.show();  // Show scene
		} catch(Exception e) {
			e.printStackTrace(); // Handle exceptions
		}
	}
	
	public static void main(String[] args) {
		launch(args); // launch through main method
	}
}
