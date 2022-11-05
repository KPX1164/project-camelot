package se233.camelot.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import se233.camelot.Launcher;

import java.util.HashMap;
import java.util.Map;

public class MusicController {

    private MediaPlayer bgPlayer;
    private MediaPlayer voiceOver;
    private MediaPlayer soundEffect;

    private double bgmVolume ;
    private double effectVolume ;
    private double voiceOverVolume ;

    private Map<String , Media> musicMap = new HashMap<>() ;

    public MusicController() {
        this.bgmVolume = 1.0 ;
        this.effectVolume = 1.0 ;
        this.voiceOverVolume = 1.0 ;

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

        this.bgPlayer = new MediaPlayer(this.musicMap.get("main"));
        this.soundEffect = new MediaPlayer(this.musicMap.get("kick"));
        this.voiceOver = new MediaPlayer(this.musicMap.get("click"));
    }


    public void stop() {
        if(this.bgPlayer != null){
            this.bgPlayer.stop();
        }
    }

    public void stopEffect() {
        if(this.soundEffect != null){
            this.soundEffect.stop();
        }
    }

    public void stopVoice() {
        if(this.voiceOver != null){
            this.voiceOver.stop();
        }
    }

    public void playSound(String song){
        stop() ;
        this.bgPlayer = new MediaPlayer(this.musicMap.get(song));
        this.bgPlayer.setAutoPlay(true);
        this.bgPlayer.setCycleCount(100);
        this.bgPlayer.setVolume(this.bgmVolume);
    }
    public void playVoice(String song){
        stopVoice();
        this.voiceOver = new MediaPlayer(this.musicMap.get(song));
        this.voiceOver.setAutoPlay(true);
        this.voiceOver.setCycleCount(1);
        this.voiceOver.setVolume(this.voiceOverVolume);
    }

    public void playEffect(String song){
        stopEffect();
        this.soundEffect = new MediaPlayer(this.musicMap.get(song));
        this.soundEffect.setAutoPlay(true);
        this.soundEffect.setCycleCount(1);
        this.soundEffect.setVolume(this.effectVolume);
    }

    public void updateAllSoundVolume() {
        this.soundEffect.setVolume(this.effectVolume);
        this.voiceOver.setVolume(this.voiceOverVolume);
        this.bgPlayer.setVolume(this.bgmVolume);
    }

    public void  resetAllSoundVolume() {
        this.bgmVolume = 1 ;
        this.effectVolume = 1 ;
        this.voiceOverVolume = 1;

        this.updateAllSoundVolume();
    }

    public MediaPlayer getBgPlayer() {
        return bgPlayer;
    }

    public MediaPlayer getVoiceOver() {
        return voiceOver;
    }

    public MediaPlayer getSoundEffect() {
        return soundEffect;
    }

    public double getBgmVolume() {
        return bgmVolume;
    }

    public void setBgmVolume(double bgmVolume) {
        this.bgmVolume = bgmVolume;
        this.updateAllSoundVolume();
    }

    public double getEffectVolume() {
        return effectVolume;
    }

    public void setEffectVolume(double effectVolume) {
        this.effectVolume = effectVolume;
        this.updateAllSoundVolume();
    }

    public double getVoiceOverVolume() {
        return voiceOverVolume;
    }

    public void setVoiceOverVolume(double voiceOverVolume) {
        this.voiceOverVolume = voiceOverVolume;
    }
}
