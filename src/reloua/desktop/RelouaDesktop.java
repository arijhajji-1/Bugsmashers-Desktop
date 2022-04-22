/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reloua.desktop;

import Model.Commande;
import Model.Livraison;
import Model.Reclamation;
import java.sql.SQLException;
import services.ServiceCommande;
import services.ServiceLivraison;
import services.ServiceReclamation;

/**
 *
 * @author Gloria
 */
public class RelouaDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
//         Reclamation r1 = new Reclamation(530,"location","reclamation","bonjour je voudrais faire une reclamation","2022-04-08");
 Reclamation r2 = new Reclamation(23,100,"montage","montage mal fait","bonjour vous avez mal réparé mon pc","2022-04-08");
//  Reclamation r3 = new Reclamation(540,"montage","mauvais montage","bonjour vous avez mal monté mon pc","2022-04-08");
 ServiceReclamation sr =  new ServiceReclamation();
//  
//  Commande c1=new Commande("michelle","domicile","jaffa","photo.png");
//   //Commande c2=new Commande("gloria","bureau","aouina","aouina.png");
    Commande c2=new Commande(2,"michelle","école","esprit","photo.png");
   //ServiceCommande sc =  new ServiceCommande();
//   
   Livraison l1= new Livraison(4,"cheque","ecole","Lac 2","souris","2022-04-14",c2);
   ServiceLivraison sl= new ServiceLivraison();
   
   
   //-----------------------------------test---------------------------
     sr.modifier(r2);
   /*sr.supprimer(r2);
    sr.chercher(8);*/
  /*sc.ajouter(c1);
 
  System.out.println(sc.afficher().toString());*/
  //sl.afficher().toString();
    //System.out.println("test");
 //sl.modifier(l1);
 //sc.chercher(0);
  
   System.out.println(sr.afficher().toString());

    }
    
}
