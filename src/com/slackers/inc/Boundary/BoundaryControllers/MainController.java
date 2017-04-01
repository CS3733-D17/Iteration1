package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Controllers.AccountController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;


public class MainController implements Initializable{

    private Preferences programPref;
    private AccountController userController;

    @FXML private AnchorPane mainContainer;
    @FXML private AnchorPane results;
    @FXML private AnchorPane search;
    @FXML private AnchorPane applications;
    @FXML private AnchorPane form;


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
            try {
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

                System.out.println("The email is " + userController.getUser().getEmail() + "\nThe password is: " + userController.getUser().getPassword());

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: Unable to Access Login Page");
            }
        }else{
            System.out.println("OK it is persisiting information now");
        }

        loadFXML();

    }

    private void loadFXML(){
        try {
            results = FXMLLoader.load(getClass().getResource("../FXML/results.fxml"));
            applications = FXMLLoader.load(getClass().getResource("../FXML/applications.fxml"));
            form =  FXMLLoader.load(getClass().getResource("../FXML/form.fxml"));

            FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("../FXML/search.fxml"));
            search = searchLoader.load();
            SearchController searchController = searchLoader.getController();
            searchController.setMainController(this);

            mainContainer.getChildren().setAll(applications);
        } catch (IOException e) {
            e.printStackTrace();
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
        mainContainer.getChildren().setAll(form);
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

}
