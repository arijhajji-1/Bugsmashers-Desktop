/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Montage;
import Model.ProduitAcheter;
import Services.ServiceMontage;
import Services.ServiceProduitAcheter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class MontageController implements Initializable {

    @FXML
    private ComboBox<String> proc;
    @FXML
    private ComboBox<String> cartemere;
    @FXML
    private ComboBox<String> cartegraph;
    @FXML
    private ComboBox<String> stockagesupp;
    @FXML
    private ComboBox<String> disquedur;
    @FXML
    private ComboBox<String> boitier;
    @FXML
    private Button afficher;
  private boolean update;
    int montageId;
     float x=0;

    @FXML
    private TextField montant;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           montant.setEditable(false);
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==1){
            proc.getItems().add(produit.getNom());
      proc.setOnAction((event) -> {
   

   

    x+=produit.getPrix();
   // System.out.println(a);
   String a=Float.toString(x);
   montant.setText(a+"dt");
});
         }}
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==7){
            cartemere.getItems().add(produit.getNom());
          cartemere.setOnAction((event) -> {

          x+=produit.getPrix();
             //  System.out.println(b);
 String a=Float.toString(x);
 montant.setText(a+"dt");
          
    });
         }
        }
         
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==2)
            cartegraph.getItems().add(produit.getNom());
           cartegraph.setOnAction((event) -> {
   
  
          x+=produit.getPrix();
             //  System.out.println(c);
 String a=Float.toString(x);
    montant.setText(a+"dt");
           
    });
        }
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==9){
            stockagesupp.getItems().add(produit.getNom());
           stockagesupp.setOnAction((event) -> {
   

          x+=produit.getPrix();
             //  System.out.println(d);
 String a=Float.toString(x);
   montant.setText(a+"dt");
            
    });
         }  }
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==9){
            disquedur.getItems().add(produit.getNom());
           disquedur.setOnAction((event) -> {
   

          x+=produit.getPrix();
        //  System.out.println(e);
           String a=Float.toString(x);
   montant.setText(a+"dt");
    });
         }}
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==6){
            boitier.getItems().add(produit.getNom());
           boitier.setOnAction((event) -> {

   
          x+=produit.getPrix();
             //  System.out.println(f);

             String a=Float.toString(x);
   montant.setText(a+"dt");
    });
         }  }
 //x+=a+b+c+d+e+f;
   
   
    }    
private boolean controlerFormulaire() {
        boolean isOk = true;
        List<String> messageErreur = new ArrayList<>();
        if ( cartegraph.getValue() == null || cartegraph.getValue().isEmpty()) {
            isOk = false;
            messageErreur.add("Le champ \"Carte Graphique\" est obligatoire");
        }
        if (disquedur.getValue() == null || disquedur.getValue().isEmpty()) {
            isOk = false;
            messageErreur.add("Le champ \"Disque dur\" est obligatoire");
        }  
        if (cartemere.getValue() == null ||cartemere.getValue().isEmpty()) {
            isOk = false;
            messageErreur.add("Le champ \"Carte mere\" est obligatoire");
        }
         if(proc.getValue()==null||proc.getValue().isEmpty())
         {
                isOk = false;
            messageErreur.add("Le champ \"processeur\" est obligatoire");  
         }
          if(boitier.getValue()==null||boitier.getValue().isEmpty())
         {
                isOk = false;
            messageErreur.add("Le champ \"boitier\" est obligatoire");  
         }
           if(stockagesupp.getValue()==null||stockagesupp.getValue().isEmpty())
         {
                isOk = false;
            messageErreur.add("Le champ \"Stockage supplementaire\" est obligatoire");  
         }
        if(!isOk) {
            Alert erreur = new Alert(AlertType.ERROR);
            erreur.setTitle("Erreur ! ");
            StringBuilder sb = new StringBuilder();
            messageErreur.stream().forEach((x) -> sb.append("\n" + x));
            erreur.setHeaderText(sb.toString());
            erreur.showAndWait();
        }      
        return isOk;
    }
    @FXML
    private void ajoutermontage(ActionEvent event) {
         

              
        if ( controlerFormulaire()) {
            if(update==false){
                 ServiceMontage rep = new ServiceMontage();
		
		
		
		rep.ajouter(new Montage((String) proc.getValue(),
                        (String) cartemere.getValue(),
                        (String) cartegraph.getValue(),
                        (String) disquedur.getValue(),(String) stockagesupp.getValue(),(String) boitier.getValue(),(int)x,"arij.hajji@esprit.tn",1));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("montage added");
                                alert.showAndWait();
                 x=0;               
        clean();
       
                   }
        
        else {
      
                  
                 
                     
       ServiceMontage rep = new ServiceMontage();

                   rep.modifier(new Montage(montageId,(String) proc.getValue(),
                        (String) cartemere.getValue(),
                        (String) cartegraph.getValue(),
                        (String) disquedur.getValue(),(String) stockagesupp.getValue(),(String) boitier.getValue(),0,"arij.hajji@esprit.tn",1));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("montage modified");
                                alert.showAndWait();
                 }
  
                         }
		
        
    }

    @FXML
    private void afficherMontage(MouseEvent event) {
          FXMLLoader main = new FXMLLoader(getClass().getResource("AffichageMontage.fxml"));
                try {
                    Parent root =(Parent) main.load();
                   
                    afficher.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
   private void clean() {
       
      proc.setValue(null);
      cartemere.setValue(null);
      cartegraph.setValue(null);
      stockagesupp.setValue(null);
      disquedur.setValue(null);
      boitier.setValue(null); 
        
    }
    void setTextField(int id, String pr, String cr, String cg, String ss,String dd,String b) {

        montageId= id;
  
        proc.setValue(pr);
         cartemere.setValue(cr);
      cartegraph.setValue(cg);
      stockagesupp.setValue(ss);
      disquedur.setValue(dd);
      boitier.setValue(b); 

    }
     void setUpdate(boolean b) {
        this.update = b;

    } 
}
