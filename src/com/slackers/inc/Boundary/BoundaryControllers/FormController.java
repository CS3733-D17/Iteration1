package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.ManufacturerController;
import com.slackers.inc.database.entities.Label;
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
    public ManufacturerController manufacturerController;
    public LabelApplication labelApplication;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        source.setValue("Domestic");
        source.setItems(sourceList);
        type.setValue("Beer");
        type.setItems(typeList);

    }

    public void init(Manufacturer manufacturer) throws SQLException{
        manufacturerController = new ManufacturerController(manufacturer);
        manufacturerController.createApplication();
        labelApplication = manufacturerController.getLabelAppController().getLabelApplication();

        repID.setText(labelApplication.getLabel().getRepresentativeIdNumber());
        email.setText(labelApplication.getEmailAddress());
        phone.setText(labelApplication.getPhoneNumber());
        PBBNumber.setText(labelApplication.getLabel().getPlantNumber());
        nameAddress.setText(labelApplication.getApplicantAddress().toString());

    }

    @FXML
    void submit() {
            labelApplication.getLabel().setAlcoholContent(Integer.parseInt(alcoholContent.getText()));
            labelApplication.getLabel().setBrandName(brand.getText());
            labelApplication.getLabel().setProductType(Label.BeverageType.valueOf(type.getValue()));
            labelApplication.getLabel().setProductSource(Label.BeverageSource.valueOf(source.getValue()));
            labelApplication.getLabel().setPlantNumber(PBBNumber.getText());
            labelApplication.getLabel().setRepresentativeIdNumber(repID.getText());

        try {
            manufacturerController.submitApplication();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to Submit Application.");
        }

    }

}
