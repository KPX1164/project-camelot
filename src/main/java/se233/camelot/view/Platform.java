package se233.camelot.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import se233.camelot.Launcher;
import se233.camelot.model.*;
import se233.camelot.model.Character;

import java.util.ArrayList;


public class Platform extends Pane {
    public static final long MATCHDURATION = 20 ;
    public static final int WIDTH = 1280 ;
    public static  final int HEIGHT = 720 ;
    public static final int GROUND = 620 ;
    private static ArrayList<Character> characters ;
    private static ArrayList<Score> scoreList = new ArrayList<>();
    private static ArrayList<CharacterIcon> characterIcons = new ArrayList<>() ;
    private  ArrayList<Goal> goalList = new ArrayList<>();

    private Timer timer ;
    private static Ball ball ;
    private Keys keys ;
    private Image platformImg ;
    private Goal playerOneGoal;
    private Goal playerTwoGoal;
    private static UltimateBar ultimateBar;
    private static ImageView ultiField;
    private static CutScene cutScene ;
    private static Alert alertPopup;


    public Platform(ArrayList<CharacterType> characterTypes) {
        try{
            keys = new Keys();
            platformImg = new Image(Launcher.class.getResourceAsStream("assets/Background.jpg")) ;
            ImageView backgroundImg = new ImageView(platformImg) ;
            backgroundImg.setFitHeight(HEIGHT);
            backgroundImg.setFitWidth(WIDTH);
            this.setHeight(HEIGHT);
            this.setWidth(WIDTH);


            this.characters = new ArrayList<Character>();
            characters.add(new Character(140,GROUND-128,0,0, KeyCode.A,KeyCode.D,KeyCode.W, characterTypes.get(0),KeyCode.Z,KeyCode.X));
            characters.add(new Character(920,GROUND-128,0,0, KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP, characterTypes.get(1),KeyCode.K,KeyCode.L));

            this.getChildren().addAll(backgroundImg);

            this.timer = new Timer(624,20);
            this.getChildren().add(timer);


            this.ball = new Ball(624,250);
            this.getChildren().add(ball);

            scoreList.add(new Score(160 + 32  , 128 ));
            scoreList.add(new Score(Platform.WIDTH - 192 - 32 ,  128 ));
            scoreList.forEach( list -> { this.getChildren().add(list) ; });

            CharacterIcon playerOneIcon = new CharacterIcon(characters.get(0),32,64) ;
            CharacterIcon playerTwoIcon = new CharacterIcon(characters.get(1), Platform.WIDTH - 160 ,64);
            characterIcons.add(playerOneIcon);
            characterIcons.add(playerTwoIcon);


            playerOneGoal = new Goal(-20,Platform.GROUND - 200,"Player1");
            playerTwoGoal = new Goal(Platform.WIDTH - 90,Platform.GROUND - 200,"Player2");
            goalList.add(playerOneGoal);
            goalList.add(playerTwoGoal);

            this.ultimateBar = new UltimateBar(characters);
            this.getChildren().addAll(playerOneGoal,playerTwoGoal, ultimateBar);

            ultiField = new ImageView(new Image(Launcher.class.getResourceAsStream("Images/ultimateField.png")));
            ultiField.setFitWidth(1280);
            ultiField.setFitHeight(720);
            ultiField.setVisible(false);
            this.getChildren().add(ultiField);

            characterIcons.forEach( icon -> {
                this.getChildren().add(icon);
            });

            this.characters.forEach( ch -> {
                this.getChildren().add(ch) ;
            });


            this.cutScene = new CutScene(400,200);
            this.alertPopup = new Alert(150,380);
            this.getChildren().addAll(cutScene,alertPopup);
        } catch (Throwable e){
            e.printStackTrace();
        }
    }
    //Test
    public Platform() {
        try{
            keys = new Keys();
            platformImg = new Image(Launcher.class.getResourceAsStream("assets/Background.jpg")) ;
            ImageView backgroundImg = new ImageView(platformImg) ;
            backgroundImg.setFitHeight(HEIGHT);
            backgroundImg.setFitWidth(WIDTH);
            this.setHeight(HEIGHT);
            this.setWidth(WIDTH);


            this.characters = new ArrayList<Character>();

            characters.add(new Character(140,GROUND-128,0,0, KeyCode.A,KeyCode.D,KeyCode.W, CharacterType.saber,KeyCode.Z,KeyCode.X));
            characters.add(new Character(920,GROUND-128,0,0, KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP, CharacterType.mashu,KeyCode.K,KeyCode.L));

            this.getChildren().addAll(backgroundImg);


            this.timer = new Timer(624,20);
            this.getChildren().add(timer);


            this.ball = new Ball(624,250);
            this.getChildren().add(ball);

            scoreList.add(new Score(160 + 32  , 128 ));
            scoreList.add(new Score(Platform.WIDTH - 192 - 32 ,  128 ));
            scoreList.forEach( list -> { this.getChildren().add(list) ; });

            CharacterIcon playerOneIcon = new CharacterIcon(characters.get(0),32,64) ;
            CharacterIcon playerTwoIcon = new CharacterIcon(characters.get(1), Platform.WIDTH - 160 ,64);
            characterIcons.add(playerOneIcon);
            characterIcons.add(playerTwoIcon);


            playerOneGoal = new Goal(-20,Platform.GROUND - 200,"Player1");
            playerTwoGoal = new Goal(Platform.WIDTH - 90,Platform.GROUND - 200,"Player2");
            goalList.add(playerOneGoal);
            goalList.add(playerTwoGoal);

            this.ultimateBar = new UltimateBar(characters);
            this.getChildren().addAll(playerOneGoal,playerTwoGoal, ultimateBar);

            ultiField = new ImageView(new Image(Launcher.class.getResourceAsStream("Images/ultimateField.png")));
            ultiField.setFitWidth(1280);
            ultiField.setFitHeight(720);
            ultiField.setVisible(false);
            this.getChildren().add(ultiField);

            characterIcons.forEach( icon -> {
                this.getChildren().add(icon);
            });

            this.characters.forEach( ch -> {
                this.getChildren().add(ch) ;
            });

            this.cutScene = new CutScene(400,200);
            this.alertPopup = new Alert(150,380);
            this.getChildren().addAll(cutScene,alertPopup);

        } catch (Throwable e){
            e.printStackTrace();
        }

    }
    public Keys getKeys() {
        return keys;
    }

