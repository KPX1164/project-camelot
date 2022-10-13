package se233.camelot.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import se233.camelot.Launcher;
import se233.camelot.model.Keys;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Platform extends Pane {

    private Keys keys ;
    public static final int WIDTH = 300 ;
    public static  final int HEIGHT = 300 ;

    public Platform() {
        keys = new Keys();
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);


        ImageView imageView = new ImageView(new Image(Launcher.class.getResource("cs1.gif").toString()));
//        this.getChildren().add(imageView);

    }
    public Keys getKeys() {
        return keys;
    }

    public void setKeys(Keys keys) {
        this.keys = keys;
    }

}
