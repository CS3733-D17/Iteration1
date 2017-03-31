package com.slackers.inc.Boundary.BoundaryControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationsController implements Initializable{

    @FXML Accordion accordionID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("This is the Application Controller");

        for (Node node : accordionID.getPanes()) {
            System.out.println("Id: " + node.getClass());
            
        }

    }

    @FXML
    public void addApplication() throws IOException {
        Parent newApp = FXMLLoader.load(getClass().getResource("../FXML/form.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Form");
        stage.setScene(new Scene(newApp));
        stage.show();

    }


}