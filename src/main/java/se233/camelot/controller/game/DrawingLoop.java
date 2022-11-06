package se233.camelot.controller.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.Launcher;
import se233.camelot.model.Ball;
import se233.camelot.model.Character;
import se233.camelot.model.Goal;
import se233.camelot.view.CharacterIcon;
import se233.camelot.view.Platform;

import java.util.ArrayList;

public class DrawingLoop implements Runnable {
    private Platform platform;
    private int frameRate ;
    private float interval ;
    private boolean running ;
    private int frameFlag ;
    private Logger logger = LoggerFactory.getLogger(DrawingLoop.class);

    public DrawingLoop(Platform platform){

        this.platform = platform ;
        this.frameRate = 30 ;
        this.interval = 1000.0f / frameRate ;
        this.running = true ;
        this.frameFlag = 0 ;
    }

    private void checkDrawCollisions(ArrayList<Character> characters, Ball ball, ArrayList<Goal> goalList) throws Exception{

        ball.checkReachFloor();
        ball.checkReachGameWall();

        for(Character character : characters){
            character.checkReachGameWall();
            character.checkReachHighest();
            character.checkReachFloor();

            if(ball.getBoundsInParent().intersects(character.getBoundsInParent())){
                Launcher.musicController.playEffect("kick");
                try {
                    ball.collided(character);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            for(Character cB: characters){
                Character cA = character ;
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
            try {
                paintCharacterIcon(platform.getCharacterIcons());
                checkDrawCollisions(platform.getCharacters(), platform.getBall(), platform.getGoalList());
                paint(platform.getCharacters(), platform.getBall());

                Thread.sleep(1000 / this.frameRate);
                this.frameFlag += 1;
            }catch (InterruptedException ex){
                logger.warn(ex.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
//                System.exit(0);
            }

            if(frameFlag == (Platform.MATCHDURATION) * this.frameRate){
                break;
            }
        }
    }


}

