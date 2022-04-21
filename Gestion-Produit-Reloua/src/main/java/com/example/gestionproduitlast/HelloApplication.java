package com.example.gestionproduitlast;

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
            Parent root = FXMLLoader.load(getClass().getResource("/ProduitGUI/home.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("-----Gestion Produits ------");
            stage.show();
        } catch(IOException e){
            System.out.println(e);
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