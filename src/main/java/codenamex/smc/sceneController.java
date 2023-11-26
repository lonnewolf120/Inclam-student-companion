package codenamex.smc;

//import codenamex.smc.Login;
//import codenamex.smc.RegisterUser;
//import io.github.palexdev.materialfx.controls.*;
import codenamex.smc.Database.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
    public static void switchToSignupA(ActionEvent e) throws IOException {
       switchControlsAction(SIGNUP_PAGE, e);
    }
    public static void switchToLoginA(ActionEvent e) throws IOException {
        switchControlsAction(LOGIN_PAGE,e);
    }
    public static void switchToLogin(MouseEvent e) throws IOException {
        switchControls(e,LOGIN_PAGE);
    }
    public static void switchToFP(MouseEvent e) throws IOException {
        switchControls(e,FP_PAGE);
    }
    public static void closeButton(ActionEvent e){
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void ForgotPasswordSubmit(ActionEvent actionEvent) {
    }




    ///TODO--------LOGIN SEGMENT (make separate java file)--------



    ////TODO-----REGISTER USER (make separate Java file)/--------



    }




