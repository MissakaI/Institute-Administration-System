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
public class TutorSubjectDTO extends SubjectDTO{
    private String tsid;
    private String tid;
    private String subId;

    public TutorSubjectDTO() {
    }

    public TutorSubjectDTO(String tsid, String tid, String subId) {
        this.tsid = tsid;
        this.tid = tid;
        this.subId = subId;
    }

    /**
     * @return the tsid
     */
    public String getTsid() {
        return tsid;
    }

    /**
     * @return the tid
     */
    public String getTid() {
        return tid;
    }

    /**
     * @return the subId
     */
    public String getSubId() {
        return subId;
    }

    /**
     * @param tsid the tsid to set
     */
    public void setTsid(String tsid) {
        this.tsid = tsid;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * @param subId the subId to set
     */
    public void setSubId(String subId) {
        this.subId = subId;
    }
}
