/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Montage;
import Model.Reparation;
import Services.ServiceMontage;
import Services.ServiceReparation;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class AffichageMontageController implements Initializable {
 ObservableList<Montage> montages = FXCollections.observableArrayList();
    public static Montage montageActuel;
    private final static int rowsPerPage = 2;
    Montage montage;
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
    private TableColumn<Montage, String> tabmontant;
    @FXML
    private TextField filterField;
    @FXML
    private Label label;
    @FXML
    private Button backButton;
    @FXML
    private TableColumn<Montage, String> action;

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

       montageActuel= null;

        tab.setItems(montages);
   Callback<TableColumn<Montage, String>, TableCell<Montage, String>> cellFoctory = (TableColumn<Montage, String> param) -> {
            // make cell containing buttons
            final TableCell<Montage, String> cell = new TableCell<Montage, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                       // FontAwesomeIconView deleteicon = new FontAwesomeIconView(FontAwesomeIcon.REMOVE);

                           FontAwesomeIconView deleteicon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editicon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteicon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editicon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteicon.setOnMouseClicked((MouseEvent event) -> {
                            
                            montage = tab.getSelectionModel().getSelectedItem();
                            ServiceMontage rep = new ServiceMontage();
                            rep.supprimer(montage);
                            refreshTable();
                            
                           

                          

                        });
                        editicon.setOnMouseClicked((MouseEvent event) -> {
                            
                            montage = tab.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("Montage.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageReparationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            
                            MontageController modifier = loader.getController();
                          //  String a = "" + reparation.getId();
                          //  modifier.setTextField(reparation.getDescription(), a);
                          // ReparationController.modifier(reparation.getId(), reparation.getDescription());
                           modifier.setUpdate(true);
                           modifier.setTextField(montage.getIdmontage(),montage.getProcesseur(), montage.getCarte_mere(), 
                                    montage.getCarte_graphique(), montage.getDisque_systeme(),montage.getStockage_supp(),montage.getBoitier(),montage.getMontant());

                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
loadDate();

                           

                        });

                        HBox managebtn = new HBox(editicon, deleteicon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteicon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editicon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         action.setCellFactory(cellFoctory);
         tab.setItems(montages);
        //// Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Montage> filteredData = new FilteredList<>(montages, c -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(montages -> {
                // If filter text is empty, display all candidats.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare nom and prenom of every candidat with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (montages.getProcesseur().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                } else if (montages.getCarte_mere().toLowerCase().contains(lowerCaseFilter)) {
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
    private void back(ActionEvent event) {
        try {
            FXMLLoader main = new FXMLLoader(getClass().getResource("Montage.fxml"));
            Parent root = (Parent) main.load();
         
            this.backButton.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void selectMontage(MouseEvent event) {
                      montageActuel = tab.getSelectionModel().getSelectedItem();

    }
    
}
