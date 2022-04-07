/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relouadesktop;

import Model.AvisReparation;
import Model.Montage;
import Model.Reparation;
import Services.ServiceAvisReparation;
import Services.ServiceMontage;
import Services.ServiceReparation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Arij Hajji
 */
public class RelouaDesktop extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Afficher");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                  ServiceReparation sp =  new ServiceReparation();

        System.out.println(sp.afficher().toString());
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Affichage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
                 ServiceReparation p =  new ServiceReparation();
                  java.util.Date dt = new java.util.Date();
           java.text.SimpleDateFormat sdf = 
            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String currentTime = sdf.format(dt);
           java.time.LocalDate.now();
                 //ServiceMontage p2 =  new ServiceMontage();
                 ServiceAvisReparation p3 =  new ServiceAvisReparation();
                                // p3.ajouter(new AvisReparation(129,1,"carte", "bugs ","fdgdgfd"));
//p3.supprimer(3);
//p3.modifier(new AvisReparation(5,"bonjour"));

                  System.out.println(p3.afficher());
  //System.out.println(p2.afficher());
                // p2.ajouter(new Montage("carte", "bugs ","fdgdgfd","gdfg","fgfd","en cours",500,"dgfgdd",1));
                  //System.out.println(p2.afficher());
//p2.supprimer(114);
        //p2.modifier(new Montage(114,"bonjour"));

                 p.ajouter(new Reparation("carte", "bugs ","fdgdgfd",java.time.LocalDate.now().toString(),"+21690197079","en cours","arij.hajji@esprit.tn",1));
                // ServiceReparation sp =  new ServiceReparation();
  System.out.println(p.afficher());
//p.supprimer(129);
//sp.modifier(new Reparation(127,"bonjour"));
 // System.out.println(sp.afficher());

    
    }
    
}
