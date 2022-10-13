package se233.camelot;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    public static Stage stage ;
    public static HostServices hs ;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage ;
        hs = getHostServices();

        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("HomePage.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        this.stage.setTitle("Head soccer");

        this.stage.setScene(scene);
        this.stage.show();
    }

}
