package se233.camelot.view;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class Platform extends Pane {
    private KeyCode key;
    public static final int WIDTH = 300 ;
    public static  final int HEIGHT = 300 ;

    public Platform() {
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);

        this.getChildren().add(new Label("Hi Hi Hi"));
    }
    public KeyCode getKey() {
        return key;
    }

    public void setKey(KeyCode key) {
        this.key = key;
    }

}
