package codenamex.smc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static codenamex.smc.Database.Const.NOTE_HOME;

/**
 *
 * @author iftee
 */
public class NotesApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(NOTE_HOME));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Logos/notes2.png")));
            stage.setTitle("Note");
            stage.show();
        }
        catch (fxmlException e)
        {
            throw new fxmlException("Error loading "+ NOTE_HOME+"\n");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
