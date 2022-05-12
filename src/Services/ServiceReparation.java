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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import utils.MyDb;


/**
 *
 * @author Arij Hajji
 */
public class ServiceReparation implements ReparationCrud<Reparation>{
  private static ServiceReparation  instance;
    private final Connection cnx;

    public static ServiceReparation  getInstance() {
        if (instance == null) {
            instance = new ServiceReparation ();
        }
        return instance;

    }

    public ServiceReparation () {
        cnx = MyDb.getInstance().getCnx();
    }
    @Override
    public void ajouter(Reparation t) {
    try {
        String querry= "INSERT INTO reparation(`category`,`type`,`description`, `reserver`,`telephone`,`email`,`iduser`) VALUES"
                + " ('"+t.getCategory()+"','"+t.getType()+"','"+t.getDescription()+"','"+t.getReserver()+"','"+t.getTelephone()+"','"+t.getEmail()+"','"+t.getIduser()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
             
   
        
    }
 @Override
    public ObservableList<Reparation> getReparation() {
         ObservableList<Reparation> listReparation = FXCollections.observableArrayList();

        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM reparation");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listReparation.add(new Reparation(
                        resultSet.getInt("id"),
                        resultSet.getString("category"),
                        resultSet.getString("type"),
                        resultSet.getString("Description"),
                        resultSet.getString("date")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage (tout) reparation : " + e.getMessage());
        }
        return listReparation;
    }

    @Override
    public ObservableList<Reparation> afficher() {
                 ObservableList<Reparation> listReparation = FXCollections.observableArrayList();

    // List<Reparation> personnes = new ArrayList();
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
        p.setEmail(rs.getString("email"));
        p.setEtat(rs.getString("etat"));
        p.setTelephone(rs.getString("telephone"));
        p.setIduser(rs.getInt("iduser"));
            listReparation.add(p);
        }
        
        
        
        return listReparation;
    } catch (SQLException ex) {
        }
    return listReparation;
    }

   
    public ObservableList<Reparation> afficher2() {
                 ObservableList<Reparation> listReparation = FXCollections.observableArrayList();

    // List<Reparation> personnes = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `reparation` where `iduser`= "+LoginSession.UID;
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Reparation p = new Reparation();
            
            p.setId(rs.getInt(1));
            p.setDescription(rs.getString("description"));
            p.setType(rs.getString(3));
            p.setCategory(rs.getString("category"));
            p.setReserver(rs.getString("Reserver"));
        p.setEmail(rs.getString("email"));
        p.setEtat(rs.getString("etat"));
        p.setTelephone(rs.getString("telephone"));
        p.setIduser(rs.getInt("iduser"));
            listReparation.add(p);
        }
        
        
        
        return listReparation;
    } catch (SQLException ex) {
        }
    return listReparation;
    }


    @Override
        public void modifier(Reparation t) {
            try{
                String querry = "UPDATE `reparation` SET category = '"+t.getCategory()+
                        "', type = '"+t.getType()+"', description = '"+t.getDescription()+
                        "', reserver = '"+t.getReserver()
                        +"' WHERE `id` = '"+t.getId()+"'";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
 @Override
        public void modifierEtat(Reparation t) {
            try{
                String querry = "UPDATE `reparation` SET etat = '"+t.getEtat()
                        +"' WHERE `id` = '"+t.getId()+"'";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    @Override
   public void supprimer(Reparation t) {
            try{
                String querry = "DELETE FROM Reparation WHERE `id` = '"+t.getId()+"'";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }
        }
   /* @Override
    public List<Reparation> recherche(Reparation s) {
     List<Reparation> reparation = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `reparation` where category like '%"+s+"%' or type  like '%"+s+"%' ";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Reparation p = new Reparation();
            
            p.setId(rs.getInt(1));
            p.setCategory(rs.getString("category"));
            p.setType(rs.getString(3));
            p.setDescription(rs.getString("description"));
            p.setReserver(rs.getString("date"));
           
            reparation.add(p);
        }
        
        
        
        return reparation;
    } catch (SQLException ex) {
        }
    return reparation;
    }*/
public List<Reparation> tristreamcategory() {

  return afficher().stream().sorted((p1,p2)->p1.getCategory().compareTo(p2.getCategory())).collect(Collectors.toList());

    }
    public List<Reparation> tristreamtype() {

  return afficher().stream().sorted((p1,p2)->p1.getType().compareTo(p2.getType())).collect(Collectors.toList());

    }
     public List<Reparation> tristreamdate() {

  return afficher().stream().sorted((p1,p2)->p1.getReserver().compareTo(p2.getReserver())).collect(Collectors.toList());

    }
     public List<Reparation> rechstream(Reparation x) {


     return afficher().stream().filter(p->p.getCategory().contains(x.getCategory())).collect(Collectors.toList());



    }
     public List<Reparation> recherche(String searched) {

        List<Reparation> lista = new ArrayList<>();
        try {

            String req = "select * from reparation WHERE category LIKE '%" + searched + "%' OR type LIKE '%" + searched + "%' OR description LIKE '%" + searched + "%';";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Reparation p = new Reparation();
            while (rs.next()) {

                   p.setId(rs.getInt(1));
            p.setDescription(rs.getString("description"));
            p.setType(rs.getString(3));
            p.setCategory(rs.getString("category"));
            p.setReserver(rs.getString("Reserver"));
        p.setEmail(rs.getString("email"));
        p.setEtat(rs.getString("etat"));
        p.setTelephone(rs.getString("telephone"));
        p.setIduser(rs.getInt("iduser"));

                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("RECHERCHE" + ex.getMessage());
        }
        return null;

    }
}
