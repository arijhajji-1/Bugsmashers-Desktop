package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import GUI.LivraisonFXMLController;
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

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


public class statistiquesController implements Initializable {
	@FXML
    private LineChart<?,?> linechart;

	@FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
	
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
	      //statJour();
	      
	    }  
	 
	 private void stat()
	    {
	          
		 XYChart.Series series= new XYChart.Series(); 
	      try {
	           
	          String query = "SELECT  COUNT(*),region FROM `LIVRAISON` GROUP BY region" ;
	       
	             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
	             rs = PreparedStatement.executeQuery();
	            
	             series.setName("region");
	                     
	            while (rs.next()){  
	            	series.getData().add(new XYChart.Data(rs.getString("region"),rs.getInt("COUNT(*)")));
	              // data.add(new PieChart.Data(rs.getString("region"),rs.getInt("COUNT(*)")));
	            }     
	        } catch (SQLException ex) {
	            Logger.getLogger(LivraisonFXMLController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	      
	       /* piechart.setTitle("**Statistiques Livraisons par région**");
	        piechart.setLegendSide(Side.LEFT);
	        piechart.setData(data);*/
	      linechart.getData().add(series);
	    
	    }  
	 
	 
	   
	           
	      
	   
	 
	
}
		 
		
	       
        
       
        	 
        	 
	 
        	 
        	 
		 
	
	    
	 

