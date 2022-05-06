/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.Commande;
import Model.Facture;
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
public class ServiceFacture implements IService<Facture>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Facture f) {
    try {
        String querry= "INSERT INTO facture(`Commande_id`, `date_fact`,`remise`,`total`) VALUES ('"+f.getCommande_id()+"','"+f.getDateF()+"','"+f.getRemise()+"','"+f.getTotal()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<Facture> afficher() {
     List<Facture> factures = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `facture`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Facture f = new Facture();
            
            f.setId(rs.getInt("id"));
            f.setCommande_id(rs.getInt("commande_id"));
            f.setDateF(rs.getString("date_fact"));
            f.setRemise(rs.getInt("remise"));
            f.setTotal(rs.getInt("total"));
            factures.add(f);
        }
        
        
        
        return factures;
    } catch (SQLException ex) {
        }
    return factures;
    }

    @Override
    public void modifier(Facture Facture) {

            String query = "UPDATE facture SET commande_id=?, date_fact=?, remise=?, total=? WHERE id=?;";
            try (PreparedStatement stm = cnx.prepareStatement(query)) {
                stm.setInt(5, Facture.getId());
                stm.setInt(1, Facture.getCommande_id());
                stm.setString(2, Facture.getDateF());
                stm.setInt(3, Facture.getRemise());
                stm.setInt(4, Facture.getTotal());
                
                

                stm.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
            }
}

   @Override
    public void supprimer(Facture Facture) {
        String query = "DELETE FROM facture WHERE id=?;";

        try(PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, Facture.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

public List<Facture> tristreamCommandeid() {

  return afficher().stream().sorted((p1,p2)->p1.getDateF().compareTo(p2.getDateF())).collect(Collectors.toList());

    }
    

   
    public List<Facture> rechstream(Facture x) {


     return afficher().stream().filter(p->p.getDateF().contains(x.getDateF())).collect(Collectors.toList());



    }
    
}
