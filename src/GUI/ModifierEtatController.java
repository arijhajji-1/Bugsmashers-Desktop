/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Reparation;
import Services.ServiceReparation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import utils.sms;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class ModifierEtatController implements Initializable {
int reparationId;
    private boolean update;
    String numTelephone;
    Reparation reparation;

    @FXML
    private ComboBox<String> etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    etat.getItems().add("En cours");
                     etat.getItems().add("réparé");

        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws IOException{
        if(update){
         ServiceReparation rep = new ServiceReparation();

                   rep.modifierEtat(new Reparation(reparationId,(String)etat.getValue())) ; 
           
                sms s = new sms();
                s.send("Votre appreil est préte merci de le recupérer", numTelephone);
        }
    }
   
    void setTextField(int id,String et,String tel) {

        reparationId= id;
  
        etat.setValue(et);
        numTelephone=tel;
      

    }
     void setUpdate(boolean b) {
        this.update = b;

    } 
}
