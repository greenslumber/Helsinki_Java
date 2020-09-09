package hurraa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


class App {
    public static void main(String[] args) {
        Application.launch(HurraaSovellus.class);
    }
}

public class HurraaSovellus extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();

        Button play = new Button("Hurraa!");

        AudioClip sound = new AudioClip("file:tasmanian-devil-daniel_simon.mp3");

        play.setOnMouseClicked((event) -> {
            sound.play();
        });

        pane.setCenter(play);

        Scene scene = new Scene(pane, 600, 400);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        App.main(args);
    }

}
