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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.BadWords;
import utils.ResizeHelper;

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
    private void AjouterAvis(ActionEvent event) throws IOException {
             
		  if (BadWords.filterText(descrip.getText()) ) {

              Notifications notificationBuilder = Notifications.create()
                        .title("Alerte").text("Notre application n'utilise pas ce genre de mots").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.CENTER_RIGHT)
                        .onAction((ActionEvent event1) -> {
                            System.out.println("clicked ON ");
                        });
                notificationBuilder.showError();
                notificationBuilder.show();

           
        }
                  else if (descrip.getText().isEmpty()) {
                         Notifications notificationBuilder = Notifications.create()
                        .title("Alerte").text("Champ vide").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.CENTER_RIGHT)
                        .onAction((ActionEvent event1) -> {
                            System.out.println("clicked ON ");
                        });
                notificationBuilder.showError();
                notificationBuilder.show();
                  }
                  else {
                   ServiceAvisReparation rep = new ServiceAvisReparation();
		rep.ajouter(new AvisReparation(descrip.getText(),"arij.hajji@esprit.tn","arij",idrep,1));
                //System.out.println(idrep);
        Notifications notificationBuilder = Notifications.create()
                        .title("Success").text("Avis ajouté avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.CENTER_LEFT)
                        .onAction((ActionEvent event1) -> {
                            System.out.println("clicked ON ");
                        });
                notificationBuilder.darkStyle();
                notificationBuilder.show();
        clean();
       
    }}
     private void clean() {
       
    descrip.setText(null);
        
    }
      void id(int id)
     {
         idrep=id;
     }
     
}
