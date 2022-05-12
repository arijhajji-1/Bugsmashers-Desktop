package controller;


import GUI.FXMLController;
import Model.AvisProduit;
import Model.ProduitAcheter;
import Services.QRcodeGenerator;
import Services.ServiceAvisProduitProduit;
import Services.ServiceProduitAcheterProduit;
import com.google.zxing.WriterException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.MyListener;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {
    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    private Image image;
    @FXML
    private Rating ratingBar;
    private int ratingValue;
    public static ProduitAcheter produitact;
    private MyListener myListener;
    private List<ProduitAcheter> produits = new ArrayList<>();
    @FXML
    private ImageView imageView;
    private void setChosenFruit(ProduitAcheter produit) {
        fruitNameLable.setText(produit.getNom());
        fruitPriceLabel.setText(produit.getPrix()+" TND");
        calculateRating(produit);
        this.produitact=produit;
        try {
            Image im = QRcodeGenerator.generateQRCodeImage(produit.toString(),100,100,"qrcode"+produit.getImage_path());
                imageView.setImage(im);
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
                //setGraphic(imageView);

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new Image(getClass().getResourceAsStream("/img/"+produit.getImage_path()));
        fruitImg.setImage(image);
        ratingBar.setRating(ratingValue);
        ratingValue=0;
        //chosenFruitCard.setStyle("-fx-background-color: #" + produit.getColor() + ";\n" +
         //       "    -fx-background-radius: 30;");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceProduitAcheterProduit spa = new ServiceProduitAcheterProduit();
        produits = spa.afficher();
        if (produits.size() > 0) {

            setChosenFruit(produits.get(0));
            myListener = produit -> setChosenFruit(produit);
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateRating(ProduitAcheter produit) {
        List<AvisProduit> lap = ServiceAvisProduitProduit.getInstance().afficher("produit_acheter_id",produit.getId());
        ratingValue=0;
        for (AvisProduit ap:
                lap) {
            ratingValue+=ap.getRating();
        }
        try{ratingValue = ratingValue/lap.size();}
        catch(Exception e){
            ratingValue=0;
        }
    }


    public void searchBar(KeyEvent keyEvent) {
        grid.getChildren().clear();
        ServiceProduitAcheterProduit spa = new ServiceProduitAcheterProduit();
        produits = spa.Rech("nom",searchField.getText());
        if (produits.size() > 0) {
            setChosenFruit(produits.get(0));
            myListener = produit -> setChosenFruit(produit);
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rateIT(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/ratingproduit.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        RatingProduit rate = loader.getController();
        rate.setValues(produitact.getId(),0);
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();



    }

    public void buy(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/commande.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(produitact);
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
}

