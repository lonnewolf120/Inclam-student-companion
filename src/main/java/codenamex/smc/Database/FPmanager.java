package codenamex.smc.Database;

import codenamex.smc.design.CustomAlert;
import codenamex.smc.sceneController;
//import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.Result;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import java.security.SecureRandom;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static codenamex.smc.Database.Const.CHANGE_PASS;


public class FPmanager {

    @FXML
    private Button FP_submitButton;

    @FXML
    private TextField codeEntered;

    @FXML
    private TextField confirmPassField;

    @FXML
    private Label emailLabel;

    @FXML
    private PasswordField newPassField;

    @FXML
    private PasswordField userName;
    private String emailToSend;




    private static final String CHARSET = "0123456789";
    private static final int CODE_LENGTH = 6;
    private String generatedCode;
    private String enteredCode;
    public static String generateUniqueCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARSET.length());
            char randomChar = CHARSET.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
    @FXML
    void sendCodeMail(ActionEvent event) throws SQLException {
    Connection con = DatabaseManager.connectDB();
    String sql = "SELECT `email` FROM `userdata`.`login_info` WHERE `username` = ?;";
    PreparedStatement preparedStatement = con.prepareStatement(sql);
    preparedStatement.setString(1, userName.getText());
    ResultSet res = preparedStatement.executeQuery();
    generatedCode = generateUniqueCode();
    if (res.next()) { // Check if there are any results
        emailToSend = res.getString("email");
        emailLabel.setText("Code sent to " + emailToSend + " 😊");
        System.out.println("The code: "+ generatedCode);
    } else {
        // Handle the case where no email is found for the given user
        emailLabel.setText("Email address not found for the user.");
        return; // Exit the method to avoid further execution
    }

        final String username = "";
        final String password = "";

        // Recipient's email address
//        String to = "recipient@example.com";

        // Sender's properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(username));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailToSend));



            // Set Subject: header field
            message.setSubject("Code for Login");

            // Now set the actual message
            message.setText("Hello, this is a test email from JavaFX!\nHere is your code: "+ generatedCode +"\nEnjoy your time!");

            // Send message
            Transport.send(message);
            System.out.println("code "+generatedCode+"\n");

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
    }
    @FXML
    void EnterCode(ActionEvent event) {
        String getCode = codeEntered.getText();
        if(getCode.equals(generatedCode))
        {
            newPassField.setDisable(false);
            confirmPassField.setDisable(false);
            FP_submitButton.setDisable(false);
        }
        else
        {
            emailLabel.setText("Wrong Code entered, Recheck!");
        }
    }

    @FXML
    void ForgotPasswordSubmit(ActionEvent event) throws SQLException {
        String usr = userName.getText();
        String newPass = newPassField.getText();
        String ConfirmPass = confirmPassField.getText();
        if(newPass.equals(ConfirmPass))
        {
            String sql = "UPDATE `userdata`.`login_info` SET `password` = ? WHERE `username` = ?";
             Connection con = DatabaseManager.connectDB();
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, newPass);
            prepare.setString(2, usr);

            Boolean res = prepare.execute();

            if(res)
            {
                System.out.println("Password Changed!");
                String tit = ("Password Changed");
                String des = ("Password has been successfully updated.\nUse it next time while logging in");
                CustomAlert.showInfoAlert(tit,des,CHANGE_PASS);
            }
            else
            {
                String tit = ("Password Not Changed");
                String des = ("Password Couldn't be updated.\nRestart server & try again!");
//                CustomAlert.showInfoAlert(tit,des,CHANGE_PASS);
            }

        }
        else
        {
            emailLabel.setText("PASSWORDS DON'T MATCH!");
        }
    }


    @FXML
    void switchToLoginA(ActionEvent event) throws IOException {
        sceneController.switchToLoginA(event);
    }

    @FXML
    void switchToSignupA(ActionEvent event) throws IOException {
        sceneController.switchToSignupA(event);
    }

}
