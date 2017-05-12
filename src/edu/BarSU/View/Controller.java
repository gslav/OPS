package edu.BarSU.View;

import static edu.BarSU.Model.Variants.V6.Data.condition;
import static edu.BarSU.Model.Variants.V6.Data.func;

import edu.BarSU.Model.SettingsData;

import edu.BarSU.Controller.Methods.*;
import edu.BarSU.Controller.LinesLevelModule.IsolinesGnuPlot;
import edu.BarSU.Controller.LinesLevelModule.LineLevel;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;

/**
 * Created on 11.05.2017.
 */
public class Controller {
    @FXML
    private TextField tfX1;
    @FXML
    private TextField tfX2;
    @FXML
    private TextField tfH;
    @FXML
    private TextField tfE;

    @FXML
    private void startDirectGrid() {
        try {
            startMethod(
                    new DirectGrid(),
                    new SettingsData(Double.valueOf(tfH.getText()), Double.valueOf(tfE.getText())));
        } catch (Exception Ex) {
            new Alert(Alert.AlertType.ERROR, "ЧТо-то пошло не так =(").show();
        }
    }

    @FXML
    private void startMonteCarlo() {
        try {
            startMethod(
                    new MonteCarlo(),
                    new SettingsData(Double.valueOf(tfH.getText()), Double.valueOf(tfE.getText())));
        } catch (Exception Ex) {
            new Alert(Alert.AlertType.ERROR, "ЧТо-то пошло не так =(").show();
        }
    }

    @FXML
    private void startHookJeeves() {
        try {
            startMethod(
                    new HookJeeves(),
                    new SettingsData(
                            Double.valueOf(tfH.getText()),
                            Double.valueOf(tfE.getText()),
                            new double[] {Double.valueOf(tfX1.getText()), Double.valueOf(tfX2.getText())}));
        } catch (Exception Ex) {
            new Alert(Alert.AlertType.ERROR, "Что-то пошло не так =(").show();
        }
    }

    @FXML
    private void startOfPenaltyFunctions() {
        try {
            startMethod(
                    new OfPenaltyFunctions(),
                    new SettingsData(
                            Double.valueOf(tfH.getText()),
                            Double.valueOf(tfE.getText()),
                            new double[] {Double.valueOf(tfX1.getText()), Double.valueOf(tfX2.getText())}));
        } catch (Exception Ex) {
            new Alert(Alert.AlertType.ERROR, "ЧТо-то пошло не так =(").show();
        }
    }

    private void startMethod(Lab Method, SettingsData data) throws Exception {
        Lab TempMethod = Method;

        ObservableList<LineLevel> linesLevel = TempMethod.Method(data);

        if (linesLevel.isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Точка за пределами ОДР").show();
            return;
        }

        double[] solution = TempMethod.getSolution();

        // генерация скрипта и запуск gnuplot
        IsolinesGnuPlot.preSet(func(), condition(), solution);
        IsolinesGnuPlot.runPlot();
    }
}
