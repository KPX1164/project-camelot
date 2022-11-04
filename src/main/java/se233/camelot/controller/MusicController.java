package se233.camelot.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import se233.camelot.Launcher;

import java.util.HashMap;
import java.util.Map;

public class MusicController {

    private Map<String , Media> musicMap = new HashMap<>() ;

    public MusicController() {

        Media mainTheme = new Media(Launcher.class.getResource("Audios/CosmosintheLostbelt.mp3").toString());
        Media gameTheme = new Media(Launcher.class.getResource("Audios/Coronation.mp3").toString());
        Media loadScene = new Media(Launcher.class.getResource("Audios/LoadSound.mp3").toString());

        this.musicMap.put("main",mainTheme);
        this.musicMap.put("game", gameTheme);
        this.musicMap.put("load", loadScene);


    }

    public void stop() {
        if(Launcher.bgPlayer != null){
            Launcher.bgPlayer.stop();
        }
    }

    public void play(String song){
        stop() ;
        Launcher.bgPlayer = new MediaPlayer(this.musicMap.get(song));
        Launcher.bgPlayer.setAutoPlay(true);
        Launcher.bgPlayer.setCycleCount(100);
        Launcher.bgPlayer.setVolume(1);
    }

}
