package edu.BarSU.Controller.Methods;

import static edu.BarSU.Model.Variants.V6.Data.*;

import edu.BarSU.Model.SettingsData;
import edu.BarSU.Controller.Support.Coordinates;
import edu.BarSU.Controller.LinesLevelModule.LineLevel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Created on 22.03.2017.
 */
public class MonteCarlo extends Lab {
    @Override
    public double[] getSolution() {
        return super.getSolution();
    }

    @Override
    public ObservableList<LineLevel> Method(SettingsData data) {
        Ymin = 1.7E+308;
        //
        double Xmax = data.Xmax;
        double Xmin = data.Xmin;
        //
        Vector<Coordinates> XYZ = new Vector<>();

        ObservableList<LineLevel> lineValues = FXCollections.observableArrayList();

        int k = -1;
        while (++k < data.numberIterations) {
            double x1 = genX(Xmin, Xmax);
            double x2 = genX(Xmin, Xmax);
            if (isOptimal(x1, x2)) {

                double Ytemp = func(x1, x2);
                if (Ytemp < Ymin) {
                    Ymin = Ytemp;
                    X1min = x1;
                    X2min = x2;
                }
                XYZ.add(new Coordinates(Ytemp, x1, x2));
            }
        }

        Collections.sort(XYZ, new Coordinates());

        {
            double temp;
            int j = 0, i = 0;
            while (i < XYZ.size()) {
                LineLevel tempLine = new LineLevel();
                temp = XYZ.get(j).getY();

                for (i = j; i < XYZ.size(); ++i) {
                    if (XYZ.get(i).getY() != temp) {
                        j = i;
                        break;
                    }
                    tempLine.addPoint(XYZ.get(i).getX1(), XYZ.get(i).getX2());
                }

                lineValues.add(tempLine);
            }
        }

        return lineValues;
    }

    private static List<Double> genX(int Num, double Xmin, double Xmax) {
        List<Double> xList = new ArrayList<>();

        Random Rnd = new Random(System.currentTimeMillis());

        for (int i = 0; i < Num; ++i)
            xList.add(Xmin + Rnd.nextDouble() * (Xmax - Xmin));

        return xList;
    }

    private static Double genX(double Xmin, double Xmax) {
        return (Xmin + Math.random() * (Xmax - Xmin));
    }

}
