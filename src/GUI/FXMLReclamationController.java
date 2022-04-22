/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Model.Reclamation;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServiceReclamation;
import utils.MyDb;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;

/**
 * FXML Controller class
 *
 * @author Gloria
 */
public class FXMLReclamationController implements Initializable {

    @FXML
    private TextField tfIdCommande;
    @FXML
    private TextField TFSujet;
    @FXML
    private TextArea TADescription;
    @FXML
    private DatePicker PickerDate;
    @FXML
    private ComboBox<String> cbCategorie;
    @FXML
    private TableView<Reclamation> TBReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> TBIdcommande;
    @FXML
    private TableColumn<Reclamation, String> TBCategorie;
    @FXML
    private TableColumn<Reclamation, String> TBSujet;
    @FXML
    private TableColumn<Reclamation, String> TBDescription;
    @FXML
    private TableColumn<Reclamation, String> TBDate;
ServiceReclamation sr = new ServiceReclamation();

 ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
final ObservableList<Reclamation> data = FXCollections.observableArrayList();
    public static Reclamation reclamationActuel;

        Reclamation reclamation ;
    /**
     * Initializes the controller class.
     */
 ObservableList<Reclamation> ReclaimList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reclamation, Integer> tbId;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         PickerDate.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
        
    cbCategorie.setItems(FXCollections.observableArrayList("Location", "reparation", "montage"));
   afficher();
    
  
    }    
    
   private void afficher(){
          List<Reclamation> listreclamations = ServiceReclamation.getInstance().afficher();

        if (!listreclamations.isEmpty()) {
            for (int i = 0; i < listreclamations.size(); i++) {
                reclamation = listreclamations.get(i);
                reclamations.add(reclamation);
            }
        }
           

       TBIdcommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        TBSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        TBCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        TBDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       TBDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
       tbId.setCellValueFactory(new PropertyValueFactory<>("id"));
       reclamationActuel= null;

        TBReclamation.setItems(reclamations); 
   }

    
     @FXML
    void modifierReclamation(ActionEvent event) {
        
         Reclamation r = TBReclamation.getSelectionModel().getSelectedItem();
//int id= Integer.parseInt(tfId.getText());
        int Idcommande = Integer.parseInt(tfIdCommande.getText());
        String categorie= cbCategorie.getSelectionModel().getSelectedItem();
        String sujet= TFSujet.getText();
        String Description = TADescription.getText();
        String date= PickerDate.getValue().toString();
      ServiceReclamation src= new ServiceReclamation();
        src.modifier(new Reclamation(r.getId(),Idcommande,categorie,sujet,Description,date));
        init();
    }

    @FXML
    void supprimerReclamation(ActionEvent event) {
        Reclamation reclamationActuelle = TBReclamation.getSelectionModel().getSelectedItem();
        ServiceReclamation sr= new ServiceReclamation();
        sr.supprimer(reclamationActuelle);
     init();
    
    }
    
    public void refreshTable() {
        
        data.clear();
        List<Reclamation> listreclamations = ServiceReclamation.getInstance().afficher();
       // TBReclamation.setItems(listreclamations); 
        
    }
    
     public void updateTable() {
       
List<Reclamation> listreclamations = ServiceReclamation.getInstance().afficher();
        TBIdcommande.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        TBCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        TBSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
       TBDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        TBDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBReclamation.setItems(reclamations);
    
    }

    public void init() {
        updateTable();
        tfIdCommande.clear();
        cbCategorie.setValue(null);
        TFSujet.clear();
        TADescription.clear();
        PickerDate.setValue(null);
        reclamations.clear();
        afficher();
        
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) {
         int idCommande = Integer.parseInt(tfIdCommande.getText()) ;
        String categorie= cbCategorie.getSelectionModel().getSelectedItem();
        String sujet= TFSujet.getText();
        String description= TADescription.getText();
        String date= PickerDate.getValue().toString();

        if(categorie.isEmpty()||sujet.isEmpty()||description.isEmpty())
        {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("veuilez remplir tous les champs");
            alert.showAndWait();
        }
        else
        {
             ServiceReclamation sr= new ServiceReclamation();
        sr.ajouter(new Reclamation(idCommande,categorie,sujet,description,date));
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Reclamation ajoutée!");
                   
        }
        init();
    }

  
    
    
}
