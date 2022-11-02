package se233.camelot.model;


import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import se233.camelot.Launcher;
import se233.camelot.view.Platform;

public class Goal extends Pane {
    private int x ;
    private int y ;

    private int WIDTH = 100 ;
    private int HEIGHT = 200 ;
    private String owner ;

    private ImageView imageView ;
    public Goal(int x , int y, String owner){
        this.x = x ;
        this.y = y ;
        this.owner = owner ;
        this.setTranslateX(x);
        this.setTranslateY(y);

        if(owner.equals("Player1")){
            this.imageView = new ImageView(Launcher.class.getResource("assets/leftGoal.png").toString());
        }else{
            this.imageView = new ImageView(Launcher.class.getResource("assets/rightGoal.png").toString());
        }

        this.imageView.setFitHeight(HEIGHT);
        this.imageView.setFitWidth(WIDTH);
        this.getChildren().addAll(this.imageView);
    }

    public void intersect(Ball ball) {

        if(y < Platform.GROUND - ball.BALL_HEIGHT){
            if(ball.getY() < Platform.GROUND-this.HEIGHT ){
                ball.setY(Platform.GROUND-this.HEIGHT);
//                ball.setxVelocity(ball.getxVelocity()*-1);
//                ball.setyVelocity(ball.getyVelocity()*-1);
            }
        }


    }

    public void intersect(Character character){
        if(character.getY() < Platform.GROUND-this.HEIGHT ){
            if(character.isJumping){
                character.setY(Platform.GROUND-this.HEIGHT);
                character.isFalling = true ;
            }else{
                character.setY(Platform.GROUND-this.HEIGHT-character.CHARACTER_HEIGHT);
            }

        }
    }


}
