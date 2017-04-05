package com.slackers.inc.Boundary.BoundaryControllers;

import com.slackers.inc.Boundary.CryptoTools;
import com.slackers.inc.Boundary.Main;
import com.slackers.inc.Controllers.AccountController;
import com.slackers.inc.database.entities.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class MainController implements Initializable{

    // Main Objects for User Information
    private Preferences programPref;
    private AccountController userController;
    private User user;

    // Pages in the Application
    @FXML private AnchorPane mainContainer;
    @FXML private AnchorPane search;
    @FXML private AnchorPane applications;
    @FXML private AnchorPane settings;

    // Side label of the User to Indicate Signed in Info
    @FXML private Label userNameLabel;
    @FXML private Label userEmailLabel;

    // Side buttons to access different pages on screen
    @FXML private Button applicationButton;
    @FXML private Button searchButton;


    /**
     * Initializes the Entire program
     * Checks for User Login or Displays login page, and then loads pages
     * @param location Location of FXML
     * @param resources Any Language Packs or other Resources
     */
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

        // Assign objects to their respective information
        user = userController.getUser();
        userNameLabel.setText(user.getFirstName() + " " + user.getLastName());
        userEmailLabel.setText(user.getEmail());

        // Tries to load the Pages
        try {
            loadPages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO fix signup so that it creates the user
    //TODO hide the application button for search users

    /**
     * Loads the Login Page and Puts the Login User in the Preferences File
     * @throws IOException When unable to load the login page FXML or CSS
     */
    private void loadLogin() throws IOException{
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/loginregisterform.fxml"));
        Parent login = loginLoader.load();
        String cssDoc = getClass().getResource("/com/slackers/inc/Boundary/CSS/custom.css").toExternalForm();
        login.getStylesheets().add(cssDoc);

        LoginController loginController = loginLoader.getController();
        loginController.setMainController(this);

        Stage stage = new Stage();
        stage.setTitle("Login or Signup");
        stage.setScene(new Scene(login));
        stage.showAndWait();


        if (userController.getUser().getEmail() == null || userController.getUser().getEmail().equals("")
                || userController.getUser().getPassword() == null || userController.getUser().getPassword().equals("")) {
            Platform.exit();
            System.exit(1);
        }

        programPref.put("email", userController.getUser().getEmail());
        try {
            programPref.put("password", CryptoTools.encrypt(userController.getUser().getPassword(), programPref));
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            Notifications.create().title("Username Not Saved").text("Could not save username and password for reiterations. Please try again later.").showError();
            System.out.println("Could not save username and password for reiterations");
        }

    }

    // TODO Make sure that preferences are completly deleted except for the key
    // TODO Figure out why it fails on Jason's computer

    /**
     * Load all of the necessary pages for the user into the program for display
     * @throws IOException When unable to load any of the FXML Files
     */
    private void loadPages() throws IOException{

        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/settings.fxml"));
        settings = settingsLoader.load();
        SettingsController settingsController = settingsLoader.getController();
        settingsController.setMainController(this);
        settingsController.setTextBoxes();

        if(this.userController.getUser().getUserType() == User.UserType.MANUFACTURER){
            FXMLLoader applicationLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/applications.fxml"));
            applications = applicationLoader.load();
            ApplicationsController applicationsController = applicationLoader.getController();
            applicationsController.setMainController(this);
            applicationsController.addAccordianChildren();

            mainContainer.getChildren().setAll(applications);
            applicationButton.setVisible(true);
            searchButton.setVisible(false);

        }else if(this.userController.getUser().getUserType() == User.UserType.US_EMPLOYEE) {
            FXMLLoader usEmployeeLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/USEmployee.fxml"));
            applications = usEmployeeLoader.load();
            USEmployeeBoundaryController applicationsController = usEmployeeLoader.getController();
            applicationsController.setMainController(this);
            applicationsController.addAccordianChildren();

            mainContainer.getChildren().setAll(applications);
            applicationButton.setVisible(true);
            searchButton.setVisible(false);

        }else{
            FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/com/slackers/inc/Boundary/FXML/search.fxml"));
            search = searchLoader.load();
            SearchBoundaryController searchBoundaryController = searchLoader.getController();
            searchBoundaryController.setMainController(this);

            mainContainer.getChildren().setAll(search);
            applicationButton.setVisible(false);
            searchButton.setVisible(true);
        }
    }

    /**
     * Changes the main page to the application page if possible
     */
    @FXML
    private void applicationClick(){
        mainContainer.getChildren().setAll(applications);
    }

    /**
     * Changes the main page to the search page if possible
     */
    @FXML
    private void searchClick(){
        mainContainer.getChildren().setAll(search);
    }

    /**
     * Changes the main page to the settings page if possible
     */
    @FXML
    private void settingsClick(){
        mainContainer.getChildren().setAll(settings);
    }

    /**
     * Logs the user out pf the program and removes the name from the preferences file
     */
    @FXML
    private void logoutClick(){
        mainContainer.getScene().getWindow().hide();
        try {
            this.userController.logout();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        programPref.put("email", "");
        Parent login = null;
        try {

            login = FXMLLoader.load(getClass().getResource("/com/slackers/inc/Boundary/FXML/outershell.fxml"));
            String cssDoc = getClass().getResource("/com/slackers/inc/Boundary/CSS/custom.css").toExternalForm();
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

    /**
     * Returns the Account Controller
     * @return Account Controller for accessing the User
     */
    public AccountController getAccountController(){
        return this.userController;
    }

    /**
     * Returns the User of the Program to access infomration of it
     * @return User of program
     */
    public User getUser(){
        return this.getAccountController().getUser();
    }
}
