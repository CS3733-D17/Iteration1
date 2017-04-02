package com.slackers.inc.Boundary.BoundaryControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Created by Jason on 3/28/2017.
 */
public class ResultsController implements Initializable {

    //TODO make the accordian template and fill with label information

    @FXML Accordion accordion;

    public MainController main;

    final String[] imageNames = new String[]{"Apples", "Flowers", "Leaves", "banana"};
    final Image[] images = new Image[imageNames.length];
    final ImageView[] pics = new ImageView[imageNames.length];
    final TitledPane[] tps = new TitledPane[imageNames.length];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < imageNames.length; i++) {
            //images[i] = new Image(getClass().getResourceAsStream(imageNames[i] + ".jpg"));
            pics[i] = new ImageView(images[i]);
            tps[i] = new TitledPane(imageNames[i],pics[i]);
        }
        accordion.getPanes().addAll(tps);
        accordion.setExpandedPane(tps[0]);

    }


}
