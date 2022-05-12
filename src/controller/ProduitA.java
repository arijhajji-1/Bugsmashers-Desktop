package controller;

import Model.Category;
import Model.ProduitAcheter;
import Services.ServiceCategoryProduit;
import Services.ServiceProduitAcheterProduit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class ProduitA implements Initializable {
    @FXML
    TextField TFnom, TFprix, TFmarque, TFQte;
    @FXML
    TextArea TFdescription;
    @FXML
    ChoiceBox TFcategorie;
    final FileChooser fc= new FileChooser();
    private int idProduitA;
    private boolean update;
    private String imagepath;

    public void ajouterProduit(ActionEvent actionEvent) {
        String nom,marque,description,category;
        int qte;
        float prix;
        nom = TFnom.getText();
        marque = TFmarque.getText();
        description = TFdescription.getText();
        category = (String) TFcategorie.getValue();
        qte = Integer.parseInt(TFQte.getText());
        prix = Float.parseFloat(TFprix.getText());

        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Insert ", "Insert", JOptionPane.YES_NO_OPTION);
        if ( nom.isEmpty() || marque.isEmpty()||category.isEmpty()||description.isEmpty()||qte==0||prix==0){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("champs vide");
            errorAlert.setContentText("vous devez remplir les champs");
            errorAlert.showAndWait();
        }
        else {if (opt==0&&update==false){
            ServiceProduitAcheterProduit pa = new ServiceProduitAcheterProduit();
            List<Category> lpc = ServiceCategoryProduit.getInstance().Rech("label", ((String) TFcategorie.getValue()).toLowerCase());
            pa.ajouter(new ProduitAcheter(0,qte,prix,nom,description,imagepath,marque,String.valueOf(lpc.get(0).getId())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("ProduitAcheter added");
            alert.showAndWait();

        }
        else{
            ServiceProduitAcheterProduit pa = new ServiceProduitAcheterProduit();
            List<Category> lpc = ServiceCategoryProduit.getInstance().Rech("label", ((String) TFcategorie.getValue()).toLowerCase());
            pa.modifier(new ProduitAcheter(idProduitA,qte,prix,nom,description,imagepath,marque, String.valueOf(lpc.get(0).getId())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("ProduitAcheter Modified");
            alert.showAndWait();}

        }


        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Category cat : ServiceCategoryProduit.getInstance().afficher()) {
                TFcategorie.getItems().add(cat.getLabel());
        }
        TFQte.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    TFQte.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    void setUpdate(boolean b) {
        this.update = b;

    }
    public String categorie;
    void setValues(int qte, String category, String marque, String description,String nom,int id,String imagePath,float prix) {
        idProduitA= id;
        TFcategorie.setValue(category);
        TFnom.setText(nom);
        TFQte.setText(String.valueOf(qte));
        TFdescription.setText(description);
        TFmarque.setText(marque);
        TFprix.setText(String.valueOf(prix));
        imagepath=imagePath;

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
    public void uploader(ActionEvent actionEvent) throws IOException {
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image file","*.png"));
        File file = fc.showOpenDialog(null);

        if(file!=null)
        {
            BufferedImage bi = ImageIO.read(file);
            String filename = "i"+generateRandomPassword() + ".png";
            imagepath = filename;
            File outputfile = new File("C:\\Users\\Hsine\\gestion location commande\\src\\img\\"+filename);
            try {
                ImageIO.write(bi, "png", outputfile);
                System.out.println(bi);
            } catch(Exception e){
                System.out.println(e.getCause());
            }
        }
    }
}
