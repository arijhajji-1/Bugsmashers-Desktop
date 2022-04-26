package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import utils.MyDb;
import javafx.collections.FXCollections;

import javafx.geometry.Side;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import GUI.FXMLDeliveryController;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


public class statistiquesController implements Initializable {
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
	      stat();
	      
	    }  
	 
	 private void stat()
	    {
	          
	           
	      try {
	           
	          String query = "SELECT  COUNT(*),region FROM `LIVRAISON` GROUP BY region" ;
	       
	             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
	             rs = PreparedStatement.executeQuery();
	            
	                     
	            while (rs.next()){               
	               data.add(new PieChart.Data(rs.getString("region"),rs.getInt("COUNT(*)")));
	            }     
	        } catch (SQLException ex) {
	            Logger.getLogger(FXMLDeliveryController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	      
	        piechart.setTitle("**Statistiques Livraisons**");
	        piechart.setLegendSide(Side.LEFT);
	        piechart.setData(data);
	    
	    }  
	    

}
