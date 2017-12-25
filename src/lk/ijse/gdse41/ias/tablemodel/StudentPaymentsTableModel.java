/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.tablemodel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author midda
 */
public class StudentPaymentsTableModel {
    private SimpleStringProperty sid=new SimpleStringProperty("");
    private SimpleStringProperty classDetalis=new SimpleStringProperty("");
    private SimpleDoubleProperty amount=new SimpleDoubleProperty(0.0);

    public StudentPaymentsTableModel() {
    }

    public StudentPaymentsTableModel(String sid, String classDetalis, double amount) {
        this.sid.set(sid);
        this.classDetalis.set(classDetalis);
        this.amount.set(amount);
    }


    /**
     * @return the sid
     */
    public String getSid() {
        return sid.get();
    }

    /**
     * @return the class_detalis
     */
    public String getClassDetalis() {
        return classDetalis.get();
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount.get();
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid.set(sid);
    }

    /**
     * @param class_detalis the class_detalis to set
     */
    public void setClassDetalis(String classDetalis) {
        this.classDetalis.set(classDetalis);
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount.set(amount);
    }
    
}
