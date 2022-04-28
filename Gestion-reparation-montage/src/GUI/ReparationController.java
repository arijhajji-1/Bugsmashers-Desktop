/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Category;
import Model.Reparation;
import Services.ServiceCategory;
import Services.ServiceReparation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import services.LoginSession;
import services.UsersSession;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.BadWords;
import utils.mail;
/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class ReparationController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> category;
    @FXML
    private TextField type;
    @FXML
    private TextArea description;
    @FXML
    private Button affiche;
    private boolean update;
    int reparationId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
date.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
  /* type.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
           type.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });*/

          List<Category> listCategory = ServiceCategory.getInstance().afficher();
        listCategory.forEach(Categories -> {
            category.getItems().add(Categories.getLabel());
        });
    }    
 private boolean controlerFormulaire() {
        boolean isOk = true;
        List<String> messageErreur = new ArrayList<>();
     
        if (type.getText() == null || type.getText().isEmpty()) {
            isOk = false;
            messageErreur.add("Le champ \"type\" est obligatoire");
        }
        if (description.getText() == null || description.getText().isEmpty()) {
            isOk = false;
            messageErreur.add("Le champ \"description\" est obligatoire");
        }  
        if (date.getValue() == null || date.getValue().toString().isEmpty()) {
            isOk = false;
            messageErreur.add("Le champ \"Date\" est obligatoire");
        }
         if(category.getValue()==null||category.getValue().isEmpty())
         {
                isOk = false;
            messageErreur.add("Le champ \"category\" est obligatoire");  
         }
        if(!isOk) {
             String tilte = "CHAMP OBLIGATOIRE";
            String message = "champs vide";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
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
       private void ajouterReparation(ActionEvent event) throws IOException {
          
          if ( controlerFormulaire()) {
if(update==false){
              
        ServiceReparation rep = new ServiceReparation();
		
		
		
		rep.ajouter(new Reparation((String) category.getValue(),
                        type.getText(),
                        description.getText(),
                        date.getValue().toString(),LoginSession.Telephone,LoginSession.email,LoginSession.UID));
        mail s = new mail();
      String  msg="Cher client votre demande de reparation est en cours de traitement";
      String subject="Demande de reparation";
                s.send(LoginSession.email,subject,msg);
                                String tilte = "reparation";
            String message = "reparation ajouté";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.FADE;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
           
        clean();
                  
                 }
                       else{
                    ServiceReparation rep = new ServiceReparation();

                   rep.modifier(new Reparation(reparationId,(String) category.getValue(),
                        type.getText(),
                        description.getText(),
                        date.getValue().toString())) ;
                   
                   
                  String tilte = "reparation";
            String message = "reparation modifié";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
                    clean();
                 }
  
                         
       }
	
    }

    @FXML
    private void AfficherReparation(MouseEvent event) {
                FXMLLoader main = new FXMLLoader(getClass().getResource("AffichageReparation.fxml"));
                try {
                    Parent root =(Parent) main.load();
                   
                    affiche.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    @FXML
    private void typecontrole(KeyEvent event) {
        
       
    }
private void clean() {
        type.setText(null);
        date.setValue(null);
        description.setText(null);
        category.setValue(null);
        
    }
    void setTextField(int id, String ty, String datee, String desc, String categ) {

        reparationId= id;
       type.setText(ty);
       datee= String.valueOf(date.getValue());
 //date.setText(datee);
       // date.setText(date);
        description.setText(desc);
        category.setValue(categ);

    }
     void setUpdate(boolean b) {
        this.update = b;

    }
}
