package com.slackers.inc.sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationsController implements Initializable{

    @FXML Accordion accordionID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("This is the Application Controller");

        /*for (Node node : accordionID.getPanes()) {
            System.out.println("Id: " + node.getClass());
            if (node instanceof TitledPane) {
                TitledPane paneNode = (TitledPane) node;
                System.out.println("This is printing out");

                ImageView imageView = new ImageView(new Image(getClass().getResource("../Images/bottle.png").toExternalForm()));

                paneNode.setGraphic(imageView);
                paneNode.setContentDisplay(ContentDisplay.RIGHT);

                paneNode.applyCss();
                paneNode.layout();

                System.out.println(paneNode.getStyleClass());

                // title region
               /* Node titleRegion=paneNode.lookup(".title");
                // padding
                Insets padding=((StackPane)titleRegion).getPadding();
                // image width
                double graphicWidth=imageView.getLayoutBounds().getWidth();
                // arrow
                double arrowWidth=titleRegion.lookup(".arrow-button").getLayoutBounds().getWidth();
                // text
                double labelWidth=titleRegion.lookup(".text").getLayoutBounds().getWidth();

                double nodesWidth = graphicWidth+padding.getLeft()+padding.getRight()+arrowWidth+labelWidth;

                paneNode.graphicTextGapProperty().bind(paneNode.widthProperty().subtract(nodesWidth));

            }
        }*/

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
