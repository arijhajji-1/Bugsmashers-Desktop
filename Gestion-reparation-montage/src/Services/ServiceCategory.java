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


import Model.Category;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Database;

public class ServiceCategory implements CategoryCrud<Category>{
      private static ServiceCategory  instance;
    private final Connection cnx;

   // private Connection cnx = Database.getInstance().getCnx() ;
 public static ServiceCategory  getInstance() {
        if (instance == null) {
            instance = new ServiceCategory ();
        }
        return instance;

    }

    public ServiceCategory () {
        cnx = Database.getInstance().getCnx();
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

   

}