/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.custom.TutorPaymentController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.TutorPaymentDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.TutorPaymentDTO;

/**
 *
 * @author midda
 */
public class TutorPaymentControllerImpl implements TutorPaymentController{

    TutorPaymentDAO tutorPaymentDAO;
    public TutorPaymentControllerImpl() {
        tutorPaymentDAO=(TutorPaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TUTOR_PAYMENT);
    }
    
    @Override
    public boolean add(ArrayList<TutorPaymentDTO> dtoList) throws Exception {
        for (TutorPaymentDTO tutorPaymentDTO : dtoList) {
            if(!add(tutorPaymentDTO)){
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean add(TutorPaymentDTO dto) throws Exception {
        return tutorPaymentDAO.add(dto);
    }

    @Override
    public TutorPaymentDTO search(TutorPaymentDTO dto) throws Exception {
        return tutorPaymentDAO.search(dto);
    }
   
}
