package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import se233.camelot.Launcher;
import se233.camelot.view.Platform;

import java.io.IOException;
import java.util.concurrent.*;

public class CharacterViewController {

    @FXML
    private Button playBtn;
    @FXML
    private Button backBtn;
    @FXML
    public void initialize() {
        playBtn.setOnAction( event -> {
            Platform platform = new Platform() ;
            GameLoop gameLoop = new GameLoop(platform);
            DrawingLoop drawingLoop = new DrawingLoop(platform);
            GameTimer gameTimer = new GameTimer(platform,60);

            Thread gameLoopThread = new Thread(gameLoop);
            gameLoopThread.setDaemon(true);
            gameLoopThread.start();

            new Thread(drawingLoop).start();
            new Thread(gameTimer).start();


            Launcher.musicController.play("game");
            Launcher.stage.getScene().setOnKeyPressed(keyEvent -> platform.getKeys().add(keyEvent.getCode()));
            Launcher.stage.getScene().setOnKeyReleased( keyEvent -> platform.getKeys().remove(keyEvent.getCode()));
            Launcher.stage.getScene().setRoot(platform);

        });

        backBtn.setOnAction(event -> {
            Launcher.musicController.play("main");
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("HomePage.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load());
                Launcher.stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
