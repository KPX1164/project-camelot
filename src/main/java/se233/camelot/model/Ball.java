package se233.camelot.model;

import javafx.scene.image.ImageView;

public class Ball {
    private int startX , startY;
    int x , y ;

    int yVelocity = 0;
    int xVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 5;
    int yMaxVelocity = 10;

    private ImageView imageView ;

    public Ball(int x , int y) {

        this.x = x ;
        this.y = y ;
        this.startX = x ;
        this.startY = y ;

    }

}
