/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Model.Commande;
import Model.Livraison;
import Model.Reclamation;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Gloria
 */
public class ServiceReclamation implements IserviceReclamation <Reclamation>{
    
    private Connection cnx = MyDb.getInstance().getCnx() ;
private PreparedStatement pre,stm;
private Statement ste;

private static ServiceReclamation  instance;
    //private final Connection conx;


 public static ServiceReclamation  getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation ();
        }
        return instance;

    }

   /* public ServiceReclamation () {
        cnx = Mydb.getInstance().getCnx();
    }*/
    
    @Override
    public void ajouter(Reclamation r) {
        try {
        String querry= "INSERT INTO reclamation(`description`, `categorie`, `date`, `id_commande`,`sujet`) VALUES (?,?,?,?,?)";
     // Statement stm = cnx.createStatement();
         

         PreparedStatement stm = cnx.prepareStatement(querry);
             stm.setString(1, r.getDescription());
             stm.setString(2, r.getCategorie());
             stm.setString(3, r.getDate());
             stm.setInt(4,r.getIdCommande() );
             stm.setString(5,r.getSujet());
            
           
             stm.executeUpdate();
             System.out.println("Reclamation ajoutée");
                     
      
    
    } catch (SQLException ex) {
    
     System.out.println("probleme");
        System.out.println(ex.getMessage());
    
    }
    }

    @Override
    public List<Reclamation> afficher() {
        
         List<Reclamation> reclamations = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `reclamation`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Reclamation r = new Reclamation();
            
            r.setId(rs.getInt(1));
            r.setIdCommande(rs.getInt(5));
            r.setCategorie(rs.getString(3));
             r.setSujet(rs.getString(6));
             r.setDescription(rs.getString(2));
             r.setDate(rs.getString(4));
            reclamations.add(r);
              
        }
        
       
        return reclamations;
   
    } catch (SQLException ex) {
        }
    return reclamations;
        
    }
    

    @Override
    public int modifier(Reclamation r) {
     
        try {
        if(chercher(r.getId())){
            
            pre = cnx.prepareStatement("UPDATE reclamation SET description = ? ,categorie =?, date = ?, id_commande= ? , sujet= ? WHERE id = "+r.getId()+" ");
            try{
                //pre.setInt(6, r.getId());
               pre.setString(1, r.getDescription());
             pre.setString(2, r.getCategorie());
             pre.setString(3, r.getDate());
             pre.setInt(4,r.getIdCommande() );
             pre.setString(5,r.getSujet());
                
                
                pre.executeUpdate();
            }
            catch (SQLException m){
                System.out.println(m.getMessage());
            }
            return 1;
        }
    }
        catch (SQLException ex) {
        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
    }
        
   
        return 0;
    }

    @Override
    public void supprimer(Reclamation r) {
    
               String req="delete from reclamation where id=?";
       
        try {
            
            PreparedStatement stm;
            stm=cnx.prepareStatement(req);
            stm.setInt(1,r.getId() );
            int i=stm.executeUpdate();
            System.out.println(i+ " reclamation suprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         }
        
    

    @Override
    public boolean chercher(int id) throws SQLException {
        
         String req="select * from reclamation where id= "+id;
        List<Integer> list = new ArrayList<>();
        
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }
    
    
    
    public ObservableList<Reclamation> rechercheSujet(String value) {
        String req = "select * from reclamation where date = '" + value + "'";
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
            	  Reclamation r = new Reclamation();
                  
                  r.setId(rs.getInt(1));
                  r.setIdCommande(rs.getInt(5));
                  r.setCategorie(rs.getString(3));
                   r.setSujet(rs.getString(6));
                   r.setDescription(rs.getString(2));
                   r.setDate(rs.getString(4));
                  
                list.add(r);


            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
}
