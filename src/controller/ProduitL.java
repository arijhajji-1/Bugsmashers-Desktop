package controller;

import Model.Category;
import Model.ProduitLouer;
import Services.ServiceCategoryProduit;
import Services.ServiceProduitLouerProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ProduitL implements Initializable {
    @FXML
    TextField TFnom, TFprix, TFmarque, TFetat;
    @FXML
    CheckBox checkDispo;
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
        String etat;
        float prix;
        nom = TFnom.getText();
        marque = TFmarque.getText();
        description = TFdescription.getText();
        category = (String) TFcategorie.getValue();
        etat = TFetat.getText();
        prix = Float.parseFloat(TFprix.getText());

        if ( nom.isEmpty() || marque.isEmpty()||category.isEmpty()||description.isEmpty()||etat.isEmpty()||prix==0){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("champs vide");
            errorAlert.setContentText("vous devez remplir les champs");
            errorAlert.showAndWait();
        }
        else {int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Insert ", "Insert", JOptionPane.YES_NO_OPTION);
            if (opt==0&&update==false){
            ServiceProduitLouerProduit pa = new ServiceProduitLouerProduit();
                List<Category> lpc = ServiceCategoryProduit.getInstance().Rech("label", ((String) TFcategorie.getValue()).toLowerCase());
                pa.ajouter(new ProduitLouer(0,prix,checkDispo.isSelected(),nom,etat,description,imagepath,marque,String.valueOf(lpc.get(0).getId())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("ProduitLouer added");
            alert.showAndWait();

        }
        else{
            ServiceProduitLouerProduit pa = new ServiceProduitLouerProduit();
                List<Category> lpc = ServiceCategoryProduit.getInstance().Rech("label", ((String) TFcategorie.getValue()).toLowerCase());
                pa.modifier(new ProduitLouer(idProduitA,prix,checkDispo.isSelected(),nom,etat,description,imagepath,marque,String.valueOf(lpc.get(0).getId())));Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("ProduitLouer Modified");
            alert.showAndWait();}

        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Category cat : ServiceCategoryProduit.getInstance().afficher()) {
            TFcategorie.getItems().add(cat.getLabel());
        }
    }
    void setUpdate(boolean b) {
        this.update = b;

    }
    void setValues(boolean dispo, String etat,String category, String marque, String description,String nom,int id,String imagePath,float prix) {

        idProduitA= id;

        TFcategorie.setValue(category);
        TFnom.setText(nom);
        TFetat.setText(etat);
        checkDispo.setSelected(dispo);
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
