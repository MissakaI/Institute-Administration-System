/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.tablemodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author midda
 */
public class HallTableModel {
    private SimpleStringProperty hid=new SimpleStringProperty("");
    private SimpleIntegerProperty seatingCap=new SimpleIntegerProperty(0);
    private SimpleStringProperty ac=new SimpleStringProperty("Not-Available");

    public HallTableModel() {
    }
    
    public HallTableModel(String hid, int seatingCap, String ac) {
        this.hid.set(hid);
        this.seatingCap.set(seatingCap);
        this.ac.set(ac);
    }

    /**
     * @return the hid
     */
    public String getHid() {
        return hid.get();
    }

    /**
     * @return the seatingCap
     */
    public int getSeatingCap() {
        return seatingCap.get();
    }

    /**
     * @return the ac
     */
    public String getAc() {
        return ac.get();
    }

    /**
     * @param hid the hid to set
     */
    public void setHid(String hid) {
        this.hid.set(hid);
    }

    /**
     * @param seatingCap the seatingCap to set
     */
    public void setSeatingCap(int seatingCap) {
        this.seatingCap.set(seatingCap);
    }

    /**
     * @param ac the ac to set
     */
    public void setAc(String ac) {
        this.ac.set(ac);
    }
    
}
