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
public class Montage {
    private int idmontage,montant,iduser;
    private String processeur,carte_graphique,carte_mere,disque_systeme,boitier,stockage_supp,email;

    public Montage() {
    }

    public String getEmail() {
        return email;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIduser() {
        return iduser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Montage(int idmontage, int montant, String processeur, String carte_graphique, String carte_mere, String disque_systeme, String boitier, String stockage_supp, String email) {
        this.idmontage = idmontage;
        this.montant = montant;
        this.processeur = processeur;
        this.carte_graphique = carte_graphique;
        this.carte_mere = carte_mere;
        this.disque_systeme = disque_systeme;
        this.boitier = boitier;
        this.stockage_supp = stockage_supp;
        this.email = email;
    }

    public Montage(int idmontage, String processeur, String carte_graphique, String carte_mere, String disque_systeme, String boitier, String stockage_supp) {
        this.idmontage = idmontage;
        this.processeur = processeur;
        this.carte_graphique = carte_graphique;
        this.carte_mere = carte_mere;
        this.disque_systeme = disque_systeme;
        this.boitier = boitier;
        this.stockage_supp = stockage_supp;
    }

    

    public Montage( String processeur, String carte_graphique, String carte_mere, String disque_systeme, String boitier, String stockage_supp, int montant,String email,int iduser) {
       
        this.processeur = processeur;
        this.carte_graphique = carte_graphique;
        this.carte_mere = carte_mere;
        this.disque_systeme = disque_systeme;
        this.boitier = boitier;
        this.stockage_supp = stockage_supp;
        this.montant = montant;
                this.email = email;
                this.iduser = iduser;

    }
    public Montage(int idmontage, String processeur, String carte_graphique, String carte_mere, String disque_systeme, String boitier, String stockage_supp, int montant,String email,int iduser) {
       this.idmontage=idmontage;
        this.processeur = processeur;
        this.carte_graphique = carte_graphique;
        this.carte_mere = carte_mere;
        this.disque_systeme = disque_systeme;
        this.boitier = boitier;
        this.stockage_supp = stockage_supp;
        this.montant = montant;
                this.email = email;
                this.iduser = iduser;

    }
    public Montage(int idmontage, String processeur) {
        this.idmontage = idmontage;
        this.processeur = processeur;
    }

    public Montage(String processeur, String carte_graphique, String carte_mere, String disque_systeme, String boitier, String stockage_supp) {
        this.processeur = processeur;
        this.carte_graphique = carte_graphique;
        this.carte_mere = carte_mere;
        this.disque_systeme = disque_systeme;
        this.boitier = boitier;
        this.stockage_supp = stockage_supp;
    }

    
    public int getIdmontage() {
        return idmontage;
    }

    public void setIdmontage(int idmontage) {
        this.idmontage = idmontage;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getProcesseur() {
        return processeur;
    }

    public void setProcesseur(String processeur) {
        this.processeur = processeur;
    }

    public String getCarte_graphique() {
        return carte_graphique;
    }

    public void setCarte_graphique(String carte_graphique) {
        this.carte_graphique = carte_graphique;
    }

    public String getCarte_mere() {
        return carte_mere;
    }

   public void setCarte_mere(String carte_mere) {
        this.carte_mere = carte_mere;
    }

    public String getDisque_systeme() {
        return disque_systeme;
    }

    public void setDisque_systeme(String disque_systeme) {
        this.disque_systeme = disque_systeme;
    }

    public String getBoitier() {
        return boitier;
    }

    public void setBoitier(String boitier) {
        this.boitier = boitier;
    }

    public String getStockage_supp() {
        return stockage_supp;
    }

    public void setStockage_supp(String stockage_supp) {
        this.stockage_supp = stockage_supp;
    }

  
    @Override
    public String toString() {
        return "montage{" + "processeur=" + processeur + ", carte_graphique=" + carte_graphique + ", carte_mere=" + carte_mere + ", disque_systeme=" + disque_systeme + ", boitier=" + boitier + ", stockage_supp=" + stockage_supp +'}';
    }

   
    
}
