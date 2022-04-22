/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Gloria
 */
public interface IserviceCommande <C> {
    
     public void  ajouter(C c);
    public List<C> afficher();
    public int  modifier (C c);
    public void supprimer (C c);
     public boolean chercher(int id) throws SQLException;
}
