package edu.BarSU.Methods;

import edu.BarSU.Const.ConstData;
import edu.BarSU.LinesLevelModule.LineLevel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static edu.BarSU.Const.ConstData.E;
import static edu.BarSU.Const.ConstData.basePoint;
import static edu.BarSU.Variants.V6.Data.*;

/**
 * Created by Govor Alexander on 08.05.2017.
 */
public class OfPenaltyFunctions extends Lab {
    @Override
    public double[] getSolution() {
        return super.getSolution();
    }

    @Override
    public ObservableList<LineLevel> Method() {
        X1min = basePoint[0];
        X2min = basePoint[1];

        Ymin = func(X1min, X2min);
        if (!isOptimal(X1min, X2min))
            Ymin += 1.0/X1min + 1.0/X2min;

        double[] basis = {X1min, X2min};

        double h = ConstData.h;

        ObservableList<LineLevel> lineList = FXCollections.observableArrayList();
        //
        LineLevel tempLine = new LineLevel();
        while (h > E) {

            byte directionIncrease = 0;
            //up
            directionIncrease = shiftPoint(basis, directionIncrease, h, 0, tempLine);
            // down
            directionIncrease = shiftPoint(basis, directionIncrease, -h, 0, tempLine);
            // right
            directionIncrease = shiftPoint(basis, directionIncrease, 0, h, tempLine);
            //left
            directionIncrease = shiftPoint(basis, directionIncrease, 0, -h, tempLine);

            if (directionIncrease == 4)
                h /= 2;
        }

        lineList.add(tempLine);

        X1min = basis[0];
        X2min = basis[1];

        return lineList;
    }

    private byte shiftPoint(double[] point, byte directionIncrease, double shiftX, double shiftY, LineLevel tempLine) {
        double YtempNew = func(point[0] + shiftX, point[1] + shiftY);

        if (!isOptimal(point[0] + shiftX, point[1] + shiftY))
            YtempNew += 1.0/(X1min + shiftX) + 1.0/(X2min + shiftY);

        if (Ymin > YtempNew) {
            point[0] += shiftX;
            point[1] += shiftY;
            Ymin = YtempNew;
            //
            tempLine.addPoint(point[0], point[1]);
        } else
            ++directionIncrease;

        return directionIncrease;
    }
}
