package edu.BarSU.Model.Variants.V6;

/**
 * Created by Govor Alexander on 22.03.2017.
 */
public class Data {
    public static double func(double X1, double X2) {
        return Math.pow(X1, 2) + Math.pow(X2, 2)- 4 * X1 - 2 * X2;
    }

    public static boolean isOptimal(double X1, double X2) {
        if (!(X1 >= 0 && X2 >= 0))
            return false;

        if (!(2 * X1 + X2 >= 4))
            return false;

        if (!(X1 + 2 * X2 >= 6))
            return false;

        return  true;
    }

    public static String func(){
        return "x**2 + y**2 - 4 * x - 2 * y";
    }

    public static String condition() {
        return "($1>=0 && " +
                "$2>=0 && " +
                "(2*$1+$2>=4) && " +
                "($1+2*$2>=6))";
    }
}
