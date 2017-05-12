package edu.BarSU.Controller.Methods;

import static edu.BarSU.Model.Variants.V6.Data.*;

import edu.BarSU.Model.SettingsData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import edu.BarSU.Controller.LinesLevelModule.LineLevel;

/**
 * Created on 27.04.2017.
 */
public class HookJeeves extends Lab {
    @Override
    public double[] getSolution() {
        return super.getSolution();
    }

    @Override
    public ObservableList<LineLevel> Method(SettingsData data) {
        double[] basis = data.basisPoint.clone();

        X1min = basis[0];
        X2min = basis[1];
        Ymin = func(X1min, X2min);

        double h = data.h;
        double E = data.E;

        if (!isOptimal(basis[0], basis[1]))
            return FXCollections.emptyObservableList();

        ObservableList<LineLevel> lineList = FXCollections.observableArrayList();

        LineLevel tempLine = new LineLevel();

        tempLine.addPoint(basis[0], basis[1]);

        lineList.add(tempLine);

        while (h > E) {
            byte directionIncrease = 0;
            //up
            directionIncrease = shiftPoint(basis, directionIncrease, h, 0, lineList);
            // down
            directionIncrease = shiftPoint(basis, directionIncrease, -h, 0, lineList);
            // right
            directionIncrease = shiftPoint(basis, directionIncrease, 0, h, lineList);
            //left
            directionIncrease = shiftPoint(basis, directionIncrease, 0, -h, lineList);

            if (directionIncrease == 4)
                h /= 2;
        }

        lineList.add(tempLine);

        X1min = basis[0];
        X2min = basis[1];

        return lineList;
    }

    private byte shiftPoint(
            double[] point,
            byte directionIncrease,
            double shiftX,
            double shiftY,
            ObservableList<LineLevel> lineList) {

        if (!isOptimal(point[0] + shiftX, point[1] + shiftY))
            return ++directionIncrease;

        double YtempNew = func(point[0] + shiftX, point[1] + shiftY);

        if (Ymin > YtempNew) {
            point[0] += shiftX;
            point[1] += shiftY;
            Ymin = YtempNew;
            //
            LineLevel tempLine = new LineLevel();
            tempLine.addPoint(point[0], point[1]);
            lineList.add(tempLine);
        } else
            ++directionIncrease;

        return directionIncrease;
    }
}