package se233.camelot;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import se233.camelot.controller.MusicController;

import java.io.IOException;

public class Launcher extends Application {

    public static Stage stage ;
    public static HostServices hs ;

    public static void main(String[] args) {
        launch(args);
    }
    public static MediaPlayer bgPlayer;
    public static MusicController musicController ;

    @Override
    public void start(Stage stage) throws IOException {
        musicController = new MusicController() ;
        musicController.play("main");

        this.stage = stage ;
        hs = getHostServices();
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("HomeView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.stage.setTitle("Head soccer");
        this.stage.setResizable(false);
        this.stage.setScene(scene);
        this.stage.show();
    }

}
