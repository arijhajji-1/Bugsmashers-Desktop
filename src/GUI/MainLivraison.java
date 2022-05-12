/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Gloria
 */
public class MainLivraison extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         try {
          Parent root = FXMLLoader.load(getClass().getResource("AjoutLivraison.fxml"));
          //stage.getIcons().add(new Image("\\Ressources\\icon statistiques.png"));

            Scene scene = new Scene(root);
          //  Image image= new Image("ressources/icon livraison(1).png");
          //  primaryStage.getIcons().add(image);
            primaryStage.setScene(scene);
         

           primaryStage.show();
        } 
         catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}