package codenamex.smc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create UI elements
        TextField textField = new TextField();
        Label label = new Label("Result will appear here");
        Button submitButton = new Button("Submit");
        String inputFilePath = "C:\\Users\\Dell\\OneDrive\\Desktop\\input.txt";
        String outputFilePath = "C:\\Users\\Dell\\OneDrive\\Desktop\\output.txt";

        // Set up event handling
        submitButton.setOnAction(event -> {
            // Get the text from the TextField
            String inputText = textField.getText();

            // Update the Label with the input text
            label.setText("You entered: " + inputText);

            // Write the input text to a file
            writeToFile(inputText, outputFilePath);
            readFile(outputFilePath);
        });

        // Create layout
        VBox root = new VBox(10); // 10 is the spacing between elements
        root.getChildren().addAll(textField, submitButton, label);

        // Create scene and set it on the stage
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);

        // Set stage properties
        primaryStage.setTitle("TextField to Label and File Example");
        primaryStage.show();
    }

    // Method to write text to a file
    private void writeToFile(String text, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            // true parameter appends to the file instead of overwriting it
            writer.write(text + System.lineSeparator());
            System.out.println("Text written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void readFile(String input)
    {

        try(    BufferedReader br = new BufferedReader(new FileReader(input)) )
        {
            String line;
            while((line = br.readLine())!=null)
            {
//                BufferedWriter bw = new BufferedWriter(new FileWriter(input,true));
//                bw.write(line);
                    System.out.println("read: "+line);
//                writeToFile(output);
             }
        }
        catch (IOException e)
        {

        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
