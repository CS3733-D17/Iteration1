package com.slackers.inc.Boundary.BoundaryControllers;

/**
 * @author Created by SrinuL on 4/3/17.
 */

import com.slackers.inc.database.entities.Address;
import com.slackers.inc.database.entities.Manufacturer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExtraManufacturerLoginController {

    @FXML private TextField repIDField;
    @FXML private TextField permitField;
    @FXML private TextField phoneField;
    @FXML private TextArea addressField;

    private MainController mainController;

    @FXML
    void continueClick(ActionEvent event){
        Manufacturer newManufacturer = (Manufacturer) mainController.getAccountController().getUser();
        newManufacturer.getTemplateApplication().setRepresentativeId(repIDField.getText());
        newManufacturer.getTemplateApplication().setPhoneNumber(phoneField.getText());
        newManufacturer.getTemplateApplication().getLabel().setPlantNumber(permitField.getText());
        newManufacturer.getTemplateApplication().setApplicantAddress(Address.tryParse(addressField.getText()));

        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController ;
    }

}
