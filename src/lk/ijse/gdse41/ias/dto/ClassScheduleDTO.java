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
public class ClassScheduleDTO  extends BatchDTO{
    private String cid;
    private String tsid;
    private String bid;
    private String hid_1;
    private String hid_2;
    private int c_day;
    private String time;
    private int duration;
    private double m_fee;
    private double reg_fee;
    
    private int studentCount;

    public ClassScheduleDTO() {
    }

    public ClassScheduleDTO(String cid, String tsid, String bid, String hid_1, String hid_2, int c_day, String time, int duration, double m_fee, double reg_fee, int studentCount) {
        this.cid = cid;
        this.tsid = tsid;
        this.bid = bid;
        this.hid_1 = hid_1;
        this.hid_2 = hid_2;
        this.c_day = c_day;
        this.duration = duration;
        this.time = time;
        this.m_fee = m_fee;
        this.reg_fee = reg_fee;
        this.studentCount=studentCount;
    }

    /**
     * @return the cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @return the tsid
     */
    public String getTsid() {
        return tsid;
    }

    /**
     * @return the bid
     */
    public String getBid() {
        return bid;
    }

    /**
     * @return the hid_1
     */
    public String getHid_1() {
        return hid_1;
    }

    /**
     * @return the hid_2
     */
    public String getHid_2() {
        return hid_2;
    }

    /**
     * @return the c_day
     */
    public int getC_day() {
        return c_day;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @return the m_fee
     */
    public double getM_fee() {
        return m_fee;
    }

    /**
     * @return the reg_fee
     */
    public double getReg_fee() {
        return reg_fee;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * @param tsid the tsid to set
     */
    public void setTsid(String tsid) {
        this.tsid = tsid;
    }

    /**
     * @param bid the bid to set
     */
    public void setBid(String bid) {
        this.bid = bid;
    }

    /**
     * @param hid_1 the hid_1 to set
     */
    public void setHid_1(String hid_1) {
        this.hid_1 = hid_1;
    }

    /**
     * @param hid_2 the hid_2 to set
     */
    public void setHid_2(String hid_2) {
        this.hid_2 = hid_2;
    }

    /**
     * @param c_day the c_day to set
     */
    public void setC_day(int c_day) {
        this.c_day = c_day;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @param m_fee the m_fee to set
     */
    public void setM_fee(double m_fee) {
        this.m_fee = m_fee;
    }

    /**
     * @param reg_fee the reg_fee to set
     */
    public void setReg_fee(double reg_fee) {
        this.reg_fee = reg_fee;
    }

    /**
     * @return the studentCount
     */
    public int getStudentCount() {
        return studentCount;
    }

    /**
     * @param studentCount the studentCount to set
     */
    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    
}

//        cid VARCHAR(5) PRIMARY KEY,
//	tsid VARCHAR(5) REFERENCES tutor_subject(tsid)
//	ON UPDATE CASCADE ON DELETE CASCADE,
//	bid VARCHAR(5) REFERENCES batch(bid)
//	ON UPDATE CASCADE ON DELETE CASCADE,
//	hid_1 VARCHAR(15) NOT NULL REFERENCES hall(hid)
//	ON UPDATE CASCADE ON DELETE CASCADE,
//	hid_2 VARCHAR(15) REFERENCES hall(hid)
//	ON UPDATE CASCADE ON DELETE CASCADE,
//	c_day INTEGER,
//	c_time TIME,
//	m_fee DECIMAL(6,2),
//	reg_fee DECIMAL(6,2)
