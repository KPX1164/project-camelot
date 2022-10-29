package se233.camelot.model;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se233.camelot.Launcher;
import se233.camelot.view.Platform;


public class Character extends Pane {

    private CharacterType characterType ;
    public int CHARACTER_WIDTH = 128;
    public int CHARACTER_HEIGHT = 128;

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
    int xAcceleration = 2;
    int yAcceleration = 1;
    int xMaxVelocity = 11;
    int yMaxVelocity = 21;

    //Animation state
    private ImageView imageView;

    // Attack state
    private  boolean isAttack = false ;
    private KeyCode attackKey ;

    // Ultimate state
    private int ultimateCharge ;
    private boolean isInUltimate = false ;
    private KeyCode ultimateKey ;

    private Logger logger = LogManager.getLogger();
    public Character(int x, int y,int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey, CharacterType characterType, KeyCode attackKey , KeyCode ultiKey) {
        this.characterType = characterType ;

        this.startX = x ;
        this.startY = y ;
        this.offSetX = offsetX ;
        this.offSetY = offsetY ;

        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);

        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.attackKey = attackKey;
        this.ultimateKey = ultiKey ;


        //get image
        this.imageView = new ImageView(Launcher.class.getResource("assets/saberTest.png").toString()) ;
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.getChildren().addAll(this.imageView);

    }

    public void repaint() {
        moveY();
        moveX();
    }
    public void jump() {
        if (canJump) {
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }

    public void checkReachHighest() {
        if (isJumping && yVelocity <= 0) {
            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }

    public void checkReachFloor() {
        if (isFalling && y >= Platform.GROUND - CHARACTER_HEIGHT) {
            isFalling = false;
            canJump = true;
            yVelocity = 0;
            this.y = Platform.GROUND - CHARACTER_HEIGHT ;
        }
    }

    public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
        } else if (x + this.getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int) (this.getWidth());
        }
    }

    public void moveLeft() {
        this.isMovingLeft = true;
        this.isMovingRight = false;
    }

    public void moveRight() {
        this.isMovingLeft = false;
        this.isMovingRight = true;
    }

    public void stop() {
        this.isMovingLeft = false;
        this.isMovingRight = false;
    }
    public void moveX() {
        setTranslateX(x);

        if (isMovingLeft) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xMaxVelocity + xAcceleration;
            x = x - xVelocity;
        }
        if (isMovingRight) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xMaxVelocity + xAcceleration;
            x = x + xVelocity;
        }

    }

    public void moveY() {
        setTranslateY(y);
        if (isFalling) {
            yVelocity = yVelocity >= yMaxVelocity ? yMaxVelocity : yMaxVelocity + yAcceleration;
            y = y + yVelocity;
        } else if (isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity - yAcceleration;
            y = y - yVelocity;
        }
    }

    public void collided(Character c) {

        if (isMovingLeft) {
            x = this.x + c.getxVelocity();
            c.setX((int)(c.getX() - this.xVelocity*(1.5)));
            stop();
        } else if (isMovingRight) {
            x = this.x - c.getxVelocity();
            c.setX((int)(c.getX() + this.xVelocity*(1.5)));
            stop();
        }
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public boolean isMovingLeft() {
        return isMovingLeft;
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

    public int getxVelocity() {
        return xVelocity;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
