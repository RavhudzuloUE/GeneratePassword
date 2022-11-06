import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sub.PasswordGeneratorPane;

public class Main extends Application {

	public void start(Stage primaryStage) throws Exception {
		 
		PasswordGeneratorPane pane1 = new PasswordGeneratorPane();   /// Layout PasswordGeneratorPane instances
		
		Scene sc = new Scene(pane1, 550, 500);          // Creating the scene from the layout
		
		primaryStage.setTitle("PASSWORD GENERATOR");    // Title of the stage 
		primaryStage.setScene(sc);                      // Setting the Scene in the Stage
		primaryStage.show();                            // Showing the Scene to the User

	}

	public static void main(String[] args) {
		
		launch();  // Launch the Application

	}

}
