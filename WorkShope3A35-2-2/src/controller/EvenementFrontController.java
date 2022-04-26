package controller;

import Model.Evenement;
import Services.ServiceEvenement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class EvenementFrontController implements Initializable {
    @FXML
    private ListView evenementList = new ListView<Evenement>();
    @FXML
    private Label Ltitle;
    ImageView imageView = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ListView<Evenement> list = new ListView<Evenement>();
        ServiceEvenement sp = new ServiceEvenement();
        ObservableList<Evenement> list = FXCollections.observableArrayList(sp.afficher());
        evenementList.setItems(list);
        evenementList.setCellFactory(param -> new ListCell<Evenement>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Evenement event, boolean empty) {
                super.updateItem(event, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try{Image im = new Image(getClass().getResourceAsStream("/"+event.getImageName()));
                        imageView.setImage(im);
                        imageView.setFitHeight(400);
                        imageView.setFitWidth(800);
                        System.out.println(getClass().getResourceAsStream(event.getImageName()));
                        setText("Nom : "+event.getNom()+"\nDate: "+event.getDate()+"\n Heure: "+event.getHeure()+":00");
                        setFont(Font.font(36));
                        setGraphic(imageView);}
                    catch(Exception e){
                        System.out.println(e.getCause());
                    }

                }
            }
        });
        evenementList.getSelectionModel().selectedItemProperty().addListener(
                (ChangeListener<Evenement>) (ov, old_val, new_val) -> Ltitle.setText("Description d'evenement :"+new_val.getDescription()));

    }
}
