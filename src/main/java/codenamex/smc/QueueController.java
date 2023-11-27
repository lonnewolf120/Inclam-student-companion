package codenamex.smc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import codenamex.smc.sceneController;

public class QueueController {

    @FXML
    private Button enqueueButton;

    @FXML
    private Button dequeueButton;
    @FXML
    private Pane stackPane;
    @FXML
    private TextField textField;
    public StackPane [] q;
    private int front;
    private int rear;
    private int size;
    private final int MAX_SIZE=15;
    public QueueController()
    {
        q=new StackPane[MAX_SIZE];
        size=0;
        front=0;
        rear=0;
    }
    public void initialize() {
        enqueueButton.setOnAction(event -> {
            try {
                enqueueSquare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        dequeueButton.setOnAction(event -> {
            try {
                dequeueSquare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void enqueueSquare() throws IOException {
        if(size==MAX_SIZE)
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Queue reached MAX_SIZE");
            alert1.setContentText("Cannot Enqueue a Full Queue");
            alert1.showAndWait();
        }
        else {
            Rectangle rectangle = new Rectangle(30, 30);
            rectangle.setFill(Color.WHITE);
            int data = Integer.parseInt(textField.getText());
            String my_string = Integer.toString(data);
            Text text = new Text(my_string);
            StackPane stackpane = new StackPane();
            stackpane.getChildren().addAll(rectangle, text);
            q[rear] = stackpane;
            rear++;
            size++;
            visualizeQueue();
        }
    }

    public void dequeueSquare() throws IOException{
        if(size==0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Queue is Empty");
            alert.setContentText("Cannot dequeue from an Empty Queue");
            alert.showAndWait();
        }
        StackPane dqStackPane=q[front];
        front++;
        size--;
        stackPane.getChildren().remove(dqStackPane);
        visualizeQueue();

    }
    public void visualizeQueue() {
            stackPane.getChildren().clear();
            int positionCounter=0;
            for(int i=front;i<rear;i++)
            {
                StackPane viewStackPane=q[i];
                viewStackPane.setLayoutX(2+positionCounter*22);
                positionCounter++;
                stackPane.getChildren().add(viewStackPane);
            }
        }

    public void backToDash(ActionEvent a) throws IOException {
        sceneController.BackButton(a);
    }
}
