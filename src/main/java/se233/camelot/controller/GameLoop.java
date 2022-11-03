package se233.camelot.controller;

import javafx.scene.input.KeyCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se233.camelot.model.Character;
import se233.camelot.view.Platform;
import se233.camelot.view.Score;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class GameLoop implements Runnable {
    private Platform platform ;
    private int frameRate ;
    private float interval ;
    private boolean running ;
    private int frameFlag ;

    public GameLoop(Platform platform){
        this.platform = platform ;
        this.frameRate = 10 ;
        this.interval = 1000.0f / frameRate ;
        this.running = true ;
        this.frameFlag = 0 ;
    }

    private void update(ArrayList<Character> characters ) {
        for (Character character : characters) {
            if (platform.getKeys().isPressed(character.getLeftKey())) {
                character.setScaleX(-1);
                character.moveLeft();
                character.trace();
            }

            if (platform.getKeys().isPressed(character.getRightKey())) {
                character.setScaleX(1);
                character.moveRight();
                character.trace();
            }

            if (!platform.getKeys().isPressed(character.getLeftKey()) && !platform.getKeys().isPressed(character.getRightKey())) {
                character.stop();
            }

            if(platform.getKeys().isPressed(character.getLeftKey()) || platform.getKeys().isPressed(character.getRightKey())) {
                character.getImageView().tick();
            }

            if (platform.getKeys().isPressed(character.getUpKey())) {
                character.jump();
                character.trace();
                character.getImageView().jump();
            }

            if(platform.getKeys().isPressed(character.getUltimateKey())){
                character.ultimateActive();
            }

            if(platform.getKeys().isPressed(KeyCode.I)){
                platform.getBall().setyVelocity(platform.getBall().getyVelocity() - 2);
            }

            if(platform.getKeys().isPressed(KeyCode.U)){
                platform.getBall().setxVelocity(platform.getBall().getxVelocity() - 2);
            }
            if(platform.getKeys().isPressed(KeyCode.O)){
                platform.getBall().setxVelocity(platform.getBall().getxVelocity() + 2);
            }
        }
    }

    private void updateScore(ArrayList<Score> scoreList , ArrayList<Character> characterList){
        javafx.application.Platform.runLater( () -> {
            for (int i = 0; i < scoreList.size(); i++) {
                scoreList.get(i).setPoint(characterList.get(i).getScore());
            }
        });
    }

    private void chargeUltimateThread(ArrayList<Character> characters){
        javafx.application.Platform.runLater( () -> {
            if(frameFlag % 10 == 0){
                for(Character ch: characters) {
                    ch.chargeUltimate();
                }
            }
        });
    }

    @Override
    public void run() {

        while (running){
            update(Platform.getCharacters());
            updateScore(Platform.getScoreList(), Platform.getCharacters());
            chargeUltimateThread(Platform.getCharacters());
            try {
                Thread.sleep(1000/this.frameRate);
                this.frameFlag += 1  ;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(frameFlag == (platform.MATCHDURATION ) * this.frameRate){
                break;
            }
        }
    }
}
