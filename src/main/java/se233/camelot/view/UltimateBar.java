package se233.camelot.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.controller.MusicController;
import se233.camelot.model.Character;
import java.util.ArrayList;

public class UltimateBar extends Pane {
    Ultimate p1 ;
    Ultimate p2 ;
    public UltimateBar(ArrayList<Character> ch) throws Exception{

        p1 = new Ultimate(32,20,ch.get(0));
        p2 = new Ultimate(Platform.WIDTH - 128,0, ch.get(1));
        VBox box = new VBox();
        box.getChildren().addAll(p1,p2);
        this.getChildren().addAll(box);

    }

    public Ultimate getP1() {
        return p1;
    }

    public Ultimate getP2() {
        return p2;
    }
}
