package sub;

import java.util.Random;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class PasswordGeneratorPane extends StackPane {

	private FlowPane   flow;
	private GridPane   grid;
	private HBox       hBox;
	private VBox       vBox;
	
	private Button userToEnableButton;
	private Button saveButton;
	
	private Label titleLabel;
	private Label messageInfoLabel;
	private Label notificationLabel;
	private Label nameLabel;
	private Label passLabel;

	private TextField nameField;
	private TextField passField;
	
	private ImageView backgroundIMG;
	
	public PasswordGeneratorPane() {
		
		setUpTheGUI(); // set up the Graphical User Interface method
		
		// Put the list of characters you want your password to be composed
		String[] alphaNumericPotentialPassword = {"a", "b", "c", "d","e", "f", "G", "H", "I", "J", "K", "L", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
				                                  "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "!", "\"", "£", "$", "%", "^", "&", "*", "=", "+", 
				                                  "-", "?", "A", "B", "C", "D","E", "F", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		
		Random random = new Random(); 

		/*
		 *  When clicking mouse on the TextField for password
		 *  password will be generated with 14 characters
		 */
		passField.setOnMouseClicked(e -> {
			
			if(grid.getChildren().contains(notificationLabel)) {
				
				grid.getChildren().remove(notificationLabel);
				
			}
			
			if(!hBox.getChildren().contains(userToEnableButton)) {
				
				hBox.getChildren().add(userToEnableButton);
				
				if(!grid.getChildren().contains(hBox)) {
					
					grid.add(hBox, 1, 2);
					
				}
			}
			
		});
		
		// Click the button to generate random number
		userToEnableButton.setOnAction(e -> {
			
			passField.setText("");
			
			String passwordStr = "";
			for(int i = 0; i < 14; i++) {
				
				int index = random.nextInt(55);   // A number chosen will be between 0 and 55
				passwordStr += alphaNumericPotentialPassword[index]; // Collection of characters(up to 14th) and get stored in the single String
				
			}
			
			String passwordGenerated = passwordStr;
			
			passField.appendText(passwordGenerated);  // Display on the password TextField
			
			hBox.getChildren().clear();       // Remove the Generate Password Button that popped up
			grid.getChildren().remove(hBox);
			
			if(grid.getChildren().contains(messageInfoLabel)) {
				
				grid.getChildren().remove(messageInfoLabel);
				
			}
			
		});
		
		// Each time a key is typed on the Password TextField
		passField.setOnKeyTyped(e -> {
			
			if(grid.getChildren().contains(notificationLabel)) {
				
				grid.getChildren().remove(notificationLabel);
				
			}
			
			// Remove the generate password Button if found when checking its container HBox
			if(grid.getChildren().contains(hBox)) {
				
				grid.getChildren().remove(hBox); 
				
			} 
			
			if(!grid.getChildren().contains(messageInfoLabel)) {
				grid.add(messageInfoLabel, 3, 1);
			}
			
			// Check if the user typed 14 characters or more
			if(passField.getText().length() > 13) {
				
				grid.getChildren().remove(messageInfoLabel); // Remove the message if true
				
			}
		});
		
		// Listen if the DONE Button is clicked
		saveButton.setOnAction(e -> {
			
			String username = nameField.getText();  // Store the entered USERNAME
			String password = passField.getText();  // Store the entered Password
			
			// Check if the User did not fill the TextFields available
			if(username.isBlank() || password.isBlank()) {
				
				grid.add(notificationLabel, 3, 1); // notify the user if found true
				
			}else {
				
				// Check if the passwords exceed 13 characters
				if(password.length() > 13) {
					
					setUpTheGUIAfterSavingInfo(); // Invoke if found to be true
					
				}
				
			}
			
			
			
		});
		
		}
	
	/**
	 * Set up the Graphical User Interface 
	 */
	private void setUpTheGUI() {
		
		flow = new FlowPane(Orientation.VERTICAL);    flow.setAlignment(Pos.CENTER);  flow.setVgap(30);
		grid = new GridPane();                        grid.setAlignment(Pos.CENTER);  grid.setVgap(10);
		hBox = new HBox();
		vBox = new VBox();                            
		
		userToEnableButton = new Button("Generate Password"); // Offers to generate password for user
		saveButton         = new Button("DONE");
		
		titleLabel        = new Label("SIGN UP TO YOUR STUDENT ACCOUNT");       titleLabel.setFont(Font.font("Serief", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 28)); titleLabel.setTextFill(Color.CHARTREUSE);    
		messageInfoLabel  = new Label("Password should be more than 13");       messageInfoLabel.setTextFill(Color.RED);
		notificationLabel = new Label("Fill in the username and/ or password"); notificationLabel.setTextFill(Color.RED);
		nameLabel         = new Label("USERNAME: ");       nameLabel.setFont(Font.font("San Serief", FontWeight.BLACK, FontPosture.REGULAR, 18));     nameLabel.setTextFill(Color.BLACK);
		passLabel         = new Label("PASSWORD: ");       passLabel.setFont(Font.font("San Serief", FontWeight.BLACK, FontPosture.REGULAR, 18));     passLabel.setTextFill(Color.AQUA);
		
		nameField = new TextField();
		passField = new TextField();
		
		backgroundIMG = new ImageView(new Image("file:data/Books.jpg")); // Set the Image
		
		BackgroundImage bcgroundImage = new BackgroundImage(backgroundIMG.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		
		Background background = new Background(bcgroundImage); 
		
		vBox.getChildren().add(titleLabel);
		
		grid.add(nameLabel, 0, 0);
		grid.add(nameField, 1, 0);
		grid.add(passLabel, 0, 1);
		grid.add(passField, 1, 1);
		grid.add(saveButton, 1, 4);
		
		flow.setBackground(background);
		flow.getChildren().add(vBox);
		flow.getChildren().add(grid);
		
		getChildren().add(flow);
		
	}
	
	/**
	 * Method that set the user interface  after Done Button is clicked
	 */
	private void setUpTheGUIAfterSavingInfo() {
		
		flow.getChildren().removeAll(vBox, grid);
		
		titleLabel.setText("YOUR STUDENT ACCOUNT IS SET");  
		messageInfoLabel.setText("CONGRATULATIONS");         messageInfoLabel.setTextFill(Color.GREEN); messageInfoLabel.setFont(Font.font(28));
		
		flow.getChildren().add(titleLabel);
		flow.getChildren().add(messageInfoLabel);
		
	}

}
