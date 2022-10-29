package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import se233.camelot.Launcher;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import se233.camelot.view.Platform;

import java.awt.*;
import java.io.IOException;


public class MainViewController {

    @FXML
    private Button tapBtn ;
    @FXML
    private ImageView changeScene;
    @FXML
    private Button moon;



    @FXML
    public void initialize()  {

        tapBtn.setOnAction( e -> {
            Launcher.musicController.play("load");
            SceneController.navigateTo("SceneLoaderView");

        });

        moon.setOnAction(actionEvent -> {
            Platform platform = new Platform() ;
            GameLoop gameLoop = new GameLoop(platform);
            DrawingLoop drawingLoop = new DrawingLoop(platform);
            GameTimer gameTimer = new GameTimer(platform);

            Thread gameLoopThread = new Thread(gameLoop);
            gameLoopThread.setDaemon(true);
            gameLoopThread.start();

            new Thread(drawingLoop).start();
            new Thread(gameTimer).start();


            Launcher.musicController.play("game");
            Launcher.stage.getScene().setOnKeyPressed(keyEvent -> platform.getKeys().add(keyEvent.getCode()));
            Launcher.stage.getScene().setOnKeyReleased( keyEvent -> platform.getKeys().remove(keyEvent.getCode()));
            Launcher.stage.getScene().setRoot(platform);        });

    }
}
