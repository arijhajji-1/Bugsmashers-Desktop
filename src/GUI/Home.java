package GUI;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home {

    @FXML
    void commandeloc(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/comm.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    void eventact(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/eventact.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    void montagerep(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/userB.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    void produitcat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/prodcat.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    void recLiv(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/ListReclamationBack.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    void user(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/GestionUserBack.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
    @FXML
    void deconnexion(ActionEvent event) {
        NewFXMain.getInstance().deconnexion();

    }
    @FXML
    void liv(ActionEvent event) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource("DeliveryFXML.fxml"));
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            //stage.getIcons().add(new Image("\\Ressources\\Logo.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

