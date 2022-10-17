package se233.camelot.controller;

import se233.camelot.model.Character;
import se233.camelot.view.Platform;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class DrawingLoop implements Runnable {
    private Platform platform;
    private int frameRate ;
    private float interval ;
    private boolean running ;
    private int frameFlag ;

    public DrawingLoop(Platform platform){

        this.platform = platform ;
        this.frameRate = 30 ;
        this.interval = 1000.0f / frameRate ;
        this.running = true ;
        this.frameFlag = 0 ;
    }

    private void checkDrawCollisions(ArrayList<Character> characters){
        for(Character character : characters){
            character.checkReachGameWall();
            character.checkReachHighest();
            character.checkReachFloor();
        }
    }
    private void paint(ArrayList<Character> characters){
        for(Character character : characters){
            character.repaint();
        }
    }

    @Override
    public void run() {
        while (running) {
            checkDrawCollisions(platform.getCharacters());
            paint(platform.getCharacters());
            try {
                Thread.sleep(1000/this.frameRate);
                this.frameFlag += 1 ;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(frameFlag == (platform.MATCHDURATION + 1) * this.frameRate){
                break;
            }
        }
    }


}

