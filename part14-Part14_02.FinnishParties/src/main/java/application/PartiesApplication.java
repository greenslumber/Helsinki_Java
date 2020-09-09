package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import javax.lang.model.type.ArrayType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

class App {
    public static void main (String [] args){
        Application.launch(PartiesApplication.class);
    }
}

public class PartiesApplication extends Application {
    public static void main(String[] args) {
        App.main(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //create axes
        NumberAxis xAxis = new NumberAxis(1968, 2010 , 4);
        NumberAxis yAxis = new NumberAxis(0, 30, 5);

        xAxis.setLabel("Years");
        yAxis.setLabel("Support");

        LineChart <Number, Number> lineChart = new LineChart <> (xAxis, yAxis);
        lineChart.setTitle("Relative support of the parties");

        //create dataset
        String filename = "partiesdata.tsv";
        dataProcessor dp = new dataProcessor();
        Map <String, Map<Integer, Double>> values = dp.workData(filename);

        //import datasets
        //go through each individual party

        values.keySet().stream().forEach( party -> {
                XYChart.Series data = new XYChart.Series();
                data.setName(party);

                values.get(party).entrySet().stream().forEach( year -> {
                    data.getData().add(new XYChart.Data(year.getKey(),year.getValue()));
                });
                lineChart.getData().add(data);
            });

        //display
        Scene view = new Scene(lineChart, 640, 480);
        stage.setScene(view);
        stage.show();
    }

}