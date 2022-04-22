/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Model.Commande;
import Model.Livraison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDb;

/**
 *
 * @author Gloria
 */
public class ServiceLivraison implements IserviceLivraison<Livraison> {

    private Connection cnx = MyDb.getInstance().getCnx();
    private PreparedStatement pre, stm;
    private Statement ste;
    private static ServiceLivraison  instance;
    
     public static ServiceLivraison  getInstance() {
        if (instance == null) {
            instance = new ServiceLivraison ();
        }
        return instance;

    }

    @Override
    public void ajouter(Livraison l) {
        try {
            String req = "INSERT INTO `livraison`(`modpaie`, `modlivr`, `region`, `description`, `date`, `commande_id`) VALUES (?,?,?,?,?,?)";
            // Statement stm = cnx.createStatement();

            PreparedStatement stm = cnx.prepareStatement(req);
            stm.setString(1, l.getModpaie());
            stm.setString(2, l.getModlivr());
            stm.setString(3, l.getRegion());
            stm.setString(4, l.getDescription());
            stm.setString(5, l.getDate());
            stm.setInt(6, l.getCommande().getId());

            stm.executeUpdate();

            System.out.println("Livraison ajoutée");
            

        } catch (SQLException ex) {

            System.out.println("probleme");
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public List<Livraison> afficher() {
        List<Livraison> livraisons = new ArrayList();
        try {

            String querry = "SELECT * FROM `livraison` ";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(querry);
            while (rs.next()) {
                Livraison l = new Livraison();

                l.setId(rs.getInt(1));
               l.setModpaie(rs.getString(2));
                l.setModlivr(rs.getString(3));
                l.setRegion(rs.getString(4));
                l.setDescription(rs.getString(5));
                l.setDate(rs.getString(6));
               // l.setCommande(rs.getCommande());

                livraisons.add(l);

            }

            return livraisons;

        } catch (SQLException ex) {
        }
        return livraisons;

    }

    @Override
    public int modifier(Livraison l) {

        try {
            if (chercher(l.getId())) {

                pre = cnx.prepareStatement("UPDATE livraison SET modpaie= ?, modlivr=?, region=?, description=?,date=? ,commande_id=? WHERE id = " + l.getId() + " ");
                try {

                    pre.setString(1, l.getModpaie());
                    pre.setString(2, l.getModlivr());
                    pre.setString(3, l.getRegion());
                    pre.setString(4, l.getDescription());
                    pre.setString(5, l.getDate());
                    pre.setInt(6, l.getCommande().getId());

                    pre.executeUpdate();
                     System.out.println("Livraison modifiée");
                } catch (SQLException m) {
                    System.out.println(m.getMessage());
                }
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    @Override
    public void supprimer(Livraison l) {

        String req = "DELETE FROM `livraison` WHERE id=?";

        try {

            PreparedStatement stm;
            stm = cnx.prepareStatement(req);
            stm.setInt(1, l.getId());
            int i = stm.executeUpdate();
            System.out.println(i + " livraison suprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean chercher(int id) throws SQLException {

        String req = "select * from livraison where id= " + id;
        List<Integer> list = new ArrayList<>();

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }

    
     public ObservableList<Livraison> tri(String value) {
        String req = "select * from livraison order by " + value;
Commande commande= new Commande();
        ObservableList<Livraison> list = FXCollections.observableArrayList();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
               Livraison l = new Livraison();

                l.setId(rs.getInt(1));
               l.setModpaie(rs.getString(2));
                l.setModlivr(rs.getString(3));
                l.setRegion(rs.getString(4));
                l.setDescription(rs.getString(5));
                l.setDate(rs.getString(6));
               // l.setCommande(rs.getCommande());

                list.add(l);


            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

     public ObservableList<Livraison> filterRegion(String value) {
        String req = "select * from livraison where region = '" + value + "'";

        ObservableList<Livraison> list = FXCollections.observableArrayList();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                  Livraison l = new Livraison();

                l.setId(rs.getInt(1));
               l.setModpaie(rs.getString(2));
                l.setModlivr(rs.getString(3));
                l.setRegion(rs.getString(4));
                l.setDescription(rs.getString(5));
                l.setDate(rs.getString(6));
               // l.setCommande(rs.getCommande());

                list.add(l);


            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

      public ObservableList<Livraison> filterDate(String value) {
        String req = "select * from livraison where date = '" + value + "'";

        ObservableList<Livraison> list = FXCollections.observableArrayList();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                  Livraison l = new Livraison();

                l.setId(rs.getInt(1));
               l.setModpaie(rs.getString(2));
                l.setModlivr(rs.getString(3));
                l.setRegion(rs.getString(4));
                l.setDescription(rs.getString(5));
                l.setDate(rs.getString(6));
               // l.setCommande(rs.getCommande());

                list.add(l);


            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

     public ObservableList<Livraison> filterDescription(String value) {
        String req = "select * from livraison where description = '" + value + "'";

        ObservableList<Livraison> list = FXCollections.observableArrayList();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                  Livraison l = new Livraison();

                l.setId(rs.getInt(1));
               l.setModpaie(rs.getString(2));
                l.setModlivr(rs.getString(3));
                l.setRegion(rs.getString(4));
                l.setDescription(rs.getString(5));
                l.setDate(rs.getString(6));
               // l.setCommande(rs.getCommande());

                list.add(l);


            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

      public ObservableList<Livraison> filterPaiement(String value) {
        String req = "select * from livraison where paiement = '" + value + "'";

        ObservableList<Livraison> list = FXCollections.observableArrayList();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                  Livraison l = new Livraison();

                l.setId(rs.getInt(1));
               l.setModpaie(rs.getString(2));
                l.setModlivr(rs.getString(3));
                l.setRegion(rs.getString(4));
                l.setDescription(rs.getString(5));
                l.setDate(rs.getString(6));
               // l.setCommande(rs.getCommande());

                list.add(l);


            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public int rowLivraison() throws SQLException{
        ObservableList<Livraison> liste = FXCollections.observableArrayList();
        String req = "SELECT * FROM livraison";
        ResultSet rs = ste.executeQuery(req);
        int i=0;
        
        try {
            cnx = MyDb.getInstance().getCnx();
            ste = cnx.createStatement();
            //ResultSet rs = ste.executeQuery(req);
           Livraison livr;
            while (rs.next()){
               i=i+1;
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (ste != null) {
        try {
            ste.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return i;
        
}


    
}
