package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import Model.Livraison;
import Model.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import Services.ServiceReclamation;

public class ListReclamationBackController implements Initializable{
	
	 @FXML
	    private TableView<Reclamation> TBReclamation;
	    @FXML
	    private TableColumn<Reclamation, Integer> TBIdcommande;
	    @FXML
	    private TableColumn<Reclamation, String> TBCategorie;
	    @FXML
	    private TableColumn<Reclamation, String> TBSujet;
	    @FXML
	    private TableColumn<Reclamation, String> TBDescription;
	    @FXML
	    private TableColumn<Reclamation, String> TBDate;
	    @FXML
	    private TableColumn<Reclamation, Integer> tbId;
	    @FXML
	    private Button btn_statRec;
	    @FXML
	    private Button btn_recherche;

	    @FXML
	    private Button btn_supprimer;
	    @FXML
	    private TextField tfRecherche;
	    
	    ServiceReclamation sr = new ServiceReclamation();

	    ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
	   final ObservableList<Reclamation> data = FXCollections.observableArrayList();
	       public static Reclamation reclamationActuel;

	           Reclamation reclamation ;
	 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		afficher();
	}
	
	@FXML
    void rechercheReclamation(ActionEvent event) {
		
	      ObservableList<Reclamation> reclamations = sr.rechercheSujet(tfRecherche.getText());

	       TBIdcommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
	        TBSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
	        TBCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
	        TBDate.setCellValueFactory(new PropertyValueFactory<>("date"));
	       TBDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
	       tbId.setCellValueFactory(new PropertyValueFactory<>("id"));
	       reclamationActuel= null;

	        TBReclamation.setItems(reclamations); 

    }
	
	 @FXML
	    void statReclamation(ActionEvent event) throws IOException {

		  Parent parent = FXMLLoader.load(getClass().getResource("statreclamation.fxml"));
          Scene scene = new Scene(parent);

         Stage stage = new Stage();
          //stage.getIcons().add(new Image("\\Ressources\\Logo.png"));
          stage.setScene(scene);
          stage.initStyle(StageStyle.UTILITY);
          stage.show();
	    }

	    @FXML
	    void supprimerReclamation(ActionEvent event) {
	    	
	    	  Reclamation reclamationActuelle = TBReclamation.getSelectionModel().getSelectedItem();
	          ServiceReclamation sr= new ServiceReclamation();
	          sr.supprimer(reclamationActuelle);
	          Notifications notificationBuilder= Notifications.create()
	      			.title("Reclamation Supprimee!")
	      			.text("vous avez supprime une reclamation")
	      			.graphic(null)
	      			.hideAfter(Duration.seconds(5))
	      			.position(Pos.TOP_RIGHT);
	      	notificationBuilder.showWarning();;
	       init();

	    }
	    
	    public void updateTable() {
	        
	    	List<Reclamation> listreclamations = ServiceReclamation.getInstance().afficher();
	    	        TBIdcommande.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
	    	        TBCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
	    	        TBSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
	    	       TBDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
	    	        TBDate.setCellValueFactory(new PropertyValueFactory<>("date"));
	    	        TBReclamation.setItems(reclamations);
	    	    
	    	    }

	    	    public void init() {
	    	        updateTable();
	    	        reclamations.clear();
	    	        afficher();
	    	        
	    	    }
	    	    
	    	    private void afficher(){
	    	          List<Reclamation> listreclamations = ServiceReclamation.getInstance().afficher();

	    	        if (!listreclamations.isEmpty()) {
	    	            for (int i = 0; i < listreclamations.size(); i++) {
	    	                reclamation = listreclamations.get(i);
	    	                reclamations.add(reclamation);
	    	            }
	    	        }
	    	           

	    	       TBIdcommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
	    	        TBSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
	    	        TBCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
	    	        TBDate.setCellValueFactory(new PropertyValueFactory<>("date"));
	    	       TBDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
	    	       tbId.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	       reclamationActuel= null;

	    	        TBReclamation.setItems(reclamations); 
	    	   }

}
