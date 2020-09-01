// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.util.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.*;

public class Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();
  private ObjectInputStream inputFromClient;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Thread( () -> {
		try {
			// Create a server socket
			ServerSocket serverSocket = new ServerSocket(8000);
			Platform.runLater(() ->
				ta.appendText("Server started at " + new Date() + '\n'));

			// Listen for a connection request
			Socket socket = serverSocket.accept();

			// Create data input and output streams
			DataInputStream inputFromClient = new DataInputStream(
				socket.getInputStream());
			ObjectOutputStream outputToClient = new ObjectOutputStream(
				socket.getOutputStream());

			while (true) {
				double interest = inputFromClient.readDouble();
				int years = inputFromClient.readInt();
				double amount = inputFromClient.readDouble();
				
				Platform.runLater(() ->
					ta.appendText("Connected to a client at " + new Date() + '\n'));
				
				String inputText = "Annual Interest Rate: " + interest + '\n' +
						"Number of Years: " + years + '\n' +
						"Loan Amount: " + amount + '\n';
				
				Platform.runLater(() ->
					ta.appendText(inputText));
				
				outputToClient.writeObject(inputText);
				
				Loan currentLoan = new Loan(interest, years, amount);
				
				String outputText = "Monthly Payment: " + currentLoan.getMonthlyPayment() + '\n' +
						"Total Payment: " + currentLoan.getTotalPayment() + '\n';
				
				Platform.runLater(() ->
					ta.appendText(outputText));
				
				outputToClient.writeObject(outputText);
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}).start();
    
  }    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
