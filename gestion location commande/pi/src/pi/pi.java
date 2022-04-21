/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi;

import Model.Commande;
import Model.Location;
import Services.ServiceLocation;
import Services.ServiceCommande;
import utils.MyDb;

/**
 *
 * @author Mahdi
 */
public class pi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  Commande p1 = new Commande("aaeaa" ,"aeae","aeaea","80 rue de jasmin",22626444);
  Commande p2 = new Commande("mourad" ,"ali","Cash","80 rue de jasmin",22626879);
  

Location p3=new Location(300,"2022-04-17","2022-04-22");
  
 
  ServiceCommande sp =  new ServiceCommande();

 ServiceLocation ss= new ServiceLocation();
    p1.setId(58);
   sp.modifier(p1);
//ss.ajouter(p3);
    
System.out.println(sp.afficher().toString());
 
System.out.println("**********************************************************");
System.out.println(ss.afficher().toString());


        
    
    
    }
   
}