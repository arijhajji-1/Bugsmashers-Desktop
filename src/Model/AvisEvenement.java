package Model;

public class AvisEvenement {
    int id,eventid;
    String nom, description, email;

    public AvisEvenement() {
    }

    public AvisEvenement(int id, int eventid, String nom, String description, String email) {
        this.id = id;
        this.eventid = eventid;
        this.nom = nom;
        this.description = description;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AvisEvenement{" +
                "id=" + id +
                ", eventid=" + eventid +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
