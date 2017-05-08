package edu.BarSU.Methods;

import edu.BarSU.Const.ConstData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import edu.BarSU.LinesLevelModule.LineLevel;

import static edu.BarSU.Const.ConstData.*;
import static edu.BarSU.Variants.V6.Data.*;

/**
 * Created by Govor Alexander on 27.04.2017.
 */
public class HookJeeves extends Lab {
    @Override
    public double[] getSolution() {
        return super.getSolution();
    }

    @Override
    public ObservableList<LineLevel> Method() {
        X1min = basePoint[0];
        X2min = basePoint[1];
        Ymin = func(X1min, X2min);

        double[] basis = {X1min, X2min};

        if (!isOptimal(basis[0], basis[1]))
            return FXCollections.emptyObservableList();

        double h = ConstData.h;

        ObservableList<LineLevel> lineList = FXCollections.observableArrayList();
        //
        while (h > E) {
            byte directionIncrease = 0;
            //up
            directionIncrease = shiftPint(basis, directionIncrease, h, 0);
            // down
            directionIncrease = shiftPint(basis, directionIncrease, -h, 0);
            // right
            directionIncrease = shiftPint(basis, directionIncrease, 0, h);
            //left
            directionIncrease = shiftPint(basis, directionIncrease, 0, -h);

            if (directionIncrease == 4)
                h /= 2;
        }

        X1min = basis[0];
        X2min = basis[1];

        return lineList;
    }

    private byte shiftPint(double[] point, byte directionIncrease, double shiftX, double shiftY) {

        if (!isOptimal(point[0] + shiftX, point[1] + shiftY))
            return ++directionIncrease;

        double YtempNew = func(point[0] + shiftX, point[1] + shiftY);

        if (Ymin > YtempNew) {
            point[0] += shiftX;
            point[1] += shiftY;
            Ymin = YtempNew;
        } else
            ++directionIncrease;

        return directionIncrease;
    }
}