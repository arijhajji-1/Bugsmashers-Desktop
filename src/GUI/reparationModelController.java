/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Reparation;
import Services.ServiceReparation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class reparationModelController implements Initializable {

    @FXML
    private VBox cardReparation;
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView deleteIcon;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnmodifier;
   Reparation reparation; 
   MyListener myListener;
    @FXML
    private Label category;
    @FXML
    private Label type;
    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private Label categorylabel;
    @FXML
    private Label typelabel;
    @FXML
    private Label descriptionlabel;
    @FXML
    private Label datelabel;
    @FXML
    private Button avis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getData();
    }    
  private ArrayList<Reparation> getData() {
        ArrayList<Reparation> reparations = new ArrayList<>();
        
        //Guide guide;
        //ObservableList<Guide> guides = FXCollections.observableArrayList();
        ServiceReparation ps = new ServiceReparation();
        ps.afficher();
        reparations.addAll(ps.afficher());
       // System.out.println(ps.afficherGuide().get(2));

        return reparations;

    }
    public void setData(Reparation reparation, MyListener mylistener) {
 
        this.reparation = reparation;
        this.myListener = myListener;
        categorylabel.setText(reparation.getCategory());
        descriptionlabel.setText(reparation.getDescription());
        typelabel.setText(reparation.getType());
        datelabel.setText(reparation.getReserver());
   
//        Image image = new Image(getClass().getResourceAsStream(guide.getJeuxImage()));
//        img.setImage(image);
    }
    @FXML
    private void DeleteGuide(MouseEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Voulez vous vraiment supprimer cette demande ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
         ServiceReparation rep = new ServiceReparation();
                            rep.supprimer(reparation);}
        getData();
    }

    @FXML
    private void Supprimer(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Voulez vous vraiment supprimer cette demande ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
         ServiceReparation rep = new ServiceReparation();
                            rep.supprimer(reparation);}
   
    }

    @FXML
    private void ModifierReparation(ActionEvent event) {
        
     
           // idG=reparation.getId();
            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("Reparation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageReparationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
ReparationController modifier = loader.getController();
         modifier.setUpdate(true);
                           modifier.setTextField(reparation.getId(),reparation.getType(), reparation.getReserver(), 
                                    reparation.getDescription(), reparation.getCategory());
         
           // Scene scene1 = new Scene();
       
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    
    }

    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void avis(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("AvisReparation.fxml"));

                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageReparationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                            AvisReparationController ajout = loader.getController();
                            
                             ajout.id(reparation.getId());
                             System.out.println(reparation.getId());
                         Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }
    
}
