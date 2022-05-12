package Model;

public class ProduitLouer {
    int id;
    float prix;
    boolean dispo;
    String nom;
    String etat;
    String description;
    String image_path;
    String marque;
    String category;

    public ProduitLouer() {
    }

    public ProduitLouer(int id, float prix, boolean dispo, String nom, String etat, String description, String image_path, String marque, String category) {
        this.id = id;
        this.prix = prix;
        this.dispo = dispo;
        this.nom = nom;
        this.etat = etat;
        this.description = description;
        this.image_path = image_path;
        this.marque = marque;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProduitLouer{" +
                "id=" + id +
                ", prix=" + prix +
                ", dispo=" + dispo +
                ", nom='" + nom + '\'' +
                ", etat='" + etat + '\'' +
                ", description='" + description + '\'' +
                ", image_path='" + image_path + '\'' +
                ", marque='" + marque + '\'' +
                ", category='" + category + '\'' +
                "}\n";
    }
}
