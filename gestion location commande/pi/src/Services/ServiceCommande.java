/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDb;

/**
 *
 * @author Mohamed
 */
public class ServiceCommande implements IService<Commande>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   private static ServiceCommande  instance;
    

    public static ServiceCommande  getInstance() {
        if (instance == null) {
            instance = new ServiceCommande ();
        }
        return instance;

    }
    @Override
    public void ajouter(Commande c) {
    try {
        String querry= "INSERT INTO commande(`nom`, `prenom`,`paiment`,`adresse`,`telephone`) VALUES ('"+c.getNom()+"','"+c.getPrenom()+"','"+c.getPaiment()+"','"+c.getAdresse()+"','"+c.getTelephone()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<Commande> afficher() {
     List<Commande> commandes = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `commande`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Commande c = new Commande();
            
            c.setId(rs.getInt("id"));
            c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenom"));
            c.setPaiment(rs.getString("paiment"));
            c.setAdresse(rs.getString("adresse"));
            c.setTelephone(rs.getInt("telephone"));
            commandes.add(c);
        }
        
        
        
        return commandes;
    } catch (SQLException ex) {
        }
    return commandes;
    }

    @Override
    public void modifier(Commande Commande) {

            String query = "UPDATE commande SET nom=?, prenom=?, paiment=?, adresse=?, telephone=? WHERE id=?;";
            try (PreparedStatement stm = cnx.prepareStatement(query)) {
                stm.setInt(6, Commande.getId());
                stm.setString(1, Commande.getNom());
                stm.setString(2, Commande.getPrenom());
                stm.setString(3, Commande.getPaiment());
                stm.setString(4, Commande.getAdresse());
                stm.setInt(5, Commande.getTelephone());
                

                stm.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
            }
}

   @Override
    public void supprimer(Commande Commande) {
        String query = "DELETE FROM commande WHERE id=?;";

        try(PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, Commande.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
}
