/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Model.Commande;

/**
 *
 * @author HP
 */
public class Facture {
int id,commande_id,remise,total;
String dateF;

    public Facture(int id, int commande_id, int remise, int total, String dateF) {
        this.id = id;
        this.commande_id = commande_id;
        this.remise = remise;
        this.total = total;
        this.dateF = dateF;
    }

    public Facture(int commande_id, int remise, int total, String dateF) {
        this.commande_id = commande_id;
        this.remise = remise;
        this.total = total;
        this.dateF = dateF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public int getRemise() {
        return remise;
    }

    public void setRemise(int remise) {
        this.remise = remise;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public Facture() {
    }
 @Override
    public String toString() {
        return "Facture{" + "id=" + id +  ", commande_id=" + commande_id + ", date_fact=" + dateF +", remise=" + remise + ", total=" + total +  "\n";
    }

    
}
