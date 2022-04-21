/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Model.Facture;
import Model.Commande;
import Model.ProduitLouer;
import Services.ServiceCommande;
import Services.ServiceFacture;
import Services.ServiceLocation;
import Services.ServiceProduitLouer;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FactureController implements Initializable {
@FXML
    private Button btnafact;

    @FXML
    private Button btnmfact;

    @FXML
    private Button btnsuppfact;

    @FXML
    private TableColumn<Facture, Integer> colcommandeid;

    @FXML
    private TableColumn<Facture, String> coldate;

    @FXML
    private TableColumn<Facture, Integer> colid;

    @FXML
    private TableColumn<Facture, Integer> colremise;

    @FXML
    private TableColumn<Facture, Integer> coltotal;

    @FXML
    private DatePicker datefct;

    @FXML
    private ComboBox<Integer> idcommande;

    @FXML
    private TableView<Facture> tfacture;

    @FXML
    private TextField tfremise;

    @FXML
    private TextField tfta;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
datefct.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
 ServiceFacture sp = new ServiceFacture();
for (Commande commandes : ServiceCommande.getInstance(). afficher()) {
            idcommande.getItems().add(commandes.getId());
}
colid.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("id"));
colcommandeid.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("commande_id"));
coldate.setCellValueFactory(new PropertyValueFactory<Model.Facture, String>("dateF"));
colremise.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("remise"));
coltotal.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("total"));   
ObservableList<Model.Facture> list = FXCollections.observableArrayList(sp.afficher());
      
 

    tfacture.setItems(list);
    }  
@FXML
    void affichefacture(ActionEvent event) {
 ServiceFacture sp = new ServiceFacture();

colid.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("id"));
colcommandeid.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("commande_id"));
coldate.setCellValueFactory(new PropertyValueFactory<Model.Facture, String>("dateF"));
colremise.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("remise"));
coltotal.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("total"));   
ObservableList<Model.Facture> list = FXCollections.observableArrayList(sp.afficher());
      
 

 tfacture.setItems(list);

    }

    @FXML
    void ajouterfacture(ActionEvent event) {
ServiceFacture sp= new ServiceFacture();
if ((tfremise.getText().isEmpty()) || (tfta.getText().isEmpty()))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Champ(s) vide(s)");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
                    }
    sp.ajouter(new Model.Facture(idcommande.getValue(),Integer.parseInt(tfremise.getText()),Integer.parseInt(tfta.getText()),datefct.getValue().toString()));
ObservableList<Model.Facture> list = FXCollections.observableArrayList(sp.afficher());
tfacture.setItems(list);
    }

    @FXML
    void modifierfacture(ActionEvent event) {
ServiceFacture sp = new ServiceFacture();
     Model.Facture f = tfacture.getSelectionModel().getSelectedItem();
     sp.modifier(new Model.Facture(f.getId(),idcommande.getValue(),Integer.parseInt(tfremise.getText()),Integer.parseInt(tfta.getText()),datefct.getValue().toString()));
ObservableList<Model.Facture> list = FXCollections.observableArrayList(sp.afficher());

    tfacture.setItems(list);
    }

    @FXML
    void supprimerfacture(ActionEvent event) {
ServiceFacture sp= new ServiceFacture();
        Model.Facture factures = tfacture.getSelectionModel().getSelectedItem();
    
sp.supprimer(factures);
 ObservableList<Model.Facture> list = FXCollections.observableArrayList(sp.afficher());

    tfacture.setItems(list);
    }  
    
}
