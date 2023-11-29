package codenamex.smc;

import codenamex.smc.Database.DatabaseManager;
import codenamex.smc.Database.Login;
import codenamex.smc.design.CustomAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static codenamex.smc.Database.Const.*;

public class userDashboard implements Initializable {

    @FXML
    private ImageView Avatar;

    @FXML
    private Button ChangeAvatar;

    @FXML
    private Button ChangeInfo;

    @FXML
    private Label DOB;

    @FXML
    private Label Email;

    @FXML
    private Label Gender;

    @FXML
    private AnchorPane topbar;

    @FXML
    private Label userName;

    @FXML
    private BorderPane window;

    @FXML
    void UpdateAvatar(ActionEvent event) {

    }

    @FXML
    private DatePicker DOB2;

    @FXML
    private TextField emailL;

    @FXML
    private TextField insL;

    @FXML
    private TextField userL;

    @FXML
    void clearEdit(ActionEvent event) {
        userL.clear(); emailL.clear();insL.clear();
        DOB2.cancelEdit();
    }

    @FXML
    void saveButtonEdit(ActionEvent event) throws SQLException, IOException {
        String sql = "UPDATE `userdata`.`login_info` SET `username` = ?,"+
        "`email` = ?, `date_of_birth` = ?, `gender` = ? WHERE `user_id` = ?;";
        Connection con = DatabaseManager.connectDB();
        PreparedStatement prepare = con.prepareStatement(sql);
        prepare.setString(1, userL.getText());
        prepare.setString(2, emailL.getText());
        prepare.setDate(3, Date.valueOf(DOB2.getValue()));
        prepare.setString(4, insL.getText());
        prepare.setInt(5, Login.getUserId());

        ResultSet res = prepare.executeQuery();
        if(res.next())
            CustomAlert.showInfoAlert("Credentials changed", "The changes has been saved!", EDIT_BUTTON);
        else
            CustomAlert.showInfoAlert("Credentials Unchanged", "The changes couldn't be changed!", CANCEL_BUTTON);

        sceneController.switchControlsAction(USER_DASHBOARD,event);
    }

    @FXML
    void UpdateInfo(ActionEvent event) throws SQLException, IOException {
//        sceneController.switchControlsAction(USER_EDIT,event);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(USER_EDIT));
				BorderPane pane1 = null;
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

    @FXML
    void closeButton(MouseEvent event) {

    }

    @FXML
    void switchToEditor(ActionEvent event) throws IOException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(EDITOR_MAIN));
				BorderPane pane1 = null;
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

    @FXML
    void swtichToNotes(ActionEvent event) throws IOException {
        sceneController.switchToNotes(event);
    }
    @FXML
    public void switchToTasks(ActionEvent event) throws IOException {
        sceneController.switchToTasks(event);
    }
    @FXML
    public void switchToTutorial(ActionEvent event) throws IOException {
         sceneController.switchToTutorial(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sql = "SELECT `username`,`email`,`date_of_birth`,`gender` FROM `userdata`.`login_info` WHERE `user_id`=?";
        Connection con = DatabaseManager.connectDB();
        PreparedStatement prepare = null;
        try {
        prepare = con.prepareStatement(sql);
        prepare.setInt(1, Login.getUserId());

        ResultSet res = prepare.executeQuery();
        if(res.next())
        {
            userName.setText(res.getString("username"));
            Email.setText(res.getString("email"));
            DOB.setText(res.getString("date_of_birth"));
            Gender.setText(res.getString("gender"));
        }
        else
        {
            userName.setText("Batman");
            Email.setText("bat@cave.com");
            DOB.setText("Void");
            Gender.setText("Oxford");
        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void SignOutButton(ActionEvent event) throws IOException {
        sceneController.switchToLoginA(event);
    }

    public void switchToViz(ActionEvent event) throws IOException {
        sceneController.switchToViz(event);
    }

//    public void switchToTasks(ActionEvent event) {
//    }
}
