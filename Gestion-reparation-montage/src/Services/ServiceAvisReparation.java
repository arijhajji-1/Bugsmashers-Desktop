/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.AvisReparation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utils.Database;

/**
 *
 * @author Arij Hajji
 */
public class ServiceAvisReparation implements AvisReparationCrud<AvisReparation>{
  private static ServiceAvisReparation  instance;
    private final Connection cnx;

    public static ServiceAvisReparation  getInstance() {
        if (instance == null) {
            instance = new ServiceAvisReparation ();
        }
        return instance;

    }

    public ServiceAvisReparation () {
        cnx = Database.getInstance().getCnx();
    }
    @Override
    public void ajouter(AvisReparation t) {
        try {
        String querry= "INSERT INTO `avis_reparation` (`description`, `nom`, `email`, `idrep_id`, `iduser`) VALUES  "
                + " ('"+t.getDescription()+"','"+t.getNom()+"','"+t.getEmail()+"','"+t.getIdreparation()+"','"+t.getIduser()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
    }

    @Override
    public List<AvisReparation> afficher() {
        
        List<AvisReparation> personnes = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `avis_reparation` ";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            AvisReparation p = new AvisReparation();
            
            p.setId(rs.getInt(1));
                        p.setIdreparation(rs.getInt("idrep"));

            p.setDescription(rs.getString("description"));
            p.setNom(rs.getString("nom"));
            p.setEmail(rs.getString("email"));
          //  p.setIdreparation(rs.getInt(1));
           // p.setIduser(rs.getInt(1));
         
            personnes.add(p);
        }
        
        
        
        return personnes;
    } catch (SQLException ex) {
        }
    return personnes;
    }

    @Override
    public void modifier(AvisReparation t) {
         try {
            String req3 = "UPDATE `avis_reparation` SET description= ? where id= ?" ;
   PreparedStatement stm = cnx.prepareStatement(req3);         
   stm.setString(1,t.getDescription());
             stm.setInt(2,t.getId());



            stm.executeUpdate() ;
             System.out.println("Your Avisreparation has been modified ");
         JOptionPane.showMessageDialog(null, "Avisrepartion modified ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             JOptionPane.showMessageDialog(null, ex);
        }
    
    }
    

    @Override
    public void supprimer(int id) {
        try {
            String requete = "DELETE FROM `avis_reparation`  WHERE id = ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            int rsltUpdate = pst.executeUpdate();
            if (rsltUpdate == 0) {
                System.err.println("EXITE AUCUN ELEMENT CORRESPOND AU DONNEE SAISIE !! \n AUCUNE SUPPRESSION N'EST EFFECTUEE !!");
            } else {
                    System.out.println("Avis DELETED SUCCESFULLY !!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    
}
