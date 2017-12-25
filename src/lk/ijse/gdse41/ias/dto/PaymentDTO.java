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
public class PaymentDTO  extends SuperDTO{
    private String pid;
    private String rid;
    private int p_month;
    private String date;
    private boolean free_card;
    
    private int ridCount;
    private int pidCount;
    private int free_cardCount;

    public PaymentDTO() {
    }
    
    public PaymentDTO(String rid, boolean free_card) {
        this.rid = rid;
        this.free_card = free_card;
    }

    public PaymentDTO(String pid, String rid, int p_month, String date, boolean free_card) {
        this.pid = pid;
        this.rid = rid;
        this.p_month = p_month;
        this.date = date;
        this.free_card = free_card;
    }

    public PaymentDTO(int ridCount, int pidCount, int free_cardCount) {
        this.ridCount = ridCount;
        this.pidCount = pidCount;
        this.free_cardCount = free_cardCount;
    }
    
    
    

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @return the rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * @return the p_month
     */
    public int getP_month() {
        return p_month;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the free_card
     */
    public boolean isFree_card() {
        return free_card;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    /**
     * @param p_month the p_month to set
     */
    public void setP_month(int p_month) {
        this.p_month = p_month;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @param free_card the free_card to set
     */
    public void setFree_card(boolean free_card) {
        this.free_card = free_card;
    }

    /**
     * @return the ridCount
     */
    public int getRidCount() {
        return ridCount;
    }

    /**
     * @param ridCount the ridCount to set
     */
    public void setRidCount(int ridCount) {
        this.ridCount = ridCount;
    }

    /**
     * @return the pidCount
     */
    public int getPidCount() {
        return pidCount;
    }

    /**
     * @param pidCount the pidCount to set
     */
    public void setPidCount(int pidCount) {
        this.pidCount = pidCount;
    }

    /**
     * @return the free_cardCount
     */
    public int getFree_cardCount() {
        return free_cardCount;
    }

    /**
     * @param free_cardCount the free_cardCount to set
     */
    public void setFree_cardCount(int free_cardCount) {
        this.free_cardCount = free_cardCount;
    }
}
