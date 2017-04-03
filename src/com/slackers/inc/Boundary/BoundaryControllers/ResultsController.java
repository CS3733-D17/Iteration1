package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.database.entities.Manufacturer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Created by Jason on 3/28/2017.
 */
public class ResultsController implements Initializable {

    //TODO make the accordian template and fill with label information

    @FXML Accordion accordion;
    @FXML private Label alcoholContent;
    @FXML private Label progress;
    @FXML private Label source;
    @FXML private Label type;
    @FXML private Label brand;
    @FXML private Label repID;

    @FXML private AnchorPane template;

    public MainController main;
    public com.slackers.inc.database.entities.Label temp;
    public Manufacturer manufacturer;

    final String[] imageNames = new String[]{"Apples", "Flowers", "Leaves", "banana"};
    //final AnchorPane[] labels;
    final TitledPane[] tps = new TitledPane[imageNames.length];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        manufacturer = (Manufacturer) main.getUser();

        for (int i = 0; i < manufacturer.getApplications().size(); i++) {


            try {
                FXMLLoader templateLoader = new FXMLLoader(getClass().getResource("../FXML/formTemplate.fxml"));
                templateLoader.setController(this);
                template = templateLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            repID.setText(manufacturer.getApplications().get(i).getLabel().getRepresentativeIdNumber());
            type.setText(manufacturer.getApplications().get(i).getLabel().getProductType().toString());
            source.setText(manufacturer.getApplications().get(i).getLabel().getProductSource().toString());
            brand.setText(manufacturer.getApplications().get(i).getLabel().getBrandName());
            progress.setText(manufacturer.getApplications().get(i).getLabel().getApproval().toString());
            alcoholContent.setText(Double.toString(manufacturer.getApplications().get(i).getLabel().getAlcoholContent()));

            tps[i] = new TitledPane(manufacturer.getApplications().get(i).getLabel().getBrandName(), template);

        }
        accordion.getPanes().addAll(tps);
        accordion.setExpandedPane(tps[0]);

    }


}
