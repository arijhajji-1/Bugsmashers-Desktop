/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Location {
    private int id,totall;
private String db,df;

    public Location(int id, int totall, String db, String df) {
        this.id = id;
        this.totall = totall;
        this.db = db;
        this.df = df;
    }
public Location(int totall,String db,String df){
this.totall=totall;
this.db=db;
this.df=df;
}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotall() {
        return totall;
    }

    public void setTotall(int totall) {
        this.totall = totall;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }
@Override
    public String toString() {
        return "Location{" + "id=" + id +  ", date_deb=" + df + ", date_fin=" + df +", total_l=" + totall + '}';
    }
public Location(){
}
}