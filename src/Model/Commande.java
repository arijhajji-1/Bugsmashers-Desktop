/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Gloria
 */
public class Commande {
    
      int id;
    String adresse;
    String paiment;
    String prenom;
    String photo;

    public Commande(int id,String prenom,  String paiment,String adresse,  String photo) {
        this.id = id;
        this.adresse = adresse;
        this.paiment = paiment;
        this.prenom = prenom;
        this.photo = photo;
    }

    public Commande( String prenom, String paiment,String adresse, String photo) {
        this.adresse = adresse;
        this.paiment = paiment;
        this.prenom = prenom;
        this.photo = photo;
    }

    public Commande() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPaiment() {
        return paiment;
    }

    public void setPaiment(String paiment) {
        this.paiment = paiment;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", adresse=" + adresse + ", paiment=" + paiment + ", prenom=" + prenom + ", photo=" + photo + '}';
    }

   
    
    
}
