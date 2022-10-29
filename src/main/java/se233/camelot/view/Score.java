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
    Label point ;
    Image characterImg ;
    ImageView characterIcon ;


    public Score(int x , int y, Character character){
        point = new Label("0");
        if(character.getCharacterType().equals(CharacterType.megaMan)){
            this.characterImg = new Image(Launcher.class.getResourceAsStream("Images/character1.png"));
        }else if(character.getCharacterType().equals(CharacterType.zeroMan)){
            this.characterImg = new Image(Launcher.class.getResourceAsStream("Images/character2.png"));
        }else{
            this.characterImg = new Image(Launcher.class.getResourceAsStream("Images/character3.png"));
        }

        this.characterIcon = new ImageView(characterImg);
        setTranslateX(x);
        setTranslateY(y);
        point.setFont(Font.font("Verdana", FontWeight.BOLD , 30));
        point.setTextFill(Color.web("#FFF"));

        VBox box = new VBox();
        box.getChildren().addAll(point , characterIcon);

        this.getChildren().addAll(box);
    }

    public void setPoint(int score){
        this.point.setText(Integer.toString(score));
    }
}