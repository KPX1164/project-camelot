package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import se233.camelot.Launcher;

import java.io.IOException;


public class MainViewController {

    @FXML
    private Button startBtn ;

    @FXML
    public void initialize()  {
        startBtn.setOnAction( e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("CharacterPage.fxml"));
            try {
                Launcher.stage.getScene().setRoot(fxmlLoader.load());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
