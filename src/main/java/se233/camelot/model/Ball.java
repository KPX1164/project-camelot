package se233.camelot.model;

import javafx.scene.image.Image;
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

    private double yVelocity = 3;
    private double xVelocity = 0;
    private double gravity =  0.35 ;

    private int MAX_XVELOCITY = 40 ;
    private double rotate = 1;

    private ImageView imageView ;
    public final int BALL_HEIGHT = 32 ;
    public final int BALL_WIDTH = 32 ;
    public Ball(int x , int y) {

        this.x = x ;
        this.y = y ;
        this.startX = x ;
        this.startY = y ;

        this.setTranslateX(x);
        this.setTranslateY(y);

        this.imageView = new ImageView(Launcher.class.getResource("assets/ball.png").toString());
        this.imageView.setFitHeight(BALL_HEIGHT);
        this.imageView.setFitWidth(BALL_WIDTH);
        this.getChildren().addAll(this.imageView);

    }

    public void repaint() {
        moveY();
        moveX();
        this.ballRolling();
    }

    public void checkReachFloor() {
        if (y >= Platform.GROUND - BALL_HEIGHT) {
            if( Math.floor(yVelocity) >= 3){
                yVelocity = - yVelocity * 0.55 ;
            }else{
//                yVelocity = 0;
                this.y = Platform.GROUND - BALL_HEIGHT ;
            }

        }
    }
    public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
            ballBouncing();
        } else if (x + this.getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int) (this.getWidth());
            ballBouncing();
        }

    }

    public void collided(Character c) {
        if(c.isMovingRight()){
            //if ulti
            if(c.isInUltimate()){
                c.useUltimateSkill();
                this.yVelocity = c.getxVelocity() * -0.7 ;
                this.xVelocity += c.getxVelocity() * 5.1 ;
            }else{
                this.yVelocity = c.getxVelocity() * -0.7 ;
                this.xVelocity += c.getxVelocity() * 1.1 ;
            }

        }

        if(c.isMovingLeft()){
            //if ulti
            if(c.isInUltimate()) {
                c.useUltimateSkill();
                this.yVelocity = c.getxVelocity() * -0.7;
                this.xVelocity -= c.getxVelocity() * 5.1;
            }else{
                this.yVelocity = c.getxVelocity() * -0.7 ;
                this.xVelocity -= c.getxVelocity() * 1.1 ;
            }

        }

        if(c.isIdle()){
            this.xVelocity = -0.2 * (this.xVelocity) ;
            c.setX((int) (c.getX() + Math.round(xVelocity * -2))) ;
//            this.xVelocity = 0 ;
        }

    }

    public void moveY() {
        setTranslateY(y);
        y = (int)(y + yVelocity);
        yVelocity += gravity ;
    }

    public void moveX() {
//        this.trace();
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
//        double ballForce = Math.abs(this.xVelocity) + Math.abs(this.yVelocity) ;
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
        this.trace();
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


    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{} rotate:{}",x,y,xVelocity,yVelocity,rotate);
    }

}
