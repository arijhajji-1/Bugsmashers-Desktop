/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import static GUI.FXMLReclamationController.reclamationActuel;
import Model.Reclamation;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author Gloria
 */
public class GestionReclamationController implements Initializable {

    @FXML
    private TextField tfIdcommande;
    @FXML
    private TextField TfSujet;
    @FXML
    private TextArea taDescription;
    @FXML
    private ComboBox<String> cbCategorie;
    @FXML
    private DatePicker pickerdate;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private TableView<Reclamation> TBReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> tbIdcommande;
    @FXML
    private TableColumn<Reclamation, String> tbCategorie;
    @FXML
    private TableColumn<Reclamation, String> tbSujet;
    @FXML
    private TableColumn<Reclamation, String> tbDescription;
    @FXML
    private TableColumn<Reclamation, String> tbDate;
    @FXML
    private Button supprimer;
    ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
    public static Reclamation reclamationActuel;

        Reclamation reclamation ;
        ObservableList<Reclamation> ReclaimList = FXCollections.observableArrayList();
        ServiceReclamation sr = new ServiceReclamation();

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbCategorie.setItems(FXCollections.observableArrayList("Location", "reparation", "montage"));
        List<Reclamation> listreclamations = ServiceReclamation.getInstance().afficher();

        if (!listreclamations.isEmpty()) {
            for (int i = 0; i < listreclamations.size(); i++) {
                reclamation = listreclamations.get(i);
                reclamations.add(reclamation);
            }
        }
           

       tbIdcommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        tbSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        tbCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
       reclamationActuel= null;

        TBReclamation.setItems(reclamations);
        setCellValueFromTableToTextfield();
    }    

    @FXML
    private void ajouterReclamation(ActionEvent event) {
        
        int idCommande = Integer.parseInt(tfIdcommande.getText()) ;
        String categorie= cbCategorie.getItems().toString();
        String sujet= tbSujet.getText();
        String description= taDescription.getText();
        String date= pickerdate.getValue().toString();

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
        
        
    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
        int idCommande = Integer.parseInt(tfIdcommande.getText()) ;
        String categorie= cbCategorie.getItems().toString();
        String sujet= tbSujet.getText();
        String description= taDescription.getText();
        String date= pickerdate.getValue().toString();
        sr.modifier(new Reclamation(idCommande,categorie,sujet,description,date));
        
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {
    }
    
    private void setCellValueFromTableToTextfield(){
        TBReclamation.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               Reclamation r= TBReclamation.getItems().get(TBReclamation.getSelectionModel().getSelectedIndex());
               //tfIdcommande.setText(r.getIdCommande());
               cbCategorie.setValue(r.getCategorie());
               tbSujet.setText(r.getSujet());
               taDescription.setText(r.getDescription());
               //pickerdate.setText(Date.valueOf(r.getDate()));
         
            }
            
        });
    }
    
    private void refreshTable(){
         TBReclamation.refresh();
         List<Reclamation> listreclamations = ServiceReclamation.getInstance().afficher();
          TBReclamation.setItems(reclamations);
    }
    
}
