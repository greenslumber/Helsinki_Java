package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

class App {
    public static void main(String[] args) {
        Application.launch(ShanghaiApplication.class);
    }
}

public class ShanghaiApplication extends Application {
    public static void main(String[] args) {
             App.main(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //create axes
        NumberAxis xAxis = new NumberAxis(2006, 2018, 2);
        NumberAxis yAxis = new NumberAxis(0, 100, 10);

        //setTitles
        xAxis.setLabel("Year");
        yAxis.setLabel("Ranking");

        //create linechart
        LineChart <Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("University of Helsinki, Shanghai ranking");
        lineChart.setLegendVisible(false);

        //dataset
        XYChart.Series Helsinki = new XYChart.Series();
        Helsinki.setName("Helsinki");
        Helsinki.getData().add(new XYChart.Data(2007,73));
        Helsinki.getData().add(new XYChart.Data(2008,68));
        Helsinki.getData().add(new XYChart.Data(2009,72));
        Helsinki.getData().add(new XYChart.Data(2010,72));
        Helsinki.getData().add(new XYChart.Data(2011,74));
        Helsinki.getData().add(new XYChart.Data(2012,73));
        Helsinki.getData().add(new XYChart.Data(2013,76));
        Helsinki.getData().add(new XYChart.Data(2014,73));
        Helsinki.getData().add(new XYChart.Data(2015,67));
        Helsinki.getData().add(new XYChart.Data(2016,56));
        Helsinki.getData().add(new XYChart.Data(2017,56));

        //linechart get data
        lineChart.getData().add(Helsinki);

        //display
        Scene view = new Scene(lineChart, 640, 480);
        stage.setScene(view);
        stage.show();
    }
}