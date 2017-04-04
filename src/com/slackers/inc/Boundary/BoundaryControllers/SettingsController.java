package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.ManufacturerController;
import com.slackers.inc.database.entities.Address;
import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.Manufacturer;
import com.slackers.inc.database.entities.User;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    // TODO Display information specific to each type of user

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;

    @FXML private TextField oldPassword;
    @FXML private TextField newPassword;
    @FXML private Label errorMessage;

    @FXML private AnchorPane manufacturerSettings;
    @FXML private TextField repIDField;
    @FXML private TextField pbbnumField;
    @FXML private TextField addressField;
    @FXML private TextField phoneField;

    private MainController mainController;
    private User user;
    private LabelApplication userTemplate;

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

    public void setTextBoxes(){
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());

        if(user.getUserType() == User.UserType.MANUFACTURER){
            repIDField.setText(userTemplate.getRepresentativeId());
            pbbnumField.setText(userTemplate.getLabel().getPlantNumber());
            addressField.setText(userTemplate.getApplicantAddress().toString());
            phoneField.setText(userTemplate.getPhoneNumber());

            manufacturerSettings.setVisible(true);
        }else{
            manufacturerSettings.setVisible(false);
        }
    }

    @FXML
    void UpdatePersonal() {
        user.setEmail(email.getText());
        user.setFirstName(firstName.getText());
        user.setLastName(lastName.getText());
    }

    @FXML
    void UpdatePassword() {
        if(user.getPassword().equals(oldPassword.getText())){
           user.setPassword(newPassword.getText());
        }
        else{
            System.out.println("Incorrect password");
            errorMessage.setVisible(true);
            fadeOut.playFromStart();
        }
    }

    @FXML
    void UpdateManufacturer() {
        userTemplate.setRepresentativeId(repIDField.getText());
        userTemplate.getLabel().setPlantNumber(pbbnumField.getText());
        userTemplate.setApplicantAddress(Address.tryParse(addressField.getText()));
        userTemplate.setPhoneNumber(phoneField.getText());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        this.user = mainController.getUser();
        this.userTemplate = ((Manufacturer) user).getTemplateApplication();

    }

}
