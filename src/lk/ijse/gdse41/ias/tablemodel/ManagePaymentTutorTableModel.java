/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.tablemodel;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author midda
 */
public class ManagePaymentTutorTableModel {
    private SimpleStringProperty tid=new SimpleStringProperty("");
    private SimpleStringProperty fName=new SimpleStringProperty("");
    private SimpleStringProperty lName=new SimpleStringProperty("");

    public ManagePaymentTutorTableModel() {
    }

    public ManagePaymentTutorTableModel(String tid, String fName, String lName) {
        this.tid.set(tid);
        this.fName.set(fName);
        this.lName.set(lName);
    }

    /**
     * @return the tid
     */
    public String getTid() {
        return tid.get();
    }

    /**
     * @return the fName
     */
    public String getFName() {
        return fName.get();
    }

    /**
     * @return the lName
     */
    public String getLName() {
        return lName.get();
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(String tid) {
        this.tid.set(tid);
    }

    /**
     * @param fName the fName to set
     */
    public void setFName(String fName) {
        this.fName.set(fName);
    }

    /**
     * @param lName the lName to set
     */
    public void setLName(String lName) {
        this.lName.set(lName);
    }
    
    
}
