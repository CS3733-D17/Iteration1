package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.UsEmployeeController;
import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.WineLabel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Jason on 4/4/2017.
 */
public class USEmployeeFormController implements Initializable {

    private ObservableList<String> sourceList = FXCollections.observableArrayList(com.slackers.inc.database.entities.Label.BeverageSource.DOMESTIC.name(), com.slackers.inc.database.entities.Label.BeverageSource.IMPORTED.name());
    private ObservableList<String> typeList = FXCollections.observableArrayList(com.slackers.inc.database.entities.Label.BeverageType.BEER.name(), com.slackers.inc.database.entities.Label.BeverageType.WINE.name(), com.slackers.inc.database.entities.Label.BeverageType.DISTILLED.name());

    
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
    @FXML private TextField vintage;
    @FXML private TextField ph;
    @FXML private Button acceptButton;
    @FXML private Button rejectButton;

    @FXML private AnchorPane wineStuff;
    
    public UsEmployeeController employeeController;
    public LabelApplication form;

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

    public void setEmployee(UsEmployeeController employeeController, LabelApplication app) {
        this.employeeController = employeeController;
        this.form = app;

        for (int i = 0; i < formAnchor.getChildren().size(); i++) {
            formAnchor.getChildren().get(i).setDisable(true);
        }
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
        //acceptButton.setDisable(false);
        //rejectButton.setDisable(false);
        if (application.getLabel() instanceof WineLabel)
        {
            vintage.setText(Integer.toString(((WineLabel)application.getLabel()).getVintage()));
            ph.setText(Double.toString(((WineLabel)application.getLabel()).getPhLevel()));
        }
    }
}
