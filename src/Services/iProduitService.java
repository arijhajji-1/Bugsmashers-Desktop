/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.ProduitAcheter;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public interface iProduitService <T>{
public Map IncrementerQte(Map panier,ProduitAcheter p);
public float calculerTotal(Map panier);
public Map AddToCart(Map panier,ProduitAcheter p);
public void AfficherPanier(Map panier);
public List RecupererPanier(Map panier);
public Map DecrementerQte(Map panier,ProduitAcheter p);
public void validerPanier(Map panier);
public String ProduitAchetes(Map panier);
}