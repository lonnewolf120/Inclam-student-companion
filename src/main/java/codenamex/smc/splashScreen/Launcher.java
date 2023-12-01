package codenamex.smc.splashScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static Stage primaryStage = null;
    public static Scene primaryScene = null;

    @Override
    public void init() {
        InitPreloader init = new InitPreloader();
        init.checkFunctions();
    }

    @Override
    public void start(Stage primaryStage) {
        Launcher.primaryStage = primaryStage;
    }

    public static void main(String[] args) {
        System.setProperty("javafx.preloader", LauncherPreloader.class.getCanonicalName());
    }

}
