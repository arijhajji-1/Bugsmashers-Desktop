package GUI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPTable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import Model.Commande;
import Model.Livraison;
import Model.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Services.ServiceCommande;
import Services.ServiceLivraison;
import Services.ServiceReclamation;
import utils.MyDb;

public class LivraisonFXMLController implements Initializable {

    @FXML
    private TableView<Livraison>  TBLivraison;

    @FXML
    private ComboBox<String> cbFiltre;

    @FXML
    private ComboBox<String> cbLivr;

    @FXML
    private ComboBox<String> cbPaie;

    @FXML
    private ComboBox<String> cbRegion;

    @FXML
    private DatePicker pickerdate;

    @FXML
    private TableColumn<Livraison, String> tbDate;

    @FXML
    private TableColumn<Livraison, String> tbDescription;

    @FXML
    private TableColumn<Livraison,Integer> tbId;

    @FXML
    private TableColumn<Livraison, String> tbLivr;

    @FXML
    private TableColumn<Livraison, String> tbPaie;

    @FXML
    private TableColumn<Livraison, String> tbRegion;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfRechercher;
    
    @FXML
    private Button btn_Print;

   

    @FXML
    private Button btn_modifier;

    @FXML
    private Button btn_pdf;

    @FXML
    private Button btn_rechercher;

    @FXML
    private Button btn_stat;

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
    private void modifierLivraison(ActionEvent event) {
        
         Livraison l = TBLivraison.getSelectionModel().getSelectedItem();
//int id= Integer.parseInt(tfId.getText());
       // int Idcommande = Integer.parseInt(tfIdcommandebu.getText());
        String modpaie= cbPaie.getItems().toString();
        String modlivr= cbLivr.getItems().toString();
        String region = cbRegion.getItems().toString();
        String date= pickerdate.getValue().toString();
        String description= tfDescription.getText();
       
    l.setId(l.getId());
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
       
    	 List<Livraison> listlivraisons = ServiceLivraison.getInstance().afficher();
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
                //tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
        //tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBLivraison.setItems(livraisons);
            }
         
        if(filtre=="description")
            {
               ObservableList<Livraison> livraisons = sl.filterDescription(tfRechercher.getText()); 
            //    tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
       // tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBLivraison.setItems(livraisons);
            }
         if(filtre=="mode de paiement")
            {
               ObservableList<Livraison> livraisons = sl.filterPaiement(tfRechercher.getText()); 
               // tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
       // tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBLivraison.setItems(livraisons);
            }
         if(filtre=="date")
            {
               ObservableList<Livraison> livraisons = sl.filterDate(tfRechercher.getText()); 
               // tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
       // tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
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
       // tbPaie.setCellValueFactory(new PropertyValueFactory<>("mod_paie"));
       // tbLivr.setCellValueFactory(new PropertyValueFactory<>("mod_livr"));
        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TBLivraison.setItems(livraisons);
    }

    @FXML
    private void pdfLivraison(ActionEvent event) throws SQLException, com.itextpdf.text.DocumentException {
    	
    	  String path = "";

          JFileChooser j = new JFileChooser();
          j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          int x = j.showSaveDialog(j);
          if (x == JFileChooser.APPROVE_OPTION) {
              path = j.getSelectedFile().getPath();

          }

          com.itextpdf.text.Document doc = new  com.itextpdf.text.Document();
          try {
              PdfWriter.getInstance( doc, new FileOutputStream(path + "/livraison.pdf"));
              (doc).open();

              PdfPTable table = new PdfPTable(5);
             
              table.addCell("mode_paiement");
              table.addCell("mode_livraison");
              table.addCell("region");
              table.addCell("description");
              table.addCell("Date");

              ServiceLivraison sl = new ServiceLivraison();
              for (int i = 0; i < sl.rowLivraison(); i++) {

                  String mode_paiement = TBLivraison.getColumns().get(0).getCellObservableValue(i).getValue().toString();
                  String mode_livraison = TBLivraison.getColumns().get(1).getCellObservableValue(i).getValue().toString();
                  String region= TBLivraison.getColumns().get(2).getCellObservableValue(i).getValue().toString();
                  String description = TBLivraison.getColumns().get(3).getCellObservableValue(i).getValue().toString();
                  String Date = TBLivraison.getColumns().get(4).getCellObservableValue(i).getValue().toString();
               

                  table.addCell(mode_paiement);
                  table.addCell(mode_livraison);
                  table.addCell(region);
                  table.addCell(description);
                  table.addCell(Date);
                

              }

              //doc.add( table);

          } catch (FileNotFoundException ex) {

          }

          doc.close();
    	
    	
      
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
    	
    	try {

            Parent parent = FXMLLoader.load(getClass().getResource("statistiques.fxml"));
            Scene scene = new Scene(parent);

           Stage stage = new Stage();
            stage.getIcons().add(new Image("\\ressources\\icon statistiques.png"));
            stage.setTitle("Statistiques");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	
    	
    }

		@Override
		public void initialize(URL url, ResourceBundle rb) {
			
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
	        /* tbPaie.setCellValueFactory(new PropertyValueFactory<>("modPaie"));
	        tbLivr.setCellValueFactory(new PropertyValueFactory<>("mode_livr"));
	        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
	        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
	       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
	       tbId.setCellValueFactory(new PropertyValueFactory<>("id"));
	       livraisonActuelle= null;*/
	        tbId.setCellValueFactory(new PropertyValueFactory<>("id"));
	        tbRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
	        tbDate.setCellValueFactory(new PropertyValueFactory<>("date"));
	       tbDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

	        TBLivraison.setItems(livraisons); 
	    }
    }



