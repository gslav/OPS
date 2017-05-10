package edu.BarSU.LinesLevelModule;

import edu.BarSU.Methods.*;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.application.Application;
import javafx.collections.ObservableList;

public class StartModule extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LineChart<Number, Number> numberLineChart =
                new LineChart<Number, Number>(new NumberAxis(), new NumberAxis());
        numberLineChart.setLegendVisible(false);

        ObservableList<LineLevel> linesLevel;
        // TODO Convert in arguments, but how ? =(
//        Lab TempMethod = new DirectGrid();
        Lab TempMethod = new MonteCarlo();
//        Lab TempMethod = new HookJeeves();
//        Lab TempMethod = new OfPenaltyFunctions();
        linesLevel = TempMethod.Method();
        //
        if (linesLevel.isEmpty())
            System.exit(0);

        double[] solution = TempMethod.getSolution();
        System.out.println("Расчет окончен");

        // генерация скрипта и запуск gnuplot
        IsolinesGnuPlot.preSet(edu.BarSU.Variants.V6.Data.func(), edu.BarSU.Variants.V6.Data.condition(), solution);
        IsolinesGnuPlot.runPlot();
        //
        System.out.println("График gnuplot построен");

        numberLineChart.setTitle(String.format("X1 = %.3f X2 = %.3f Y = %.3f", solution[0], solution[1], solution[2]));

        ObservableList<XYChart.Series<Number, Number>> seriesList = numberLineChart.getData();
        for (LineLevel tempLineLevel: linesLevel)
            seriesList.add(tempLineLevel.getSeries());

        primaryStage.setTitle("Lines level graph module");
        primaryStage.setScene(new Scene(numberLineChart, 600,600));
        System.out.println("Настройка сцена окончена");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}