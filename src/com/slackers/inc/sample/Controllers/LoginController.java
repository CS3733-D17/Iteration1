package com.slackers.inc.sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML private AnchorPane signUpPane;
    @FXML private AnchorPane logInPane;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;

    @FXML private TextField loginEmailField;
    @FXML private TextField loginPasswordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    void signUpToggle(){
        signUpPane.setVisible(true);
        signUpPane.setManaged(true);
        logInPane.setVisible(false);
        logInPane.setManaged(false);
    }

    @FXML
    void logInToggle(){
        signUpPane.setVisible(false);
        signUpPane.setManaged(false);
        logInPane.setVisible(true);
        logInPane.setManaged(true);
    }

    @FXML
    void getStartedClick(ActionEvent e) throws IOException{
        System.out.println(firstNameField.getText() + lastNameField.getText() + emailField.getText() + passwordField.getText());

        Parent main = FXMLLoader.load(getClass().getResource("../FXML/outershell.fxml"));
        main.getStylesheets().add(getClass().getResource("../CSS/custom.css").toExternalForm());

        Stage stage = new Stage();
        stage.setTitle("New Stage");
        stage.setScene(new Scene(main));
        stage.show();
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void logInClick(ActionEvent e) throws IOException {
        System.out.println(loginEmailField.getText() + loginPasswordField.getText());

        Parent main = FXMLLoader.load(getClass().getResource("../FXML/outershell.fxml"));
        main.getStylesheets().add(getClass().getResource("../CSS/custom.css").toExternalForm());

        Stage stage = new Stage();
        stage.setTitle("New Stage");
        stage.setScene(new Scene(main));
        stage.show();
        ((Node)(e.getSource())).getScene().getWindow().hide();

    }

}
