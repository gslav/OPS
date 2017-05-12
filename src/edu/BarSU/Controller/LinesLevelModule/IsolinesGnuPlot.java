package edu.BarSU.Controller.LinesLevelModule;

import edu.BarSU.Model.SettingsData;
import edu.BarSU.Controller.Support.Coordinates;

import java.io.IOException;
import java.io.PrintWriter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;

/**
 * Created on 08.05.17.
 *
 * Download GNUPLOT https://sourceforge.net/projects/gnuplot/
 */
public class IsolinesGnuPlot {
    // указываем путь к скрипту и файлу для данных
    // Сохраняются во временную определенную в системе
    // для Windows в пути дожен быть обратный экранированный слэш '\\'
    // для Unix, Mac в пути должен быть прямой слэш '/'
    private static String pathToScript = System.getProperty("java.io.tmpdir")+"/myfunc.plt";
    private static String pathToData = System.getProperty("java.io.tmpdir") + "/contour.dat";

    // указать путь к исполнямой программе, если не установлена в системе
    private static String pathToGnuPlot = "gnuplot";

    public static void preSet(String func, String condition, double[] solution, ArrayList<Coordinates>... methodWay) {
        // округляем значение до 3 знаков после запятой
        Double Zmin = new BigDecimal(solution[2]).setScale(3, RoundingMode.HALF_UP).doubleValue();
        //
        SettingsData data = new SettingsData(0,0);
        double Xmin = data.Xmin;
        double Xmax = data.Xmax;
        //
        // TODO координаты точек
        if (~methodWay.length == 0)
            ;

        try {
            PrintWriter writer = new PrintWriter(pathToScript, "UTF-8");

            writer.println(
                    "f(x,y)=" + func + "\n" +
                            "set xrange[" + Xmin + ":" + Xmax + "]" + "\n" +
                            "set yrange[" + Xmin + ":" + Xmax + "]" + "\n" +
                            "set contour base" + "\n" +
                            "set isosample 250, 250" + "\n" +
                            "set cntrparam level incremental -50, 0.5, 50" + "\n" +
                            "unset surface" + "\n" +
                            "set table '" + pathToData + "'" + "\n" +

                            "splot f(x,y)" + "\n" +
                            "unset table" + "\n" +

                            "reset" + "\n" +
                            "set xrange[" + Xmin + ":" + Xmax + "]" + "\n" +
                            "set xrange[" + Xmin + ":" + Xmax + "]" + "\n" +
                            "set grid" + "\n" +
                            "set autoscale" + "\n" +

                            "xPos=" + solution[0] + "\n" +
                            "yPos=" + solution[1] + "\n" +
//                            "zPos=" + solution[2] + "\n" +
                            "set label at xPos, yPos \"" + Zmin + "\" point pointtype 7 pointsize 1" + "\n" +

                            "unset key" + "\n" +

                            "p '" + pathToData + "'\\" + "\n" +
                            " using (" + condition + " ? $1 : 1/0):\\" + "\n" +
                            "(" + condition + " ? $2 : 1/0)\\" + "\n" +
                            " w l lt -1 lw 1.5" + "\n" +
                            "pause -1" + "\n" +
                            "exit"
            );
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
