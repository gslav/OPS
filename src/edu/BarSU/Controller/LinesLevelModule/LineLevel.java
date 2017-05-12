package edu.BarSU.Controller.LinesLevelModule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 * Created on 29.03.2017.
 */
public class LineLevel {
    private ObservableList<XYChart.Data> LineDataList = FXCollections.observableArrayList();

    public void addPoint(double x, double y) {
        LineDataList.add(new XYChart.Data(x, y));
    }

    public XYChart.Series getSeries() {
        XYChart.Series LineSeries = new XYChart.Series();
        LineSeries.setData(LineDataList);

        return LineSeries;
    }

    public XYChart.Series getSeries(String seriesName) {
        XYChart.Series seriesWithName = getSeries();
        seriesWithName.setName(seriesName);

        return seriesWithName;
    }
}
