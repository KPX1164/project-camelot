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

    public DrawingLoop(Platform platform){

        this.platform = platform ;
        this.frameRate = 30 ;
        this.interval = 1000.0f / frameRate ;
        this.running = true ;
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
            float time = System.currentTimeMillis();
            checkDrawCollisions(Platform.getCharacters());
            paint(Platform.getCharacters());

            time = System.currentTimeMillis() - time;
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

