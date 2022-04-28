package services;
import Model.User;
import utils.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class UserServices {
    private Connection cnx = Database.getInstance().getCnx();
    public static int cUserId;
    public static ResultSet cUserRow;
    public void ajouteruser(User u) {
        try {
            String requete = "INSERT into user(email,roles,first_name,last_name,adresse,photo,telephone,cin,date_naissance,password,status) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = Database.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1,u.getEmail());
            pst.setString(2,u.getRoles() );
            pst.setString(3,u.getFirstName());
            pst.setString(4,u.getLastName());
            pst.setString(5,u.getAdresse());
            pst.setString(6,u.getPhoto());
            pst.setString(7,u.getTelephone());
            pst.setInt(8,u.getCin());
            pst.setDate(9,u.getDate_naissance());
            pst.setString(10,u.getPassword());
            pst.setInt(11,u.getStatus());


            pst.executeUpdate();
            System.out.println("Client ajouteé avec succées !");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public static List<User> listerUsers() {
        List<User>mylist=new ArrayList();
        try {
            
            String requete="SELECT *FROM user";
            Statement st=Database.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            while(rs.next()){
            
            User per=new User();
            per.setId(rs.getInt(1));
            per.setTelephone(rs.getString("telephone"));
             per.setStatus(rs.getInt("status"));
             per.setCin(rs.getInt("cin"));
            per.setRoles(rs.getString("roles"));
            per.setPhoto(rs.getString("photo"));
            per.setPassword(rs.getString("password"));
            per.setLastName(rs.getString("last_name"));
            per.setFirstName(rs.getString("first_name"));
            per.setEmail(rs.getString("email"));
            per.setAdresse(rs.getString("adresse"));
            per.setDate_naissance(rs.getDate("date_naissance"));
              
            
            
      
            
            mylist.add(per);
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   return mylist;}
  public void modifierUser(User C){
        try {
            String requete =  "UPDATE user SET adresse = ?,email = ?,cin= ?,status=?, telephone = ?, first_name = ?, last_name = ?, password = ?, photo = ?, roles = ?,date_naissance = ? WHERE id=?";
           
            PreparedStatement pst= Database.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, C.getAdresse());
            pst.setString(2, C.getEmail());
            pst.setInt(3, C.getCin());
            pst.setInt(4, C.getStatus());
            pst.setString(5, C.getTelephone());
            pst.setString(6, C.getFirstName());
            pst.setString(7, C.getLastName());
            pst.setString(8, C.getPassword());
            pst.setString(9, C.getPhoto());
            pst.setString(10, C.getRoles());
            pst.setDate(11, C.getDate_naissance());
            
            
       
            pst.setInt(12, C.getId());
            

            pst.executeUpdate();
            System.out.println("user modifiée!");                        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());            
        }
    }


    public static void supprimerUser(User u){
        try {
            String requete="DELETE from user where id=?";
            PreparedStatement pst = Database.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,u.getId());
            pst.executeUpdate();
            System.err.println("user supprimé avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public boolean login(String email, String password){

        try {

            String querry ="SELECT * FROM `user` where email ='"+email+"' and password ='"+password+"'";
            Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);

            if(!rs.isBeforeFirst()){
                System.out.println("user not found !!!!");
                return false;
            }
            else{
                System.out.println("user is logged");
                while(rs.next()){
                    LoginSession.UID=rs.getInt("id");
                    LoginSession.Roles=rs.getString("roles");
                    LoginSession.firstName=rs.getString("first_name");
                    LoginSession.email=rs.getString("email");
                    LoginSession.password=rs.getString("password");
                    LoginSession.Telephone=rs.getString("Telephone");
                    LoginSession.IsLogged=true;
                }
                System.out.println(LoginSession.firstName+" is connected");
                return true;
            }
        } catch (SQLException ex) {
            //System.out.println(ex);
        }
        return false;
    }
}
