package se233.camelot;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    public static Stage stage ;
    public static HostServices hs ;

    public static void main(String[] args) {
        launch(args);
    }
    public static MediaPlayer bgPlay;

    @Override
    public void start(Stage stage) throws IOException {
        Media bgSong = new Media(getClass().getResource("Audios/Base.mp3").toString());
        bgPlay = new MediaPlayer(bgSong);
        bgPlay.setAutoPlay(true);
        bgPlay.setCycleCount(100);
        bgPlay.setVolume(0.5);
        this.stage = stage ;
        hs = getHostServices();
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.stage.setTitle("Head soccer");

        this.stage.setScene(scene);
        this.stage.show();
    }

}
