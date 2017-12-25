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
public class StudentAttendanceTableModel {
    private SimpleStringProperty rid=new SimpleStringProperty("");
    private SimpleStringProperty sid=new SimpleStringProperty("");
    private SimpleStringProperty date=new SimpleStringProperty("");
    private SimpleStringProperty checkIn=new SimpleStringProperty("");
    private SimpleStringProperty paymentStatus=new SimpleStringProperty("");

    public StudentAttendanceTableModel() {
    }

    public StudentAttendanceTableModel(String rid, String sid, String date, String checkIn, String paymentStatus) {
        this.rid.set(rid);
        this.sid.set(sid);
        this.date.set(date);
        this.checkIn.set(checkIn);
        this.paymentStatus.set(paymentStatus);
    }
    

    /**
     * @return the rid
     */
    public String getRid() {
        return rid.get();
    }

    /**
     * @return the sid
     */
    public String getSid() {
        return sid.get();
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date.get();
    }

    /**
     * @return the checkIn
     */
    public String getCheckIn() {
        return checkIn.get();
    }

    /**
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus.get();
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(String rid) {
        this.rid.set(rid);
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid.set(sid);
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date.set(date);
    }

    /**
     * @param checkIn the checkIn to set
     */
    public void setCheckIn(String checkIn) {
        this.checkIn.set(checkIn);
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus.set(paymentStatus);
    }
    
    
}
