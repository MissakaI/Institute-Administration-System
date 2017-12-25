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
public class HallDTO extends SuperDTO{
    private String hid;
    private int seatingCap;
    private boolean ac;

    public HallDTO() {
    }

    public HallDTO(String hid, int seatingCap, boolean ac) {
        this.hid = hid;
        this.seatingCap = seatingCap;
        this.ac = ac;
    }

    /**
     * @return the hid
     */
    public String getHid() {
        return hid;
    }

    /**
     * @return the seatingCap
     */
    public int getSeatingCap() {
        return seatingCap;
    }

    /**
     * @return the ac
     */
    public boolean isAc() {
        return ac;
    }

    /**
     * @param hid the hid to set
     */
    public void setHid(String hid) {
        this.hid = hid;
    }

    /**
     * @param seatingCap the seatingCap to set
     */
    public void setSeatingCap(int seatingCap) {
        this.seatingCap = seatingCap;
    }

    /**
     * @param ac the ac to set
     */
    public void setAc(boolean ac) {
        this.ac = ac;
    }
    
    
    
    
}
