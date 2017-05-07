package edu.BarSU.Variants.V11;

/**
 * Created by Govor Alexander on 07.05.2017.
 */
public class Data {
    public static double func(double X1, double X2) {
        return 2 * X1 - X1 * X1 + X2 * X2;
    }

    public static boolean isOptimal(double X1, double X2) {
        if (!(X1 >= 0 && X2 >= 0))
            return false;

        if (!(2 * X1 * X1 + 3 * X2 * X2 <= 6))
            return false;

        return  true;
    }
}