    public void setKeys(Keys keys) {
        this.keys = keys;
    }

    public static ArrayList<Character> getCharacters() {
        return characters;
    }
    public Ball getBall() {
        return ball;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public static ArrayList<Score> getScoreList() {
        return scoreList;
    }

    public ArrayList<Goal> getGoalList() {
        return goalList;
    }

    public void endPlatform(){
        keys = new Keys() ;
        this.characters.forEach( ch -> {
            ch.stop();
        });
        scoreList = new ArrayList<>() ;
        goalList = new ArrayList<Goal>();
//        this.characters = new ArrayList<Character>() ;
        this.ball.freeze();
        characterIcons = new ArrayList<>() ;
    }

    public static UltimateBar getUltimateBar() {
        return ultimateBar;
    }

    public static ImageView getUltiField() {
        return ultiField;
    }

    public static ArrayList<CharacterIcon> getCharacterIcons() {
        return characterIcons;
    }
    public static CutScene getCutScene() {
        return cutScene;
    }

    public static void setCharacters(ArrayList<Character> characters) {
        Platform.characters = characters;
    }

    public static Alert getAlertPopup() {
        return alertPopup;
    }

    public static void respawn() {
        ball.respawn();
        for(Character character : characters){
            character.respawn() ;
        }
    }
}
