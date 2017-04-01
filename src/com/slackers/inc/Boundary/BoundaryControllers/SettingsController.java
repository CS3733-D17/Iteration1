package com.slackers.inc.Boundary.BoundaryControllers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Jason on 4/1/2017.
 */
public class SettingsController implements Initializable {


    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phone;

    @FXML
    private TextField oldPassword;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField email;

    @FXML
    private Label errorMessage;

    public MainController main;

    private FadeTransition fadeOut = new FadeTransition(
            Duration.millis(1000)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources){
        fadeOut.setNode(errorMessage);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);

    }

    @FXML
    void UpdatePersonal(ActionEvent event) {
        main.getUser().setEmail(email.getText());
        //main.getUser().setPhone(phone.getText());
        //main.getUser().setFirstName(firstName.getText());
        //main.getUser().setLastName(lastName.getText());
    }

    @FXML
    void UpdatePassword(ActionEvent event) {
        if(main.getUser().getPassword().equals(oldPassword.getText())){
            main.getUser().setPassword(newPassword.getText());
        }
        else{
            System.out.println("Incorrect Email or password");
            errorMessage.setVisible(true);
            fadeOut.playFromStart();
        }
    }

    public void setMainController(MainController mainController) {
        this.main = mainController ;
    }

}
