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
public class StudentDTO extends SuperDTO{
    private String sid;
    private String fName;
    private String lName;
    private String address;
    private String DOB;
    private boolean gender;
    private String NIC;
    private String StudentTele;
    private String guardian;
    private String guardianTele;
    private String BID;
    private String photo;
    
    private String cid;

    public StudentDTO() {
    }

    public StudentDTO(String sid, String fName, String lName, String address, String DOB, boolean gender, String NIC, String StudentTele, String guardian, String guardianTele, String BID, String photo) {
        this.sid = sid;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.DOB = DOB;
        this.gender = gender;
        this.NIC = NIC;
        this.StudentTele = StudentTele;
        this.guardian = guardian;
        this.guardianTele = guardianTele;
        this.BID = BID;
        this.photo = photo;
    }
    
    

    /**
     * @return the sid
     */
    public String getSid() {
        return sid;
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
     * @return the DOB
     */
    public String getDOB() {
        return DOB;
    }

    /**
     * @return the gender
     */
    public boolean getGender() {
        return gender;
    }

    /**
     * @return the NIC
     */
    public String getNIC() {
        return NIC;
    }

    /**
     * @return the StudentTele
     */
    public String getStudentTele() {
        return StudentTele;
    }

    /**
     * @return the guardian
     */
    public String getGuardian() {
        return guardian;
    }

    /**
     * @return the guardianTele
     */
    public String getGuardianTele() {
        return guardianTele;
    }

    /**
     * @return the BID
     */
    public String getBID() {
        return BID;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
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
     * @param DOB the DOB to set
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    /**
     * @param NIC the NIC to set
     */
    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    /**
     * @param StudentTele the StudentTele to set
     */
    public void setStudentTele(String StudentTele) {
        this.StudentTele = StudentTele;
    }

    /**
     * @param guardian the guardian to set
     */
    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    /**
     * @param guardianTele the guardianTele to set
     */
    public void setGuardianTele(String guardianTele) {
        this.guardianTele = guardianTele;
    }

    /**
     * @param BID the BID to set
     */
    public void setBID(String BID) {
        this.BID = BID;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return the cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    
}