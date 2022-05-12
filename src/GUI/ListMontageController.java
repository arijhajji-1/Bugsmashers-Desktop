/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Montage;
import Services.ServiceMontage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class ListMontageController implements Initializable {

    @FXML
    private TableView<Montage> tab;
    @FXML
    private TableColumn<Montage, String> tabproc;
    @FXML
    private TableColumn<Montage, String> tabcartemere;
    @FXML
    private TableColumn<Montage, String> tabcartegraphique;
    @FXML
    private TableColumn<Montage, String> tabdisquesysteme;
    @FXML
    private TableColumn<Montage, String> tabstockagesupp;
    @FXML
    private TableColumn<Montage, String> tabboitier;
    @FXML
    private TableColumn<Montage, Integer> tabmontant;
    @FXML
    private TableColumn<Montage, String> mail;
    @FXML
    private TableColumn<Montage, Integer> iduser;
    @FXML
    private TextField filterField;
    @FXML
    private Label label;
 ObservableList<Montage> montages = FXCollections.observableArrayList();
    public static Montage montageActuel;
    private final static int rowsPerPage = 2;
    Montage montage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     loadDate();
     
    }
     private void refreshTable() {
          montages .clear();


                 List<Montage> listmontage = ServiceMontage.getInstance().afficher();

        if (!listmontage.isEmpty()) {
            for (int i = 0; i < listmontage.size(); i++) {
               Montage montage = listmontage.get(i);
                montages.add(montage);
            }
        }
     tab.setItems(montages );
    }
       private void loadDate() {
             List<Montage> listmontage = ServiceMontage.getInstance().afficher();

        if (!listmontage.isEmpty()) {
            for (int i = 0; i < listmontage.size(); i++) {
               Montage montage = listmontage.get(i);
                montages.add(montage);
            }
        }
                    refreshTable();

        tabproc.setCellValueFactory(new PropertyValueFactory<>("processeur"));
        tabcartemere.setCellValueFactory(new PropertyValueFactory<>("carte_mere"));
        tabcartegraphique.setCellValueFactory(new PropertyValueFactory<>("carte_graphique"));
        tabdisquesysteme.setCellValueFactory(new PropertyValueFactory<>("disque_systeme"));
        tabstockagesupp.setCellValueFactory(new PropertyValueFactory<>("stockage_supp"));
        tabboitier.setCellValueFactory(new PropertyValueFactory<>("boitier"));
        tabmontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
mail.setCellValueFactory(new PropertyValueFactory<>("email"));
iduser.setCellValueFactory(new PropertyValueFactory<>("iduser"));
       montageActuel= null;

        tab.setItems(montages); 
 FilteredList<Montage> filteredData = new FilteredList<>(montages, c -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(candidat -> {
                // If filter text is empty, display all candidats.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare nom and prenom of every candidat with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (candidat.getProcesseur().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                } else if (candidat.getCarte_mere().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches prenom.

                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Montage> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tab.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tab.setItems(sortedData);
        // TODO
        // TODO
    }    

    @FXML
    private void selectMontage(MouseEvent event) {
                      montageActuel = tab.getSelectionModel().getSelectedItem();

    }
    

    
}
