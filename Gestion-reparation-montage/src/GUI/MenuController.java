/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class MenuController implements Initializable {

    @FXML
    private Tab affichermon;
    @FXML
    private Tab afficherrep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void affichermon(Event event) {
         try {
          Parent root = FXMLLoader.load(getClass().getResource("Montage.fxml"));
        
        Scene scene = new Scene(root);
         
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void afficherrep(Event event) {
    }
    
}
