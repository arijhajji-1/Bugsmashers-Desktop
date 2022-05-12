package controller;

import Model.AvisEvenement;
import Services.ServiceAvisEvenementActualite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import Services.LoginSession;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AvisEvenementController implements Initializable {
    public static int id;
    @FXML
    public TextArea TAdescription;
    @FXML
    public ListView avisList;
    public static final ObservableList names =
            FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        names.clear();
         id=EvenementFrontController.eventact.getId();
         System.out.println(id);
        ServiceAvisEvenementActualite sae = new ServiceAvisEvenementActualite();
        List<AvisEvenement> ae = sae.afficher(id);
        int i=0;
        for (AvisEvenement a:
             ae) {
            i++;
            names.add("Commentaire "+i+" :\nNom : "+a.getNom()+"\nDescription : "+a.getDescription());
        }
        System.out.print(names);
        avisList.setItems(names);
    }

    public void setValues(int id){
        this.id = id;
        ServiceAvisEvenementActualite sae = new ServiceAvisEvenementActualite();
        System.out.println(sae.afficher(id));
    }

    public void submit(ActionEvent actionEvent) {
        if (TAdescription.getText().isEmpty()){

        }else{
            //produitAcheterID=51;
            //produitLouerID=31;
            ServiceAvisEvenementActualite sae = new ServiceAvisEvenementActualite();
            sae.ajouter(new AvisEvenement(0,id, LoginSession.firstName,TAdescription.getText(),LoginSession.email));
            names.clear();
            id=EvenementFrontController.eventact.getId();
            List<AvisEvenement> ae = sae.afficher(id);
            int i=0;
            for (AvisEvenement a:
                    ae) {
                i++;
                names.add("Commentaire "+i+" :\nNom : "+a.getNom()+"\nDescription : "+a.getDescription());
            }
            System.out.print(names);
            avisList.setItems(names);
        }
    }
}