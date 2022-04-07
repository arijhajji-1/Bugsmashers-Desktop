package main;

import Model.Category;
import Model.ProduitAcheter;
import Model.ProduitLouer;
import Services.ServiceCategory;
import Services.ServiceProduitAcheter;
import Services.ServiceProduitLouer;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ProduitAcheter p1 = new ProduitAcheter(35,20, 3.5F,
               "test","test","ertyuifghj","testmarque","11");


        ServiceProduitAcheter sp =  new ServiceProduitAcheter();
        sp.supprimer(p1);
        //sp.modifier(p1);
        System.out.println(sp.afficher().toString());
        System.out.println("***************************************\n Partie Categorie");

        ServiceCategory sc = new ServiceCategory();
        Category c1 = new Category(17,"Carte Graphique");
        //sc.supprimer(c1);
        //sc.ajouter(c1);
        //sc.modifier(c1);

        System.out.println(sc.afficher().toString());
        System.out.println("***************************************\n Partie ProduitLouer");
        ServiceProduitLouer spl = new ServiceProduitLouer();
        ProduitLouer pl1 = new ProduitLouer(26,23F,true,"Pc MSI TEST",
                "Neuf","Pc desc","imagepathtest","pc marque test","17");
        //spl.ajouter(pl1);
        //spl.supprimer(pl1);
        //spl.modifier(pl1);
        System.out.println(spl.afficher().toString());
    }

}
