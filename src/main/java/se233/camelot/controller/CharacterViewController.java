package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.media.MediaPlayer;
import se233.camelot.Launcher;
import se233.camelot.view.Platform;

import java.io.IOException;

public class CharacterViewController {

    @FXML
    private Toggle p1c1;
    @FXML
    private Toggle p1c2;
    @FXML
    private Toggle p1c3;
    @FXML
    private Toggle p2c1;
    @FXML
    private Toggle p2c2;
    @FXML
    private Toggle p2c3;

    @FXML
    public void initialize() {
        ToggleGroup player1 = new ToggleGroup();
        p1c1.setToggleGroup(player1);
        p1c2.setToggleGroup(player1);
        p1c3.setToggleGroup(player1);

        ToggleGroup player2 = new ToggleGroup();
        p2c1.setToggleGroup(player2);
        p2c2.setToggleGroup(player2);
        p2c3.setToggleGroup(player2);

    }
}
