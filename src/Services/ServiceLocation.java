/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.Commande;
import Model.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import utils.MyDb;

/**
 *
 * @author Mohamed
 */
public class ServiceLocation implements IService<Location>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Location l) {
    try {
        String querry= "INSERT INTO location(`date_deb`, `date_fin`,`total_l`,`iduser`) VALUES ('"+l.getDb()+"','"+l.getDf()+"','"+l.getTotall()+"','"+l.getIduser()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<Location> afficher() {
     List<Location> locations = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `location`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Location l = new Location();
            
            l.setId(rs.getInt("id"));
            l.setDb(rs.getString("date_deb"));
            l.setDf(rs.getString("date_fin"));
            l.setTotall(rs.getInt("Total_l"));
            
            locations.add(l);
        }
        
        
        
        return locations;
    } catch (SQLException ex) {
        }
    return locations;
    }

    @Override
    public void modifier(Location Location) {

            String query = "UPDATE location SET date_deb=?, date_fin=?, total_l=? WHERE id=?;";
            try (PreparedStatement stm = cnx.prepareStatement(query)) {
                stm.setInt(4, Location.getId());
                stm.setString(1, Location.getDb());
                stm.setString(2, Location.getDf());
                stm.setInt(3, Location.getTotall());
                
                

                stm.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
            }
}

   @Override
    public void supprimer(Location Location) {
        String query = "DELETE FROM location WHERE id=?;";

        try(PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, Location.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
public List<Location> tristreamdatedeb() {

  return afficher().stream().sorted((p1,p2)->p1.getDb().compareTo(p2.getDb())).collect(Collectors.toList());

    }
    public List<Location> tristreamdateF() {

  return afficher().stream().sorted((p1,p2)->p1.getDf().compareTo(p2.getDf())).collect(Collectors.toList());

    }

   
    public List<Location> rechstream(Location x) {


     return afficher().stream().filter(p->p.getDb().contains(x.getDb())).collect(Collectors.toList());



    }
}
