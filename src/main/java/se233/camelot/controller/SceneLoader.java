package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.Launcher;

import java.util.Timer;
import java.util.TimerTask;


public class SceneLoader {
    private Logger logger = LoggerFactory.getLogger(SceneLoader.class);
    @FXML
    public void initialize()  {
        try{
            new Reminder(2);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    public class Reminder {
        Timer timer;
        public Reminder(int seconds) throws Exception{
            timer = new Timer();
            timer.schedule(new RemindTask(), seconds*1000);
        }
        class RemindTask extends TimerTask {
            public void run() {
                timer.cancel(); //Terminate the timer thread
                Launcher.musicController.playSound("main");
                SceneController.navigateTo("MenuView");
            }
        }

    }
}
