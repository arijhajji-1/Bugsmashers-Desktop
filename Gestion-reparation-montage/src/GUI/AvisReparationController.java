/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.AffichageReparationController.reparationActuelle;
import Model.AvisReparation;
import Model.Reparation;
import Services.ServiceAvisReparation;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class AvisReparationController implements Initializable {

    @FXML
    private TextField descrip;
    @FXML
    private Button add;
    Reparation reparation;
int idrep;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterAvis(ActionEvent event) {
             
		
	
                   ServiceAvisReparation rep = new ServiceAvisReparation();
		rep.ajouter(new AvisReparation(idrep,descrip.getText(),"arij","arij.hajji@esprit.tn",1));
                System.out.println(idrep);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("reparation added");
                                alert.showAndWait();
        clean();
    }
     private void clean() {
       
    descrip.setText(null);
        
    }
      void id(int id)
     {
         idrep=id;
     }
     
}
