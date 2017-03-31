package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.AccountController;
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

    private AccountController userValidate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO Add More Fields That Are Necessary for Users
        // TODO Add Extra Radio Button for US Employee

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
        System.out.println(firstNameField.getText() + lastNameField.getText() + emailField.getText() + passwordField.getText());

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
