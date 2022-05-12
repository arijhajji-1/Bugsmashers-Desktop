/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Model.Commande;
import Services.Alerte;
import Services.LoginSession;
import Services.ServiceCommande;
import Services.mail;
import java.awt.event.MouseEvent;

import controller.MarketController;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    private TextField tfprodn;
    @FXML
    private TextField tfprodprix;
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
@FXML
    private ComboBox<String> trinom;
@FXML
    private TextField tfrech;
    @FXML
    private Button btnannuler;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnimprimer;
    @FXML
    private Button btnliv;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfprodn.setText(MarketController.produitact.getNom());
        tfprodprix.setText(String.valueOf(MarketController.produitact.getPrix())+" TND");
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
tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
        ServiceCommande sp1 = new ServiceCommande();
        Commande u1 =new Commande();
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
          ObservableList<Commande> list1 = FXCollections.observableArrayList(sp1.rechstream(u1));

    tcommande.setItems(list1);
    if(tfrech.getText().trim().isEmpty()){    tcommande.setItems(list);}
   ;
});
      trinom.getItems().setAll("prenom", "nom", "adresse");

    // bind the selected fruit label to the selected fruit in the combo box.
  //  LBshow.textProperty().bind(trinom.getSelectionModel().selectedItemProperty());

    // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
      trinom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

      @Override 
      public void changed(ObservableValue<? extends String> selected, String oldFruit, String newFruit) {
     if(newFruit=="prenom"){  ObservableList<Commande> list2 = FXCollections.observableArrayList(sp.tristreamprenom());
       tcommande.setItems(list2);}
     if(newFruit=="nom"){  ObservableList<Commande> list2 = FXCollections.observableArrayList(sp.tristreamnom());
       tcommande.setItems(list2);}
     if(newFruit=="adresse"){  ObservableList<Commande> list2 = FXCollections.observableArrayList(sp.tristreamadresse());
       tcommande.setItems(list2);}
    }  });  
             
    } 
@FXML
    void ajouterCommande(ActionEvent event) {
    ServiceCommande sp= new ServiceCommande();
    
/*tfnom.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\sa-zA-Z*")) {
            tfnom.setText(newValue.replaceAll("[^\sa-zA-Z]", ""));
        }
    });*/
/*tftelephone.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\d*")) {
            tftelephone.setText(newValue.replaceAll("[^\d]", ""));
        }
    });*/

if ((tfnom.getText().isEmpty()) || (tfprenom.getText().isEmpty()) || (tfpaiment.getText().isEmpty()) ||(tfadresse.getText().isEmpty()) ||(tftelephone.getText().isEmpty()))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Champ(s) vide(s)");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
                    }
    sp.ajouter(new Commande(tfnom.getText(),tfprenom.getText(),tfpaiment.getText(),tfadresse.getText(),Integer.parseInt(tftelephone.getText()),LoginSession.UID));
mail ss=new mail();
ss.envoyer();
Alerte.display("RelouaTeam", "Commande passer avec succes merci pour votre confiance :D");
ObservableList<Commande> list = FXCollections.observableArrayList(sp.afficher());
tcommande.setItems(list);

    }
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
Alerte.display("RelouaTeam", "Commande supprimer");
 ObservableList<Commande> list = FXCollections.observableArrayList(sp.afficher());

    tcommande.setItems(list);


    }

@FXML
    void modifiercommande(ActionEvent event) {
ServiceCommande sp = new ServiceCommande();
     Commande c = tcommande.getSelectionModel().getSelectedItem();
     sp.modifier(new Commande(c.getId(),tfnom.getText(),tfprenom.getText(),tfpaiment.getText(),tfadresse.getText(),Integer.parseInt(tftelephone.getText()),LoginSession.UID));
Alerte.display("RelouaTeam", "Votre Commande modifier avec succes Bienvenue :D");
ObservableList<Commande> list = FXCollections.observableArrayList(sp.afficher());

    tcommande.setItems(list);
    }

@FXML
    void imprimer(ActionEvent event) {
      PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.tcommande;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }
        }

    }

    @FXML
    private void affichercommande(SortEvent<Commande> event) {
    }
public static Commande commandeact;
    @FXML
    private void livraison(ActionEvent event) {
        ServiceCommande sp = new ServiceCommande();
        
     Commande c = tcommande.getSelectionModel().getSelectedItem();
     commandeact=c;
     FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/AjoutLivraison.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
       
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
//ObservableList<Commande> list = FXCollections.observableArrayList(sp.afficher());
    }
    
    

}

