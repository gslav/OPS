package edu.BarSU.Controller.Methods;

import static edu.BarSU.Model.Variants.V6.Data.*;

import edu.BarSU.Model.SettingsData;
import edu.BarSU.Controller.LinesLevelModule.LineLevel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created on 08.05.2017.
 */
public class OfPenaltyFunctions extends Lab {
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
        if (!isOptimal(X1min, X2min))
            Ymin += 1.0/X1min + 1.0/X2min;

        double h = data.h;

        ObservableList<LineLevel> lineList = FXCollections.observableArrayList();
        //
        LineLevel tempLine = new LineLevel();
        tempLine.addPoint(basis[0], basis[1]);
        lineList.add(tempLine);

        while (h > data.E) {
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
        double YtempNew = func(point[0] + shiftX, point[1] + shiftY);

        if (!isOptimal(point[0] + shiftX, point[1] + shiftY))
            YtempNew += 1.0/(X1min + shiftX) + 1.0/(X2min + shiftY);

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
