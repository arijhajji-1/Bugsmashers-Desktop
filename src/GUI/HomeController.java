package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import GUI.LivraisonFXMLController;
import javafx.fxml.Initializable;

public class HomeController implements Initializable {
	@FXML
	private Button btn_gestRecl;
	@FXML
	private Button btn_Livraison;
	@FXML
	private ImageView image;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	// Event Listener on Button[#btn_gestRecl].onAction
	@FXML
	public void modReclamation(ActionEvent event) {
		try {

            Parent parent = FXMLLoader.load(getClass().getResource("FXMLReclamation.fxml"));
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
	// Event Listener on Button[#btn_Livraison].onAction
	@FXML
	public void modLivraison(ActionEvent event) {
		try {

            Parent parent = FXMLLoader.load(getClass().getResource("FXMLDelivery.fxml"));
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
