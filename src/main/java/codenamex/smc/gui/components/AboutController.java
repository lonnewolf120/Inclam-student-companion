package codenamex.smc.gui.components;

import codenamex.smc.sceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static codenamex.smc.Database.Const.MAIN_SCREEN;


public class AboutController {

    @FXML
    private Hyperlink githubLink;

    @FXML
    private Button okButton;

    @FXML
    private Text aboutText;

    @FXML
    void okButtonClicked(ActionEvent event) throws IOException {
        sceneController.switchControlsAction(MAIN_SCREEN,event);
    }

    @FXML
    void githubLinkOnAction(ActionEvent event) {

        String githubURL = "https://github.com/lonnewolf120";
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + githubURL);
            }
            Runtime.getRuntime().exec("xdg-open " + githubURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}