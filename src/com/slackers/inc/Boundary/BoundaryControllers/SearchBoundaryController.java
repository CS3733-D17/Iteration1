package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.COLASearchController;
import com.slackers.inc.Controllers.Filters.AlcoholFilter;
import com.slackers.inc.Controllers.Filters.BrandNameFilter;
import com.slackers.inc.Controllers.Filters.TypeFilter;
import com.slackers.inc.database.entities.BeerLabel;
import com.slackers.inc.database.entities.ColaUser;
import com.slackers.inc.database.entities.DistilledLabel;
import com.slackers.inc.database.entities.Label;
import com.slackers.inc.database.entities.Label.BeverageType;
import com.slackers.inc.database.entities.User;
import com.slackers.inc.database.entities.WineLabel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class SearchBoundaryController implements Initializable {

    private ObservableList<String> typeList = FXCollections.observableArrayList("All", BeverageType.BEER.name(), BeverageType.WINE.name(), BeverageType.DISTILLED.name());
    private ObservableList<String> contentList = FXCollections.observableArrayList("All", "20 <", "21-50", "51 >");
    private ObservableList<String> pastList = FXCollections.observableArrayList("Vodka", "Gin", "Tequila", "Rum", "Mixers");
    private ObservableList<String> currentList = FXCollections.observableArrayList("Sweet", "Dry", "Coconut", "cherry");

    private MainController mainController;

    @FXML private TextField keyword;
    @FXML private ChoiceBox type;
    @FXML private TextField alcoholContent;
    @FXML private ListView pastSearch;
    @FXML private ListView currentFilter;

    public COLASearchController search;
    public Label label;
    public List results;

    @FXML
    private void getResultsClick(ActionEvent e) throws IOException, SQLException {
        
        search.getSearchControl().reset();

        Label l = new Label();
        if(!type.getValue().toString().equals("All")) {
            BeverageType btype = Label.BeverageType.valueOf(type.getValue().toString());
            if (btype == BeverageType.BEER)
            {
                l = new BeerLabel();
            }
            else if (btype == BeverageType.WINE)
            {
                l = new WineLabel();
            }
            else if (btype == BeverageType.DISTILLED)
            {
                l = new DistilledLabel();
            }
            TypeFilter f = new TypeFilter(btype);
            search.getSearchControl().addFilter(f);
        }
        
        if(!alcoholContent.getText().equals("All")){
            try
            {
                AlcoholFilter f = new AlcoholFilter(Double.parseDouble(alcoholContent.getText()));
                search.getSearchControl().addFilter(f);
            }catch (Exception ee){}
        }

        if (keyword.getText()!=null && !keyword.getText().equals(""))
        {
            BrandNameFilter f = new BrandNameFilter(keyword.getText());
            search.getSearchControl().addFilter(f);
        }

        

        FXMLLoader resultsLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/results.fxml"));
        Parent page = resultsLoader.load();
        page.getStylesheets().add(getClass().getResource("/com/slackers/inc/Boundary/CSS/custom.css").toExternalForm());
        ResultsController resultsController = resultsLoader.getController();
        resultsController.setSearchBoundaryController(this);

        results = search.search(l);
        resultsController.displayResults(results);

        //TODO actually search shit by keyword, alcohol content or type

        Stage stage = new Stage();
        stage.setTitle("Main Stage");
        stage.setScene(new Scene(page));
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent t) {
                resultsController.download(e);
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        type.setValue("All");
        type.setItems(typeList);
        pastSearch.setItems(pastList);
        currentFilter.setItems(currentList);

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController ;
        User usr = this.mainController.getUser();
        if (usr instanceof ColaUser)
        {
            this.search = new COLASearchController((ColaUser) usr);
        }
    }

}
