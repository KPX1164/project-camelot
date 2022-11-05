package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import se233.camelot.Launcher;
import se233.camelot.model.Character;
import se233.camelot.view.Platform;

import java.util.ArrayList;

public class FinalViewController {
    @FXML
    private Button homeBtn;
    @FXML
    private ImageView chat ;
    @FXML
    private ImageView chatWide ;
    @FXML
    private Label p1win;
    @FXML
    private Label p2win ;
    @FXML
    private Label bothWin ;

    private ArrayList<Character> characters ;

    @FXML
    public void initialize()  {
        Launcher.musicController.stop();
        Launcher.musicController.playEffect("audience");
        this.characters = Platform.getCharacters() ;

        if(characters.get(0).getScore() > characters.get(1).getScore()){
            chat.setVisible(true);
            p1win.setVisible(true);
        }else if(characters.get(0).getScore() < characters.get(1).getScore()){
            chat.setVisible(true);
            p2win.setVisible(true);
        }else{
            chat.setVisible(false);
            chatWide.setVisible(true);
            bothWin.setVisible(true);
        }

        homeBtn.setOnAction( e -> {
            Launcher.musicController.playSound("main");
            Platform.setCharacters(new ArrayList<>());
            SceneController.navigateTo("MenuView");
        });

    }
}
