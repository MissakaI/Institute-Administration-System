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
public class ManagePaymentPaymentsTableModel {
    private SimpleStringProperty cid = new SimpleStringProperty("");
    private SimpleDoubleProperty amountRecieved = new SimpleDoubleProperty(0.0);
    private SimpleDoubleProperty commission = new SimpleDoubleProperty(0.0);

    public ManagePaymentPaymentsTableModel() {
    }

    public ManagePaymentPaymentsTableModel(String cid, double amountRecieved, double commission) {
        this.cid.set(cid);
        this.amountRecieved.set(amountRecieved);
        this.commission.set(commission);
    }

    /**
     * @return the cid
     */
    public String getCid() {
        return cid.get();
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(String cid) {
        this.cid.set(cid);
    }

    /**
     * @return the amountRecieved
     */
    public double getAmountRecieved() {
        return amountRecieved.get();
    }

    /**
     * @param amountRecieved the amountRecieved to set
     */
    public void setAmountRecieved(double amountRecieved) {
        this.amountRecieved.set(amountRecieved);
    }

    /**
     * @return the commission
     */
    public double getCommission() {
        return commission.get();
    }

    /**
     * @param commission the commission to set
     */
    public void setCommission(double commission) {
        this.commission.set(commission);
    }
    
    
}
