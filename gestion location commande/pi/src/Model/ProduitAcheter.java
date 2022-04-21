package Model;

public class ProduitAcheter {
int id,qte;
float prix;
String nom;
    String description;
    String image_path;
    String marque;
    String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
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

    public ProduitAcheter() {
    }

    public ProduitAcheter(int id, int qte, float prix, String nom, String description, String image_path, String marque, String category) {
        this.id = id;
        this.qte = qte;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image_path = image_path;
        this.marque = marque;
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProduitAcheter{" +
                "id=" + id +
                ", qte=" + qte +
                ", prix=" + prix +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", image_path='" + image_path + '\'' +
                ", marque='" + marque + '\'' +
                ", category='" + category + '\'' +
                "}\n";
    }
}
