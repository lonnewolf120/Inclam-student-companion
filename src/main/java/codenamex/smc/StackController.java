package codenamex.smc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class StackController {

    @FXML
    private Button pushButton;
    // push
    @FXML
    private Button popButton;

    @FXML
    private Pane stackPane;
    @FXML
    private TextField textField;
    private final Stack<StackPane> stack = new Stack<>();
    private Stage stage;
    private Scene scene;
    public void initialize() {
        pushButton.setOnAction(event -> {
            try {
                pushSquare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        popButton.setOnAction(event -> {
            try {
                popSquare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            try {
//                popSquare();
//            } catch (StackUnderFlowException e) {
//                // Create a text file to record the error.
//                FileWriter writer = null;
//                try {
//                    writer = new FileWriter("stack_underflow.txt");
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//                try {
//                    writer.write(e.getMessage());
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//                try {
//                    writer.close();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//           }
        });
    }
//    public class StackUnderFlowException extends Exception {
//
//        public StackUnderFlowException(String message) {
//            super(message);
//        }
//    }
    private void pushSquare() throws IOException{
        if(stack.size()==10)
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Stack OverFlow");
            alert1.setContentText("Cannot push a Full Stack");
            alert1.showAndWait();
        }
        else {
            Rectangle rectangle = new Rectangle(30, 30);
            rectangle.setFill(Color.WHITE);
            //rectangle.setX(stackPane.getWidth());
            //rectangle.setY(stackPane.getHeight());
            int data = Integer.parseInt(textField.getText());
            String my_string = Integer.toString(data);
            Text text = new Text(my_string);
            StackPane stackpane = new StackPane();
            stackpane.getChildren().addAll(rectangle, text);
            stackpane.relocate(300,5000);
            stack.push(stackpane);
            //stackPane.getChildren().add(rectangle);

            visualizeStack();
        }
    }

    //    private void popSquare() throws IOException {
////        if (stack.isEmpty()) {
////            System.out.println("StackUnderFlow");
////            return;
////        }
//
//        StackPane stakePane = stack.pop();
//        stackPane.getChildren().remove(stakePane);
//
//        visualizeStack();
//    }
    private void popSquare() throws  IOException {
        if (stack.isEmpty()) {
            //throw new StackUnderFlowException("Stack is empty");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Stack Underflow");
            alert.setContentText("Cannot pop from an empty stack");
            alert.showAndWait();

        }
        else
        {StackPane stakePane = stack.pop();
        stackPane.getChildren().remove(stakePane);

        visualizeStack();}
    }
    private void visualizeStack() {
        stackPane.getChildren().clear();

        for (int i = 0; i < stack.size(); i++) {
            StackPane stk = stack.get(i);

            stk.setLayoutY(stackPane.getHeight() / 2 - i * 22);

            stackPane.getChildren().add(stk);
        }
    }

    public void closeButtonA(ActionEvent actionEvent) {
        sceneController.closeButtonA(actionEvent);
    }

    public void switchToTutorial(ActionEvent actionEvent) throws IOException {
        sceneController.switchToTutorial(actionEvent);
    }
}
