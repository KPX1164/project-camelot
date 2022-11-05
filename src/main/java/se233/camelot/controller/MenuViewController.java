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
import java.util.Timer;
import java.util.TimerTask;

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
    @FXML
    private Label decibelDetail;
    @FXML
    private Label decibel;
    @FXML
    private Button safetyBtn;

    private static final DecimalFormat df = new DecimalFormat("0.00");



    @FXML
    public void initialize()  {
        Double cur_value = Launcher.musicController.getBgmVolume();
        Double cur_effect = Launcher.musicController.getEffectVolume();
        Double cur_voice = Launcher.musicController.getVoiceOverVolume();

        soundBar.setValue(cur_value);
        effectBar.setValue(cur_effect);
        voiceBar.setValue(cur_voice);

        cur_value = cur_value*100;
        cur_effect = cur_effect*100;
        cur_voice = cur_voice*100;

        String percent_cur_value = String.format("%.0f",cur_value);
        String percent_cur_effect = String.format("%.0f",cur_effect);
        String percent_cur_voice = String.format("%.0f",cur_voice);

        soundPercent.setText(percent_cur_value+"%");
        effectPercent.setText(percent_cur_effect+"%");
        voicePercent.setText(percent_cur_voice+"%");


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

        safetyBtn.setOnAction( e -> {
            Launcher.musicController.playEffect("click");
            Launcher.musicController.safety();

            voicePercent.setText("40%");
            soundPercent.setText("40%");
            effectPercent.setText("40%");

            voiceBar.setValue(0.4);
            effectBar.setValue(0.4);
            soundBar.setValue(0.4);


            decibel.setVisible(true);
            decibelDetail.setVisible(true);

        });

        soundBar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String new_value = String.format("%.2f",soundBar.getValue());
                Double double_new_value = Double.parseDouble(new_value);
                Launcher.musicController.setBgmVolume(double_new_value);
                double_new_value = double_new_value*100;
                String percent_new_value = String.format("%.0f",double_new_value);
                soundPercent.setText(percent_new_value+"%");
                if(percent_new_value != "40"){
                    decibel.setVisible(false);
                    decibelDetail.setVisible(false);
                }
            }
        });

        effectBar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String new_value = String.format("%.2f",effectBar.getValue());
                Double double_new_value = Double.parseDouble(new_value);
                Launcher.musicController.setEffectVolume(double_new_value);
                double_new_value = double_new_value*100;
                String percent_new_value = String.format("%.0f",double_new_value);
                effectPercent.setText(percent_new_value+"%");
                Launcher.musicController.playEffect("hover");
                if(percent_new_value != "40"){
                    decibel.setVisible(false);
                    decibelDetail.setVisible(false);
                }
            }
        });

        voiceBar.valueProperty().addListener( new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String newValue = String.format("%.2f",voiceBar.getValue());
                Double calculatedNewValue = Double.parseDouble(newValue);
                Launcher.musicController.setVoiceOverVolume(calculatedNewValue);
                calculatedNewValue *= 100 ;
                String percent_new_value = String.format("%.0f",calculatedNewValue);
                voicePercent.setText(percent_new_value+"%");
                Launcher.musicController.playVoice("voiceTest");
                if(percent_new_value != "40"){
                    decibel.setVisible(false);
                    decibelDetail.setVisible(false);
                }
            }
        });


        resetBtn.setOnAction(resetEvent -> {
            Launcher.musicController.playEffect("click");
            Launcher.musicController.resetAllSoundVolume();

            soundBar.setValue(soundBar.getMax());
            effectBar.setValue(effectBar.getMax());
            voiceBar.setValue(voiceBar.getMax());

            decibel.setVisible(false);
            decibelDetail.setVisible(false);

            soundPercent.setText("100%");
            voicePercent.setText("100%");
            effectPercent.setText("100%");

        });
    }

}
