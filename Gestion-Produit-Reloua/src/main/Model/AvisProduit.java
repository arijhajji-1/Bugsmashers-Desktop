package Model;

public class AvisProduit {
    int id,produitAcheterid,produitLouerid,rating;
    String nom;
    String email;
    String description;

    public AvisProduit() {
    }

    public AvisProduit(int id, int produitAcheterid, int produitLouerid, int rating, String nom, String email, String description) {
        this.id = id;
        this.produitAcheterid = produitAcheterid;
        this.produitLouerid = produitLouerid;
        this.rating = rating;
        this.nom = nom;
        this.email = email;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduitAcheterid() {
        return produitAcheterid;
    }

    public void setProduitAcheterid(int produitAcheterid) {
        this.produitAcheterid = produitAcheterid;
    }

    public int getProduitLouerid() {
        return produitLouerid;
    }

    public void setProduitLouerid(int produitLouerid) {
        this.produitLouerid = produitLouerid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AvisProduit{" +
                "id=" + id +
                ", produitAcheterid=" + produitAcheterid +
                ", produitLouerid=" + produitLouerid +
                ", rating=" + rating +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                "}\n";
    }
}
