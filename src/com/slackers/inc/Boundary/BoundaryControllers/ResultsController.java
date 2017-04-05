package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.database.entities.Manufacturer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Created by Jason on 3/28/2017.
 */
public class ResultsController implements Initializable {

    @FXML private Accordion accordion;
    @FXML private TitledPane titledPane;

    @FXML private Label alcoholContent;
    @FXML private Label progress;
    @FXML private Label source;
    @FXML private Label type;
    @FXML private Label brand;
    @FXML private Label repID;

    @FXML private Label titleLabel;
    @FXML private Button extraButton;

    @FXML private TitledPane template;

    public MainController main;
    public SearchBoundaryController searchBoundaryController;
    public com.slackers.inc.database.entities.Label temp;
    public Manufacturer manufacturer;
    public List<com.slackers.inc.database.entities.Label> results;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void displayResults(List<com.slackers.inc.database.entities.Label> results) {

        this.results = results;
        if (results != null && !results.isEmpty()) {
            for (int i = 0; i < results.size(); i++) {
                com.slackers.inc.database.entities.Label l =((com.slackers.inc.database.entities.Label) results.get(i));
                try {
                    FXMLLoader templateLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/formTemplate.fxml"));
                    templateLoader.setController(this);
                    template = templateLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                System.out.println(((com.slackers.inc.database.entities.Label) results.get(i)));
                if (type!=null)
                    type.setText(((com.slackers.inc.database.entities.Label) results.get(i)).getProductType().toString());
                if (source!=null)
                    source.setText(((com.slackers.inc.database.entities.Label) results.get(i)).getProductSource().toString());
                if (brand!=null)
                    brand.setText(((com.slackers.inc.database.entities.Label) results.get(i)).getBrandName());
                if (alcoholContent!=null)
                    alcoholContent.setText(Double.toString(((com.slackers.inc.database.entities.Label) results.get(i)).getAlcoholContent()));

                
                if (type!=null)
                    extraButton.setText("More Info");
                
                if (accordion!=null)
                    accordion.getPanes().add(template);
            }

            if (accordion!=null && accordion.getPanes().size() > 0) {
                accordion.setExpandedPane(accordion.getPanes().get(0));
            }
        } else {
            System.out.println("No results to display!");
        }
    }

    @FXML
    public void download(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        ExtensionFilter filter = new ExtensionFilter("Csv files ","*.csv");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setSelectedExtensionFilter(filter);
        
        File result = fileChooser.showSaveDialog(((Node)event.getSource()).getScene().getWindow());
        if (result!=null)
        {
            try {
                FileOutputStream stream = new FileOutputStream(result);
                CsvWriter writer = new CsvWriter(stream);
                writer.init(com.slackers.inc.database.entities.Label.class);
                writer.initSubtype(com.slackers.inc.database.entities.WineLabel.class);
                writer.initSubtype(com.slackers.inc.database.entities.BeerLabel.class);
                writer.initSubtype(com.slackers.inc.database.entities.DistilledLabel.class);
                writer.addIgnoredGetMethod("getTableName");
                writer.addIgnoredGetMethod("getEntityValues");
                writer.addIgnoredGetMethod("getUpdatableEntityValues");
                writer.addIgnoredGetMethod("getEntityNameTypePairs");
                writer.addIgnoredGetMethod("getPrimaryKeyName");
                writer.addIgnoredGetMethod("getPrimaryKeyValue");
                writer.write(results);
            } catch (IOException ex) {
                Logger.getLogger(ResultsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setSearchBoundaryController(SearchBoundaryController searchBoundaryController) {
        this.searchBoundaryController = searchBoundaryController ;
    }


}
