import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import java.net.*;

public class Exercise31_09Server extends Application {
  private TextArea taHistory = new TextArea();
  private TextArea taInput = new TextArea();
  
  ObjectInputStream fromClient = null;
  DataOutputStream toClient = null;
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taHistory.setWrapText(true);
    taInput.setWrapText(true);
    taHistory.setEditable(false);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taHistory));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taInput));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // To complete later
    new Thread( () -> {
      try {
        // Create a server socket
        ServerSocket serverSocket = new ServerSocket(8000);
  
        // Listen for a connection request
        Socket socket = serverSocket.accept();
  
        // Create data input and output streams
        fromClient = new ObjectInputStream(
          socket.getInputStream());
        toClient = new DataOutputStream(
          socket.getOutputStream());
  
        while (true) {
          String receivedText = (String)fromClient.readObject();
          Platform.runLater(() -> {
            taHistory.appendText("C: " + receivedText + '\n');
          });
        }
      }
      catch(IOException ex) {
        ex.printStackTrace();
      }
      catch(ClassNotFoundException ex) {
        ex.printStackTrace();
      }
    }).start();
    
    taInput.setOnKeyPressed(e -> {
      switch (e.getCode()) {
        case ENTER: 
        try {
          String outputText = taInput.getText().trim();
          taHistory.appendText("H: " + outputText + '\n');
          taInput.setText("");
          toClient.writeUTF(outputText);
        }
        catch (IOException ex) {
          ex.printStackTrace();
        };
      }
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
