package codenamex.smc;

//package com.me.myproject.launcher;

import codenamex.smc.ViewStyles;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static codenamex.smc.Database.Const.LOGIN_PAGE;

public class InitPreloader implements Initializable {

    public Label lblLoading;
    public static Label lblLoadingg;

    @FXML
    private MediaView mediaView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoadingg=lblLoading;
        String videoFile = "path/to/your/video.mp4";
        Media media = new Media(new File(videoFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    public String checkFunctions(){

        final String[] message = {""};
        Thread t1 = new Thread(() -> {
            message[0] = "First Function";
            Platform.runLater(() -> lblLoadingg.setText(message[0]));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            message[0] = "Second Function";
            Platform.runLater(() -> lblLoadingg.setText(message[0]));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            message[0] = "Open Main Stage";
            Platform.runLater(() -> lblLoadingg.setText(message[0]));

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource(LOGIN_PAGE))));
                        ViewStyles.MoveAbleWindow(stage,root);   //Moveable window option
                        stage.setScene(new Scene(root));
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Logos/notes2.png")));
                        stage.show();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        });

        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return message[0];
    }

}