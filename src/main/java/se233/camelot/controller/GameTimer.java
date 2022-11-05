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

            if(this.duration == 6 ){
                Launcher.musicController.playVoice("countDown");
            }
            if (this.duration == 5){
                Launcher.musicController.stop();
            }

            if(this.duration < 1){
                Platform.getCutScene().timeupTrigger();
            }

            if(this.duration < 0){

            }

            if(this.duration == -2){
                this.running = false;
            }
        }


        javafx.application.Platform.runLater(
                () -> {
                    this.platform.endPlatform();
                    Launcher.musicController.playSound("main");


                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                    SceneController.navigateTo("FinalView");

                }
        );

    }
}
