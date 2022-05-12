package GUI;

import java.io.IOException;
import java.time.YearMonth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainHome extends Application {
	
	
    @Override
    public void start(Stage primaryStage) {
         try {
          Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
           // primaryStage.setScene(new Scene(new FullCalendarView(YearMonth.now()).getView()));
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
