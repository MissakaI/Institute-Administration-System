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
public class TutorPaymentReportDTO extends TutorPaymentDTO{
    private String section_subject_batch_classId;
    private String month;

    public TutorPaymentReportDTO() {
    }

    public TutorPaymentReportDTO(String section_subject_batch_classId) {
        this.section_subject_batch_classId = section_subject_batch_classId;
    }
    
    public TutorPaymentReportDTO(String section_subject_batch_classId, String month, String tpid, String tid, String cid, String pay_date, int pay_month, int no_of_students, int no_of_free_cards, double total_amount, double commission_rate, double commission, double pay_amount) {
        super(tpid, tid, cid, pay_date, pay_month, no_of_students, no_of_free_cards, total_amount, commission_rate, commission, pay_amount);
        this.section_subject_batch_classId = section_subject_batch_classId;
        this.month=month;
    }

    /**
     * @return the section_subject_batch_classId
     */
    public String getSection_subject_batch_classId() {
        return section_subject_batch_classId;
    }

    /**
     * @param section_subject_batch_classId the section_subject_batch_classId to set
     */
    public void setSection_subject_batch_classId(String section_subject_batch_classId) {
        this.section_subject_batch_classId = section_subject_batch_classId;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    public TutorPaymentDTO getTutorPaymentDTO(){
        return new TutorPaymentDTO(this.getTpid(), this.getTid(), this.getCid(), this.getPay_date(), this.getPay_month(), this.getNo_of_students(), this.getNo_of_free_cards(), this.getTotal_amount(), this.getCommission_rate(), this.getCommission(), this.getPay_amount());
    }
    
}
