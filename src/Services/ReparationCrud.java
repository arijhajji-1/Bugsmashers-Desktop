/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;


/**
 *
 * @author Arij Hajji
 */
public interface ReparationCrud <T> {
    
    public void  ajouter(T t);
    public List<T> afficher();
   public List<T> getReparation();
    public void  modifier (T t);
    public void  modifierEtat (T t);
    public void supprimer (T t);
    /*
    ....
TO DO  
tri 
recherche 
.... */
}


