package GUI;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Model.Commande;
import Model.Livraison;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.ServiceCommande;
import services.ServiceLivraison;

public class AjoutLivraisonController implements Initializable {
	 ServiceLivraison sl = new ServiceLivraison();
	    Commande commande;
	    @FXML
	    private Button btn_ajouter;

	    @FXML
	    private ComboBox<String> cblivr;

	    @FXML
	    private ComboBox<String> cbpaie;

	    @FXML
	    private ComboBox<String> cbregion;

	    @FXML
	    private DatePicker pickerdate;

	    @FXML
	    private TextField tfdescription;
	
	
	
	  @FXML
	    private void ajouterLivraison(ActionEvent event) {
	        
	    //    int Idcommande = Integer.parseInt(tfIdcommandebu.getText());
	        String modpaie= cbpaie.getSelectionModel().getSelectedItem();
	        String modlivr= cblivr.getSelectionModel().getSelectedItem();
	                
	        String region = cbregion.getSelectionModel().getSelectedItem();
	        String date= pickerdate.getValue().toString();
	        String description= tfdescription.getText();
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
	             
	             ServiceCommande sc= new ServiceCommande();
	             sc.ajouter(commande);
	        sl.ajouter(new Livraison(modpaie,modlivr,region,description,date,commande));
	          Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                    alert.setTitle("Livraison ajoutée!");
	                   
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
	        cbpaie.getItems().addAll("cash","chèque","carte");
	       
	cblivr.setItems(FXCollections.observableArrayList("à domicile","au magasin"));
	        cbregion.setItems(FXCollections.observableArrayList("Petite Ariana", "l'aouina", "Lac 1" ,"Lac 2", "Menzah 1", "Menzah 6","enkhilet","raoued","nour jaffa"));
		 
	}
}
