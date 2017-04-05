package com.slackers.inc.Boundary;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;

import java.io.IOException;

public class Main extends Application {

    /**
     * Main JavaFx function for initializing the display, background tools, and pass control to the Main Controller
     * @param primaryStage Main stage for placing objects on
     * @throws IOException When unable to access FXML and CSS files from Package tree
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        //sun.util.logging.PlatformLogger platformLogger = PlatformLogger.getLogger("java.util.prefs");
        //platformLogger.setLevel(PlatformLogger.Level.OFF);

        Parent login = FXMLLoader.load(getClass().getResource("/com/slackers/inc/Boundary/FXML/outershell.fxml"));
        String cssDoc = getClass().getResource("/com/slackers/inc/Boundary/CSS/custom.css").toExternalForm();
        login.getStylesheets().add(cssDoc);

        primaryStage.setTitle("Main Screen");
        primaryStage.setScene(new Scene(login));
        primaryStage.show();
    }

    /**
     * Main function for Starting JavaFX program
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void notifier(String pTitle, String pMessage) {
        Platform.runLater(() -> {
            Stage owner = new Stage(StageStyle.TRANSPARENT);
            StackPane root = new StackPane();
            root.setStyle("-fx-background-color: TRANSPARENT");
            Scene scene = new Scene(root, 1, 1);
            scene.setFill(Color.TRANSPARENT);
            owner.setScene(scene);
            owner.setWidth(1);
            owner.setHeight(1);
            owner.toBack();
            owner.show();
            Notifications.create().title(pTitle).text(pMessage).showInformation();
        });
    }

}
