package edu.BarSU.Methods;

import edu.BarSU.LinesLevelModule.LineLevel;
import javafx.collections.ObservableList;

/**
 * Created by Govor Alexander on 30.03.2017.
 */
public abstract class Lab {
    protected double X1min;
    protected double X2min;
    protected double Ymin;

    public double[] getSolution() {
        return new double[]{X1min, X2min, Ymin};
    }

    public abstract ObservableList<LineLevel> Method();
}
