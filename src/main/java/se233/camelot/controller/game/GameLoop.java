package se233.camelot.controller.game;

import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.model.Character;
import se233.camelot.view.Platform;
import se233.camelot.view.Score;
import se233.camelot.view.UltimateBar;

import java.util.ArrayList;


public class GameLoop implements Runnable {
    private Platform platform ;
    private int frameRate ;
    private float interval ;
    private boolean running ;
    private int frameFlag ;

    Logger logger = LoggerFactory.getLogger(GameLoop.class);

    public GameLoop(Platform platform){
        this.platform = platform ;
        this.frameRate = 10 ;
        this.interval = 1000.0f / frameRate ;
        this.running = true ;
        this.frameFlag = 0 ;
    }

    private void update(ArrayList<Character> characters )  throws Exception {
        for (int i = 0 ; i < characters.size(); i++) {
            Character character = characters.get(i);

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

            if(platform.getKeys().isPressed(character.getAttackKey())){
                character.attack();
                character.getImageView().attack();
                platform.getKeys().remove(character.getAttackKey());
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

    private void updateUltimate(UltimateBar ulti) {
        javafx.application.Platform.runLater( () -> {
            try{
                ulti.getP1().update();
                ulti.getP2().update();
            }catch (Exception e){
                logger.error(e.getMessage());
            }

        });
    }

    private void chargeUltimateThread(ArrayList<Character> characters) throws Exception{
        javafx.application.Platform.runLater( () -> {
            try{
                if(frameFlag % 10 == 0){
                    for(Character ch: characters) {
                        ch.chargeUltimate();
                    }
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void run() {
        while (running){
            try {
                update(Platform.getCharacters());
                chargeUltimateThread(Platform.getCharacters());
                updateScore(Platform.getScoreList(), Platform.getCharacters());
                updateUltimate(Platform.getUltimateBar());
                Thread.sleep(1000 / this.frameRate);
                this.frameFlag += 1;
            }catch (InterruptedException ex){
                logger.warn(ex.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
//                Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry bro there is a critical error please restart the program");
//                alert.showAndWait();
//                System.exit(0);
            }
            if(frameFlag == (Platform.MATCHDURATION) * this.frameRate){
                break;
            }
        }
    }
}
