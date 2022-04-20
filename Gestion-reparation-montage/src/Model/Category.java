/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Arij Hajji
 */
public class Category {
    

    int id;
    String label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category() {
    }

    public Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Category(int id, String label) {
        this.id = id;
        this.label = label;
    }
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", label='" + label + '\'' +
                "}\n";
    }
}