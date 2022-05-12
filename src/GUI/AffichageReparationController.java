/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.AvisReparation;
import Model.Reparation;
import Services.ServiceAvisReparation;
import Services.ServiceReparation;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import javafx.scene.control.TableView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import Services.LoginSession;

/**
 * FXML Controller class
 *
 * @author Arij Hajji
 */
public class AffichageReparationController implements Initializable {
 ObservableList<Reparation> reparations = FXCollections.observableArrayList();
                public static Reparation reparationActuelle ;
    private final static int rowsPerPage = 2;
        Reparation reparation ;
    
    private TableView<Reparation> tab;
    private TextField filterField;
    private Button backButton;
    
// private ObservableList<Reparation> listreparations;
    private TableColumn<Reparation, String> action;
    @FXML
    private Button Ajouter;
    @FXML
    private TextField searchedTXT;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    MyListener myListener;
    @FXML
    private ImageView refreshImgV;
    @FXML
    private ImageView exit1;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
         //pagination.setPageFactory(this::createPage);
         loadDate() ;
     } catch (IOException ex) {
         Logger.getLogger(AffichageReparationController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
 
 private void refreshTable() throws IOException {
         
grid.getChildren().clear();
       reparations.clear(); 
       getData();
    }
  private ArrayList<Reparation> getData() throws IOException {
        ArrayList<Reparation> reparations = new ArrayList<>();
        Reparation rep;
        ServiceReparation ps = new ServiceReparation();
        
        reparations.addAll(ps.afficher2());
     
//refreshTable();
        return reparations;

    }

   
      private void loadDate() throws IOException {
          
        exit1.setOnMouseClicked(event -> {
            System.exit(0);
        });
        List<Reparation> listreparations = ServiceReparation.getInstance().afficher2();

         
        reparations.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < reparations.size(); i++) {
              
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("reparationModel.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                reparationModelController itemController = fxmlLoader.getController();
                itemController.setData(reparations.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
             } catch (IOException e) {
            e.printStackTrace();
        }

      
    }    
 
   

    

    @FXML
    private void Ajouter(ActionEvent event) {
         try {
            FXMLLoader main = new FXMLLoader(getClass().getResource("Reparation.fxml"));
            Parent root = (Parent) main.load();
         
            this.Ajouter.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e);
        } 
    }

    @FXML
    private void searchedAvance(KeyEvent event) {
         grid.getChildren().clear();

        ServiceReparation gs = new ServiceReparation();
        java.util.List<Reparation> mylist = new ArrayList<>();

        mylist = gs.recherche(searchedTXT.getText());

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < mylist.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("reparationModel.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                reparationModelController itemController = fxmlLoader.getController();
                itemController.setData(mylist.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    private void RechercheGuide(ActionEvent event) {
          ServiceReparation gs = new ServiceReparation();
        
  int column = 0;
        int row = 1;
        for (int i = 0; i < reparations.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("reparationModel.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                reparationModelController itemController = fxmlLoader.getController();
                itemController.setData(reparations.get(i), myListener);
                if (column == 1) {
                    column = 0;
                    row++;

                }

                grid.add(anchorPane, column++, row);

                GridPane.setMargin(anchorPane, new Insets(10));
                // setGrid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                // setGrid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void ClearAll(MouseEvent event) throws IOException {
            grid.getChildren().clear();
            reparations.clear();
             loadDate();
    }

   

    
    
}
