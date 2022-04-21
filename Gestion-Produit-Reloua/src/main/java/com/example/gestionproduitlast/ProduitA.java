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

import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;


public class ProduitA implements Initializable {
    @FXML
    TextField TFnom, TFprix, TFmarque, TFQte;
    @FXML
    TextArea TFdescription;
    @FXML
    ChoiceBox TFcategorie;
    private int idProduitA;
    private boolean update;

    public void ajouterProduit(ActionEvent actionEvent) {
        String nom,marque,description,category;
        int qte;
        float prix;
        nom = TFnom.getText();
        marque = TFmarque.getText();
        description = TFdescription.getText();
        category = (String) TFcategorie.getValue();
        qte = Integer.parseInt(TFQte.getText());
        prix = Float.parseFloat(TFprix.getText());

        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Insert ", "Insert", JOptionPane.YES_NO_OPTION);
        if ( nom.isEmpty() || marque.isEmpty()||category.isEmpty()||description.isEmpty()||qte==0||prix==0){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("champs vide");
            errorAlert.setContentText("vous devez remplir les champs");
            errorAlert.showAndWait();
        }
        else {if (opt==0&&update==false){
            ServiceProduitAcheter pa = new ServiceProduitAcheter();



            pa.ajouter(new ProduitAcheter(0,qte,prix,nom,description,"testimgpath",marque,"11"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("ProduitAcheter added");
            alert.showAndWait();

        }
        else{
            ServiceProduitAcheter pa = new ServiceProduitAcheter();



            pa.modifier(new ProduitAcheter(idProduitA,qte,prix,nom,description,"testimgpath",marque,"11"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("ProduitAcheter Modified");
            alert.showAndWait();}

        }


        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Category cat : ServiceCategory.getInstance().afficher()) {
                TFcategorie.getItems().add(cat.getLabel());
        }
        TFQte.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    TFQte.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    void setUpdate(boolean b) {
        this.update = b;

    }
    void setValues(int qte, String category, String marque, String description,String nom,int id,String imagePath,float prix) {

        idProduitA= id;

        TFcategorie.setValue(category);
        TFnom.setText(nom);
        TFQte.setText(String.valueOf(qte));
        TFdescription.setText(description);
        TFmarque.setText(marque);
        TFprix.setText(String.valueOf(prix));

    }
}
