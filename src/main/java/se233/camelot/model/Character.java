package se233.camelot.model;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class Character extends Pane {

    private CharacterType characterType ;
    private int ultimateCharge ;

    //Normal state
    private int x, y;
    private int startX , startY;
    private int offSetX , offSetY ;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;
    boolean isMovingRight = false;
    boolean isMovingLeft = false;
    private int score = 0 ;

    //Moving state
    int yVelocity = 0;
    int xVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 5;
    int yMaxVelocity = 10;

    //Animation state
    private AnimatedSprite imageView;

    private Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass()) ;
    public Character(int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey, CharacterType characterType) {

        this.startX = x ;
        this.startY = y ;
        this.offSetX = offsetX ;
        this.offSetY = offsetY ;

        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);

        this.characterType = characterType ;
        //get image


        this.getChildren().addAll(this.imageView);

    }


    public int getOffSetX() {
        return offSetX;
    }

    public int getOffSetY() {
        return offSetY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
    public int getScore() {
        return score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }
    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}", x, y, xVelocity, yVelocity);
    }


}
