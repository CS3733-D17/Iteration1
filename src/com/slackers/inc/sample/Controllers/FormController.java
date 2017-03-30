package com.slackers.inc.sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    private ObservableList<String> sourceList = FXCollections.observableArrayList("Domestic", "International");
    private ObservableList<String> typeList = FXCollections.observableArrayList("Beer", "Wine", "Hard Alcohol");

    @FXML private ChoiceBox sourceComboBox;
    @FXML private ChoiceBox typeComboBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*sourceComboBox.setValue("Domestic");
        sourceComboBox.setItems(sourceList);

        typeComboBox.setValue("Beer");
        typeComboBox.getItems().add(sourceList);*/
    }
}
