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
    private ImageView overLayerH;
    @FXML
    private ImageView askLayer;
    @FXML
    private ImageView yesView;
    @FXML
    private Button yesBtn;
    @FXML
    private ImageView noView;
    @FXML
    private Button noBtn;
    @FXML
    private ImageView parinyaLayer;




    @FXML
    public void initialize()  {

        startBtn.setOnAction( e -> {
            SceneController.navigateTo("MenuView");
        });
        
        quitBtn.setOnAction(event -> {
            overLayerH.setVisible(true);
            askLayer.setVisible(true);
            parinyaLayer.setVisible(true);
            yesView.setVisible(true);
            yesBtn.setVisible(true);
            noView.setVisible(true);
            noBtn.setVisible(true);

            yesBtn.setOnAction(yesEvent ->{
                System.exit(0);
            });
            noBtn.setOnAction(noEvent ->{
                overLayerH.setVisible(false);
                askLayer.setVisible(false);
                parinyaLayer.setVisible(false);
                yesView.setVisible(false);
                yesBtn.setVisible(false);
                noView.setVisible(false);
                noBtn.setVisible(false);
            });
        });
    }
}
