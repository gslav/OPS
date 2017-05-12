package edu.BarSU.Model;

/**
 * Created by Govor Alexander on 29.03.2017.
 */
public class SettingsData {
    public double Xmin;
    public double Xmax;
    public double h;
    public double E;

    public double[] basisPoint;

    public int numberIterations;

    public SettingsData(double h, double e, double[] basisPoint) {
        Xmin   = -1;
        Xmax   =  4;
        numberIterations = 500; // 121
        //
        this.h = h;
        E      = e;

        this.basisPoint = basisPoint;
    }

    public SettingsData(double h, double e) {
        Xmin = -1;
        Xmax =  4;
        numberIterations = 500; // 121
        //
        this.h = h;
        E = e;
    }
}
