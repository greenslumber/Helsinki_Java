package collage;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageWriter;

class App {
    public static void main(String[] args) {
        Application.launch(CollageApplication.class);
    }
}

public class CollageApplication extends Application {

    @Override
    public void start(Stage stage) {

        // the example opens the image, creates a new image, and copies the opened image
        // into the new one, pixel by pixel
        Image sourceImage = new Image("file:monalisa.png");

        PixelReader imageReader = sourceImage.getPixelReader();

        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();

        WritableImage targetImage = new WritableImage(width, height);
        PixelWriter imageWriter = targetImage.getPixelWriter();

        //process row by row, pixel by pixel
        for (int yCoordinate = 0; yCoordinate < height; yCoordinate++) {
            if (yCoordinate % 2 == 0) {

                for (int xCoordinate = 0; xCoordinate < width; xCoordinate++) {
                    if(xCoordinate % 2 == 0){

                        Color color = imageReader.getColor(xCoordinate, yCoordinate);
                        double red = color.getRed();
                        double green = color.getGreen();
                        double blue = color.getBlue();
                        double opacity = color.getOpacity();

                        Color newColor = new Color( 1-red, 1-green, 1-blue, opacity);

                        //imageWriter.setColor(xCoordinate, yCoordinate, newColor);
                        imageWriter.setColor(xCoordinate/2, yCoordinate/2, newColor);
                        imageWriter.setColor(xCoordinate/2 + width/2, yCoordinate/2, newColor);
                        imageWriter.setColor(xCoordinate/2 , yCoordinate/2 + height/2, newColor);
                        imageWriter.setColor(xCoordinate/2 + width/2, yCoordinate/2 + height/2, newColor);


                    }
                }
            }
        }

        ImageView image = new ImageView(targetImage);

        Pane pane = new Pane();
        pane.getChildren().add(image);

        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static void main(String[] args) {
        App.main(args);
    }
}
