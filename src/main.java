import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application{

    public static void main(String[] args) {
       /* Actualite p2 = new Actualite(33,"kl","2022-09-09","dfgbgfhjkj","dfghjk");


        ServiceActualite sp =  new ServiceActualite();
        sp.modifier(p2);
        System.out.println(sp.afficher().toString());*/

        /*ServiceAvisEvenement av = new ServiceAvisEvenement();
        AvisEvenement avi=new AvisEvenement(0,4,"nour","description avis","nour@gmail.com");
        av.ajouter(avi);
        System.out.println(av.afficher(4));
         */
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("GUI/mainMenu.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Home.fxml"));
        Scene scene =new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("----- Home ------");
        primaryStage.show();
    }
}
