package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.*;
import com.slackers.inc.Controllers.SearchController;
import com.slackers.inc.database.entities.Manufacturer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
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

    @FXML private Accordion accordionID;
    @FXML private TitledPane titledPane;

    @FXML private Label alcoholContent;
    @FXML private Label progress;
    @FXML private Label source;
    @FXML private Label type;
    @FXML private Label brand;
    @FXML private Label repID;

    @FXML private Label titleLabel;
    @FXML private Button extraButton;

    @FXML private AnchorPane template;

    public MainController main;
    public SearchBoundaryController searchBoundaryController;
    public com.slackers.inc.database.entities.Label temp;
    public Manufacturer manufacturer;
    public List results;

    //final AnchorPane[] labels;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void displayResults(List results) {

        this.results = results;

        if (results != null && results.size() != 0) {
            for (int i = 0; i < results.size(); i++) {

                try {
                    FXMLLoader templateLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/formTemplate.fxml"));
                    templateLoader.setController(this);
                    template = templateLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                type.setText(((com.slackers.inc.database.entities.Label) results.get(i)).getProductType().toString());
                source.setText(((com.slackers.inc.database.entities.Label) results.get(i)).getProductSource().toString());
                brand.setText(((com.slackers.inc.database.entities.Label) results.get(i)).getBrandName());
                alcoholContent.setText(Double.toString(((com.slackers.inc.database.entities.Label) results.get(i)).getAlcoholContent()));

                extraButton.setText("More Info");

                accordionID.getPanes().add(titledPane);
            }

            if (accordionID.getPanes().size() > 0) {
                accordionID.setExpandedPane(accordionID.getPanes().get(0));
            }
        } else {
            System.out.println("No results to display!");
        }
    }

    public void setSearchBoundaryController(SearchBoundaryController searchBoundaryController) {
        this.searchBoundaryController = searchBoundaryController ;
    }


}
