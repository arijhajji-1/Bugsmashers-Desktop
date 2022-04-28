package Services;

import Model.ProduitAcheter;
import Model.ProduitLouer;
import utils.MyDb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceProduitLouer implements IService<ProduitLouer>{
    private Connection cnx = MyDb.getInstance().getCnx() ;
    private static ServiceProduitLouer  instance;

    public static ServiceProduitLouer getInstance() {
        if (instance == null) {
            instance = new ServiceProduitLouer ();
        }
        return instance;
    }

    @Override
    public void ajouter(ProduitLouer t) {
        int dispo=0;
        try {
            if(t.isDispo()){
                dispo = 1;
            }else{
                dispo = 0;
            }
            String querry= "INSERT INTO produit_Louer(`nom` , `prix`, `description`, `etat`, `image_path`, `marque`, `dispo`, `category_id`) VALUES ('"+t.getNom()+
                    "','"+t.getPrix()+"','"+t.getDescription()+"','"+t.getEtat()+"','"+t.getImage_path()+"','"+t.getMarque()+"','"+dispo+"','"+t.getCategory()+"')";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


    }

    @Override
    public List<ProduitLouer> afficher() {
        List<ProduitLouer> produitsLouer = new ArrayList();
        try {

            String querry ="SELECT `produit_louer`.*,label FROM `produit_louer`, `category` where category_id = `category`.`id`";
            Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            while (rs.next()){
                ProduitLouer p = new ProduitLouer();

                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setCategory(rs.getString("label"));
                p.setMarque(rs.getString("marque"));
                p.setPrix(rs.getFloat("prix"));
                p.setImage_path(rs.getString("image_path"));
                p.setDispo(rs.getBoolean("dispo"));
                p.setEtat(rs.getString("etat"));
                produitsLouer.add(p);
            }



            return produitsLouer;
        } catch (SQLException ex) {
        }
        return produitsLouer;
    }

    @Override
    public void modifier(ProduitLouer t) {
        int dispo = 0;
        try{
            if(t.isDispo()){
                dispo = 1;
            }else{
                dispo = 0;
            }
            String querry = "UPDATE `produit_louer` SET id ='"+
                    t.getId()+"', nom = '"+t.getNom()+
                    "', prix = '"+t.getPrix()+"', description = '"+t.getDescription()+
                    "', etat = '"+t.getEtat()+"', marque = '"+t.getMarque()+"', image_path = '"+t.getImage_path()+"', category_id = '"+t.getCategory()+
                    "', dispo = '"+dispo+"'WHERE `id` = '"+t.getId()+"'";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(ProduitLouer t) {
        try{
            String querry = "DELETE FROM `produit_louer` WHERE `id` = '"+t.getId()+"'";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    public List<ProduitLouer> Rech(String critera, String value){
        List<ProduitLouer> produitsLouer = new ArrayList();
        try {

            String querry ="SELECT `produit_louer`.*,label FROM `produit_louer`, `category` where category_id = `category`.`id` and `"+critera+"` like '"+value+"%'";
            System.out.println(querry);
            Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            while (rs.next()){
                ProduitLouer p = new ProduitLouer();

                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setCategory(rs.getString("label"));
                p.setMarque(rs.getString("marque"));
                p.setPrix(rs.getFloat("prix"));
                p.setImage_path(rs.getString("image_path"));
                p.setEtat(rs.getString("etat"));
                p.setDispo(rs.getBoolean("dispo"));
                produitsLouer.add(p);
            }



            return produitsLouer;
        } catch (SQLException ex) {
        }
        return produitsLouer;
    }

}
