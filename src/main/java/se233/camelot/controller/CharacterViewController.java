package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.Launcher;
import se233.camelot.controller.game.DrawingLoop;
import se233.camelot.controller.game.GameLoop;
import se233.camelot.controller.game.GameTimer;
import se233.camelot.model.CharacterType;
import se233.camelot.view.Platform;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    private Logger logger = LoggerFactory.getLogger(CharacterViewController.class);
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
            Launcher.musicController.playEffect("click");

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

                ExecutorService gameLoopEx = Executors.newSingleThreadExecutor();
                gameLoopEx.submit(gameLoop);
                ExecutorService drawingLoopEx = Executors.newFixedThreadPool(3);
                drawingLoopEx.submit(drawingLoop);
                ExecutorService gameTimerEx = Executors.newSingleThreadExecutor();
                gameTimerEx.submit(gameTimer);



                gameLoopEx.shutdown();
                drawingLoopEx.shutdown();
                gameTimerEx.shutdown();

                Launcher.musicController.playSound("game");
                Launcher.stage.getScene().setOnKeyPressed(keyEvent -> platform.getKeys().add(keyEvent.getCode()));
                Launcher.stage.getScene().setOnKeyReleased( keyEvent -> platform.getKeys().remove(keyEvent.getCode()));
                Launcher.stage.getScene().setRoot(platform);

            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry bro there is a critical error please restart the program");
                logger.error(e.getMessage());
                alert.showAndWait();
                System.exit(0);
            }



        });

        homeBtn.setOnAction(event -> {
            Launcher.musicController.playEffect("click");
            SceneController.navigateTo("MenuView");
        });

    }

}
