package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class eventPage implements Initializable {


    @FXML
    private AnchorPane content;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("fuck");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/Reparation.fxml"));
        try {
            content.getChildren().setAll((Node) loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
