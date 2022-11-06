package se233.camelot.view;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se233.camelot.model.Character;

public class Ultimate extends Pane {
    private Character owner;
    private ProgressBar ultiGate;
    public Ultimate(int x, int y, Character owner) throws Exception{
        this.owner = owner;
        setTranslateX(x);
        setTranslateY(y);
        ultiGate = new ProgressBar(0);
        VBox box = new VBox();
        box.getChildren().addAll(ultiGate);
        this.getChildren().addAll(box);
    }

    public void update() throws Exception {
        Double gate = owner.getUltimateCharge()/100.0;
        ultiGate.setProgress(gate);
    }


}
