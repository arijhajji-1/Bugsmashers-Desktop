/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Model.Location;
import Model.ProduitLouer;
import Services.Alerte;
import Services.LoginSession;
import Services.ServiceCommande;
import Services.ServiceLocation;
import Services.ServiceProduitLouerProduit;
import Services.mail;
import Services.maillocation;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.MarketLController;
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
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
 @FXML
    private Button btnajoutl;
@FXML
    private TableColumn<Location, String> coldeb;

    @FXML
    private TableColumn<Location, String> colfin;

    @FXML
    private TableColumn<Location, Integer> colid;

    @FXML
    private TableColumn<Location, Integer> coltotal;

@FXML
    private TableView<Location> tlocation;


    @FXML
    private DatePicker datedeb;

    @FXML
    private DatePicker datefin;

    @FXML
    private TextField tftotal;
@FXML
    private ComboBox<String> triDate;
@FXML
    private TextField tfrech;
public static ProduitLouer pl;

float x=0;
public void setProduitlouer(ProduitLouer prod){
    this.pl=prod;
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
tftotal.setEditable(false);
        // TODO
ServiceLocation sp = new ServiceLocation();
datedeb.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
datefin.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
        tftotal.setText(MarketLController.produitact.getPrix() +"TND");
        x=MarketLController.produitact.getPrix();
/*for (ProduitLouer produit : ServiceProduitLouerProduit.getInstance(). afficher()) {
            produitlouer.getItems().add(produit.getNom());
produitlouer.setOnAction((event)->
{
x+=produit.getPrix();
String a=Float.toString(x);
tftotal.setText(a+"TND");
});
        }    */
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldeb.setCellValueFactory(new PropertyValueFactory<>("db"));
        colfin.setCellValueFactory(new PropertyValueFactory<>("df"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("totall"));
coldeb.setCellFactory(TextFieldTableCell.<Model.Location> forTableColumn());
  coldeb.setOnEditCommit((TableColumn.CellEditEvent<Model.Location, String> event) -> {
            TablePosition<Model.Location, String> pos = event.getTablePosition();
            
            String nom = event.getNewValue();
            
            int row = pos.getRow();
            Model.Location locations = event.getTableView().getItems().get(row);
            locations.setDb(nom);
            sp.modifier(locations);
        });

        ObservableList<Location> list = FXCollections.observableArrayList(sp.afficher());
        tlocation.setItems(list);
        tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
        ServiceLocation sp1 = new ServiceLocation();
        Model.Location u1 =new Model.Location ();
        String nom = tfrech.getText();
        u1.setDb(nom);
        u1.setDf(nom);
        
         try{
              int cin1 = Integer.parseInt(nom);
             u1.setId(cin1);
         }
   catch(Exception e){}
    //   LBshow.setText(nom);
          ObservableList<Model.Location> list1 = FXCollections.observableArrayList(sp1.rechstream(u1));

    tlocation.setItems(list1);
    if(tfrech.getText().trim().isEmpty()){    tlocation.setItems(list);}
   ;
});
      triDate.getItems().setAll("db", "df");

    // bind the selected fruit label to the selected fruit in the combo box.
  //  LBshow.textProperty().bind(trinom.getSelectionModel().selectedItemProperty());

    // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
      triDate.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

      @Override 
      public void changed(ObservableValue<? extends String> selected, String oldFruit, String newFruit) {
     if(newFruit=="db"){  ObservableList<Model.Location> list2 = FXCollections.observableArrayList(sp.tristreamdatedeb());
       tlocation.setItems(list2);}
     if(newFruit=="df"){  ObservableList<Model.Location> list2 = FXCollections.observableArrayList(sp.tristreamdateF());
       tlocation.setItems(list2);}
    }  });



    }    
@FXML
    void afficherlocation(ActionEvent event) {
  ServiceLocation sp = new ServiceLocation();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldeb.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        colfin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total_l"));
        ObservableList<Location> list = FXCollections.observableArrayList(sp.afficher());
        tlocation.setItems(list);

}
  

    @FXML
    void ajouterlocation(ActionEvent event) {
          ServiceLocation sp=new ServiceLocation();
if ((datedeb.getValue().toString().isEmpty()) || (datefin.getValue().toString().isEmpty()) || (tftotal.getText().isEmpty()))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Champ(s) vide(s)");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
                    }
        sp.ajouter(new Model.Location((int)x,datedeb.getValue().toString(),datefin.getValue().toString(),LoginSession.UID));
     maillocation ss=new maillocation();
ss.envoyer();
Alerte.display("RelouaTeam", "Location passer avec succes merci pour votre confiance :D");
ObservableList<Model.Location> list = FXCollections.observableArrayList(sp.afficher());
tlocation.setItems(list);

    }
@FXML
    void supprimerlocation(ActionEvent event) {
ServiceLocation sp= new ServiceLocation();
        Model.Location locations = tlocation.getSelectionModel().getSelectedItem();
    
sp.supprimer(locations);
Alerte.display("RelouaTeam", "Location supprimer :''''");
 ObservableList<Model.Location> list = FXCollections.observableArrayList(sp.afficher());

    tlocation.setItems(list);

    }
@FXML
    void modifierlocation(ActionEvent event) {
ServiceLocation sp = new ServiceLocation();
     Model.Location l = tlocation.getSelectionModel().getSelectedItem();
     sp.modifier(new Model.Location(l.getId(),Integer.parseInt(tftotal.getText()),datedeb.getValue().toString(),datefin.getValue().toString(),LoginSession.UID));
Alerte.display("RelouaTeam", "Location modifier avec succes Bienvenue :D");
ObservableList<Model.Location> list = FXCollections.observableArrayList(sp.afficher());

    tlocation.setItems(list);

    }


    
}
