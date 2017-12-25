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
public class TutorPaymentDTO extends SuperDTO{
    private String tpid;
    private String tid;
    private String cid;
    private String pay_date;
    private int pay_month;
    private int no_of_students;
    private int no_of_free_cards;
    private double total_amount;
    private double commission_rate;
    private double commission;
    private double pay_amount;
    
    private String prev_pay_Date;
    private double prev_pay_amount;

    public TutorPaymentDTO() {
    }

    public TutorPaymentDTO(String tpid, String tid, String cid, String pay_date, int pay_month, int no_of_students, int no_of_free_cards, double total_amount, double commission_rate, double commission, double pay_amount) {
        this.tpid = tpid;
        this.tid = tid;
        this.cid = cid;
        this.pay_date = pay_date;
        this.pay_month = pay_month;
        this.no_of_students = no_of_students;
        this.no_of_free_cards = no_of_free_cards;
        this.total_amount = total_amount;
        this.commission_rate = commission_rate;
        this.commission = commission;
        this.pay_amount = pay_amount;
    }

    /**
     * @return the tpid
     */
    public String getTpid() {
        return tpid;
    }

    /**
     * @param tpid the tpid to set
     */
    public void setTpid(String tpid) {
        this.tpid = tpid;
    }

    /**
     * @return the tid
     */
    public String getTid() {
        return tid;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(String tid) {
        this.tid = tid;
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

    /**
     * @return the pay_date
     */
    public String getPay_date() {
        return pay_date;
    }

    /**
     * @param pay_date the pay_date to set
     */
    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    /**
     * @return the pay_month
     */
    public int getPay_month() {
        return pay_month;
    }

    /**
     * @param pay_month the pay_month to set
     */
    public void setPay_month(int pay_month) {
        this.pay_month = pay_month;
    }

    /**
     * @return the no_of_students
     */
    public int getNo_of_students() {
        return no_of_students;
    }

    /**
     * @param no_of_students the no_of_students to set
     */
    public void setNo_of_students(int no_of_students) {
        this.no_of_students = no_of_students;
    }

    /**
     * @return the no_of_free_cards
     */
    public int getNo_of_free_cards() {
        return no_of_free_cards;
    }

    /**
     * @param no_of_free_cards the no_of_free_cards to set
     */
    public void setNo_of_free_cards(int no_of_free_cards) {
        this.no_of_free_cards = no_of_free_cards;
    }

    /**
     * @return the total_amount
     */
    public double getTotal_amount() {
        return total_amount;
    }

    /**
     * @param total_amount the total_amount to set
     */
    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    /**
     * @return the commission_rate
     */
    public double getCommission_rate() {
        return commission_rate;
    }

    /**
     * @param commission_rate the commission_rate to set
     */
    public void setCommission_rate(double commission_rate) {
        this.commission_rate = commission_rate;
    }

    /**
     * @return the commission
     */
    public double getCommission() {
        return commission;
    }

    /**
     * @param commission the commission to set
     */
    public void setCommission(double commission) {
        this.commission = commission;
    }

    /**
     * @return the pay_amount
     */
    public double getPay_amount() {
        return pay_amount;
    }

    /**
     * @param pay_amount the pay_amount to set
     */
    public void setPay_amount(double pay_amount) {
        this.pay_amount = pay_amount;
    }

    /**
     * @return the prev_pay_Date
     */
    public String getPrev_pay_Date() {
        return prev_pay_Date;
    }

    /**
     * @param prev_pay_Date the prev_pay_Date to set
     */
    public void setPrev_pay_Date(String prev_pay_Date) {
        this.prev_pay_Date = prev_pay_Date;
    }

    /**
     * @return the prev_pay_amount
     */
    public double getPrev_pay_amount() {
        return prev_pay_amount;
    }

    /**
     * @param prev_pay_amount the prev_pay_amount to set
     */
    public void setPrev_pay_amount(double prev_pay_amount) {
        this.prev_pay_amount = prev_pay_amount;
    }
    
}
