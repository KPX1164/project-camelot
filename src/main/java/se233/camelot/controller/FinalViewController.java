package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import se233.camelot.Launcher;
import se233.camelot.model.Character;
import se233.camelot.view.Platform;

import java.util.ArrayList;

public class FinalViewController {
    @FXML
    private Button homeBtn;

    @FXML
    private Label p1win ;
    @FXML
    private Label p2win ;
    @FXML
    private Label p12win ;
    @FXML
    private ImageView chat ;
    @FXML
    private ImageView chatWide ;

    private ArrayList<Character> characters ;

    @FXML
    public void initialize()  {
        this.characters = Platform.getCharacters() ;

        characters.forEach( character -> {
            System.out.println(character.getScore());
        });

        if(characters.get(0).getScore() > characters.get(1).getScore()){
            p1win.setVisible(true);
            chat.setVisible(true);

        }else if(characters.get(0).getScore() < characters.get(1).getScore()){
            p2win.setVisible(true);
            chat.setVisible(true);
        }else{
            p12win.setVisible(true);
            chatWide.setVisible(true);
        }

        homeBtn.setOnAction( e -> {
            Platform.setCharacters(new ArrayList<>());
            SceneController.navigateTo("MenuView");
        });

    }
}
