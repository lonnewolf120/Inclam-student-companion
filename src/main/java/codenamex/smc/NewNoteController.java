/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codenamex.smc;

//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;

        import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
import javafx.scene.Node;
        import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UpToDate
 */
public class NewNoteController implements Initializable {

    @FXML
    private TextArea text;

    private double xOffset = 0;
    private double yOffset = 0;

    private String hexaColor = "#FFF2AB";
    @FXML
    private AnchorPane AP;
    @FXML
    private Button closeBTN;
    @FXML
    private Button addBTN;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("NewNoteController -> initialize()  ...");
    }

    @FXML
    public void RootMousePressed(Event event) {
        MouseEvent e = (MouseEvent) event;
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }

    @FXML
    public void RootMouseDragged(Event event) {
        MouseEvent e = (MouseEvent) event;
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).setX(e.getScreenX() - xOffset);
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).setY(e.getScreenY() - yOffset);
    }

    @FXML
    private void closeAction(Event event) {
        System.out.println("NewNoteController -> closeAction()  ...");
        ((Stage) (text.getScene().getWindow())).close();
    }

    @FXML
    private void AddAction(Event event) {
        try {
            System.out.println("NewNoteController -> AddAction()  ...");
            HomeController.insert(text.getText(), hexaColor);
            closeAction(event);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void green(ActionEvent event) {
        hexaColor = "#90EE90";
        AP.setStyle("-fx-background-color : " + hexaColor + ";");
        addBTN.setStyle("-fx-background-color: #006400");
        closeBTN.setStyle("-fx-background-color: #006400");
    }

    @FXML
    private void blue(ActionEvent event) {
        hexaColor = "#ADD8E6";
        AP.setStyle("-fx-background-color : " + hexaColor);
        addBTN.setStyle("-fx-background-color: #4682B4");
        closeBTN.setStyle("-fx-background-color: #99ccff");
    }

    @FXML
    private void yellow(ActionEvent event) {
        hexaColor = "#FFD700";
        AP.setStyle("-fx-background-color : " + hexaColor + ";");
        addBTN.setStyle("-fx-background-color: #B8860B" + ";");
        closeBTN.setStyle("-fx-background-color: #B8860B" + ";");
    }

    @FXML
    private void gray(ActionEvent event) {
        hexaColor = "#FFA07A";
        AP.setStyle("-fx-background-color : " + hexaColor);
        addBTN.setStyle("-fx-background-color: #8B0000");
        closeBTN.setStyle("-fx-background-color: #DC143C");
    }

}
