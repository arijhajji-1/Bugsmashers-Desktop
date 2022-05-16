/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Services.*;
import Services.SendEmailWthImage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import Model.User;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.sql.Date;
import java.util.Hashtable;
import java.util.ResourceBundle;
import static javax.swing.JOptionPane.showMessageDialog;


/**
 * FXML Controller class
 *
 * @author Hsine
 */
public class UserSignup implements Initializable {

    @FXML
    private Label Cinctrl;

    @FXML
    private TextField Nomf;

    @FXML
    private Label adressectrl;

    @FXML
    private TextField adressef;

    @FXML
    private TextField cinf;

    @FXML
    private DatePicker datenf;

    @FXML
    private Label emailctrl;

    @FXML
    private TextField emailf;

    @FXML
    private Button login;

    @FXML
    private TextField mdpf;

    @FXML
    private Label nomctrl;

    @FXML
    private Label passwordctrl;

    @FXML
    private AnchorPane pnSignUp;

    @FXML
    private Label prenomctrl;

    @FXML
    private TextField prenomf;

    @FXML
    private Circle profilepicture;

    @FXML
    private Label telctrl;

    @FXML
    private TextField telf;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane pnVerif;
    String f;
    private static  String res;
    @FXML
    private TextField tfVerif;
    public static User userConn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pnSignUp.toFront();
    }

    @FXML
    private void signin(ActionEvent event) {
       ControleSaisie w =new ControleSaisie();
        if (!w.testnomprenom(Nomf.getText()))
        {
            nomctrl.setText("Erreur ! Veuillez insérer un Nom valide");
        }
        else
        {
            nomctrl.setText("");
        }
        if (!w.testnomprenom(prenomf.getText()))
        {
            prenomctrl.setText("Erreur ! Veuillez insérer un Prénom valide");
        }
        else
        {
            prenomctrl.setText("");
        }
        if (!w.Num(cinf.getText()))
        {
            Cinctrl.setText("Erreur ! Veuillez insérer un nbre valide");
        }
        else
        {
            Cinctrl.setText("");
        }
        if (!w.Num(telf.getText()))
        {
            telctrl.setText("Erreur ! Veuillez insérer un num valide");
        }
        else
        {
            telctrl.setText("");
        }
        if (!w.testadresse(adressef.getText()))
        {
            adressectrl.setText("Erreur ! Veuillez insérer un adresse valide");
        }
        else
        {
            adressectrl.setText("");
        }
        String Nom =Nomf.getText();
        String prenom =prenomf.getText();
        String email =emailf.getText();
        String mdp =mdpf.getText();
        String adresse =adressef.getText();
        int cin = Integer.parseInt(cinf.getText());
        String tel = telf.getText();
        UserServices cc = new UserServices();
        Date daten = java.sql.Date.valueOf(datenf.getValue());
        mdp=cc.crypter_password(mdp);
        User u = new User( tel,cin,1,prenom,Nom, adresse,email,mdp,daten,"[\"ROLE_USER\"]",f);
        int id= cc.ajouteruser(u);
        u.setId(id);
        userConn=u;
        Charset charset = Charset.forName("UTF-8");
        CharsetEncoder encoder = charset.newEncoder();
        byte[] b = null;
        try {
            // Convert a string to UTF-8 bytes in a ByteBuffer
            String result = RandomStringUtils.random(8, false, true);
            res=result;
            ByteBuffer bbuf = encoder.encode(CharBuffer.wrap("Votre code de validation de compte : "+result));
            b = bbuf.array();
        } catch (CharacterCodingException e) {
            System.out.println(e.getMessage());
        }

        String data;
        ByteMatrix matrix = null;
       /* try {
            data = new String(b, "UTF-8");
            // get a byte matrix for the data
            ByteMatrix matrix = null;
            int h = 100;
            int x = 100;
            com.google.zxing.Writer writer = new MultiFormatWriter();
            try {
                Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(2);
                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                matrix = writer.encode(data, com.google.zxing.BarcodeFormat.QR_CODE, x, h, hints);
            } catch (com.google.zxing.WriterException e) {
                System.out.println(e.getMessage());
            }*/
        try {
            data = new String(b, "UTF-8");
            Image im = QRcodeGenerator.generateQRCodeImage(data,100,100,"qr_png.png");
            //QRCodeWriter qrCodeWriter = new QRCodeWriter();
            //ByteMatrix byteMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 180, 100);


        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

         /*   // change this path to match yours (this is my mac home folder, you can use: c:\\qr_png.png if you are on windows)
            String filePath = "C:/Users/Hsine/bugSmashers/GestionUserh/img/qr_png.png";
            File file = new File(filePath);
            try {
                MatrixToImageWriter.writeToFile(matrix, "PNG", file);
                System.out.println("printing to " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }*/

        String htmlMessage = "Votre compte est en attente scanner ce QR code pour recevoir code de activation  ! ";
        new SendEmailWthImage(u.getEmail(),"Compte En attente",htmlMessage,"C:\\Users\\Hsine\\gestion location commande\\src\\img\\"+"qr_png.png");


        pnVerif.toFront();


    }

        @FXML
        public void uploadsiguppic (ActionEvent event)
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            fileChooser.setInitialDirectory(new File("C:\\Users\\Hsine\\BugSmashers\\GestionUserh\\src\\images"));
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                String TempprofilePicture = file.toURI().toString();
                System.out.println(TempprofilePicture);
                Image image = new Image(TempprofilePicture);
                ImagePattern pattern = new ImagePattern(image);
                UsersSession.setProfilepicture(TempprofilePicture);
                profilepicture.setFill(pattern);
                profilepicture.setStroke(Color.SEAGREEN);
                profilepicture.setEffect(new DropShadow(20, Color.BLACK));
                f = file.getAbsolutePath();
            }

        }
        @FXML
        public void switchToSignup (ActionEvent event) throws IOException
        {
            //System.out.println("hello");
            root = FXMLLoader.load(getClass().getResource("loginUser.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void fnVerifier (ActionEvent event) throws IOException {
            if (tfVerif.getText().equals(res)) {
                root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Front");
                stage.setScene(scene);
                stage.show();
                showMessageDialog(null, "votre compte est verifier");
                LoginSession.UID = userConn.getId();
                LoginSession.Roles = userConn.getRoles();
                LoginSession.firstName = userConn.getFirstName();
                LoginSession.email = userConn.getEmail();
                //LoginSession.avatar=rs.getString("avatar");
                LoginSession.IsLogged = true;
            } else {
                showMessageDialog(null, "enter Valid number");
            }
        }

    }

