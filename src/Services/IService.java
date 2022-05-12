/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.util.List;

/**
 *
 * @author Mohamed
 */
public interface IService <T> {
    
    public void  ajouter(T c);
    public List<T> afficher();
    public void  modifier (T c);
    public void supprimer (T c);
}