/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Model.Reclamation;
import org.controlsfx.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import Services.ServiceReclamation;
import utils.MyDb;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;

/**
 * FXML Controller class
 *
 * @author Gloria
 */
public class FXMLReclamationController implements Initializable {

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
   
ServiceReclamation sr = new ServiceReclamation();


        Reclamation reclamation ;
    /**
     * Initializes the controller class.
     */
 
    private Button btn_ajouter;
  
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
   
    
  
    }    
    
  
    @FXML
    private void ajouterReclamation(ActionEvent event) {
    	Image img=new Image("\\Ressources\\iconAdd.png");
         int idCommande = Integer.parseInt(tfIdCommande.getText()) ;
        String categorie= cbCategorie.getSelectionModel().getSelectedItem();
        String sujet= TFSujet.getText();
        String description= TADescription.getText();
        String date= PickerDate.getValue().toString();

        if(categorie.isEmpty()||sujet.isEmpty()||description.isEmpty())
        {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("veuilez remplir tous les champs");
            alert.showAndWait();
        }
        else
        {
             ServiceReclamation sr= new ServiceReclamation();
        sr.ajouter(new Reclamation(idCommande,categorie,sujet,description,date));
        Notifications notificationBuilder= Notifications.create()
    			.title("Reclamation Ajoutee!")
    			.text("votre reclamation a bien ete ajoutee")
    			.graphic(new ImageView(img))
    			.hideAfter(Duration.seconds(5))
    			.position(Pos.TOP_RIGHT);
    	notificationBuilder.showInformation();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Reclamation ajoutée!");
                   
        }
       
    }

    //--------------------------------------------------
    @FXML
    void listReclamation(ActionEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("listReclamationFront.fxml"));
        Scene scene = new Scene(parent);

       Stage stage = new Stage();
        //stage.getIcons().add(new Image("\\Ressources\\Logo.png"));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
   
    
    
}
