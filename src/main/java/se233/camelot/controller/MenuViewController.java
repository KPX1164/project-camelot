package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuViewController {

    @FXML
    private Button playBtn ;
    @FXML
    private Button settingBtn;
    @FXML
    private Button creditBtn;



    @FXML
    public void initialize()  {

        playBtn.setOnAction( e -> {
            SceneController.navigateTo("CharacterView");
        });

        settingBtn.setOnAction(event -> {
            SceneController.navigateTo("CharacterView");
        });

        creditBtn.setOnAction(event -> {
            SceneController.navigateTo("CreditView");
        });
    }
}
