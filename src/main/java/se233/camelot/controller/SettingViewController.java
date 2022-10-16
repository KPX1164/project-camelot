package se233.camelot.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import se233.camelot.Launcher;

import java.text.DecimalFormat;

public class SettingViewController {
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

    private static final DecimalFormat df = new DecimalFormat("0.00");


    @FXML
    public void initialize()  {

        homeBtn.setOnAction( e -> {
            SceneController.navigateTo("MenuView");
        });
        Double cur_value = Launcher.bgPlayer.getVolume();
        soundBar.setValue(cur_value);
        cur_value = cur_value*100;
        String percent_cur_value = String.format("%.0f",cur_value);
        soundPercent.setText(percent_cur_value+"%");

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

        resetBtn.setOnAction(resetEvent -> {
            Launcher.bgPlayer.setVolume(1);
            soundBar.setValue(soundBar.getMax());
            soundPercent.setText("100%");
        });
    }
}
