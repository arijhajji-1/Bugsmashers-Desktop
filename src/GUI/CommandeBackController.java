/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Commande;
import Services.Alerte;
import Services.ServiceCommande;
import Services.mail;
import java.awt.event.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CommandeBackController implements Initializable {

    @FXML
    private TableView<Commande> tcommande2;
    @FXML
    private Button btnmodifier2;
    @FXML
    private Button btnsupp2;
    @FXML
    private TableColumn<Model.Commande, Integer> colid;
    @FXML
    private TableColumn<Model.Commande, String> colnom;
    @FXML
    private TableColumn<Model.Commande, String> colprenom;
    @FXML
    private TableColumn<Model.Commande, String> colpaiment;
    @FXML
    private TableColumn<Model.Commande, String> coladresse;
    @FXML
    private TableColumn<Model.Commande, Integer> coltelephone;
    @FXML
    private TextField tfrech;
    @FXML
    private ComboBox<String> trienom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcommande2.setEditable(true);
  ServiceCommande sp = new ServiceCommande();

colid.setCellValueFactory(new PropertyValueFactory<Model.Commande, Integer>("id"));
colnom.setCellValueFactory(new PropertyValueFactory<Model.Commande, String>("nom"));
colprenom.setCellValueFactory(new PropertyValueFactory<Model.Commande, String>("prenom"));
colpaiment.setCellValueFactory(new PropertyValueFactory<Model.Commande, String>("paiment"));
coladresse.setCellValueFactory(new PropertyValueFactory<Model.Commande, String>("adresse"));
coltelephone.setCellValueFactory(new PropertyValueFactory<Model.Commande, Integer>("telephone"));
colnom.setCellFactory(TextFieldTableCell.<Model.Commande> forTableColumn());
  colnom.setOnEditCommit((CellEditEvent<Model.Commande, String> event) -> {
            TablePosition<Model.Commande, String> pos = event.getTablePosition();
            
            String nom = event.getNewValue();
            
            int row = pos.getRow();
            Model.Commande commandes = event.getTableView().getItems().get(row);
            commandes.setNom(nom);
            sp.modifier(commandes);
        });
     ObservableList<Model.Commande> list = FXCollections.observableArrayList(sp.afficher());

      
 

    tcommande2.setItems(list);
tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
        ServiceCommande sp1 = new ServiceCommande();
        Model.Commande u1 =new Model.Commande();
        String nom = tfrech.getText();
        u1.setNom(nom);
        u1.setPrenom(nom);
        u1.setAdresse(nom);
         try{
              int cin1 = Integer.parseInt(nom);
             u1.setId(cin1);
         }
   catch(Exception e){}
    //   LBshow.setText(nom);
          ObservableList<Model.Commande> list1 = FXCollections.observableArrayList(sp1.rechstream(u1));

    tcommande2.setItems(list1);
    if(tfrech.getText().trim().isEmpty()){    tcommande2.setItems(list);}
   ;
});
      trienom.getItems().setAll("prenom", "nom", "adresse");

    // bind the selected fruit label to the selected fruit in the combo box.
  //  LBshow.textProperty().bind(trinom.getSelectionModel().selectedItemProperty());

    // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
      trienom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

      @Override 
      public void changed(ObservableValue<? extends String> selected, String oldFruit, String newFruit) {
     if(newFruit=="prenom"){  ObservableList<Model.Commande> list2 = FXCollections.observableArrayList(sp.tristreamprenom());
       tcommande2.setItems(list2);}
     if(newFruit=="nom"){  ObservableList<Model.Commande> list2 = FXCollections.observableArrayList(sp.tristreamnom());
       tcommande2.setItems(list2);}
     if(newFruit=="adresse"){  ObservableList<Model.Commande> list2 = FXCollections.observableArrayList(sp.tristreamadresse());
       tcommande2.setItems(list2);}
    }  });  
             
    }
    @FXML
    void annulercommande(ActionEvent event) {
ServiceCommande sp= new ServiceCommande();
        Model.Commande commandes = tcommande2.getSelectionModel().getSelectedItem();
    
sp.supprimer(commandes);
Alerte.display("RelouaTeam", "Commande supprimer");
 ObservableList<Model.Commande> list = FXCollections.observableArrayList(sp.afficher());

    tcommande2.setItems(list);


    }


    
}
