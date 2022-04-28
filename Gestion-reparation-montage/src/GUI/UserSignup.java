/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import services.UserServices;
import services.UsersSession;


/**
 * FXML Controller class
 *
 * @author Hsine
 */
public class UserSignup implements Initializable {

    @FXML
    private TextField Nomf;
    @FXML
    private TextField prenomf;
    @FXML
    private TextField emailf;
    @FXML
    private TextField mdpf;
    @FXML
    private TextField cinf;
    @FXML
    private TextField telf;
    @FXML
    private TextField adressef;
    @FXML
    private DatePicker datenf;
    @FXML
    private Circle profilepicture;
    String f;
    @FXML
    private Label nomctrl;
    @FXML
    private Label prenomctrl;
    @FXML
    private Label emailctrl;
    @FXML
    private Label Cinctrl;
    @FXML
    private Label telctrl;
    @FXML
    private Label adressectrl;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signin(ActionEvent event) {
        ControleSaisie w =new ControleSaisie();
         if (!w.testnomprenom(Nomf.getText()))
        {
            nomctrl.setText("Erreur ! Veuillez insérer un Nom valide");
        }
        else 
        {
             nomctrl.setText("");
        }
        if (!w.testnomprenom(prenomf.getText()))
        {
            prenomctrl.setText("Erreur ! Veuillez insérer un Prénom valide");
        }
        else 
        {
            prenomctrl.setText("");
        }
    if (!w.Num(cinf.getText()))
        {
            Cinctrl.setText("Erreur ! Veuillez insérer un nbre valide");
        }
        else 
        {
            Cinctrl.setText("");
        }       
      if (!w.Num(telf.getText()))
        {
            telctrl.setText("Erreur ! Veuillez insérer un num valide");
        }
        else 
        {
            telctrl.setText("");
        }      
       if (!w.testnomprenom(adressef.getText()))
        {
            adressectrl.setText("Erreur ! Veuillez insérer un adresse valide");
        }
        else 
        {
             adressectrl.setText("");
        }
        String Nom =Nomf.getText();
        String prenom =prenomf.getText();
        String email =emailf.getText();
        String mdp =mdpf.getText();
        String adresse =adressef.getText();
        int cin = Integer.parseInt(cinf.getText());
        String tel = telf.getText();
        UserServices cc = new UserServices();
         Date daten = java.sql.Date.valueOf(datenf.getValue());
             
         User u = new User(5, tel,cin,1,prenom,Nom, adresse,email,mdp,daten,"[\"ROLE_User\"]",f);
            cc.ajouteruser(u);
    }
    @FXML
    public void uploadsiguppic(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setInitialDirectory(new File("D:\\project\\desktop\\Bugsmashers-Desktop\\Gestion-reparation-montage\\src\\images"));
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) {
            String TempprofilePicture = file.toURI().toString();
            System.out.println(TempprofilePicture);
            Image image = new Image(TempprofilePicture);
            ImagePattern pattern = new ImagePattern(image);
            UsersSession.setProfilepicture(TempprofilePicture);
            profilepicture.setFill(pattern);
            profilepicture.setStroke(Color.SEAGREEN);
            profilepicture.setEffect(new DropShadow(20, Color.BLACK));
           f =file.getAbsolutePath();
    }

    
}
    public void switchToSignup(ActionEvent event) throws IOException {
 System.out.println("hello");
        root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

    }
}
