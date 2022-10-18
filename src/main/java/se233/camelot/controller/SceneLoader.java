package se233.camelot.controller;

import javafx.fxml.FXML;

public class SceneLoader {

    @FXML
    public void initialize()  {
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
            SceneController.navigateTo("MenuView");

    }

}
