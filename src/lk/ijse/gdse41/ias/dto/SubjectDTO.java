/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dto;

/**
 *
 * @author midda
 */
public class SubjectDTO extends SuperDTO{
    private String subID;
    private String subName;
    private String grade;
    
    private String bid;

    public SubjectDTO() {
    }
    
    public SubjectDTO(String bid) {
        this.bid=bid;
    }

    public SubjectDTO(String subID, String subName, String grade) {
        this.subID = subID;
        this.subName = subName;
        this.grade = grade;
    }
   

    /**
     * @return the subID
     */
    public String getSubID() {
        return subID;
    }

    /**
     * @return the subName
     */
    public String getSubName() {
        return subName;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param subID the subID to set
     */
    public void setSubID(String subID) {
        this.subID = subID;
    }

    /**
     * @param subName the subName to set
     */
    public void setSubName(String subName) {
        this.subName = subName;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return the bid
     */
    public String getBid() {
        return bid;
    }

    /**
     * @param bid the bid to set
     */
    public void setBid(String bid) {
        this.bid = bid;
    }
    
    
}

//CREATE TABLE subject(
//	subID VARCHAR(5) PRIMARY KEY,
//	subName VARCHAR(10) NOT NULL,
//	grade VARCHAR(10) NOT NULL,
//	UNIQUE KEY(subName,grade)
//);