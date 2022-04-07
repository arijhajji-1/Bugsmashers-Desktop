
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDb {
    final  String URL ="jdbc:mysql://127.0.0.1:3306/Reloua";
    final  String LOGIN ="root";
    final  String PASSWORD ="";
    static   private Connection cnx;
    static  private MyDb instance ;

    private MyDb(){


        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connexion reussie ......");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    static public MyDb getInstance(){
        if(instance==null)
            instance= new MyDb();

        return instance;//null
    }

    static public Connection getCnx (){
        return cnx;
    }

}
