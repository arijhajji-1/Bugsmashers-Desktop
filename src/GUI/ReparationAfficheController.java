/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Reparation;
import Services.ServiceReparation;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.beans.value.ChangeListener;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class ReparationAfficheController implements Initializable {
 ObservableList<Reparation> reparations = FXCollections.observableArrayList();
 ServiceReparation sp= new ServiceReparation();
                public static Reparation reparationActuelle ;
    private final static int rowsPerPage = 2;
        private final List<Reparation> data = createData();    
Reparation u1 = new Reparation();
        Reparation reparation ;
    @FXML
    private TableView<Reparation> tab;
    @FXML
    private TableColumn<Reparation, String> tabcategory;
    @FXML
    private TableColumn<Reparation, String> tabType;
    @FXML
    private TableColumn<Reparation, String> tabDescription;
    @FXML
    private TableColumn<Reparation, String> tabDate;
    @FXML
    private TableColumn<Reparation, String> mail;
    @FXML
    private TableColumn<Reparation, String> tell;
    @FXML
    private TableColumn<Reparation, String> etat;
    @FXML
    private TableColumn<Reparation, String> iduser;
    @FXML
    private TableColumn<Reparation, String> action;
    @FXML
    private TextField filterField;
    @FXML
    private Label label;
    private Button backButton;
    @FXML
    private Button avis;
    private ImageView exit;
    @FXML
    private AnchorPane drawerPane;
    @FXML
    private ImageView exit1;
    String numTelephone;
    @FXML
    private Pagination pagination;
    @FXML
    private JFXComboBox<String> tri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     tri.getItems().setAll("category", "type", "date");

    // bind the selected fruit label to the selected fruit in the combo box.
  //  LBshow.textProperty().bind(trinom.getSelectionModel().selectedItemProperty());

    // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
      tri.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

      @Override 
      public void changed(ObservableValue<? extends String> selected, String oldFruit, String newFruit) {
     if(newFruit=="category"){  ObservableList<Reparation> list2 = FXCollections.observableArrayList(sp.tristreamcategory());
       tab.setItems(list2);}
     if(newFruit=="type"){  ObservableList<Reparation> list2 = FXCollections.observableArrayList(sp.tristreamtype());
       tab.setItems(list2);}
     if(newFruit=="date"){  ObservableList<Reparation> list2 = FXCollections.observableArrayList(sp.tristreamdate());
       tab.setItems(list2);}
    }  });

        pagination.setPageFactory(this::createPage);    

      loadDate() ;
      
    }
  private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
        tab.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
        return tab;
    }
 private void refreshTable() {
          reparations .clear();

      List<Reparation> listreparations = ServiceReparation.getInstance().afficher();

        if (!listreparations.isEmpty()) {
            for (int i = 0; i < listreparations.size(); i++) {
                reparation = listreparations.get(i);
                reparations.add(reparation);
            }
        }
     tab.setItems(reparations );
    }
      private void loadDate() {
              exit1.setOnMouseClicked(event -> {
            System.exit(0);
        });
        List<Reparation> listreparations = ServiceReparation.getInstance().afficher();

        if (!listreparations.isEmpty()) {
            for (int i = 0; i < listreparations.size(); i++) {
                reparation = listreparations.get(i);
                reparations.add(reparation);
            }
        }
            refreshTable();

        tabcategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tabType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tabDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tabDate.setCellValueFactory(new PropertyValueFactory<>("Reserver"));
       mail.setCellValueFactory(new PropertyValueFactory<>("email"));
       tell.setCellValueFactory(new PropertyValueFactory<>("telephone"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       iduser.setCellValueFactory(new PropertyValueFactory<>("iduser"));
       reparationActuelle= null;

        tab.setItems(reparations);
      Callback<TableColumn<Reparation, String>, TableCell<Reparation, String>> cellFoctory = (TableColumn<Reparation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reparation, String> cell = new TableCell<Reparation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                       // FontAwesomeIconView deleteicon = new FontAwesomeIconView(FontAwesomeIcon.REMOVE);

                        FontAwesomeIconView editicon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                     

                      
                        editicon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                       
                  
                
 editicon.setOnMouseClicked((MouseEvent event) -> {
                            reparation = tab.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("ModifierEtat.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ReparationAfficheController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                                          refreshTable();

                            ModifierEtatController modifier = loader.getController();
                          //  String a = "" + reparation.getId();
                          //  modifier.setTextField(reparation.getDescription(), a);
                          // ReparationController.modifier(reparation.getId(), reparation.getDescription());
                           modifier.setUpdate(true);
                           modifier.setTextField(reparation.getId(),reparation.getEtat(),reparation.getTelephone());
                           
                             refreshTable();

                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
         


                           

                        });
                        HBox managebtn = new HBox(editicon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editicon, new Insets(2, 3, 0, 2));


                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         action.setCellFactory(cellFoctory);
         tab.setItems(reparations);
        //// Wrap the ObservableList in a FilteredList (initially display all data).
       filterField.textProperty().addListener((observable, oldValue, newValue) -> {
       
       
        String nom = filterField.getText();
        u1.setCategory(nom);
        u1.setType(nom);
        u1.setDescription(nom);
        u1.setReserver(nom);
        u1.setTelephone(nom);
        u1.setEmail(nom);
        u1.setEtat(nom);
         
        
         try{
             int cin1 = Integer.parseInt(nom);
             u1.setIduser(cin1);
         }
   catch(Exception e){}
    //   LBshow.setText(nom);
          ObservableList<Reparation> list1 = FXCollections.observableArrayList(sp.rechstream(u1));

    tab.setItems(list1);
    if(filterField.getText().trim().isEmpty()){    tab.setItems(reparations);}
   ;
});
        // TODO
    }    
 
    @FXML
    private void selectReparation(MouseEvent event) {
              reparationActuelle = tab.getSelectionModel().getSelectedItem();
    }

   

    @FXML
    private void avis(ActionEvent event) {
        try {
            FXMLLoader main = new FXMLLoader(getClass().getResource("ListAvis.fxml"));
            Parent root = (Parent) main.load();
         
            this.avis.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e);
        } 
    }

     private List<Reparation> createData() {
      List<Reparation> listreparations = ServiceReparation.getInstance().afficher();

        if (!listreparations.isEmpty()) {
            for (int i = 0; i < listreparations.size(); i++) {
                reparation = listreparations.get(i);
                reparations.add(reparation);
            }
        }


        return listreparations;
    }
     
}
