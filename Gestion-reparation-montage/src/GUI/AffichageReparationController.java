/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.AvisReparation;
import Model.Reparation;
import Services.ServiceAvisReparation;
import Services.ServiceReparation;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import javafx.scene.control.TableView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class AffichageReparationController implements Initializable {
 ObservableList<Reparation> reparations = FXCollections.observableArrayList();
                public static Reparation reparationActuelle ;
    private final static int rowsPerPage = 2;
        Reparation reparation ;
    
    @FXML
    private TableView<Reparation> tab;
    @FXML
    private TableColumn<Reparation, String> tabcategory;
    @FXML
    private TableColumn<Reparation, String> tabType;
    @FXML
    private TableColumn<Reparation, String> tabDescription;
    @FXML
    private TableColumn<Reparation,String> tabDate;
    @FXML
    private TextField filterField;
    @FXML
    private Label label;
    @FXML
    private Button backButton;
    
// private ObservableList<Reparation> listreparations;
    @FXML
    private TableColumn<Reparation, String> action;
    @FXML
    private Button avis;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //pagination.setPageFactory(this::createPage);
         loadDate() ;
    }
 
 private void refreshTable() {
          reparations .clear();

      List<Reparation> listreparations = ServiceReparation.getInstance().afficher();

        if (!listreparations.isEmpty()) {
            for (int i = 0; i < listreparations.size(); i++) {
                reparation = listreparations.get(i);
                reparations.add(reparation);
            }
        }
     tab.setItems(reparations );
    }
      private void loadDate() {
        List<Reparation> listreparations = ServiceReparation.getInstance().afficher();

        if (!listreparations.isEmpty()) {
            for (int i = 0; i < listreparations.size(); i++) {
                reparation = listreparations.get(i);
                reparations.add(reparation);
            }
        }
            refreshTable();

        tabcategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tabType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tabDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tabDate.setCellValueFactory(new PropertyValueFactory<>("Reserver"));
       
       reparationActuelle= null;

        tab.setItems(reparations);
      Callback<TableColumn<Reparation, String>, TableCell<Reparation, String>> cellFoctory = (TableColumn<Reparation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reparation, String> cell = new TableCell<Reparation, String>() {
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
                        FontAwesomeIconView plus = new FontAwesomeIconView(FontAwesomeIcon.PLUS);

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
                         plus.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#0000FF;"
                        );
                        deleteicon.setOnMouseClicked((MouseEvent event) -> {
                            
                            reparation = tab.getSelectionModel().getSelectedItem();
                            ServiceReparation rep = new ServiceReparation();
                            rep.supprimer(reparation);
                            refreshTable();
                            
                           

                          

                        });
                          plus.setOnMouseClicked((MouseEvent event) -> {
                             

         
                                                                            reparationActuelle = reparation;

                           reparation = tab.getSelectionModel().getSelectedItem();
                          FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("AvisReparation.fxml"));

                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageReparationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         loadDate();
                            AvisReparationController ajout = loader.getController();
                            
                             ajout.id(reparation.getId());
                             System.out.println(reparation.getId());
                         Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            refreshTable();
                            
                           

                          

                        });
                        editicon.setOnMouseClicked((MouseEvent event) -> {
                            
                            reparation = tab.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("Reparation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageReparationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            loadDate();
                            ReparationController modifier = loader.getController();
                          //  String a = "" + reparation.getId();
                          //  modifier.setTextField(reparation.getDescription(), a);
                          // ReparationController.modifier(reparation.getId(), reparation.getDescription());
                           modifier.setUpdate(true);
                           modifier.setTextField(reparation.getId(),reparation.getType(), reparation.getReserver(), 
                                    reparation.getDescription(), reparation.getCategory());

                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
loadDate();

                           

                        });

                        HBox managebtn = new HBox(editicon, deleteicon,plus);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteicon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editicon, new Insets(2, 3, 0, 2));
                                                HBox.setMargin(editicon, new Insets(2, 4, 0, 1));


                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         action.setCellFactory(cellFoctory);
         tab.setItems(reparations);
        //// Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Reparation> filteredData = new FilteredList<>(reparations, c -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(candidat -> {
                // If filter text is empty, display all candidats.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare nom and prenom of every candidat with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (candidat.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                } else if (candidat.getType().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches prenom.

                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reparation> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tab.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tab.setItems(sortedData);
        // TODO
    }    
 
    @FXML
    private void selectReparation(MouseEvent event) {
              reparationActuelle = tab.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void back(ActionEvent event) {
         try {
            FXMLLoader main = new FXMLLoader(getClass().getResource("Reparation.fxml"));
            Parent root = (Parent) main.load();
         
            this.backButton.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    @FXML
    private void avis(ActionEvent event) {
        try {
            FXMLLoader main = new FXMLLoader(getClass().getResource("ListAvis.fxml"));
            Parent root = (Parent) main.load();
         
            this.avis.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e);
        } 
    }

    
    
}
