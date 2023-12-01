package codenamex.smc.viz;

import codenamex.smc.sceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

import static codenamex.smc.Const.BACK_BUTTON;
import static codenamex.smc.Const.TUTORIAL_HOME;
import static codenamex.smc.sceneController.MoveAbleWindow;
//import static codenamex.smc.sceneController.root;

public class BSTAnimation extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private static Stage bstStage;
    @Override///
    public void start(Stage primaryStage) throws Exception {
        BST<Integer> tree = new BST<Integer>(); // Create a tree
        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree); // Create a View
        pane.setCenter(view);
        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
//        BorderPane bp = new BorderPane();
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);
        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) { // key is in the tree already
                view.displayTree();
                view.setStatus(key + " is already in the tree");
            } else {
                tree.insert(key); // Insert a new key
                view.displayTree();
                view.setStatus(key + " is inserted in the tree");
            }
        });
        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) { // key is not in the tree
                view.displayTree();
                view.setStatus(key + " is not in the tree");
            } else {
                tree.delete(key); // Delete a key
                view.displayTree();
                view.setStatus(key + " is deleted from the tree");
            }
        });
        Button btn = new Button();
        ImageView imgV = new ImageView();
        Image img = new Image(getClass().getResourceAsStream(BACK_BUTTON));
        imgV.setImage(img);
        imgV.setFitHeight(25);
        imgV.setFitWidth(25);
        btn.setGraphic(imgV);
        pane.setTop(btn);
        btn.setOnAction(e -> {
            try {
                sceneController.BackButton(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
// Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 450, 250);
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setOnCloseRequest(this::handleCloseRequest);
        bstStage = primaryStage;
        primaryStage.show(); // Display the stage


    }
    public static Stage getPrimaryStage()
    {
        return bstStage;
    }
    // Handle close request
    private void handleCloseRequest(WindowEvent event) {
        // Perform any cleanup or actions before closing

        // Switch back to TUTORIAL_HOME
        try {
//            root = ;
            bstStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(sceneController.class.getResource(TUTORIAL_HOME))));
            MoveAbleWindow();   //Moveable window option
//        stage.initStyle(StageStyle.UNDECORATED);
            bstStage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
            bstStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    @Override
    public void stop() throws Exception {
        // This method is called when the application is about to exit
        // Perform any cleanup or actions before closing

        super.stop();
    }

    public void initialize(Stage newStage) {
    }
}