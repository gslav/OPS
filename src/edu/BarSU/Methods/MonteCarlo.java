package edu.BarSU.Methods;

import static edu.BarSU.Const.ConstData.*;
import static edu.BarSU.Variants.V6.Data.*;

import edu.BarSU.Coordinates;
import edu.BarSU.LinesLevelModule.LineLevel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Created by Govor Alexander on 22.03.2017.
 */
public class MonteCarlo extends Lab {
    @Override
    public double[] getSolution() {
        return super.getSolution();
    }

    @Override
    public ObservableList<LineLevel> Method() {
        System.out.println("Начат расчет методом Монте-Карло");
        Ymin = 1.7E+308;

        Vector<Coordinates> XYZ = new Vector<>();

        ObservableList<LineLevel> lineValues = FXCollections.observableArrayList();

        int k = 0;
        while (k++ < numberIterations) {
            double x1 = genX(), x2 = genX();
//            System.out.println(x1 + "   " + x2);
            if (isOptimal(x1, x2)) {
//                System.out.println(x1 + " " + x2);

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
//                    System.out.println(XYZ.get(i).getY() + "  " + XYZ.get(i).getX1() + "  " + XYZ.get(i).getX2());
                }

                lineValues.add(tempLine);
            }
        }

        return lineValues;
    }

/*
    @Override
    public ObservableList<LineLevel> Method() {

        List<Double> x1List = genX(numberIterations);
        List<Double> x2List = genX(numberIterations);
        //
        for (double x1: x1List)
            for (double x2 : x2List)
                if (isOptimal(x1, x2)) {
                    Ymin = func(x1, x2);
                    X1min = x1;
                    X2min = x2;

                }
        //
        ObservableList<LineLevel> lineValues = FXCollections.observableArrayList();

        for (double x1: x1List) {
            LineLevel tempLine = new LineLevel();

            for (double x2 : x2List)
                if (isOptimal(x1, x2)) {
                    double Ytemp = func(x1, x2);

                    if (Ytemp < Ymin) {
                        Ymin = Ytemp;
                        X1min = x1;
                        X2min = x2;
                    }

                    tempLine.addPoint(x2, Ytemp);
                }

            lineValues.add(tempLine);
        }

        return lineValues;
    }
*/

    private static List<Double> genX(int Num) {
        List<Double> xList = new ArrayList<>();

        Random Rnd = new Random(System.currentTimeMillis());

        for (int i = 0; i < Num; ++i)
            xList.add(Xmin + Rnd.nextDouble() * (Xmax - Xmin));

        return xList;
    }

    private static Double genX() {
        return (Xmin + Math.random() * (Xmax - Xmin));
    }

}
