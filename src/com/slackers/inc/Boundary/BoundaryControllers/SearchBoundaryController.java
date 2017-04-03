package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.COLASearchController;
import com.slackers.inc.database.entities.ColaUser;
import com.slackers.inc.database.entities.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SearchBoundaryController implements Initializable {

    private ObservableList<String> typeList = FXCollections.observableArrayList("All", "Beer", "Wine", "Hard Alcohol");
    private ObservableList<String> contentList = FXCollections.observableArrayList("All", "20 <", "21-50", "51 >");
    private ObservableList<String> pastList = FXCollections.observableArrayList("Vodka", "Gin", "Tequila", "Rum", "Mixers");
    private ObservableList<String> currentList = FXCollections.observableArrayList("Sweet", "Dry", "Coconut", "cherry");

    private MainController mainController;

    @FXML private TextField keyword;
    @FXML private ChoiceBox type;
    @FXML private ChoiceBox alcoholContent;
    @FXML private ListView pastSearch;
    @FXML private ListView currentFilter;

    public COLASearchController search;
    public ColaUser user;
    public Label label;
    public List results;

    @FXML
    private void getResultsClick(ActionEvent e) throws IOException, SQLException {

        search = new COLASearchController();
        label = new Label();

        if(!alcoholContent.getValue().toString().equals("All")){
            label.setAlcoholContent(Double.parseDouble(alcoholContent.getValue().toString()));
        }

        label.setBrandName(keyword.getText());

        if(!type.getValue().toString().equals("All")) {
            label.setProductType(Label.BeverageType.valueOf(type.getValue().toString()));
        }

        user = (ColaUser) mainController.getUser();
        search.setColaUser(user);

        FXMLLoader resultsLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/results.fxml"));
        Parent page = resultsLoader.load();
        page.getStylesheets().add(getClass().getResource("/com/slackers/inc/Boundary/CSS/custom.css").toExternalForm());
        ResultsController resultsController = resultsLoader.getController();
        resultsController.setSearchBoundaryController(this);

        results = search.search(label);
        resultsController.displayResults(results);

        //TODO actually search shit by keyword, alcohol content or type

        Stage stage = new Stage();
        stage.setTitle("Main Stage");
        stage.setScene(new Scene(page));
        stage.show();


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
