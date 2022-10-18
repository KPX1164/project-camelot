package se233.camelot.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import se233.camelot.Launcher;
import se233.camelot.view.Platform;

public class Ball extends Pane {
    private int startX , startY;
    int x , y ;

    private double yVelocity = 3;
    private double xVelocity = 0;
    private double gravity =  0.25 ;

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
    }

    public void checkReachFloor() {
        if (y >= Platform.GROUND - BALL_HEIGHT) {
            if(Math.floor(yVelocity) >= 1){
                yVelocity = - yVelocity * 0.65 ;
            }else{
                yVelocity = 0;
                this.y = Platform.GROUND - BALL_HEIGHT ;
                rotate = 0 ;
            }

        }
    }
    public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
        } else if (x + this.getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int) (this.getWidth());
        }
    }

    public void collided(Character c) {
//        if(c.isMovingRight()){
//            x = this.x + c.getxVelocity()*100;
//        }
//
//        if(c.isMovingLeft()){
//            x = this.x - c.getxVelocity()*100;
//        }

    }

    public void moveY() {
        setTranslateY(y);
        this.setRotate(this.getRotate() + rotate);
        y = (int)(y + yVelocity);
        yVelocity += gravity ;
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


}
