package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.LabelApplicationController;
import com.slackers.inc.Controllers.UsEmployeeController;
import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.LabelComment;
import com.slackers.inc.database.entities.UsEmployee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javax.xml.soap.Text;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by Jason on 4/4/2017.
 */
public class USEmployeeFormController implements Initializable {

    @FXML private TextField alcoholContent;

    @FXML private ChoiceBox source;
    @FXML private ChoiceBox type;
    @FXML private TextField brand;
    @FXML private TextField repID;
    @FXML private TextArea address1Field;
    @FXML private TextArea address2Field;
    @FXML private TextField country1Field;
    @FXML private TextField country2Field;
    @FXML private AnchorPane formAnchor;

    @FXML private Button acceptButton;
    @FXML private Button rejectButton;

    public UsEmployeeController employeeController;
    public LabelApplication form;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        acceptButton.setDisable(false);
        rejectButton.setDisable(false);
    }


    @FXML
    public void accept(){
        Date expiration = new Date(new java.util.Date().getTime()+63072000000L);// 2 yrs

        employeeController.acceptApplicaton(expiration);
    }

    @FXML
    public void reject(){
        employeeController.rejectApplication(form);
    }

    public void setEmployee(UsEmployeeController employeeController, LabelApplication app) {
        this.employeeController = employeeController;
        this.form = app;

        for (int i = 0; i < formAnchor.getChildren().size(); i++) {
            formAnchor.getChildren().get(i).setDisable(true);
        }

        acceptButton.setDisable(false);
        rejectButton.setDisable(false);

    }
}
