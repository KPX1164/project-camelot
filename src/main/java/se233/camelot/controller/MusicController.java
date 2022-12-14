package se233.camelot.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.Launcher;
import se233.camelot.model.Character;

import java.util.HashMap;
import java.util.Map;

public class MusicController {

    private MediaPlayer bgPlayer;
    private MediaPlayer voiceOver;
    private MediaPlayer soundEffect;

    private double bgmVolume ;
    private double effectVolume ;
    private double voiceOverVolume ;
    private Logger logger = LoggerFactory.getLogger(MusicController.class);

    private Map<String , Media> musicMap = new HashMap<>() ;

    public MusicController() {
        this.bgmVolume = 1.0 ;
        this.effectVolume = 1.0 ;
        this.voiceOverVolume = 1.0 ;

        try{
            Media mainTheme = new Media(Launcher.class.getResource("Audios/CosmosintheLostbelt.mp3").toString());
            Media gameTheme = new Media(Launcher.class.getResource("Audios/Coronation.mp3").toString());
            Media loadScene = new Media(Launcher.class.getResource("Audios/LoadSound.mp3").toString());
            Media ballFalling = new Media(Launcher.class.getResource("Audios/BallFalling.mp3").toString());
            Media ballKick = new Media(Launcher.class.getResource("Audios/BallKick.mp3").toString());
            Media buttonHover = new Media(Launcher.class.getResource("Audios/ButtonHover.mp3").toString());
            Media buttonClick = new Media(Launcher.class.getResource("Audios/ButtonClick.mp3").toString());
            Media countDown = new Media(Launcher.class.getResource("Audios/321Done.mp3").toString());
            Media audience = new Media(Launcher.class.getResource("Audios/Audience.mp3").toString());
            Media voiceTesting = new Media(Launcher.class.getResource("Audios/VoiceTest.mp3").toString());
            Media goalCheer = new Media(Launcher.class.getResource("Audios/Goal.mp3").toString());
            Media playButton = new Media(Launcher.class.getResource("Audios/AccessibilityKits/PlayButton.mp3").toString());

            this.musicMap.put("main",mainTheme);
            this.musicMap.put("game", gameTheme);
            this.musicMap.put("load", loadScene);
            this.musicMap.put("click",buttonClick);
            this.musicMap.put("hover", buttonHover);
            this.musicMap.put("kick", ballKick);
            this.musicMap.put("falling", ballFalling);
            this.musicMap.put("countDown", countDown);
            this.musicMap.put("audience", audience);
            this.musicMap.put("voiceTest", voiceTesting);
            this.musicMap.put("cheer", goalCheer);
            this.musicMap.put("play", playButton);


        } catch (NullPointerException ex){
            logger.warn(ex.getMessage());
        }

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

    public void playSound(String song) {
        try{
            stop() ;
            this.bgPlayer = new MediaPlayer(this.musicMap.get(song));
            this.bgPlayer.setAutoPlay(true);
            this.bgPlayer.setCycleCount(100);
            this.bgPlayer.setVolume(this.bgmVolume);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }

    }
    public void playVoice(String song){
        try{
            stopVoice();
            this.voiceOver = new MediaPlayer(this.musicMap.get(song));
            this.voiceOver.setAutoPlay(true);
            this.voiceOver.setCycleCount(1);
            this.voiceOver.setVolume(this.voiceOverVolume);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }

    }

    public void playEffect(String song){
        try{
            stopEffect();
            this.soundEffect = new MediaPlayer(this.musicMap.get(song));
            this.soundEffect.setAutoPlay(true);
            this.soundEffect.setCycleCount(1);
            this.soundEffect.setVolume(this.effectVolume);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    public void safety(){
        this.soundEffect.setVolume(0.3);
        this.voiceOver.setVolume(0.3);
        this.bgPlayer.setVolume(0.3);

        this.updateAllSoundVolume();

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
