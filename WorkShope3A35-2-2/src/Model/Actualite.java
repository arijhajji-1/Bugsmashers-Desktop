package Model;

import java.util.Date;

public class Actualite {
    int id;
    String titre,description,imageName;
    String date;

    public Actualite(int id,String titre, String date, String description, String imageName) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.description = description;
        this.imageName = imageName;
    }

    public Actualite(String titre, String description, String imageName, String date) {
        this.titre = titre;
        this.description = description;
        this.imageName = imageName;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Actualite{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Actualite() {

    }
}
