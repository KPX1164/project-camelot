package se233.camelot.model;


import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.Launcher;
import se233.camelot.view.Platform;

public class Goal extends Pane {

    private Logger logger = LoggerFactory.getLogger(Goal.class);
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

        try{
            if(owner.equals("Player1")){
                this.imageView = new ImageView(Launcher.class.getResource("assets/leftGoal.png").toString());
            }else{
                this.imageView = new ImageView(Launcher.class.getResource("assets/rightGoal.png").toString());
            }
        }catch (NullPointerException ex){
            logger.error(ex.getMessage());
        }

        this.imageView.setFitHeight(HEIGHT);
        this.imageView.setFitWidth(WIDTH);
        this.getChildren().addAll(this.imageView);
    }

    public void intersect(Ball ball) {

        // bouncing on top frame
        if(ball.getY() >= 400 && ball.getY() <= 420 ){
            if(!ball.isFalling()){
                ball.setyVelocity(ball.getyVelocity() * -1);
            }
            if(owner.equals("Player1")){
                ball.setxVelocity(2);
            }else{
                ball.setxVelocity(-2);
            }
        }

        // bouncing the goal frame from top front
        if(owner.equals("Player1")){
            if(ball.getX() <= this.WIDTH){
                if(ball.getY() < 400 && ball.getY() >= 395 ){
                    ball.setxVelocity(2);
                    ball.setyVelocity(0);
                }
            }
        }else{
            if(ball.getX() >= Platform.WIDTH - this.WIDTH){
                if(ball.getY() < 400 && ball.getY() >= 395 ){
                    ball.setxVelocity(-2);
                    ball.setyVelocity(0);
                }
            }
        }

        int goalHeight = Platform.GROUND - this.HEIGHT ;
        // ball coming inside goal
        if(ball.getY() > goalHeight && ball.getY() < Platform.HEIGHT){
            if(owner.equals("Player1")){
                if(ball.getX() < 45){
                    logger.info("Player 2 got point");
//                    Platform.getCutScene().goalTrigger();
                    Platform.getAlertPopup().goalTrigger();
                    Platform.getCharacters().get(1).addScore() ;
                    Launcher.musicController.playEffect("cheer");
                    Platform.getCharacters().get(0).chargeUltimate();

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Platform.getAlertPopup().hide();
                    Platform.respawn() ;
                }
            }else{
                if(ball.getX() > Platform.WIDTH - this.WIDTH + 5){
                    logger.info("Player 1 got point");
//                    Platform.getCutScene().goalTrigger();
                    Platform.getAlertPopup().goalTrigger();
                    Platform.getCharacters().get(0).addScore() ;
                    Launcher.musicController.playEffect("cheer");
                    Platform.getCharacters().get(1).chargeUltimate();

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Platform.getAlertPopup().hide();
                    Platform.respawn() ;
                }
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
