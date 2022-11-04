package se233.camelot.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import se233.camelot.Launcher;
import se233.camelot.model.Character;
import se233.camelot.model.CharacterType;
import se233.camelot.model.Direction;

import java.util.HashMap;
import java.util.Map;

public class CutScene extends Pane {
    private ImageView cutSceneImage ;
    private Map<CharacterType , Image> charaImageMap = new HashMap<>();
    private Image goalCutSceneImage ;

    public CutScene(int x , int y ) {
        this.goalCutSceneImage = new Image(Launcher.class.getResourceAsStream("Images/goal.jpg"));
        loadImage();
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setVisible(false);

        cutSceneImage.setFitWidth(512);
        cutSceneImage.setFitHeight(512);
        this.getChildren().add(cutSceneImage);
    }

    public void goalTrigger(){
        this.cutSceneImage.setImage(goalCutSceneImage);
        this.setTranslateY(128);
        this.setTranslateX(624);
        cutSceneImage.setFitHeight(512);
        cutSceneImage.setFitWidth(512);
        this.setVisible(true);
    }

    public void trigger(Character character) {
        this.cutSceneImage.setImage(charaImageMap.get(character.getCharacterType()));

        this.setTranslateY(character.getY()-288);

        if(character.getHeadingDirection().equals(Direction.left)){
            this.setTranslateX(character.getX());
            this.setScaleX(-1);
        }else{
            this.setTranslateX(character.getX()-384);
            this.setScaleX(1);
        }
        this.setVisible(true);

    }

    public void hide() {
        this.setVisible(false);
    }

    public void loadImage() {
        this.charaImageMap.put(CharacterType.megaMan , new Image(Launcher.class.getResourceAsStream("assets/ultimateArcueid.png")));
        this.charaImageMap.put(CharacterType.mashu,new Image(Launcher.class.getResourceAsStream("assets/ultimateMashu.png")));
        this.charaImageMap.put(CharacterType.saber, new Image(Launcher.class.getResourceAsStream("assets/ultimateSaber.png")));
        this.cutSceneImage = new ImageView(charaImageMap.get(CharacterType.megaMan));
    }

}
