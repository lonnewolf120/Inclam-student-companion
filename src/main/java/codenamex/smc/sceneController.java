package codenamex.smc;

//import codenamex.smc.Login;
//import codenamex.smc.RegisterUser;
//import io.github.palexdev.materialfx.controls.*;
//import codenamex.smc.DSViz.*;
import codenamex.smc.tictactoe.*;
import javafx.event.ActionEvent;
import codenamex.smc.viz.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static codenamex.smc.Const.*;

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
    public static void switchControls(String view, ActionEvent e) throws IOException{
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
//            stage.initStyle(StageStyle.DECORATED);
            scene = new Scene(root);
            MoveAbleWindow();   //Moveable window option
//        stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
//        }

    }
    public static <rootPane> void floatingPane(rootPane pane1,String fxmlPath)
    {
        FXMLLoader loader1 = new FXMLLoader(sceneController.class.getResource(fxmlPath));
				try {
					pane1 = loader1.load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
//				AddAdminController controller = loader1.getController();
				Parent root = (Parent) pane1;
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
    }
    public static void switchToSignup(MouseEvent e) throws IOException {
        switchControls(e,SIGNUP_PAGE);
    }
    @FXML
    public void launchTicTacToe(ActionEvent e){
        new TicTacToeGui().setVisible(true);
    }
    public static void switchToSignupA(ActionEvent e) throws IOException {
       switchControls(SIGNUP_PAGE, e);
    }
    public static void switchToLoginA(ActionEvent e) throws IOException {
        switchControls(LOGIN_PAGE,e);
    }
    public static void switchToLogin(MouseEvent e) throws IOException {
        switchControls(e,LOGIN_PAGE);
    }
//    public static void switchToFP(ActionEvent e) throws IOException {
//        switchControlsAction(FP_PAGE,e);
//    }
    public static void switchToTasks(ActionEvent e) throws IOException {
        switchControls(TASK_DASHBOARD,e);
    }
    public static void switchToNotes(ActionEvent e) throws IOException {
        switchControls(NOTE_HOME,e);
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
        switchControls(NOTE_HOME,a);
    }

    public static void switchToEditor(ActionEvent a) throws IOException {
        VBox vb = null;
        floatingPane(vb,EDITOR_MAIN);
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
        switchControls(TUTORIAL_HOME,e);
    }
    public void switchToEditor(MouseEvent e) throws IOException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(EDITOR_MAIN));
				VBox pane1 = null;
				try {
					pane1 = loader1.load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
//				AddAdminController controller = loader1.getController();

				Parent root = (Parent) pane1;
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
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
        switchControls(TUTORIAL_HOME,e);
    }

    public static void switchToViz(ActionEvent event) throws IOException {
        sceneController.switchControls(WEB_DASHBOARD,event);
    }

    public static void switchToUser(ActionEvent event) throws IOException {
        sceneController.switchControls(USER_DASHBOARD,event);
//        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(USER_DASHBOARD));
//				BorderPane pane1 = null;
//				try {
//					pane1 = loader1.load();
//                    Parent root = (Parent) pane1;
//				Stage stage = new Stage();
//				stage.setScene(new Scene(root));
//				stage.show();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				AddAdminController controller = loader1.getController();


    }

    public void switchToTask(ActionEvent event) throws IOException {
        sceneController.switchToTasks(event);
    }

    public void switchToUserA(ActionEvent event) throws IOException {
        switchToUser(event);
    }

    public void switchToVizA(ActionEvent event) throws IOException {
        switchToViz(event);
    }


//    public void launchTicTacToe(ActionEvent event) {
//        sceneController.launchTicTacToe(event);
//    }


//    public void closeButtonA(ActionEvent actionEvent) {
//    }


    ///TODO--------LOGIN SEGMENT (make separate java file)--------



    ////TODO-----REGISTER USER (make separate Java file)/--------



    }




