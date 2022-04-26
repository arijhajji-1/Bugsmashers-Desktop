package Services;

import Model.Actualite;

import java.util.List;

public interface IService <T>{
    public void ajouter(T t);
    public List<T> afficher();
    public void  modifier (T t);
    public void supprimer (T t);
    public void chercher(int x);
}