package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Boundary.InputValidator;
import com.slackers.inc.Controllers.ManufacturerController;
import com.slackers.inc.Controllers.UsEmployeeController;
import com.slackers.inc.database.entities.*;
import com.slackers.inc.Controllers.ManufacturerController;
import com.slackers.inc.database.entities.Label.BeverageSource;
import com.slackers.inc.database.entities.Label.BeverageType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.AnchorPane;

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
    @FXML private TextArea address1Field;
    @FXML private TextArea address2Field;
    @FXML private TextField country1Field;
    @FXML private TextField country2Field;
    @FXML private AnchorPane formAnchor;
    @FXML private AnchorPane wineStuff;


    @FXML private javafx.scene.control.Label info;
    @FXML private Button submit;

    public User user;
    public ManufacturerController manufacturer;
    public Manufacturer man;
    public UsEmployeeController employee;
    public UsEmployee emp;
    public LabelApplication labelApplication;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        source.setValue(BeverageSource.DOMESTIC.name());
        source.setItems(sourceList);
        type.setValue(BeverageType.BEER.name());
        type.setItems(typeList);


        type.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldType, String newType) {
                BeverageType typeB = BeverageType.valueOf(newType);
                wineStuff.setVisible(false);
                if (typeB != null) {
                    switch (typeB) {
                        case BEER:
                            break;
                        case WINE:
                            wineStuff.setVisible(true);
                            break;
                        case DISTILLED:
                            break;
                    }
                }
            }
        });
    }

    public void init(){

    }

    public void setManufacturer(User user) throws SQLException {

        if(user.getUserType() == User.UserType.US_EMPLOYEE) {
            emp = (UsEmployee) user;
            this.employee = new UsEmployeeController(emp);

            for (int i = 0; i < formAnchor.getChildren().size(); i++) {
                formAnchor.getChildren().get(i).setDisable(true);
            }
        }
        else{
            man = (Manufacturer) user;
            manufacturer = new ManufacturerController(man);
            manufacturer.createApplication();
            this.update(this.manufacturer.getLabelAppController().getLabelApplication());
        }
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
        address1Field.setText(application.getApplicantAddress().toString());
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
            label.setProductSource(BeverageSource.valueOf(source.getValue()));
            label.setIsAccepted(false);
            application.setLabel(label);
            Address adr = Address.tryParse(address1Field.getText());
            Address adr2 = Address.tryParse(address2Field.getText());


            if (adr!=null && adr2!=null)
            {
                application.setMailingAddress(adr2);
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
