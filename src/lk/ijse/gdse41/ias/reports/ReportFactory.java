/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.reports;

import java.util.ArrayList;
import java.util.Collection;
import lk.ijse.gdse41.ias.dto.SuperDTO;
import lk.ijse.gdse41.ias.dto.TutorPaymentReportDTO;

/**
 *
 * @author midda
 */
public class ReportFactory {

    public static ArrayList<SuperDTO> dtoList=new ArrayList<>();
    
    public static Collection generateCollection(){
//        TutorPaymentReportDTO tpdto=new TutorPaymentReportDTO("Section-subject-batch(ClassId)","May","TP", "TID", "CS", "2017-06-01", 6, 50, 12, 1234.00, 20.0, 123.0, 1234.00);
//        tpdto.setPrev_pay_amount(12.0);
//        tpdto.setPrev_pay_Date("2017-05-20");
//        dtoList.add(tpdto);
        return dtoList;
    }
    
    
    
    
    
    
    
}
