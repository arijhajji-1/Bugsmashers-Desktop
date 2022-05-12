package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import utils.MyDb;

public class statReclamationController implements Initializable{
	  @FXML
	    private PieChart piechart;
	  private Statement st;
	    private ResultSet rs;
	    private Connection cnx;
	  
	   ObservableList <PieChart.Data> data= FXCollections.observableArrayList(
				/* new PieChart.Data("ariana", 1),
				 new PieChart.Data("aouina", 1),
				 new PieChart.Data("lac 1", 1)*/
				 ); 

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		 cnx=MyDb.getInstance().getCnx();
	     statJour();
		
	}
	
	   
	    private void statJour()
	    {
	          
	           
	      try {
	           
	          String query = "SELECT  COUNT(*),dayofweek(date) FROM `RECLAMATION` GROUP BY date" ;
	       
	             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
	             rs = PreparedStatement.executeQuery();
	             
	                     
	            while (rs.next()){  
	               data.add(new PieChart.Data(rs.getString("dayofweek(date)"),rs.getInt("COUNT(*)")));
	            }     
	        }
	      catch (SQLException ex) {
	            Logger.getLogger(FXMLReclamationController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	      
	      piechart.setTitle("**Statistiques reclamation par jour**");
	        piechart.setLegendSide(Side.LEFT);
	        piechart.setData(data);
	    
	    }  
	 
	   
	  
	
}
