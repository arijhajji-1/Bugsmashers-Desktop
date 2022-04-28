package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserServices;

import java.io.IOException;



public class LoginUser {

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private TextField password;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void login(ActionEvent event) throws IOException {

        String email1=email.getText();
        String password1=password.getText();
        UserServices sp = new UserServices();
        if((sp.login(email1, password1)==true)){
            root = FXMLLoader.load(getClass().getResource("Reparation.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Front");
            stage.setScene(scene);
            stage.show();
        }else
        {
            System.out.print("nnnnnnnn");
        }

    }
}

