package codenamex.smc;

//import codenamex.smc.Login;
//import codenamex.smc.RegisterUser;
//import io.github.palexdev.materialfx.controls.*;
//import codenamex.smc.DSViz.*;
import codenamex.smc.Database.Login;
import javafx.application.Application;
import codenamex.smc.tictactoe.*;
import javafx.event.ActionEvent;
import codenamex.smc.viz.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

import static codenamex.smc.Database.Const.*;

public class sceneController {
    private static Parent root;
    private static Stage stage;
    private static Scene scene;
    static double x=0.0;
    static double y=0.0;//for Stage's getX, getY
    public static void MoveAbleWindow()
    {
        x=(stage.getX());
        y=(stage.getY());
        root.setOnMousePressed(event -> {
            x=(event.getSceneX());
            y=(event.getSceneY());
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(.7);
        });
        root.setOnMouseReleased((MouseEvent e)->{
            stage.setOpacity(1);
        });
    }
    public static void switchControls(MouseEvent e, String view) throws IOException{
        root = FXMLLoader.load((Objects.requireNonNull(sceneController.class.getResource(view))));
        stage= (Stage) ((Node)e.getSource()).getScene().getWindow();
        MoveAbleWindow();   //Moveable window option
        scene = new Scene(root);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public static void switchControlsAction(String view, ActionEvent e) throws IOException{
//        URL resource = ((sceneController.class.getResource(view)));
//        if(resource==null){
//            System.err.println("Error: Resource not found - " + view);
//            return;
//        }
//        else
//        {
//            root = FXMLLoader.load(resource);
        root = FXMLLoader.load(Objects.requireNonNull(sceneController.class.getResource(view)));
            stage= (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.initStyle(StageStyle.DECORATED);
            scene = new Scene(root);
            MoveAbleWindow();   //Moveable window option
//        stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
//        }

    }
    public static void switchToSignup(MouseEvent e) throws IOException {
        switchControls(e,SIGNUP_PAGE);
    }
    @FXML
    public static void launchTicTacToe(ActionEvent e){
        new TicTacToeGui().setVisible(true);
    }
    public static void switchToSignupA(ActionEvent e) throws IOException {
       switchControlsAction(SIGNUP_PAGE, e);
    }
    public static void switchToLoginA(ActionEvent e) throws IOException {
        switchControlsAction(LOGIN_PAGE,e);
    }
    public static void switchToLogin(MouseEvent e) throws IOException {
        switchControls(e,LOGIN_PAGE);
    }
//    public static void switchToFP(ActionEvent e) throws IOException {
//        switchControlsAction(FP_PAGE,e);
//    }
    public static void switchToTasks(ActionEvent e) throws IOException {
        switchControlsAction(TASK_DASHBOARD,e);
    }
    public static void switchToNotes(ActionEvent e) throws IOException {
        switchControlsAction(NOTE_HOME,e);
    }
    public static void closeButton(ActionEvent e){
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

//    public static void switchToTutorial(ActionEvent actionEvent) throws IOException {
//        switchControlsAction(TUTORIAL_HOME,actionEvent);
//    }

    public void ForgotPasswordSubmit(ActionEvent actionEvent) {
    }

    public void swtichToNotes(ActionEvent a) throws IOException {
        switchControlsAction(NOTE_HOME,a);
    }

    public static void switchToEditor(ActionEvent a) throws IOException {
        switchControlsAction(EDITOR_MAIN,a);
    }

    public static void closeButton(MouseEvent e) {
        ((Stage) ((Node)e.getSource()).getScene().getWindow()).close();
    }
    public static void closeButtonA(ActionEvent e) {
        ((Stage) ((Node)e.getSource()).getScene().getWindow()).close();
    }
    public void switchToStack(MouseEvent e) throws IOException {
        switchControls(e, STACK_PAGE);
    }

    public void switchToQueue(MouseEvent e) throws IOException {
        switchControls(e,QUEUE_PAGE);
    }
    public static void switchToTutorial(ActionEvent e) throws IOException {
        switchControlsAction(TUTORIAL_HOME,e);
    }
    public void switchToEditor(MouseEvent e) throws IOException {
        switchControls(e,EDITOR_MAIN);
    }

     public void switchToBST() {
        try {
            // Create an instance of BSTAnimation
            BSTAnimation bstAnimation = new BSTAnimation();

            // Initialize the new stage
            Stage newStage = new Stage();

            // Set up the stage as needed
            // ...

            // Call a method in BSTAnimation to perform any additional setup
            bstAnimation.initialize(newStage);

            // Show the new stage
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void BackButton(ActionEvent e) throws IOException {
        switchControlsAction(TUTORIAL_HOME,e);
    }




//    public void launchTicTacToe(ActionEvent event) {
//        sceneController.launchTicTacToe(event);
//    }


//    public void closeButtonA(ActionEvent actionEvent) {
//    }


    ///TODO--------LOGIN SEGMENT (make separate java file)--------



    ////TODO-----REGISTER USER (make separate Java file)/--------



    }




