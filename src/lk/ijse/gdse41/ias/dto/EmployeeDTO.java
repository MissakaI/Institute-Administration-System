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
public class EmployeeDTO extends SuperDTO{
    private String eid;
    private String titleName;
    private String fName;
    private String lName;
    private String address;
    private String dob;
    private String nic;
    private int age;
    private String tpNo1;
    private String tpNo2;
    private String username;
    private String password;
    private String accessLevel;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String eid, String titleName, String fName, String lName, String address, String dob, String nic, int age, String tpNo1, String tpNo2, String username, String password, String accessLevel) {
        this.eid = eid;
        this.titleName = titleName;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.dob = dob;
        this.nic = nic;
        this.age = age;
        this.tpNo1 = tpNo1;
        this.tpNo2 = tpNo2;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    /**
     * @return the eid
     */
    public String getEid() {
        return eid;
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
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the tpNo1
     */
    public String getTpNo1() {
        return tpNo1;
    }

    /**
     * @return the tpNo2
     */
    public String getTpNo2() {
        return tpNo2;
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
     * @return the accessLevel
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
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
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @param tpNo1 the tpNo1 to set
     */
    public void setTpNo1(String tpNo1) {
        this.tpNo1 = tpNo1;
    }

    /**
     * @param tpNo2 the tpNo2 to set
     */
    public void setTpNo2(String tpNo2) {
        this.tpNo2 = tpNo2;
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
     * @param accessLevel the accessLevel to set
     */
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * @return the titleName
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * @param titleName the titleName to set
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
    
    
}
