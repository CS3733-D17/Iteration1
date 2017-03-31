package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.AccountController;
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

    private AccountController userValidate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO Add More Fields That Are Necessary for Users

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

        try {
            userValidate = new AccountController();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    void getStartedClick(ActionEvent e) throws IOException{
        System.out.println("Name: " + firstNameField.getText() + " " + lastNameField.getText()
                + "\nEmail" + emailField.getText() + "\nPassword" + passwordField.getText());

        // TODO Form Validation (will probably have the button disable itself)
        // TODO User Creation in Database

        Parent main = FXMLLoader.load(getClass().getResource("../FXML/outershell.fxml"));
        main.getStylesheets().add(getClass().getResource("../CSS/custom.css").toExternalForm());

        Stage stage = new Stage();
        stage.setTitle("Main Stage");
        stage.setScene(new Scene(main));
        stage.show();
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void logInClick(ActionEvent event) throws IOException {
        System.out.println("Email: " + loginEmailField.getText() + "\nPassword: " + loginPasswordField.getText());

        // TODO Form Validation (will probably have the button disable itself)

        try {
            if(userValidate.verifyCredentials(loginEmailField.getText(), loginPasswordField.getText())) {
                Parent main = FXMLLoader.load(getClass().getResource("../FXML/outershell.fxml"));
                main.getStylesheets().add(getClass().getResource("../CSS/custom.css").toExternalForm());

                Stage stage = new Stage();
                stage.setTitle("Main Stage");
                stage.setScene(new Scene(main));
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }

            // TODO Incorrect Password Appears

        }catch (SQLException e) {
            System.out.println("Login Failed");

            // TODO Incorrect Username Appears

        }

    }

}
