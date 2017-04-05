package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.AccountController;
import com.slackers.inc.database.entities.Address;
import com.slackers.inc.database.entities.Manufacturer;
import com.slackers.inc.database.entities.User;
import javafx.animation.FadeTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable{

    private MainController mainController;

    @FXML private AnchorPane signUpPane;
    @FXML private AnchorPane logInPane;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private ToggleGroup userType;
    @FXML private Label signUpErrorLabel;

    @FXML private TextField loginEmailField;
    @FXML private TextField loginPasswordField;
    @FXML private Label logInErrorLabel;

    @FXML private Button logInButton;
    @FXML private Button getStartedButton;


    private FadeTransition fadeOut = new FadeTransition(
            Duration.millis(1000)
    );

    // TODO Figure out why closing doesn't work exactly all the time

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

        fadeOut.setNode(logInErrorLabel);
        fadeOut.setNode(signUpErrorLabel);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);

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
    void getStartedClick(ActionEvent event){

        User.UserType type;

        if(userType.getSelectedToggle() != null){
            switch (((RadioButton)userType.getSelectedToggle()).getText()){
                case "Customer":
                    type = User.UserType.COLA_USER;
                    break;
                case "Manufacturer":
                    type = User.UserType.MANUFACTURER;
                    break;
                case "Employee":
                    type = User.UserType.US_EMPLOYEE;
                    break;
                default:
                    type = User.UserType.COLA_USER;
            }
        } else {
            type = User.UserType.COLA_USER;
        }

        System.out.println("Name: " + firstNameField.getText() + " " + lastNameField.getText()
                + "\nEmail: " + emailField.getText() + "\nPassword: " + passwordField.getText()
                + "\nType: " + type);

        try {
            mainController.getAccountController().createAccount(firstNameField.getText(), lastNameField.getText(),
                    emailField.getText(), passwordField.getText(), type);

            // TODO Form validation for right information

        }catch (IllegalStateException exception){
            System.out.println("Account Creation Failed. Crashes.");
            signUpErrorLabel.setVisible(true);
            fadeOut.playFromStart();
        }

        if(type == User.UserType.MANUFACTURER) {
            try {

                FXMLLoader extraLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/manufacturerregisterform.fxml"));
                Parent moreInfo = extraLoader.load();
                String cssDoc = getClass().getResource("/com/slackers/inc/Boundary/CSS/custom.css").toExternalForm();
                moreInfo.getStylesheets().add(cssDoc);
                ExtraManufacturerLoginController extraController = extraLoader.getController();
                extraController.setMainController(mainController);

                Stage stage = new Stage();
                stage.setTitle("Manufacturer More Info");
                stage.setScene(new Scene(moreInfo));
                stage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            mainController.getAccountController().editAccount();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                logInErrorLabel.setVisible(true);
                fadeOut.playFromStart();
            }
        }catch (SQLException e) {
            System.out.println("Login Failed");
            logInErrorLabel.setVisible(true);
            fadeOut.playFromStart();
        }

    }



    public void setMainController(MainController mainController) {
        this.mainController = mainController ;
    }

}
