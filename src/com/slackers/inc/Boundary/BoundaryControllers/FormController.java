package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Boundary.InputValidator;
import com.slackers.inc.Controllers.ManufacturerController;
import com.slackers.inc.database.entities.Address;
import com.slackers.inc.Controllers.ManufacturerController;
import com.slackers.inc.database.entities.Label;
import com.slackers.inc.database.entities.Label.BeverageSource;
import com.slackers.inc.database.entities.Label.BeverageType;
import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.Manufacturer;
import com.slackers.inc.database.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;

public class FormController implements Initializable {

    private ObservableList<String> sourceList = FXCollections.observableArrayList(BeverageSource.DOMESTIC.name(), BeverageSource.IMPORTED.name());
    private ObservableList<String> typeList = FXCollections.observableArrayList(BeverageType.BEER.name(), BeverageType.WINE.name(), BeverageType.DISTILLED.name());

    @FXML private ChoiceBox sourceComboBox;
    @FXML private ChoiceBox typeComboBox;
    @FXML private TextField PBBNumber;
    @FXML private TextField phone;
    @FXML private TextField alcoholContent;
    @FXML private ChoiceBox<String> source;
    @FXML private ChoiceBox<String> type;
    @FXML private TextField brand;
    @FXML private TextField repID;
    @FXML private TextField email;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField addressField1;
    @FXML private TextField addressField2;
    @FXML private TextField countryField;
    @FXML private TextField cityField;
    @FXML private TextField zipField;
    @FXML private TextField stateField;


    @FXML private javafx.scene.control.Label info;
    @FXML private Button submit;

    public ManufacturerController manufacturer;
    public LabelApplication labelApplication;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        source.setValue(BeverageSource.DOMESTIC.name());
        source.setItems(sourceList);
        type.setValue(BeverageType.BEER.name());
        type.setItems(typeList);

    }

    public void setManufacturer(ManufacturerController manufacturer) throws SQLException {
        this.manufacturer = manufacturer;
        this.manufacturer.createApplication();
        this.update(this.manufacturer.getLabelAppController().getLabelApplication());
    }
    
    @FXML
    void submit() {

        if (this.validateFields())
        {
            try {
                manufacturer.submitApplication();
            } catch (SQLException ex) {
                Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void update(LabelApplication application)
    {
        source.setValue(application.getLabel().getProductSource().name());
        type.setValue(application.getLabel().getProductType().name());
        PBBNumber.setText(application.getLabel().getPlantNumber());
        phone.setText(application.getPhoneNumber());
        alcoholContent.setText(Double.toString(application.getLabel().getAlcoholContent()));
        addressField1.setText(application.getApplicantAddress().toString());
        brand.setText(application.getLabel().getBrandName());
        repID.setText(application.getRepresentativeId());
        email.setText(application.getEmailAddress());
        validateFields();
    }
    
    private boolean validateFields()
    {
        try
        {
            LabelApplication application = this.manufacturer.getLabelAppController().getLabelApplication();
            Label label = application.getLabel();            
            label.setAlcoholContent(InputValidator.validateDouble(alcoholContent.getText(), "Alcohol content", 0, 100));
            label.setBrandName(InputValidator.validateStringNotEmpty(brand.getText(),"Brand name"));
            label.setPlantNumber(InputValidator.validateStringNotEmpty(PBBNumber.getText(),"Plant registry number"));
            label.setRepresentativeIdNumber(repID.getText());
            application.setEmailAddress(InputValidator.validateStringWithinLength(email.getText(),"Email",5,200));
            label.setProductType(BeverageType.valueOf(type.getValue()));
            label.setProductSource(BeverageSource.valueOf(type.getValue()));
            label.setIsAccepted(false);
            application.setLabel(label);
            Address adr = new Address();
            adr.setCity(cityField.getText());
            adr.setState(stateField.getText());
            adr.setZipCode(Integer.parseInt(zipField.getText()));
            adr.setLine1(addressField1.getText());
            adr.setLine2(addressField2.getText());
            adr.setCountry(countryField.getText());

            if (adr!=null)
            {
                application.setMailingAddress(adr);
                application.setApplicantAddress(adr);
            }
            else
            {
                throw new IllegalArgumentException("You must enter a valid address in the address field");
            }
            info.setText("");
            return true;
        }
        catch (Exception e)
        {
            info.setText(e.getMessage());
        }
        return false;
    }

}
