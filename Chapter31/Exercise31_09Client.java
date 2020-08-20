import javafx.application.Application;
import javafx.application.Platform;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;import java.io.*;
import java.net.*;
import java.util.*;


public class Exercise31_09Client extends Application {
  private TextArea taHistory = new TextArea();
  private TextArea taInput = new TextArea();
  
  ObjectOutputStream toServer = null;
  DataInputStream fromServer = null;
 
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
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    taInput.setOnKeyPressed(e -> {
      switch (e.getCode()) {
        case ENTER: 
        try {
          String outputText = taInput.getText().trim();
          taHistory.appendText("C: " + outputText + '\n');
          taInput.setText("");
          toServer.writeObject(outputText);
        }
        catch (IOException ex) {
          ex.printStackTrace();
        };
      }
    });
    
    new Thread(() -> {
       try {
          Socket socket = new Socket("localHost", 8000);
          toServer = new ObjectOutputStream(socket.getOutputStream());
          fromServer = new DataInputStream(socket.getInputStream());
          while(true) {
            String receivedText = fromServer.readUTF();
            Platform.runLater(() -> {
              taHistory.appendText("H: " + receivedText + '\n');
            });
          }
        }
        catch (IOException ex) {
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
