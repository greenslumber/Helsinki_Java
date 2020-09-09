package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class App {
    public static void main(String[] args) {
        Application.launch(SavingsCalculatorApplication.class);
    }
}

public class SavingsCalculatorApplication extends Application {
    public static void main(String[] args) {
        App.main(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //create borderPane

        BorderPane mainLayout = new BorderPane();

        //add vbox with two borderpanes
        VBox sliderDisplay = new VBox();
        sliderDisplay.setSpacing(10);
        sliderDisplay.setAlignment(Pos.CENTER);
        sliderDisplay.setPadding(new Insets(10));

        //top borderpane
        BorderPane top = new BorderPane();
        top.setPrefHeight(20);
        top.setPadding(new Insets(10));

        Label savingsTitle = new Label("Monthly savings");
        Label savingsDisplay = new Label("25");

        //top slider for savings
        Slider savingsSlider = new Slider();
        savingsSlider.setMin(25);
        savingsSlider.setMax(250);
        savingsSlider.setSnapToTicks(true);
        savingsSlider.setBlockIncrement(500);
        savingsSlider.setShowTickMarks(true);
        savingsSlider.setShowTickLabels(true);

        top.setLeft(savingsTitle);
        top.setCenter(savingsSlider);
        top.setRight(savingsDisplay);

        //bottom slider for %rate
        BorderPane bottom = new BorderPane();
        bottom.setPrefHeight(20);
        bottom.setPadding(new Insets(10));

        Label rateTitle = new Label("Yearly interest rate");
        Label rateDisplay = new Label("0");

        //bottom slider
        Slider rateSlider = new Slider();
        rateSlider.setMin(0);
        rateSlider.setMax(10);
        rateSlider.setBlockIncrement(10);
        rateSlider.setShowTickMarks(true);
        rateSlider.setShowTickLabels(true);

        bottom.setLeft(rateTitle);
        bottom.setCenter(rateSlider);
        bottom.setRight(rateDisplay);

        sliderDisplay.getChildren().addAll(top, bottom);
        mainLayout.setTop(sliderDisplay);

        //creating chart
        NumberAxis xAxis = new NumberAxis(0, 30, 1);
        NumberAxis yAxis = new NumberAxis();
        LineChart <Number, Number>lineChart = new LineChart <> (xAxis, yAxis);
        lineChart.setAnimated(false);
        lineChart.setLegendVisible(false);
        lineChart.setTitle("The Stonkanator!");
        mainLayout.setCenter(lineChart);

        XYChart.Series savings = new XYChart.Series();
        XYChart.Series savingsWithInterest = new XYChart.Series();

        lineChart.getData().addAll(savings, savingsWithInterest);

        //now to build the logic to alter graphs on slider movement
        savingsSlider.setOnMouseReleased(e -> {
            savingsDisplay.setText("" + savingsSlider.getValue());
            //update chart here
            updateChart(savingsSlider.getValue(), rateSlider.getValue(),savings, savingsWithInterest);
        });

        rateSlider.setOnMouseReleased(e -> {
            String interestString = "" + rateSlider.getValue();
            if (interestString.length() > 4){
                interestString = interestString.substring(0, 4);
            }
            rateDisplay.setText(interestString);
            //update chart here
            updateChart(savingsSlider.getValue(), rateSlider.getValue(), savings, savingsWithInterest);

        });

        //display
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.setTitle("Savings Calculator");
        stage.show();
    }

    private void updateChart(double monthlySavings, double interestRate, XYChart.Series savings, XYChart.Series savingsWithInterest){
        //clear previous data so don't end up with multiple series
        savings.getData().clear();
        savingsWithInterest.getData().clear();

        savings.getData().add(new XYChart.Data(0,0));
        savingsWithInterest.getData().add(new XYChart.Data(0,0));

        double previous = 0;

        for (int i = 0; i <= 30; i++){
            savings.getData().add(new XYChart.Data (i, i * monthlySavings * 12));
            previous += monthlySavings * 12;
            previous *= (1 + interestRate/100);
            savingsWithInterest.getData().add(new XYChart.Data(i , previous));
        }
    }
}