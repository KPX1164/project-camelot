package se233.camelot;

import se233.camelot.controller.DrawingLoop;
import se233.camelot.controller.GameLoop;
import se233.camelot.controller.GameTimer;
import se233.camelot.model.Ball;
import se233.camelot.model.Character;
import se233.camelot.model.CharacterType;
import javafx.embed.swing.JFXPanel;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import se233.camelot.model.Goal;
import se233.camelot.view.Platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class CharacterTest {
    private ArrayList<CharacterType> playerMap = new ArrayList<>() ;
    private ArrayList<Character> charactersUnderTest = new ArrayList<>() ;
    private ArrayList<Goal> goalListUnderTest;
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
    public void characterInitialValueShouldMatchConstructor() {
        Character character = charactersUnderTest.get(0) ;

        assertEquals("Character Init X", 140, character.getX());
        assertEquals("Character Init Y", 620-128, character.getY());
        assertEquals("Left key", KeyCode.A , character.getLeftKey());
        assertEquals("Right key", KeyCode.D, character.getRightKey());
        assertEquals("Up key", KeyCode.W, character.getUpKey());
    }

    @Test
    public void characterShouldMoveWhenKeyIsPressed() throws InvocationTargetException, IllegalAccessException  {
        Character character = charactersUnderTest.get(0) ;

        int startX = character.getX() ;
        platformUnderTest.getKeys().add(KeyCode.A);
        clockTickHelper();


        assertNotEquals("Character Position should be not same after move", startX , character.getX());
    }

    @Test
    public void characterShouldFallWhenNotStayingOnGround() throws InvocationTargetException, IllegalAccessException {
        Character character = charactersUnderTest.get(0) ;

        int startY = character.getY() ;
        clockTickHelper();

        assertNotEquals("Character Position should be not same after fall", startY , character.getY());
        assertTrue("Character is falling", character.isFalling());
    }

    @Test
    public void characterShouldNotFallWhenOnGround() {
        Character character = new Character(0,620-128,0,0, KeyCode.A,KeyCode.D,KeyCode.W, CharacterType.mashu,KeyCode.Z,KeyCode.X);
        character.checkReachFloor();

        assertEquals("Character Position should be same ",character.getY(),platformUnderTest.GROUND - character.CHARACTER_HEIGHT);
        assertFalse("Character is falling", character.isFalling());
    }

    @Test
    public void characterShouldNotEnableToJumpWhenNotOnGround() {
        Character character = new Character(0,10,0,0, KeyCode.A,KeyCode.D,KeyCode.W, CharacterType.mashu,KeyCode.Z,KeyCode.X);
        character.checkReachFloor();

        assertFalse("Character can jump", character.isCanJump());
    }

    @Test
    public void characterShouldJumpWhenOnGround() throws InvocationTargetException, IllegalAccessException {
        Character character = charactersUnderTest.get(0);
        character.checkReachFloor();

        assertTrue("Character can jump", character.isCanJump());

        platformUnderTest.getKeys().add(KeyCode.W);
        int startY = character.getY();

        clockTickHelper();
        clockTickHelper();

        assertTrue("Character Position should be not same after jump",character.getY() < startY);
    }

    @Test
    public void characterShouldBounceWhenReachGameWall() throws InvocationTargetException, IllegalAccessException  {
        Character character = charactersUnderTest.get(0);
        character.setX(0);
        int startX = character.getX() ;
        platformUnderTest.getKeys().add(KeyCode.A);

        clockTickHelper();
        clockTickHelper();
        character.checkReachGameWall();


        assertEquals(character.getX(), startX);
    }
    @Test
    public void characterShouldBounceEachCharacter() throws InvocationTargetException, IllegalAccessException {
        Character character1 = charactersUnderTest.get(0);
        Character character2 = charactersUnderTest.get(1);

        character1.setX(20);
        character2.setX(150);

        int[] characterStartLocation = { character1.getX() , character2.getX() };

        platformUnderTest.getKeys().add(KeyCode.LEFT);

        clockTickHelper();
        clockTickHelper();
        clockTickHelper();
        clockTickHelper();

        character2.collided(character1);
        character1.collided(character2);

        assertNotEquals(character1.getX(),characterStartLocation[0]);
        assertNotEquals(character2.getX(),characterStartLocation[1]);
    }
    @Test
    public void characterShouldRespawnOnStartLocation() {
        Character characterUnderTest = charactersUnderTest.get(0);
        characterUnderTest.setX(500);
        characterUnderTest.setY(500);

        characterUnderTest.respawn();
        assertEquals(characterUnderTest.getStartX() , characterUnderTest.getX());
        assertEquals(characterUnderTest.getStartY(), characterUnderTest.getY());
    }

    @Test
    public void characterShouldNotUseUltimateWhenTheChargeIsNotFull() throws InvocationTargetException, IllegalAccessException {
        Character characterUnderTest = charactersUnderTest.get(0);
        characterUnderTest.setUltimateCharge(0);
        platformUnderTest.getKeys().add(KeyCode.X);

        clockTickHelper();
        assertFalse("Ultimate status should not be active",characterUnderTest.isInUltimate());
    }

    @Test
    public void characterShouldUseUltimateWhenTheChargeIsFull() throws InvocationTargetException, IllegalAccessException {
        Character characterUnderTest = charactersUnderTest.get(0);
        characterUnderTest.setUltimateCharge(100);
        platformUnderTest.getKeys().add(KeyCode.X);

        clockTickHelper();
        assertTrue("Ultimate status should be active",characterUnderTest.isInUltimate());
    }

    @Test
    public void characterShouldChargeUltimateByTen() {
        Character characterUnderTest = charactersUnderTest.get(0);

        characterUnderTest.setUltimateCharge(0);
        characterUnderTest.chargeUltimate();
        assertEquals("Ultimate charge should be added by 10",10, characterUnderTest.getUltimateCharge());

        characterUnderTest.setUltimateCharge(100);
        characterUnderTest.chargeUltimate();
        assertEquals("Ultimate charge should not over 100",100, characterUnderTest.getUltimateCharge());
    }

}


