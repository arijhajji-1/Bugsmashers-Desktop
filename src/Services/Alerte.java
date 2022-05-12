/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Alerte {
    public static void display(String title,String message){
Stage window=new Stage();
window.setTitle(title);
window.setMinWidth(250);
window.setMaxHeight(100);
Label label=new Label();
label.setText(message);
Button buttonOK=new Button("OK");
buttonOK.setOnAction(e->window.close());
VBox layout = new VBox(5);
layout.getChildren().addAll(label,buttonOK);
layout.setAlignment(Pos.CENTER);
Scene scene=new Scene(layout);
window.setScene(scene);
window.showAndWait();
}

}
