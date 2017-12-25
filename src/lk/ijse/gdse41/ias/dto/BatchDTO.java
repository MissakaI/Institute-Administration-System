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
public class BatchDTO  extends SuperDTO{
    private String bid;
    private String bName;
    private int year;
    private String section;

    public BatchDTO() {
    }

    public BatchDTO(String bid, String bName, int year, String section) {
        this.bid = bid;
        this.bName = bName;
        this.year = year;
        this.section = section;
    }

    /**
     * @return the bid
     */
    public String getBid() {
        return bid;
    }

    /**
     * @return the bName
     */
    public String getbName() {
        return bName;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * @param bid the bid to set
     */
    public void setBid(String bid) {
        this.bid = bid;
    }

    /**
     * @param bName the bName to set
     */
    public void setbName(String bName) {
        this.bName = bName;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @param section the section to set
     */
    public void setSection(String section) {
        this.section = section;
    }
    
    
}