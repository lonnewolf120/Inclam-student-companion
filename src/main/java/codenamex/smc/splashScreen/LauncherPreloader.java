package codenamex.smc.splashScreen;

//package com.me.myproject.launcher;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static codenamex.smc.Const.PRELOADER;

public class LauncherPreloader extends Preloader {

    private Stage proloaderStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.proloaderStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource(PRELOADER));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
            proloaderStage.hide();
        }
    }
}