package com.slackers.inc.Boundary.BoundaryControllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationsController implements Initializable{

    @FXML Accordion accordionID;
    @FXML private BorderPane helloThing;
    @FXML TitledPane title1;
    private DoubleProperty width = new SimpleDoubleProperty(500);


    final String[] imageNames = new String[]{"Apples", "Flowers", "Leaves", "banana"};
    final Image[] images = new Image[imageNames.length];
    final ImageView[] pics = new ImageView[imageNames.length];
    final TitledPane[] tps = new TitledPane[imageNames.length];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("This is the Application Controller");

        for (int i = 0; i < imageNames.length; i++) {
            //images[i] = new Image(getClass().getResourceAsStream(imageNames[i] + ".jpg"));
            pics[i] = new ImageView(images[i]);
            tps[i] = new TitledPane(imageNames[i],pics[i]);
        }
        accordionID.getPanes().addAll(tps);
        accordionID.setExpandedPane(tps[0]);


        //width.bind(accordionID.widthProperty());
        //helloThing.styleProperty().bind(Bindings.concat("-fx-pref-width: ", width.subtract(50).asString(), ";"));

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

    //TODO make the accordian template and fill with form information

}