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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

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

          List<Category> listCategory = ServiceCategory.getInstance().afficher();
        listCategory.forEach(Categories -> {
            category.getItems().add(Categories.getLabel());
        });
    }    

    @FXML
       private void ajouterReparation(ActionEvent event) throws IOException {
           String cat= category.getValue();

        String ty = type.getText();

        String desc = description.getText();

        String dat= date.getValue().toString();

              
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Insert ", "Insert", JOptionPane.YES_NO_OPTION);
        if ( desc.isEmpty() || ty.isEmpty()||dat.isEmpty()||cat.isEmpty()) {
Alert errorAlert = new Alert(AlertType.ERROR);
errorAlert.setHeaderText("champs vide");
errorAlert.setContentText("vous devez remplir les champs");
errorAlert.showAndWait();
                   }
        else { if (opt==0&&update==false){
        ServiceReparation rep = new ServiceReparation();
		
		
		
		rep.ajouter(new Reparation((String) category.getValue(),
                        type.getText(),
                        description.getText(),
                        date.getValue().toString(),"90197079","en cours","arij.hajji@esprit.tn",1));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("reparation added");
                                alert.showAndWait();
        clean();
                  
                 }
                       else{
                    ServiceReparation rep = new ServiceReparation();

                   rep.modifier(new Reparation(reparationId,(String) category.getValue(),
                        type.getText(),
                        description.getText(),
                        date.getValue().toString())) ;    
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
 //date.setText(datee);
       // date.setText(date);
        description.setText(desc);
        category.setValue(categ);

    }
     void setUpdate(boolean b) {
        this.update = b;

    }
}
