package controller;
import Model.Actualite;
import Services.ServiceActualite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
public class actualiteController implements Initializable
{
    Actualite actualite;
@FXML
private Button btnajouter;

@FXML
private Button btnmodifier;

@FXML
private Button btnsupprimer;

@FXML
private TableColumn<Actualite, String> colDate;

@FXML
private TableColumn<Actualite, String> colDescription;

@FXML
private TableColumn<Actualite, String> colTitre;

@FXML
private TableColumn<Actualite, String> colimageName;


    @FXML
    private DatePicker date;

@FXML
private TextField tfdescription;

@FXML
private TextField tfimageName;

@FXML
private TextField tftitre;

@FXML
private TableView<Actualite> tvBocks;

    @FXML
    void afficherActualite(ActionEvent event){
        ServiceActualite sp = new ServiceActualite();
        colTitre.setCellValueFactory(new PropertyValueFactory<Actualite, String>("titre"));
        colDate.setCellValueFactory(new PropertyValueFactory<Actualite, String>("date"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Actualite, String>("description"));
        colimageName.setCellValueFactory(new PropertyValueFactory<Actualite, String>("imageName"));
        ObservableList<Actualite> list = FXCollections.observableArrayList(sp.afficher());
        System.out.println(list);
        tvBocks.setItems(list);
        tvBocks.setEditable(true);
    }
    @FXML
    void ajouterActualite(ActionEvent event){
        ServiceActualite sp= new ServiceActualite();
        if ( tftitre.getText().isEmpty() || date.getValue()==null ||tfdescription.getText().isEmpty()||tfimageName.getText().isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("champs vide");
            errorAlert.setContentText("vous devez remplir les champs");
            errorAlert.showAndWait();
        }else {

                sp.ajouter(new Actualite(tftitre.getText(), tfdescription.getText(), tfimageName.getText(), date.getValue().toString()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Actualite added");
                alert.showAndWait();

        }
        ObservableList<Actualite> list = FXCollections.observableArrayList(sp.afficher());
            tvBocks.setItems(list);
        this.afficherActualite(event);
    }

    @FXML
    void modifierActualite(ActionEvent event){
        ServiceActualite sp = new ServiceActualite();
        actualite = tvBocks.getSelectionModel().getSelectedItem();
        if(!tftitre.getText().isEmpty())
        actualite.setTitre(tftitre.getText());
        if(!tfdescription.getText().isEmpty())
        actualite.setDescription(tfdescription.getText());
        if(date.getValue() != null)
        actualite.setDate(date.getValue().toString());
        if(!tfimageName.getText().isEmpty()) {
            actualite.setImageName(tfimageName.getText());
        }

            sp.modifier(actualite);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Actualite modified");
            alert.showAndWait();

        this.afficherActualite(event);
    }

    @FXML
    void supprimerActualite(ActionEvent event){
        actualite = tvBocks.getSelectionModel().getSelectedItem();
        ServiceActualite rep = new ServiceActualite();
        rep.supprimer(actualite);
        this.afficherActualite(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    final FileChooser fc= new FileChooser();
    private String imagepath;

    public void UploadImage(ActionEvent actionEvent) throws IOException {
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image file","*.png"));
        File file = fc.showOpenDialog(null);

        if(file!=null)
        {
            BufferedImage bi = ImageIO.read(file);
            String filename = "a"+generateRandomPassword() + ".png";
            imagepath = filename;
            File outputfile = new File("/Users/nourmakkari/Downloads/WorkShope3A35-2-2/src/img/"+filename);
            try {
                ImageIO.write(bi, "png", outputfile);
                System.out.println(bi);
                tfimageName.setText(imagepath);
            } catch(Exception e){
                System.out.println(e.getCause());
            }
        }
    }
    public static String generateRandomPassword() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                +"lmnopqrstuvwxyz_-";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

}
