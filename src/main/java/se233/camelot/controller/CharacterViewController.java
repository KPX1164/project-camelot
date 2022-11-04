package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import se233.camelot.Launcher;
import se233.camelot.model.CharacterType;
import se233.camelot.view.Platform;

import java.util.ArrayList;

public class CharacterViewController {

    private ArrayList<CharacterType> playerMap = new ArrayList<>() ;
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
    @FXML
    public void initialize() {

        playerMap.add(CharacterType.megaMan);
        playerMap.add(CharacterType.megaMan);
        ToggleGroup player1 = new ToggleGroup();
        p1c1.setToggleGroup(player1);
        p1c2.setToggleGroup(player1);
        p1c3.setToggleGroup(player1);

        ToggleGroup player2 = new ToggleGroup();
        p2c1.setToggleGroup(player2);
        p2c2.setToggleGroup(player2);
        p2c3.setToggleGroup(player2);

        p2c1.setSelected(true);
        p1c1.setSelected(true);

        readyBtn.setOnAction( event -> {

            if (p1c1.isSelected()){
                playerMap.set(0, CharacterType.megaMan);
            }
            if (p1c2.isSelected()){
                playerMap.set(0, CharacterType.mashu);
            }
            if (p1c3.isSelected()){
                playerMap.set(0,CharacterType.saber);
            }
            if (p2c1.isSelected()){
                playerMap.set(1,CharacterType.megaMan);
            }
            if (p2c2.isSelected()){
                playerMap.set(1,CharacterType.mashu);
            }
            if (p2c3.isSelected()){
                playerMap.set(1,CharacterType.saber);
            }

            try{

                Platform platform = new Platform(playerMap) ;
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

            }catch (Exception e){
                e.printStackTrace();
            }

        });

        homeBtn.setOnAction(event -> {
            SceneController.navigateTo("MenuView");
        });

    }

}
