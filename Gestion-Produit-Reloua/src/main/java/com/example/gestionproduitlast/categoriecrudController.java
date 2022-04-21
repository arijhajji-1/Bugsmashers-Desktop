package com.example.gestionproduitlast;

import Model.Category;
import Model.ProduitAcheter;
import Services.ServiceCategory;
import Services.ServiceProduitAcheter;
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
import java.security.Provider;
import java.util.List;
import java.util.ResourceBundle;

public class categoriecrudController implements Initializable {
    ObservableList<Category> categories = FXCollections.observableArrayList();
    public static Category categorieactuel;
    private final static int rowsPerPage = 2;
    Category categorieA;
    @FXML
    private TableView<Category> tab;
    @FXML
    private TextField TFsearch;
    @FXML
    private TableColumn<Category, String> tabid;
    @FXML
    private TableColumn<Category, String> tabnom;
    @FXML
    private TableColumn<Category, String> action;

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
        categories.clear();


        List<Category> listcategory = ServiceCategory.getInstance().afficher();

        if (!listcategory.isEmpty()) {
            for (int i = 0; i < listcategory.size(); i++) {
                Category produit = listcategory.get(i);
                categories.add(produit);
            }
        }
        tab.setItems(categories );
    }
    private void loadDate() {
        List<Category> listcategorie = ServiceCategory.getInstance().afficher();

        if (!listcategorie.isEmpty()) {
            for (int i = 0; i < listcategorie.size(); i++) {
                Category categorie = listcategorie.get(i);
                categories.add(categorie);
            }
        }
        refreshTable();

        tabid.setCellValueFactory(new PropertyValueFactory<Category, String>("id"));
        tabnom.setCellValueFactory(new PropertyValueFactory<Category, String>("label"));

        /*
        tabmontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
         */

        categorieactuel = null;

        tab.setItems(categories);
        Callback<TableColumn<Category, String>, TableCell<Category, String>> cellFoctory = (TableColumn<Category, String> param) -> {
            // make cell containing buttons
            final TableCell<Category, String> cell = new TableCell<Category, String>() {
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

                            categorieA = tab.getSelectionModel().getSelectedItem();
                            ServiceCategory rep = new ServiceCategory();
                            rep.supprimer(categorieA);
                            refreshTable();





                        });
                        editicon.setOnMouseClicked((MouseEvent event) -> {

                            categorieA = tab.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/ProduitGUI/categorie.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
//
                            categorie modifier = loader.getController();
                            modifier.setUpdate(true);
                            modifier.setValues(categorieA.getLabel(),categorieA.getId());
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
        tab.setItems(categories);

    }
    public void refreshCategorie(ActionEvent actionEvent) {
        refreshTable();
    }

    public void ajouterCategorie(ActionEvent actionEvent) {
        categorieA = tab.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ProduitGUI/categorie.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
//
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public void searchBar(KeyEvent inputMethodEvent) {
        tab.getItems().clear();
        String critera = TFsearch.getText();
        List<Category> listcategory = ServiceCategory.getInstance().Rech("label",critera);

        if (!listcategory.isEmpty()) {
            for (int i = 0; i < listcategory.size(); i++) {
                Category produit = listcategory.get(i);
                categories.add(produit);
            }
        }
        tab.setItems(categories);


    }
}
