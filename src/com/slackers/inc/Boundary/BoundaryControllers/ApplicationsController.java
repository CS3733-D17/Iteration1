package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.ManufacturerController;
import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.Manufacturer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationsController implements Initializable{

    @FXML Accordion accordionID;
    @FXML private BorderPane helloThing;
    @FXML TitledPane titledPane;
    @FXML private Label alcoholContent;
    @FXML private Label progress;
    @FXML private Label source;
    @FXML private Label type;
    @FXML private Label brand;
    @FXML private Label repID;
    @FXML private Label approvalDate;
    @FXML private Label agentName;
    @FXML private Label expireDate;
    @FXML private TitledPane template;

    @FXML private Label titleLabel;
    @FXML private Button extraButton;

    public MainController main;
    public ManufacturerController manufacturer;

    private DoubleProperty width = new SimpleDoubleProperty(500);


    final String[] imageNames = new String[]{"Apples", "Flowers", "Leaves", "banana"};
    final TitledPane[] tps = new TitledPane[imageNames.length];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("This is the Application Controller");

        //width.bind(accordionID.widthProperty());
        //helloThing.styleProperty().bind(Bindings.concat("-fx-pref-width: ", width.subtract(50).asString(), ";"));

    }

    public void addAccordianChildren(){
        accordionID.getPanes().clear();
        for (int i = 0; i < manufacturer.getManufacturer().getApplications().size(); i++) {

            try {
                FXMLLoader templateLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/formTemplate.fxml"));
                templateLoader.setController(this);
                template = templateLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            LabelApplication app = manufacturer.getManufacturer().getApplications().get(i);
            titleLabel.setText(app.getLabel().getBrandName());
            repID.setText(app.getLabel().getRepresentativeIdNumber());
            type.setText(app.getLabel().getProductType().toString());
            source.setText(app.getLabel().getProductSource().toString());
            brand.setText(app.getLabel().getBrandName());
            progress.setText(app.getStatus().toString());
            alcoholContent.setText(Double.toString(app.getLabel().getAlcoholContent()));
            agentName.setText(app.getReviewer().getFirstName());
            if (app.getApplicationApproval()!=null)
            {
                approvalDate.setText(app.getApplicationApproval().getApprovalDate().toString());
                expireDate.setText(app.getApplicationApproval().getExperationDate().toString());
            }

            extraButton.setText("Edit");
            extraButton.setOnAction(event -> {
                try {
                    edit();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            accordionID.getPanes().add(titledPane);
        }

        if(accordionID.getPanes().size() > 0){
            accordionID.setExpandedPane(accordionID.getPanes().get(0));
        }


    }

    @FXML
    public void addApplication() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/form.fxml"));
            Parent newApp = loader.load();
            FormController formController = loader.getController();
            formController.setManufacturer(main.getAccountController());
            formController.setAppController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Form");
            stage.setScene(new Scene(newApp));
            formController.init();
            stage.show();
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void edit() throws IOException, SQLException {
        System.out.println("edit page");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/form.fxml"));
        Parent newApp = loader.load();
        FormController formController = loader.getController();
        formController.setManufacturer(main.getAccountController());
        formController.setAppController(this);
        formController.edit();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("edit Form");
        stage.setScene(new Scene(newApp));
        formController.init();
        stage.show();
    }

    //TODO make the accordian template and fill with form information

    public void setMainController(MainController mainController) {
        this.main = mainController;
        try {
            this.manufacturer = new ManufacturerController((Manufacturer) this.main.getAccountController().getUser());
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
