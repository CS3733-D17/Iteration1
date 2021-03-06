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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

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




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("This is the Application Controller");

        //width.bind(accordionID.widthProperty());
        //helloThing.styleProperty().bind(Bindings.concat("-fx-pref-width: ", width.subtract(50).asString(), ";"));

    }

    void addAccordianChildren(){
        System.out.println("Adding children");
        manufacturer.refresh();
        accordionID.getPanes().clear();
        List<LabelApplication> apps = manufacturer.getManufacturer().getApplications();
        for (LabelApplication a : apps)
        {
            System.out.println("ID: "+a.getApplicationId());
        }
        for (int i = 0; i < apps.size(); i++) {
            System.out.println("Adding Panel "+i+ " of "+ apps.size());
            try {
                FXMLLoader templateLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/formTemplate.fxml"));
                templateLoader.setController(this);
                template = templateLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            LabelApplication app = apps.get(i);
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
                    edit(app.getApplicationId());
                } catch (IOException | SQLException e) {
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
            stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                @Override
                public void handle(WindowEvent t) {
                    LabelApplication app = formController.manufacturer.getLabelAppController().getLabelApplication();
                    if (app.getStatus()==LabelApplication.ApplicationStatus.NOT_COMPLETE)
                    {
                        try {
                            formController.manufacturer.getLabelAppController().deleteApplication();
                        } catch (SQLException ex) {
                            Logger.getLogger(ApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    addAccordianChildren();
                }
            });

            stage.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.addAccordianChildren();
    }

    private void edit(long applicationID) throws IOException, SQLException {
        System.out.println("edit page");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/form.fxml"));
        Parent newApp = loader.load();
        FormController formController = loader.getController();
        formController.setManufacturer(main.getAccountController());
        formController.setAppController(this);
        formController.edit(applicationID);

        Stage stage = new Stage();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent t) {
                try {
                    formController.manufacturer.getLabelAppController().editApplication();
                    addAccordianChildren();
                } catch (SQLException ex) {
                    Logger.getLogger(ApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("edit Form");
        stage.setScene(new Scene(newApp));
        formController.init();
        stage.showAndWait();
        this.addAccordianChildren();
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
