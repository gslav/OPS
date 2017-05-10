package edu.BarSU.LinesLevelModule;

import edu.BarSU.Methods.*;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.application.Application;
import javafx.collections.ObservableList;

import static edu.BarSU.Variants.V11.Data.condition;
import static edu.BarSU.Variants.V11.Data.func;


public class StartModule extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LineChart<Number, Number> numberLineChart =
                new LineChart<Number, Number>(new NumberAxis(), new NumberAxis());
        numberLineChart.setLegendVisible(false);

        ObservableList<LineLevel> linesLevel;
        // TODO Convert in arguments, but how ? =(
        Lab TempMethod = new OfPenaltyFunctions();
        linesLevel = TempMethod.Method();
        //
        if (linesLevel.isEmpty())
            System.exit(0);

        double[] solution = TempMethod.getSolution();

        numberLineChart.setTitle(String.format("X1 = %.3f X2 = %.3f Y = %.3f", solution[0], solution[1], solution[2]));

        ObservableList<XYChart.Series<Number, Number>> seriesList = numberLineChart.getData();
        for (LineLevel tempLineLevel: linesLevel)
            seriesList.add(tempLineLevel.getSeries());

        primaryStage.setTitle("Lines level graph module");
        primaryStage.setScene(new Scene(numberLineChart, 600,600));
        primaryStage.show();

        // генерация скрипта запуск gnuplot
        IsolinesGnuPlot.preSet(func(), condition());
        IsolinesGnuPlot.runPlot();
    }

    public static void main(String[] args) {
        launch(args);
    }
}