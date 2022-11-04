package se233.camelot.model;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se233.camelot.Launcher;
import se233.camelot.view.Platform;

public class Ball extends Pane {
    private Logger logger = LogManager.getLogger();
    private int startX , startY;
    int x , y ;

    private double yVelocity = 0;
    private double xVelocity = 0;
    private double gravity =  0.35 ;

    private int MAX_XVELOCITY = 40 ;
    private double rotate = 1;

    private ImageView imageView ;
    public final int BALL_HEIGHT = 32 ;
    public final int BALL_WIDTH = 32 ;
    private boolean stop = false ;
    public Ball(int x , int y) {
        this.x = x ;
        this.y = y ;
        this.startX = x ;
        this.startY = y ;
        this.stop = false ;
        this.setTranslateX(x);
        this.setTranslateY(y);

        this.imageView = new ImageView(Launcher.class.getResource("assets/ball.png").toString());
        this.imageView.setFitHeight(BALL_HEIGHT);
        this.imageView.setFitWidth(BALL_WIDTH);
        this.getChildren().addAll(this.imageView);
    }

    public void repaint() {
        if(stop) return;
        moveY();
        moveX();
        this.ballRolling();
    }

    public void checkReachFloor() {
        if (y >= Platform.GROUND - BALL_HEIGHT) {
            if( Math.floor(yVelocity) >= 3){
                yVelocity = - yVelocity * 0.55 ;
            }else{
                yVelocity = (int)(yVelocity);
                this.rotate = 0 ;
                this.y = Platform.GROUND - BALL_HEIGHT ;
            }
        }
    }
    public void checkReachGameWall() {
        if (x <= -30) {
            x = -30;
            ballBouncing();
        } else if (x + this.getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int) (this.getWidth());
            ballBouncing();
        }

    }

    public void collided(Character c) {

        if(c.isInUltimate()){
            if(c.getHeadingDirection().equals(Direction.right)){
                this.setX(c.getX() + c.CHARACTER_WIDTH + 20);
                c.useUltimateSkill();
                this.yVelocity = c.getxVelocity() * -0.3 ;
                this.xVelocity += 500 ;
            }

            if(c.getHeadingDirection().equals(Direction.left)){
                this.setX(c.getX()- c.CHARACTER_WIDTH - 20);
                c.useUltimateSkill();
                this.yVelocity = c.getxVelocity() * -0.3;
                this.xVelocity -= 500;
            }
            return;
        }

        if(c.isAttack()){
            if(c.getHeadingDirection().equals(Direction.right)){
                this.setX(c.getX() + c.CHARACTER_WIDTH + 10);
                this.yVelocity = c.getxVelocity() * -0.7 ;
                this.xVelocity += 30 ;
            }

            if(c.getHeadingDirection().equals(Direction.left)){
                this.setX(c.getX()- c.CHARACTER_WIDTH - 10);
                this.yVelocity = c.getxVelocity() * -0.7;
                this.xVelocity -= 30;
            }

            c.setIsAttack(false) ;
            return;
        }

        if(c.isMovingRight()){
            this.yVelocity = c.getxVelocity() * -0.3 ;
            this.xVelocity += c.getxVelocity() * 1.1 ;
        }

        if(c.isMovingLeft()){
            this.yVelocity = c.getxVelocity() * -0.3 ;
            this.xVelocity -= c.getxVelocity() * 1.1 ;
        }

        if(c.isIdle() || c.getHeadingDirection().equals(Direction.idle)){
            this.xVelocity = -0.2 * (this.xVelocity) ;
            c.setX((int) (c.getX() + Math.round(xVelocity * -2))) ;
        }
    }

    public void moveY() {
        setTranslateY(y);
        y = (int)(y + yVelocity);
        yVelocity += gravity ;
    }

    public void moveX() {
        setTranslateX(x);
        if(xVelocity > 0){
            xVelocity = xVelocity >= MAX_XVELOCITY ? MAX_XVELOCITY : xVelocity ;
        }else{
            xVelocity = xVelocity <= -MAX_XVELOCITY ? -MAX_XVELOCITY : xVelocity ;
        }
        x = (int)(x + xVelocity) ;
        ballAirResistance();
    }

    public void ballRolling() {
        if(this.xVelocity != 0 ){
            if(xVelocity > 0){
                if(Math.abs(xVelocity) < 3){
                    this.rotate = 4 ;
                }else{
                    this.rotate = 10 ;
                }
            }else if(xVelocity < 0){
                if(Math.abs(xVelocity) < 3){
                    this.rotate = -4 ;
                }else{
                    this.rotate = -10 ;
                }
            }
        }else{
            if(Math.abs(yVelocity) > 0.5){
                this.rotate = yVelocity ;
            }else{
                this.rotate = 0 ;
            }
        }
        this.setRotate( this.getRotate() + rotate);
    }

    public void ballAirResistance() {
        if( (Math.abs(this.xVelocity)) >= 1){
            this.xVelocity = 0.99 * (this.xVelocity) ;
        }else{
            this.xVelocity = 0 ;
        }
    }

    public void ballBouncing() {
        if( (Math.abs(this.xVelocity)) >= 1.5){
            this.xVelocity = -0.8 * (this.xVelocity) ;
        }else{
            this.xVelocity = 0 ;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public boolean isFalling() {
        if(this.xVelocity > 0.35) return true;
        return  false ;
    }

    public void respawn() {
        this.x = startX ;
        this.y = startY ;
        this.xVelocity = 0 ;
        this.yVelocity = 0 ;
    }

    public void freeze() {
        this.stop = true ;
        this.setxVelocity(0);
        this.setyVelocity(0);
        this.setTranslateX(this.x);
        this.setTranslateY(this.y);
    }
    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{} rotate:{}",x,y,xVelocity,yVelocity,rotate);
    }

}
