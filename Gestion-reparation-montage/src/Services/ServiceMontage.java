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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import utils.Database;

/**
 *
 * @author Arij Hajji
 */
public class ServiceMontage implements MontageCrud<Montage> {
   private static ServiceMontage  instance;
    private final Connection cnx;

    public static ServiceMontage  getInstance() {
        if (instance == null) {
            instance = new ServiceMontage ();
        }
        return instance;

    }

    public ServiceMontage () {
        cnx = Database.getInstance().getCnx();
    }
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
    public ObservableList<Montage> afficher() {
                 ObservableList<Montage> listMontage = FXCollections.observableArrayList();

    // List<Reparation> personnes = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `montage`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Montage p = new Montage();
            
            p.setIdmontage(rs.getInt(1));
            p.setProcesseur(rs.getString("processeur"));
            p.setCarte_mere(rs.getString("carte_mere"));
            p.setCarte_graphique(rs.getString("carte_graphique"));
            p.setDisque_systeme(rs.getString("disque_systeme"));
                        p.setStockage_supp(rs.getString("stockage_supp"));
                                    p.setBoitier(rs.getString("boitier"));
                                                p.setMontant(rs.getInt("montant"));

 p.setEmail(rs.getString("email"));
  p.setIduser(rs.getInt("iduser"));

        
            listMontage.add(p);
        }
        
        
        
        return listMontage;
    } catch (SQLException ex) {
        }
    return listMontage;
    }
   

    @Override
    public void modifier(Montage t) {
       try {
           String querry = "UPDATE `montage` SET processeur = '"+t.getProcesseur()+
                        "', carte_mere = '"+t.getCarte_mere()+"', carte_graphique = '"+t.getCarte_graphique()+
                        "', disque_systeme = '"+t.getDisque_systeme()
                        +"', stockage_supp = '"+t.getStockage_supp() +"', boitier= '"+t.getBoitier()+"' WHERE `id` = '"+t.getIdmontage()+"'";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);
             System.out.println("Your montage has been modified ");
         JOptionPane.showMessageDialog(null, "montage modified ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             JOptionPane.showMessageDialog(null, ex);
        }
      

    }

  @Override
   public void supprimer(Montage t) {
            try{
                String querry = "DELETE FROM montage WHERE `id` = '"+t.getIdmontage()+"'";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }
        }
    
}
