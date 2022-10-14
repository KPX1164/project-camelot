package se233.camelot.controller;

import se233.camelot.model.Character;
import se233.camelot.view.Platform;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class GameLoop implements Runnable {
    private Platform platform ;
    private int frameRate ;
    private float interval ;
    private boolean running ;

    public GameLoop(Platform platform){
        this.platform = platform ;
        this.frameRate = 10 ;
        this.interval = 1000.0f / frameRate ;
        this.running = true ;
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

//            if(platform.getKeys().isPressed(character.getLeftKey()) || platform.getKeys().isPressed(character.getRightKey())) {
//                character.getImageView().tick();
//            }

            if (platform.getKeys().isPressed(character.getUpKey())) {
                character.jump();
            }
        }
    }

    @Override
    public void run() {

        while (running){
            float time = System.currentTimeMillis();
            update(Platform.getCharacters());


            time = System.currentTimeMillis() - time ;
            if(time < interval){
                try{
                    Thread.sleep((long)(interval-time));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }else{
                try{
                    Thread.sleep((long)(interval - (interval%time)));
                }catch (InterruptedException e){
                    e.printStackTrace();

                }
            }

        }
    }
}
