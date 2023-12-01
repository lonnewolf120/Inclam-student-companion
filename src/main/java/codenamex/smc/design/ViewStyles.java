package codenamex.smc.design;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewStyles {

    private static double xOffset = 0;
    private static double yOffset = 0;

    public static void MoveAbleWindow(Stage stage, Parent root) {
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
            stage.setOpacity(0.8);
        });

        root.setOnMouseReleased(event -> {
            stage.setOpacity(1.0);
        });
    }

    public static void MinimizeWindow(Stage stage) {
        stage.setIconified(true);
    }
    public static void Resizeable(Stage stage)
    {
        stage.setResizable(true);
//        stage.?
    }
    public static void MaximizeRestoreWindow(Stage stage) {
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }

    public static void CloseWindow() {
        System.exit(0);
    }
}
