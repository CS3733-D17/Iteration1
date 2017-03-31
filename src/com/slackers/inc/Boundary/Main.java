package com.slackers.inc.Boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent login = FXMLLoader.load(getClass().getResource("FXML/loginregisterform.fxml"));
        String cssDoc = getClass().getResource("CSS/custom.css").toExternalForm();
        login.getStylesheets().add(cssDoc);

        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(new Scene(login));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
