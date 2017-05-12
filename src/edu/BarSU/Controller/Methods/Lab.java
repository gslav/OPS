package edu.BarSU.Controller.Methods;

import edu.BarSU.Model.SettingsData;
import edu.BarSU.Controller.LinesLevelModule.LineLevel;

import javafx.collections.ObservableList;

/**
 * Created on 30.03.2017.
 */
public abstract class Lab {
    protected double X1min;
    protected double X2min;
    protected double Ymin;

    public double[] getSolution() {
        return new double[]{X1min, X2min, Ymin};
    }

    public abstract ObservableList<LineLevel> Method(SettingsData data);
}
