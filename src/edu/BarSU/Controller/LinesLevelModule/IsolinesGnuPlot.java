package edu.BarSU.Controller.LinesLevelModule;

import edu.BarSU.Model.SettingsData;
import edu.BarSU.Controller.Support.Coordinates;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;

/**
 * Created by gslav on 08.05.17.
 *
 * Download GNUPLOT https://sourceforge.net/projects/gnuplot/
 */
public class IsolinesGnuPlot {
    // указываем путь к скрипту и файлу для данных
    // Сохраняются во временную определенную в системе
    private static String pathToScript = System.getProperty("java.io.tmpdir") + "/myfunc.plt";
    private static String pathToDataContour = System.getProperty("java.io.tmpdir") + "/contour.dat";
    private static String pathToDataPoints = System.getProperty("java.io.tmpdir") + "/points.dat";

    // указать путь к исполнямой программе, если не установлена в системе
    private static String pathToGnuPlot = "gnuplot";

    // TODO изменить тип списка с координатами на нужный (ArrayList<Coordinates> ???)
    public static void preSet(String func, String condition, double[] solution, ArrayList<Coordinates> ... methodWay) {
        // округляем значение до 3 знаков после запятой
        //Double Zmin = new BigDecimal(solution[2]).setScale(3, RoundingMode.HALF_UP).doubleValue();
        //
        SettingsData data = new SettingsData(0,0);
        double Xmin = data.Xmin;
        double Xmax = data.Xmax;

        StringBuilder plotStr = new StringBuilder();

        plotStr.append("plot ");
        plotStr.append("'").append(pathToDataContour).append("' ");
        plotStr.append("with lines notitle ");
        plotStr.append(", '").append(pathToDataContour).append("' ");
        plotStr.append("every ::5::5 with labels boxed notitle ");

        if (~methodWay.length == 0) {
            try {
                PrintWriter writer = new PrintWriter(pathToDataPoints, "UTF-8");
                for (int i=0; i < methodWay.length; ++i) {
                    writer.println(
                            " " + methodWay[0].get(i).getX1() +
                            " " + methodWay[0].get(i).getX1() +
                            " " + methodWay[0].get(i).getY());
                }
                writer.close();

            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // построение линий по точкам для метода Хука-Дживса и метода штрафных функций
            plotStr.append(", '").append(pathToDataPoints).append("' ");
            plotStr.append("with lines notitle ");
            // TODO добавить отображение координат для каждой точки
            //plotStr.append(", ");
        }

        try {
            PrintWriter writer = new PrintWriter(pathToScript, "UTF-8");

            writer.println("f(x,y)= (" + condition + " ? " + func + " : 1/0)");
            writer.println("labels(x,y) = f(x,y)");
            writer.println("x1="+solution[0]);
            writer.println("x2="+solution[1]);

            writer.println("set contour base");
            writer.println("unset surface");

            writer.println("set cntrparam levels auto 15");

            writer.println("set xrange[" + Xmin + ":" + Xmax + "]");
            writer.println("set yrange[" + Xmin + ":" + Xmax + "]");

            writer.println("set isosample 250, 250");

            writer.println("set table '" + pathToDataContour + "'");
            writer.println("splot f(x,y) with lines notitle");
            writer.println("unset table");

            writer.println("reset");

            writer.println("set title" +
                    " 'X1min = " + new BigDecimal(solution[0]).setScale(3, RoundingMode.HALF_UP).doubleValue() +
                    ";  X2min = " + new BigDecimal(solution[1]).setScale(3, RoundingMode.HALF_UP).doubleValue() +
                    ";  F(X1min, X2min) = " + new BigDecimal(solution[2]).setScale(3, RoundingMode.HALF_UP).doubleValue() + "'");

            writer.println("set view map");
            writer.println("set grid");

            writer.println("set xrange[" + Xmin + ":" + Xmax + "]");
            writer.println("set yrange[" + Xmin + ":" + Xmax + "]");

            writer.println("set style textbox opaque margins  0.5,  0.5 noborder");
            writer.println("set cntrlabel  format '%8.3g' font ',7'");

            writer.println("set autoscale");
            writer.println("unset key");

            writer.println(plotStr.toString());
            writer.println("set label at x1, x2 \"Fmin\" boxed point pointtype 7 pointsize 1");

            writer.println("pause -1");
            writer.println("exit");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runPlot() throws IOException, InterruptedException {
        // указываем в конструкторе ProcessBuilder,
        // что нужно запустить программу gnuplot и с путем к скрипту в качестве параметра
        ProcessBuilder procBuilder = new ProcessBuilder(pathToGnuPlot, pathToScript);

        // перенаправляем стандартный поток ошибок на
        // стандартный вывод
        procBuilder.redirectErrorStream(true);

        // запуск программы
        Process process = procBuilder.start();

        // ждем пока завершится вызванная программа
        // и сохраняем код, с которым она завершилась в
        // в переменную exitVal
        //int exitVal = process.waitFor();
    }
}
