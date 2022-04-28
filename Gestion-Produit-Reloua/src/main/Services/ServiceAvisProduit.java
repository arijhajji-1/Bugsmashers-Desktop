package Services;

import Model.AvisProduit;
import Model.Category;
import utils.MyDb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceAvisProduit implements IService<AvisProduit> {
    private Connection cnx = MyDb.getInstance().getCnx() ;
    private static ServiceAvisProduit  instance;

    public static ServiceAvisProduit getInstance() {
        if (instance == null) {
            instance = new ServiceAvisProduit ();
        }
        return instance;
    }
    @Override
    public void ajouter(AvisProduit avisProduit) {
        try {
            if(avisProduit.getProduitLouerid()==0) {
                String querry = "INSERT INTO avis_produit(`description`," +
                        " `nom`, `email`, `rating`, `produit_acheter_id`) VALUES " +
                        "('" + avisProduit.getDescription() + "','" + avisProduit.getNom() + "','" + avisProduit.getEmail() +
                        "','" + avisProduit.getRating() + "','" + avisProduit.getProduitAcheterid() +
                         "')";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);
            }else{
                String querry = "INSERT INTO avis_produit(`description`," +
                        " `nom`, `email`, `rating`, `produit_louer_id`) VALUES " +
                        "('" + avisProduit.getDescription() + "','" + avisProduit.getNom() + "','" + avisProduit.getEmail() +
                        "','" + avisProduit.getRating() + "','" +
                        avisProduit.getProduitLouerid() + "')";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    public List<AvisProduit> afficher(String critera, int id){
        List<AvisProduit> avisProduits = new ArrayList();
        try {

            String querry ="SELECT * FROM `avis_produit` WHERE "+critera+"='"+
                    id+"'";
            System.out.println(querry);
            Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            while (rs.next()){
                AvisProduit p = new AvisProduit();
                p.setId(rs.getInt(1));
                p.setDescription(rs.getString("description"));
                p.setNom(rs.getString("nom"));
                p.setEmail(rs.getString("email"));
                p.setRating(rs.getInt("rating"));
                p.setProduitAcheterid(rs.getInt("produit_acheter_id"));
                p.setProduitLouerid(rs.getInt("produit_louer_id"));
                avisProduits.add(p);
            }



            return avisProduits;
        } catch (SQLException ex) {
        }
        return avisProduits;
    }
    @Override
    public List<AvisProduit> afficher() {
        return null;
    }

    @Override
    public void modifier(AvisProduit avisProduit) {

    }

    @Override
    public void supprimer(AvisProduit avisProduit) {

    }
}
