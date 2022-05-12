package controller;

import Model.Actualite;
import Services.ServiceActualiteActualite;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class ActualiteFront implements Initializable {
    @FXML
    private ListView actualiteList = new ListView<Actualite>();
    @FXML
    private Label Ltitle;
    ImageView imageView = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ListView<Evenement> list = new ListView<Evenement>();
        ServiceActualiteActualite sp = new ServiceActualiteActualite();
        ObservableList<Actualite> list = FXCollections.observableArrayList(sp.afficher());
        actualiteList.setItems(list);
        actualiteList.setCellFactory(param -> new ListCell<Actualite>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Actualite event, boolean empty) {
                super.updateItem(event, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try{
                        Image im = new Image(getClass().getResourceAsStream("/"+event.getImageName()));
                        imageView.setImage(im);
                        imageView.setFitHeight(400);
                        imageView.setFitWidth(800);
                        System.out.println(getClass().getResourceAsStream(event.getImageName()));
                        setText("Nom : "+event.getTitre()+"\nDate: "+event.getDate());
                        setFont(Font.font(36));
                        setGraphic(imageView);}
                    catch(Exception e){
                        System.out.println(e.getCause());
                    }

                }
            }
        });
        actualiteList.getSelectionModel().selectedItemProperty().addListener(
                (ChangeListener<Actualite>) (ov, old_val, new_val) -> Ltitle.setText("Description d'actualite :"+new_val.getDescription()));

    }
}
