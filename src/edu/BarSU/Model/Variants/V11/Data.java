package edu.BarSU.Model.Variants.V11;

/**
 * Created by gslav on 07.05.2017.
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

    public static String func(){
        return "2*x-x*x+y*y";
    }

    public static String condition() {
        return "($1>=0 && $2>=0 && 2*$1*$1+3*$2*$2<=6)";
    }

}