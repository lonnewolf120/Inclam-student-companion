package codenamex.smc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import codenamex.smc.utilities.EditorUtils;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

import static codenamex.smc.Database.Const.*;
import static codenamex.smc.sceneController.MoveAbleWindow;

public class EditorMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage newStage;
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
        newStage = primaryStage;
        primaryStage.show();
        primaryStage.setOnCloseRequest(this::handleCloseRequest);
    }
    private void handleCloseRequest(WindowEvent event) {
        // Perform any cleanup or actions before closing

        // Switch back to TUTORIAL_HOME
        try {
//            root = ;
            newStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(sceneController.class.getResource(TASK_DASHBOARD))));
//            MoveAbleWindow();   //Moveable window option
//        stage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

}
