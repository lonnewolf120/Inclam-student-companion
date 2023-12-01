package codenamex.smc.dashboards;

import codenamex.smc.sceneController;
import codenamex.smc.tictactoe.TicTacToeGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import codenamex.smc.design.CustomAlert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static codenamex.smc.Const.*;

public class webviewDash implements Initializable {


    @FXML
    private AnchorPane topbar;

    @FXML
    private WebView webView;

    @FXML
    private BorderPane window;
    private WebEngine webEngine;

    @Override
public void initialize(URL url, ResourceBundle resourceBundle) {
    webEngine = webView.getEngine();
    loadPage();
}

    private void loadPage() {
        webEngine.load(WEBPAGE);
    }

    @FXML
    void closeButton(MouseEvent event) {
        sceneController.closeButton(event);
    }

    @FXML
    void gameOn(ActionEvent event) {
        new TicTacToeGui().setVisible(true);
    }

    @FXML
    void switchToEditor(ActionEvent event) throws IOException {
        sceneController.switchToEditor(event);
        CustomAlert.showInfoAlert("User other switches", "If you can't access Editor from here\nSwitch to Task/Notes/User tab\nAnd select Editor from there",CANCEL_BUTTON);
    }

    @FXML
    void switchToTutorial(ActionEvent event) throws IOException {
        sceneController.switchToTutorial(event);
    }

    @FXML
    void swtichToNotes(ActionEvent event) throws IOException {
        sceneController.switchToNotes(event);
    }


    public void switchToUser(ActionEvent event) throws IOException {
       sceneController.switchToUser(event);
    }

    public void switchToViz(ActionEvent event) throws IOException {
        sceneController.switchToViz(event);
    }

    public void refreshButton(ActionEvent event) {
        webEngine.reload();
    }
    public void homeButton(ActionEvent e)
    {
        loadPage();
    }
    public void AIbutton(ActionEvent e)
    {
        webEngine.load("https://bard.google.com/");
    }
}
