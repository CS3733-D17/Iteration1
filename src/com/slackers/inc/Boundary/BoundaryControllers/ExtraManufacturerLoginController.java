package com.slackers.inc.Boundary.BoundaryControllers;

/**
 * @author Created by SrinuL on 4/3/17.
 */

import com.slackers.inc.Controllers.LabelApplicationController;
import com.slackers.inc.Controllers.ManufacturerController;
import com.slackers.inc.database.entities.Address;
import com.slackers.inc.database.entities.Manufacturer;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @FXML private javafx.scene.control.Label info;
    
    private MainController mainController;

    @FXML
    void continueClick(ActionEvent event){
        try {
            ManufacturerController controller = new ManufacturerController((Manufacturer) mainController.getAccountController().getUser());
            controller.getManufacturer().getTemplateApplication().setRepresentativeId(repIDField.getText());
            controller.getManufacturer().getTemplateApplication().setPhoneNumber(phoneField.getText());
            controller.getManufacturer().getTemplateApplication().getLabel().setPlantNumber(permitField.getText());
            controller.getManufacturer().getTemplateApplication().setEmailAddress(controller.getManufacturer().getEmail());
            Address adr = Address.tryParse(addressField.getText());
            if (adr==null)
                throw new IllegalArgumentException("You must provide a valid address");
            controller.getManufacturer().getTemplateApplication().setApplicantAddress(adr);
            new LabelApplicationController(controller.getManufacturer().getTemplateApplication()).createApplication();
            controller.updateManufacturer();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (SQLException ex) {
            Logger.getLogger(ExtraManufacturerLoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException e){
            info.setText(e.getMessage());
        }catch (Exception e){
            info.setText("You must complete the fields");
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController ;
    }

}
