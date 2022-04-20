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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==1)
            proc.getItems().add(produit.getNom());
        }
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==7)
            cartemere.getItems().add(produit.getNom());
        }
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==2)
            cartegraph.getItems().add(produit.getNom());
        }
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==9)
            stockagesupp.getItems().add(produit.getNom());
        }
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==9)
            disquedur.getItems().add(produit.getNom());
        }
      for (ProduitAcheter produit : ServiceProduitAcheter.getInstance().afficher()) {
         if((int)Float.parseFloat(produit.getCategory())==6)
            boitier.getItems().add(produit.getNom());
        }
   
    }    

    @FXML
    private void ajoutermontage(ActionEvent event) {
          String cat= proc.getValue();

        String ty =  cartemere.getValue();

        String desc = cartegraph.getValue();

        String dat= disquedur.getValue();

              
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Insert ", "Insert", JOptionPane.YES_NO_OPTION);
        if ( desc.isEmpty() || ty.isEmpty()||dat.isEmpty()||cat.isEmpty()) {
Alert errorAlert = new Alert(AlertType.ERROR);
errorAlert.setHeaderText("champs vide");
errorAlert.setContentText("vous devez remplir les champs");
errorAlert.showAndWait();
                   }
        else { if (opt==0&&update==false){
       ServiceMontage rep = new ServiceMontage();
		
		
		
		rep.ajouter(new Montage((String) proc.getValue(),
                        (String) cartemere.getValue(),
                        (String) cartegraph.getValue(),
                        (String) disquedur.getValue(),(String) stockagesupp.getValue(),(String) boitier.getValue(),0,"arij.hajji@esprit.tn",1));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("reparation added");
                                alert.showAndWait();
        clean();
                  
                 }
                       else{
       ServiceMontage rep = new ServiceMontage();

                   rep.modifier(new Montage(montageId,(String) proc.getValue(),
                        (String) cartemere.getValue(),
                        (String) cartegraph.getValue(),
                        (String) disquedur.getValue(),(String) stockagesupp.getValue(),(String) boitier.getValue(),0,"arij.hajji@esprit.tn",1));
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
