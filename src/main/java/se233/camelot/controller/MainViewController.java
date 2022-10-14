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
    private ImageView askLayer;
//    @FXML
//    private ImageView overLayerTwo;


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
        
        quitBtn.setOnAction(event -> {

            askLayer.setVisible(true);
//            overLayerTwo.setVisible(true);
//            System.exit(0);
        });
    }
}
