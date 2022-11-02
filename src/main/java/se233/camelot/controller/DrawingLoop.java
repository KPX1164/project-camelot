package se233.camelot.controller;

import se233.camelot.model.Ball;
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

    private void checkDrawCollisions(ArrayList<Character> characters, Ball ball){
        for(Character character : characters){
            character.checkReachGameWall();
            character.checkReachHighest();
            character.checkReachFloor();
        }
        ball.checkReachFloor();
        ball.checkReachGameWall();
        characters.forEach( character ->  {
            if(ball.getBoundsInParent().intersects(character.getBoundsInParent())){
                ball.collided(character);
            }
        });

        for (Character cA : characters){
            for(Character cB: characters){
                if(cA != cB){

                    if(cA.getBoundsInParent().intersects(cB.getBoundsInParent())){
                        cA.collided(cB);
                        cB.collided(cA);
                        return;
                    }
                }
            }
        }
    }
    private void paint(ArrayList<Character> characters, Ball ball){
        for(Character character : characters){
            character.repaint();
        }
        ball.repaint();
    }

    @Override
    public void run() {
        while (running) {
            checkDrawCollisions(platform.getCharacters(),platform.getBall());
            paint(platform.getCharacters(),platform.getBall());
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

