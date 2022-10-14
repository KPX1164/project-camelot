package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import se233.camelot.Launcher;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.io.IOException;


public class MainViewController {

    @FXML
    private Button startBtn ;
    @FXML
    private Button quitBtn;



    @FXML
    public void initialize()  {

        startBtn.setOnAction( e -> {
            SceneController.navigateTo("CharacterView");
        });
        
        quitBtn.setOnAction(event -> {
            System.exit(0);
        });
    }
}
