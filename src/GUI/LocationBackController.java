/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Location;
import Model.ProduitLouer;
import Services.Alerte;
import Services.ServiceCommande;
import Services.ServiceLocation;
import Services.ServiceProduitLouerProduit;
import Services.mail;
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
public class LocationBackController implements Initializable {

    @FXML
    private TableView<Location> tlocation2;
    @FXML
    private Button btnsupp2;
    @FXML
    private TextField tfrech;
    @FXML
    private ComboBox<String> trienom;
    @FXML
    private TableColumn<Location, Integer> colid;
    @FXML
    private TableColumn<Location, String> coldeb;
    @FXML
    private TableColumn<Location, String> colfin;
    @FXML
    private TableColumn<Location, Integer> coltotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
ServiceLocation sp = new ServiceLocation();

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

        ObservableList<Model.Location> list = FXCollections.observableArrayList(sp.afficher());
        tlocation2.setItems(list);
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

    tlocation2.setItems(list1);
    if(tfrech.getText().trim().isEmpty()){    tlocation2.setItems(list);}
   ;
});
      trienom.getItems().setAll("db", "df");

    // bind the selected fruit label to the selected fruit in the combo box.
  //  LBshow.textProperty().bind(trinom.getSelectionModel().selectedItemProperty());

    // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
      trienom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

      @Override 
      public void changed(ObservableValue<? extends String> selected, String oldFruit, String newFruit) {
     if(newFruit=="db"){  ObservableList<Model.Location> list2 = FXCollections.observableArrayList(sp.tristreamdatedeb());
       tlocation2.setItems(list2);}
     if(newFruit=="df"){  ObservableList<Model.Location> list2 = FXCollections.observableArrayList(sp.tristreamdateF());
       tlocation2.setItems(list2);}
    }  });



    }
@FXML
    void supprimerlocation(ActionEvent event) {
ServiceLocation sp= new ServiceLocation();
        Model.Location locations = tlocation2.getSelectionModel().getSelectedItem();
    
sp.supprimer(locations);
Alerte.display("RelouaTeam", "Location supprimer :''''");
 ObservableList<Model.Location> list = FXCollections.observableArrayList(sp.afficher());

    tlocation2.setItems(list);

    }    
    
}
