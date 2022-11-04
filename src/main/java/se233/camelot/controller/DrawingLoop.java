package se233.camelot.controller;

import se233.camelot.model.Ball;
import se233.camelot.model.Character;
import se233.camelot.model.Goal;
import se233.camelot.view.CharacterIcon;
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

    private void checkDrawCollisions(ArrayList<Character> characters, Ball ball, ArrayList<Goal> goalList){
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

        for( Goal goal : goalList){
            for (Character character : characters){
                if(goal.getBoundsInParent().intersects(character.getBoundsInParent())){
                    goal.intersect(character);
                }
            }

            if(goal.getBoundsInParent().intersects(ball.getBoundsInParent())){
                goal.intersect(ball);
            }
        }
    }
    private void paint(ArrayList<Character> characters, Ball ball){
        for(Character character : characters){
            character.repaint();
        }
        ball.repaint();
    }

    private void paintCharacterIcon(ArrayList<CharacterIcon> characterIcons) {
        javafx.application.Platform.runLater( () -> {
            characterIcons.forEach( characterIcon -> characterIcon.update() );
        });
    }

    @Override
    public void run() {
        while (running) {
            checkDrawCollisions(platform.getCharacters(),platform.getBall(), platform.getGoalList());
            paint(platform.getCharacters(),platform.getBall());
            paintCharacterIcon(platform.getCharacterIcons());
            try{
                Thread.sleep(1000/this.frameRate);
                this.frameFlag += 1 ;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(frameFlag == (Platform.MATCHDURATION) * this.frameRate){
                break;
            }
        }
    }


}

