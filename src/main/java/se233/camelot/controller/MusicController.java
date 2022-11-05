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
        Media ballFalling = new Media(Launcher.class.getResource("Audios/BallFalling.mp3").toString());
        Media ballKick = new Media(Launcher.class.getResource("Audios/BallKick.mp3").toString());
        Media buttonHover = new Media(Launcher.class.getResource("Audios/ButtonHover.mp3").toString());
        Media buttonClick = new Media(Launcher.class.getResource("Audios/ButtonClick.mp3").toString());





        this.musicMap.put("main",mainTheme);
        this.musicMap.put("game", gameTheme);
        this.musicMap.put("load", loadScene);
        this.musicMap.put("click",buttonClick);
        this.musicMap.put("hover", buttonHover);
        this.musicMap.put("kick", ballKick);
        this.musicMap.put("falling", ballFalling);

    }

    public void stop() {
        if(Launcher.bgPlayer != null){
            Launcher.bgPlayer.stop();
        }
    }


    public void playSound(String song){
        stop() ;
        Launcher.bgPlayer = new MediaPlayer(this.musicMap.get(song));
        Launcher.bgPlayer.setAutoPlay(true);
        Launcher.bgPlayer.setCycleCount(100);
        Launcher.bgPlayer.setVolume(1);
    }

    public void playVoice(String song){
        Launcher.voiceOver = new MediaPlayer(this.musicMap.get(song));
        Launcher.voiceOver.setAutoPlay(true);
        Launcher.voiceOver.setCycleCount(1);
        Launcher.voiceOver.setVolume(1);
    }

    public void playEffect(String song){
        Launcher.soundEffect = new MediaPlayer(this.musicMap.get(song));
        Launcher.soundEffect.setAutoPlay(true);
        Launcher.soundEffect.setCycleCount(1);
        Launcher.soundEffect.setVolume(1);
    }

}
