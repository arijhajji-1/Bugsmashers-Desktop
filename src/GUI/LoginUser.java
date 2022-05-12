package GUI;

import Model.User;
import Services.LoginSession;
import Services.UserServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


public class LoginUser {

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;


    private Stage stage;
    private Scene scene;
    private Parent root;
    public static User user;


    public void login(ActionEvent event) throws IOException {

        UserServices cc=new UserServices();

        String email1=(email.getText());
        String password1=cc.crypter_password(password.getText());
        UserServices sp = new UserServices();
        if((sp.login(email1, password1)==true)){
            System.out.println(LoginSession.Roles);
            if(LoginSession.Roles.equals("[\"ROLE_ADMIN\"]")){

                root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Dashboard");
                stage.setScene(scene);
                stage.show();
            }else{
                root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Profile");
                stage.setScene(scene);
                stage.show();
            }

        }else
        {
            System.out.print("nope");
            User u=UserSignup.userConn;
        }

    }
    /*@FXML
    private void showRegisterStage() throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginUser.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }*/
}


