package com.example.gestionproduitlast;

import Model.AvisProduit;
import Services.LoginSession;
import Services.ServiceAvisProduit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            //ServiceAvisProduit.getInstance().ajouter(new AvisProduit(0,0,31,4
              //          ,"nom","email","description"));
            //System.out.println(ServiceAvisProduit.getInstance().afficher("produit_louer_id",31));
            Parent root = FXMLLoader.load(getClass().getResource("/ProduitGUI/home.fxml"));
            LoginSession.firstName="houssem";
            LoginSession.email="houssem@gmail.com";
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("----- Gestion Produits ------");
            stage.show();
        } catch(IOException e){
            System.out.println("here "+e.getMessage());
        }
        /*try {
            Parent root = FXMLLoader.load(getClass().getResource("/ProduitGUI/produitA.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Gestion Produits");
            stage.show();
        } catch(IOException e){
            System.out.println(e);
        }*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}