package edu.BarSU.Model.Variants.V9;

/**
 * Created by Govor Alexander on 07.05.2017.
 */
public class Data {
    public static double func(double X1, double X2) {
        return 0.5*X1*X1+0.5*X2*X2-X1-2*X2+5;
    }

    public static boolean isOptimal(double X1, double X2) {
        if (!(X1 >= 0 && X2 >= 0))
            return false;

        if (!(2*X1+3*X2>=6))
            return false;

        if (!(X1+4*X2>=5))
            return false;

        return  true;
    }

    public static String func(){
        return "0.5*x**2+0.5*y**2-x-2*y+5";
    }

    public static String condition() {
        return "($1>=0 &&" +
                " $2>=0 &&" +
                " 2*$1+3*$2>=6) &&" +
                " $1+4*$2>=5";
    }

}