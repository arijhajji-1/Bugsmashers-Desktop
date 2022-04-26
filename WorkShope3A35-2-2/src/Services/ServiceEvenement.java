package Services;


import Model.Actualite;
import Model.Evenement;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEvenement implements IService<Evenement>{
    private Connection cnx = MyDb.getInstance().getCnx() ;

    @Override
    public void ajouter(Evenement evenement) {
        try {
            String querry= "INSERT INTO evenement(`nom`,`date`,`heure`,`description`,`image_name`) VALUES ('"+evenement.getNom()+ "','"+evenement.getDate()+ "','"+evenement.getHeure()+ "','"+evenement.getDescription()+ "','"+evenement.getImageName()+ "')";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);
            System.out.println("ajouté avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public List<Evenement> afficher() {
        List<Evenement> Evenements = new ArrayList();
        try {

            String querry ="SELECT * FROM `evenement`";
            Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            while (rs.next()){
                Evenement p = new Evenement();

                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setDate(rs.getString("date"));
                p.setHeure(rs.getString("heure"));
                p.setDescription(rs.getString("Description"));
                p.setImageName(rs.getString("image_name"));


                Evenements.add(p);
            }



            return Evenements;
        } catch (SQLException ex) {
        }
        return Evenements;
    }

    @Override
    public void modifier(Evenement evenement) {
        try{
            String querry = "UPDATE `evenement` SET id ='"+
                    evenement.getId()+"', nom = '"+evenement.getNom()+"',date = '"+evenement.getDate()+"',description = '"+evenement.getDescription()+"',image_name = '"+evenement.getImageName()+"',heure = '"+evenement.getHeure()+"' WHERE `id` = '"+evenement.getId()+"'";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Evenement evenement) {
        try{
            String querry = "DELETE FROM `evenement` WHERE `id` ='"+evenement.getId()+"'";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);
            System.out.println("delete success");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


    }

    @Override
    public void chercher(int x) {
        List<Evenement> evenements = new ArrayList();
        boolean test ;
        try {
            String querry="SELECT * FROM evenement ";
            Statement stm =cnx.createStatement();
            ResultSet rs=stm.executeQuery(querry);

            while (rs.next()){
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
                e.setNom(rs.getString("nom"));
                e.setDate(rs.getString("date"));
                e.setDescription(rs.getString("description"));
                e.setImageName(rs.getString("image_name"));


                evenements.add(e);
            }
            test =evenements.stream().anyMatch((p -> p.getId()==x ));
            if (test ==true ){
                for (int i = 0; i < evenements.size(); i++) {
                    if (evenements.get(i).getId()== x) {
                        System.out.println( evenements.get(i));

                    } }

            }
            else
            {
                System.out.println( "aucun evenemnt");
            }
        } catch (SQLException ex){} ;

    }

}
