package codenamex.smc.Database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import codenamex.smc.sceneController;
import javafx.scene.input.MouseEvent;

public class RegisterUser {


    @FXML
    private PasswordField confirm_pass;

    @FXML
    private DatePicker date_of_birth;
    @FXML
    private MenuButton institute;

    @FXML
    private PasswordField new_pass;

    @FXML
    private TextField register_email;

    @FXML
    private Button register_submit;
    @FXML
    private Label doesnt_match_text;
    @FXML
    private Label userblank;
    @FXML
    private Label passblank1;
    @FXML
    private Label passblank2;
    @FXML
    private Label passblank;
    @FXML
    private TextField register_username;

    public void registerAdmin(ActionEvent e) {
//        String sql_command ="SELECT count(1) FROM login_info WHERE username = ? AND password = ?;";
        Connection connect = DatabaseManager.connectDB();
        String username = register_username.getText(), email = register_email.getText(), pass = new_pass.getText();
        String insertFields = "INSERT INTO `userdata`.`login_info` (`username`, `password`,`email`) VALUES ('";
        String insertValues = username + "','" + pass + "','" + email + "');";
        String insertToRegister = insertFields + insertValues;    //#SQL command
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(insertToRegister);

            //!Alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Completed");
            alert.setHeaderText("Registered Mr." + username + "'s account");
            alert.setContentText("Congratulations, your ID has been registered.\nNow login with the credentials");
            alert.showAndWait();
            //!Alert
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void register_submit_button(ActionEvent e) throws IOException {
//        doesnt_match_text.setVisible(new_pass.getText().equals(confirm_pass.getText()));
//        register_username.setVisible(register_username.getText().isEmpty());
//        new_pass.setVisible(new_pass.getText().isEmpty());
        userblank.setVisible(register_username.getText().isEmpty());
        passblank1.setVisible(register_email.getText().isEmpty());
        passblank.setVisible(new_pass.getText().isEmpty());
        passblank2.setVisible(confirm_pass.getText().isEmpty());
        if (register_username.getText().isEmpty() || new_pass.getText().isEmpty() || confirm_pass.getText().isEmpty() || register_email.getText().isEmpty()) {
//            afterLoginText.setStyle("-fx-text-fill: red;");
//            afterLoginText.setText("Please enter your credentials");
        } else {
            if (new_pass.getText().equals(confirm_pass.getText())) {
                doesnt_match_text.getStyleClass().add("green");
                doesnt_match_text.setText("Matched!");
                registerAdmin(e);
//                sceneController.switchToLoginA(e);
                sceneController.switchToLoginA(e);
            } else {

                doesnt_match_text.getStyleClass().add("red");
                doesnt_match_text.setText("doesn't match!");
            }

        }

    }

    public void switchToLogin(MouseEvent e) throws IOException {
        sceneController.switchToLogin(e);
    }

    public void closeButton(ActionEvent e) {
        sceneController.closeButton(e);
    }
}
