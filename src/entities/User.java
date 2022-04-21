package entities;
import java.sql.Date;
public class User {
    private int id, telephone, cin, status;
    private String firstName;
    private String lastName;
    private String adresse;
    private String email;
    private String password;
    private Date date_naissance;

    public User(int id, int telephone, int cin, int status, String firstName, String lastName, String adresse, String email, String password, Date date_naissance, String roles, String photo) {
        this.id = id;
        this.telephone = telephone;
        this.cin = cin;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.date_naissance = date_naissance;
        this.roles = roles;
        this.photo = photo;
    }

    private String roles;
    private String photo;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", telephone=" + telephone +
                ", cin=" + cin +
                ", status=" + status +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adresse='" + adresse + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", date_naissance=" + date_naissance +
                ", roles='" + roles + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
