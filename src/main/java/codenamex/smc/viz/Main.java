package codenamex.smc.viz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
//hi
    public static void main(String[] args) {
       /* BinaryTree b=new BinaryTree();
        b.insert(18);
        b.insert(1);
        b.insert(19);
        b.insert(10);
        b.insert(5);
        b.insert(15);
        b.print();*/
        launch();
    }
}