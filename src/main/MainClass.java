package main;
import java.sql.Date;
import entities.User;
import services.UserServices;
public class MainClass{
    public static void main(String[] args) {
        try {

            //MyConnection mc = new MyConnection();
            UserServices cc = new UserServices();
            User u = new User(565,595,548,1,"lastname","adresse","photo","ddd","sdfdf7",new Date(20,12,20),"\t\n" +
                    "[\"ROLE_User\"]","fddf");
            cc.ajouteruser(u);

            System.out.println(cc.listerUsers());
            //cc.modifierUsers(1, "Mounir", "Mese3di", "chayma", "chayma", "chayma", "monji");
            //cc.supprimerUser(u,4);
            //cc.Login("aa", "vv");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*User e = new User(565,"fdfd","dsd","firstname","lastname","adresse","photo",5484,7,7,"password",4);
    
    UserServices s = new UserServices();

    s.ajouteruser(e);
 //Date D=new java.util.Date(20,5,7)

    //System.out.println(cc.listerUsers());
    //cc.modifierUsers(1, "Mounir", "Mese3di", "chayma", "chayma", "chayma", "monji");
   // cc.supprimerUser(u);*/
}