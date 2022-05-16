package controller;

import Model.Evenement;
import Services.ServiceEvenementActualite;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

public class evenementController implements Initializable {
    Evenement evenement;
    @FXML
    private Button btnajouter;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;

    @FXML
    private TableColumn<Evenement, String> colDate;

    @FXML
    private TableColumn<Evenement, String> colDescription;

    @FXML
    private TableColumn<Evenement, String> colTitre;

    @FXML
    private TableColumn<Evenement, String> colimageName;
    @FXML
    private TableColumn<Evenement, String> colHeure;

    @FXML
    private DatePicker date;

    @FXML
    private TextField tfdescription;

    @FXML
    private TextField tfimageName;

    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfheure;

    @FXML
    private TableView<Evenement> tvBocks;

    public void ajouterEvenement(ActionEvent actionEvent) {
        ServiceEvenementActualite sp= new ServiceEvenementActualite();

        if ( tftitre.getText().isEmpty() ||date.getValue()==null|| tfheure.getText().isEmpty()||tfdescription.getText().isEmpty()||tfimageName.getText().isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("champs vide");
            errorAlert.setContentText("vous devez remplir les champs");
            errorAlert.showAndWait();
        }else{

            sp.ajouter(new Evenement(tftitre.getText(), tfheure.getText(), tfdescription.getText(), tfimageName.getText(), date.getValue().toString()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            try {
                LocalDateTime now = LocalDateTime.now();
                Date dateApres = sdf.parse(date.getValue().toString());
                Date dateAvant = sdf.parse(LocalDateTime.now().toString());
                long diff = dateApres.getTime() - dateAvant.getTime();
                float res = (diff / (1000*60*60*24));
                System.out.println("Nombre de jours entre les deux dates est: "+res);
                alert.setContentText("Event added\n"+"Il reste : "+(int)res+" jours");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        ObservableList<Evenement> list = FXCollections.observableArrayList(sp.afficher());
        tvBocks.setItems(list);
        this.afficherEvenement(actionEvent);

    }

    public void modifierEvenement(ActionEvent actionEvent) {
        ServiceEvenementActualite sp = new ServiceEvenementActualite();
        evenement = tvBocks.getSelectionModel().getSelectedItem();
        if(!tftitre.getText().isEmpty())
            evenement.setNom(tftitre.getText());
        if(!tfdescription.getText().isEmpty())
            evenement.setDescription(tfdescription.getText());
        if(date.getValue() != null)
            evenement.setDate(date.getValue().toString());
        if(!tfimageName.getText().isEmpty()) {
            evenement.setImageName(tfimageName.getText());
        }
        if(!tfheure.getText().isEmpty()) {
            evenement.setHeure(tfheure.getText());
            System.out.println(evenement);
        }

            sp.modifier(evenement);

        this.afficherEvenement(actionEvent);
    }

    public void afficherEvenement(ActionEvent actionEvent) {
        ServiceEvenementActualite sp = new ServiceEvenementActualite();
        colTitre.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom"));
        colDate.setCellValueFactory(new PropertyValueFactory<Evenement, String>("date"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        colimageName.setCellValueFactory(new PropertyValueFactory<Evenement, String>("imageName"));
        colHeure.setCellValueFactory(new PropertyValueFactory<Evenement, String>("heure"));
        ObservableList<Evenement> list = FXCollections.observableArrayList(sp.afficher());
        System.out.println(list);
        tvBocks.setItems(list);
        tvBocks.setEditable(true);
    }

    public void supprimerEvenement(ActionEvent actionEvent) {
        evenement = tvBocks.getSelectionModel().getSelectedItem();
        ServiceEvenementActualite rep = new ServiceEvenementActualite();

        rep.supprimer(evenement);
        this.afficherEvenement(actionEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfheure.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfheure.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
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
            String filename = "i"+generateRandomPassword() + ".png";
            imagepath = filename;
            File outputfile = new File("C:\\Users\\Hsine\\gestion location commande\\src\\img\\"+filename);
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
@FXML
private TextField TFsearch;
    public void search(KeyEvent keyEvent) {
        tvBocks.getItems().clear();
        ServiceEvenementActualite se = new ServiceEvenementActualite();


        colTitre.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom"));
        colDate.setCellValueFactory(new PropertyValueFactory<Evenement, String>("date"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        colimageName.setCellValueFactory(new PropertyValueFactory<Evenement, String>("imageName"));
        colHeure.setCellValueFactory(new PropertyValueFactory<Evenement, String>("heure"));
        System.out.println(TFsearch.getText());
        String text = TFsearch.getText();
        ObservableList<Evenement> list = FXCollections.observableArrayList(se.rech(text));
        System.out.println(list);
        tvBocks.setItems(list);
        tvBocks.setEditable(true);


    }

    /*@FXML
    private void imprimer(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.tvBocks;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }
        }
    }*/
}
