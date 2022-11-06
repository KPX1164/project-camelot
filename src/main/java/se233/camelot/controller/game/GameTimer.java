package se233.camelot.controller.game;


import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.Launcher;
import se233.camelot.controller.SceneController;
import se233.camelot.view.Platform;

import java.util.concurrent.TimeUnit;

public class GameTimer implements Runnable {
    private long duration ;
    private boolean running ;
    Platform platform ;
    private Logger logger = LoggerFactory.getLogger(GameTimer.class);

    public GameTimer(Platform platform) {
        this.running = true ;
        this.platform = platform ;
        this.duration = platform.MATCHDURATION ;

    }

    public void updateTimeCountDown() {

        javafx.application.Platform.runLater(
                () -> {
                    try {
                        if(this.duration >= 0){
                            this.platform.getTimer().setTime((int)duration);
                        }
                    }catch (NullPointerException e){
                        logger.error(e.getMessage());
                    }

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
                logger.warn(e.getMessage());
            }

            if(this.duration == 6 ){
                Launcher.musicController.playVoice("countDown");
            }
            if (this.duration == 5){
                Launcher.musicController.stop();
            }

            if(this.duration < 1){
                try {
                    Platform.getAlertPopup().timeupTrigger();
                }catch (NullPointerException ex){
                    logger.warn(ex.getMessage());
                }
//                Platform.getCutScene().timeupTrigger();
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
                        logger.warn(e.getMessage());
                    }
                    SceneController.navigateTo("FinalView");

                }
        );

    }
}
