package se233.camelot.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Timer extends Pane {
    Label time ;
    public Timer(int x , int y){
        time = new Label("0000");
        setTranslateX(x);
        setTranslateY(y);
        time.setFont(Font.font("Verdana", FontWeight.BOLD , 30));
        time.setTextFill(Color.web("#FFF"));
        this.getChildren().addAll(time);
    }

    public void setTime(int timeLeft){
        this.time.setText(Integer.toString(timeLeft));
    }
}
