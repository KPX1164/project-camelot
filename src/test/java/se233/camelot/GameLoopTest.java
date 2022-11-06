package se233.camelot;

import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;
import se233.camelot.controller.game.DrawingLoop;
import se233.camelot.controller.game.GameLoop;
import se233.camelot.controller.game.GameTimer;
import se233.camelot.model.Ball;
import se233.camelot.model.Character;
import se233.camelot.model.CharacterType;
import se233.camelot.model.Goal;
import se233.camelot.view.Platform;
import se233.camelot.view.Score;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameLoopTest {
    private ArrayList<CharacterType> playerMap = new ArrayList<>() ;
    private ArrayList<Character> charactersUnderTest = new ArrayList<>() ;
    private ArrayList<Goal> goalListUnderTest;
    private ArrayList<Score> scoreListUnderTest;
    private Platform platformUnderTest ;
    private GameLoop gameLoopUnderTest ;
    private DrawingLoop drawingLoopUnderTest ;
    private GameTimer gameTimerUnderTest ;
    private Ball ballUnderTest ;
    private Method updateMethod, redrawMethod ;


    @Before
    public void setup() {
        JFXPanel jfxPanel = new JFXPanel() ;

        playerMap.add(CharacterType.megaMan);
        playerMap.add(CharacterType.mashu);

        platformUnderTest = new Platform(playerMap) ;
        gameLoopUnderTest = new GameLoop(platformUnderTest);
        drawingLoopUnderTest = new DrawingLoop(platformUnderTest);
        gameTimerUnderTest = new GameTimer(platformUnderTest);


        charactersUnderTest = platformUnderTest.getCharacters() ;
        goalListUnderTest = platformUnderTest.getGoalList();
        ballUnderTest = platformUnderTest.getBall();
        scoreListUnderTest = platformUnderTest.getScoreList() ;

        try{
            updateMethod = GameLoop.class.getDeclaredMethod("update", ArrayList.class) ;
            redrawMethod = DrawingLoop.class.getDeclaredMethod("paint", ArrayList.class, Ball.class) ;
            updateMethod.setAccessible(true);
            redrawMethod.setAccessible(true);

        }catch (NoSuchMethodException e){
            e.printStackTrace();
            updateMethod = null ;
            redrawMethod = null ;

        }
    }

    private void clockTickHelper() throws InvocationTargetException, IllegalAccessException {
        updateMethod.invoke(gameLoopUnderTest,charactersUnderTest);
        redrawMethod.invoke(drawingLoopUnderTest,charactersUnderTest,ballUnderTest);

    }


    @Test
    public void playerShouldGetScoreAfterMakeTheGoal() throws InvocationTargetException, IllegalAccessException, InterruptedException {
        Character characterUnderTest = charactersUnderTest.get(0);
        for (Character character : charactersUnderTest){
            character.setY(0);
            character.setX(0);
        }

        ballUnderTest.setX(1200);
        ballUnderTest.setY(Platform.GROUND - ballUnderTest.BALL_HEIGHT);
        ballUnderTest.setxVelocity(10);

        try{
            for(int i = 0 ; i < 2; i++){
                ballUnderTest.trace();
                for(Goal goal : goalListUnderTest){
                    goal.intersect(ballUnderTest);
                }
                clockTickHelper();
            }
        }catch (NullPointerException ex){

        }
        
        assertEquals("Player 1 should get score", 1 , characterUnderTest.getScore() );
        assertEquals("Player 2's score should be same", 0 , charactersUnderTest.get(1).getScore());
    }

}
