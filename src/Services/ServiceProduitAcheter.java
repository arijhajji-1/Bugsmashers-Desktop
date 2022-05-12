/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Arij Hajji
 */


import Model.ProduitAcheter;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDb;

    public class ServiceProduitAcheter implements ProduitAcheterCrud<ProduitAcheter>{
            private static ServiceProduitAcheter  instance;
    private final Connection cnx;

   // private Connection cnx = Database.getInstance().getCnx() ;
 public static ServiceProduitAcheter  getInstance() {
        if (instance == null) {
            instance = new ServiceProduitAcheter ();
        }
        return instance;

    }

    public ServiceProduitAcheter () {
        cnx = MyDb.getInstance().getCnx();
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

                String querry ="SELECT * FROM `produit_acheter`";
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

        @Override
        public void modifier(ProduitAcheter t) {
            try{
                String querry = "UPDATE `produit_acheter` SET id ='"+
                        t.getId()+"', nom = '"+t.getNom()+
                        "', prix = '"+t.getPrix()+"', description = '"+t.getDescription()+
                        "', qte = '"+t.getQte()+"', marque = '"+t.getMarque()+"', image_path = '"+t.getImage_path()
                        +"' WHERE `id` = '"+t.getId()+"'";
                Statement stm = cnx.createStatement();

                stm.executeUpdate(querry);
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
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

    }