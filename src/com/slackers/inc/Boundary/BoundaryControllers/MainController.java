package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.AccountController;
import com.slackers.inc.database.entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class MainController implements Initializable{

    private Preferences programPref;
    private AccountController userController;
    private User user;

    @FXML private AnchorPane mainContainer;
    @FXML private AnchorPane results;
    @FXML private AnchorPane search;
    @FXML private AnchorPane applications;
    @FXML private AnchorPane form;
    @FXML private AnchorPane settings;

    SearchController searchController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("This is the main controller. Starting up program ...");

        try {
            userController = new AccountController();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Account Controller created. Now checking Preferences");

        programPref = Preferences.userNodeForPackage(MainController.class);
        if(programPref.get("user", null) == null){
            try{
                loadLogin();
            }catch (IOException exception){
                exception.printStackTrace();
                System.out.println("Unable to load login page.");
            }
        }else{
            System.out.println("OK it is persisiting information now");
        }

        user = userController.getUser();

        try {
            loadPages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLogin() throws IOException{
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("../FXML/loginregisterform.fxml"));
        Parent login = loginLoader.load();
        String cssDoc = getClass().getResource("../CSS/custom.css").toExternalForm();
        login.getStylesheets().add(cssDoc);

        LoginController loginController = loginLoader.getController();
        loginController.setMainController(this);

        Stage stage = new Stage();
        stage.setTitle("Login or Signup");
        stage.setScene(new Scene(login));
        stage.showAndWait();

        System.out.println("The email is " + userController.getUser().getEmail() +
                "\nThe password is: " + userController.getUser().getPassword());

    }

    private void loadPages() throws IOException{

        settings = FXMLLoader.load(getClass().getResource("../FXML/settings.fxml"));

        FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("../FXML/search.fxml"));
        search = searchLoader.load();
        SearchController searchController = searchLoader.getController();
        searchController.setMainController(this);

        if(user.getUserType() != User.UserType.COLA_USER){
            results = FXMLLoader.load(getClass().getResource("../FXML/results.fxml"));
            applications = FXMLLoader.load(getClass().getResource("../FXML/applications.fxml"));
            form =  FXMLLoader.load(getClass().getResource("../FXML/form.fxml"));

            mainContainer.getChildren().setAll(applications);

        }else {
            mainContainer.getChildren().setAll(search);
        }
    }

    @FXML
    private void applicationClick(){
        mainContainer.getChildren().setAll(applications);
    }

    @FXML
    private void searchClick(){
        mainContainer.getChildren().setAll(search);
    }

    @FXML
    private void settingsClick(){
        mainContainer.getChildren().setAll(settings);
    }

    public void resultsClick(){
        mainContainer.getChildren().setAll(results);
    }

    public AccountController getAccountController(){
        return this.userController;
    }

    public Preferences getPreferences(){
        return this.programPref;
    }

    public User getUser(){
        return this.user;
    }
}
