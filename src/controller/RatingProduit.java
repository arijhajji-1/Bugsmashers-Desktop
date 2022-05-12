package controller;

import Model.AvisProduit;
import Model.ProduitAcheter;
import Model.ProduitLouer;
import Services.LoginSession;
import Services.ServiceAvisProduitProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import org.controlsfx.control.Rating;
import utils.TwillioMailSender;

import java.net.URL;
import java.util.ResourceBundle;

public class RatingProduit implements Initializable{
    @FXML
    private Rating ratingStar;
    @FXML
    private TextArea TAdescription;
    @FXML
    private ListView avisList = new ListView<AvisProduit>();
    public static int produitAcheterID;
    public static int produitLouerID;


    public void submit(ActionEvent actionEvent) {
        if (TAdescription.getText().isEmpty()){

        }else{
            //produitAcheterID=51;
            //produitLouerID=31;
            ServiceAvisProduitProduit.getInstance().ajouter(new AvisProduit(0,produitAcheterID,produitLouerID,(int) ratingStar.getRating(),
                    LoginSession.firstName,LoginSession.email,TAdescription.getText()));
            TwillioMailSender.sendMail("Hello "+LoginSession.firstName+" !\nYour rate of "+(int) ratingStar.getRating()+" star(s) was successfully added.\nThank you for rating one of our products!\n");
            ServiceAvisProduitProduit sp = new ServiceAvisProduitProduit();
            try{
                ProduitAcheter prod=MarketController.produitact;
                produitAcheterID = prod.getId();
            }catch (Exception e){
                System.out.println(e.getCause());
            }
            try{
                ProduitLouer prod=MarketLController.produitact;
                produitLouerID = prod.getId();
            }catch (Exception e){
                System.out.println(e.getCause());
            }
            System.out.println(produitLouerID);
            ObservableList list;
            if(produitLouerID==0) {
                list = FXCollections.observableArrayList(sp.afficher("produit_acheter_id", produitAcheterID));
            }
            else{
                list = FXCollections.observableArrayList(sp.afficher("produit_louer_id",produitLouerID));
            }

            avisList.setItems(list);
            avisList.setCellFactory(param -> new ListCell<AvisProduit>() {
                private Rating rating = new Rating();
                @Override
                public void updateItem(AvisProduit avis, boolean empty) {
                    super.updateItem(avis, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        try{Rating rating = new Rating();
                            rating.setRating(avis.getRating());
                            rating.setMouseTransparent(true);
                            //System.out.println(getClass().getResourceAsStream(avis.getImageName()));
                            setText("User : "+avis.getNom()+"\ndescription: "+avis.getDescription());
                            setFont(Font.font(36));
                            setGraphic(rating);}
                        catch(Exception e){
                            System.out.println(e.getCause());
                        }

                    }
                }
            });
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceAvisProduitProduit sp = new ServiceAvisProduitProduit();
        try{
            ProduitAcheter prod=MarketController.produitact;
            produitAcheterID = prod.getId();
        }catch (Exception e){
            System.out.println(e.getCause());
        }
        try{
            ProduitLouer prod=MarketLController.produitact;
            produitLouerID = prod.getId();
        }catch (Exception e){
            System.out.println(e.getCause());
        }
        System.out.println(produitLouerID);
        ObservableList list;
        if(produitLouerID==0) {
            list = FXCollections.observableArrayList(sp.afficher("produit_acheter_id", produitAcheterID));
        }
        else{
          list = FXCollections.observableArrayList(sp.afficher("produit_louer_id",produitLouerID));
        }

        avisList.setItems(list);
        avisList.setCellFactory(param -> new ListCell<AvisProduit>() {
            private Rating rating = new Rating();
            @Override
            public void updateItem(AvisProduit avis, boolean empty) {
                super.updateItem(avis, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try{Rating rating = new Rating();
                        rating.setRating(avis.getRating());
                        rating.setMouseTransparent(true);
                        //System.out.println(getClass().getResourceAsStream(avis.getImageName()));
                        setText("User : "+avis.getNom()+"\ndescription: "+avis.getDescription());
                        setFont(Font.font(36));
                        setGraphic(rating);}
                    catch(Exception e){
                        System.out.println(e.getCause());
                    }

                }
            }
        });
    }
    public void setValues(int prodA,int prodL){
        produitAcheterID = prodA;
        produitLouerID = prodL;


    }
}
