package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.database.entities.Manufacturer;
import com.slackers.inc.database.entities.UsEmployee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jason on 4/2/2017.
 */
public class USEmployeeController implements Initializable{

    @FXML Accordion accordionID;
    @FXML private BorderPane helloThing;
    @FXML TitledPane title1;
    @FXML private Label alcoholContent;
    @FXML private Label progress;
    @FXML private Label source;
    @FXML private Label type;
    @FXML private Label brand;
    @FXML private Label repID;
    @FXML private AnchorPane template;

    public MainController main;
    public UsEmployee employee;
    final String[] imageNames = new String[]{"Apples", "Flowers", "Leaves", "banana"};
    final TitledPane[] tps = new TitledPane[imageNames.length];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void addAccordianChildren(){
        employee = (UsEmployee) main.getUser();

        for (int i = 0; i < employee.getApplications().size(); i++) {

            try {
                FXMLLoader templateLoader = new FXMLLoader(getClass().getResource("../FXML/formTemplate.fxml"));
                templateLoader.setController(this);
                template = templateLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            repID.setText(employee.getApplications().get(i).getLabel().getRepresentativeIdNumber());
            type.setText(employee.getApplications().get(i).getLabel().getProductType().toString());
            source.setText(employee.getApplications().get(i).getLabel().getProductSource().toString());
            brand.setText(employee.getApplications().get(i).getLabel().getBrandName());
            progress.setText(employee.getApplications().get(i).getLabel().getApproval().toString());
            alcoholContent.setText(Double.toString(employee.getApplications().get(i).getLabel().getAlcoholContent()));

            tps[i] = new TitledPane(employee.getApplications().get(i).getLabel().getBrandName(), template);

        }
        accordionID.getPanes().addAll(tps);
        accordionID.setExpandedPane(tps[0]);


    }

    public void setMainController(MainController mainController) {
        this.main = mainController ;
    }
}
