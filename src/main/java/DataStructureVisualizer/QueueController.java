package com.example.DataStructureVisualizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;

public class QueueController {

    @FXML
    private Button enqueueButton;

    @FXML
    private Button dequeueButton;

    @FXML
    private Pane stackPane;
    @FXML
    private TextField textField;
    private final Queue<StackPane> queue = new LinkedList<>();
    private Stage stage;
    private Scene scene;

    public void initialize() {
        enqueueButton.setOnAction(event -> enqueueSquare());
        dequeueButton.setOnAction(event -> dequeueSquare());
    }

    public void enqueueSquare() {
        Rectangle rectangle = new Rectangle(30, 30);
        rectangle.setFill(Color.WHITE);
        //rectangle.setX(stackPane.getWidth());
        //rectangle.setY(stackPane.getHeight());
        int data = Integer.parseInt(textField.getText());
        String my_string = Integer.toString(data);
        Text text = new Text(my_string);
        StackPane stackpane = new StackPane();
        stackpane.getChildren().addAll(rectangle, text);
        queue.add(stackpane);
        //stackPane.getChildren().add(rectangle);

        visualizeQueue();
    }

    public void dequeueSquare() {
        if (queue.isEmpty()) {
            System.out.println("StackUnderFlow");
            return;
        }

        StackPane stakePane = queue.poll();
        stackPane.getChildren().remove(stakePane);

        visualizeQueue();
    }
    public void printQueueInGUI() {
        stackPane.getChildren().clear();
        while(!queue.isEmpty())
        {

        }
    }
    private void visualizeQueue() {
        stackPane.getChildren().clear();
        int i=1;
        while(!queue.isEmpty())
        {
            StackPane stk=queue.element();
            stk.setLayoutY(10);
            stk.setLayoutX(stackPane.getHeight() / 2 - i * 22);
            stackPane.getChildren().add(stk);
            i++;
        }
    }
}
