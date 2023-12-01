package codenamex.smc.Database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import codenamex.smc.sceneController;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class RegisterUser {


    @FXML
    private PasswordField confirm_pass;

    @FXML
    private DatePicker date_of_birth;
    @FXML
    private TextField institute;

    @FXML
    private PasswordField new_pass;

    @FXML
    private TextField register_email;

    @FXML
    private Button register_submit;
    @FXML
    private static Label doesnt_match_text;
    @FXML
    private Label valid_mail;
    @FXML
    private Label userblank;
//    @FXML
//    private Label passblank1;
    @FXML
    private Label passblank2;
    @FXML
    private Label passblank;
    @FXML
    private Label checker;
    @FXML
    private TextField register_username;
    private String gend;
    Boolean yes;

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static Boolean checkPasswordStrength(String password) {
        int length = password.length();
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = !password.matches("[a-zA-Z0-9 ]*");

        if (length >= 8 && hasUppercase && hasLowercase && hasDigit && hasSpecialChar) {
            doesnt_match_text.setText("Password is strong!");
            doesnt_match_text.setTextFill(Paint.valueOf("#0FAA1F"));
            return true;
        } else {

            System.out.println("Password is not strong. Suggestions:");

            if (length < 8) {
               doesnt_match_text.setText("Should be at least 8 characters long.");
            }

            if (!hasUppercase) {
                doesnt_match_text.setText("Should contain at least one uppercase letter.");
            }

            if (!hasLowercase) {
                doesnt_match_text.setText("Should contain at least one lowercase letter.");
            }

            if (!hasDigit) {
                doesnt_match_text.setText("Should contain at least one digit.");
            }

            if (!hasSpecialChar) {
                doesnt_match_text.setText("Should contain at least one special character.");
            }
            return false;
        }
    }
    public void registerAdmin(ActionEvent e) {
//        String sql_command ="SELECT count(1) FROM login_info WHERE username = ? AND password = ?;";
//        checker.setVisible(false);
        Connection connect = DatabaseManager.connectDB();
        String username = register_username.getText(), pass = new_pass.getText();
        String confirmPass = confirm_pass.getText();
        yes = false;
        String email = "";
        if(isValidEmail(register_email.getText()))
        {
            email = register_email.getText();
            yes = true;
        }
        else
        {
            register_email.setText("Enter valid one");
            valid_mail.setText("⚠NOT A VALID MAIL!!");
            yes = false;
        }
        yes = (checkPasswordStrength(new_pass.getText()));
        if(new_pass.equals(confirm_pass))
        {
            doesnt_match_text.setText("Password matches!"); yes = true;
        }
        else
        {
            doesnt_match_text.setText("Password Doesn't match!"); yes = false;
        }
        Date dob = Date.valueOf(date_of_birth.getValue());
        gend = institute.getText();
//        String gender = genderSelect.
        if(yes) {
//            checker.setVisible(false);
            String insertFields = "INSERT INTO `userdata`.`login_info` (`username`, `password`,`email`,`date_of_birth`,`gender`) VALUES ('";
            String insertValues = username + "','" + pass + "','" + email + "," + dob + "," + gend + ");";
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
        else
        {
//            checker.setVisible(true);
            checker.setText("CREDENTIALS INVALID (Enter valid mail & pass)");

        }
    }

    public void register_submit_button(ActionEvent e) throws IOException {
//        doesnt_match_text.setVisible(new_pass.getText().equals(confirm_pass.getText()));
//        register_username.setVisible(register_username.getText().isEmpty());
//        new_pass.setVisible(new_pass.getText().isEmpty());
        userblank.setVisible(register_username.getText().isBlank());
        if(register_email.getText().isBlank())valid_mail.setText("Can't be blank");
        else valid_mail.setText("");
//        passblank1.setVisible(register_email.getText().isEmpty());
        passblank.setVisible(new_pass.getText().isBlank());
        passblank2.setVisible(confirm_pass.getText().isBlank());
        if (register_username.getText().isBlank() || new_pass.getText().isBlank() || confirm_pass.getText().isBlank() || register_email.getText().isBlank()) {
//            afterLoginText.setStyle("-fx-text-fill: red;");

//            afterLoginText.setText("Please enter your credentials");
        } else {
            if (new_pass.getText().equals(confirm_pass.getText())) {
                doesnt_match_text.setText("Matched!");
                doesnt_match_text.setTextFill(Paint.valueOf("#0FAA1F"));
                registerAdmin(e);
//                sceneController.switchToLoginA(e);
                sceneController.switchToLoginA(e);
            } else {
                doesnt_match_text.setText("doesn't match!");
                doesnt_match_text.setTextFill(Paint.valueOf("#0FAA1F"));
            }

        }

    }

    public void switchToLogin(MouseEvent e) throws IOException {
        sceneController.switchToLogin(e);
    }

    public void closeButton(ActionEvent e) {
        sceneController.closeButtonA(e);
    }
}
