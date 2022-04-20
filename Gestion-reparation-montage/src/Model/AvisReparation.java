/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Arij Hajji
 */
public class AvisReparation {
  private int id,idreparation,iduser;
   private String Description,email,nom;

    public AvisReparation(int id, String Description, String email, String nom) {
        this.id = id;
        this.Description = Description;
        this.email = email;
        this.nom = nom;
    }

    public AvisReparation(int idreparation, int iduser, String Description, String email, String nom) {
        this.idreparation = idreparation;
        this.iduser = iduser;
        this.Description = Description;
        this.email = email;
        this.nom = nom;
    }

    public AvisReparation(int i, AvisReparation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIduser() {
        return iduser;
    }

    public AvisReparation(int id, int idreparation, int iduser, String Description, String email, String nom) {
        this.id = id;
        this.idreparation = idreparation;
        this.iduser = iduser;
        this.Description = Description;
        this.email = email;
        this.nom = nom;
    }
  public AvisReparation( int idreparation, String Description, String email, String nom,int iduser) {
     
      this.idreparation=idreparation;
        this.Description = Description;
        this.email = email;
        this.nom = nom;
          this.iduser = iduser;
    }
    public int getIdreparation() {
        return idreparation;
    }

    public void setIdreparation(int idreparation) {
        this.idreparation = idreparation;
    }

    public AvisReparation(String Description) {
        this.Description = Description;
    }

    public AvisReparation() {
    }

    public AvisReparation(int idreparation, String Description) {
        this.idreparation = idreparation;
        this.Description = Description;
    }
    public AvisReparation(int idreparation, String Description, int iduser) {
        this.idreparation = idreparation;
        this.Description = Description;
        this.iduser=iduser;
    }
    


   
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "AvisReparation{" + "Description=" + Description + ", email=" + email + ", nom=" + nom + '}';
    }

  

    
}
