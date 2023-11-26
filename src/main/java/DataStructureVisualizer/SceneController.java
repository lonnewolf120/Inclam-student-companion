package com.example.DataStructureVisualizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void SwitchToStack(ActionEvent eventStack) throws IOException {
            root= FXMLLoader.load(getClass().getResource("Stack.fxml"));
            stage= (Stage) ((Node)eventStack.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    public void SwitchToQueue(ActionEvent eventQueue) throws IOException {
        root= FXMLLoader.load(getClass().getResource("Queue.fxml"));
        stage= (Stage) ((Node)eventQueue.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
