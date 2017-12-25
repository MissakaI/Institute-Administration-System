/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.tablemodel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author midda
 */
public class StudentRegistrationTableModel {
    private SimpleStringProperty tutor=new SimpleStringProperty("");
    private SimpleStringProperty subject=new SimpleStringProperty("");
    private SimpleStringProperty section=new SimpleStringProperty("");
    private SimpleIntegerProperty batch=new SimpleIntegerProperty(2000);
    private SimpleDoubleProperty fee=new SimpleDoubleProperty(0.0);

    public StudentRegistrationTableModel() {
    }
    
    public StudentRegistrationTableModel(String tutor, String subject, String section, int batch, double fee) {
        this.tutor.set(tutor);
        this.subject.set(subject);
        this.section.set(section);
        this.batch.set(batch);
        this.fee.set(fee);
    }
    
    /**
     * @return the tutor
     */
    public String getTutor() {
        return tutor.get();
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject.get();
    }

    /**
     * @return the section
     */
    public String getSection() {
        return section.get();
    }

    /**
     * @return the batch
     */
    public int getBatch() {
        return batch.get();
    }

    /**
     * @return the fee
     */
    public double getFee() {
        return fee.get();
    }

    /**
     * @param tutor the tutor to set
     */
    public void setTutor(String tutor) {
        this.tutor.set(tutor);
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    /**
     * @param section the section to set
     */
    public void setSection(String section) {
        this.section.set(section);
    }

    /**
     * @param batch the batch to set
     */
    public void setBatch(int batch) {
        this.batch.set(batch);
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(double fee) {
        this.fee.set(fee);
    }
    
    
}
