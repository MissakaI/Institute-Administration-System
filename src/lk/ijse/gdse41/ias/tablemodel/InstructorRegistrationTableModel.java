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
public class InstructorRegistrationTableModel {
    private SimpleStringProperty tid=new SimpleStringProperty("");
    private SimpleStringProperty subId=new SimpleStringProperty("");
    private SimpleStringProperty subject=new SimpleStringProperty("");
    private SimpleStringProperty section=new SimpleStringProperty("");

    public InstructorRegistrationTableModel() {
    }

    public InstructorRegistrationTableModel(String tid, String subId, String subject, String section) {
        this.tid.set(tid);
        this.subId.set(subId);
        this.subject.set(subject);
        this.section.set(section);
    }
    
    /**
     * @return the tid
     */
    public String getTid() {
        return tid.get();
    }

    /**
     * @return the subId
     */
    public String getSubId() {
        return subId.get();
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
     * @param tid the tid to set
     */
    public void setTid(String tid) {
        this.tid.set(tid);
    }

    /**
     * @param subId the subId to set
     */
    public void setSubId(String subId) {
        this.subId.set(subId);
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
}
