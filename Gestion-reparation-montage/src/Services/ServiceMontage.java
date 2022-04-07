/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Montage;
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
public class ServiceMontage implements IService<Montage> {
    private Connection cnx = Database.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Montage t) {
    try {
        String querry= "INSERT INTO montage(`processeur`,`carte_graphique`,`carte_mere`, `disque_systeme`,`boitier`,`stockage_supp`,`montant`,`email`,`iduser`) VALUES"
                + " ('"+t.getProcesseur()+"','"+t.getCarte_graphique()+"','"+t.getCarte_mere()+"','"+t.getDisque_systeme()+"','"+t.getBoitier()+"','"+t.getStockage_supp()+"','"+t.getMontant()+"','"+t.getEmail()+"','"+t.getIduser()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<Montage> afficher() {
     List<Montage> personnes = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `montage`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Montage p = new Montage();
            
            p.setIdmontage(rs.getInt(1));
            p.setProcesseur(rs.getString("Processeur"));
            p.setCarte_graphique(rs.getString(3));
            p.setCarte_mere(rs.getString("carte_mere"));
            p.setDisque_systeme(rs.getString("disque_systeme"));
            p.setBoitier(rs.getString("Boitier"));
            p.setStockage_supp(rs.getString("stockage_supp"));
            p.setMontant(rs.getInt(1));
                        p.setEmail(rs.getString("email"));

            personnes.add(p);
        }
        
        
        
        return personnes;
    } catch (SQLException ex) {
        }
    return personnes;
    }

   

    @Override
    public void modifier(Montage t) {
       try {
            String req3 = "UPDATE Montage SET processeur= ? where id= ?" ;
   PreparedStatement stm = cnx.prepareStatement(req3);         
   stm.setString(1,t.getProcesseur());
             stm.setInt(2,t.getIdmontage());



            stm.executeUpdate() ;
             System.out.println("Your montage has been modified ");
         JOptionPane.showMessageDialog(null, "montage modified ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
         try {
            String requete = "DELETE FROM montage WHERE id = ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            int rsltUpdate = pst.executeUpdate();
            if (rsltUpdate == 0) {
                System.err.println("EXITE AUCUN ELEMENT CORRESPOND AU DONNEE SAISIE !! \n AUCUNE SUPPRESSION N'EST EFFECTUEE !!");
            } else {
                    System.out.println("montage DELETED SUCCESFULLY !!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
