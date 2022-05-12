package Services;

import Model.ProduitAcheter;
import utils.MyDb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceProduitAcheterProduit implements IServiceProduit<ProduitAcheter> {
        private Connection cnx = MyDb.getInstance().getCnx() ;
        private static ServiceProduitAcheterProduit instance;

        public static ServiceProduitAcheterProduit getInstance() {
            if (instance == null) {
                instance = new ServiceProduitAcheterProduit();
            }
            return instance;
        }

 
        @Override
        public void ajouter(ProduitAcheter t) {
            try {
                String querry= "INSERT INTO produit_Acheter(`nom` , `prix`, `description`, `qte`, `image_path`, `marque` , `category_id`) VALUES ('"+t.getNom()+
                        "','"+t.getPrix()+"','"+t.getDescription()+"','"+t.getQte()+"','"+t.getImage_path()+"','"+t.getMarque()+"','"+t.getCategory()+"')";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }


        }
        @Override
        public List<ProduitAcheter> afficher() {
            List<ProduitAcheter> produitsAcheter = new ArrayList();
            try {

                String querry ="SELECT `produit_acheter`.*,label FROM `produit_acheter`, `category` where category_id = `category`.`id`";
                Statement stm = cnx.createStatement();
                ResultSet rs= stm.executeQuery(querry);
                while (rs.next()){
                    ProduitAcheter p = new ProduitAcheter();

                    p.setId(rs.getInt(1));
                    p.setNom(rs.getString("nom"));
                    p.setDescription(rs.getString("description"));
                    p.setCategory(rs.getString("label"));
                    p.setMarque(rs.getString("marque"));
                    p.setPrix(rs.getFloat("prix"));
                    p.setImage_path(rs.getString("image_path"));
                    p.setQte(rs.getInt("qte"));
                    produitsAcheter.add(p);
                }



                return produitsAcheter;
            } catch (SQLException ex) {
            }
            return produitsAcheter;
        }

        @Override
        public void modifier(ProduitAcheter t) {
            try{
                String querry = "UPDATE `produit_acheter` SET id ='"+
                        t.getId()+"', nom = '"+t.getNom()+
                        "', prix = '"+t.getPrix()+"', description = '"+t.getDescription()+
                        "', qte = '"+t.getQte()+"', marque = '"+t.getMarque()+"', image_path = '"+t.getImage_path()+"', category_id = '"+t.getCategory()
                        +"' WHERE `id` = '"+t.getId()+"'";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

        public Map<String, Integer> produitCategorie(){
            Map<String, Integer> m= new HashMap<>();
            try {
                String querry ="SELECT COUNT(*) as value,label FROM `produit_acheter`,`category` WHERE category_id = `category`.`id` GROUP BY label";
                Statement stm = cnx.createStatement();
                ResultSet rs= stm.executeQuery(querry);

                while (rs.next()){
                    Integer value=rs.getInt("value");
                    String label = rs.getString("label");
                    m.put(label,value);
                }

                return m;
            } catch (SQLException ex) {
            }
            return m;
        }

        @Override
        public void supprimer(ProduitAcheter t) {
            try{
                String querry = "DELETE FROM `produit_acheter` WHERE `id` = '"+t.getId()+"'";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }
        }
        public List<ProduitAcheter> tri(String critera,String ad){
            List<ProduitAcheter> produitsAcheter = new ArrayList();
            try {

                String querry ="SELECT * FROM `produit_acheter` ORDER BY `"+critera+"` "+ad+"";
                System.out.println(querry);
                Statement stm = cnx.createStatement();
                ResultSet rs= stm.executeQuery(querry);
                while (rs.next()){
                    ProduitAcheter p = new ProduitAcheter();

                    p.setId(rs.getInt(1));
                    p.setNom(rs.getString("nom"));
                    p.setDescription(rs.getString("description"));
                    p.setCategory(rs.getString("category_id"));
                    p.setMarque(rs.getString("marque"));
                    p.setPrix(rs.getFloat("prix"));
                    p.setImage_path(rs.getString("image_path"));
                    p.setQte(rs.getInt("qte"));
                    produitsAcheter.add(p);
                }



                return produitsAcheter;
            } catch (SQLException ex) {
            }
            return produitsAcheter;
        }
        public List<ProduitAcheter> Rech(String critera,String value){
            List<ProduitAcheter> produitsAcheter = new ArrayList();
            try {

                String querry ="SELECT `produit_acheter`.*,label FROM `produit_acheter`, `category` where category_id = `category`.`id` and `"+critera+"` like '"+value+"%'";
                System.out.println(querry);
                Statement stm = cnx.createStatement();
                ResultSet rs= stm.executeQuery(querry);
                while (rs.next()){
                    ProduitAcheter p = new ProduitAcheter();

                    p.setId(rs.getInt(1));
                    p.setNom(rs.getString("nom"));
                    p.setDescription(rs.getString("description"));
                    p.setCategory(rs.getString("label"));
                    p.setMarque(rs.getString("marque"));
                    p.setPrix(rs.getFloat("prix"));
                    p.setImage_path(rs.getString("image_path"));
                    p.setQte(rs.getInt("qte"));
                    produitsAcheter.add(p);
                }



                return produitsAcheter;
            } catch (SQLException ex) {
            }
            return produitsAcheter;
        }

    }

