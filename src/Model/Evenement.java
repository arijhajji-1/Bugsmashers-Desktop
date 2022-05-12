package Model;

import java.util.Date;

public class Evenement {
    int id;
    String nom,heure,description,ImageName;
    String date;

    public Evenement(int id, String nom, String date, String heure, String description, String imageName) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.heure = heure;
        this.description = description;
        ImageName = imageName;
    }

    public Evenement(String nom, String heure, String description, String imageName, String date) {
        this.nom = nom;
        this.heure = heure;
        this.description = description;
        ImageName = imageName;
        this.date = date;
    }

    public Evenement() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", description='" + description + '\'' +
                ", ImageName='" + ImageName + '\'' +
                '}';
    }
}
