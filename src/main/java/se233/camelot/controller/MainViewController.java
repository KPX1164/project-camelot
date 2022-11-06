package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.Launcher;
import se233.camelot.controller.game.DrawingLoop;
import se233.camelot.controller.game.GameLoop;
import se233.camelot.controller.game.GameTimer;
import se233.camelot.view.Platform;



public class MainViewController {

    @FXML
    private Button tapBtn ;
    @FXML
    private ImageView changeScene;
    @FXML
    private Button moon;
    private Logger logger = LoggerFactory.getLogger(MainViewController.class);

    @FXML
    public void initialize()  {

        tapBtn.setOnAction( e -> {
            Launcher.musicController.playSound("load");
            SceneController.navigateTo("SceneLoaderView");

        });

        moon.setOnAction(actionEvent -> {
            try{
                Platform platform = new Platform() ;
                GameLoop gameLoop = new GameLoop(platform);
                DrawingLoop drawingLoop = new DrawingLoop(platform);
                GameTimer gameTimer = new GameTimer(platform);

                Thread gameLoopThread = new Thread(gameLoop);
                gameLoopThread.setDaemon(true);
                gameLoopThread.start();

                new Thread(drawingLoop).start();
                new Thread(gameTimer).start();

                Launcher.musicController.playSound("game");
                Launcher.stage.getScene().setOnKeyPressed(keyEvent -> platform.getKeys().add(keyEvent.getCode()));
                Launcher.stage.getScene().setOnKeyReleased( keyEvent -> platform.getKeys().remove(keyEvent.getCode()));
                Launcher.stage.getScene().setRoot(platform);
            }catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry bro there is a critical error please restart the program");
                logger.error(ex.getMessage());
                alert.showAndWait();
                System.exit(0);
            }

        });

    }
}
