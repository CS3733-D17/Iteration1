package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.AccountController;
import com.slackers.inc.database.entities.User;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    private MainController mainController;

    @FXML private AnchorPane signUpPane;
    @FXML private AnchorPane logInPane;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;

    @FXML private TextField loginEmailField;
    @FXML private TextField loginPasswordField;

    @FXML private Button logInButton;
    @FXML private Button getStartedButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        BooleanBinding getStartedBinding = new BooleanBinding() {
            {
                super.bind(firstNameField.textProperty(),
                lastNameField.textProperty(),
                emailField.textProperty(),
                passwordField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return firstNameField.getText().isEmpty() ||
                        lastNameField.getText().isEmpty() ||
                        emailField.getText().isEmpty() ||
                        passwordField.getText().isEmpty();
            }
        };

        BooleanBinding logInBinding = new BooleanBinding() {
            {
                super.bind(loginEmailField.textProperty(),
                        loginPasswordField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return loginEmailField.getText().isEmpty() ||
                        loginPasswordField.getText().isEmpty();
            }
        };

        getStartedButton.disableProperty().bind(getStartedBinding);
        logInButton.disableProperty().bind(logInBinding);

    }

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
    void getStartedClick(ActionEvent event) throws IOException{
        System.out.println("Name: " + firstNameField.getText() + " " + lastNameField.getText()
                + "\nEmail: " + emailField.getText() + "\nPassword: " + passwordField.getText());

        mainController.getAccountController().createAccount(firstNameField.getText()+lastNameField.getText(),
                emailField.getText(),passwordField.getText(), User.UserType.COLA_USER);


        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void logInClick(ActionEvent event) throws IOException {
        System.out.println("Email: " + loginEmailField.getText() + "\nPassword: " + loginPasswordField.getText());

        try {
            if(mainController.getAccountController().loginUser(loginEmailField.getText(), loginPasswordField.getText()) != null) {
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }else{
                System.out.println("Incorrect Email or password");
            }

            // TODO Incorrect Password Appears

        }catch (SQLException e) {
            System.out.println("Login Failed");

            // TODO Incorrect Username Appears

        }

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController ;
    }

}
