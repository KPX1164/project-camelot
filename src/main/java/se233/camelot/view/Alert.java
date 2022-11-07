package se233.camelot.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import se233.camelot.Launcher;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Alert extends Pane {
    private Image goalCutSceneImage, timeupCutscene ;
    private ImageView alertImageView ;

    public Alert(int y , int x ) throws NullPointerException {

        this.goalCutSceneImage = new Image(Launcher.class.getResourceAsStream("Images/goal.png"));
        this.timeupCutscene = new Image(Launcher.class.getResourceAsStream("Images/timeup.png"));

        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setVisible(false);
        this.alertImageView = new ImageView(goalCutSceneImage);

        alertImageView.setFitHeight(150);
        alertImageView.setFitWidth(505);
        this.getChildren().add(alertImageView);
    }

    public void goalTrigger(){
        this.alertImageView.setImage(goalCutSceneImage);
        this.setTranslateY(150);
        this.setTranslateX(380);
        this.setVisible(true);
    }

    public void timeupTrigger(){
        this.alertImageView.setImage(timeupCutscene);
        this.setVisible(true);
    }

    public void hide() {
        this.setVisible(false);
    }
}
