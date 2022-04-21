package Services;

import Model.Category;
import Model.ProduitAcheter;
import utils.MyDb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategory implements IService<Category>{
    private Connection cnx = MyDb.getInstance().getCnx() ;
    private static ServiceCategory  instance;

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory ();
        }
        return instance;
    }

    @Override
    public void ajouter(Category t) {
        try {
            String querry= "INSERT INTO category(`label`) VALUES ('"+t.getLabel()+ "')";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


    }

    @Override
    public List<Category> afficher() {
        List<Category> Categories = new ArrayList();
        try {

            String querry ="SELECT * FROM `category`";
            Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            while (rs.next()){
                Category p = new Category();

                p.setId(rs.getInt(1));
                p.setLabel(rs.getString("label"));
                Categories.add(p);
            }



            return Categories;
        } catch (SQLException ex) {
        }
        return Categories;
    }

    @Override
    public void modifier(Category t) {
        try{
            String querry = "UPDATE `category` SET id ='"+
                    t.getId()+"', label = '"+t.getLabel()+"' WHERE `id` = '"+t.getId()+"'";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Category t) {
        try{
            String querry = "DELETE FROM `category` WHERE `id` = '"+t.getId()+"'";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public List<Category> Rech(String critera, String value){
        List<Category> categories = new ArrayList();
        try {

            String querry ="SELECT * FROM `category` WHERE `"+critera+"` like '"+value+"%'";
            Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            while (rs.next()) {
                Category p = new Category();

                p.setId(rs.getInt(1));
                p.setLabel(rs.getString("label"));
                categories.add(p);
            }

            return categories;
        } catch (SQLException ex) {
        }
        return categories;
    }

}

