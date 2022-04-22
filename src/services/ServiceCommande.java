/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import Model.Commande;
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

/**
 *
 * @author Gloria
 */
public class ServiceCommande implements IserviceCommande <Commande>{
    
     private Connection cnx = MyDb.getInstance().getCnx() ;
private PreparedStatement pre,stm;
private Statement ste;

    @Override
    public void ajouter(Commande c) {
        try {
        String querry= "INSERT INTO commande(`prenom`, `paiment`, `adresse`, `photo`) VALUES (?,?,?,?)";
     // Statement stm = cnx.createStatement();
         

         PreparedStatement stm = cnx.prepareStatement(querry);
               stm.setString(1, c.getPrenom());
             stm.setString(2, c.getPaiment());
             stm.setString(3, c.getAdresse());
             stm.setString(4,c.getPhoto() );
            
           
             stm.executeUpdate();
             System.out.println("Commande ajoutée");
                     
      
    
            stm.executeUpdate( );
    
    } catch (SQLException ex) {
    
     System.out.println("probleme");
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
            
            c.setId(rs.getInt(1));
            c.setPrenom(rs.getString(2));
            c.setPaiment(rs.getString(3));
             c.setAdresse(rs.getString(4));
             c.setPhoto(rs.getString(5));
            
            commandes.add(c);
              
        }
        
       
        return commandes;
   
    } catch (SQLException ex) {
        }
    return commandes;
        
    }
    

    @Override
    public int modifier(Commande c) {
     
        try {
        if(chercher(c.getId())){
            
            pre = cnx.prepareStatement("UPDATE commande SET prenom=? , paiment=?, adresse=? ,photo=? WHERE id = "+c.getId()+" ");
            try{
               pre.setString(1, c.getPrenom());
             pre.setString(2, c.getPaiment());
             pre.setString(3, c.getAdresse());
             pre.setString(4,c.getPhoto() );
            
          
                
                pre.executeUpdate();
                System.out.println("Commande modifiée");
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
    public void supprimer(Commande c) {
    
               String req="delete from commande where id=?";
       
        try {
            
            PreparedStatement stm;
            stm=cnx.prepareStatement(req);
            stm.setInt(1,c.getId() );
            int i=stm.executeUpdate();
            System.out.println(i+ " commande suprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         }
        
    

    @Override
    public boolean chercher(int id) throws SQLException {
        
         String req="select * from commande where id= "+id;
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

   
    
    
    
}
