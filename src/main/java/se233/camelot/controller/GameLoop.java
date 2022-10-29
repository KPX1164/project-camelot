package se233.camelot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se233.camelot.model.Character;
import se233.camelot.view.Platform;

import java.util.ArrayList;


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
            }
        }
    }

    @Override
    public void run() {

        while (running){
            update(Platform.getCharacters());
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
