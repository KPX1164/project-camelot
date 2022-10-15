package se233.camelot.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;

public class CreditViewController {
    @FXML
    private Button homeBtn;

    @FXML
    public void initialize()  {

        homeBtn.setOnAction( e -> {
            SceneController.navigateTo("MenuView");
        });

    }
}
