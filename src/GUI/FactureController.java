/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Model.Facture;
import Model.Commande;
import Model.ProduitLouer;
import Services.Alerte;
import Services.ServiceCommande;
import Services.ServiceFacture;
import Services.ServiceLocation;
import Services.ServiceProduitLouerProduit;
import Services.pdf;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
@FXML
    private TextField tfrech;
 @FXML
    private ComboBox<String> triDate;


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
tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
        ServiceFacture sp1 = new ServiceFacture();
        Facture u1 =new Facture();
        String nom = tfrech.getText();
        u1.setDateF(nom);
         try{
              int cin1 = Integer.parseInt(nom);
             u1.setId(cin1);
         }
   catch(Exception e){}
    //   LBshow.setText(nom);
          ObservableList<Facture> list1 = FXCollections.observableArrayList(sp1.rechstream(u1));

    tfacture.setItems(list1);
    if(tfrech.getText().trim().isEmpty()){    tfacture.setItems(list);}
   ;
});
      triDate.getItems().setAll("dateF");

    // bind the selected fruit label to the selected fruit in the combo box.
  //  LBshow.textProperty().bind(trinom.getSelectionModel().selectedItemProperty());

    // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
      triDate.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

      @Override 
      public void changed(ObservableValue<? extends String> selected, String oldFruit, String newFruit) {
     if(newFruit=="dateF"){  ObservableList<Facture> list2 = FXCollections.observableArrayList(sp.tristreamCommandeid());
       tfacture.setItems(list2);}
    }  });
    }  
@FXML
    void affichefacture(ActionEvent event) {
 ServiceFacture sp = new ServiceFacture();

colid.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("id"));
colcommandeid.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("commande_id"));
coldate.setCellValueFactory(new PropertyValueFactory<Model.Facture, String>("dateF"));
colremise.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("remise"));
coltotal.setCellValueFactory(new PropertyValueFactory<Model.Facture, Integer>("total"));
pdf pp=new pdf();   
pp.imprimer();
Alerte.display("RelouaTeam", "Generation de fichier PDF avec success");
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
Alerte.display("RelouaTeam", "facture ajouter avec succes");
ObservableList<Model.Facture> list = FXCollections.observableArrayList(sp.afficher());
tfacture.setItems(list);
    }

    @FXML
    void modifierfacture(ActionEvent event) {
ServiceFacture sp = new ServiceFacture();
     Model.Facture f = tfacture.getSelectionModel().getSelectedItem();
     sp.modifier(new Model.Facture(f.getId(),idcommande.getValue(),Integer.parseInt(tfremise.getText()),Integer.parseInt(tfta.getText()),datefct.getValue().toString()));
Alerte.display("RelouaTeam", "facture modifier avec succes ");
ObservableList<Model.Facture> list = FXCollections.observableArrayList(sp.afficher());

    tfacture.setItems(list);
    }

    @FXML
    void supprimerfacture(ActionEvent event) {
ServiceFacture sp= new ServiceFacture();
        Model.Facture factures = tfacture.getSelectionModel().getSelectedItem();
    
sp.supprimer(factures);
Alerte.display("RelouaTeam", "facture supprimer");
 ObservableList<Model.Facture> list = FXCollections.observableArrayList(sp.afficher());

    tfacture.setItems(list);
    }  
    
}
