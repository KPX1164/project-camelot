package se233.camelot.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import se233.camelot.Launcher;
import se233.camelot.model.Character;
import se233.camelot.model.CharacterType;

import java.util.HashMap;
import java.util.Map;

public class CharacterIcon extends Pane {

    private Character character ;
//    private Image characterImg ;
    private ImageView imageView ;
    private int x ;
    private int y ;
    private Map<String , Image> characterImgMap ;
    private ImageView ultimateAura ;

    public CharacterIcon(Character character , int x , int y) {

        this.character = character ;
        this.x = x ;
        this.y = y ;

        this.setTranslateX(x);
        this.setTranslateY(y);

        this.characterImgMap = new HashMap<>();
        loadImage() ;

        //default image
        this.imageView = new ImageView(characterImgMap.get("normal"));
        this.imageView.setFitWidth(128);
        this.imageView.setFitHeight(128);

        this.getChildren().add(imageView);

        ultimateAura = new ImageView(new Image(Launcher.class.getResource("assets/UltiEffect.png").toString()));
        ultimateAura.setFitWidth(128);
        ultimateAura.setFitHeight(128);
        ultimateAura.setVisible(false);
        this.getChildren().add(ultimateAura);

    }

    public void update() {
        if(this.character.isInUltimate()){
            // with red eye
            this.imageView.setImage(characterImgMap.get("redEye"));
        }else{
            this.imageView.setImage(characterImgMap.get("normal"));
        }

        if(character.getUltimateCharge() == 100){
            ultimateAura.setVisible(true);
        }else{
            ultimateAura.setVisible(false);
        }

    }

    public void loadImage() {

        if(character.getCharacterType().equals(CharacterType.megaMan)){
            Image normalImg = new Image(Launcher.class.getResourceAsStream("Images/character1.png"));
            Image redEyeImg = new Image(Launcher.class.getResourceAsStream("Images/megaManUlti.png"));
            this.characterImgMap.put("normal",normalImg);
            this.characterImgMap.put("redEye", redEyeImg);

        }else if(character.getCharacterType().equals(CharacterType.mashu)){
            Image normalImg = new Image(Launcher.class.getResourceAsStream("Images/character2.png"));
            Image redEyeImg = new Image(Launcher.class.getResourceAsStream("Images/mashuUlti.png"));
            this.characterImgMap.put("normal",normalImg);
            this.characterImgMap.put("redEye", redEyeImg);
        }else{
            Image normalImg = new Image(Launcher.class.getResourceAsStream("Images/character3.png"));
            Image redEyeImg = new Image(Launcher.class.getResourceAsStream("Images/saberUlti.png"));
            this.characterImgMap.put("normal",normalImg);
            this.characterImgMap.put("redEye", redEyeImg);
        }
    }
}
