package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import se233.camelot.Launcher;
import se233.camelot.model.Character;
import se233.camelot.model.CharacterType;
import se233.camelot.view.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class CharacterViewController {

    @FXML
    private Toggle p1c1;
    @FXML
    private Toggle p1c2;
    @FXML
    private Toggle p1c3;
    @FXML
    private Toggle p2c1;
    @FXML
    private Toggle p2c2;
    @FXML
    private Toggle p2c3;
    @FXML
    private Button homeBtn;
    @FXML
    private Button readyBtn;
//    ArrayList<CharacterType> player = new ArrayList<>();
    private Map<String,CharacterType> = new HashMap();

    @FXML
    public void initialize() {

        readyBtn.setOnAction( event -> {

            Platform platform = new Platform(player) ;
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
            Launcher.stage.getScene().setRoot(platform);

        });


        homeBtn.setOnAction(event -> {
            SceneController.navigateTo("MenuView");
        });

        ToggleGroup player1 = new ToggleGroup();
        p1c1.setToggleGroup(player1);
        p1c2.setToggleGroup(player1);
        p1c3.setToggleGroup(player1);

        ToggleGroup player2 = new ToggleGroup();
        p2c1.setToggleGroup(player2);
        p2c2.setToggleGroup(player2);
        p2c3.setToggleGroup(player2);

        if (p1c1.isSelected()){
            player.add(CharacterType.megaMan);
        }
        if (p1c2.isSelected()){
            player.add(CharacterType.zeroMan);
        }
        if (p1c3.isSelected()){
            player.add(CharacterType.saber);
        }
        if (p2c1.isSelected()){
            player.add(CharacterType.megaMan);
        }
        if (p2c2.isSelected()){
            player.add(CharacterType.zeroMan);
        }
        if (p2c3.isSelected()){
            player.add(CharacterType.saber);
        }

    }

}
