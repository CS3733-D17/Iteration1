package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.UsEmployeeController;
import com.slackers.inc.database.entities.UsEmployee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jason on 4/4/2017.
 */
public class USEmployeeFormController implements Initializable {

    @FXML private Label alcoholContent;

    @FXML private Label source;
    @FXML private Label type;
    @FXML private Label brand;
    @FXML private Label repID;
    @FXML private TextField address1Field;
    @FXML private TextField address2Field;
    @FXML private TextField country1Field;
    @FXML private TextField country2Field;
    @FXML private AnchorPane formAnchor;

    public UsEmployee employee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void accept(){

    }

    @FXML
    public void reject(){

    }

    public void setEmployee(UsEmployee employee) {
        this.employee = employee ;

        for (int i = 0; i < formAnchor.getChildren().size(); i++) {
            formAnchor.getChildren().get(i).setDisable(true);
        }
    }
}
