package controller;

import Model.ProduitAcheter;
import Model.ProduitLouer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.MyListener;
import main.MyListenerL;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;


    private ProduitAcheter produit;
    private ProduitLouer produitL;
    MyListener myListener;
    MyListenerL myListenerL;

    public void setData(ProduitAcheter produit, MyListener myListener) {
        this.produit = produit;
        this.myListener = myListener;
        nameLabel.setText(produit.getNom());
        priceLable.setText(produit.getPrix()+" TND");
        Image image = new Image(getClass().getResourceAsStream("/img/"+produit.getImage_path()));
        img.setImage(image);
    }
    public void setData(ProduitLouer produit, MyListenerL myListener) {
        this.produitL = produit;
        this.myListenerL = myListener;
        nameLabel.setText(produit.getNom());
        priceLable.setText(produit.getPrix()+" TND/Day");
        Image image = new Image(getClass().getResourceAsStream("/img/"+produit.getImage_path()));
        img.setImage(image);
    }
    @FXML
    public void click(MouseEvent mouseEvent) {
        if(produit!=null)
            myListener.onClickListener(produit);
        else{
            myListenerL.onClickListener(produitL);
        }
    }
}
