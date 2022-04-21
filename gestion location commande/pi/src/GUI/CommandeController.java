/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Model.Commande;
import Services.ServiceCommande;
import java.awt.event.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CommandeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnajouter;

    @FXML
    private TableColumn<Commande, String> coladresse;

    @FXML
    private TableColumn<Commande, Integer> colid;

    @FXML
    private TableColumn<Commande, String> colnom;

    @FXML
    private TableColumn<Commande, String> colpaiment;

    @FXML
    private TableColumn<Commande, String> colprenom;

    @FXML
    private TableColumn<Commande, Integer> coltelephone;

    @FXML
    private TableView<Commande> tcommande;

    @FXML
    private TextField tfadresse;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfpaiment;

    @FXML
    private TextField tfprenom;

    @FXML
    private TextField tftelephone;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
tcommande.setEditable(true);
  ServiceCommande sp = new ServiceCommande();

colid.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));
colnom.setCellValueFactory(new PropertyValueFactory<Commande, String>("nom"));
colprenom.setCellValueFactory(new PropertyValueFactory<Commande, String>("prenom"));
colpaiment.setCellValueFactory(new PropertyValueFactory<Commande, String>("paiment"));
coladresse.setCellValueFactory(new PropertyValueFactory<Commande, String>("adresse"));
coltelephone.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("telephone"));
    colnom.setCellFactory(TextFieldTableCell.<Commande> forTableColumn());
  colnom.setOnEditCommit((CellEditEvent<Commande, String> event) -> {
            TablePosition<Commande, String> pos = event.getTablePosition();
            
            String nom = event.getNewValue();
            
            int row = pos.getRow();
            Commande commandes = event.getTableView().getItems().get(row);
            commandes.setNom(nom);
            sp.modifier(commandes);
        });
     ObservableList<Commande> list = FXCollections.observableArrayList(sp.afficher());
      
 

    tcommande.setItems(list);
             
    } 
@FXML
    void ajouterCommande(ActionEvent event) {
    ServiceCommande sp= new ServiceCommande();
if ((tfnom.getText().isEmpty()) || (tfprenom.getText().isEmpty()) || (tfpaiment.getText().isEmpty()) ||(tfadresse.getText().isEmpty()) ||(tftelephone.getText().isEmpty()))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Champ(s) vide(s)");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
                    }
    sp.ajouter(new Commande(tfnom.getText(),tfprenom.getText(),tfpaiment.getText(),tfadresse.getText(),Integer.parseInt(tftelephone.getText())));
ObservableList<Commande> list = FXCollections.observableArrayList(sp.afficher());
tcommande.setItems(list);
    }
@FXML
    void affichercommande(ActionEvent event) {
          ServiceCommande sp = new ServiceCommande();
colid.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));
colnom.setCellValueFactory(new PropertyValueFactory<Commande, String>("nom"));
colprenom.setCellValueFactory(new PropertyValueFactory<Commande, String>("prenom"));
colpaiment.setCellValueFactory(new PropertyValueFactory<Commande, String>("paiment"));
coladresse.setCellValueFactory(new PropertyValueFactory<Commande, String>("adresse"));
coltelephone.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("telephone"));   
ObservableList<Commande> list = FXCollections.observableArrayList(sp.afficher());
      
 

    tcommande.setItems(list);
    }
@FXML
    void annulercommande(ActionEvent event) {
ServiceCommande sp= new ServiceCommande();
        Commande commandes = tcommande.getSelectionModel().getSelectedItem();
    
sp.supprimer(commandes);
 ObservableList<Commande> list = FXCollections.observableArrayList(sp.afficher());

    tcommande.setItems(list);


    }

@FXML
    void modifiercommande(ActionEvent event) {
ServiceCommande sp = new ServiceCommande();
     Commande c = tcommande.getSelectionModel().getSelectedItem();
     sp.modifier(new Commande(c.getId(),tfnom.getText(),tfprenom.getText(),tfpaiment.getText(),tfadresse.getText(),Integer.parseInt(tftelephone.getText())));
ObservableList<Commande> list = FXCollections.observableArrayList(sp.afficher());

    tcommande.setItems(list);
    }


    

}

