package Views;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.LoginSession;
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
    public static User user;


    public void login(ActionEvent event) throws IOException {

        UserServices cc=new UserServices();

        String email1=cc.crypter_password(email.getText());
        String password1=password.getText();
        UserServices sp = new UserServices();
        if((sp.login(email1, password1)==true)){
            if(LoginSession.Roles.equals("[\n" +
                    "    \"ROLE_USER\"\n" +
                    "]")){

                root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Front");
                stage.setScene(scene);
                stage.show();
            }else{
                root = FXMLLoader.load(getClass().getResource("GestionUserBack.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Front");
                stage.setScene(scene);
                stage.show();
            }

        }else
        {
            System.out.print("nnnnnnnn");
            User u=UserSignup.userConn;
        }

    }
}

