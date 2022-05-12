package Services;

import Model.AvisEvenement;
import utils.MyDb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceAvisEvenementActualite implements IServiceActualite<AvisEvenement> {
    private Connection cnx = MyDb.getInstance().getCnx() ;
    @Override
    public void ajouter(AvisEvenement avisEvenement) {
        try {
            String querry= "INSERT INTO avis(`nom`,`description`,`email`,`evenement_id`) VALUES ('"+avisEvenement.getNom()+ "','"+avisEvenement.getDescription()+ "','"+avisEvenement.getEmail()+ "','"+avisEvenement.getEventid()+ "')";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);
            System.out.println("ajouté avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public List<AvisEvenement> afficher() {
        return null;
    }

    public List<AvisEvenement> afficher(int id) {
        List<AvisEvenement> avis = new ArrayList();
        try {

            String querry ="SELECT * FROM `avis` WHERE evenement_id = "+id;
            Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            while (rs.next()){
                AvisEvenement p = new AvisEvenement();

                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setEmail(rs.getString("email"));
                p.setEventid(rs.getInt("evenement_id"));


                avis.add(p);
            }



            return avis;
        } catch (SQLException ex) {
        }
        return avis;
    }

    @Override
    public void modifier(AvisEvenement avisEvenement) {

    }

    @Override
    public void supprimer(AvisEvenement avisEvenement) {

    }

    @Override
    public void chercher(int x) {

    }
}
