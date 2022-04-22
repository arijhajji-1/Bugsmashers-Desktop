/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Gloria
 */
public interface IserviceReclamation <R> {
    
    public void  ajouter(R r);
    public List<R> afficher();
    public int  modifier (R r);
    public void supprimer (R r);
     public boolean chercher(int id) throws SQLException;
    
}
