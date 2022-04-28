package com.example.gestionproduitlast;

import Model.ProduitLouer;
import Model.ProduitLouer;
import Services.ServiceProduitLouer;
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

public class PLCRUD implements Initializable {
    ObservableList<ProduitLouer> produits = FXCollections.observableArrayList();
    public static ProduitLouer produitActuel;
    private final static int rowsPerPage = 2;
    ProduitLouer produitA;
    @FXML
    private TableView<ProduitLouer> tab;
    @FXML
    private TableColumn<ProduitLouer, String> tabid;
    @FXML
    private TableColumn<ProduitLouer, String> tabnom;
    @FXML
    private TableColumn<ProduitLouer, String> tabprix;
    @FXML
    private TableColumn<ProduitLouer, String> tabcategorie;
    @FXML
    private TableColumn<ProduitLouer, String> tabetat;
    @FXML
    private TableColumn<ProduitLouer, String> tabdispo;

    @FXML
    private TableColumn<ProduitLouer, String> tabmarque;

    @FXML
    private TableColumn<ProduitLouer, String> action;

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


        List<ProduitLouer> listproduit = ServiceProduitLouer.getInstance().afficher();

        if (!listproduit.isEmpty()) {
            for (int i = 0; i < listproduit.size(); i++) {
                ProduitLouer produit = listproduit.get(i);
                produits.add(produit);
            }
        }
        tab.setItems(produits );
    }
    private void loadDate() {
        List<ProduitLouer> listproduit = ServiceProduitLouer.getInstance().afficher();

        if (!listproduit.isEmpty()) {
            for (int i = 0; i < listproduit.size(); i++) {
                ProduitLouer produit = listproduit.get(i);
                produits.add(produit);
            }
        }
        refreshTable();

        tabid.setCellValueFactory(new PropertyValueFactory<ProduitLouer, String>("id"));
        tabnom.setCellValueFactory(new PropertyValueFactory<ProduitLouer, String>("nom"));
        tabprix.setCellValueFactory(new PropertyValueFactory<ProduitLouer, String>("prix"));
        tabcategorie.setCellValueFactory(new PropertyValueFactory<>("category"));
        tabetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tabdispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
        tabmarque.setCellValueFactory(new PropertyValueFactory<>("marque"));

        /*
        tabmontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
         */

        produitActuel = null;

        tab.setItems(produits);
        Callback<TableColumn<ProduitLouer, String>, TableCell<ProduitLouer, String>> cellFoctory = (TableColumn<ProduitLouer, String> param) -> {
            // make cell containing buttons
            final TableCell<ProduitLouer, String> cell = new TableCell<ProduitLouer, String>() {
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
                            ServiceProduitLouer rep = new ServiceProduitLouer();
                            rep.supprimer(produitA);
                            refreshTable();





                        });
                        editicon.setOnMouseClicked((MouseEvent event) -> {

                            produitA = tab.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/ProduitGUI/produitL.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
//
                            ProduitL modifier = loader.getController();
                            modifier.setUpdate(true);
                            modifier.setValues(produitA.isDispo(),produitA.getEtat(),produitA.getCategory(), produitA.getMarque(),
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
        loader.setLocation(getClass().getResource("/ProduitGUI/produitL.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
//
        ProduitL modifier = loader.getController();
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
        List<ProduitLouer> listproduit = ServiceProduitLouer.getInstance().Rech("nom", critera);

        if (!listproduit.isEmpty()) {
            for (int i = 0; i < listproduit.size(); i++) {
                ProduitLouer produit = listproduit.get(i);
                produits.add(produit);
            }
        }
        tab.setItems(produits);


    }
}
