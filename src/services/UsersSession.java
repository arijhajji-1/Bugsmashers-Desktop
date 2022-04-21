/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Hsine
 */
public class UsersSession {
    public static UsersSession getInstance() {
        return instance;
    }

    private static int id;
    public static UsersSession instance;
    private static String email;
    private static String role;
    private static String name;
    private static String lastname;
    private static String password;
    private static String profilepicture;


    public static void setId(int id) {
        UsersSession.id = id;
    }

    public static int getId() {
        return id;
    }

    public static void setProfilepicture(String profilepicture) {
        UsersSession.profilepicture = profilepicture;
    }

    public static void setName(String name) {
        UsersSession.name = name;
    }

    public static void setLastname(String lastname) {
        UsersSession.lastname = lastname;
    }
    

    public static String getProfilepicture() {
        return profilepicture;
    }

    public static String getName() {
        return name;
    }

    public static String getlastname() {
        return lastname;
    }
    
    


    public static void setRole(String r) {
        role = r;
    }

    public static String getRole() {
        return role;
    }

    public static void setPassword(String p) {
        password = p;
    }

    public static String getPassword() {
        return password;
    }

    public static void setEmail(String email) {
        email = email;
    }

    public static String getEmail() {
        return email;
    }

    public UsersSession(int id, String role, String email, String name, String lastname, String profilepicture) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.name = name;
        this.lastname = lastname;
        this.profilepicture = profilepicture;
    }

    public static void setInstance(UsersSession instance) {
        UsersSession.instance = instance;
    }

    public static UsersSession getInstance(int id, String email, String role, String name, String lastname, String profilepicture) {
        if (instance == null) {
            instance = new UsersSession(id, email, role, name, lastname, profilepicture);
        }


        return instance;

    }

    public static void cleanUserSession() {
        id = 0;
        email = null;
        role = null;
        name = null;
        lastname = null;
        profilepicture = null;
        instance = null;
    }
    
    public static void  addUserLogin(ResultSet userRow) {
        try {
          String requete = "INSERT into userlogins(id_user) values(?) ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, userRow.getInt("id"));
            pst.execute();
            pst.close();
            System.out.println("Added user session!");
            
            id = userRow.getInt("id");
            email = userRow.getString("email");
            role = userRow.getString("role");
            name = userRow.getString("name");
            lastname = userRow.getString("lastname");
            password = userRow.getString("password");
            profilepicture = userRow.getString("profilepicture");

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            
        }

    }

    @Override
    public String toString() {
        return "Logged in as {" +
                "Email='" + email + '\'' +
                ", Role ='" + role + '\'' +
                ", Name ='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
