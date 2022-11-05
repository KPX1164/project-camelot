package se233.camelot.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import se233.camelot.Launcher;

import java.text.DecimalFormat;

public class MenuViewController {

    @FXML
    private Button playBtn ;
    @FXML
    private Button settingBtn;

    @FXML
    private Button quitBtn;
    @FXML
    private ImageView overLayerH;
    @FXML
    private ImageView askLayer;
    @FXML
    private ImageView yesView;
    @FXML
    private Button yesBtn;
    @FXML
    private ImageView noView;
    @FXML
    private Button noBtn;
    @FXML
    private ImageView parinyaLayer;
    @FXML
    private Pane settingView;
    @FXML
    private Button homeBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Slider soundBar;
    @FXML
    private Slider voiceBar;
    @FXML
    private Slider effectBar;
    @FXML
    private Label soundPercent;
    @FXML
    private Label effectPercent;
    @FXML
    private Label voicePercent;

    private static final DecimalFormat df = new DecimalFormat("0.00");



    @FXML
    public void initialize()  {
        Double cur_value = Launcher.bgPlayer.getVolume();
//        Double cur_effect = Launcher.soundEffect.getVolume();
        soundBar.setValue(cur_value);
//        effectBar.setValue(cur_effect);
        cur_value = cur_value*100;
//        cur_effect = cur_effect*100;
        String percent_cur_value = String.format("%.0f",cur_value);
//        String percent_cur_effect = String.format("%.0f",cur_effect);
        soundPercent.setText(percent_cur_value+"%");
//        effectPercent.setText(percent_cur_effect+"%");


        playBtn.setOnAction( e -> {
            Launcher.musicController.playEffect("click");
            SceneController.navigateTo("CharacterView");
        });

        settingBtn.setOnAction(event -> {
            Launcher.musicController.playEffect("click");
            settingView.setVisible(true);
        });


        quitBtn.setOnAction(event -> {
            Launcher.musicController.playEffect("click");
            overLayerH.setVisible(true);
            askLayer.setVisible(true);
            parinyaLayer.setVisible(true);
            yesView.setVisible(true);
            yesBtn.setVisible(true);
            noView.setVisible(true);
            noBtn.setVisible(true);

            yesBtn.setOnAction(yesEvent ->{
                Launcher.musicController.playEffect("click");
                System.exit(0);
            });
            noBtn.setOnAction(noEvent ->{
                Launcher.musicController.playEffect("click");
                overLayerH.setVisible(false);
                askLayer.setVisible(false);
                parinyaLayer.setVisible(false);
                yesView.setVisible(false);
                yesBtn.setVisible(false);
                noView.setVisible(false);
                noBtn.setVisible(false);
            });
        });
        homeBtn.setOnAction( e -> {
            Launcher.musicController.playEffect("click");
            settingView.setVisible(false);
        });

        soundBar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String new_value = String.format("%.2f",soundBar.getValue());
                Double double_new_value = Double.parseDouble(new_value);
                Launcher.bgPlayer.setVolume(double_new_value);
                double_new_value = double_new_value*100;
                String percent_new_value = String.format("%.0f",double_new_value);
                soundPercent.setText(percent_new_value+"%");
            }
        });

        effectBar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String new_value = String.format("%.2f",effectBar.getValue());
                Double double_new_value = Double.parseDouble(new_value);
                Launcher.soundEffect.setVolume(double_new_value);
                double_new_value = double_new_value*100;
                String percent_new_value = String.format("%.0f",double_new_value);
                effectPercent.setText(percent_new_value+"%");
            }
        });

        resetBtn.setOnAction(resetEvent -> {
            Launcher.musicController.playEffect("click");
            Launcher.bgPlayer.setVolume(1);
            Launcher.soundEffect.setVolume(1);
//            Launcher.voiceOver.setVolume(1);

            soundBar.setValue(soundBar.getMax());
            effectBar.setValue(effectBar.getMax());
//            voiceBar.setValue(voiceBar.getMax());

            soundPercent.setText("100%");
//            voicePercent.setText("100%");
            effectPercent.setText("100%");

        });
    }
}
