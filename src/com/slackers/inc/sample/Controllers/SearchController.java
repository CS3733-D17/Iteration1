package com.slackers.inc.sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    ObservableList<String> typeList = FXCollections.observableArrayList("All", "Beer", "Wine", "Hard Alcohol");
    ObservableList<String> contentList = FXCollections.observableArrayList("All", "20 <", "21-50", "51 >");
    ObservableList<String> pastList = FXCollections.observableArrayList("Vodka", "Gin", "Tequila", "Rum", "Mixers");
    ObservableList<String> currentList = FXCollections.observableArrayList("Sweet", "Dry", "Coconut", "cherry");

    private MainController mainController;

    @FXML private TextField keyword;
    @FXML private ChoiceBox type;
    @FXML private ChoiceBox alcoholContent;
    @FXML private ListView pastSearch;
    @FXML private ListView currentFilter;

    @FXML
    private void getResultsClick(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        type.setValue("All");
        alcoholContent.setValue("All");
        type.setItems(typeList);
        alcoholContent.setItems(contentList);
        pastSearch.setItems(pastList);
        currentFilter.setItems(currentList);

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController ;
    }

}
