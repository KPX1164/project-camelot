package se233.camelot.model;

import javafx.scene.image.Image;
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
    private AnimatedSprite  imageView;
    private ImageView ultimateAura ;

    // Attack state
//    private  boolean isAttack = false ;
//    private KeyCode attackKey ;

    // Ultimate state
    private int ultimateCharge ;
    private boolean isInUltimate = false ;
    private KeyCode ultimateKey ;

    private Logger logger = LogManager.getLogger();
    private Image characterImg;

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
//        this.attackKey = attackKey;
        this.ultimateKey = ultiKey ;


        ultimateAura = new ImageView(new Image(Launcher.class.getResource("assets/UltiEffect.png").toString()));
        ultimateAura.setFitWidth(128);
        ultimateAura.setFitHeight(128);
        ultimateAura.setVisible(false);

        //get image
        if (this.characterType.equals(CharacterType.saber)){
            this.characterImg = new Image(Launcher.class.getResourceAsStream("assets/saber.png"));
//            this.getChildren().addAll(new ImageView(characterImg));
            this.imageView = new AnimatedSprite(characterImg,8,8,1,offsetX,offsetY,65 ,60);

        }else if (characterType.equals(CharacterType.megaMan)) {
            this.characterImg = new Image(Launcher.class.getResourceAsStream("assets/megaMan.png"));
            this.imageView = new AnimatedSprite(characterImg, 8, 8, 1, offsetX, offsetY, 65, 60);
        }else{
            this.characterImg = new Image(Launcher.class.getResource("assets/zeroMan.png").toString());
            this.imageView = new AnimatedSprite(characterImg, 8, 8, 1, offsetX, offsetY, 65, 80);
            CHARACTER_WIDTH = 138;
            CHARACTER_HEIGHT = 158;
        }


        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.getChildren().addAll(this.imageView, this.ultimateAura);

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
            imageView.landing();
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
            c.setX((int)(c.getX() - this.xVelocity));
            stop();
        } else if (isMovingRight) {
            x = this.x - c.getxVelocity();
            c.setX((int)(c.getX() + this.xVelocity));
            stop();
        }
    }

    public void ultimateActive(){
        if(this.ultimateCharge == 100 && !this.isInUltimate){
            this.isInUltimate = true ;
            this.ultimateAura.setVisible(true);
            this.ultimateCharge = 0 ;
        }else{
            this.ultimateCharge = 100 ;
        }
    }
    public void useUltimateSkill() {
        if(this.isInUltimate){
            this.isInUltimate = false ;
            this.ultimateAura.setVisible(false);
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

    public AnimatedSprite getImageView() {
        return imageView;
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

    public boolean isIdle() {
        return !isMovingLeft && !isMovingRight ;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean isCanJump() {
        return canJump;
    }

//    public boolean isAttack() {
//        return isAttack;
//    }
//
//    public KeyCode getAttackKey() {
//        return attackKey;
//    }

    public boolean isInUltimate() {
        return isInUltimate;
    }

    public KeyCode getUltimateKey() {
        return ultimateKey;
    }

    public int getUltimateCharge() {
        return ultimateCharge;
    }

    public void setUltimateCharge(int ultimateCharge) {
        this.ultimateCharge = ultimateCharge;
    }

    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}", x, y, xVelocity, yVelocity);
    }


}
