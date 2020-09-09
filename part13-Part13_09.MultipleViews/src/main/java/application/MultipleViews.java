package application;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MultipleViews extends Application {

    public static void main(String[] args) {
        launch(MultipleViews.class);
    }

    @Override
    public void start (Stage window) throws Exception {

        Button toSecond = new Button ("To the second view!");
        Button toThird = new Button ("To the third view!");
        Button toFirst = new Button ("To the first view!");

        BorderPane first = new BorderPane();
        first.setTop(new Label("First view!"));
        first.setCenter(toSecond);

        Scene view = new Scene(first);
        window.setScene(view);
        window.show();
    }
}