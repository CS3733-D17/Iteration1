package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.UsEmployeeController;
import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.WineLabel;
import com.slackers.inc.database.entities.*;
import com.slackers.inc.database.entities.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Created by Jason on 4/4/2017.
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
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private TextField PBBNumber;
    @FXML private Button acceptButton;
    @FXML private Button rejectButton;
    
    public UsEmployeeController employeeController;
    public LabelApplication form;
    public UsEmployee employee;

    private ObservableList<String> sourceList = FXCollections.observableArrayList(Label.BeverageSource.DOMESTIC.name(), Label.BeverageSource.IMPORTED.name());
    private ObservableList<String> typeList = FXCollections.observableArrayList(Label.BeverageType.BEER.name(), Label.BeverageType.WINE.name(), Label.BeverageType.DISTILLED.name());

    @FXML private TextField vintage;
    @FXML private TextField ph;
    @FXML private AnchorPane wineStuff;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        source.setValue(com.slackers.inc.database.entities.Label.BeverageSource.DOMESTIC.name());
        source.setItems(sourceList);
        type.setValue(com.slackers.inc.database.entities.Label.BeverageType.BEER.name());
        type.setItems(typeList);

        type.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldType, String newType) {
                com.slackers.inc.database.entities.Label.BeverageType typeB = com.slackers.inc.database.entities.Label.BeverageType.valueOf(newType);
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


    @FXML
    public void accept(){
        Date expiration = new Date(new java.util.Date().getTime()+63072000000L);// 2 yrs
        employeeController.acceptApplicaton(expiration);
    }

    @FXML
    public void reject(){
        employeeController.rejectApplication(form);
    }

    public void setEmployee(UsEmployeeController employeeController, LabelApplication application) {
        this.employeeController = employeeController;
        this.form = application;

        ArrayList<Node> nodes = new ArrayList<Node>();
        children(formAnchor, nodes);

        for(Node n: nodes){
            n.setDisable(true);
        }

        acceptButton.setDisable(false);
        rejectButton.setDisable(false);

        this.update(this.form);
    }

    private void update(LabelApplication application) {
        source.setValue(application.getLabel().getProductSource().name());
        type.setValue(application.getLabel().getProductType().name());
        PBBNumber.setText(application.getLabel().getPlantNumber());
        phone.setText(application.getPhoneNumber());
        alcoholContent.setText(Double.toString(application.getLabel().getAlcoholContent()));
        address1Field.setText(application.getApplicantAddress().toString());
        address2Field.setText(application.getMailingAddress().toString());
        brand.setText(application.getLabel().getBrandName());
        repID.setText(application.getRepresentativeId());
        email.setText(application.getEmailAddress());
        country1Field.setText(application.getApplicantAddress().getCountry());
        country2Field.setText(application.getMailingAddress().getCountry());

        if(application.getLabel().getProductType() == Label.BeverageType.WINE) {
            vintage.setText(String.valueOf(((WineLabel) application.getLabel()).getVintage()));
            ph.setText(String.valueOf(((WineLabel) application.getLabel()).getPhLevel()));
        }

    }

    private void children(Pane parent,  ArrayList<Node> nodes){
        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof Pane) {
                children((Pane) node, nodes);
            }else if (node instanceof TextField || node instanceof TextArea || node instanceof ChoiceBox){
                nodes.add(node);

            }
        }
    }
}
