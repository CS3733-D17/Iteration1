package com.slackers.inc.Boundary.BoundaryControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;

/**
 * Created by Jason on 4/1/2017.
 */
public class SettingsController {


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

    public MainController main;

    @FXML
    void UpdatePersonal(ActionEvent event) {
        //main.getUser();
    }

    @FXML
    void UpdatePassword(ActionEvent event) {

    }
}
