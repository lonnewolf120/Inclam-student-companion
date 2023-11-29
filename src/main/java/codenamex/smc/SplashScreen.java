package codenamex.smc;

import javafx.event.ActionEvent;

import java.io.IOException;

import static codenamex.smc.Database.Const.ABOUT_PAGE;

public class SplashScreen {
    public void enterApp(ActionEvent event) throws IOException {
        sceneController.switchToLoginA(event);
    }

    public void showMembers(ActionEvent event) throws IOException {
        sceneController.switchControls(ABOUT_PAGE,event);
    }

    public void closeApp(ActionEvent event) {
        sceneController.closeButton(event);
    }
}
