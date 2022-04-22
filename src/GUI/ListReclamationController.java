/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import static GUI.GestionReclamationController.reclamationActuel;
import Model.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Gloria
 */
public class ListReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> TBReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> tbid_commande;
    @FXML
    private TableColumn<Reclamation, String> tbcategorie;
    @FXML
    private TableColumn<Reclamation, String> tbsujet;
    @FXML
    private TableColumn<Reclamation, String> tbdescription;
    @FXML
    private TableColumn<Reclamation, String> tbdate;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnAjouter;
 ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
    public static Reclamation reclamationActuel;

        Reclamation reclamation ;
        ObservableList<Reclamation> ReclaimList = FXCollections.observableArrayList();
        ServiceReclamation sr = new ServiceReclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Reclamation> listreclamations = ServiceReclamation.getInstance().afficher();

        if (!listreclamations.isEmpty()) {
            for (int i = 0; i < listreclamations.size(); i++) {
                reclamation = listreclamations.get(i);
                reclamations.add(reclamation);
            }
        }
           

       tbid_commande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        tbsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        tbcategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tbdate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tbdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
       reclamationActuel= null;

        TBReclamation.setItems(reclamations);
      
    }    

    @FXML
    private void supprimerReclamation(ActionEvent event) {
        Reclamation reclamationActuelle = TBReclamation.getSelectionModel().getSelectedItem();
        ServiceReclamation sr= new ServiceReclamation();
        sr.supprimer(reclamationActuelle);
        TBReclamation.refresh();
    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
        
      Reclamation reclaim= TBReclamation.getSelectionModel().getSelectedItem();
      FXMLLoader loader= new FXMLLoader();
      loader.setLocation(getClass().getResource("gestionReclamation.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ListReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
      GestionReclamationController gestionReclamationController= loader.getController();
      
        
        
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {
    }
    
}
