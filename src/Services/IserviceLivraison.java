/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Gloria
 */
public interface IserviceLivraison <L> {
    
     public void  ajouter(L l);
    public List<L> afficher();
    public int  modifier (L l);
    public void supprimer (L l);
     public boolean chercher(int id) throws SQLException;
    
}
