package controller;

import Model.ProduitAcheter;
import Services.ServiceProduitAcheterProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PAAddAFFCRUD implements Initializable {
    ObservableList<ProduitAcheter> produits = FXCollections.observableArrayList();
    public static ProduitAcheter produitActuel;
    private final static int rowsPerPage = 2;
    ProduitAcheter produitA;
    @FXML
    private TableView<ProduitAcheter> tab;
    @FXML
    private TableColumn<ProduitAcheter, String> tabid;
    @FXML
    private TableColumn<ProduitAcheter, String> tabnom;
    @FXML
    private TableColumn<ProduitAcheter, String> tabprix;
   @FXML
    private TableColumn<ProduitAcheter, String> tabcategorie;
    @FXML
    private TableColumn<ProduitAcheter, String> tabqte;

    @FXML
    private TableColumn<ProduitAcheter, String> tabmarque;

    @FXML
    private TableColumn<ProduitAcheter, String> action;

    @FXML
    private TextField TFsearch;

   /*
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

    */

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    loadDate();
    }
    private void refreshTable() {
        produits.clear();


        List<ProduitAcheter> listproduit = ServiceProduitAcheterProduit.getInstance().afficher();

        if (!listproduit.isEmpty()) {
            for (int i = 0; i < listproduit.size(); i++) {
                ProduitAcheter produit = listproduit.get(i);
                produits.add(produit);
            }
        }
        tab.setItems(produits );
    }
    private void loadDate() {
        List<ProduitAcheter> listproduit = ServiceProduitAcheterProduit.getInstance().afficher();

        if (!listproduit.isEmpty()) {
            for (int i = 0; i < listproduit.size(); i++) {
                ProduitAcheter produit = listproduit.get(i);
                produits.add(produit);
            }
        }
        refreshTable();

        tabid.setCellValueFactory(new PropertyValueFactory<ProduitAcheter, String>("id"));
        tabnom.setCellValueFactory(new PropertyValueFactory<ProduitAcheter, String>("nom"));
        tabprix.setCellValueFactory(new PropertyValueFactory<ProduitAcheter, String>("prix"));
        tabcategorie.setCellValueFactory(new PropertyValueFactory<>("category"));
        tabqte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        tabmarque.setCellValueFactory(new PropertyValueFactory<>("marque"));

        /*
        tabmontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
         */

        produitActuel = null;

        tab.setItems(produits);
       Callback<TableColumn<ProduitAcheter, String>, TableCell<ProduitAcheter, String>> cellFoctory = (TableColumn<ProduitAcheter, String> param) -> {
            // make cell containing buttons
            final TableCell<ProduitAcheter, String> cell = new TableCell<ProduitAcheter, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        // FontAwesomeIconView deleteicon = new FontAwesomeIconView(FontAwesomeIcon.REMOVE);

                        Label deleteicon = new Label("delete");
                        Label editicon = new Label("edit");

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

                            produitA = tab.getSelectionModel().getSelectedItem();
                            ServiceProduitAcheterProduit rep = new ServiceProduitAcheterProduit();
                            rep.supprimer(produitA);
                            refreshTable();





                        });
                        editicon.setOnMouseClicked((MouseEvent event) -> {

                            produitA = tab.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUI/produitA.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
//
                            ProduitA modifier = loader.getController();
                            modifier.setUpdate(true);
                            modifier.setValues(produitA.getQte(),produitA.getCategory(), produitA.getMarque(),
                                    produitA.getDescription(), produitA.getNom(),produitA.getId(),produitA.getImage_path(),produitA.getPrix());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();


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
        tab.setItems(produits);

    }


    public void AfficherProduitA(ActionEvent actionEvent) {
        produitA = tab.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/produitA.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
//
        ProduitA modifier = loader.getController();
        modifier.setUpdate(false);
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public void refreshClick(ActionEvent actionEvent) {
        refreshTable();
    }

    public void searchBar(KeyEvent inputMethodEvent) {
        tab.getItems().clear();
        String critera = TFsearch.getText();
        List<ProduitAcheter> listproduit = ServiceProduitAcheterProduit.getInstance().Rech("nom",critera);

        if (!listproduit.isEmpty()) {
            for (int i = 0; i < listproduit.size(); i++) {
                ProduitAcheter produit = listproduit.get(i);
                produits.add(produit);
            }
        }
        tab.setItems(produits);


    }
}