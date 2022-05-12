/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.User;
import Services.LoginSession;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import utils.BadWords;
import utils.ResizeHelper;

/**
 *
 * @author Hsine
 */
public class NewFXMain extends Application {
   
    public static ResizeHelper resizeHelper;
    public static Stage mainStage;

    private static NewFXMain instance;
    private static User session;

    public static NewFXMain getInstance() {
        if (instance == null) {
            instance = new NewFXMain();
        }
        return instance;
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Chargements des mots a filtrer ..");
        BadWords.loadConfigs();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        mainStage = primaryStage;
 
        loadConnexion();

    }

    private void loadScene(String fxmlLink, String title, int width, int height, boolean isAuthentification) {
        try {
            Stage primaryStage = mainStage;
            primaryStage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlLink)));
            scene.setFill(Color.TRANSPARENT);

        primaryStage.getIcons().add(new Image("GUI/images/reloua logo-01.png"));
            primaryStage.setTitle(title);
           primaryStage.setWidth(width);
            primaryStage.setHeight(height);
            primaryStage.setMinWidth(width);
            primaryStage.setMinHeight(height);
            primaryStage.setScene(scene);
            primaryStage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - (width / 2));
            primaryStage.setY((Screen.getPrimary().getBounds().getHeight() / 2) - (height / 2));

            if (!isAuthentification) {
                resizeHelper = new ResizeHelper(primaryStage, 0, 45);
            }
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadConnexion() {
        loadScene(
                "loginUser.fxml",
                "Connexion",
                800,
                600,
                true
                
        );
    }

 

  
    public void deconnexion() {
        LoginSession.IsLogged=false;
        System.out.println("Deconnexion ..");
        loadConnexion();
    }

    public static User getSession() {
        return session;
    }

    public static void setSession(User session) {
       NewFXMain.session = session;
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
