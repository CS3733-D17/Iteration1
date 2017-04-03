package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Boundary.CryptoTools;
import com.slackers.inc.Controllers.AccountController;
import com.slackers.inc.database.entities.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
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
    @FXML private AnchorPane settings;

    @FXML private Button applicationButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("This is the main controller. Starting up program ...");

        // Create the Account Controller
        try {
            userController = new AccountController();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Check program preferences for a user to log in
        programPref = Preferences.userNodeForPackage(MainController.class);
        if(programPref.get("email", "").equals("")){
            try {
                loadLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            String password = "";
            try {
                password = CryptoTools.decrypt(programPref.get("password", ""), programPref);
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
            }

            try {
                if(userController.loginUser(programPref.get("email", ""), password) == null){
                    programPref.put("email", "");
                    try {
                        loadLogin();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                programPref.put("email", "");
                try {
                    loadLogin();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        user = userController.getUser();

        try {
            loadPages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO fix signup so that it creates the user
    //TODO hide the application button for search users

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


        if (userController.getUser().getEmail() == null) {
            Platform.exit();
            System.exit(1);
        }

        programPref.put("email", userController.getUser().getEmail());
        try {
            programPref.put("password", CryptoTools.encrypt(userController.getUser().getPassword(), programPref));
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            System.out.println("Could not save username and password for reiterations");
        }

    }

    // TODO Make sure that preferences are completly deleted except for the key
    // TODO Figure out why it fails on Jason's computer
    // TODO Change outer shell so it only shows possible options

    private void loadPages() throws IOException{

        FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("../FXML/search.fxml"));
        search = searchLoader.load();
        SearchController searchController = searchLoader.getController();
        searchController.setMainController(this);
        //results = FXMLLoader.load(getClass().getResource("../FXML/results.fxml"));

        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("../FXML/settings.fxml"));
        settings = settingsLoader.load();
        SettingsController settingsController = settingsLoader.getController();
        settingsController.setMainController(this);
        settingsController.setTextBoxes();

        if(user.getUserType() == User.UserType.MANUFACTURER){
            FXMLLoader applicationLoader = new FXMLLoader(getClass().getResource("../FXML/applications.fxml"));
            applications = applicationLoader.load();
            ApplicationsController applicationsController = applicationLoader.getController();
            applicationsController.setMainController(this);
            applicationsController.addAccordianChildren();

            mainContainer.getChildren().setAll(applications);
            applicationButton.setVisible(true);

        }else if(user.getUserType() == User.UserType.US_EMPLOYEE) {
            FXMLLoader usEmployeeLoader = new FXMLLoader(getClass().getResource("../FXML/USEmployee.fxml"));
            applications = usEmployeeLoader.load();
            USEmployeeController applicationsController = usEmployeeLoader.getController();
            applicationsController.setMainController(this);
            applicationsController.addAccordianChildren();

            mainContainer.getChildren().setAll(applications);
            applicationButton.setVisible(true);

        }else{
            applicationButton.setVisible(false);
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

    @FXML
    private void logoutClick(){
        mainContainer.getScene().getWindow().hide();
        user = null;
        programPref.put("email", "");
        Parent login = null;
        try {

            login = FXMLLoader.load(getClass().getResource("../FXML/outershell.fxml"));
            String cssDoc = getClass().getResource("../CSS/custom.css").toExternalForm();
            login.getStylesheets().add(cssDoc);

            Stage stage = new Stage();
            stage.setTitle("Main Application");
            stage.setScene(new Scene(login));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Logout Failed");
            System.exit(0);
        }

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
