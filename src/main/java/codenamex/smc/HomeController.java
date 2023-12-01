/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codenamex.smc;

import codenamex.smc.Database.DatabaseManager;
//import com.jfoenix.controls.JFXDialog;
//import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import codenamex.smc.notes.NoteController;
import codenamex.smc.notes.NoteFX;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static codenamex.smc.Database.Const.*;

/**
 * @author UpToDate
 * Here is a breakdown of the code:
 *
 * The HomeController class is responsible for handling the user interface of the application.
 * The InitializeNotes() method initializes the list of notes and displays them in the FlowPane.
 * The createNotesTable() method creates the notes table in the database if it does not already exist.
 * The insert() method inserts a new note into the database.
 * The select() method selects notes from the database.
 * The refresh() method refreshes the list of notes.
 */
public class HomeController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    public static Dialog aboutDialog;
    private Boolean isVisible=false;

    @FXML
    public FlowPane FP;

    static Connection con = null;
    @FXML
    private TextField searchComBox;
    @FXML
    private StackPane AP;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu file;
    @FXML
    private MenuItem About;
    @FXML
    private MenuItem close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("HomeController -> initialize() ...");
        InitializeNotes();
        try {
            AnchorPane aboutPane = FXMLLoader.load(getClass().getResource(ABOUT_PAGE));
            aboutDialog = new Dialog();
            isVisible=!isVisible;
        } catch (IOException ex) {
            System.out.println("error in loading about fxml");
        }

    }

    @FXML
    public void RootMousePressed(Event event) {
        MouseEvent e = (MouseEvent) event;
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }

    @FXML
    public void RootMouseDragged(Event event) {
        MouseEvent e = (MouseEvent) event;
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).setX(e.getScreenX() - xOffset);
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).setY(e.getScreenY() - yOffset);
    }

    @FXML
    private void closeWindow(Event event) {
        System.exit(0);
    }

    @FXML
    private void AddNoteAction(ActionEvent event) {
        System.out.println("HomeController -> AddNoteAction() ...");
        try {
            Parent newNoteFXML = FXMLLoader.load(getClass().getResource(NEW_NOTE));
            Scene sc = new Scene(newNoteFXML);
            Stage stage = new Stage();
            stage.setScene(sc);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Note");
            stage.showAndWait();
            InitializeNotes();
            System.gc();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void searchEvent(KeyEvent event) {
        System.out.println("HomeController -> searchEvent() ...");
        if ((searchComBox.getText().trim().equals(""))) {
            InitializeNotes();
        } else {

            FP.getChildren().clear();
            List<NoteFX> noteList = select("where text like '%" + searchComBox.getText().trim() + "%'");
            int count = noteList.size();
            for (int i = 0; i < count; i++) {
                try {
                    Parent NoteFXML = FXMLLoader.load(getClass().getResource(NOTE_PAGE));
                    NoteController.textStatic.setText(noteList.get(i).getText());
                    NoteController.idStaitc.setText(noteList.get(i).getid() + "");
                    NoteController.APStatic.setStyle("-fx-background-color : " + noteList.get(i).getHexaColor() + ";");
                    FP.getChildren().add(NoteFXML);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public void InitializeNotes() {
        System.out.println("HomeController -> InitializeNotes() ...");
        createNotesTable();
        FP.getChildren().clear();
        List<NoteFX> noteList = select("");
        int count = noteList.size();
        for (int i = 0; i < count; i++) {
            try {
                Parent NoteFXML = FXMLLoader.load(getClass().getResource(NOTE_PAGE));
                NoteController.textStatic.setText(noteList.get(i).getText());
                NoteController.idStaitc.setText(noteList.get(i).getid() + "");
                NoteController.APStatic.setStyle("-fx-background-color : " + noteList.get(i).getHexaColor() + ";");

                FP.getChildren().add(NoteFXML);
            } catch (IOException ex) {
//                System.out.println(ex);
//                System.out.println();
                ex.printStackTrace();
            }
        }
    }

    private void createNotesTable() {
        System.out.println("HomeController -> createNotesTable() ...");
        try {
//            Class.forName("org.hsqldb.jdbcDriver");
            con = DatabaseManager.connectDB();
//            con = DriverManager.getConnection("jdbc:hsqldb:file:db/NoteDatabase", "SA", "");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        String tableCreateSQL = "CREATE TABLE IF NOT EXISTS `userdata`.`notes` ("
                + " id INT AUTO_INCREMENT PRIMARY KEY,"
                + " text LONGTEXT NOT NULL ,"
                + " color VARCHAR(25) NOT NULL )";
        try {
            con.prepareStatement(tableCreateSQL).execute();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("failed to create table");
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("error in close connection ");
                System.out.println(ex);
            }
        }
    }

    public static int getNumOfNotes() {
        System.out.println("HomeController -> getNumOfNotes() ...");
        int n = 0;
        try {
//            Class.forName("org.hsqldb.jdbcDriver");
            con = DatabaseManager.connectDB();
//            con = DriverManager.getConnection("jdbc:hsqldb:file:db/NoteDatabase", "SA", "");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        String sqlString = "SELECT COUNT(*) AS total FROM `userdata`.`notes`";
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sqlString);
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();
            n = resultSet.getInt("total");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        System.out.println("n = " + n);
        return n;
    }

    public static void insert(String text, String color) {
        System.out.println("HomeController -> insert() ...");
        String sqlString = "INSERT INTO `userdata`.`notes`(`id`,`text`,`color`) VALUES(?,?,?);";
        try {
            con = DatabaseManager.connectDB();
//            Class.forName("org.hsqldb.jdbcDriver");
//            con = DriverManager.getConnection("jdbc:hsqldb:file:db/NoteDatabase", "SA", "");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sqlString);
            prepareStatement.setInt(1, (getNumOfNotes() + 1));
            prepareStatement.setString(2, text);
            prepareStatement.setString(3, color);
            prepareStatement.execute();
            System.out.println("insert Done ...");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
        }

    }

    /*
    "where text like '" + searchComBox.getText().trim() + "%'"
     */
    public static List select(String s) {
        System.out.println("HomeController -> select() ...");
        String sqlString;
        if ("".equals(s)) {
            sqlString = "SELECT * FROM `userdata`.`notes`";
        } else {
            sqlString = "SELECT * FROM `userdata`.`notes`" + s;
        }
        try {
            con = DatabaseManager.connectDB();
//            Class.forName("org.hsqldb.jdbcDriver");
//            con = DriverManager.getConnection("jdbc:hsqldb:file:db/NoteDatabase", "SA", "");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        List listNotes = new ArrayList<NoteFX>();
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sqlString);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                listNotes.add(new NoteFX(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        printList(listNotes);

        return listNotes;
    }

    @FXML
    public void refresh(ActionEvent event) {
        InitializeNotes();
        System.gc();
    }

    private static void printList(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @FXML
    private void aboutWidow(ActionEvent event) {
        if (isVisible) {
            return;
        }
        aboutDialog.show();
        isVisible = true;
    }

    public void closeButton(MouseEvent e) {
        ((Stage) (((Node) (e.getSource())).getScene().getWindow())).close();
    }

    public void switchToTasks(ActionEvent e) throws IOException {
        sceneController.switchToTasks(e);
    }

    public void switchToTutorial(ActionEvent e) throws IOException {
        sceneController.switchToTutorial(e);
    }

    public void switchToViz(ActionEvent event) throws IOException {
        sceneController.switchToViz(event);
    }

    public void switchToEditor(ActionEvent event) throws IOException {
        sceneController.switchToEditor(event);
    }

    public void switchToUser(ActionEvent event) throws IOException {
        sceneController.switchToUser(event);
    }
}
