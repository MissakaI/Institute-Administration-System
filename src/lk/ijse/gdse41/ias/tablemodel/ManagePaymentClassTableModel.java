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
public class ManagePaymentClassTableModel {
    private SimpleStringProperty cid=new SimpleStringProperty("");
    private SimpleStringProperty subject=new SimpleStringProperty("");
    private SimpleStringProperty b_section=new SimpleStringProperty("");
    private SimpleIntegerProperty b_year=new SimpleIntegerProperty(0);

    public ManagePaymentClassTableModel() {
    }

    public ManagePaymentClassTableModel(String cid, String subject, String b_section, int b_year) {
        this.cid.set(cid);
        this.subject.set(subject);
        this.b_section.set(b_section);
        this.b_year.set(b_year);
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
     * @return the subject
     */
    public String getSubject() {
        return subject.get();
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    /**
     * @return the b_section
     */
    public String getB_section() {
        return b_section.get();
    }

    /**
     * @param b_section the b_section to set
     */
    public void setB_section(String b_section) {
        this.b_section.set(b_section);
    }

    /**
     * @return the b_year
     */
    public int getB_year() {
        return b_year.get();
    }

    /**
     * @param b_year the b_year to set
     */
    public void setB_year(int b_year) {
        this.b_year.set(b_year);
    }
    
    
    
}
