/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Reparation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.Database;


/**
 *
 * @author Arij Hajji
 */
public class ServiceReparation implements IService<Reparation>{
private Connection cnx = Database.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Reparation t) {
    try {
        String querry= "INSERT INTO reparation(`category`,`type`,`description`, `reserver`,`telephone`,`etat`,`email`,`iduser`) VALUES"
                + " ('"+t.getCategory()+"','"+t.getType()+"','"+t.getDescription()+"','"+t.getReserver()+"','"+t.getTelephone()+"','"+t.getEtat()+"','"+t.getEmail()+"','"+t.getIduser()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<Reparation> afficher() {
     List<Reparation> personnes = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `reparation`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Reparation p = new Reparation();
            
            p.setId(rs.getInt(1));
            p.setDescription(rs.getString("description"));
            p.setType(rs.getString(3));
            p.setCategory(rs.getString("category"));
            p.setReserver(rs.getString("Reserver"));
            p.setTelephone(rs.getString("Telephone"));
            p.setEtat(rs.getString("Etat"));
            personnes.add(p);
        }
        
        
        
        return personnes;
    } catch (SQLException ex) {
        }
    return personnes;
    }

   

    @Override
    public void modifier(Reparation t) {
       try {
            String req3 = "UPDATE Reparation SET description= ? where id= ?" ;
   PreparedStatement stm = cnx.prepareStatement(req3);         
   stm.setString(1,t.getDescription());
             stm.setInt(2,t.getId());



            stm.executeUpdate() ;
             System.out.println("Your reparation has been modified ");
         JOptionPane.showMessageDialog(null, "repartion modified ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
         try {
            String requete = "DELETE FROM reparation WHERE id = ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            int rsltUpdate = pst.executeUpdate();
            if (rsltUpdate == 0) {
                System.err.println("EXITE AUCUN ELEMENT CORRESPOND AU DONNEE SAISIE !! \n AUCUNE SUPPRESSION N'EST EFFECTUEE !!");
            } else {
                    System.out.println("REPARATION DELETED SUCCESFULLY !!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
