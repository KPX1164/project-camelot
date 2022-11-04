package se233.camelot.controller;


import javafx.scene.control.Alert;
import se233.camelot.Launcher;
import se233.camelot.view.Platform;

import java.util.concurrent.TimeUnit;

public class GameTimer implements Runnable {
    private long duration ;
    private boolean running ;
    Platform platform ;


    public GameTimer(Platform platform) {
        this.running = true ;
        this.platform = platform ;
        this.duration = platform.MATCHDURATION ;

    }

    public void updateTimeCountDown() {

        javafx.application.Platform.runLater(
                () -> {
                    this.platform.getTimer().setTime((int)duration);
                }
        );
    }

    @Override
    public void run() {

        while (running) {
            try {
                duration--;
                updateTimeCountDown();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(this.duration == 0){
                this.running = false;
            }
        }


        javafx.application.Platform.runLater(
                () -> {
                    this.platform.endPlatform();
                    Launcher.musicController.play("main");

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Time UP!");
                    alert.setHeaderText(null);
                    alert.setContentText("Time UP!");
                    alert.showAndWait();
                    SceneController.navigateTo("HomeView");

                }
        );

    }
}
