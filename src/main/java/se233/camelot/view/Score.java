package se233.camelot.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import se233.camelot.Launcher;
import se233.camelot.model.Character;
import se233.camelot.model.CharacterType;

public class Score extends Pane {
    private Label point ;
    private int x ;
    private int y ;

    public Score(int x , int y){
        this.x = x ;
        this.y = y ;
        this.setTranslateX(this.x);
        this.setTranslateY(this.y);

        point = new Label("0");


        point.setFont(Font.font("Verdana", FontWeight.BOLD , 30));
        point.setTextFill(Color.web("#FFF"));

        this.getChildren().addAll(point);
    }

    public void update(){

    }
    public void setPoint(int score){
        this.point.setText(Integer.toString(score));
    }
}