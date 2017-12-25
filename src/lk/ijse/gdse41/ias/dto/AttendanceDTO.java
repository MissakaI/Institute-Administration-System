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
public class AttendanceDTO extends SuperDTO{
    private String id;
    private String a_date;
    private String check_in;
    private String check_out;

    public AttendanceDTO() {
    }
    
    public AttendanceDTO(String rid, String a_date, String check_in) {
        this.id = rid;
        this.a_date = a_date;
        this.check_in = check_in;
    }

    public AttendanceDTO(String eid, String a_date, String check_in, String check_out) {
        this.id = eid;
        this.a_date = a_date;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public AttendanceDTO(String cid, String a_date) {
        this.id = cid;
        this.a_date = a_date;
    }
    
    

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the a_date
     */
    public String getA_date() {
        return a_date;
    }

    /**
     * @return the check_in
     */
    public String getCheck_in() {
        return check_in;
    }

    /**
     * @return the check_out
     */
    public String getCheck_out() {
        return check_out;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param a_date the a_date to set
     */
    public void setA_date(String a_date) {
        this.a_date = a_date;
    }

    /**
     * @param check_in the check_in to set
     */
    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    /**
     * @param check_out the check_out to set
     */
    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }
    
    
}
