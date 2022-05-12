/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Gloria
 */
public class Reclamation {
    
    int id;
    String sujet;
    String description;
    String categorie;
   int idCommande;
    String date;

    public Reclamation(int id,int idCommande,  String categorie,String sujet, String description,  String date) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.categorie = categorie;
        this.idCommande = idCommande;
        this.date = date;
    }

    public Reclamation() {
    }

    public Reclamation(int idCommande, String categorie,String sujet, String description,  String date) {
        this.sujet = sujet;
        this.description = description;
        this.categorie = categorie;
        this.idCommande = idCommande;
        this.date = date;
    }

    
    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", sujet=" + sujet + ", description=" + description + ", categorie=" + categorie + ", idCommande=" + idCommande + ", date=" + date + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
