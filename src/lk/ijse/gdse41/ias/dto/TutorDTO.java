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
public class TutorDTO extends SuperDTO{
    private String tid;
    private String titleName;
    private String fName;
    private String lName;
    private String address;
    private String NIC;
    private String telPrimary;
    private String telSecondary;
    private String username;
    private String password;
    
    private String subId;
    private String bid;

    public TutorDTO() {
    }
    
    public TutorDTO(String subId, String bid){
        this.subId=subId;
        this.bid=bid;
    }

    public TutorDTO(String tid, String titleName, String fName, String lName, String address, String NIC, String telPrimary, String telSecondary, String username, String password) {
        this.tid = tid;
        this.titleName = titleName;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.NIC = NIC;
        this.telPrimary = telPrimary;
        this.telSecondary = telSecondary;
        this.username = username;
        this.password = password;
    }

    /**
     * @return the tid
     */
    public String getTid() {
        return tid;
    }

    /**
     * @return the titleName
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @return the lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the NIC
     */
    public String getNIC() {
        return NIC;
    }

    /**
     * @return the telPrimary
     */
    public String getTelPrimary() {
        return telPrimary;
    }

    /**
     * @return the telSecondary
     */
    public String getTelSecondary() {
        return telSecondary;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * @param titleName the titleName to set
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @param lName the lName to set
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param NIC the NIC to set
     */
    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    /**
     * @param telPrimary the telPrimary to set
     */
    public void setTelPrimary(String telPrimary) {
        this.telPrimary = telPrimary;
    }

    /**
     * @param telSecondary the telSecondary to set
     */
    public void setTelSecondary(String telSecondary) {
        this.telSecondary = telSecondary;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the subId
     */
    public String getSubId() {
        return subId;
    }

    /**
     * @param subId the subId to set
     */
    public void setSubId(String subId) {
        this.subId = subId;
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