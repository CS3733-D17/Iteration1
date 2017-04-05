package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.LabelApplicationController;
import com.slackers.inc.Controllers.UsEmployeeController;
import com.slackers.inc.database.entities.LabelApplication;
import com.slackers.inc.database.entities.UsEmployee;
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

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Jason on 4/2/2017.
 */
public class USEmployeeBoundaryController implements Initializable{

    @FXML Accordion accordionID;
    @FXML private BorderPane helloThing;
    @FXML TitledPane titledPane;
    @FXML private Label alcoholContent;
    @FXML private Label progress;
    @FXML private Label source;
    @FXML private Label type;
    @FXML private Label brand;
    @FXML private Label repID;
    @FXML private TextField address1Field;
    @FXML private TextField address2Field;
    @FXML private TextField country1Field;
    @FXML private TextField country2Field;
    @FXML private Label agentName;

    @FXML private TitledPane template;

    @FXML private Label titleLabel;
    @FXML private Button extraButton;

    public MainController main;
    public UsEmployeeController UScontroller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void addAccordianChildren(){
        List<LabelApplication> apps = UScontroller.getEmployee().getApplications();
        accordionID.getPanes().clear();
        if (apps != null && apps.size() != 0) {
            for (int i = 0; i < apps.size(); i++) {

                try {
                    FXMLLoader templateLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/formTemplate.fxml"));
                    templateLoader.setController(this);
                    template = templateLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("This is not loading!");
                }

                LabelApplication app = apps.get(i);
                titleLabel.setText(app.getLabel().getBrandName());
                repID.setText(app.getLabel().getRepresentativeIdNumber());
                type.setText(app.getLabel().getProductType().toString());
                source.setText(app.getLabel().getProductSource().toString());
                brand.setText(app.getLabel().getBrandName());
                progress.setText(app.getLabel().getApproval().toString());
                alcoholContent.setText(Double.toString(app.getLabel().getAlcoholContent()));
                agentName.setText(app.getReviewer().getFirstName());

                extraButton.setText("Review");
                extraButton.setOnAction(event -> {
                    try {
                        review(app);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                accordionID.getPanes().add(titledPane);
            }
        }

        if(accordionID.getPanes().size() > 0){
            accordionID.setExpandedPane(accordionID.getPanes().get(0));
        }

    }

    @FXML
    public void addApplication() throws IOException{


    }

    public void review(LabelApplication app, long appID) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/USform.fxml"));
        Parent newApp = loader.load();
        USEmployeeFormController USformController = loader.getController();
        USformController.setEmployee(this.UScontroller, (UsEmployee) main.getUser(), app);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Review Form");
        stage.setScene(new Scene(newApp));
        stage.show();
    }



    public void setMainController(MainController mainController) {
        this.main = mainController ;
        this.UScontroller = new UsEmployeeController((UsEmployee) this.main.getAccountController().getUser());
    }
}
