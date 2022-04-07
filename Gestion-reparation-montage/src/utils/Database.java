/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Arij Hajji
 */
public class Database {
    


     final  String URL ="jdbc:mysql://127.0.0.1:3306/reloua";
   final  String LOGIN ="root";
   final  String PASSWORD ="";
  static   private Connection cnx;
  static  private Database instance ;
    
   private Database(){
       
       
       try {
           cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
           System.out.println("Connexion reussie ......");
       
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());      
       
       }
      
   }
   
  static public Database getInstance(){
       if(instance==null)
       instance= new Database();
       
       return instance;//null
   }
  
  static public Connection getCnx (){
      return cnx;
  }
}
