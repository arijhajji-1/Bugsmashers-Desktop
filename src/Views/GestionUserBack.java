/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.shape.Circle;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import services.UserServices;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Hsine
 */
public class GestionUserBack implements Initializable {

    @FXML
    private TableView<User> tableuserback;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<User, Integer> iduser;
    @FXML
    private TableColumn<User, String> tel;
    @FXML
    private TableColumn<User, Integer> cin;
    @FXML
    private TableColumn<User,Integer> status;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> mdp;
    @FXML
    private TableColumn<User, Date> daten;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private TableColumn<User, String> photo;
    @FXML
    private Button pdf;
    UserServices reService = new UserServices();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //CabineCRUD resc=new CabineCRUD();
        ActionEvent event= new ActionEvent();
        AfficheResBack(event);
        iduser.setCellValueFactory(new PropertyValueFactory<>("id"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        nom.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mdp.setCellValueFactory(new PropertyValueFactory<>("password"));
        daten.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));

        tableuserback.setEditable(true);

        tableuserback.getSelectionModel().setCellSelectionEnabled(true);

        //IdREScol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        adresse.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setCellFactory(TextFieldTableCell.forTableColumn());
        mdp.setCellFactory(TextFieldTableCell.forTableColumn());
        photo.setCellFactory(TextFieldTableCell.forTableColumn());
        role.setCellFactory(TextFieldTableCell.forTableColumn());
        iduser.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tel.setCellFactory(TextFieldTableCell.forTableColumn());
        cin.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        status.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        daten.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));


        AfficheResBack(event);


    }

    @FXML
    private void OnEdittel(TableColumn.CellEditEvent<User, String> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setTelephone(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    private void OnEditcin(TableColumn.CellEditEvent<User, Integer> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setCin(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    private void OnEditStatus(TableColumn.CellEditEvent<User, Integer> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setStatus(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    private void OnEditPrenom(TableColumn.CellEditEvent<User, String> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setFirstName(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    private void OnEditNom(TableColumn.CellEditEvent<User, String> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setLastName(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    private void OnEditAdresse(TableColumn.CellEditEvent<User, String> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setAdresse(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    private void OnEditEmail(TableColumn.CellEditEvent<User, String> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setEmail(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    private void OnEditMdp(TableColumn.CellEditEvent<User, String> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setPassword(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    private void OnEditRole(TableColumn.CellEditEvent<User, String> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setRoles(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    private void OnEditPhoto(TableColumn.CellEditEvent<User, String> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setPhoto(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    public void AfficheResBack(ActionEvent event) {
        UserServices reService = new UserServices();
        List<User> liste = UserServices.listerUsers();
        ObservableList<User> olist = FXCollections.observableArrayList(liste);


        tableuserback.setItems(olist);
    }

    @FXML
    private void SupprimerResBack(ActionEvent event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        UserServices vService = new UserServices();
        UserServices.supprimerUser(re);

        AfficheResBack(event);
    }
    /*@FXML
    void pdf(ActionEvent event) {
        Printer printer = Printer.getDefaultPrinter();
        Node node = new Circle(400, 800, 800);
        PrinterJob job = PrinterJob.createPrinterJob(printer);
        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow())
            boolean success = job.printPage(tableuserback);
            if (success) {
                job.endJob();
            }
        }
    }
*/
    @FXML
    void pdf(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.tableuserback;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            Node node = new Circle(400, 800, 800);

            boolean success = job.printPage(tableuserback);
            if (success) {
                job.endJob();
            }
        }

    }

    @FXML
    private void Rechercher(ActionEvent event) {
        UserServices evc = new UserServices();



        List<User> liste = evc.listerUsers();
        ObservableList<User> olist = FXCollections.observableArrayList(liste);
        try{

            tableuserback.setItems(olist);
            FilteredList<User> filtredData = new FilteredList<>(olist, b -> true);
            recherche.textProperty().addListener((observable, olValue, newValue)->{
                filtredData.setPredicate(person-> {
                    if(newValue == null|| newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter= newValue.toLowerCase();

                    if(person.getFirstName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }
                    else if(String.valueOf(person.getLastName()).indexOf(lowerCaseFilter)!=-1)
                        return true;
                    else
                        return false;
                });
            });
            SortedList<User> sortedData = new SortedList<User>(filtredData);
            sortedData.comparatorProperty().bind(tableuserback.comparatorProperty());
            tableuserback.setItems(sortedData);

        }catch(Exception e){
            System.out.println(e.getMessage());

        }


    }
    @FXML
    void excel(ActionEvent event) {
        String filename = "C:\\Users\\Hsine\\bugSmashers\\GestionUserh\\export\\Reclamation.xlsx";
        try
        {
            //creating an instance of HSSFWorkbook class
            HSSFWorkbook workbook = new HSSFWorkbook();
            //invoking creatSheet() method and passing the name of the sheet to be created
            HSSFSheet sheet = workbook.createSheet("List Users");
            //creating the 0th row using the createRow() method
            HSSFRow rowhead = sheet.createRow((short)0);
            //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method
            rowhead.createCell((short)0).setCellValue("ID");
            rowhead.createCell(Short.parseShort("1")).setCellValue("First Name");
            rowhead.createCell(Short.parseShort("2")).setCellValue("Last Name");
            rowhead.createCell(Short.parseShort("3")).setCellValue("Email");
            rowhead.createCell(Short.parseShort("4")).setCellValue("Adresse");
            for(int i=0;i<reService.listerUsers().size();i++){
                HSSFRow row = sheet.createRow((short)i+1);
                //inserting data in the first row
                row.createCell(Short.parseShort("0")).setCellValue(reService.listerUsers().get(i).getId());
                row.createCell(Short.parseShort("1")).setCellValue(reService.listerUsers().get(i).getFirstName());
                row.createCell(Short.parseShort("2")).setCellValue(reService.listerUsers().get(i).getLastName());
                row.createCell(Short.parseShort("3")).setCellValue(reService.listerUsers().get(i).getEmail());
                row.createCell(Short.parseShort("4")).setCellValue(reService.listerUsers().get(i).getAdresse());
            }
            //creating the 1st row


            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            //closing the Stream
            fileOut.close();
            //closing the workbook

            //prints the message on the console
            System.out.println("Excel file has been generated successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
    

