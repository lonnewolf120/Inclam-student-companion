package codenamex.smc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import codenamex.smc.utilities.EditorUtils;

import static codenamex.smc.Database.Const.*;

public class EditorMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Font.loadFont(this.getClass().getResourceAsStream(ROBOTO_FONT), 16);
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(EDITOR_MAIN));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource(EDITOR_STYLE).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(640);
        primaryStage.setTitle("untitled");
        primaryStage.setOnCloseRequest(event -> {
            EditorUtils.onCloseExitConfirmation();
            event.consume();
        });
        primaryStage.show();
    }


}
