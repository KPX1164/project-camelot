package se233.camelot;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import se233.camelot.controller.MusicController;

import java.io.IOException;

public class Launcher extends Application {

    public static Stage stage ;
    public static HostServices hs ;

    public static void main(String[] args) {
        launch(args);
    }

    public static MusicController musicController ;
    private Logger logger = LogManager.getLogger(Launcher.class);
    @Override
    public void start(Stage stage) throws IOException {
        try{
            musicController = new MusicController() ;
            musicController.playSound("main");

            this.stage = stage ;
            hs = getHostServices();
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("HomeView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            this.stage.setTitle("Head soccer");
            this.stage.setResizable(false);
            this.stage.setScene(scene);
            this.stage.show();

        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry bro there is a critical error please restart the program");
            logger.fatal(ex.getMessage());
            alert.showAndWait();
            System.exit(0);
        }

    }

}
