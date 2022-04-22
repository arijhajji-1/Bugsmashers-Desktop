/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;
//package com.lowagie.text.pdf;


import static GUI.FXMLReclamationController.reclamationActuel;
import Model.Commande;
import Model.Livraison;
import Model.Reclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import services.ServiceCommande;
import services.ServiceLivraison;
import services.ServiceReclamation;
import utils.MyDb;

/**
 * FXML Controller class
 *
 * @author Gloria
 */
public class FXMLDeliveryController implements Initializable {

    @FXML
    private DatePicker pickerdate;
    @FXML
    private TextField tfDescription;
    @FXML
    private ComboBox<String> cbPaie;
    @FXML
    private ComboBox<String> cbLivr;
    @FXML
    private ComboBox<String> cbRegion;
    private TextField tfId;
    private TextField tfIdcommandebu;
    @FXML
    private TableView<Livraison> TBLivraison;
    @FXML
    private TableColumn<Livraison,Integer> tbId;
    @FXML
    private TableColumn<Livraison, String> tbPaie;
    @FXML
    private TableColumn<Livraison, String> tbLivr;
    @FXML
    private TableColumn<Livraison, String> tbRegion;
    @FXML
    private TableColumn<Livraison, String> tbDescription;
    @FXML
    private TableColumn<Livraison, String> tbDate;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;

    ServiceLivraison sl = new ServiceLivraison();
    Commande commande;
  private Connection cnx = MyDb.getInstance().getCnx();
    private PreparedStatement pre, stm;
    private Statement ste;

 ObservableList<Livraison> livraisons = FXCollections.observableArrayList();
    public static Livraison livraisonActuelle;

       Livraison  livraison ;
    @FXML
    private Button btn_rechercher;
    @FXML
    private Button btn_Print;
    @FXML
    private TextField tfRechercher;
    @FXML
    private ComboBox<String> cbFiltre;
    @FXML
    private Button btn_trier;
    @FXML
    private Button btn_pdf;
    @FXML
    private Button btn_stat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //-------------contrôle de saisie sur la date--------------------------
        pickerdate.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
        //--------------------------combo box----------------------------
        cbPaie.getItems().addAll("cash","chèque","carte");
             cbFiltre.getItems().addAll("date","region","mode de paiement","description");
cbLivr.setItems(FXCollections.observableArrayList("à domicile","au magasin"));
        cbRegion.setItems(FXCollections.observableArrayList("Petite Ariana", "l'aouina", "Lac 1" ,"Lac 2", "Menzah 1", "Menzah 6","enkhilet","raoued","nour jaffa"));
    afficher();
    } 
    
    private void afficher(){
          List<Livraison> listlivraisons = ServiceLivraison.getInstance().afficher();

        if (!listlivraisons.isEmpty()) {
            for (int i = 0; i < listlivraisons.size(); i++) {
                livraison = listlivraisons.get(i);
                livraisons.add(livraison);
            }
        }
         tbPaie.setCellValueFactory(new PropertyValueFactory<>("modPaie"));
        tbLivr.setCellValueFactory(new PropertyValueFactory<>("mode_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
       tbId.setCellValueFactory(new PropertyValueFactory<>("id"));
       livraisonActuelle= null;

        TBLivraison.setItems(livraisons); 
    }

    @FXML
    private void ajouterLivraison(ActionEvent event) {
        
    //    int Idcommande = Integer.parseInt(tfIdcommandebu.getText());
        String modpaie= cbPaie.getSelectionModel().getSelectedItem();
        String modlivr= cbLivr.getSelectionModel().getSelectedItem();
                
        String region = cbRegion.getSelectionModel().getSelectedItem();
        String date= pickerdate.getValue().toString();
        String description= tfDescription.getText();
          commande= new Commande(9,"celia","école","esprit","photo.png");
         if(modpaie.isEmpty()||modlivr.isEmpty()||region.isEmpty()||description.isEmpty())
        {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("veuilez remplir tous les champs");
            alert.showAndWait();
        }
        else
        {
             ServiceReclamation sr= new ServiceReclamation();
             ServiceCommande sc= new ServiceCommande();
             sc.ajouter(commande);
        sl.ajouter(new Livraison(modpaie,modlivr,region,description,date,commande));
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Livraison ajoutée!");
                   
        }
        init();
    }

    @FXML
    private void modifierLivraison(ActionEvent event) {
        
         Livraison l = TBLivraison.getSelectionModel().getSelectedItem();
int id= Integer.parseInt(tfId.getText());
       // int Idcommande = Integer.parseInt(tfIdcommandebu.getText());
        String modpaie= cbPaie.getItems().toString();
        String modlivr= cbLivr.getItems().toString();
        String region = cbRegion.getItems().toString();
        String date= pickerdate.getValue().toString();
        String description= tfDescription.getText();
       
l.setId(id);
     l.setDate(date);
     l.setDescription(description);
     l.setModlivr(modlivr);
     l.setModpaie(modpaie);
     l.setRegion(region);
      
        l.setCommande(commande);
        sl.modifier(l);
       init();
        
    }

    @FXML
    private void supprimerLivraison(ActionEvent event) {
        Livraison livraisonActuelle = TBLivraison.getSelectionModel().getSelectedItem();
        ServiceLivraison sl= new ServiceLivraison();
        sl.supprimer(livraisonActuelle);
       init();
        
    }
    
    public void updateTable() {
       
List<Reclamation> listreclamations = ServiceReclamation.getInstance().afficher();
        tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
        tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBLivraison.setItems(livraisons);
    
    }

     
    public void init() {
        updateTable();
        cbPaie.setValue(null);
        cbRegion.setValue(null);
        cbLivr.setValue(null);
        tfDescription.clear();
       pickerdate.setValue(null);
        livraisons.clear();
        afficher();
        
    }

    @FXML
    private void rechercheLivraison(ActionEvent event) {
      
        String filtre = cbFiltre.getSelectionModel().getSelectedItem();
         if(filtre=="region")
            {
               ObservableList<Livraison> livraisons = sl.filterRegion(tfRechercher.getText()); 
                tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
        tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBLivraison.setItems(livraisons);
            }
         
        if(filtre=="description")
            {
               ObservableList<Livraison> livraisons = sl.filterDescription(tfRechercher.getText()); 
                tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
        tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBLivraison.setItems(livraisons);
            }
         if(filtre=="mode de paiement")
            {
               ObservableList<Livraison> livraisons = sl.filterPaiement(tfRechercher.getText()); 
                tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
        tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBLivraison.setItems(livraisons);
            }
         if(filtre=="date")
            {
               ObservableList<Livraison> livraisons = sl.filterDate(tfRechercher.getText()); 
                tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
        tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBLivraison.setItems(livraisons);
            }
         if(tfRechercher.getText().isEmpty())
         {
             init();
         }
      
        } 
        
      

    @FXML
    private void imprimer(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.TBLivraison;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }
        }
    }
   
    @FXML
    private void triLivraison(ActionEvent event) {
        
          String tri = cbFiltre.getSelectionModel().getSelectedItem();
        ObservableList<Livraison> livraisons = sl.tri(tri);
        tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
        tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBLivraison.setItems(livraisons);
    }

    @FXML
    private void pdfLivraison(ActionEvent event) throws SQLException {
      
    }

    @FXML
    private void OnClickedPrint(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.TBLivraison;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }
        }
        
    }

    @FXML
    private void StatLivraison(ActionEvent event) {
    }
    
      
      

    }



    

