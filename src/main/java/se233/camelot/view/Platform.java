package se233.camelot.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import se233.camelot.Launcher;
import se233.camelot.model.Ball;
import se233.camelot.model.Character;
import se233.camelot.model.CharacterType;
import se233.camelot.model.Keys;

import java.util.ArrayList;
import java.util.Map;


public class Platform extends Pane {
    public static final long MATCHDURATION = 60 ;
    public static final int WIDTH = 1280 ;
    public static  final int HEIGHT = 720 ;
    public static final int GROUND = 620 ;
    private static ArrayList<Character> characters ;
    private static ArrayList<Score> scoreList = new ArrayList<>();
    private  ArrayList<Goal> goalList = new ArrayList<>();

    private ArrayList<UltimateBar> ultimateBars ;
    private Timer timer ;
    private Ball ball ;
    private Keys keys ;
    private Image platformImg ;
    private Image leftGoalInPlatform;
    private Image rightGoalInPlatform;


    public Platform(ArrayList<CharacterType> characterTypes) {
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
        this.characters.forEach( ch -> {
            this.getChildren().add(ch) ;
        });

        this.timer = new Timer(624,20);
        this.getChildren().add(timer);

        this.ball = new Ball(624,250);
        this.getChildren().add(ball);

        scoreList.add(new Score(30 , 64, characters.get(0) ));
        scoreList.add(new Score(Platform.WIDTH - 180 ,  64 , characters.get(1)));
        scoreList.forEach( list -> { this.getChildren().add(list) ; });
    }
    //Test
    public Platform() {
        leftGoalInPlatform = new Image(Launcher.class.getResourceAsStream("assets/leftGoal.png"));
        rightGoalInPlatform = new Image(Launcher.class.getResourceAsStream("assets/rightGoal.png"));
        goalList.add(leftGoalInPlatform);
        goalList.add(rightGoalInPlatform);
        keys = new Keys();
        platformImg = new Image(Launcher.class.getResourceAsStream("assets/Background.jpg")) ;
        ImageView backgroundImg = new ImageView(platformImg) ;
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);


        this.characters = new ArrayList<Character>();

        characters.add(new Character(140,GROUND-128,0,0, KeyCode.A,KeyCode.D,KeyCode.W, CharacterType.zeroMan,KeyCode.Z,KeyCode.X));
        characters.add(new Character(920,GROUND-128,0,0, KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP, CharacterType.saber,KeyCode.K,KeyCode.L));



        this.getChildren().addAll(backgroundImg);
        this.characters.forEach( ch -> {
            this.getChildren().add(ch) ;
        });

        this.timer = new Timer(624,20);
        this.getChildren().add(timer);

        this.ball = new Ball(624,250);
        this.getChildren().add(ball);

        scoreList.add(new Score(30 , 64, characters.get(0) ));
        scoreList.add(new Score(Platform.WIDTH - 180 ,  64 , characters.get(1)));
        scoreList.forEach( list -> { this.getChildren().add(list) ; });
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

    public void endPlatform(){
        scoreList = new ArrayList<>() ;
        keys = new Keys() ;
        this.characters.forEach( ch -> {
            ch.stop();
        });
    }
}
