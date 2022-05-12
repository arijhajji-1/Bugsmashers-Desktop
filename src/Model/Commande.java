/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author HP
 */
public class Commande {
private int id,Telephone,iduser;

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIduser() {
        return iduser;
    }
private String nom,prenom,paiment,adresse;

    public Commande(String nom, String prenom, String paiment, String adresse,int telephone,int iduser) {
        this.nom = nom;
        this.prenom = prenom;
        this.paiment = paiment;
        this.adresse = adresse;
        this.Telephone=telephone;
        this.iduser=iduser;
    }

    public Commande(int id, int Telephone, int iduser, String nom, String prenom, String paiment, String adresse) {
        this.id = id;
        this.Telephone = Telephone;
        this.iduser = iduser;
        this.nom = nom;
        this.prenom = prenom;
        this.paiment = paiment;
        this.adresse = adresse;
    }

    public Commande(int id, int Telephone, String nom, String prenom, String paiment, String adresse) {
        this.id = id;
        this.Telephone = Telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.paiment = paiment;
        this.adresse = adresse;
    }

    

   

    public Commande() {
    }

    

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPaiment() {
        return paiment;
    }

    public void setPaiment(String paiment) {
        this.paiment = paiment;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

public void setTelephone(int Telephone) {
        this.Telephone = Telephone;
    }

    public int getTelephone() {
        return Telephone;
    }
 

    public Commande(int id, String nom, String prenom, String paiment, String adresse,int telephone,int iduser) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.paiment = paiment;
        this.adresse = adresse;
        this.Telephone=telephone;
        this.iduser=iduser;
    }
@Override
    public String toString() {
        return "Commande{" + "id=" + id +  ", nom=" + nom + ", prenom=" + prenom +", paiment=" + paiment + ", adresse=" + adresse +  ", telephone=" + Telephone + ", iduser=" + iduser + "\n";
    }
    
}

