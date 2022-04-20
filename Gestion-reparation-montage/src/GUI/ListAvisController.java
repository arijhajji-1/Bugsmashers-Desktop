/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.AffichageMontageController.montageActuel;
import Model.AvisReparation;
import Model.Montage;
import Services.ServiceAvisReparation;

import Services.ServiceMontage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class ListAvisController implements Initializable {
 ObservableList<AvisReparation> avis = FXCollections.observableArrayList();
    public static AvisReparation AvisActuel;
    private final static int rowsPerPage = 2;
   AvisReparation avisRep;
    @FXML
    private TableColumn<AvisReparation, String> idrep;
    @FXML
    private TableColumn<AvisReparation, String> desc;
    @FXML
    private TableColumn<AvisReparation, String> nom;
    @FXML
    private TableColumn<AvisReparation, String> mail;
    @FXML
    private TableView<AvisReparation> tab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    loadDate();
     
    }
     private void refreshTable() {
          avis .clear();


                 List<AvisReparation> listAvis = ServiceAvisReparation.getInstance().afficher();

        if (!listAvis.isEmpty()) {
            for (int i = 0; i < listAvis.size(); i++) {
               AvisReparation AvisReparation = listAvis.get(i);
                avis.add(AvisReparation);
            }
        }
     tab.setItems(avis);
    }
       private void loadDate() {
        List<AvisReparation> listAvis = ServiceAvisReparation.getInstance().afficher();

        if (!listAvis.isEmpty()) {
            for (int i = 0; i < listAvis.size(); i++) {
               AvisReparation AvisReparation = listAvis.get(i);
                avis.add(AvisReparation);
            }
        }
                    refreshTable();

        idrep.setCellValueFactory(new PropertyValueFactory<>("idrep"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
  

       AvisActuel= null;

        tab.setItems(avis);
  
        //// Wrap the ObservableList in a FilteredList (initially display all data).
      

       
        // TODO
        // TODO
    }    


    

 @FXML
    private void selectAvis(MouseEvent event) {
                     AvisActuel = tab.getSelectionModel().getSelectedItem();

    }   
    
}
