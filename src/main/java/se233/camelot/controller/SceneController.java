package se233.camelot.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.Launcher;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Logger logger = LoggerFactory.getLogger(SceneController.class);

    public static void navigateTo(String scene){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(scene+".fxml"));
            Launcher.stage.getScene().setRoot(fxmlLoader.load());
        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry bro there is a critical error please restart the program");
            logger.error(ex.getMessage());
            alert.showAndWait();
            System.exit(0);

        }

    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public Parent getRoot() {
        return root;
    }
}
