package Services;

import Model.Actualite;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceActualiteActualite implements IServiceActualite<Actualite> {
    private  Connection cnx = MyDb.getInstance().getCnx();
     @Override
   public void ajouter(Actualite t) {
         try {
             String querry= "INSERT INTO actualite(`titre`,`date`,`description`,`image_name`) VALUES ('"+t.getTitre()+ "','"+t.getDate() + "','"+t.getDescription()+ "','"+t.getImageName()+ "')";
             System.out.println(querry);
             Statement stm = cnx.createStatement();

             stm.executeUpdate(querry);
     System.out.println("ajouté avec succés");
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());

         }


     }

    @Override
    public List<Actualite> afficher() {
        List<Actualite> Actualites = new ArrayList();
        try {

            String querry ="SELECT * FROM `actualite`";
            Statement stm =  cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            while (rs.next()){
                Actualite p = new Actualite();

                p.setId(rs.getInt(1));
                p.setTitre(rs.getString("titre"));
                p.setDate(rs.getString("date"));
                p.setDescription(rs.getString("Description"));
                p.setImageName(rs.getString("image_name"));


                Actualites.add(p);
            }



            return Actualites;
        } catch (SQLException ex) {
        }
        return Actualites;
    }

    @Override
    public void modifier(Actualite t) {
        try{
            String querry = "UPDATE `actualite` SET id ='"+
                    t.getId()+"', titre = '"+t.getTitre()+"',date = '"+t.getDate()+"',description = '"+t.getDescription()+"',image_name = '"+t.getImageName()+"' WHERE `id` = '"+t.getId()+"'";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Actualite t) {
        try{
            String querry = "DELETE FROM `actualite` WHERE `id` ='"+t.getId()+"'";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("delete success");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public void chercher(int x) {
        List<Actualite> actualites = new ArrayList();
        boolean test ;
        try {
            String querry="SELECT * FROM actualite ";
            Statement stm =cnx.createStatement();
            ResultSet rs=stm.executeQuery(querry);

            while (rs.next()){
                Actualite a = new Actualite();
                a.setId(rs.getInt(1));
                a.setTitre(rs.getString("Titre"));
                a.setDate(rs.getString("date"));
                a.setDescription(rs.getString("description"));
                a.setImageName(rs.getString("image_name"));


                actualites.add(a);
            }
            test =actualites.stream().anyMatch((p -> p.getId()==x ));
            if (test ==true ){
                for (int i = 0; i < actualites.size(); i++) {
                    if (actualites.get(i).getId()== x) {
                        System.out.println( actualites.get(i));

                    } }

            }
            else
            {
                System.out.println( "aucune actualite");
            }
        } catch (SQLException ex){} ;

    }

}