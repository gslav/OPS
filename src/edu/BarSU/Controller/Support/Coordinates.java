package edu.BarSU.Controller.Support;

import java.util.Comparator;

/**
 * Created on 07.05.2017.
 */
public class Coordinates implements Comparator {
    private Double Y, X1, X2;

    public Coordinates(){ }

    public Coordinates(double y, double x1, double x2){
        this.setY(y);
        this.setX1(x1);
        this.setX2(x2);
    }

    public double getY() {
        return Y;
    }

    private void setY(double Y) {
        this.Y = Y;
    }

    public  double getX1() {
        return X1;
    }

    private void setX1(double X1) {
        this.X1 =X1;
    }

    public double getX2() {
        return X2;
    }

    private void setX2(double X2) {
        this.X2 = X2;
    }

    public int compare(Object obj1, Object obj2) {
        Coordinates sh1 = (Coordinates )obj1;
        Coordinates sh2 = (Coordinates )obj2;

        int result = sh1.Y.compareTo(sh2.Y);

        if (result != 0)
            return (result/Math.abs(result));

        result = sh1.X1.compareTo(sh2.X1);
        if (result != 0)
            return (result/Math.abs(result));

        result = sh1.X2.compareTo(sh2.X2);
        return (result != 0) ? (result/Math.abs(result)) : 0;
    }
}
