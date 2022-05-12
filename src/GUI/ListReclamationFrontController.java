package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import Services.ServiceReclamation;

public class ListReclamationFrontController implements Initializable {
	
	

    @FXML
    private TableColumn<Reclamation, String> tbcategorie;

    @FXML
    private TableColumn<Reclamation, String> tbdate;

    @FXML
    private TableColumn<Reclamation, String>tbdescription;

    @FXML
    private TableColumn<Reclamation, Integer> tbid_commande;

    @FXML
    private TableColumn<Reclamation, String> tbsujet;
	
	@FXML
    private TextField tfIdCommande;
    @FXML
    private TextField TFSujet;
    @FXML
    private TextArea TADescription;
    @FXML
    private DatePicker PickerDate;
    @FXML
    private ComboBox<String> cbCategorie;
	
	
	 @FXML
	    private TableView<Reclamation> TBReclamation;
	  
	   
	    @FXML
	    private Button btnModifier;
	    
	    ServiceReclamation sr = new ServiceReclamation();

	    ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
	   final ObservableList<Reclamation> data = FXCollections.observableArrayList();
	       public static Reclamation reclamationActuel;

	           Reclamation reclamation ;
	 

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		PickerDate.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
	        
	    cbCategorie.setItems(FXCollections.observableArrayList("Location", "reparation", "montage"));
		afficher();
	}
	
	 @FXML
	    void modifierReclamation(ActionEvent event) throws IOException {
		 
		 Image img=new Image("\\Ressources\\iconUpdate.jpg");
         Reclamation r = TBReclamation.getSelectionModel().getSelectedItem();
//int id= Integer.parseInt(tfId.getText());
        int Idcommande = Integer.parseInt(tfIdCommande.getText());
        String categorie= cbCategorie.getSelectionModel().getSelectedItem();
        String sujet= TFSujet.getText();
        String Description = TADescription.getText();
        String date= PickerDate.getValue().toString();
      ServiceReclamation src= new ServiceReclamation();
        src.modifier(new Reclamation(r.getId(),Idcommande,categorie,sujet,Description,date));
        Notifications notificationBuilder= Notifications.create()
    			.title("Reclamation modifiee!")
    			.text("votre reclamation a bien ete modifiee")
    			.graphic(new ImageView(img))
    			.hideAfter(Duration.seconds(5))
    			.position(Pos.TOP_RIGHT);
    	notificationBuilder.showInformation();
        init();

	    }
	 
	 public void updateTable() {
	        
	    	List<Reclamation> listreclamations = ServiceReclamation.getInstance().afficher();
	    	        tbid_commande.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
	    	        tbcategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
	    	        tbsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
	    	       tbdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
	    	        tbdate.setCellValueFactory(new PropertyValueFactory<>("date"));
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
	    	           

	    	       tbid_commande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
	    	        tbsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
	    	        tbcategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
	    	        tbdate.setCellValueFactory(new PropertyValueFactory<>("date"));
	    	       tbdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
	    	
	    	      reclamationActuel= null;

	    	        TBReclamation.setItems(reclamations); 
	    	   }


}
