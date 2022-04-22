/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Gloria
 */
public class Livraison {
    
    int id;
    String modpaie;
    String modlivr;
    String region;
    String description;
    String date;
    Commande commande;

    public Livraison(int id, String modpaie, String modlivr, String region, String description, String date, Commande commande) {
        this.id = id;
        this.modpaie = modpaie;
        this.modlivr = modlivr;
        this.region = region;
        this.description = description;
        this.date = date;
        this.commande = commande;
    }

    public Livraison(String modpaie, String modlivr, String region, String description, String date, Commande commande) {
        this.modpaie = modpaie;
        this.modlivr = modlivr;
        this.region = region;
        this.description = description;
        this.date = date;
        this.commande = commande;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  

 

    public Livraison() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModpaie() {
        return modpaie;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    

    public void setModpaie(String modpaie) {
        this.modpaie = modpaie;
    }

    public String getModlivr() {
        return modlivr;
    }

    public void setModlivr(String modlivr) {
        this.modlivr = modlivr;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", modpaie=" + modpaie + ", modlivr=" + modlivr + ", region=" + region + ", description=" + description + ", date=" + date + ", commande=" + commande + '}';
    }
    
    
    
    
}
