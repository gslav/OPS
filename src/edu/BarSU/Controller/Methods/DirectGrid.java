package edu.BarSU.Controller.Methods;

import static edu.BarSU.Model.Variants.V6.Data.*;

import edu.BarSU.Model.SettingsData;
import edu.BarSU.Controller.LinesLevelModule.LineLevel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Created on 20.02.2017.
 */
public class DirectGrid extends Lab {
    @Override
    public double[] getSolution() {
        return super.getSolution();
    }

    @Override
    public ObservableList<LineLevel> Method(SettingsData data) {
        X1min = data.Xmin;
        X2min = data.Xmin;
        Ymin = func(X1min, X2min);
        //
        double Xmin = data.Xmin;
        double Xmax = data.Xmax;
        double h = data.h;
        //
        ObservableList<LineLevel> lineLst = FXCollections.observableArrayList();

        for (double X1 = Xmin; X1 <= Xmax; X1 += h) {
            LineLevel tempLine = new LineLevel();

            for (double X2 = Xmin + h; X2 <= Xmax; X2 += h)
                if (isOptimal(X1, X2)) {
                    double Ytemp = func(X1, X2);

                    if (Ytemp <= Ymin) {
                        Ymin = Ytemp;

                        X1min = X1;
                        X2min = X2;
                    }
                    tempLine.addPoint(X2, Ytemp);
                }

            lineLst.add(tempLine);
        }

        return lineLst;
    }
}


