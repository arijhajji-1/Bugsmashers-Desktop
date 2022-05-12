package controller;

import Services.ServiceProduitAcheterProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Stats implements Initializable {
    @FXML
    PieChart pieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();

        pieChart.setData(pieChartData);
        Map<String,Integer> m = ServiceProduitAcheterProduit.getInstance().produitCategorie();
        System.out.println(m);
        for (Map.Entry<String,Integer> entry : m.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        pieChart.setLabelLineLength(10);
        pieChart.setLegendSide(Side.LEFT);


    }
}
