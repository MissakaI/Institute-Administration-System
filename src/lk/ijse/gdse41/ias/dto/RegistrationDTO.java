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
public class RegistrationDTO extends SuperDTO{
    private String rid;
    private String sid;
    private String cid;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String rid, String sid, String cid) {
        this.rid = rid;
        this.sid = sid;
        this.cid = cid;
    }
    

    /**
     * @return the rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * @return the sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @return the cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(String cid) {
        this.cid = cid;
    }
    
    
}
