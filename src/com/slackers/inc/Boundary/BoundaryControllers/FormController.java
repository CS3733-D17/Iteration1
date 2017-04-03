package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.database.entities.Label;
import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.Manufacturer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    private ObservableList<String> sourceList = FXCollections.observableArrayList("Domestic", "International");
    private ObservableList<String> typeList = FXCollections.observableArrayList("Beer", "Wine", "Hard Alcohol");

    @FXML private ChoiceBox sourceComboBox;
    @FXML private ChoiceBox typeComboBox;
    @FXML private TextField PBBNumber;
    @FXML private TextField phone;
    @FXML private TextField alcoholContent;
    @FXML private TextArea nameAddress;
    @FXML private ChoiceBox<String> source;
    @FXML private ChoiceBox<String> type;
    @FXML private TextField brand;
    @FXML private TextField repID;
    @FXML private TextField email;

    public Manufacturer manufacturer;
    public LabelApplication application;
    public Label label;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO auto fill sections with user info

        // TODO send form information to the db

        source.setValue("Domestic");
        source.setItems(sourceList);
        type.setValue("Beer");
        type.setItems(typeList);

        //email.setText(manufacturer.getEmail());
        //nameAddress.setText(manufacturer.getFirstName() + manufacturer.getLastName());
    }


    @FXML
    void submit(ActionEvent event) {

        try
        {
            label.setAlcoholContent(Integer.parseInt(alcoholContent.getText()));
            label.setBrandName(brand.getText());
            //label.setProductType(type.getValue());
            //label.setProductSource((Label.BeverageSource) source.getValue());
            label.setPlantNumber(PBBNumber.getText());
            label.setRepresentativeIdNumber(repID.getText());

            application.setApplicant(manufacturer);
            application.setApplicationId(Integer.parseInt(repID.getText()));
            application.setEmailAddress(manufacturer.getEmail());
            application.setLabel(label);

            manufacturer.getApplications().add(application);
        }
        catch (Exception e)
        {
            
        }

    }

}
