package edu.BarSU.Methods;

import edu.BarSU.LinesLevelModule.LineLevel;

import static edu.BarSU.Const.ConstData.*;
import static edu.BarSU.Variants.V6.Data.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Created by Govor Alexander on 20.02.2017.
 */
public class DirectGrid extends Lab {
    @Override
    public double[] getSolution() {
        return super.getSolution();
    }

    @Override
    public ObservableList<LineLevel> Method() {
        X1min = Xmin;
        X2min = Xmin;
        Ymin = func(Xmin, Xmin);

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


