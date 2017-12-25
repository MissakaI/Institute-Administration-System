/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom.impl;

import lk.ijse.gdse41.ias.controller.custom.PaymentController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.PaymentDAO;
import lk.ijse.gdse41.ias.dao.custom.QueryDAO;
import lk.ijse.gdse41.ias.dto.PaymentDTO;

/**
 *
 * @author midda
 */
public class PaymentControllerImpl implements PaymentController{

    PaymentDAO paymentDAO;
    public PaymentControllerImpl() {
        paymentDAO=(PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    }

    @Override
    public boolean add(PaymentDTO dto) throws Exception {
        return paymentDAO.add(dto);
    }

    @Override
    public PaymentDTO search(PaymentDTO dto) throws Exception {
        return paymentDAO.search(dto);
    }

    @Override
    public PaymentDTO getTutorsMonthlyPayment(String cid, int month) throws Exception {
        QueryDAO queryDAO=(QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
        return queryDAO.getTutorsMonthlyPayment(cid, month);
    }
}
