package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import se233.camelot.Launcher;
import se233.camelot.view.Platform;

public class CharacterViewController {

    @FXML
    private Button playBtn;
    @FXML
    public void initialize() {
        playBtn.setOnAction( event -> {
            Platform platform = new Platform() ;
            Launcher.stage.getScene().setRoot(platform);
        });
    }
}
