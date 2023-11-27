package codenamex.smc;
import codenamex.smc.ViewStyles;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

import static codenamex.smc.Database.Const.LOGIN_PAGE;

public class HelloApplication extends Application {

//    private double x=0.0, y=0.0;
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource(LOGIN_PAGE))));

//        stage.initStyle(StageStyle.TRANSPARENT);
        ViewStyles.MoveAbleWindow(stage,root);   //Moveable window option
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Logos/notes2.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}