package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainMenuController {
    public void eventpage(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/evenement.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("----- Evenement ------");
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public void actualitepage(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/actualite.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("----- Actualite ------");
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public void eventFront(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/evenementFront.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("----- Evenement ------");
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public void actFront(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/actualiteFront.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("----- Evenement ------");
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
}
