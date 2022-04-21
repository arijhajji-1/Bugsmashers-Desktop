package com.example.gestionproduitlast;

import Model.Category;
import Model.ProduitAcheter;
import Services.ServiceCategory;
import Services.ServiceProduitAcheter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class categorie implements Initializable {
    @FXML
    TextField TFlabel;
    private int idCategorie;
    private boolean update;

    public void effectuer(ActionEvent actionEvent) {
        String nom;
        nom = TFlabel.getText();

        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Insert ", "Insert", JOptionPane.YES_NO_OPTION);
        if (nom.isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("champs vide");
            errorAlert.setContentText("vous devez remplir les champs");
            errorAlert.showAndWait();
        } else {
            if (opt == 0 && update == false) {
                ServiceCategory pa = new ServiceCategory();


                pa.ajouter(new Category(0, nom));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Category added");
                alert.showAndWait();

            } else {
                ServiceCategory pa = new ServiceCategory();


                pa.modifier(new Category(idCategorie, nom));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Category modified");
                alert.showAndWait();

            }


        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    void setValues(String label, int id) {

        idCategorie= id;

        TFlabel.setText(label);

    }
}
