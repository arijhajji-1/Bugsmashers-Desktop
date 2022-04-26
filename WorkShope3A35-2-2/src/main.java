import Model.Actualite;
import Services.ServiceActualite;
import Services.ServiceEvenement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class main extends Application{

    public static void main(String[] args) {
       /* Actualite p2 = new Actualite(33,"kl","2022-09-09","dfgbgfhjkj","dfghjk");


        ServiceActualite sp =  new ServiceActualite();
        sp.modifier(p2);
        System.out.println(sp.afficher().toString());*/
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/mainMenu.fxml"));
        Scene scene =new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("----- Menu ------");
        primaryStage.show();
    }
}
