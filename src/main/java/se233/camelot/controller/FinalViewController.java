package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import se233.camelot.Launcher;
import se233.camelot.model.Character;
import se233.camelot.view.Platform;

import java.util.ArrayList;

public class FinalViewController {
    @FXML
    private Button homeBtn;

    @FXML
    private Label winnerLabel ;

    private ArrayList<Character> characters ;

    @FXML
    public void initialize()  {
        this.characters = Platform.getCharacters() ;

        characters.forEach( character -> {
            System.out.println(character.getScore());
        });

        if(characters.get(0).getScore() > characters.get(1).getScore()){
            winnerLabel.setText("Player 1");

        }else if(characters.get(0).getScore() < characters.get(1).getScore()){
            winnerLabel.setText("Player 2");

        }else{
            winnerLabel.setText("Player 1 and Player 2");
        }

        homeBtn.setOnAction( e -> {
            Platform.setCharacters(new ArrayList<>());
            SceneController.navigateTo("MenuView");
        });

    }
}
