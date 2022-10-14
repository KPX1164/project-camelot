package se233.camelot.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import se233.camelot.Launcher;
import se233.camelot.view.Platform;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameTimer implements Runnable {
    private long duration ;
    private boolean running ;
    Platform platform ;


    public GameTimer(Platform platform, long duration) {

        this.duration = duration ;
        this.running = true ;
        this.platform = platform ;
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
                    FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("HomePage.fxml"));
                    try {
                        Scene scene = new Scene(fxmlLoader.load());
                        Launcher.stage.setScene(scene);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.exit(0);
                }
        );

    }
}
