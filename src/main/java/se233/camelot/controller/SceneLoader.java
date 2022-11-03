package se233.camelot.controller;

import javafx.fxml.FXML;
import se233.camelot.Launcher;

import java.util.Timer;
import java.util.TimerTask;


public class SceneLoader {

    @FXML
    public void initialize()  {
        new Reminder(2);
    }

    public class Reminder {
        Timer timer;
        public Reminder(int seconds) {
            timer = new Timer();
            timer.schedule(new RemindTask(), seconds*1000);
        }
        class RemindTask extends TimerTask {
            public void run() {
                timer.cancel(); //Terminate the timer thread
                Launcher.musicController.play("main");
                SceneController.navigateTo("MenuView");
            }
        }

    }
}
