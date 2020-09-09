package smiley;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

class App {
    public static void main(String[] args) {
        Application.launch(SmileyApplication.class);
    }
}

public class SmileyApplication extends Application {

    public static void main(String[] args) {
        App.main(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane layout = new BorderPane();
        int width = 320;
        int length = 240;
        Canvas painting = new Canvas(width,length);
        GraphicsContext painter = painting.getGraphicsContext2D();

        layout.setCenter(painting);
        //layout.setRight(colorPicker);

        drawShapes(painter, width, length);

        /*
        painting.setOnMouseDragged((event) -> {
            double xLocation = event.getX();
            double yLocation = event.getY();
            painter.setFill(colorPicker.getValue());
            painter.fillOval(xLocation, yLocation, 5, 5);
        });
        */

        stage.setScene(new Scene(layout));
        stage.show();
    }

    public void drawShapes(GraphicsContext gc, int width, int height){
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(50);
        //eyes
        gc.strokeLine(width*0.3, height*0.2 ,width*0.35  ,height*0.2);
        gc.strokeLine(width*0.7, height*0.2 ,width*0.65  ,height*0.2);
        //mouth
        gc.strokeLine(width*0.2, height*0.6 ,width*0.25  ,height*0.6);
        gc.strokeLine(width*0.8, height*0.6 ,width*0.75  ,height*0.6);
        gc.strokeLine(width*0.25, height*0.7 ,width*0.75  ,height*0.7);
    }
}
